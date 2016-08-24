/*
MySQL Data Transfer
Source Host: localhost
Source Database: diary
Target Host: localhost
Target Database: diary
Date: 2016/8/24 9:54:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for apk_info
-- ----------------------------
DROP TABLE IF EXISTS `apk_info`;
CREATE TABLE `apk_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(255) DEFAULT NULL COMMENT '版本号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `last_version` varchar(255) DEFAULT NULL COMMENT '上一版本',
  `path` varchar(255) DEFAULT NULL COMMENT '保存路径',
  `upload_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `status` varchar(3) DEFAULT '0' COMMENT '状态(是否允许下载等)',
  `file_type` varchar(30) DEFAULT NULL COMMENT '文件类型（apk,zip等）',
  `run_plat` varchar(255) DEFAULT NULL COMMENT '运行平台',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_editor
-- ----------------------------
DROP TABLE IF EXISTS `diary_editor`;
CREATE TABLE `diary_editor` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '编码标识符',
  `text` text COMMENT '页面内容',
  `image` text COMMENT '图片名称l路径（名称路径以:分隔）集合，图片间以‘；’隔开',
  `font_style` varchar(255) DEFAULT NULL COMMENT '字体',
  `background` varchar(255) DEFAULT NULL COMMENT '背景',
  `last_sync_date` timestamp NULL DEFAULT NULL COMMENT '上次同步时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeIdx` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_user
-- ----------------------------
DROP TABLE IF EXISTS `diary_user`;
CREATE TABLE `diary_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `status` varchar(3) NOT NULL DEFAULT '0' COMMENT '用户状态（封号等作用）默认0，正常状态',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '上次登录ip',
  `last_login_date` timestamp NULL DEFAULT NULL COMMENT '上次登录时间',
  `photo_uri` varchar(512) DEFAULT NULL COMMENT '用户头像路径',
  `desc` text COMMENT '个人简介',
  `reg_date` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_user_log
-- ----------------------------
DROP TABLE IF EXISTS `diary_user_log`;
CREATE TABLE `diary_user_log` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(50) DEFAULT '' COMMENT '用户名',
  `mac` varchar(255) DEFAULT NULL COMMENT '设备号',
  `ip` varchar(50) DEFAULT NULL COMMENT '登录ip',
  `action` varchar(255) DEFAULT NULL COMMENT '登录目的（登录哪个模块）',
  `date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
