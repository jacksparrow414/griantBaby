/*
 Navicat Premium Data Transfer

 Source Server         : mysql3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : baby

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 14/03/2020 21:15:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `age` int(32) NOT NULL COMMENT '机构ID',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录邮箱',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1238704634990817283 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1238704634990817282, 'admin', 24, '2990151812@qq.com', 'c05f2abfabebc47c4ecf3476444e28d2ef57c65f0657c1519412f08235c3aa7a', 'QUOheNLeASTTIgpjzvdo');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
