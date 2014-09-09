/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-oa

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-08-28 17:21:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_icon`
-- ----------------------------
DROP TABLE IF EXISTS `oa_icon`;
CREATE TABLE `oa_icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL DEFAULT '0',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `url` tinytext NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_icon_type`
-- ----------------------------
DROP TABLE IF EXISTS `oa_icon_type`;
CREATE TABLE `oa_icon_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon_type
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_log`
-- ----------------------------
DROP TABLE IF EXISTS `oa_log`;
CREATE TABLE `oa_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `type` varchar(32) NOT NULL DEFAULT '',
  `module` varchar(32) NOT NULL DEFAULT '',
  `controller` varchar(32) NOT NULL DEFAULT '',
  `action` varchar(32) NOT NULL DEFAULT '',
  `request` tinytext NOT NULL,
  `result` text NOT NULL,
  `idCreate` int(11) NOT NULL DEFAULT '0',
  `ipCreate` varchar(64) NOT NULL DEFAULT '',
  `timeCreate` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_log
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_member`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member`;
CREATE TABLE `oa_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(64) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `idCreate` int(11) NOT NULL DEFAULT '0',
  `idUpdate` int(11) NOT NULL DEFAULT '0',
  `password` char(32) NOT NULL DEFAULT '',
  `salt` char(6) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `timeCreate` bigint(20) NOT NULL DEFAULT '0',
  `timeUpdate` bigint(20) NOT NULL DEFAULT '0',
  `timeActive` bigint(20) NOT NULL DEFAULT '0',
  `ipCreate` varchar(64) NOT NULL DEFAULT '',
  `ipActive` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member
-- ----------------------------
INSERT INTO `oa_member` VALUES ('1', 'admin', '超极管理员', '0', '0', '9634A8F6EA8AB6E32E0B24C2919B66BC', '888888', '0', '1', '0', '0', '0', '', '');

-- ----------------------------
-- Table structure for `oa_member_organize_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member_organize_rel`;
CREATE TABLE `oa_member_organize_rel` (
  `id_member` int(11) NOT NULL DEFAULT '0',
  `id_organize` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_member`,`id_organize`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member_organize_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_member_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member_role_rel`;
CREATE TABLE `oa_member_role_rel` (
  `id_member` int(11) NOT NULL DEFAULT '0',
  `id_role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_member`,`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member_role_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_menu`
-- ----------------------------
DROP TABLE IF EXISTS `oa_menu`;
CREATE TABLE `oa_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `idParent` int(11) NOT NULL DEFAULT '0',
  `idCreate` int(11) NOT NULL DEFAULT '0',
  `idUpdate` int(11) NOT NULL DEFAULT '0',
  `icon` varchar(128) NOT NULL DEFAULT '',
  `url` tinytext NOT NULL,
  `target` varchar(8) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `timeCreate` bigint(20) NOT NULL DEFAULT '0',
  `timeUpdate` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_notice`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice`;
CREATE TABLE `oa_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `id_type` int(11) NOT NULL DEFAULT '0',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `sort` bigint(20) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_notice_publish`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice_publish`;
CREATE TABLE `oa_notice_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_notice` int(11) NOT NULL DEFAULT '0',
  `id_rel` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice_publish
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_notice_type`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice_type`;
CREATE TABLE `oa_notice_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice_type
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_organize`
-- ----------------------------
DROP TABLE IF EXISTS `oa_organize`;
CREATE TABLE `oa_organize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `id_parent` int(11) NOT NULL DEFAULT '0',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_organize
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_resource`
-- ----------------------------
DROP TABLE IF EXISTS `oa_resource`;
CREATE TABLE `oa_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `content` tinytext NOT NULL,
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_role`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `id_parent` int(11) NOT NULL DEFAULT '0',
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_role_menu_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_menu_rel`;
CREATE TABLE `oa_role_menu_rel` (
  `id_role` int(11) NOT NULL DEFAULT '0',
  `id_menu` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_role`,`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_menu_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_role_resource_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_resource_rel`;
CREATE TABLE `oa_role_resource_rel` (
  `id_role` int(11) NOT NULL DEFAULT '0',
  `id_resource` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_role`,`id_resource`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_resource_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_setting`
-- ----------------------------
DROP TABLE IF EXISTS `oa_setting`;
CREATE TABLE `oa_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL DEFAULT '',
  `type` varchar(64) NOT NULL DEFAULT '',
  `content` tinytext NOT NULL,
  `remark` tinytext NOT NULL,
  `id_create` int(11) NOT NULL DEFAULT '0',
  `id_update` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_setting
-- ----------------------------
INSERT INTO `oa_setting` VALUES ('1', 'webName', 'system', '在线办公管理系统', '系统名称', '0', '0', '0', '0', '0');
INSERT INTO `oa_setting` VALUES ('2', 'pageSize', 'system', '15', '分页大小', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `oa_upload`
-- ----------------------------
DROP TABLE IF EXISTS `oa_upload`;
CREATE TABLE `oa_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_create` int(11) NOT NULL DEFAULT '0',
  `url` tinytext NOT NULL,
  `file` tinytext NOT NULL,
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_upload
-- ----------------------------
