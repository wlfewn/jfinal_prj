package com.jfinal.controller.admin;

import com.jfinal.core.Controller;
import com.jfinal.utils.MenuUtil;

/**
 * 订单管理
 * @author geng
 *2018年1月29日
 */
public class OrderController extends Controller{

	public void index(){
		//更新菜单缓存数据
		MenuUtil.updateSelect("order");
		render("/pages/admin/order/list.html");
	}
}
