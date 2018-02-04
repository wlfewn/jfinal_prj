package com.jfinal.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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
import com.jfinal.utils.BeanKitUtil;
import com.jfinal.utils.SerialNumberUtil;
import com.jfinal.utils.SystemUtil;


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
	
	/**
	 * 更新
	 * @param product
	 * @param imagesArr
	 * @param sysUser
	 * @return
	 */
	@Before(Tx.class)//启动事务
	public Message update(Product product,String[] imagesArr,SysUser sysUser){
		
		Product findProduct = Product.dao.findById(product.getId());
		
		Date date = new Date();
		//更新需要的属性
		try {
			BeanKitUtil.copyPropertiesExclude(findProduct, product, 
					new String[]{"name","costPrice","marketPrice","discountPrice","decribition"});
		} catch (Exception e) {
			//拷贝失败手动处理
			product.setCreateById(findProduct.getCreateById());
			product.setCreateDate(findProduct.getCreateDate());
			product.setDelState(findProduct.getDelState());
			product.setMonthSaleCount(findProduct.getMonthSaleCount());
			product.setSaleCount(findProduct.getSaleCount());
			product.setStartCount(findProduct.getStartCount());
		}
		product.setMarketState(false);
		product.setUpdateById(sysUser.getId());
		product.setUpdateDate(date);
		product.update();
		//处理图片记录
		if( null != imagesArr ){
			List<Images> imagesList = imagesService.findByResourceId(product.getId());
			//找出不在 imagesList 里的imagesArr
			//使用jdk8处理
			Images findImages;
			for (String id : imagesArr) {
				boolean isInOld = SystemUtil.elementInArray(imagesList, images->id.equals(images.getId()));
				//isInOld为true说明id在集合中
				if(!isInOld){
					findImages = Images.dao.findById(id);
					if( null != findImages ){
						findImages.setUpdateById(sysUser.getId());
						findImages.setUpdateDate(date);
						findImages.setSourceId(product.getId());
						findImages.setType(Images.productType);
						findImages.update();
					}
				}
			}
		}
			
		return MessageUtil.createSuccessMsg("更新成功");
	}
	
}
