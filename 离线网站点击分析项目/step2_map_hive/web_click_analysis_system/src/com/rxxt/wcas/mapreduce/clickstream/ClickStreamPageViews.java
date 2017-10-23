package com.rxxt.wcas.mapreduce.clickstream;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.rxxt.wcas.po.WebLogBean;
import com.rxxt.wcas.util.GetConfigUtil;
import com.rxxt.wcas.util.TransUtil;

public class ClickStreamPageViews {

	static class PageViewMapper extends Mapper<LongWritable, Text, Text, WebLogBean> {
		Text k = new Text();
		WebLogBean v = new WebLogBean();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString().trim();
			if (!line.equals("")) {
				String[] fields = line.split("\001");
				if (fields.length < 9)
					return;
				// 将切分出来的各字段set到weblogbean中
				v.set("true".equals(fields[0]) ? true : false, fields[1], fields[2], fields[3], fields[4], fields[5],
						fields[6], fields[7], fields[8]);
				k.set(v.getRemote_addr());
				// 根据用户id进行分组
				context.write(k, v);
			}
		}
	}

	static class PageViewReducer extends Reducer<Text, WebLogBean, NullWritable, Text> {

		Text v = new Text();

		@Override
		protected void reduce(Text key, Iterable<WebLogBean> values, Context context)
				throws IOException, InterruptedException {
			// 创建容器用于接收reduce读取的values中的数据
			ArrayList<WebLogBean> beans = new ArrayList<WebLogBean>();
			// 循环取出value中的用户访问记录
			for (WebLogBean bean : values) {
				// 创建WebLogBean对象用于接收取出的数据
				WebLogBean webLogBean = new WebLogBean();
				try {
					// 将取出的对象属性拷贝到webLogBean对象中
					BeanUtils.copyProperties(webLogBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 将封装了属性值的webLogBean对象添加到容器中，用于数据比较
				beans.add(webLogBean);
			}

			/**
			 * 以下逻辑为：从有序bean中分辨出各次visit，并对一次visit中所访问的page按顺序标号step
			 */

			int step = 1;
			// 生成一个随机的uuid作为sessionid
			String session = UUID.randomUUID().toString();
			for (int i = 0; i < beans.size(); i++) {
				WebLogBean bean = beans.get(i);
				// 如果仅有1条数据（即该用户只访问了一个页面），则直接输出
				if (1 == beans.size()) {

					// 设置默认停留时长为60s
					v.set(session + "\001" + key.toString() + "\001" + bean.getRemote_user() + "\001"
							+ bean.getTime_local() + "\001" + bean.getRequest() + "\001" + step + "\001" + (60) + "\001"
							+ bean.getHttp_referer() + "\001" + bean.getHttp_user_agent() + "\001"
							+ bean.getBody_bytes_sent() + "\001" + bean.getStatus());
					context.write(NullWritable.get(), v);
					session = UUID.randomUUID().toString();// 重新生成sessionid。如果只有一条的话，此行代码可以不用写
					break;
				}

				// 如果不止1条数据，则将第一条跳过不输出，遍历第二条时再输出
				if (i == 0) {
					continue;
				}
				// 调用自定义函数timeDiff，求近两次时间差
				long timeDiff = TransUtil.timeDiff(bean.getTime_local(), beans.get(i - 1).getTime_local());
				// 如果本次-上次时间差<30分钟，认为这两次访问是在同一个session中，则输出前一次的页面访问信息
				if (timeDiff < 30 * 60 * 1000) {
					v.set(session + "\001" + key.toString() + "\001" + beans.get(i - 1).getRemote_user() + "\001"
							+ beans.get(i - 1).getTime_local() + "\001" + beans.get(i - 1).getRequest() + "\001" + step
							+ "\001" + (timeDiff / 1000) + "\001" + beans.get(i - 1).getHttp_referer() + "\001"
							+ beans.get(i - 1).getHttp_user_agent() + "\001" + beans.get(i - 1).getBody_bytes_sent()
							+ "\001" + beans.get(i - 1).getStatus());
					context.write(NullWritable.get(), v);
					step++;
				} else {

					// 如果本次-上次时间差>30分钟，则输出前一次的页面访问信息作为此次session的最后一个访问页面，且将step重置，以分隔为新的session
					v.set(session + "\001" + key.toString() + "\001" + beans.get(i - 1).getRemote_user() + "\001"
							+ beans.get(i - 1).getTime_local() + "\001" + beans.get(i - 1).getRequest() + "\001"
							+ (step) + "\001" + (60) + "\001" + beans.get(i - 1).getHttp_referer() + "\001"
							+ beans.get(i - 1).getHttp_user_agent() + "\001" + beans.get(i - 1).getBody_bytes_sent()
							+ "\001" + beans.get(i - 1).getStatus());
					context.write(NullWritable.get(), v);
					// 输出完上一条之后，重置step编号
					step = 1;
					// 重置sessionid编号，之后的循环就会以新的sessionid和新的step开始
					session = UUID.randomUUID().toString();
				}

				// 如果此次遍历的是最后一条，则将本条直接输出。其实最后一条也是需要考虑是否和前一条访问时间间隔是否>30分，只不过逻辑已经在上面定义过了，无论是否大于直接输出就可以了
				if (i == beans.size() - 1) {
					// 设置默认停留时长为60s
					v.set(session + "\001" + key.toString() + "\001" + bean.getRemote_user() + "\001"
							+ bean.getTime_local() + "\001" + bean.getRequest() + "\001" + step + "\001" + (60) + "\001"
							+ bean.getHttp_referer() + "\001" + bean.getHttp_user_agent() + "\001"
							+ bean.getBody_bytes_sent() + "\001" + bean.getStatus());
					context.write(NullWritable.get(), v);
				}
			}

		}

	}
	public static void step3() throws Exception {
		Properties config = GetConfigUtil.getPathConfig();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(ClickStreamPageViews.class);
		job.setMapperClass(PageViewMapper.class);
		job.setReducerClass(PageViewReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(WebLogBean.class);
		FileInputFormat.setInputPaths(job, new Path(config.getProperty("target_path2")+"*/"));
		FileSystem fs = FileSystem.get(new URI(config.getProperty("hdfs_ip")), conf, "root");
		if(fs.exists(new Path(config.getProperty("target_path3")))){
			fs.delete(new Path(config.getProperty("target_path3")), true);
		}
		FileOutputFormat.setOutputPath(job, new Path(config.getProperty("target_path3")+TransUtil.getDate()+"/"));
		job.waitForCompletion(true);

	}

}
