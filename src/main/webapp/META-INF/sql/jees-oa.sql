/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-oa

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-10-10 17:21:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_duty`
-- ----------------------------
DROP TABLE IF EXISTS `oa_duty`;
CREATE TABLE `oa_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_duty
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_icon`
-- ----------------------------
DROP TABLE IF EXISTS `oa_icon`;
CREATE TABLE `oa_icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `url` tinytext,
  `sort` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon
-- ----------------------------
INSERT INTO `oa_icon` VALUES ('1', 'asf', '0', '0', '0', null, '0', '0', '0');

-- ----------------------------
-- Table structure for `oa_icon_type`
-- ----------------------------
DROP TABLE IF EXISTS `oa_icon_type`;
CREATE TABLE `oa_icon_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon_type
-- ----------------------------
INSERT INTO `oa_icon_type` VALUES ('2', '类型', '0', '1', '1', '0', '0', '1412823937563', '1412824598882');
INSERT INTO `oa_icon_type` VALUES ('3', '子类型', '2', '1', '1', '0', '1', '1412823957848', '1412824752251');
INSERT INTO `oa_icon_type` VALUES ('4', '第二类型', '0', '1', '1', '0', '1', '1412848164048', '1412848164048');

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
  `referer` tinytext,
  `request_url` tinytext,
  `request_param` tinytext,
  `session_id` varchar(255) DEFAULT NULL,
  `session_value` tinytext,
  `response_view` varchar(255) DEFAULT NULL,
  `response_data` tinytext,
  `content` tinytext,
  `operate_id` int(11) NOT NULL DEFAULT '0',
  `operate_ip` varchar(64) NOT NULL DEFAULT '',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_log
-- ----------------------------
INSERT INTO `oa_log` VALUES ('1', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '73AB54ED8DACBA9F52F614823AEA3BC8', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412757325809');
INSERT INTO `oa_log` VALUES ('2', '日志设置', 'system', 'index', 'log', 'saveSetting', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1412757666541');
INSERT INTO `oa_log` VALUES ('3', '日志设置', 'system', 'index', 'log', 'saveSetting', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1412757697143');
INSERT INTO `oa_log` VALUES ('4', '日志设置', 'system', 'index', 'log', 'saveSetting', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1412757698231');
INSERT INTO `oa_log` VALUES ('5', '日志设置', 'system', 'index', 'log', 'saveSetting', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1412757698993');
INSERT INTO `oa_log` VALUES ('6', '日志设置', 'system', 'index', 'log', 'saveSetting', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1412757700014');
INSERT INTO `oa_log` VALUES ('7', '日志设置', 'system', 'index', 'log', 'saveSetting', 'http://127.0.0.1:8080/jees-oa/index/log/editSetting', 'http://127.0.0.1:8080/jees-oa/index/log/saveSetting', '{\"id\":[\"10\"],\"session_id\":[\"1\"],\"request_param\":[\"1\"],\"enable\":[\"1\"],\"response_data\":[\"1\"],\"referer\":[\"1\"],\"response_view\":[\"1\"],\"request_url\":[\"1\"],\"session_value\":[\"1\"]}', '73AB54ED8DACBA9F52F614823AEA3BC8', '{\"mid\":1}', '', '{}', null, '1', '127.0.0.1', '1412757700846');
INSERT INTO `oa_log` VALUES ('8', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', 'FD9ADC7A8D63509993E43AC1113F89CD', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412823874711');
INSERT INTO `oa_log` VALUES ('9', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '79443F8B58F136DCB583F1124E160F18', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412848157177');

-- ----------------------------
-- Table structure for `oa_log_setting`
-- ----------------------------
DROP TABLE IF EXISTS `oa_log_setting`;
CREATE TABLE `oa_log_setting` (
  `id` int(11) NOT NULL,
  `enable` tinyint(4) NOT NULL DEFAULT '0',
  `referer` tinyint(4) NOT NULL DEFAULT '0',
  `request_url` tinyint(4) NOT NULL DEFAULT '0',
  `request_param` tinyint(4) NOT NULL DEFAULT '0',
  `session_id` tinyint(4) NOT NULL DEFAULT '0',
  `session_value` tinyint(4) NOT NULL DEFAULT '0',
  `response_view` tinyint(4) NOT NULL DEFAULT '0',
  `response_data` tinyint(4) NOT NULL DEFAULT '0',
  `operate_id` int(11) NOT NULL DEFAULT '0',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_log_setting
-- ----------------------------
INSERT INTO `oa_log_setting` VALUES ('2', '0', '0', '1', '1', '0', '0', '0', '0', '1', '1412757696642');
INSERT INTO `oa_log_setting` VALUES ('7', '0', '1', '0', '0', '0', '1', '0', '0', '1', '1412757697466');
INSERT INTO `oa_log_setting` VALUES ('8', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1412757698591');
INSERT INTO `oa_log_setting` VALUES ('9', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1412757699265');
INSERT INTO `oa_log_setting` VALUES ('10', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1412757700340');

-- ----------------------------
-- Table structure for `oa_member`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member`;
CREATE TABLE `oa_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial` varchar(64) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `password` char(32) NOT NULL DEFAULT '',
  `salt` char(6) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `active_time` bigint(20) NOT NULL DEFAULT '0',
  `create_ip` varchar(64) NOT NULL DEFAULT '',
  `active_ip` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member
