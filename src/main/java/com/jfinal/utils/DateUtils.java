package com.jfinal.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 日期处理工具类
 * joda-time 官方文档 http://www.joda.org/joda-time/quickstart.html
 */
public class DateUtils {

	 /**定义常量**/
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_LONG_STR = "yyyy-MM-dd kk:mm:ss.SSS";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_All_KEY_STR = "yyyyMMddHHmmss";
    
    
    /**
     * 解析文本时间
     * @param dataStr
     * @param formater
     * @return
     */
    public static Date parseStrDate(String dateStr,String formatter){
    	DateTimeFormatter format = DateTimeFormat.forPattern(formatter);
    	return DateTime.parse(dateStr, format).toDate(); 
    }
    /**
     * 格式化时间
     * @return
     */
    public static String parseDate(Date date,String pattern){
    	DateTime dateTime = new DateTime(date); 
    	return dateTime.toString(pattern);
    }
    
    
}
