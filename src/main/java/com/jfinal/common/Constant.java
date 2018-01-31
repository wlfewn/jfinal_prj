package com.jfinal.common;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.jfinal.config.Menu;

/**
 * 系统常量配置
 * @author geng
 *2018年1月27日
 */
public class Constant {
	
	//window 系统输出/
	public static final String separator = File.separator;
	
	/**
	 * 管理员请求路径开头
	 */
	public static final String adminRoot = "/admin";
	
	/**
	 * 存放上传图片的文件夹名称
	 */
	public static final String uploadPath = "upload";
	/**
	 * 从session中获取管理员key
	 */
	public static final String adminSessionKey = "admin";
	
	/**
	 * 管理登陆页面路径
	 */
	public static final String adminLoginPage = "/pages/admin/login.html";
	
	/**
	 * 管理员不需要登陆拦截的url,管理员登陆页面，验证码,登陆请求
	 */
	public static final List<String> ignoreUrls = Arrays.asList(Constant.adminRoot,"/imageCode","/admin/login");
	
	
	/**
	 * 创建菜单
	 * @return
	 */
	public static List<Menu> createMenus(){
		String adminPath = Constant.adminRoot;
		return Arrays.asList(
				new Menu(adminPath+"/home", true, "今日分析", "fa-tachometer"),
				new Menu(adminPath+"/product/", false, "产品管理", "fa-pencil-square-o"),
				new Menu(adminPath+"/order/", false, "订单管理", "fa-list"),
				new Menu("#", false, "配送单管理", "fa-list-alt"),
				new Menu("#", false, "配送点管理", "fa-map"));
	}
}