-- ----------------------------
INSERT INTO `oa_member` VALUES ('1', 'admin', '超极管理员', '1', '1', '0f4e8ac95b5c1fc48dc98004c7525bc7', '888888', '0', '1', '1411090286300', '1411090286300', '1411090286300', '127.0.0.1', '127.0.0.1');

-- ----------------------------
-- Table structure for `oa_member_organize_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member_organize_rel`;
CREATE TABLE `oa_member_organize_rel` (
  `member_id` int(11) NOT NULL DEFAULT '0',
  `organize_id` int(11) NOT NULL DEFAULT '0',
  `duty_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`member_id`,`organize_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member_organize_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_member_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_member_role_rel`;
CREATE TABLE `oa_member_role_rel` (
  `member_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`member_id`,`role_id`)
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
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `icon` varchar(128) NOT NULL DEFAULT '',
  `url` tinytext,
  `target` varchar(8) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------
INSERT INTO `oa_menu` VALUES ('1', '测试', '0', '1', '1', '', '', '', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `oa_notice`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice`;
CREATE TABLE `oa_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `content` text,
  `sort` bigint(20) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
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
  `notice_id` int(11) NOT NULL DEFAULT '0',
  `rel_id` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
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
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
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
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
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
  `module` varchar(64) NOT NULL DEFAULT '',
  `controller` varchar(64) NOT NULL DEFAULT '',
  `action` varchar(64) NOT NULL DEFAULT '',
  `refer_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_resource
-- ----------------------------
INSERT INTO `oa_resource` VALUES ('2', '测试按时发', 'index', 'resource', 'list', '2', '1', '1', '1', '1411867810171', '1411869123921');
INSERT INTO `oa_resource` VALUES ('7', 'asf', 'asf', 'asf', 'asf', '0', '0', '1', '1', '1411887872308', '1411887872308');
INSERT INTO `oa_resource` VALUES ('8', '用户登录', 'index', 'member', 'logon', '0', '0', '1', '1', '1412754896836', '1412754896836');
INSERT INTO `oa_resource` VALUES ('9', '日志管理', 'index', 'log', 'layout', '0', '0', '1', '1', '1412755223211', '1412755223211');
INSERT INTO `oa_resource` VALUES ('10', '日志设置', 'index', 'log', 'saveSetting', '0', '0', '1', '1', '1412757644532', '1412757644532');

-- ----------------------------
-- Table structure for `oa_role`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('1', '后台管理员', '0', '1', '1', '0', '1', '1411090286300', '1411090286300', '父级');
INSERT INTO `oa_role` VALUES ('2', '超极管理员', '1', '1', '1', '0', '1', '1411090286300', '1411090286300', '');
INSERT INTO `oa_role` VALUES ('3', '普通管理员', '1', '1', '1', '0', '1', '1411090286300', '1411090286300', '');
INSERT INTO `oa_role` VALUES ('6', '角色', '0', '1', '1', '0', '1', '1411715962184', '1411715980111', 'asfasf');

-- ----------------------------
-- Table structure for `oa_role_menu_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_menu_rel`;
CREATE TABLE `oa_role_menu_rel` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_menu_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `oa_role_resource_rel`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_resource_rel`;
CREATE TABLE `oa_role_resource_rel` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `resource_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`resource_id`)
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
  `content` tinytext,
  `remark` tinytext,
  `create_id` int(11) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
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
  `name` tinytext,
  `uri` tinytext,
  `operate_id` int(11) NOT NULL DEFAULT '0',
  `operate_ip` varchar(64) NOT NULL DEFAULT '',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_upload
-- ----------------------------
