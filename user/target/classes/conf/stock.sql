/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 127.0.0.1:3306
 Source Schema         : stock

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 20/05/2023 11:53:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banks
-- ----------------------------
DROP TABLE IF EXISTS `banks`;
CREATE TABLE `banks`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `bank` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bank_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '银行代码',
  `b2c` int NOT NULL DEFAULT 1 COMMENT '支持网银b2c',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for day_setting
-- ----------------------------
DROP TABLE IF EXISTS `day_setting`;
CREATE TABLE `day_setting`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户的user表中的主键',
  `invest_amount` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户投资奖金',
  `levels` int NULL DEFAULT NULL COMMENT '用户的杠杆',
  `fund_cycle` int NULL DEFAULT NULL COMMENT '配资周期',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '总计应支付',
  `loss_close_line` decimal(10, 2) NULL DEFAULT NULL COMMENT '亏损平仓线',
  `loss_warn_line` decimal(10, 2) NULL DEFAULT NULL COMMENT '亏损警戒线',
  `total_allocation_fund` decimal(10, 2) NULL DEFAULT NULL COMMENT '总配资资金',
  `isread` bigint NULL DEFAULT NULL COMMENT '是否阅读合同0表示没有，1表示有',
  `start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '结束时间',
  `is_settled` int NULL DEFAULT 0 COMMENT '是否已经结算，已结算为1，未结算0，已结算了不会再做定时任务中计算',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for month_setting
-- ----------------------------
DROP TABLE IF EXISTS `month_setting`;
CREATE TABLE `month_setting`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户的user表中的主键',
  `invest_amount` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户投资奖金',
  `levels` int NULL DEFAULT NULL COMMENT '用户的杠杆',
  `fund_cycle` int NULL DEFAULT NULL COMMENT '配资周期',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `total_allocation_fund` decimal(10, 2) NULL DEFAULT NULL COMMENT '总配资资金',
  `isread` bigint NULL DEFAULT NULL COMMENT '是否阅读合同0表示没有，1表示有',
  `start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '结束时间',
  `is_settled` int NULL DEFAULT NULL COMMENT '是否已经结算，已结算为1，未结算0，已结算了不会再做定时任务中计算',
  `loss_warn_line` decimal(10, 2) NULL DEFAULT NULL COMMENT '亏损警戒线',
  `loss_close_line` decimal(10, 2) NULL DEFAULT NULL COMMENT '亏损平仓线',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '股票id,自增长',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '股票代码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发行时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '股票名称',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '股票类别',
  `is_trade` int NULL DEFAULT NULL COMMENT '是否可以交易',
  `quantity` bigint NULL DEFAULT NULL COMMENT '市场还有多少股可以购买',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stock_increase
-- ----------------------------
DROP TABLE IF EXISTS `stock_increase`;
CREATE TABLE `stock_increase`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '股票代码',
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '时间',
  `open` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开盘价',
  `turnover_ratio` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '换手率',
  `amount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '成交量',
  `change` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '价格变动',
  `high` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最高价',
  `low` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最低价',
  `change_ratio` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '涨跌幅',
  `close` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '收盘价',
  `volume` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '成交量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5801 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键值，自动增长',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户Id',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'buy,sell,in,out',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `result` int NULL DEFAULT NULL COMMENT '0失败，1成功',
  `channel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是通过哪个渠道进行的，buy,sell ,可以是web页面，in,out 是通过哪个渠道进行转帐',
  `stock_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '股票Id（股票买卖）',
  `bank_id` bigint NULL DEFAULT NULL COMMENT '银行卡id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '交易主键',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户id',
  `stock_id` int NULL DEFAULT NULL COMMENT '股票主键',
  `trade_type` int NULL DEFAULT NULL COMMENT 'buy 0, sell 1',
  `quantity` float(20, 2) NULL DEFAULT NULL COMMENT '购买或者卖出多少股',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '购买或者卖出的价格',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表中主键，自增',
  `amount` float(14, 2) NULL DEFAULT NULL COMMENT '金额',
  `bank_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '银行名称或者支付宝或者微信',
  `card_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '银行卡号或者支付宝帐号或者微信号',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户id',
  `transfer_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '银行卡交易返回单号或者支付宝的单号，微信转帐的单号',
  `result` int NULL DEFAULT NULL COMMENT '转帐结果，0不成功，1表示成功',
  `card_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '银行卡密码（需要吗？）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户姓名',
  `area_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地区代码',
  `phone_no` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号码',
  `head_img_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像地址，相对地址，前端要加服务器地址才可以显示',
  `trade_password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'mysql PASSWORD加密保存',
  `invitation_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邀请码',
  `complete_level` int NULL DEFAULT NULL COMMENT '信息填写完整值（可以通过完整值，看一下系统奖励多少现金，只能抵扣平台管理费用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int NULL DEFAULT NULL COMMENT '账号状态：0：禁用，1：启用，2：禁止交易',
  `login_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '登录密码 mysql PASSWORD加密',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '推荐人的id',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '身份证号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_bank
-- ----------------------------
DROP TABLE IF EXISTS `user_bank`;
CREATE TABLE `user_bank`  (
  `id` bigint NOT NULL COMMENT '主键，自增',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户userId',
  `bank_id` bigint NULL DEFAULT NULL COMMENT '银行banks表中Id',
  `card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '银行卡号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开户人名字',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '开户地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_stock
-- ----------------------------
DROP TABLE IF EXISTS `user_stock`;
CREATE TABLE `user_stock`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户主键',
  `stock_id` int NOT NULL COMMENT '股票主键',
  `quantity` float(20, 2) NOT NULL COMMENT '此用户占有此股票多少股，可以有小数',
  PRIMARY KEY (`user_id`, `stock_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表主键，自增',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `after_balance` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户使用钱包之后的余额',
  `before_balance` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户使用钱包之前的余额',
  `after_freeze` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户使用钱包之后的冻结余额',
  `before_freeze` decimal(14, 2) NULL DEFAULT NULL COMMENT '用户使用钱包之前的冻结余额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `wallet_log_id` int NULL DEFAULT NULL COMMENT 'wallet_log表中的Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wallet_log
-- ----------------------------
DROP TABLE IF EXISTS `wallet_log`;
CREATE TABLE `wallet_log`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户ID，可以直接在用户表中找到此用户的',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '使用的奖金',
  `in_out` int NULL DEFAULT NULL COMMENT '是进帐还是出帐，1表示进帐，0表示出帐',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `descr` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
