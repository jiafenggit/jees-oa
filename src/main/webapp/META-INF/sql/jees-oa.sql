/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-oa

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-10-28 14:52:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oa_duty`
-- ----------------------------
DROP TABLE IF EXISTS `oa_duty`;
CREATE TABLE `oa_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_duty
-- ----------------------------
INSERT INTO `oa_duty` VALUES ('1', '基础职务', '0', '0', '1', '1', '1414474444315', '1', '1414474444315');
INSERT INTO `oa_duty` VALUES ('2', '研发组职务', '0', '0', '1', '1', '1414474510316', '1', '1414474510316');
INSERT INTO `oa_duty` VALUES ('3', '部门负责人', '1', '0', '1', '1', '1414474562763', '1', '1414474562763');
INSERT INTO `oa_duty` VALUES ('4', '部门职员', '1', '0', '1', '1', '1414474569567', '1', '1414474569567');
INSERT INTO `oa_duty` VALUES ('5', '研发组长', '2', '0', '1', '1', '1414474576872', '1', '1414474576872');
INSERT INTO `oa_duty` VALUES ('6', '研发组员', '2', '0', '1', '1', '1414474582268', '1', '1414474582268');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon
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
  `referer` text,
  `request_url` text,
  `request_param` text,
  `session_id` tinytext,
  `session_value` text,
  `response_view` tinytext,
  `response_data` text,
  `content` text,
  `operate_id` int(11) NOT NULL DEFAULT '0',
  `operate_ip` varchar(64) NOT NULL DEFAULT '',
  `operate_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_log
-- ----------------------------
INSERT INTO `oa_log` VALUES ('1', '登录操作', 'system', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '0', '127.0.0.1', '1414476749684');

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
INSERT INTO `oa_log_setting` VALUES ('44', '1', '0', '0', '0', '0', '0', '0', '0', '1', '1414474893177');

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
  `active_time` bigint(20) DEFAULT '0',
  `active_ip` varchar(64) DEFAULT '',
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
INSERT INTO `oa_member` VALUES ('1', 'admin', '管理员', '0f4e8ac95b5c1fc48dc98004c7525bc7', '888888', '0', '1', '1414478973383', '127.0.0.1', '1', '127.0.0.1', '1411090286300', '1', '1414476075652');

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
INSERT INTO `oa_member_organize_rel` VALUES ('1', '6', '5');

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
INSERT INTO `oa_member_role_rel` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `oa_menu`
-- ----------------------------
DROP TABLE IF EXISTS `oa_menu`;
CREATE TABLE `oa_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `icon` varchar(128) NOT NULL DEFAULT '',
  `goal` varchar(8) NOT NULL DEFAULT '',
  `url` tinytext,
  `sort` int(11) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `create_id` int(11) NOT NULL DEFAULT '0',
  `create_time` bigint(20) NOT NULL DEFAULT '0',
  `update_id` int(11) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------
