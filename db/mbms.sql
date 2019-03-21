/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.109
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 192.168.0.109:4000
 Source Schema         : mbms

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 20/03/2019 19:11:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mbms_company
-- ----------------------------
DROP TABLE IF EXISTS `mbms_company`;
CREATE TABLE `mbms_company`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mbms_material
-- ----------------------------
DROP TABLE IF EXISTS `mbms_material`;
CREATE TABLE `mbms_material`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_id` int(11) NOT NULL DEFAULT 0,
  `smtype_id` int(11) NOT NULL DEFAULT 0,
  `size` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `smstyle_id` int(11) NOT NULL DEFAULT 0,
  `product_theme` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `download` int(11) NOT NULL DEFAULT 0,
  `consume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `starlevel` int(11) NOT NULL DEFAULT 0,
  `media_id` int(11) NOT NULL DEFAULT 0,
  `platform` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `like` int(11) NOT NULL DEFAULT 0,
  `browse` int(11) NOT NULL DEFAULT 0,
  `entrytime` timestamp NULL DEFAULT NULL,
  `materialpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isdelete` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mbms_media
-- ----------------------------
DROP TABLE IF EXISTS `mbms_media`;
CREATE TABLE `mbms_media`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `m_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mbms_smstyle
-- ----------------------------
DROP TABLE IF EXISTS `mbms_smstyle`;
CREATE TABLE `mbms_smstyle`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mbms_smtype
-- ----------------------------
DROP TABLE IF EXISTS `mbms_smtype`;
CREATE TABLE `mbms_smtype`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_dept
-- ----------------------------
DROP TABLE IF EXISTS `user_dept`;
CREATE TABLE `user_dept`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sina` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(10) UNSIGNED NULL DEFAULT 2,
  `dept_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `lastlogintime` timestamp NULL DEFAULT NULL,
  `head_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'hetaolinmedia.png',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
