/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50131
Source Host           : localhost:3306
Source Database       : jfinal

Target Server Type    : MYSQL
Target Server Version : 50131
File Encoding         : 65001

Date: 2018-02-04 14:58:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_member`;
CREATE TABLE `sys_member` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '会员名称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '登陆ip',
  `deposit` decimal(11,2) DEFAULT '0.00' COMMENT '余额',
  `head_url` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `wx_open_id` varchar(255) DEFAULT NULL COMMENT '微信openId',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `wx_open_id` (`wx_open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_member
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_ip` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2018-01-29 21:17:36', '2018-02-01 16:59:32', 'admin', '$2a$10$HmSdTZ2rI9xQlwzYYSyQgeuoXCEQZm8AmP4WkH1slGNYnBYCSkfZ2', '2018-02-01 16:59:32', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for t_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `t_advertisement`;
CREATE TABLE `t_advertisement` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `product_id` varchar(64) DEFAULT NULL COMMENT '产品id',
  `url` varchar(255) NOT NULL COMMENT '广告图片链接',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `member_id` varchar(64) NOT NULL,
  `score` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '评价分数',
  `describition` varchar(255) NOT NULL COMMENT '评论',
  `product_id` varchar(64) NOT NULL COMMENT '评论产品',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `sys_member` (`id`),
  CONSTRAINT `t_comment_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_distribution_station
