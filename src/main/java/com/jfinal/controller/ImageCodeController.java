package com.jfinal.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import com.jfinal.core.Controller;
import com.jfinal.utils.ImageCode;

/**
 * 验证码工具类
 * @author geng
 *2018年1月28日
 */
public class ImageCodeController extends Controller{
	
	/**
	 * 生成验证码,这里可以记录单位时间内每个ip请求验证码的次数,做限制
	 */
	public void index(){
		try {
			new ImageCode().doGet(getRequest(), getResponse());
		} catch (ServletException|IOException e) {
			e.printStackTrace();
		} 
		renderNull();
	}
}
