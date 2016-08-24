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
  `version` varchar(255) DEFAULT NULL COMMENT '�汾��',
  `name` varchar(255) DEFAULT NULL COMMENT '����',
  `size` int(11) DEFAULT NULL COMMENT '�ļ���С',
  `last_version` varchar(255) DEFAULT NULL COMMENT '��һ�汾',
  `path` varchar(255) DEFAULT NULL COMMENT '����·��',
  `upload_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '�ϴ�ʱ��',
  `status` varchar(3) DEFAULT '0' COMMENT '״̬(�Ƿ��������ص�)',
  `file_type` varchar(30) DEFAULT NULL COMMENT '�ļ����ͣ�apk,zip�ȣ�',
  `run_plat` varchar(255) DEFAULT NULL COMMENT '����ƽ̨',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_editor
-- ----------------------------
DROP TABLE IF EXISTS `diary_editor`;
CREATE TABLE `diary_editor` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '�����ʶ��',
  `text` text COMMENT 'ҳ������',
  `image` text COMMENT 'ͼƬ����l·��������·����:�ָ������ϣ�ͼƬ���ԡ���������',
  `font_style` varchar(255) DEFAULT NULL COMMENT '����',
  `background` varchar(255) DEFAULT NULL COMMENT '����',
  `last_sync_date` timestamp NULL DEFAULT NULL COMMENT '�ϴ�ͬ��ʱ��',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeIdx` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_user
-- ----------------------------
DROP TABLE IF EXISTS `diary_user`;
CREATE TABLE `diary_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL COMMENT '�û���',
  `password` varchar(255) NOT NULL COMMENT '�û�����',
  `email` varchar(255) NOT NULL COMMENT '����',
  `status` varchar(3) NOT NULL DEFAULT '0' COMMENT '�û�״̬����ŵ����ã�Ĭ��0������״̬',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '�ֻ�����',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '�ϴε�¼ip',
  `last_login_date` timestamp NULL DEFAULT NULL COMMENT '�ϴε�¼ʱ��',
  `photo_uri` varchar(512) DEFAULT NULL COMMENT '�û�ͷ��·��',
  `desc` text COMMENT '���˼��',
  `reg_date` timestamp NULL DEFAULT NULL COMMENT 'ע��ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for diary_user_log
-- ----------------------------
DROP TABLE IF EXISTS `diary_user_log`;
CREATE TABLE `diary_user_log` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(50) DEFAULT '' COMMENT '�û���',
  `mac` varchar(255) DEFAULT NULL COMMENT '�豸��',
  `ip` varchar(50) DEFAULT NULL COMMENT '��¼ip',
  `action` varchar(255) DEFAULT NULL COMMENT '��¼Ŀ�ģ���¼�ĸ�ģ�飩',
  `date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '��¼ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