INSERT INTO `oa_menu` VALUES ('1', '默认菜单', '0', '', '_iframe', '', '0', '1', '1', '1414473027167', '1', '1414473027167');
INSERT INTO `oa_menu` VALUES ('2', '基础管理', '1', '', '_iframe', '', '0', '1', '1', '1414473128316', '1', '1414473171153');
INSERT INTO `oa_menu` VALUES ('3', '服务支持', '1', '', '_iframe', '', '0', '1', '1', '1414473191743', '1', '1414473191743');
INSERT INTO `oa_menu` VALUES ('4', '个人事务', '2', '', '_iframe', '', '0', '1', '1', '1414473213512', '1', '1414473213512');
INSERT INTO `oa_menu` VALUES ('5', '基础设置', '2', '', '_iframe', '', '0', '1', '1', '1414473222525', '1', '1414473222525');
INSERT INTO `oa_menu` VALUES ('6', '系统管理', '2', '', '_iframe', '', '0', '1', '1', '1414473231226', '1', '1414473231226');
INSERT INTO `oa_menu` VALUES ('7', '个人信息', '4', '', '_iframe', 'index/member/showSelf', '0', '1', '1', '1414473258867', '1', '1414473323577');
INSERT INTO `oa_menu` VALUES ('8', '密码修改', '4', '', '_iframe', 'index/member/editPassword', '0', '1', '1', '1414473301898', '1', '1414473363515');
INSERT INTO `oa_menu` VALUES ('9', '通知公告', '4', '', '_iframe', 'index/notice/layout', '0', '1', '1', '1414473847340', '1', '1414473847340');
INSERT INTO `oa_menu` VALUES ('10', '用户管理', '5', '', '_iframe', 'index/member/layout', '0', '1', '1', '1414473880919', '1', '1414473880919');
INSERT INTO `oa_menu` VALUES ('11', '组织管理', '5', '', '_iframe', 'index/organize/layout', '0', '1', '1', '1414473895222', '1', '1414473895222');
INSERT INTO `oa_menu` VALUES ('12', '角色管理', '5', '', '_iframe', 'index/role/layout', '0', '1', '1', '1414473908483', '1', '1414473908483');
INSERT INTO `oa_menu` VALUES ('13', '配置参数', '6', '', '_iframe', 'index/setting/layout', '0', '1', '1', '1414473954180', '1', '1414473954180');
INSERT INTO `oa_menu` VALUES ('14', '环境状态', '6', '', '_iframe', 'index/server/info', '0', '1', '1', '1414473978182', '1', '1414473978182');
INSERT INTO `oa_menu` VALUES ('15', '备份还原', '6', '', '_iframe', 'index/database/layout', '0', '1', '1', '1414473996215', '1', '1414473996215');
INSERT INTO `oa_menu` VALUES ('16', '图标管理', '6', '', '_iframe', 'index/icon/layout', '0', '1', '1', '1414474015579', '1', '1414474015579');
INSERT INTO `oa_menu` VALUES ('17', '菜单管理', '6', '', '_iframe', 'index/menu/layout', '0', '1', '1', '1414474026735', '1', '1414474026735');
INSERT INTO `oa_menu` VALUES ('18', '上传文件', '6', '', '_iframe', 'index/upload/layout', '0', '1', '1', '1414474054106', '1', '1414474054106');
INSERT INTO `oa_menu` VALUES ('19', '日志管理', '6', '', '_iframe', 'index/log/layout', '0', '1', '1', '1414474069981', '1', '1414474069981');
INSERT INTO `oa_menu` VALUES ('20', '资源管理', '6', '', '_iframe', 'index/resource/layout', '0', '1', '1', '1414474167390', '1', '1414474167390');
INSERT INTO `oa_menu` VALUES ('21', '官方主页', '3', '', '_iframe', 'http://www.iisquare.com/', '0', '1', '1', '1414476952893', '1', '1414477049753');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice
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
-- Records of oa_organize
-- ----------------------------
INSERT INTO `oa_organize` VALUES ('1', '灰鸦社区', '0', '0', '1', '', '1', '1414474261923', '1', '1414474261923');
INSERT INTO `oa_organize` VALUES ('2', '爱好者', '1', '0', '1', '', '1', '1414474376538', '1', '1414474376538');
INSERT INTO `oa_organize` VALUES ('3', '精英汇', '1', '0', '1', '', '1', '1414474386308', '1', '1414474386308');
INSERT INTO `oa_organize` VALUES ('4', '开发者', '1', '0', '1', '', '1', '1414474397820', '1', '1414474397820');
INSERT INTO `oa_organize` VALUES ('5', '创业者', '1', '0', '1', '', '1', '1414474404000', '1', '1414474404000');
INSERT INTO `oa_organize` VALUES ('6', '研发组', '1', '0', '1', '', '1', '1414474411804', '1', '1414474411804');

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
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_resource
-- ----------------------------
INSERT INTO `oa_resource` VALUES ('1', '基础模块', '0', '0', '1', '0', 'index', '', '', '0', '0', '1', '1414399426157', '1', '1414399426157');
INSERT INTO `oa_resource` VALUES ('2', '数据库管理', '1', '0', '1', '0', 'index', 'database', '', '0', '0', '1', '1414400830252', '1', '1414400830252');
INSERT INTO `oa_resource` VALUES ('3', '操作面板', '2', '0', '1', '1', 'index', 'database', 'layout', '0', '0', '1', '1414400894225', '1', '1414400894225');
INSERT INTO `oa_resource` VALUES ('4', '信息列表', '2', '0', '0', '0', 'index', 'database', 'list', '0', '0', '1', '1414400944169', '1', '1414400944169');
INSERT INTO `oa_resource` VALUES ('5', '数据备份', '2', '0', '0', '0', 'index', 'database', 'backup', '0', '0', '1', '1414400966217', '1', '1414400966217');
INSERT INTO `oa_resource` VALUES ('6', '数据还原', '2', '0', '0', '0', 'index', 'database', 'revert', '0', '0', '1', '1414400983956', '1', '1414400983956');
INSERT INTO `oa_resource` VALUES ('7', '删除备份', '2', '0', '0', '0', 'index', 'database', 'delete', '0', '0', '1', '1414401003781', '1', '1414401003781');
INSERT INTO `oa_resource` VALUES ('8', '职务管理', '1', '0', '1', '0', 'index', 'duty', '', '0', '0', '1', '1414401485227', '1', '1414401485227');
INSERT INTO `oa_resource` VALUES ('9', '信息面板', '8', '0', '1', '1', 'index', 'duty', 'layout', '0', '0', '1', '1414401511673', '1', '1414401633197');
INSERT INTO `oa_resource` VALUES ('10', '信息列表', '8', '0', '0', '0', 'index', 'duty', 'list', '0', '0', '1', '1414401527117', '1', '1414401642141');
INSERT INTO `oa_resource` VALUES ('11', '信息查看', '8', '0', '0', '0', 'index', 'duty', 'show', '0', '0', '1', '1414401544398', '1', '1414401652724');
INSERT INTO `oa_resource` VALUES ('12', '信息编辑', '8', '0', '0', '0', 'index', 'duty', 'edit', '0', '0', '1', '1414401558454', '1', '1414401619629');
INSERT INTO `oa_resource` VALUES ('13', '信息保存', '8', '12', '0', '0', 'index', 'duty', 'save', '0', '0', '1', '1414401721807', '1', '1414401721807');
INSERT INTO `oa_resource` VALUES ('14', '信息删除', '8', '0', '0', '0', 'index', 'duty', 'delete', '0', '0', '1', '1414401780062', '1', '1414401780062');
INSERT INTO `oa_resource` VALUES ('15', '图标管理', '1', '0', '1', '0', 'index', 'icon', '', '0', '0', '1', '1414401979277', '1', '1414401979277');
INSERT INTO `oa_resource` VALUES ('16', '信息面板', '15', '0', '1', '1', 'index', 'icon', 'layout', '0', '0', '1', '1414402032626', '1', '1414402032626');
INSERT INTO `oa_resource` VALUES ('17', '信息列表', '15', '0', '0', '0', 'index', 'icon', 'list', '0', '0', '1', '1414402095471', '1', '1414402095471');
INSERT INTO `oa_resource` VALUES ('18', '信息查看', '15', '0', '0', '0', 'index', 'icon', 'show', '0', '0', '1', '1414402108019', '1', '1414402108019');
INSERT INTO `oa_resource` VALUES ('19', '信息编辑', '15', '0', '0', '0', 'index', 'icon', 'edit', '0', '0', '1', '1414402124092', '1', '1414402124092');
INSERT INTO `oa_resource` VALUES ('20', '信息保存', '15', '19', '0', '0', 'index', 'icon', 'save', '0', '0', '1', '1414402130741', '1', '1414402197958');
INSERT INTO `oa_resource` VALUES ('21', '信息删除', '15', '0', '0', '0', 'index', 'icon', 'delete', '0', '0', '1', '1414402142717', '1', '1414402142717');
INSERT INTO `oa_resource` VALUES ('22', '生成样式', '15', '0', '0', '0', 'index', 'icon', 'render', '0', '0', '1', '1414402164263', '1', '1414402164263');
INSERT INTO `oa_resource` VALUES ('23', '日志管理', '1', '0', '1', '0', 'index', 'log', '', '0', '0', '1', '1414402272986', '1', '1414402272986');
INSERT INTO `oa_resource` VALUES ('24', '信息面板', '23', '0', '1', '1', 'index', 'log', 'layout', '0', '0', '1', '1414402296886', '1', '1414402296886');
INSERT INTO `oa_resource` VALUES ('25', '信息列表', '23', '0', '0', '0', 'index', 'log', 'list', '0', '0', '1', '1414402322140', '1', '1414402322140');
INSERT INTO `oa_resource` VALUES ('26', '信息查看', '23', '0', '0', '0', 'index', 'log', 'show', '0', '0', '1', '1414402336315', '1', '1414402336315');
INSERT INTO `oa_resource` VALUES ('27', '信息删除', '23', '0', '0', '0', 'index', 'log', 'delete', '0', '0', '1', '1414402343369', '1', '1414402343369');
INSERT INTO `oa_resource` VALUES ('28', '清空日志', '23', '0', '0', '0', 'index', 'log', 'truncate', '0', '0', '1', '1414402362954', '1', '1414402362954');
INSERT INTO `oa_resource` VALUES ('29', '配置编辑', '23', '0', '0', '0', 'index', 'log', 'editSetting', '0', '0', '1', '1414402405746', '1', '1414402405746');
INSERT INTO `oa_resource` VALUES ('30', '配置保存', '23', '29', '0', '0', 'index', 'log', 'saveSetting', '0', '0', '1', '1414402433522', '1', '1414402433522');
INSERT INTO `oa_resource` VALUES ('31', '用户管理', '1', '0', '1', '0', 'index', 'member', '', '0', '0', '1', '1414402491921', '1', '1414402491921');
INSERT INTO `oa_resource` VALUES ('32', '个人信息查看', '31', '0', '1', '1', 'index', 'member', 'showSelf', '0', '0', '1', '1414402546818', '1', '1414402546818');
INSERT INTO `oa_resource` VALUES ('33', '个人信息编辑', '31', '0', '0', '0', 'index', 'member', 'editSelf', '0', '0', '1', '1414402562703', '1', '1414473532137');
INSERT INTO `oa_resource` VALUES ('34', '个人信息保存', '31', '33', '0', '0', 'index', 'member', 'saveSelf', '0', '0', '1', '1414402605681', '1', '1414402605681');
INSERT INTO `oa_resource` VALUES ('35', '密码修改', '31', '0', '1', '1', 'index', 'member', 'editPassword', '0', '0', '1', '1414402625112', '1', '1414402625112');
INSERT INTO `oa_resource` VALUES ('36', '密码保存', '31', '35', '0', '0', 'index', 'member', 'savePassword', '0', '0', '1', '1414402660355', '1', '1414402660355');
INSERT INTO `oa_resource` VALUES ('37', '信息面板', '31', '0', '1', '1', 'index', 'member', 'layout', '0', '0', '1', '1414402685905', '1', '1414402685905');
INSERT INTO `oa_resource` VALUES ('38', '信息列表', '31', '0', '0', '0', 'index', 'member', 'list', '0', '0', '1', '1414402697030', '1', '1414402697030');
INSERT INTO `oa_resource` VALUES ('39', '信息查看', '31', '0', '0', '0', 'index', 'member', 'show', '0', '0', '1', '1414402706006', '1', '1414402706006');
INSERT INTO `oa_resource` VALUES ('40', '信息编辑', '31', '0', '0', '0', 'index', 'member', 'edit', '0', '0', '1', '1414402713165', '1', '1414402713165');
INSERT INTO `oa_resource` VALUES ('41', '信息保存', '31', '40', '0', '0', 'index', 'member', 'save', '0', '0', '1', '1414402744183', '1', '1414402744183');
INSERT INTO `oa_resource` VALUES ('42', '信息删除', '31', '0', '0', '0', 'index', 'member', 'delete', '0', '0', '1', '1414403375177', '1', '1414403375177');
INSERT INTO `oa_resource` VALUES ('43', '用户登录', '31', '0', '0', '0', 'index', 'member', 'login', '0', '-1', '1', '1414403427001', '1', '1414403427001');
INSERT INTO `oa_resource` VALUES ('44', '登录操作', '31', '43', '0', '0', 'index', 'member', 'logon', '0', '-1', '1', '1414403482207', '1', '1414403546406');
INSERT INTO `oa_resource` VALUES ('45', '退出登录', '31', '0', '1', '1', 'index', 'member', 'logout', '0', '-1', '1', '1414403504054', '1', '1414473548347');
INSERT INTO `oa_resource` VALUES ('46', '管理面板', '31', '0', '0', '0', 'index', 'member', 'platform', '0', '0', '1', '1414403576300', '1', '1414473555796');
INSERT INTO `oa_resource` VALUES ('47', '菜单管理', '1', '0', '1', '0', 'index', 'menu', '', '0', '0', '1', '1414403891391', '1', '1414403891391');
INSERT INTO `oa_resource` VALUES ('48', '信息面板', '47', '0', '1', '1', 'index', 'menu', 'layout', '0', '0', '1', '1414404306214', '1', '1414404306214');
INSERT INTO `oa_resource` VALUES ('49', '信息列表', '47', '0', '0', '0', 'index', 'menu', 'list', '0', '0', '1', '1414404314895', '1', '1414404314895');
INSERT INTO `oa_resource` VALUES ('50', '信息查看', '47', '0', '0', '0', 'index', 'menu', 'show', '0', '0', '1', '1414404329289', '1', '1414404329289');
INSERT INTO `oa_resource` VALUES ('51', '信息编辑', '47', '0', '0', '0', 'index', 'menu', 'edit', '0', '0', '1', '1414404336445', '1', '1414404336445');
INSERT INTO `oa_resource` VALUES ('52', '信息保存', '47', '51', '0', '0', 'index', 'menu', 'save', '0', '0', '1', '1414404365813', '1', '1414404365813');
INSERT INTO `oa_resource` VALUES ('53', '信息删除', '47', '0', '0', '0', 'index', 'menu', 'delete', '0', '0', '1', '1414404376789', '1', '1414404376789');
INSERT INTO `oa_resource` VALUES ('54', '个人菜单', '47', '0', '0', '0', 'index', 'menu', 'listSelf', '0', '0', '1', '1414404413958', '1', '1414404413958');
INSERT INTO `oa_resource` VALUES ('55', '通知公告管理', '1', '0', '1', '0', 'index', 'notice', '', '0', '0', '1', '1414404457696', '1', '1414404457696');
INSERT INTO `oa_resource` VALUES ('56', '信息面板', '55', '0', '1', '1', 'index', 'notice', 'layout', '0', '0', '1', '1414458698435', '1', '1414458698435');
INSERT INTO `oa_resource` VALUES ('57', '信息列表', '55', '0', '0', '0', 'index', 'notice', 'list', '0', '0', '1', '1414458711121', '1', '1414458711121');
INSERT INTO `oa_resource` VALUES ('58', '信息编辑', '55', '0', '0', '0', 'index', 'notice', 'edit', '0', '0', '1', '1414458719499', '1', '1414458719499');
INSERT INTO `oa_resource` VALUES ('59', '信息保存', '55', '58', '0', '0', 'index', 'notice', 'save', '0', '0', '1', '1414458764845', '1', '1414458764845');
INSERT INTO `oa_resource` VALUES ('60', '信息查看', '55', '0', '0', '0', 'index', 'notice', 'show', '0', '0', '1', '1414458774570', '1', '1414458774570');
INSERT INTO `oa_resource` VALUES ('61', '信息删除', '55', '0', '0', '0', 'index', 'notice', 'delete', '0', '0', '1', '1414458782864', '1', '1414458782864');
INSERT INTO `oa_resource` VALUES ('62', '通知公告类型管理', '1', '0', '1', '0', 'index', 'noticeType', '', '0', '0', '1', '1414458831505', '1', '1414458866683');
INSERT INTO `oa_resource` VALUES ('63', '信息面板', '62', '0', '1', '1', 'index', 'noticeType', 'layout', '0', '0', '1', '1414458956167', '1', '1414458956167');
INSERT INTO `oa_resource` VALUES ('64', '信息列表', '62', '0', '0', '0', 'index', 'noticeType', 'list', '0', '0', '1', '1414458969335', '1', '1414458969335');
INSERT INTO `oa_resource` VALUES ('65', '信息查看', '62', '0', '0', '0', 'index', 'noticeType', 'show', '0', '0', '1', '1414458977059', '1', '1414458977059');
INSERT INTO `oa_resource` VALUES ('66', '信息编辑', '62', '0', '0', '0', 'index', 'noticeType', 'edit', '0', '0', '1', '1414458985130', '1', '1414458985130');
INSERT INTO `oa_resource` VALUES ('67', '信息保存', '62', '66', '0', '0', 'index', 'noticeType', 'save', '0', '0', '1', '1414459034444', '1', '1414459034444');
INSERT INTO `oa_resource` VALUES ('68', '信息删除', '62', '0', '0', '0', 'index', 'noticeType', 'delete', '0', '0', '1', '1414459045022', '1', '1414459045022');
INSERT INTO `oa_resource` VALUES ('69', '组织机构管理', '1', '0', '1', '0', 'index', 'organize', '', '0', '0', '1', '1414459082278', '1', '1414459082278');
INSERT INTO `oa_resource` VALUES ('70', '信息面板', '69', '0', '1', '1', 'index', 'organize', 'layout', '0', '0', '1', '1414459163148', '1', '1414459163148');
INSERT INTO `oa_resource` VALUES ('71', '信息列表', '69', '0', '0', '0', 'index', 'organize', 'list', '0', '0', '1', '1414459173404', '1', '1414459173404');
INSERT INTO `oa_resource` VALUES ('72', '信息查看', '69', '0', '0', '0', 'index', 'organize', 'show', '0', '0', '1', '1414459180245', '1', '1414459180245');
INSERT INTO `oa_resource` VALUES ('73', '信息编辑', '69', '0', '0', '0', 'index', 'organize', 'edit', '0', '0', '1', '1414459197236', '1', '1414459197236');
INSERT INTO `oa_resource` VALUES ('74', '信息保存', '69', '73', '0', '0', 'index', 'organize', 'save', '0', '0', '1', '1414459205962', '1', '1414459327553');
INSERT INTO `oa_resource` VALUES ('75', '信息删除', '69', '0', '0', '0', 'index', 'organize', 'delete', '0', '0', '1', '1414459220682', '1', '1414459220682');
INSERT INTO `oa_resource` VALUES ('76', '资源管理', '1', '0', '1', '0', 'index', 'resource', '', '0', '0', '1', '1414459522076', '1', '1414459522076');
INSERT INTO `oa_resource` VALUES ('77', '信息面板', '76', '0', '1', '1', 'index', 'resource', 'layout', '0', '0', '1', '1414459567488', '1', '1414459567488');
INSERT INTO `oa_resource` VALUES ('78', '信息列表', '76', '0', '0', '0', 'index', 'resource', 'list', '0', '0', '1', '1414459579421', '1', '1414459579421');
INSERT INTO `oa_resource` VALUES ('79', '信息查看', '76', '0', '0', '0', 'index', 'resource', 'show', '0', '0', '1', '1414459586669', '1', '1414459586669');
INSERT INTO `oa_resource` VALUES ('80', '信息编辑', '76', '0', '0', '0', 'index', 'resource', 'edit', '0', '0', '1', '1414459594366', '1', '1414459594366');
INSERT INTO `oa_resource` VALUES ('81', '信息保存', '76', '80', '0', '0', 'index', 'resource', 'save', '0', '0', '1', '1414459640744', '1', '1414459640744');
INSERT INTO `oa_resource` VALUES ('82', '信息删除', '76', '0', '0', '0', 'index', 'resource', 'delete', '0', '0', '1', '1414459653125', '1', '1414459653125');
INSERT INTO `oa_resource` VALUES ('83', '角色管理', '1', '0', '1', '0', 'index', 'role', '', '0', '0', '1', '1414459694481', '1', '1414459694481');
INSERT INTO `oa_resource` VALUES ('84', '信息面板', '83', '0', '1', '1', 'index', 'role', 'layout', '0', '0', '1', '1414459717821', '1', '1414459717821');
INSERT INTO `oa_resource` VALUES ('85', '信息列表', '83', '0', '0', '0', 'index', 'role', 'list', '0', '0', '1', '1414459726982', '1', '1414459726982');
INSERT INTO `oa_resource` VALUES ('86', '信息查看', '83', '0', '0', '0', 'index', 'role', 'show', '0', '0', '1', '1414459733953', '1', '1414459733953');
INSERT INTO `oa_resource` VALUES ('87', '信息编辑', '83', '0', '0', '0', 'index', 'role', 'edit', '0', '0', '1', '1414459745612', '1', '1414459745612');
INSERT INTO `oa_resource` VALUES ('88', '信息保存', '83', '87', '0', '0', 'index', 'role', 'save', '0', '0', '1', '1414459804648', '1', '1414459804648');
INSERT INTO `oa_resource` VALUES ('89', '信息删除', '83', '0', '0', '0', 'index', 'role', 'delete', '0', '0', '1', '1414459817120', '1', '1414459817120');
INSERT INTO `oa_resource` VALUES ('90', '权限菜单编辑', '83', '0', '0', '0', 'index', 'role', 'editPower', '0', '0', '1', '1414459831534', '1', '1414459831534');
INSERT INTO `oa_resource` VALUES ('91', '权限菜单保存', '83', '90', '0', '0', 'index', 'role', 'savePower', '0', '0', '1', '1414459850875', '1', '1414459850875');
INSERT INTO `oa_resource` VALUES ('92', '服务器信息管理', '1', '0', '1', '0', 'index', 'server', '', '0', '0', '1', '1414459880216', '1', '1414459880216');
INSERT INTO `oa_resource` VALUES ('93', '环境状态', '92', '0', '1', '1', 'index', 'server', 'info', '0', '0', '1', '1414460037057', '1', '1414460037057');
INSERT INTO `oa_resource` VALUES ('94', '配置管理', '1', '0', '1', '0', 'index', 'setting', '', '0', '0', '1', '1414460066587', '1', '1414460066587');
INSERT INTO `oa_resource` VALUES ('95', '信息面板', '94', '0', '1', '1', 'index', 'setting', 'layout', '0', '0', '1', '1414460091787', '1', '1414460091787');
INSERT INTO `oa_resource` VALUES ('96', '信息列表', '94', '0', '0', '0', 'index', 'setting', 'list', '0', '0', '1', '1414460100457', '1', '1414460100457');
INSERT INTO `oa_resource` VALUES ('97', '信息查看', '94', '0', '0', '0', 'index', 'setting', 'show', '0', '0', '1', '1414460108513', '1', '1414460108513');
INSERT INTO `oa_resource` VALUES ('98', '信息编辑', '94', '0', '0', '0', 'index', 'setting', 'eidt', '0', '0', '1', '1414460118136', '1', '1414460118136');
INSERT INTO `oa_resource` VALUES ('99', '信息保存', '94', '98', '0', '0', 'index', 'setting', 'save', '0', '0', '1', '1414460130225', '1', '1414460130225');
INSERT INTO `oa_resource` VALUES ('100', '信息删除', '94', '0', '0', '0', 'index', 'setting', 'delete', '0', '0', '1', '1414460141657', '1', '1414460141657');
INSERT INTO `oa_resource` VALUES ('101', '上传文件管理', '1', '0', '1', '0', 'index', 'upload', '', '0', '0', '1', '1414460183474', '1', '1414460183474');
INSERT INTO `oa_resource` VALUES ('102', '信息面板', '101', '0', '1', '1', 'index', 'upload', 'layout', '0', '0', '1', '1414460205524', '1', '1414460205524');
INSERT INTO `oa_resource` VALUES ('103', '信息列表', '101', '0', '0', '0', 'index', 'upload', 'list', '0', '0', '1', '1414460215464', '1', '1414460215464');
INSERT INTO `oa_resource` VALUES ('104', '信息删除', '101', '0', '0', '0', 'index', 'upload', 'delete', '0', '0', '1', '1414460231081', '1', '1414460231081');
INSERT INTO `oa_resource` VALUES ('105', '允许上传', '101', '0', '0', '0', 'index', 'upload', 'uploadJson', '0', '0', '1', '1414460281712', '1', '1414460281712');
INSERT INTO `oa_resource` VALUES ('106', '允许浏览', '101', '0', '0', '0', 'index', 'upload', 'fileManagerJson', '0', '0', '1', '1414460315927', '1', '1414460315927');
INSERT INTO `oa_resource` VALUES ('107', '编辑器示例', '101', '0', '1', '1', 'index', 'upload', 'editor', '0', '0', '1', '1414460341793', '1', '1414460341793');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('1', '系统管理员', '0', '0', '1', '', '1', '1414475359109', '1', '1414475359109');
INSERT INTO `oa_role` VALUES ('2', '超级管理员', '1', '0', '1', '', '1', '1414475820669', '1', '1414475820669');
INSERT INTO `oa_role` VALUES ('3', '普通管理员', '1', '0', '1', '', '1', '1414475827975', '1', '1414475827975');
INSERT INTO `oa_role` VALUES ('4', '普通用户组', '0', '0', '1', '', '1', '1414475854203', '1', '1414475854203');
INSERT INTO `oa_role` VALUES ('5', '游客', '4', '0', '1', '', '1', '1414475889347', '1', '1414475889347');

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
INSERT INTO `oa_role_menu_rel` VALUES ('2', '2');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '3');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '4');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '5');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '6');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '7');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '8');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '9');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '10');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '11');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '12');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '13');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '14');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '15');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '16');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '17');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '18');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '19');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '20');
INSERT INTO `oa_role_menu_rel` VALUES ('2', '21');

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
INSERT INTO `oa_role_resource_rel` VALUES ('2', '1');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '2');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '3');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '4');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '5');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '6');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '7');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '8');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '9');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '10');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '11');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '12');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '14');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '15');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '16');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '17');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '18');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '19');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '21');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '22');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '23');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '24');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '25');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '26');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '27');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '28');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '29');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '31');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '32');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '33');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '35');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '37');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '38');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '39');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '40');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '42');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '43');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '45');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '46');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '47');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '48');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '49');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '50');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '51');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '53');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '54');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '55');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '56');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '57');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '58');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '60');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '61');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '62');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '63');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '64');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '65');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '66');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '68');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '69');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '70');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '71');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '72');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '73');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '75');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '76');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '77');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '78');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '79');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '80');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '82');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '83');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '84');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '85');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '86');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '87');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '89');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '90');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '92');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '93');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '94');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '95');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '96');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '97');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '98');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '100');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '101');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '102');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '103');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '104');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '105');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '106');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '107');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_upload
-- ----------------------------
