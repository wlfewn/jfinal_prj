package com.jfinal.validate.admin;

import com.jfinal.core.Controller;
import com.jfinal.validate.ValidatorExpand;

public class ProductValidate extends ValidatorExpand{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("product.name", "nameError", "产品名称不能为空");
		validateDouble("product.marketPrice", 0d, Double.MAX_VALUE, "marketPriceError", "折前价不能小于0");
		validateDouble("product.discountPrice", 0d, Double.MAX_VALUE, "discountPriceError", "折后加不能小于0");
		//第二个参数regExpression，正则表达式,源码可看出 参考https://www.cnblogs.com/lzq198754/p/5780340.html
		//validateRegex(field, regExpression, errorKey, errorMessage);
	}

	@Override
	protected void handleError(Controller c) {
		
	}

	
}
