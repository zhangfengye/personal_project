package com.rxxt.wcas.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransUtil {
	 public static String getDate(){
		   Date dt=new Date();
		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		   return df.format(dt);
	 }
	public static String toStr(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		return df.format(date);
	}
	//�Զ��巽�������ڽ������ַ���ת��Ϊ��Ӧ��ʽ������
	public static Date toDate(String timeStr){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			return df.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static long timeDiff(String time1, String time2){
		
		Date d1 = toDate(time1);
		Date d2 = toDate(time2);
		if(d1==null){
			if(d2==null){
				return 0;
			}else{
				return d2.getTime();
			}
		}else{
			if(d2==null){
				return d1.getTime();
			}else{
				return d1.getTime()-d2.getTime();
			}
		}
	

	}
	//�Զ��庯��timeDiff������������������ʱ���
	public static long timeDiff(Date d1, Date d2) {
		if(d1==null){
			if(d2==null){
				return 0;
			}else{
				return d2.getTime();
			}
		}else{
			if(d2==null){
				return d1.getTime();
			}else{
				return d1.getTime()-d2.getTime();
			}
		}

	}
}
