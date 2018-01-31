package com.jfinal.controller.admin;


import com.jfinal.aop.Before;
import com.jfinal.common.BaseController;
import com.jfinal.core.Controller;
import com.jfinal.message.Message;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.Product;
import com.jfinal.model.SysUser;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.service.ProductService;
import com.jfinal.utils.MenuUtil;
import com.jfinal.validate.admin.ProductValidate;

/**
 * 产品管理
 * @author geng
 *2018年1月29日
 */
public class ProductController extends BaseController{
	
	static ProductService productService = new ProductService();
	
	private static final String prodcutListPage = "/pages/admin/product/list.html";
	
	public void index(){
		//更新菜单缓存数据
		MenuUtil.updateSelect("product");
		//获取分页数据
		Page<Product> page = productService.paginate(getParaToInt(0, 1), 10);
		setAttr("page", page);
		//测试message
		//setAttr("message", MessageUtil.createSuccessMsg("请求成功"));
		render(prodcutListPage);
	}
	
	/**
	 * 添加产品跳转
	 */
	public void add(){
		render("/pages/admin/product/form.html");
	}
	
	/**
	 * 编辑，已上架的需要先下架
	 */
	public void edit(){
		
		
	}
	
	/**
	 * 保存产品,注意
	 */
	@Before(ProductValidate.class)
	public void save(){
		Product product = getBean(Product.class);
		String[] imagesArr = getParaValues("images");
		SysUser sysUser = getLoginUser();
		
		Message message = productService.save(product,imagesArr,sysUser);
		
		setAttr("message", message);
		redirect("/admin/product/");
	}
	
}
