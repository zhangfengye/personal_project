package com.rxxt.wcas.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;


public class WebLogParser {
	//用于对日期字符串进行格式�?
	public static SimpleDateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
	public static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	/**
	 * 定义此方法用于对日志数据进行处理�?
	 * 1、处理之前先判断数据是否合法：按照�?? ”拆分，判断时�?�拆分结果数组长度是�?>11,如果大于11则数据合理，否则，认为数据不合法（调用setValid(false)，false不合法，true合法），
	 * 2、如果数据合法，则分别将数据设置到webLogBean的属性中
	 * 
	 * 问题1：为�?么要判断是否大于11，来确定是否合法�?
	 * 答：因为我们的日志数据中�?11个字�?
	 * 问题2：那又为�?么要大于11，�?�不用等�?11�?
	 * 答：因为�?后的“客户浏览器信息”字段格式不统一，里面有可能有�?? ”，我们按照�? ”拆分数据后，拆出的数据个数就可能大�?11
	 * @param line
	 * @return
	 */
	public static WebLogBean parser(String line) {
		WebLogBean webLogBean = new WebLogBean();
		String[] arr = line.split(" ");
		if (arr.length > 11) {
			webLogBean.setRemote_addr(arr[0]);//记录客户端的ip地址
			webLogBean.setRemote_user(arr[1]);//记录客户端用户名�?,忽略属�??"-"
			/**
			 * 调用本类中自定义的日期格式化方法formatDate（）将字符串转化为这种格式的日期�?"yyyy-MM-dd HH:mm:ss"
			 */
			String time_local = formatDate(arr[3].substring(1));//arr[3].substring(1)代表将数据中第四个字段的“[”去掉，得到日期字符串：[18/Sep/2013:06:49:18
			if(null==time_local) time_local="-invalid_time-";//如果时间字符串不合法，导致time_local为null，则赋上默认值�??-invalid_time-�?
			webLogBean.setTime_local(time_local);//如果时间字符串合法，则正常赋值即�?
			webLogBean.setRequest(arr[6]);// 记录请求的url与http协议
			webLogBean.setStatus(arr[8]);// 记录请求状�?�；成功�?200
			webLogBean.setBody_bytes_sent(arr[9]);// 记录发�?�给客户端文件主体内容大�?
			webLogBean.setHttp_referer(arr[10]);// 用来记录从那个页面链接访问过来的
			/**
			 * �?后还剩一个属性useragent（客户浏览器的相关信息）
			 * 但是，由于客户浏览器信息格式不一样，有可能按照�?? ”拆分之后的数组arr的大小是大于11的，那么我们�?要将剩下的这些数组元素拼接起来，作为useragent
			 */
			//如果useragent元素较多，拼接useragent
			if (arr.length > 12) {
				StringBuilder sb = new StringBuilder();
				for(int i=11;i<arr.length;i++){
					sb.append(arr[i]);
				}
				webLogBean.setHttp_user_agent(sb.toString());
			} else {
				webLogBean.setHttp_user_agent(arr[11]);
			}
			//判断“状态码”是否大�?400，如果大�?400则认为数据不合法，将WebLogBean.valid设置为false
			if (Integer.parseInt(webLogBean.getStatus()) >= 400) {// 大于400，HTTP错误
				webLogBean.setValid(false);
			}
			//判断“WebLogBean.time_local”是否等于�??-invalid_time-”，如果是则认为数据不合法，将WebLogBean.valid设置为false
			if("-invalid_time-".equals(webLogBean.getTime_local())){
				webLogBean.setValid(false);
			}
		} else {
			//数据不合法，将此属�?�设置到WebLogBean对象的valid属�?�中
			webLogBean.setValid(false);
		}

		return webLogBean;
	}
	/**
	 * 自定义方法，用于判断用户请求是静态还是非静�?�，即判断数据是否合�?
	 * @param bean ：数据处理后封装的自定义类WebLogBean的对�?
	 * @param pages：合法数据集合pages
	 */
	public static void filtStaticResource(WebLogBean bean, Set<String> pages) {
		//判断方法：判断pages容器中是否包含WebLogBean.request的请求数据，如果包含，则合法
		if (!pages.contains(bean.getRequest())) {
			bean.setValid(false);
		}
	}
	//自定义日期格式化类，用于将日期字符串转换为df2格式的日�?
	public static String formatDate(String time_local) {
		try {
			//先将日期字符串转换为df1格式的日期，再将此日期转换为df2格式
			return df2.format(df1.parse(time_local));
		} catch (ParseException e) {
			return null;
		}

	}

}
