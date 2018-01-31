package com.jfinal.validate;

/**
 * 扩展validateRequire验证规则，验证必须大于0的数字
 * @author geng
 *2018年1月31日
 */
public abstract class ValidatorExpand extends Validator{
	
	
	protected void validateRequiredNumberGtZero(String field, String errorKey, String errorMessage){
		try {
			Double value = new Double(controller.getPara(field));
			if(value <=0 ){
				addError(errorKey, errorMessage);
			}
		} catch (NumberFormatException e) {//不是数字
			addError(errorKey, errorMessage);
		}
		
	}
	
}
