package com.jfinal.utils;

import com.jfinal.model.SysUser;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 * 管理员管理工具类
 * @author geng
 *2018年1月31日
 */
public class AdminUtil {

	private AdminUtil(){}
	
	/**
	 * 获取登陆管理员
	 * @param adminId
	 * @return
	 */
	public static SysUser getLoginUser(String adminId){
		return CacheKit.get("adminId", adminId, new IDataLoader(){
			@Override
			public Object load() {
				return SysUser.dao.findById(adminId);
			}
		});
	}

	
}
