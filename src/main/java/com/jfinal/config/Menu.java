package com.jfinal.config;

import java.io.Serializable;

/**
 * 系统菜单类，暂时不考虑层级关系
 * @author geng
 *2018年1月28日
 */
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String url;
	private boolean beSelect;
	private String name;
	private String iconClass;
	
	public Menu(){}
	
	public Menu(String url,boolean beSelect,String name,String iconClass){
		this.url = url;
		this.beSelect = beSelect;
		this.name = name;
		this.iconClass = iconClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public boolean getBeSelect() {
		return beSelect;
	}
	public void setBeSelect(boolean beSelect) {
		this.beSelect = beSelect;
	}
}
