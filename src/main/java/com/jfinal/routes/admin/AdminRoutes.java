package com.jfinal.routes.admin;


import com.jfinal.common.Constant;
import com.jfinal.config.Routes;
import com.jfinal.controller.admin.IndexController;
import com.jfinal.controller.admin.ProductController;

/**
 * 管理员管理页面路由配置
 * @author geng
 *2018年1月27日
 */
public class AdminRoutes extends Routes{

	@Override
	public void config() {
		String separator = Constant.separator;// 斜杠/
		String adminPath = Constant.adminRoot;
		//第三个参数指明文件路径
		add(adminPath, IndexController.class,separator);
		add(adminPath+"/product",ProductController.class,separator);
		
	}

}