-- ----------------------------
DROP TABLE IF EXISTS `t_distribution_station`;
CREATE TABLE `t_distribution_station` (
  `id` varchar(64) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `business_id` varchar(64) NOT NULL COMMENT '会员',
  `position` varchar(255) NOT NULL COMMENT '详细地址',
  `contact` varchar(20) NOT NULL COMMENT '联系人电话',
  `gps` varchar(100) NOT NULL COMMENT 'gps经纬度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_distribution_station
-- ----------------------------

-- ----------------------------
-- Table structure for t_images
-- ----------------------------
DROP TABLE IF EXISTS `t_images`;
CREATE TABLE `t_images` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `size` int(11) NOT NULL DEFAULT '0' COMMENT '图片大小,单位：字节',
  `dist_url` varchar(255) NOT NULL COMMENT '图片路径',
  `source_id` varchar(64) DEFAULT NULL COMMENT '关联记录id',
  `type` char(1) DEFAULT NULL COMMENT '图片类型，0产品图片，1广告图片',
  `relative_path` varchar(255) NOT NULL COMMENT '相对路径',
  `name` varchar(255) NOT NULL COMMENT '图片名称',
  `content_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `source_id` (`source_id`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_images
-- ----------------------------
INSERT INTO `t_images` VALUES ('317dd2d0774f48d18bf2ed79b46d2f45', '2018-02-01 16:59:55', '2018-02-01 17:00:11', null, '1', '323643', 'F:\\git_git_repository_jfinal\\jfinal01\\src\\main\\webapp\\upload\\8d9f3b2860ead767e183419a94da0663.jpg', '499178e0f8f94a2fac47792dfb360769', '0', 'upload/8d9f3b2860ead767e183419a94da0663.jpg', '8d9f3b2860ead767e183419a94da0663.jpg', 'image/jpeg');
INSERT INTO `t_images` VALUES ('54bb2992e45141c99b480e8a5aadd3c7', '2018-02-01 16:59:45', '2018-02-01 17:00:11', null, '1', '357561', 'F:\\git_git_repository_jfinal\\jfinal01\\src\\main\\webapp\\upload\\15fab152b6c515e7d132afec17d11939.jpg', '499178e0f8f94a2fac47792dfb360769', '0', 'upload/15fab152b6c515e7d132afec17d11939.jpg', '15fab152b6c515e7d132afec17d11939.jpg', 'image/jpeg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `member_id` varchar(64) NOT NULL,
  `total_amount` decimal(11,2) NOT NULL COMMENT '总额',
  `memo` varchar(255) DEFAULT NULL COMMENT '购买备注信息',
  `total_weight` double(11,2) DEFAULT '0.00' COMMENT '重量,单位克',
  `distribution_fee` decimal(11,2) DEFAULT '0.00' COMMENT '配送费',
  `business_id` varchar(64) NOT NULL COMMENT '商家id',
  `status` int(1) DEFAULT '0' COMMENT '订单状态,默认0未处理',
  `shipping_date` datetime NOT NULL COMMENT '预计配送时间',
  `distrbution_id` varchar(64) NOT NULL COMMENT '配送站id',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`) USING BTREE,
  KEY `business_id` (`business_id`) USING BTREE,
  KEY `distrbution_id` (`distrbution_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `sys_member` (`id`),
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`business_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `t_order_ibfk_3` FOREIGN KEY (`distrbution_id`) REFERENCES `t_distribution_station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `order_id` varchar(64) NOT NULL COMMENT '订单id',
  `product_id` varchar(64) NOT NULL COMMENT '配送单id',
  `price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '购买前价格',
  `discount_price` decimal(11,2) NOT NULL COMMENT '购买后价格',
  `weight` double(11,2) DEFAULT '0.00' COMMENT '单位：克',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_prodct_sale_log
-- ----------------------------
DROP TABLE IF EXISTS `t_prodct_sale_log`;
CREATE TABLE `t_prodct_sale_log` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `year` tinyint(5) DEFAULT NULL COMMENT '年',
  `month` tinyint(2) DEFAULT NULL COMMENT '月',
  `product_id` varchar(64) NOT NULL,
  `sale_count` int(11) DEFAULT '0' COMMENT '月销量',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `year` (`year`) USING BTREE,
  KEY `month` (`month`) USING BTREE,
  CONSTRAINT `t_prodct_sale_log_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_prodct_sale_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL COMMENT '产品名称',
  `cost_price` decimal(11,2) DEFAULT '0.00' COMMENT '成本价',
  `market_price` decimal(11,2) NOT NULL COMMENT '折前价',
  `discount_price` decimal(11,2) NOT NULL COMMENT '折后价',
  `create_by_id` varchar(64) NOT NULL COMMENT '创建人',
  `update_by_id` varchar(64) DEFAULT NULL COMMENT '修改人',
  `decribition` varchar(255) DEFAULT NULL COMMENT '描述',
  `start_count` double(11,2) DEFAULT '0.00' COMMENT '用户综合评分',
  `sale_count` int(11) DEFAULT '0' COMMENT '累计销量',
  `month_sale_count` int(11) DEFAULT '0' COMMENT '月销量',
  `market_state` bit(1) DEFAULT b'0' COMMENT '0未上架，1已上架',
  `del_state` bit(1) DEFAULT b'0' COMMENT '删除标志，默认0不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('499178e0f8f94a2fac47792dfb360769', '2018-01-31 13:42:02', '2018-02-01 17:00:11', 'test001', '0.00', '1.00', '1.00', '1', '1', null, '0.00', '0', '0', '\0', '\0');

-- ----------------------------
-- Table structure for t_shipping
-- ----------------------------
DROP TABLE IF EXISTS `t_shipping`;
CREATE TABLE `t_shipping` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `order_id` varchar(64) NOT NULL,
  `business_id` varchar(64) NOT NULL COMMENT '商家id',
  `member_id` varchar(64) NOT NULL COMMENT '购买会员',
  `status` int(1) DEFAULT '0' COMMENT '发货单状态，默认0未审核',
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `business_id` (`business_id`),
  KEY `member_id` (`member_id`),
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `t_shipping_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `t_shipping_ibfk_2` FOREIGN KEY (`business_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `t_shipping_ibfk_3` FOREIGN KEY (`member_id`) REFERENCES `sys_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shipping
-- ----------------------------

-- ----------------------------
-- Table structure for t_shipping_item
-- ----------------------------
DROP TABLE IF EXISTS `t_shipping_item`;
CREATE TABLE `t_shipping_item` (
  `id` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `cretate_by_id` varchar(64) DEFAULT NULL,
  `update_by_id` varchar(64) DEFAULT NULL,
  `order_item_id` varchar(64) NOT NULL,
  `price` decimal(11,2) NOT NULL COMMENT '发货时订单价格，冗余字段',
  PRIMARY KEY (`id`),
  KEY `order_item_id` (`order_item_id`),
  CONSTRAINT `t_shipping_item_ibfk_1` FOREIGN KEY (`order_item_id`) REFERENCES `t_order_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shipping_item
-- ----------------------------

-- ----------------------------
-- Table structure for wx_users
-- ----------------------------
DROP TABLE IF EXISTS `wx_users`;
CREATE TABLE `wx_users` (
  `openId` varchar(64) NOT NULL,
  `nickName` varchar(255) NOT NULL,
  `unionid` varchar(64) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `sex` varchar(3) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_users
-- ----------------------------
