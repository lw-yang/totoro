/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : totoro

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 14/05/2020 23:04:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址所属省份',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址所属城市',
  `county` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市的地区',
  `detail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址信息',
  `tag` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址标签',
  `receiver` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人手机号',
  `user_id` bigint(20) NOT NULL COMMENT '地址对应的用户',
  `postal_code` bigint(20) NULL DEFAULT NULL COMMENT '邮政编码',
  `default_address` tinyint(4) NULL DEFAULT 0 COMMENT '是否为默认地址【0：不是、1：是】',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_address_user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (2, '北京市', '北京市', '朝阳区', '1111111', NULL, '李四', '18000000000', 12, 111111, 0, '2020-03-06 18:47:01', '2020-03-06 20:43:11');
INSERT INTO `address` VALUES (3, '重庆市', '县', '秀山土家族苗族自治县', '3333', NULL, '王五', '18000000000', 12, 333333, 0, '2020-03-06 18:50:35', '2020-03-06 19:47:47');
INSERT INTO `address` VALUES (4, '河南省', '省直辖县', '济源市', '1111111', NULL, '张李', '18100001111', 12, 111111, 0, '2020-03-06 20:11:01', NULL);
INSERT INTO `address` VALUES (5, '安徽省', '宣城市', '宁国市', '2222222', NULL, '王张', '13111110000', 12, 222222, 0, '2020-03-06 20:11:48', NULL);
INSERT INTO `address` VALUES (9, '江苏省', '南京市', '玄武区', '2132423', NULL, '样六级', '18744446666', 12, 434234, 1, '2020-03-06 20:24:21', '2020-03-07 21:18:10');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id\n',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别图片',
  `parent_id` bigint(20) NULL DEFAULT -1 COMMENT '父级类别id，无父级类别为【-1】',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：【0：禁用；1：正常】',
  `gender` tinyint(4) NULL DEFAULT 0 COMMENT '类别适用性别 【0：通用；1：男性；2：女性】',
  `priority` int(11) NULL DEFAULT 10000 COMMENT '类别优先级 【越小优先级越高】',
  `home` tinyint(4) NULL DEFAULT NULL COMMENT '首页分类展示',
  `recommend` tinyint(4) NULL DEFAULT NULL COMMENT '是否为推荐类别 【1：是；0：不是】',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `category_name_parent_id_uindex`(`name`, `parent_id`) USING BTREE,
  INDEX `category_priority_index`(`priority`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '男装', NULL, -1, 1, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (2, '女装', 'icon-nvshi', -1, 1, 2, 2, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (3, '美妆护肤', 'icon-hufuhuli', -1, 1, 0, 6, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (4, '个护清洁', 'icon-xifahufa', -1, 1, 0, 7, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (5, '推荐分类', '', 0, 1, 0, 0, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (6, '衬衫', 'icon-gray-tshirt', 1, 1, 1, 10000, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (7, '男鞋', NULL, -1, 1, 0, 3, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (8, '女鞋', NULL, -1, 1, 0, 4, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (9, '箱包手袋', NULL, -1, 1, 0, 20, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (10, '手机数码', 'icon-shoujizhoubian', -1, 1, 0, 30, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (11, '电脑办公', 'icon-diannaozhoubian', -1, 1, 0, 40, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (12, '家用电器', NULL, -1, 1, 0, 50, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (13, '食品饮料', 'icon-fangbianshipin', -1, 1, 0, 60, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (14, '母婴童装', NULL, -1, 1, 0, 70, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (15, '玩具乐器', 'icon-yizhiwanju', -1, 1, 0, 80, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (16, '运动户外', NULL, -1, 1, 0, 90, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (17, '医药保健', 'icon-baojianpin', -1, 1, 0, 100, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (18, '家居厨具', 'icon-chuju', -1, 1, 0, 110, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (19, '钟表珠宝', NULL, -1, 1, 0, 10, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (20, '礼品鲜花', NULL, -1, 1, 0, 130, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (21, '图书文娱', 'icon-tushu', -1, 1, 0, 140, 1, NULL, NULL, NULL);
INSERT INTO `category` VALUES (22, 'T恤', 'icon-pink-tshirt', 2, 1, 2, 10010, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (23, '牛仔裤', 'icon-black-pants', 1, 1, 1, 10020, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (24, '连衣裙', 'icon-green-dress', 2, 1, 2, 10030, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (25, '运动鞋', 'icon-purple-running-shoe', 7, 1, 1, 10060, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (26, '高跟鞋', 'icon-red-high-heel', 8, 1, 2, 10070, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (27, '太阳帽', 'icon-sun-hat', 31, 1, 2, 10050, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (28, '渔夫帽', 'icon-trilby', 31, 1, 1, 10090, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (29, '瑞表', 'icon-watch', 19, 1, 0, 10200, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (30, '太阳镜', 'icon-sunglasses', 31, 1, 0, 10100, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (31, '内衣配饰', NULL, -1, 1, 0, 5, NULL, NULL, NULL, NULL);
INSERT INTO `category` VALUES (32, '围巾', 'icon-scarf', 31, 1, 0, 10000, NULL, 0, NULL, NULL);
INSERT INTO `category` VALUES (33, '泳裤', 'icon-swimming-shorts', 1, 1, 1, 10040, NULL, 1, NULL, NULL);
INSERT INTO `category` VALUES (34, '拖鞋', 'icon-yellow-flip-flop', 7, 1, 1, 10080, NULL, 1, NULL, NULL);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id\n',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券名称',
  `value` int(11) NOT NULL DEFAULT 0 COMMENT '优惠券面值',
  `use_condition` int(11) NOT NULL DEFAULT 0 COMMENT '使用限制：购买金额需大于该值',
  `img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券图片',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '优惠券是否可用【1：可用，2：禁用】',
  `effective_time` date NULL DEFAULT NULL COMMENT '优惠券生效日期',
  `expire_time` date NULL DEFAULT NULL COMMENT '优惠券失效日期',
  `cd_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券兑换码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `coupon_cd_key_uindex`(`cd_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, '满100减20', 20, 100, 'Src', 1, '2020-03-05', '2020-03-07', '', NULL, NULL);
