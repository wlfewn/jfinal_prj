package com.jfinal.utils;

public class SystemUtil {

	private SystemUtil(){}
	
	/**
	 * 创建成sql in ('xx','xx')括号中的部分
	 * @param strArr
	 * @return
	 */
	public static String createIn(String[] strArr){
		StringBuilder builder = new StringBuilder();
		
		for (String id : strArr) {
			builder.append("'"+id+"',");
		}
		//删除末尾,
		builder = builder.deleteCharAt(builder.length()-1);
		return builder.toString();
	}
}
