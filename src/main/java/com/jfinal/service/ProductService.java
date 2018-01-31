package com.jfinal.service;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.common.BaseService;
import com.jfinal.message.Message;
import com.jfinal.message.utils.MessageUtil;
import com.jfinal.model.Images;
import com.jfinal.model.Product;
import com.jfinal.model.SysUser;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import com.jfinal.utils.SerialNumberUtil;


public class ProductService extends BaseService{
	
	static ImagesService imagesService = new ImagesService();
	
	public Page<Product> paginate(int pageNumber, int pageSize){
		return Product.dao.paginate(pageNumber, pageSize, 
				"select * "," from t_product order by create_date desc");
	}
	
	/**
	 * 保存产品，保存产品中图片的引用
	 * @param product
	 * @param imagesList
	 * @return
	 */
	@Before(Tx.class)//启动事务
	public Message save(Product product,String[] imagesArr,SysUser sysUser){
		String uuid = SerialNumberUtil.createUUID();
		product.setId(uuid);
		product.setCreateById(sysUser.getId());
		product.setUpdateById(sysUser.getId());
		product.setCreateDate(new Date());
		product.setUpdateDate(product.getCreateDate());
		product.setMarketState(false);
		product.save();
		
		//更新图片记录
		if(null !=imagesArr && imagesArr.length > 0){
			List<Images> imagesList = imagesService.findListByIds(imagesArr);
			//使用lambda表达式，比较简洁
			imagesList.forEach(images -> {
				images.setSourceId(uuid);
				images.setType(Images.productType);
				images.update();
			});
		}
		
		return MessageUtil.createSuccessMsg("保存成功");
	}
}
