package com.rxxt.wcas.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class GetConfigUtil {
     static Properties config=new Properties();
     static public Properties getPathConfig(){
    	 try {
			config.load(new FileReader(GetConfigUtil.class.getClassLoader().getResource("path.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return config;
     }
     static public Properties getDBConfig(){
    	 try {
			config.load(new FileReader(GetConfigUtil.class.getClassLoader().getResource("db.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return config;
     }
     static public Properties getDBConfig2(){
    	 try {
			config.load(new FileReader(GetConfigUtil.class.getClassLoader().getResource("db2.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return config;
     }
}
