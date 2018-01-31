package com.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.utils.Config;

/**
 * 全局异常处理
 */
public class ExceptionInterceptor implements Interceptor{
	
	private final static Log log = Log.getLog(ExceptionInterceptor.class);
	
	@Override
	public void intercept(Invocation inv) {
		
		try {
			inv.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("异常：", e);
			Controller controller = inv.getController();
			controller.setAttr("error", e.toString());
			controller.render(Config.getStr("PAGES.500"));
		}
		
	}

}
