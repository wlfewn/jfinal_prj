package com.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.common.Constant;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.model.SysUser;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.utils.AdminUtil;

/**
 * 拦截action所有请求,验证需要登陆才能请求的路径
 * @author geng
 *2018年1月28日
 */
public class LoginInterceptor implements Interceptor{

	Log log = Log.getLog(LoginInterceptor.class);
	
	/**
	 * 用户与管理员请求分开,不能用户登陆后可以访问管理员可以访问资源
	 */
	@Override
	public void intercept(Invocation inv) {
		System.out.println("isActionInvocation="+inv.isActionInvocation());
		System.out.println("actionKey="+inv.getActionKey());// /admin/home
		System.out.println("controllerKey="+inv.getControllerKey());///admin
		
		Controller controller = inv.getController();
		//action层的请求
		if(inv.isActionInvocation()){
			//根据actionKey区分管理员请求还是用户请求
			String actionKey = inv.getActionKey();
			//检查session中数据,以后考虑使用spring session或者redis
			//排除不需要过滤url
			if(actionKey.startsWith("/admin") && !Constant.ignoreUrls.contains(actionKey)){
				boolean result = checkAdminLogin(controller);
				if( !result ){//去登陆页面
					String ip = controller.getRequest().getLocalAddr();
					log.info(String.format("ip:%s未登陆请求链接:%s", ip,actionKey));
					//重定向到登陆页面
					controller.setAttr("errorMsg", "请重新登陆");
					controller.redirect(Constant.adminRoot);
					return;
				}
			}
		}
		
		inv.invoke();
	}
	
	/**
	 * 
	 * @param controller
	 * @return true 检查通过 | false 检查不通过
	 */
	private boolean checkAdminLogin(Controller controller){
		String adminId = controller.getSessionAttr(Constant.adminSessionKey);
		if( null == adminId){
			return false;
		}
		//根据id查找admin
		//参考http://www.jfinal.com/doc/7-5
		//CacheKit.get方法提供了一个IDataLoader接口，该接口中的load()方法在缓存值不存在时才会被调用。
		//该方法的具体操作流程是：首先以cacheName=blog以及key=blogList为参数去缓存取数据，
		//如果缓存中数据存在就直接返回该数据，不存在则调用IDataLoader.load()方法来获取数据
		//key使用数据库记录id,确保多线程唯一
		SysUser sysUser = AdminUtil.getLoginUser(adminId);
		
		if( null == sysUser ){
			return false;
		}
		return true;
	}
	
	
}
