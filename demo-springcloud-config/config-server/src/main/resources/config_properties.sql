/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : test_database

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 27/04/2020 17:53:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_properties
-- ----------------------------
DROP TABLE IF EXISTS `config_properties`;
CREATE TABLE `config_properties`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'key',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '值',
  `application` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用名',
  `profile` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '激活的文件',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户ID预留字段',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_properties
-- ----------------------------
INSERT INTO `config_properties` VALUES (1, 'server.port', '8083', 'config-client-demo', 'dev', 'master', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_properties` VALUES (2, 'foo', 'bar-jdbc', 'config-client-demo', 'dev', 'master', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