INSERT INTO `coupon` VALUES (2, '满200减50', 100, 200, '', 1, '2020-03-05', '2020-03-13', NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (3, '满200减50', 50, 100, NULL, 1, '2020-03-02', '2020-03-03', NULL, NULL, NULL);
INSERT INTO `coupon` VALUES (7, '10元新人优惠券', 10, 0, NULL, 1, '2020-03-06', '2020-03-09', 'VALUE_10_CONDITION_0', '2020-03-06 14:30:38', NULL);
INSERT INTO `coupon` VALUES (8, '20元新人优惠券', 20, 50, NULL, 1, '2020-03-06', '2020-03-13', 'VALUE_20_CONDITION_50', '2020-03-06 14:30:38', NULL);
INSERT INTO `coupon` VALUES (9, '30元新人优惠券', 30, 100, NULL, 1, '2020-03-06', '2020-04-05', 'VALUE_30_CONDITION_100', '2020-03-06 14:30:38', NULL);

-- ----------------------------
-- Table structure for daily_choice
-- ----------------------------
DROP TABLE IF EXISTS `daily_choice`;
CREATE TABLE `daily_choice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '精选商品id',
  `choice_reason` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入选理由',
  `choice_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '精选标签',
  `priority` int(11) NULL DEFAULT NULL COMMENT '展示顺序【0：优先级最高】',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `totoro_choice_product_id_index`(`product_id`) USING BTREE,
  INDEX `totoro_choice_order_index`(`priority`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '每日精选表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daily_choice
-- ----------------------------
INSERT INTO `daily_choice` VALUES (1, 1, '爆款人气', '人气', NULL, NULL, NULL);
INSERT INTO `daily_choice` VALUES (2, 2, '销量第一', '销量', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for daily_special
-- ----------------------------
DROP TABLE IF EXISTS `daily_special`;
CREATE TABLE `daily_special`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '秒杀商品id',
  `priority` int(11) NULL DEFAULT NULL COMMENT '展示顺序【0：优先级最高】',
  `origin_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '原始价格',
  `discount_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价格',
  `effective_date` date NULL DEFAULT NULL COMMENT '特价时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `totoro_seckill_product_id_index`(`product_id`) USING BTREE,
  INDEX `totoro_seckill_order_index`(`priority`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '每日特价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of daily_special
-- ----------------------------
INSERT INTO `daily_special` VALUES (1, 1, NULL, 100.00, 80.00, '2020-03-25', NULL, NULL);
INSERT INTO `daily_special` VALUES (2, 2, NULL, 10.00, 7.00, '2020-03-25', NULL, NULL);
INSERT INTO `daily_special` VALUES (3, 1, NULL, NULL, NULL, '2020-03-26', NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `thumb` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品缩略图',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '商品状态 【0：下架；1：上架；2：审核中】',
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `sales` int(11) NULL DEFAULT 0 COMMENT '销量',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属类别',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_category_id_index`(`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '测试商品01', '测试商品01', 'https://img.yzcdn.cn/vant/cat.jpeg', '测试商品01', 100.00, 1, 0, 0, 12, NULL, NULL);
INSERT INTO `product` VALUES (2, '测试商品02', '测试商品02', 'https://img.yzcdn.cn/vant/cat.jpeg', '测试商品02', 10.00, 1, 0, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user_browse_history
-- ----------------------------
DROP TABLE IF EXISTS `user_browse_history`;
CREATE TABLE `user_browse_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id\n',
  `product_id` bigint(20) NOT NULL COMMENT '商品id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_browse_history_user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '浏览记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `coupon_id` bigint(20) NOT NULL COMMENT '优惠券id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_coupon_user_id_coupon_id_uindex`(`user_id`, `coupon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
INSERT INTO `user_coupon` VALUES (1, 12, 1, NULL, NULL);
INSERT INTO `user_coupon` VALUES (2, 12, 2, NULL, NULL);
INSERT INTO `user_coupon` VALUES (3, 12, 3, NULL, NULL);
INSERT INTO `user_coupon` VALUES (7, 52, 7, '2020-03-06 14:30:38', NULL);
INSERT INTO `user_coupon` VALUES (8, 52, 8, '2020-03-06 14:30:38', NULL);
INSERT INTO `user_coupon` VALUES (9, 52, 9, '2020-03-06 14:30:38', NULL);
INSERT INTO `user_coupon` VALUES (10, 53, 7, '2020-03-06 14:36:55', NULL);
INSERT INTO `user_coupon` VALUES (11, 53, 8, '2020-03-06 14:36:55', NULL);
INSERT INTO `user_coupon` VALUES (12, 53, 9, '2020-03-06 14:36:55', NULL);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex` tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '用户性别 【0：未知、1：男、2：女】',
  `birthday` date NULL DEFAULT NULL COMMENT '用户生日',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `vip` tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '是否会员 【0：不是、1：是】',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户token',
  `token_expire_time` datetime(0) NULL DEFAULT NULL COMMENT 'token失效时间',
  `points` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '用户积分',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '用户余额',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：【0：禁用；1：正常】',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_email_uindex`(`email`) USING BTREE,
  UNIQUE INDEX `user_nickname_uindex`(`nickname`) USING BTREE,
  UNIQUE INDEX `user_phone_uindex`(`phone`) USING BTREE,
  UNIQUE INDEX `user_token_uindex`(`token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (12, 'lwyang', '$2a$10$s8PKmO2dOap0WHypW9lKHuF9t.a2n3Mux7gY5yL2ze9PTtqhtPSV.', 'https://img.yzcdn.cn/vant/cat.jpeg', 0, '2013-12-28', '18702760000', '1670906168@qq.com', 1, '88754e367c1342e8980d129ea59fd6e5', '2020-03-26 15:31:58', 12, 2.34, 1, '2020-03-25 15:31:58', '2020-02-29 23:13:48', NULL);
INSERT INTO `user_info` VALUES (45, '33333', '$2a$10$t0Q6wprMwTYsFyU0hZC02.ja86ZAtuQ5rvIlWcNmyPw8GyYMrpRRG', 'https://img.yzcdn.cn/vant/cat.jpeg', 2, '1950-01-01', '18702760971', '167090@aa.cc', 1, '75119027ff584fa7ba50e75bf0334ab0', '2020-03-03 17:35:14', 243, 154.65, 1, '2020-03-02 17:35:14', '2020-03-01 14:35:47', NULL);
INSERT INTO `user_info` VALUES (46, '0001', '$2a$10$1TYyqtWKGcG.8SvsZDTjMOGOUBlFIXZQf2PKzlm61qpUT/4Z7f4XO', 'https://img.yzcdn.cn/vant/cat.jpeg', 2, '2020-03-10', '18702760968', '1670906160@qq.com', 1, NULL, NULL, 35, 2324.00, 1, NULL, '2020-03-01 14:41:28', NULL);
INSERT INTO `user_info` VALUES (52, 'coupon1', '$2a$10$WuvWG9NlNBvC98TJIys5QeZWmiSdo3We8TO1qm04.nmIstRHjlWJq', NULL, 1, '1958-06-01', '18702760001', '1113584@qq.fd', 0, '3b0768353b10461b9b09ebaa90145834', '2020-03-07 20:23:06', 0, 0.00, 1, '2020-03-06 20:23:06', '2020-03-06 14:30:38', NULL);
INSERT INTO `user_info` VALUES (53, 'coupon2', '$2a$10$ANNEqkC4H1DhGy1NprsLzOPjfH5vPXP6zpHlHEaOYIDZPa9faQNp.', NULL, 0, NULL, NULL, NULL, 0, NULL, NULL, 0, 0.00, 1, NULL, '2020-03-06 14:36:55', NULL);

SET FOREIGN_KEY_CHECKS = 1;
