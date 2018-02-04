package com.jfinal.utils;

import com.jfinal.json.Jackson;

/**
 * json转换工具类,使用框架jackson实现
 * @author geng
 *2018年2月1日
 */
public class JsonUtil {

	private static Jackson jackson;
	
	public static void config(){
		if( null == jackson ){
			jackson = new Jackson();
		}
	}
	
	public static String toJson(Object object){
		config();
		return jackson.toJson(object);
	}
	
	public static<T> T parse(String jsonString, Class<T> type){
		config();
		return jackson.parse(jsonString,type);
	} 
	
}
