package com.jfinal.handler;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Before;
import com.jfinal.common.Constant;
import com.jfinal.config.Menu;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

/**
 * 处理静态文件路径问题
 */
public class BasePathHandler extends Handler{

	private String basePathName;
	
	public BasePathHandler() {
		basePathName = "BASE_PATH";
	}
	
	public BasePathHandler(String contextPathName) {
		if (StrKit.isBlank(contextPathName)) {
			throw new IllegalArgumentException("contextPathName can not be blank.");
		} else {
			this.basePathName = contextPathName;
		}
	}
	
	/**
	 * 这里可以使用缓存
	 * 缓存使用参考http://www.jfinal.com/doc/7-3
	 */
	@Before(CacheInterceptor.class)
	@CacheName("basePath")
	@Override
	public void handle(String target, HttpServletRequest request, 
			HttpServletResponse response, boolean[] isHandled) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() //
				+ ":" + request.getServerPort() + path ;
//System.out.println("basePath="+basePath);
		request.setAttribute(basePathName, basePath);
		request.setAttribute("uploadPath", Constant.uploadPath);
		request.setAttribute("ctx", basePath);
		next.handle(target, request, response, isHandled);
	}

}
