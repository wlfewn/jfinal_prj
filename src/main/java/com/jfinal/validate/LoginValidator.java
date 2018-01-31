package com.jfinal.validate;

import com.jfinal.core.Controller;

/**
 * 登陆验证
 * @author geng
 *2018年1月28日
 */
public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("username", "usernameMsg", "请输入用户名");
		validateRequiredString("password", "passwordMsg", "请输入密码");
		validateRequiredString("yzm", "yzmMsg", "请输入验证码");
	}

	@Override
	protected void handleError(Controller c) {
		
	}

}
