package com.hpe.onlinexam.test;

import java.util.Arrays;

public class T {
	public static void main(String[] args) {
		
		String ss = Arrays.toString(new String[]{"1","2"}); // ['a','a'] ===> (1,2)
		String tager = ss.replace(']', ')').replace('[', '(');
		System.out.println(tager);
	}
}
