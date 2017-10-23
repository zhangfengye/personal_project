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
				// ���зֳ����ĸ��ֶ�set��weblogbean��
				v.set("true".equals(fields[0]) ? true : false, fields[1], fields[2], fields[3], fields[4], fields[5],
						fields[6], fields[7], fields[8]);
				k.set(v.getRemote_addr());
				// �����û�id���з���
				context.write(k, v);
			}
		}
	}

	static class PageViewReducer extends Reducer<Text, WebLogBean, NullWritable, Text> {

		Text v = new Text();

		@Override
		protected void reduce(Text key, Iterable<WebLogBean> values, Context context)
				throws IOException, InterruptedException {
			// �����������ڽ���reduce��ȡ��values�е�����
			ArrayList<WebLogBean> beans = new ArrayList<WebLogBean>();
			// ѭ��ȡ��value�е��û����ʼ�¼
			for (WebLogBean bean : values) {
				// ����WebLogBean�������ڽ���ȡ��������
				WebLogBean webLogBean = new WebLogBean();
				try {
					// ��ȡ���Ķ������Կ�����webLogBean������
					BeanUtils.copyProperties(webLogBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// ����װ������ֵ��webLogBean������ӵ������У��������ݱȽ�
				beans.add(webLogBean);
			}

			/**
			 * �����߼�Ϊ��������bean�зֱ������visit������һ��visit�������ʵ�page��˳����step
			 */

			int step = 1;
			// ����һ�������uuid��Ϊsessionid
			String session = UUID.randomUUID().toString();
			for (int i = 0; i < beans.size(); i++) {
				WebLogBean bean = beans.get(i);
				// �������1�����ݣ������û�ֻ������һ��ҳ�棩����ֱ�����
				if (1 == beans.size()) {

					// ����Ĭ��ͣ��ʱ��Ϊ60s
					v.set(session + "\001" + key.toString() + "\001" + bean.getRemote_user() + "\001"
							+ bean.getTime_local() + "\001" + bean.getRequest() + "\001" + step + "\001" + (60) + "\001"
							+ bean.getHttp_referer() + "\001" + bean.getHttp_user_agent() + "\001"
							+ bean.getBody_bytes_sent() + "\001" + bean.getStatus());
					context.write(NullWritable.get(), v);
					session = UUID.randomUUID().toString();// ��������sessionid�����ֻ��һ���Ļ������д�����Բ���д
					break;
				}

				// �����ֹ1�����ݣ��򽫵�һ������������������ڶ���ʱ�����
				if (i == 0) {
					continue;
				}
				// �����Զ��庯��timeDiff���������ʱ���
				long timeDiff = TransUtil.timeDiff(bean.getTime_local(), beans.get(i - 1).getTime_local());
				// �������-�ϴ�ʱ���<30���ӣ���Ϊ�����η�������ͬһ��session�У������ǰһ�ε�ҳ�������Ϣ
				if (timeDiff < 30 * 60 * 1000) {
					v.set(session + "\001" + key.toString() + "\001" + beans.get(i - 1).getRemote_user() + "\001"
							+ beans.get(i - 1).getTime_local() + "\001" + beans.get(i - 1).getRequest() + "\001" + step
							+ "\001" + (timeDiff / 1000) + "\001" + beans.get(i - 1).getHttp_referer() + "\001"
							+ beans.get(i - 1).getHttp_user_agent() + "\001" + beans.get(i - 1).getBody_bytes_sent()
							+ "\001" + beans.get(i - 1).getStatus());
					context.write(NullWritable.get(), v);
					step++;
				} else {

					// �������-�ϴ�ʱ���>30���ӣ������ǰһ�ε�ҳ�������Ϣ��Ϊ�˴�session�����һ������ҳ�棬�ҽ�step���ã��Էָ�Ϊ�µ�session
					v.set(session + "\001" + key.toString() + "\001" + beans.get(i - 1).getRemote_user() + "\001"
							+ beans.get(i - 1).getTime_local() + "\001" + beans.get(i - 1).getRequest() + "\001"
							+ (step) + "\001" + (60) + "\001" + beans.get(i - 1).getHttp_referer() + "\001"
							+ beans.get(i - 1).getHttp_user_agent() + "\001" + beans.get(i - 1).getBody_bytes_sent()
							+ "\001" + beans.get(i - 1).getStatus());
					context.write(NullWritable.get(), v);
					// �������һ��֮������step���
					step = 1;
					// ����sessionid��ţ�֮���ѭ���ͻ����µ�sessionid���µ�step��ʼ
					session = UUID.randomUUID().toString();
				}

				// ����˴α����������һ�����򽫱���ֱ���������ʵ���һ��Ҳ����Ҫ�����Ƿ��ǰһ������ʱ�����Ƿ�>30�֣�ֻ�����߼��Ѿ������涨����ˣ������Ƿ����ֱ������Ϳ�����
				if (i == beans.size() - 1) {
					// ����Ĭ��ͣ��ʱ��Ϊ60s
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
