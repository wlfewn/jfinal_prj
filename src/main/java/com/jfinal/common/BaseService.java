package com.jfinal.common;

import com.jfinal.log.Log;

/**
 * service基类,考虑存放公用方法
 * @author geng
 *2018年1月28日
 */
public class BaseService {
	
	/**
	 * 通用日志
	 */
	Log log = Log.getLog(getClass());
}
