package com.jfinal.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
	
	/**
	 * 判断集合某个字段是否拥有某个值,使用lambda表达式,定义接口，自己实现方法
	 * @param list
	 * @param condition 
	 * @return
	 */
	public static<T> boolean elementInArray(List<T> list,Predicate<T> predicate){
		for (T t : list) {
			if(predicate.test(t)){
				return true;
			}
		}
		return false;
	}
	
	
}
