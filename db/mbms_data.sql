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

 Date: 21/03/2019 13:16:16
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mbms_company
-- ----------------------------
INSERT INTO `mbms_company` VALUES (2, '灰狐科技');
INSERT INTO `mbms_company` VALUES (3, '讯通科技');
INSERT INTO `mbms_company` VALUES (4, '容艺传媒');
INSERT INTO `mbms_company` VALUES (5, '景天科技');
INSERT INTO `mbms_company` VALUES (6, '狐影传媒');
INSERT INTO `mbms_company` VALUES (7, '迅通科技');

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mbms_material
-- ----------------------------
INSERT INTO `mbms_material` VALUES (1, 'AI-科技-EA', 2, 1, '800*551', 1, '科技图片', 0, '后台数据', 5, 2, 'Android', 0, 0, '2019-03-21 04:16:52', '201903211216521553141812291529f86.jpg', 0);
INSERT INTO `mbms_material` VALUES (2, 'AI-科技-EG', 3, 1, '800*462', 1, '科技图片', 0, '后台数据', 3, 2, 'Android', 0, 0, '2019-03-21 04:20:08', '20190321122008155314200812742a664.jpg', 0);
INSERT INTO `mbms_material` VALUES (3, '仰望星空', 2, 1, '900*506', 5, '简约壁纸', 0, '后台数据', 5, 1, 'IOS', 0, 0, '2019-03-21 04:22:19', '201903211222191553142139031e90328.jpg', 0);
INSERT INTO `mbms_material` VALUES (4, '容艺传媒', 4, 1, '850*538', 4, '应用素材', 0, '后台数据', 3, 1, 'IOS', 0, 0, '2019-03-21 04:25:13', '2019032112251315531423138853b466b.png', 0);
INSERT INTO `mbms_material` VALUES (5, 'Color.Fado', 2, 1, '1280*747', 5, 'Color.Fado', 0, '后台数据', 4, 1, 'PC', 0, 0, '2019-03-21 04:28:24', '2019032112282415531425046284fb15c.jpg', 0);
INSERT INTO `mbms_material` VALUES (6, '应用壁纸-EG', 5, 1, '640*360', 5, '应用壁纸', 0, '后台数据', 2, 1, 'Android', 0, 0, '2019-03-21 04:30:45', '201903211230451553142645401d0d5fb.jpg', 0);
INSERT INTO `mbms_material` VALUES (7, '简约壁纸-EGG', 5, 1, '626*626', 5, '简约壁纸', 0, '后台数据', 1, 3, 'IOS', 0, 0, '2019-03-21 04:33:38', '2019032112333815531428182442068a5.jpg', 0);
INSERT INTO `mbms_material` VALUES (8, '城市的夜-EG6482051', 2, 1, '800*534', 3, '城市的夜', 0, '后台数据', 3, 1, 'PC', 0, 0, '2019-03-21 04:35:38', '201903211235381553142938205d50e23.jpg', 0);
INSERT INTO `mbms_material` VALUES (9, '城市之晨 - GA921021', 2, 1, '800*509', 3, '城市之晨', 0, '后台数据', 3, 3, 'PC', 0, 0, '2019-03-21 04:37:51', '201903211237511553143071032e32fde.jpg', 0);
INSERT INTO `mbms_material` VALUES (10, '科技壁纸', 5, 1, '800*588', 5, '科技壁纸', 0, '后台数据', 2, 3, 'PC', 0, 0, '2019-03-21 04:42:41', '201903211242411553143361578044812.jpg', 0);
INSERT INTO `mbms_material` VALUES (11, '炫黑简约壁纸', 6, 1, '800*534', 5, '炫黑简约', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 04:44:37', '201903211244371553143477937cdb0cd.jpg', 0);
INSERT INTO `mbms_material` VALUES (12, '炫黑简约 - HK12054', 6, 1, '800*534', 5, '炫黑简约', 0, '后台数据', 5, 4, 'PC', 0, 0, '2019-03-21 04:46:12', '20190321124612155314357281522bf6e.jpg', 0);
INSERT INTO `mbms_material` VALUES (13, '炫黑简约 - IDD821', 6, 1, '800*534', 5, '炫黑简约', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 04:47:31', '2019032112473115531436512935a555c.jpg', 0);
INSERT INTO `mbms_material` VALUES (14, '炫黑简约 - TE15240', 6, 1, '295*400', 5, '炫黑简约', 0, '后台数据', 3, 1, 'Android', 0, 0, '2019-03-21 04:48:46', '201903211248461553143726317897df6.jpg', 0);
INSERT INTO `mbms_material` VALUES (15, 'AI - 科技', 2, 1, '800*600', 1, '科技素材', 0, '后台数据', 4, 2, 'PC', 0, 0, '2019-03-21 04:50:07', '2019032112500715531438076902317cf.jpg', 0);
INSERT INTO `mbms_material` VALUES (16, '简约 - DS20561', 5, 1, '800*482', 5, '简约壁纸', 0, '后台数据', 3, 1, 'IOS', 0, 0, '2019-03-21 04:51:15', '201903211251151553143875630265540.jpg', 0);
INSERT INTO `mbms_material` VALUES (17, '传媒之窗', 6, 1, '800*530', 4, '传媒之窗', 0, '后台数据', 3, 2, 'PC', 0, 0, '2019-03-21 04:53:58', '201903211253581553144038535ea4f34.jpg', 0);
INSERT INTO `mbms_material` VALUES (18, '简约 - HH152045', 7, 1, '800*536', 5, '简约', 0, '后台数据', 5, 1, 'IOS', 0, 0, '2019-03-21 04:55:23', '2019032112552315531441236410aefc1.jpg', 0);
INSERT INTO `mbms_material` VALUES (19, 'News', 4, 1, '800*533', 4, 'News', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 04:56:25', '2019032112562515531441857195c88d6.jpg', 0);
INSERT INTO `mbms_material` VALUES (20, '传媒之路', 4, 1, '800*533', 4, '传媒之路', 0, '后台数据', 5, 1, 'PC', 0, 0, '2019-03-21 04:57:34', '201903211257341553144254989788ae5.jpg', 0);
INSERT INTO `mbms_material` VALUES (21, '摄像', 6, 1, '800*533', 4, '摄像', 0, '后台数据', 5, 1, 'PC', 0, 0, '2019-03-21 04:58:39', '2019032112583915531443193839abd9c.jpg', 0);
INSERT INTO `mbms_material` VALUES (22, 'News - SD53502', 4, 1, '822*566', 4, 'News', 0, '后台数据', 5, 1, 'PC', 0, 0, '2019-03-21 04:59:36', '201903211259361553144376006306d1e.jpg', 0);
INSERT INTO `mbms_material` VALUES (23, '时尚专贴 - FD10265', 2, 1, '800*533', 2, '时尚专贴', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 05:01:22', '201903211301221553144482802af1cf8.jpg', 0);
INSERT INTO `mbms_material` VALUES (24, '时尚专贴 - KL16502', 2, 1, '800*533', 2, '时尚专贴', 0, '后台数据', 5, 3, 'PC', 0, 0, '2019-03-21 05:02:11', '201903211302111553144531799a7712d.jpg', 0);
INSERT INTO `mbms_material` VALUES (25, '时尚专贴 - HD120542', 2, 1, '800*541', 2, '时尚专贴', 0, '后台数据', 5, 3, 'IOS', 0, 0, '2019-03-21 05:03:21', '20190321130321155314460123501bde8.jpg', 0);
INSERT INTO `mbms_material` VALUES (26, '时尚专贴 - LL51021', 2, 1, '800*526', 2, '时尚专贴', 0, '后台数据', 4, 1, 'IOS', 0, 0, '2019-03-21 05:04:17', '201903211304171553144657913882bdf.jpg', 0);
INSERT INTO `mbms_material` VALUES (27, '粉色童话', 7, 1, '800*533', 5, '粉色童话', 0, '后台数据', 2, 4, 'Android', 0, 0, '2019-03-21 05:05:35', '201903211305351553144735480ee5541.jpg', 0);
INSERT INTO `mbms_material` VALUES (28, 'AI-LL51022', 2, 1, '800*534', 1, 'AI科技', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 05:06:40', '201903211306401553144800457d34aa6.jpg', 0);
INSERT INTO `mbms_material` VALUES (29, 'AI - HJ152044', 2, 1, '800*526', 1, 'AI科技', 0, '后台数据', 5, 2, 'PC', 0, 0, '2019-03-21 05:07:32', '2019032113073215531448522077fefbf.jpg', 0);
INSERT INTO `mbms_material` VALUES (30, '应用壁纸', 5, 1, '400*282', 5, '应用壁纸', 0, '后台数据', 5, 4, 'Android', 0, 0, '2019-03-21 05:08:38', '201903211308381553144918960d5bc08.jpg', 0);
INSERT INTO `mbms_material` VALUES (31, '应用壁纸 - JK520211', 5, 1, '800*483', 5, '应用壁纸', 0, '后台数据', 5, 3, 'IOS', 0, 0, '2019-03-21 05:11:35', '201903211311351553145095276fb6fcc.jpg', 0);
INSERT INTO `mbms_material` VALUES (32, '应用壁纸 - OP22055', 4, 1, '800*534', 5, '应用壁纸', 0, '后台数据', 2, 1, 'PC', 0, 0, '2019-03-21 05:12:36', '20190321131236155314515661491d498.jpg', 0);

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
-- Records of mbms_media
-- ----------------------------
INSERT INTO `mbms_media` VALUES (1, '手机百度');
INSERT INTO `mbms_media` VALUES (2, '百度浏览器');
INSERT INTO `mbms_media` VALUES (3, '百度贴吧');
INSERT INTO `mbms_media` VALUES (4, '芒果TV');

-- ----------------------------
-- Table structure for mbms_smstyle
-- ----------------------------
DROP TABLE IF EXISTS `mbms_smstyle`;
CREATE TABLE `mbms_smstyle`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of mbms_smstyle
-- ----------------------------
INSERT INTO `mbms_smstyle` VALUES (1, '科技');
INSERT INTO `mbms_smstyle` VALUES (2, '人物');
INSERT INTO `mbms_smstyle` VALUES (3, '景致');
INSERT INTO `mbms_smstyle` VALUES (4, '传媒');
INSERT INTO `mbms_smstyle` VALUES (5, '壁纸');

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
-- Records of mbms_smtype
-- ----------------------------
INSERT INTO `mbms_smtype` VALUES (1, '图片');
INSERT INTO `mbms_smtype` VALUES (2, '视频');

-- ----------------------------
-- Table structure for user_dept
-- ----------------------------
DROP TABLE IF EXISTS `user_dept`;
CREATE TABLE `user_dept`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_dept
-- ----------------------------
INSERT INTO `user_dept` VALUES (1, '管理部');
INSERT INTO `user_dept` VALUES (2, '策划部');
INSERT INTO `user_dept` VALUES (3, '制作部');
INSERT INTO `user_dept` VALUES (4, '市场部');

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (15, 'Aaron', 'e10adc3949ba59abbe56e057f20f883e', '18311067343', NULL, NULL, NULL, 1, 1, '2019-03-21 04:09:00', '2019-03-21 04:14:54', '201903211210361553141436785df5e8b.jpg');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, '管理员');
INSERT INTO `user_role` VALUES (2, '普通用户');

SET FOREIGN_KEY_CHECKS = 1;
