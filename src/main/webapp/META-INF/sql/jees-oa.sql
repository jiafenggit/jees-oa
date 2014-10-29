/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-oa

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-10-29 11:08:47
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_icon
-- ----------------------------
INSERT INTO `oa_icon` VALUES ('1', '系统图标', '0', 'api/jQuery/themes/icons/ok.png', '0', '1', '1', '1414550792111', '1', '1414551076574');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_log
-- ----------------------------
INSERT INTO `oa_log` VALUES ('1', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414490468022');
INSERT INTO `oa_log` VALUES ('2', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414545029912');
INSERT INTO `oa_log` VALUES ('3', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414545168109');
INSERT INTO `oa_log` VALUES ('4', '用户登陆', 'service', 'index', 'member', 'guest', null, null, null, null, null, null, null, null, '2', '127.0.0.1', '1414546950839');
INSERT INTO `oa_log` VALUES ('5', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414549189833');
INSERT INTO `oa_log` VALUES ('6', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414549226782');
INSERT INTO `oa_log` VALUES ('7', '用户登陆', 'service', 'index', 'member', 'guest', null, null, null, null, null, null, null, null, '2', '127.0.0.1', '1414549237147');
INSERT INTO `oa_log` VALUES ('8', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '2', '127.0.0.1', '1414549407194');
INSERT INTO `oa_log` VALUES ('9', '用户登陆', 'service', 'index', 'member', 'guest', null, null, null, null, null, null, null, null, '2', '127.0.0.1', '1414549503629');
INSERT INTO `oa_log` VALUES ('10', '用户登陆', 'service', 'index', 'member', 'logon', null, null, null, null, null, null, null, null, '1', '127.0.0.1', '1414550599929');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_member
-- ----------------------------
INSERT INTO `oa_member` VALUES ('1', 'admin', '管理员', '0f4e8ac95b5c1fc48dc98004c7525bc7', '888888', '0', '1', '1414551642712', '127.0.0.1', '1', '127.0.0.1', '1411090286300', '1', '1414476075652');
INSERT INTO `oa_member` VALUES ('2', 'guest', '访客', '793a507822c9ec991c3418dfb7950f07', '338043', '0', '1', '1414550595011', '127.0.0.1', '1', '127.0.0.1', '1414546536140', '1', '1414546536140');

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
INSERT INTO `oa_member_organize_rel` VALUES ('2', '2', '4');

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
INSERT INTO `oa_member_role_rel` VALUES ('2', '5');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_notice
-- ----------------------------
INSERT INTO `oa_notice` VALUES ('1', '系统管理子系统', '3', '<p class=\"MsoNormal\" style=\"text-indent:21.1pt;\">\r\n	<b>2.2.2</b><b> </b><b>系统管理子系统</b><b></b>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	系统管理子系统是业务管理系统的系统初始化的管理平台，通过此系统，系统管理员可以定制操作界面、按级按角色授权、提高系统安全性，对系统参数及日志进行维护等。<a name=\"_Toc293411153\"></a><span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	（<span>1</span>）流程的定制和修改模块。系统提供行政办公、执法业务管理流程的自定义功能。用户可以根据自身的需要因应业务操作的变动或优化，自行对系统中相应的办公、执法流程的进行定制和修改。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	<a name=\"_Toc293411154\"></a>（<span>2</span>）组织架构管理模块。用于对层次性的组织机构进行管理，可以用树状结构表示各职能部门的人事结构及从属关系、支持基于角色的代理人功能，支持目录服务<span>LDAP</span>的人员资料信息同步。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	部门管理用于维护用户所属单位、部门的组织结构，按照政府机关的实际组织结构来划分，并维护到数据库中，目的是对机构、用户进行统一管理。系统可实现部门的可视化的定义管理，包含以下内容：<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  部门设置，基本信息定义、修改、删除等操作。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  部门分级、排序、检索。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  部门与执法事项、流程环节、办事人员等关联。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	每个用户都属于某个特定的机构，当机构改变，如合并、撤销时，相应用户也要处理。当撤销时，该机构作删除处理，相应用户也作删除处理，当合并时，先做撤销处理，然后在另一个机构中添加用户。注意，这里的删除只是作删除标记，并不真正在数据库里删除。另外，在组织机构管理中也可以对部门或者用户赋予相应的应用权限和菜单权限。同时，组织机构管理支持符合<span>LDAP</span>命名格式的组织单元模型，可以依托目录服务中的组织结构，可以将目录服务中的组织机构和用户数据作为系统组织机构的基础数据，并无缝衔接到其它应用中。同时，通过实现和目录服务的数据捆绑，使得整个系统可以实现单点登录模式。<a name=\"_Toc293411155\"></a><span>  </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	（<span>3</span>）用户管理模块。用户管理模块提供用户资料方面的管理功能，主要有：<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户基本资料；用户基本资料用于对系统中的用户进行管理，注册用户，管理用户的基本信息及<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	删除用户的基本管理功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户权限：给用户赋予相应登寻权限（选择一个用户组权限）以确保系统安全、可靠、有效地进<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	行，防止用户非法操作系统功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户密码修改：当用户忘记密码或因特殊需要必须更改先前设置的密码，并且用户拥有足够权限<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	时，系统才允许用户操作此功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	<a name=\"_Toc293411156\"></a>（<span>4</span>）<a name=\"_Toc293411158\"></a>权限设置模块。权限管理用于管理系统的权限信息，可根据业务和管理要求分配不同的控制管理权限给指定用户或者角色。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  对应用系统的所有资源进行权限控制，系统根据用户的权限对工作窗口进行初始化，为不同角<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	色的用户定制不同的工作窗口界面和功能菜单。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  无限级功能模块管理，自定义模块排序，可以对整个系统中所有模块进行分类管理。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  自定义操作动作<span>(</span>如增加、删除、修改、查询、审核等动作<span>)</span>。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  灵活地为各模块分配操作，即每个模块有哪些操作需要被控制。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  对所有用户基础信息进行管理，实行有效期机制，过期自动失效，在有效期间亦可强制停止用<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	户使用。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  自定义角色，任意角色可任意组合系统权限功能点。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  按角色给用户授权，按用户分配角色，一个用户可有多个角色<span>(</span>多身份<span>)</span>，一个角色也可以被多<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	个用户拥有<span>(</span>同身份<span>)</span>（多对多），灵活授权。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  组织管理，与单位的部门或者机构对应，用于实现对用户和权限的分组归类管理。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:45.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户及角色分级管理，下级用户只能拥有上级用户权限的子集，可无限分级。\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	（<span>5</span>）数据维护模块。数据维护是赋予系统管理员的一种特殊权限，管理员在特定的情况下，可以对数据库进行维护操作，包括制订数据备份计划、数据结构的修改、业务流程的修改等，这类操作必须具备严格的限度控制，必须保留全部修改日志，便于监督追踪。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	<a name=\"_Toc293411157\"></a>（<span>6</span>）日志管理模块。包括用户日志和系统日志。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	用户日志管理主要是系统自动记录所有进入系统用户的信息资料，包括记录操作用户的编号、姓名、进入时间、操作电脑<span>IP</span>等。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:24.0pt;\">\r\n	系统操作日志管理主要是系统自动管理进行过业务处理的日志信息，主要记录用户编号、姓名、进入时间、业务模块操作动作（增加、删除、修改、查询、下载、上传等），授权用户也可以进看系统日志，并可以对日志及统计进行分析、研究。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:26.25pt;\">\r\n	<a name=\"_Toc293411160\"></a>（<span>7</span>）消息管理模块。消息中心是系统内用户互发和收取信息的消息交换枢纽中心，是用户在系统内交流的平台。通过定制的方式实现短信、邮件、文件、文字等信息载体的传送，也可为客户定制向移动设备发送短信功能。主要功能有短信消息管理、论坛、通知公告和系统信息。同时消息中心提供窗口人员评测功能，通过对评测主题的管理，可以让内部人员互相评测，然后在对这些评测数据进行统计、分析和展示。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.1pt;\">\r\n	<b>①手机短信。</b>系统可动态配置短信接口，实现与短信系统的对接，通过短信平台传递立案信息、执法环节信息、结果信息、预警提示等信息，及时通过短信方式反馈给业务人员和相关用户。并可保存信息日志，供管理人员调阅。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  短信系统可与现有软件平台各应用系统、网站和其他第三方系统无缝对接。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  系统支持中国移动<span>CMPP</span>、中国联通<span>SGIP</span>、中国电信<span>SM</span>Ｇ<span>P</span>等多种短信接入协议，后台守护进程<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	方式工作，支持<span>HTTP</span>、<span>WebService</span>和数据共享三种接口方式。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  短信提醒功能。重要信息、事务提醒，节假日祝福，预设日程通知，系统群发与自动回复。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  短信自动发送，支持号段、群组群发，实时或定时发送。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  通讯录分组管理，支持文件导入联系人功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  支持超长短信发送和短信自动分割功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  支持自动回复功能，可定制短信自动回复代码及内容，并支持动态回复扩展功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  支持按角色分配用户权限，支持对短信发送的灵活权限控制，支持短信发送审核管理。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  系统提供强大的查询统计及分析功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  短信处理能力双向最高可达<span>300</span>条<span>/</span>秒，忙时不低于<span>100</span>条<span>/</span>秒，消息丢失率小于<span>0.01%</span>，平均运<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	行无故障时间大于<span>99.6%</span>。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.1pt;\">\r\n	<b>②论坛<span></span></b>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	论坛系统是一个信息交流的平台，为广大用户提供分享经验、探讨问题的网上社区。系统提供会员注册、发表及回复帖子、浏览帖子等前台功能<span>,</span>同时也为论坛的管理人员提供对应后台的管理功能，包括会员管理、论坛版块管理、帖子管理等功能。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  注册管理：能够对用户名，密码的简单验证，能够防止利用页面刷新重复注册，已经存在的用户<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	名称不能重复注册。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  浏览帖子：可以根据作者或内容等为关键字搜索帖子，分论坛版块显示帖子，显示帖子详情，浏<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	览帖子时不要求用户登录，但回复帖子前用户必须登录。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  发表及回复帖子：只有登录用户才可以发表和回复帖子，并且对帖子的字数有限制。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户个人信息管理：登录用户可以编辑注册后的个人资料，如修改密码等，但是不可以修改用户<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	名称。也可以对自己发表的帖子进行操作：如修改，删除。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  回复管理：各论坛版块的版主可以对自己管理的版块的帖子的回复的帖子进行管理，可以屏蔽回<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	复。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  论坛版块管理：只有管理员有该权限，管理员可以添加，删除版块，也可以修改版块资料，如版<span></span>\r\n</p>\r\n<p class=\"MsoNormal\">\r\n	块名称、上级版块、版主等。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  用户管理：只有管理员有该权限，管理员可以禁用，启用用户，进行用户密码重置。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  公告管理：只有管理员有该权限，管理员可以添加，删除公告。<span></span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"margin-left:42.0pt;text-indent:-21.0pt;\">\r\n	Ø  帖子管理：各论坛版块的版主可以对自己管理的版块的帖子进行管理，可以屏蔽和删除帖子。<span> </span>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.1pt;\">\r\n	<b>③消息公告<span></span></b>\r\n</p>\r\n<p class=\"MsoNormal\" style=\"text-indent:21.0pt;\">\r\n	消息公告，即消息公告的传达处理系统，目的是为了让用户获得需要得到的消息（通知公告、待办业务、活动安排等）及提醒并进行处理，代替日常普通纸质文件的下发和处理，提高工作效率，节省时间和资源。系统提供网页在线图文编辑、表格处理、附件上传等功能，并提供对消息公告分类管理的功能。用户登录系统时，如果有新的消息公告，会弹出一个提示框，显示系统消息条数，点击后可以查看消息公告内容。<span></span>\r\n</p>', '2147483647', '1', '1', '1414487118605', '1', '1414487118605');

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
INSERT INTO `oa_notice_type` VALUES ('1', '内部通知', '0', '0', '1', '1', '1414486602560', '1', '1414486602560');
INSERT INTO `oa_notice_type` VALUES ('2', '紧急通知', '1', '0', '1', '1', '1414486632933', '1', '1414486632933');
INSERT INTO `oa_notice_type` VALUES ('3', '日常文件', '1', '0', '1', '1', '1414486646337', '1', '1414486646337');

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
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

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
INSERT INTO `oa_resource` VALUES ('22', '生成样式', '15', '0', '0', '0', 'index', 'icon', 'renderCss', '0', '0', '1', '1414402164263', '1', '1414551163891');
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
INSERT INTO `oa_resource` VALUES ('98', '信息编辑', '94', '0', '0', '0', 'index', 'setting', 'edit', '0', '0', '1', '1414460118136', '1', '1414546404335');
INSERT INTO `oa_resource` VALUES ('99', '信息保存', '94', '98', '0', '0', 'index', 'setting', 'save', '0', '0', '1', '1414460130225', '1', '1414460130225');
INSERT INTO `oa_resource` VALUES ('100', '信息删除', '94', '0', '0', '0', 'index', 'setting', 'delete', '0', '0', '1', '1414460141657', '1', '1414460141657');
INSERT INTO `oa_resource` VALUES ('101', '上传文件管理', '1', '0', '1', '0', 'index', 'upload', '', '0', '0', '1', '1414460183474', '1', '1414460183474');
INSERT INTO `oa_resource` VALUES ('102', '信息面板', '101', '0', '1', '1', 'index', 'upload', 'layout', '0', '0', '1', '1414460205524', '1', '1414460205524');
INSERT INTO `oa_resource` VALUES ('103', '信息列表', '101', '0', '0', '0', 'index', 'upload', 'list', '0', '0', '1', '1414460215464', '1', '1414460215464');
INSERT INTO `oa_resource` VALUES ('104', '信息删除', '101', '0', '0', '0', 'index', 'upload', 'delete', '0', '0', '1', '1414460231081', '1', '1414460231081');
INSERT INTO `oa_resource` VALUES ('105', '允许上传', '101', '0', '0', '0', 'index', 'upload', 'uploadJson', '0', '0', '1', '1414460281712', '1', '1414460281712');
INSERT INTO `oa_resource` VALUES ('106', '允许浏览', '101', '0', '0', '0', 'index', 'upload', 'fileManagerJson', '0', '0', '1', '1414460315927', '1', '1414460315927');
INSERT INTO `oa_resource` VALUES ('107', '编辑器示例', '101', '0', '1', '1', 'index', 'upload', 'editor', '0', '0', '1', '1414460341793', '1', '1414460341793');
INSERT INTO `oa_resource` VALUES ('108', '管理首页', '1', '0', '0', '0', 'index', 'index', '', '0', '0', '1', '1414482115257', '1', '1414482192308');
INSERT INTO `oa_resource` VALUES ('109', '任务面板', '108', '0', '0', '0', 'index', 'index', 'task', '0', '0', '1', '1414482176022', '1', '1414482176022');
INSERT INTO `oa_resource` VALUES ('110', '登陆日志', '23', '46', '0', '0', 'index', 'log', 'listLogon', '0', '0', '1', '1414489656476', '1', '1414489656476');
INSERT INTO `oa_resource` VALUES ('111', '首页列表', '55', '46', '0', '0', 'index', 'notice', 'listIndex', '0', '0', '1', '1414489700812', '1', '1414489700812');
INSERT INTO `oa_resource` VALUES ('112', '信息阅读', '55', '46', '0', '0', 'index', 'notice', 'read', '0', '0', '1', '1414489714899', '1', '1414489714899');
INSERT INTO `oa_resource` VALUES ('113', '访客模式', '31', '0', '1', '1', 'index', 'member', 'guest', '0', '-1', '1', '1414546177437', '1', '1414546177437');

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
INSERT INTO `oa_role_menu_rel` VALUES ('5', '2');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '3');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '4');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '5');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '6');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '7');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '8');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '9');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '10');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '11');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '12');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '13');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '14');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '15');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '16');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '17');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '18');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '19');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '20');
INSERT INTO `oa_role_menu_rel` VALUES ('5', '21');

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
INSERT INTO `oa_role_resource_rel` VALUES ('2', '108');
INSERT INTO `oa_role_resource_rel` VALUES ('2', '109');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '1');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '2');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '3');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '4');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '8');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '9');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '10');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '11');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '15');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '16');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '17');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '18');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '23');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '24');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '25');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '26');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '31');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '32');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '35');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '37');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '38');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '39');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '43');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '45');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '46');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '47');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '48');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '49');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '50');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '54');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '55');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '56');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '57');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '60');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '62');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '63');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '64');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '65');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '69');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '70');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '71');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '72');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '76');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '77');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '78');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '79');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '83');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '84');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '85');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '86');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '92');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '93');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '94');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '95');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '96');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '97');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '101');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '102');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '103');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '106');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '107');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '108');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '109');
INSERT INTO `oa_role_resource_rel` VALUES ('5', '113');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_setting
-- ----------------------------
INSERT INTO `oa_setting` VALUES ('1', 'webName', 'system', '在线办公管理系统', '系统名称', '1', '127.0.0.1', '1413018957263');
INSERT INTO `oa_setting` VALUES ('2', 'pageSize', 'system', '15', '分页大小', '1', '127.0.0.1', '1413018859640');
INSERT INTO `oa_setting` VALUES ('3', 'guestSerial', 'system', 'guest', '访客模式账号', '1', '127.0.0.1', '1414549233193');

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
