package com.jfinal.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("sys_member", "id", SysMember.class);
		arp.addMapping("sys_user", "id", SysUser.class);
		arp.addMapping("t_advertisement", "id", Advertisement.class);
		arp.addMapping("t_comment", "id", Comment.class);
		arp.addMapping("t_distribution_station", "id", DistributionStation.class);
		arp.addMapping("t_images", "id", Images.class);
		arp.addMapping("t_order", "id", Order.class);
		arp.addMapping("t_order_item", "id", OrderItem.class);
		arp.addMapping("t_prodct_sale_log", "id", ProdctSaleLog.class);
		arp.addMapping("t_product", "id", Product.class);
		arp.addMapping("t_shipping", "id", Shipping.class);
		arp.addMapping("t_shipping_item", "id", ShippingItem.class);
	}
}
