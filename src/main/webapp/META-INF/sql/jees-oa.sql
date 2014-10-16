/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-oa

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-10-16 14:16:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_duty`
-- ----------------------------
DROP TABLE IF EXISTS `oa_duty`;
CREATE TABLE `oa_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
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
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `url` tinytext,
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon
-- ----------------------------
INSERT INTO `oa_icon` VALUES ('1', 'asfaf', '2', 'files/attached/icon/20141015/1413339518324.png', '1', '0', '1', '1413280161974', '1', '1413339519272');
INSERT INTO `oa_icon` VALUES ('2', 'afasf', '0', 'files/attached/icon/20141015/1413339300840.png', '0', '0', '1', '1413339302187', '1', '1413339302187');
INSERT INTO `oa_icon` VALUES ('4', 'sdgsdg', '0', 'files/attached/icon/20141015/1413339366004.png', '0', '0', '1', '1413339366865', '1', '1413339366865');
INSERT INTO `oa_icon` VALUES ('5', 'asfafasf', '4', 'files/attached/icon/20141015/1413339470573.png', '0', '0', '1', '1413339471694', '1', '1413339471694');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

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
INSERT INTO `oa_log` VALUES ('10', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '3B711605981FEBEAA2101FA748671D46', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412995710927');
INSERT INTO `oa_log` VALUES ('11', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '7A80463E1A37D719A83544C972A5E454', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412997492702');
INSERT INTO `oa_log` VALUES ('12', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '1FEA005C17A47355A143321A00FA1D24', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412998114936');
INSERT INTO `oa_log` VALUES ('13', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', 'EA14879DD28028A1F10431AB9C1E022D', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1412999311110');
INSERT INTO `oa_log` VALUES ('14', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '00508C8D452EABC4EFADED9F41D5652E', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1413005216537');
INSERT INTO `oa_log` VALUES ('16', '用户登录', 'system', 'index', 'member', 'logon', 'http://127.0.0.1:8080/jees-oa/login', 'http://127.0.0.1:8080/jees-oa/index/member/logon', '******', '2251D01E360FB64798EE391A2E90DB50', '{\"mid\":1}', '', '{}', null, '0', '127.0.0.1', '1413089226504');
INSERT INTO `oa_log` VALUES ('17', '信息管理', 'system', 'index', 'log', 'layout', null, 'http://127.0.0.1:8080/jees-oa/index/log/layout', null, '3004EC0E20BF25B1B4FE7E99FBA2617E', null, null, null, null, '0', '127.0.0.1', '1413350567861');
INSERT INTO `oa_log` VALUES ('18', '信息管理', 'system', 'index', 'log', 'layout', null, 'http://127.0.0.1:8080/jees-oa/index/log/layout', null, 'E02D06E9259348E7696ED0D3833F0F0C', null, null, null, null, '1', '127.0.0.1', '1413367036348');
INSERT INTO `oa_log` VALUES ('19', '信息管理', 'system', 'index', 'log', 'layout', null, 'http://127.0.0.1:8080/jees-oa/index/log/layout', null, '09A1E4CEDF342716CA9D59E2D42C735A', null, null, null, null, '1', '127.0.0.1', '1413431700736');

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
INSERT INTO `oa_log_setting` VALUES ('1', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1413173925267');
INSERT INTO `oa_log_setting` VALUES ('2', '0', '1', '0', '0', '0', '0', '0', '0', '1', '1413173925440');
INSERT INTO `oa_log_setting` VALUES ('3', '1', '0', '1', '0', '1', '0', '0', '0', '1', '1413173925555');
INSERT INTO `oa_log_setting` VALUES ('4', '0', '0', '1', '1', '0', '0', '0', '0', '1', '1413173925663');
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
  `password` char(32) NOT NULL DEFAULT '',
  `salt` char(6) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `active_time` bigint(20) NOT NULL DEFAULT '0',
  `active_ip` varchar(64) NOT NULL DEFAULT '',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_ip` varchar(64) NOT NULL DEFAULT '',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member
-- ----------------------------
INSERT INTO `oa_member` VALUES ('1', 'admin', '超极管理员', '0f4e8ac95b5c1fc48dc98004c7525bc7', '888888', '0', '1', '1411090286300', '127.0.0.1', '1', '127.0.0.1', '1411090286300', '1', '1411090286300');

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
  `icon` varchar(128) NOT NULL DEFAULT '',
  `target` varchar(8) NOT NULL DEFAULT '',
  `url` tinytext,
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------
INSERT INTO `oa_menu` VALUES ('2', 'safasf', '0', 'icon-save', '_blank', 'asf', '0', '0', '1', '1413252255813', '1', '1413252255813');
INSERT INTO `oa_menu` VALUES ('3', 'afasfasf', '2', 'icon-auto2', '_tab', 'asf/asfsss/asf', '0', '1', '1', '1413252274492', '1', '1413433030405');
INSERT INTO `oa_menu` VALUES ('4', 'asfasf', '0', 'icon-auto4', '_blank', 'index/log/layout', '0', '1', '1', '1413264061812', '1', '1413433011125');

-- ----------------------------
-- Table structure for `oa_notice`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice`;
CREATE TABLE `oa_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `type_id` int(11) NOT NULL DEFAULT '0',
  `content` text,
  `sort` bigint(20) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice
-- ----------------------------
INSERT INTO `oa_notice` VALUES ('1', '文章标题', '1', 'dsgsdg', '2147483647', '0', '1', '1413337742808', '1', '1413338129516');
INSERT INTO `oa_notice` VALUES ('2', 'sdgsdgsdg', '2', 'sdgsgd', '2147483647', '0', '1', '1413338224381', '1', '1413338224381');
INSERT INTO `oa_notice` VALUES ('3', 'sdgsdgsdg', '1', 'fgdfdfhdfhdfh', '2147483647', '0', '1', '1413338244784', '1', '1413338244784');
INSERT INTO `oa_notice` VALUES ('4', 'sdgsdgsdg', '2', 'sdgsdgsdg', '2147483647', '0', '1', '1413338259932', '1', '1413338259932');

-- ----------------------------
-- Table structure for `oa_notice_publish`
-- ----------------------------
DROP TABLE IF EXISTS `oa_notice_publish`;
CREATE TABLE `oa_notice_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_id` int(11) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `rel_id` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
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
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice_type
-- ----------------------------
INSERT INTO `oa_notice_type` VALUES ('1', '内部通知', '0', '0', '0', '1', '1413336213384', '1', '1413336213384');
INSERT INTO `oa_notice_type` VALUES ('2', '公共通知', '0', '0', '0', '1', '1413336695361', '1', '1413336710451');
INSERT INTO `oa_notice_type` VALUES ('3', '部门通知', '1', '0', '0', '1', '1413336926085', '1', '1413336926085');

-- ----------------------------
-- Table structure for `oa_organize`
-- ----------------------------
DROP TABLE IF EXISTS `oa_organize`;
CREATE TABLE `oa_organize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `remark` text,
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
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
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `refer_id` int(11) NOT NULL DEFAULT '0',
  `menu_list_enable` tinyint(4) NOT NULL DEFAULT '0',
  `menu_pick_enable` tinyint(4) NOT NULL DEFAULT '0',
  `module` varchar(64) NOT NULL DEFAULT '',
  `controller` varchar(64) NOT NULL DEFAULT '',
  `action` varchar(64) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_resource
-- ----------------------------
INSERT INTO `oa_resource` VALUES ('1', '默认模块', '0', '0', '1', '0', 'index', '', '', '0', '0', '1', '1413172095468', '1', '1413431628590');
INSERT INTO `oa_resource` VALUES ('2', '日志管理', '1', '0', '1', '0', 'index', 'log', '', '0', '0', '1', '1413172138260', '1', '1413431641472');
INSERT INTO `oa_resource` VALUES ('3', '信息管理', '2', '0', '1', '1', 'index', 'log', 'layout', '0', '0', '1', '1413172230720', '1', '1413431687430');
INSERT INTO `oa_resource` VALUES ('4', '信息列表', '2', '0', '0', '0', 'index', 'log', 'list', '0', '0', '1', '1413172303783', '1', '1413172303783');
INSERT INTO `oa_resource` VALUES ('5', '信息编辑', '2', '3', '1', '0', 'index', 'log', 'edit', '0', '0', '1', '1413177000028', '1', '1413431675044');
INSERT INTO `oa_resource` VALUES ('6', 'fafasf', '0', '0', '1', '0', 'fasf', 'asf', 'asf', '0', '0', '1', '1413343590371', '1', '1413362116778');
INSERT INTO `oa_resource` VALUES ('7', 'sdsg', '0', '0', '0', '0', 'sdg', 'sdg', 'sdg', '0', '0', '1', '1413343686112', '1', '1413343686112');
INSERT INTO `oa_resource` VALUES ('8', 'afas', '0', '0', '0', '0', 'index', 'log', 'list', '0', '0', '1', '1413343711628', '1', '1413343711628');
INSERT INTO `oa_resource` VALUES ('9', 'fsfaasf', '0', '0', '1', '0', 'asf', 'asf', 'asf', '0', '-1', '1', '1413361649960', '1', '1413361817521');
INSERT INTO `oa_resource` VALUES ('10', 'asfasf', '0', '0', '1', '1', 'asf', 'asfsss', 'asf', '0', '0', '1', '1413431559776', '1', '1413431559776');

-- ----------------------------
-- Table structure for `oa_role`
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `remark` text,
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('1', '后台管理员', '0', '0', '1', '父级', '1', '1411090286300', '1', '1411090286300');
INSERT INTO `oa_role` VALUES ('2', '超极管理员', '1', '0', '1', '', '1', '1411090286300', '1', '1411090286300');
INSERT INTO `oa_role` VALUES ('3', '普通管理员', '1', '0', '1', '', '1', '1411090286300', '1', '1411090286300');
INSERT INTO `oa_role` VALUES ('6', '角色', '0', '0', '1', 'asfasf', '1', '1411715962184', '1', '1411715980111');

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
  `operate_id` int(11) NOT NULL DEFAULT '0',
  `operate_ip` varchar(64) NOT NULL DEFAULT '',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_setting
-- ----------------------------
INSERT INTO `oa_setting` VALUES ('1', 'webName', 'system', '在线办公管理系统', '系统名称', '1', '127.0.0.1', '1413018957263');
INSERT INTO `oa_setting` VALUES ('2', 'pageSize', 'system', '15', '分页大小', '1', '127.0.0.1', '1413018859640');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_upload
-- ----------------------------
INSERT INTO `oa_upload` VALUES ('1', 'key.png', 'files/attached/icon/20141014/1413280160802.png', '1', '127.0.0.1', '1413280160802');
INSERT INTO `oa_upload` VALUES ('2', 'crowd.png', 'files/attached/icon/20141015/1413339264450.png', '1', '127.0.0.1', '1413339264450');
INSERT INTO `oa_upload` VALUES ('3', 'home.png', 'files/attached/icon/20141015/1413339300840.png', '1', '127.0.0.1', '1413339300840');
INSERT INTO `oa_upload` VALUES ('4', 'select.png', 'files/attached/icon/20141015/1413339351286.png', '1', '127.0.0.1', '1413339351286');
INSERT INTO `oa_upload` VALUES ('5', 'setting.png', 'files/attached/icon/20141015/1413339366004.png', '1', '127.0.0.1', '1413339366004');
INSERT INTO `oa_upload` VALUES ('6', 'key.png', 'files/attached/icon/20141015/1413339470573.png', '1', '127.0.0.1', '1413339470573');
INSERT INTO `oa_upload` VALUES ('7', 'detail.png', 'files/attached/icon/20141015/1413339518324.png', '1', '127.0.0.1', '1413339518324');
