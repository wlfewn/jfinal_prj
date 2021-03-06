package com.jfinal.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseDistributionStation<M extends BaseDistributionStation<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setCreateById(java.lang.String createById) {
		set("create_by_id", createById);
		return (M)this;
	}
	
	public java.lang.String getCreateById() {
		return getStr("create_by_id");
	}

	public M setUpdateById(java.lang.String updateById) {
		set("update_by_id", updateById);
		return (M)this;
	}
	
	public java.lang.String getUpdateById() {
		return getStr("update_by_id");
	}

	public M setBusinessId(java.lang.String businessId) {
		set("business_id", businessId);
		return (M)this;
	}
	
	public java.lang.String getBusinessId() {
		return getStr("business_id");
	}

	public M setPosition(java.lang.String position) {
		set("position", position);
		return (M)this;
	}
	
	public java.lang.String getPosition() {
		return getStr("position");
	}

	public M setContact(java.lang.String contact) {
		set("contact", contact);
		return (M)this;
	}
	
	public java.lang.String getContact() {
		return getStr("contact");
	}

	public M setGps(java.lang.String gps) {
		set("gps", gps);
		return (M)this;
	}
	
	public java.lang.String getGps() {
		return getStr("gps");
	}

}
