package com.jfinal.common;

import com.jfinal.core.Controller;
import com.jfinal.model.SysUser;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.utils.AdminUtil;

/**
 * 扩展
 * @author geng
 *2018年1月31日
 */
public abstract class BaseController extends Controller{
	
	
	/**
	 * 获取登陆admin
	 * @return
	 */
	public SysUser getLoginUser(){
		return AdminUtil.getLoginUser(getSessionAttr(Constant.adminSessionKey));
	}
	
}
