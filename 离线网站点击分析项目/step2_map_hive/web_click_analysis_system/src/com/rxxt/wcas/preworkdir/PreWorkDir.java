package com.rxxt.wcas.preworkdir;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import com.rxxt.wcas.util.GetConfigUtil;



public class PreWorkDir {
	private static Logger logger = Logger.getLogger("PreWorkDir");
	static Properties config;
	static Configuration configuration = new Configuration();
public static void step1() throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException {
	 config=GetConfigUtil.getPathConfig();
	//∂®“ÂFileSystem
	FileSystem fileSystem = FileSystem.get(new URI(config.getProperty("hdfs_ip")), configuration, "root");
	diGuiCopy(fileSystem,new Path(config.getProperty("source_path")));
}
public static void diGuiCopy(FileSystem fs,Path p) throws IOException{
              FileStatus[] listStatus = fs.listStatus(p);
              for(FileStatus ft:listStatus){
            	  if(ft.isDirectory()){
            		  diGuiCopy(fs, ft.getPath());
            	  }else{
            		  if(ft.getPath().toString().endsWith(".tmp")){
            			  continue;
            		  }else{
            			  FileUtil.copy(fs,ft.getPath(), fs, new Path(ft.getPath().toString().replaceAll(config.getProperty("source_path"), config.getProperty("target_path1"))), true,true, configuration);
            			 logger.info(ft.getPath().toString().replaceAll(config.getProperty("source_path"), config.getProperty("target_path1")));
            		  }
            	  }
              }
     
	 
    }
}
