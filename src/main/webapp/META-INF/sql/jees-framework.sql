/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : jees-framework

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-08-13 09:22:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jees_test`
-- ----------------------------
DROP TABLE IF EXISTS `jees_test`;
CREATE TABLE `jees_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL DEFAULT '',
  `content` text NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `time_create` bigint(20) NOT NULL DEFAULT '0',
  `time_update` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jees_test
-- ----------------------------
