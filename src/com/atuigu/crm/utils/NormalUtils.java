package com.atuigu.crm.utils;

public class NormalUtils {
	public static String randomString(){
		String standard="0123456789abcdef";
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<16;i++) {
			int a =(int) (Math.random()*16);
			sb.append(standard.charAt(a));
		}
		return sb.toString();
	}
}
