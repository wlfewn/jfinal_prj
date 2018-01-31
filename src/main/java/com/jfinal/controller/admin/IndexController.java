package com.jfinal.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.common.Constant;
import com.jfinal.core.Controller;
import com.jfinal.message.Message;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.SysUser;
import com.jfinal.service.SysUserService;
import com.jfinal.utils.ImageCode;
import com.jfinal.validate.LoginValidator;

public class IndexController extends Controller{
	
	
	//登陆页面，如果已经登陆需要跳转到系统主页
	public void index(){
		render(Constant.adminLoginPage);
	}
	
	//管理员登陆
	@Before(LoginValidator.class)
	public void login(){
		//如果已经登陆
		String adminSessionKey = getSessionAttr(Constant.adminSessionKey);
		if( null != adminSessionKey ){
			redirect(Constant.adminRoot+"/home");
		}
		//获取缓存中的验证码
		String imageCode = getSessionAttr(ImageCode.class.getName());
		String username = getPara("username");
		String password = getPara("password");
		String checkCode = getPara("yzm");
		if( null == imageCode ){
			render(Constant.adminLoginPage);
			return;
		}
		
		if( !imageCode.equalsIgnoreCase(checkCode) ){
			setAttr("yzmMsg", "验证码错误！");
			render(Constant.adminLoginPage);
			return;
		}
		
		Message message = new SysUserService().login(username,password,getRequest());
		if(MessageUtil.matchSuccess(message)){
			//render("/pages/admin/home.html");//去首页
			redirect(Constant.adminRoot+"/home");
		}else{
			//返回错误信息
			setAttr("errorMsg", message.getMessage());
			render(Constant.adminLoginPage);
		}
	}
	
	
	public void home(){
		render("/pages/admin/home.html");
	}
	
}
