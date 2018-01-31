package com.jfinal.service;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.jfinal.aop.Before;
import com.jfinal.common.BaseService;
import com.jfinal.common.Constant;
import com.jfinal.message.Message;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.SysUser;
import com.jfinal.utils.ImageCode;


public class SysUserService extends BaseService{
	
	/**
	 * 用户登陆
	 * @param sysUser
	 * @return
	 */
	public Message login(String username,String password,HttpServletRequest request){
		SysUser findSysUser = 
				SysUser.dao.findFirst("select * from sys_user where username = ?",username);
		//通过用户名查用户
		if(findSysUser == null ){
			return MessageUtil.createErrorMsg("用户名或密码错误");
		}
		//匹配密码
		//				    加密前密码,加密后密码,//true 密码匹配
		if(BCrypt.checkpw(password, findSysUser.getPassword())){
			//更新信息,这里可以记录用户登陆日志,以后需要再添加
			Date date = new Date();
			findSysUser.setLoginDate(new Date());
			findSysUser.setLoginIp(request.getLocalAddr());//记录登陆ip
			findSysUser.setUpdateDate(date);
			findSysUser.update();
			//会员记录id存入session
			HttpSession session =request.getSession();
			session.setAttribute(Constant.adminSessionKey, findSysUser.getId());
			session.setAttribute("username", username);
			//移除验证码session
			session.removeAttribute(ImageCode.class.getName());
			
			return MessageUtil.createSuccessMsg("");
		}else{//密码不匹配
			return MessageUtil.createErrorMsg("用户名或密码错误");
		}
	}
}
