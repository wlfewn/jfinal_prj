package com.jfinal.utils;

import java.util.List;

import com.jfinal.common.Constant;
import com.jfinal.config.Menu;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 菜单工具类
 * @author geng
 *2018年1月29日
 */
public class MenuUtil {
	
	public static List<Menu> getAll(){
		return CacheKit.get("menuList", "menuList");
	}
	
	
	/**
	 * 缓存被清理，这里需要做处理
	 * @param url
	 */
	public static void updateSelect(String url){
		List<Menu> menuList = getAll();
		int index = 0;
		Menu menu;
		if( null == menuList ){
			menuList = Constant.createMenus();
		}
		
		for (int i = 0; i < menuList.size(); i++) {
			menu = menuList.get(i);
			menu.setBeSelect(false);
			if(menu.getUrl().contains(url)){
				index = i;
			}
		}
		menuList.get(index).setBeSelect(true);
		//更新缓存
		CacheKit.put("menuList", "menuList", menuList);
	}
	
}
