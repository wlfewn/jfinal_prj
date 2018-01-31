package com.jfinal.utils;

import java.util.UUID;

import com.jfinal.kit.StrKit;

public class SerialNumberUtil {
	
	/**
	 * 创建uuid
	 * @return
	 */
	public static String createUUID(){
		//已经有实现，使用其代替
		return StrKit.getRandomUUID();
		//return UUID.randomUUID().toString().replace("-", "");
	}
	
}
