package com.jfinal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Config {
	private final static URL classPathUrl = Config.class.getResource("/");
	private final static String classPath = new File(classPathUrl.getFile()).getPath();
	private static String configPath = "/conf/";
	private static Map<String, String> configMap = new HashMap<String, String>();
	
	static {
		setConfigMap();
	}
	
	public static String getStr(String key) {
		if (configMap.size() < 0) {
			return null;
		}
		return configMap.get(key);
	}
	
	private static void setConfigMap() {
		String filePath = classPath + configPath;
		filePath = PathUtils.rebuild(filePath);
		List<String> list = findFiles(filePath);
		
		Map<String, String> tmpConfigMap = new HashMap<String, String>();
		for (String configName : list) {
			Properties props = getProperties(filePath + configName);
			Iterator<Entry<Object, Object>> it = props.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				if (!tmpConfigMap.containsKey(key)) {
					tmpConfigMap.put(key, value);
				} else {
					System.err.println("CONFIG EEOR:key(" + key + ") is exist;");
				}
			}
		}
		
		Config.configMap = tmpConfigMap;
	}
	
	/**
	 * 查找当前文件下所有properties文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 */
	private static List<String> findFiles(String baseDirName) {
		List<String> configFiles = new ArrayList<String>();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.err.println("search error：" + baseDirName + " is not a dir！");
		} else {
			String[] filelist = baseDir.list();
			for (String fileName : filelist) {
				if (fileName.endsWith("properties")) {
					configFiles.add(fileName);
				}
			}
		}
		return configFiles;
	}
	
	/**
	 * 获取Properties文件
	 * 
	 * 2014年7月5日 上午12:23:14 flyfox 330627517@qq.com
	 * 
	 * @param file
	 * @return
	 */
	private static Properties getProperties(String fileName) {
		Properties props = new Properties();
		try {
			java.io.InputStream propFile = new FileInputStream(fileName);
			props.load(propFile);
		} catch (IOException e) {
			System.err.println("file read fail:" + fileName);
			e.printStackTrace();
		}
		return props;
	}
}
