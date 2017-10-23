package com.rxxt.wcas.mapreduce.pre;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.rxxt.wcas.po.WebLogBean;
import com.rxxt.wcas.po.WebLogParser;
import com.rxxt.wcas.preworkdir.PreWorkDir;
import com.rxxt.wcas.util.GetConfigUtil;
import com.rxxt.wcas.util.TransUtil;




/**
 * 处理原始日志，过滤出真实pv请求
 * 转换时间格式
 * 对缺失字段填充默认�??
 * 对记录标记valid和invalid
 * 
 * @author
 *
 */
public class WeblogPreProcess {

	static Properties config;
	static class WeblogPreProcessMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		//用来存储网站url分类数据
		
		Set<String> pages = new HashSet<String>();
		
		NullWritable v = NullWritable.get();

		/**
		 * 从外部加载网站url分类数据
		 */
		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			/**
			 * 设计有效数据关键字，通过判断是否日志信息中是否包含这些关键字来判断访问数据是否有�?
			 */
			pages.add("/about");
			pages.add("/black-ip-list/");
			pages.add("/cassandra-clustor/");
			pages.add("/finance-rhive-repurchase/");
			pages.add("/hadoop-family-roadmap/");
			pages.add("/hadoop-hive-intro/");
			pages.add("/hadoop-zookeeper-intro/");
			pages.add("/hadoop-mahout-roadmap/");
			
		}

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			String line = value.toString().trim();
			//对数据进行处理，将处理之后的数据封装到自定义的WebLogParser数据类型中，
			//由于数据过于复杂，定义一个parser方法，专门用于数据处�?
			if(!line.equals("")){
				WebLogBean webLogBean = WebLogParser.parser(line);
				// 过滤js/图片/css等静态资源，可以设置也可以不设置，按照业务需求来
				WebLogParser.filtStaticResource(webLogBean, pages);
				  if(webLogBean.isValid()){
					  Text k = new Text();
					  k.set(webLogBean.toString());
					  context.write(k, v);
				  }
			}
			
			
		}

	}

	public static void step2() throws Exception {
		config=GetConfigUtil.getPathConfig();
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(WeblogPreProcess.class);

		job.setMapperClass(WeblogPreProcessMapper.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
		FileInputFormat.setInputPaths(job, new Path(config.getProperty("target_path1")+"*/"));
		FileSystem fs = FileSystem.get(new URI(config.getProperty("hdfs_ip")), conf, "root");
	
		if(fs.exists(new Path(config.getProperty("target_path2")))){
			fs.delete(new Path(config.getProperty("target_path2")), true);
		}
		FileOutputFormat.setOutputPath(job, new Path(config.getProperty("target_path2")+TransUtil.getDate()+"/"));

		job.setNumReduceTasks(0);
		
		job.waitForCompletion(true);

	}

}
