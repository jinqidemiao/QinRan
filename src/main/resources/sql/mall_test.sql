/*
 Navicat Premium Data Transfer

 Source Server         : qinran
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 47.105.151.169:3306
 Source Schema         : mall_test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/01/2020 16:21:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品取货码生成规则（前缀）',
  `goods_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_link_id` bigint(20) NULL DEFAULT NULL COMMENT '商品链接id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` tinyint(10) NULL DEFAULT NULL COMMENT '状态 0禁用 1启用',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开抢时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '年货礼盒', NULL, 100, '年货礼盒', '100g * 60', 50.00, NULL, 2, NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (2, '饮料礼盒', NULL, 100, '饮料礼盒', '100g * 60', 60.00, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (3, '水果礼盒', NULL, 100, '水果礼盒', '100g * 60', 70.00, NULL, 2, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for goods_user
-- ----------------------------
DROP TABLE IF EXISTS `goods_user`;
CREATE TABLE `goods_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `num` int(11) NULL DEFAULT NULL COMMENT '预约数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '预约时间',
  `send_way` tinyint(10) NULL DEFAULT NULL COMMENT '配送方式   0到店自取 1配送',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配送方式为0，则为null，若为1，则保存客户地址',
  `extra_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加信息（备注）',
  `goods_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '商品费用',
  `extra_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '额外费用（配送费）',
  `sum_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '总费用',
  `pickup_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '取货码',
  `state` tinyint(10) NULL DEFAULT NULL COMMENT '状态 0取消订单 1可用 ',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` tinyint(10) NULL DEFAULT NULL COMMENT '状态 0无效 1有效',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (1, 'www.baidu.com', '2020-01-09 15:21:53', 1, NULL);
INSERT INTO `link` VALUES (2, 'www.taobao.com', '2020-01-09 15:37:11', 1, '20200109新增的链接');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_agent` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户设备',
  `user_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `codes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约码集合，逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML; like Gecko) Mobile/12F70 MicroMessenger/6.1.5 NetType/WIFI', '17316348423', NULL);
INSERT INTO `user` VALUES (3, 'Mozilla/5.0 (Linux; Android 9; SEA-AL10 Build/HUAWEISEA-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/67.0.3396.87 XWEB/1166 MMWEBSDK/191201 Mobile Safari/537.36 MMWEBID/9731 MicroMessenger/7.0.10.1580(0x27000A55) Process/tools NetType/4G Language/zh_CN ABI/arm64', '18234768450', NULL);

-- ----------------------------
-- Function structure for calaV436
-- ----------------------------
DROP FUNCTION IF EXISTS `calaV436`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `calaV436`(`length` integer,`weigth` integer,`high` integer) RETURNS int(11)
BEGIN
	#Routine body goes here...
  DECLARE rs INTEGER;
  SET rs = 1;
	RETURN rs;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
