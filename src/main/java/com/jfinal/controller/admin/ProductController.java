package com.jfinal.controller.admin;


import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.common.BaseController;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.message.Message;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.Images;
import com.jfinal.model.Product;
import com.jfinal.model.SysUser;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.service.ImagesService;
import com.jfinal.service.ProductService;
import com.jfinal.utils.Config;
import com.jfinal.utils.JsonUtil;
import com.jfinal.utils.MenuUtil;
import com.jfinal.validate.admin.ProductValidate;

/**
 * 产品管理
 * @author geng
 *2018年1月29日
 */
public class ProductController extends BaseController{
	//使用static关键字修饰的会被先初始化
	static ProductService productService = new ProductService();
	static ImagesService imagesService = new ImagesService();
	
	private final String prodcutListPage = "/pages/admin/product/list.html";
	private final String productListUrl = "/admin/product/";
	private final String productFormPage = "/pages/admin/product/form.html";
	
	public void index(){
		//更新菜单缓存数据
		MenuUtil.updateSelect("product");
		//获取分页数据
		Page<Product> page = productService.paginate(getParaToInt(0, 1), 10);
		setAttr("page", page);
		//测试message
		//setSessionAttr("message", MessageUtil.createSuccessMsg("请求成功"));
		render(prodcutListPage);
	}
	
	/**
	 * 添加产品跳转
	 */
	public void add(){
		setAttr("actionUrl", "/admin/product/save");
		render(productFormPage);
	}
	
	/**
	 * 编辑，已上架的需要先下架
	 */
	public void edit(){
		String id = getPara("id");
		if(StrKit.isBlank(id)){
			render(Config.getStr("PAGES.404"));
			return;
		}
		Product product = Product.dao.findById(id);
		List<Images> imagesList = imagesService.findByResourceId(id);
		
		setAttr("product", product);
		setAttr("imagesList", imagesList);
		setAttr("actionUrl", "/admin/product/update");
		render(productFormPage); 
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
		
		setSessionAttr("message", message);
		redirect(productListUrl);
	}
	
	@Before(ProductValidate.class)
	public void update(){
		Product product = getBean(Product.class);
		String[] imagesArr = getParaValues("images");
		SysUser sysUser = getLoginUser();
		
		Message message = productService.update(product, imagesArr, sysUser);
		
		setSessionAttr("message", message);
		redirect(productListUrl);
	}
}
