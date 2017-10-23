package com.hpe.onlinexam.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class ToolUtil {
	
	/**
	 * 字符串 非空判断
	 * @return
	 */
	public static boolean isNotNull(String str){
		if(str == null ){
			return false;
		}
		if(str.trim().equals("")){
			return false;
		}
		return true;
	}
	/**
	 * 集合非空 判断
	 * @return
	 */
	public static boolean isNotNull(List list){
		if(list == null){
			return false;
		}
		if(list.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sf.format(date);
	}
	
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}

	public static Date getDate(String date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		try {
			d = sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 根据毫秒计算 天和小时及秒
	 * @param diff
	 * @return
	 */
	public static String getDateDif(long diff){
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
	}
	
	public static String getDateDif(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	/**
	 * 
	 * @param endDate 截止日期
	 * @param nowDate 当前日期
	 * @return
	 */
	public static Map<String,Long> getDayHourSe(Date endDate, Date nowDate) {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    Map<String,Long> map = new HashMap<String, Long>();
	    map.put("d", day);
	    map.put("h", hour);
	    map.put("m", min);
	    map.put("s", sec);
	    return map;
	}

	/**
	 * 计算天，小时，分钟，秒 ，封装成一个map对象
	 * @param diff 毫秒
	 * @return
	 */
	public static Map<String,Long> getDayHourSe(long diff){
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    Map<String,Long> map = new HashMap<String, Long>();
	    map.put("d", day);
	    map.put("h", hour);
	    map.put("m", min);
	    map.put("s", sec);
	    return map;
	}
	public final static String LOGINUSER = "loginUser";

	public static String arraytoString(String[] array) {
		StringBuffer s = new StringBuffer();
		if (null == array)
			return s.toString();
		for (int i = 0; i < array.length; i++) {
			s.append(array[i]);
			if (i != array.length - 1)
				s.append(",");
		}
		return s.toString();
	}
	/**
	 * 转化为UNICODE编码。  // xxxServlet?method=show&name=张三&deptName=开发
	 * @param value
	 * @return
	 */
	public static String encode(String value){
		String newname = null;
		try {
			newname = URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newname;
	}
	
	public static String decode(String value){
		String newName=null;
		try {
			newName = URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newName;
	}
	
	public static void main (String [] args){
		String[] classes = new String[]{"1","2222"};
		System.out.println(ToolUtil.arraytoString(classes));
		System.out.println(encode("你好"));
		String s = encode("你好");
		System.out.println(decode(s));
		long t = 1000 * (2*60*24*60 + 20*60*60 + 2* 60 + 34);
		System.out.println(getDateDif(t));
//7042701
		long diff = 1000*2 * 60;
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long temp = diff % nd;
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    long sec = diff % nd % nh % nm / ns;
	    Map<String,Long> map = new HashMap<String, Long>();
 
	}
}
