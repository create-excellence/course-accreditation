/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : course_accreditation

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 30/03/2020 17:27:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `credit` double(12, 1) NULL DEFAULT NULL,
  `nature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 236 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (191, 'XT044', '测试课程44', 44.0, '选修18', '2019-12-09 17:29:22', '2019-12-09 17:29:22');
INSERT INTO `course` VALUES (192, 'XT045', '测试课程45', 45.0, '选修19', '2019-12-09 17:29:22', '2019-12-09 17:29:22');
INSERT INTO `course` VALUES (194, 'XT047', '测试课程47', 47.0, '选修21', '2019-12-09 17:29:22', '2019-12-09 17:29:22');
INSERT INTO `course` VALUES (195, 'XT048', '测试课程48', 48.0, '选修22', '2019-12-09 17:29:22', '2019-12-09 17:29:22');
INSERT INTO `course` VALUES (196, 'XT002', '测试课程2', 2.0, '必修2', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (197, 'XT003', '测试课程3', 3.0, '必修3', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (198, 'XT005', '测试课程5', 5.0, '必修5', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (199, 'XT006', '测试课程6', 6.0, '必修6', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (200, 'XT007', '测试课程7', 7.0, '必修7', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (201, 'XT008', '测试课程8', 8.0, '必修8', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (202, 'XT009', '测试课程9', 9.0, '必修9', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (203, 'XT010', '测试课程10', 10.0, '必修10', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (204, 'XT011', '测试课程11', 11.0, '必修11', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (205, 'XT012', '测试课程12', 12.0, '必修12', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (206, 'XT013', '测试课程13', 13.0, '必修13', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (207, 'XT014', '测试课程14', 14.0, '必修14', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (208, 'XT015', '测试课程15', 15.0, '必修15', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (209, 'XT016', '测试课程16', 16.0, '必修16', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (210, 'XT017', '测试课程17', 17.0, '必修17', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (211, 'XT018', '测试课程18', 18.0, '必修18', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (212, 'XT019', '测试课程19', 19.0, '必修19', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (213, 'XT020', '测试课程20', 20.0, '必修20', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (214, 'XT021', '测试课程21', 21.0, '必修21', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (215, 'XT022', '测试课程22', 22.0, '必修22', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (216, 'XT023', '测试课程23', 23.0, '必修23', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (217, 'XT024', '测试课程24', 24.0, '必修24', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (218, 'XT025', '测试课程25', 25.0, '必修25', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (219, 'XT026', '测试课程26', 26.0, '必修26', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (220, 'XT027', '测试课程27', 27.0, '选修1', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (221, 'XT028', '测试课程28', 28.0, '选修2', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (222, 'XT029', '测试课程29', 29.0, '选修3', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (223, 'XT030', '测试课程30', 30.0, '选修4', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (224, 'XT031', '测试课程31', 31.0, '选修5', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (225, 'XT032', '测试课程32', 32.0, '选修6', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (226, 'XT033', '测试课程33', 33.0, '选修7', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (227, 'XT034', '测试课程34', 34.0, '选修8', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (228, 'XT035', '测试课程35', 35.0, '选修9', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (229, 'XT036', '测试课程36', 36.0, '选修10', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (230, 'XT037', '测试课程37', 37.0, '选修11', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (231, 'XT038', '测试课程38', 38.0, '选修12', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (232, 'XT039', '测试课程39', 39.0, '选修13', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (233, 'XT040', '测试课程40', 40.0, '选修14', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (234, 'XT041', '测试课程41', 41.0, '选修15', '2019-12-09 17:32:17', '2019-12-09 17:32:17');
INSERT INTO `course` VALUES (235, 'XT042', '测试课程42', 42.0, '选修16', '2019-12-09 17:32:17', '2019-12-09 17:32:17');

-- ----------------------------
-- Table structure for course_class
-- ----------------------------
DROP TABLE IF EXISTS `course_class`;
CREATE TABLE `course_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程序号',
  `semester_id` int(11) NULL DEFAULT NULL COMMENT '学期id',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '老师id',
  `start_week` int(11) NULL DEFAULT NULL,
  `end_week` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '0-开课 1-未开课',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `学期`(`semester_id`) USING BTREE,
  INDEX `课程`(`course_id`) USING BTREE,
  INDEX `教师`(`teacher_id`) USING BTREE,
  CONSTRAINT `学期` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `教师` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `课程` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course_class
-- ----------------------------
INSERT INTO `course_class` VALUES (1, '1313163', 61, 191, 1, 3, 18, 0, '2019-12-13 17:36:25', '2020-03-30 15:56:10');
INSERT INTO `course_class` VALUES (2, '10989811', 60, 196, 1, 3, 18, 0, '2020-03-29 01:08:40', '2020-03-30 17:06:02');

-- ----------------------------
-- Table structure for course_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `course_evaluation`;
CREATE TABLE `course_evaluation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_class_id` int(11) NULL DEFAULT NULL,
  `questionnaire_id` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '0-未开始 1-进行中 2-已结束',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_class_id`(`course_class_id`) USING BTREE,
  INDEX `questionnaire_id`(`questionnaire_id`) USING BTREE,
  CONSTRAINT `course_evaluation_ibfk_1` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `course_evaluation_ibfk_2` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_evaluation
-- ----------------------------
INSERT INTO `course_evaluation` VALUES (1, '测试发布', 1, 1, NULL, NULL, '2020-03-30 17:00:00', '2020-03-31 18:02:00', '2020-03-29 03:29:28', '2020-03-29 03:29:28');
INSERT INTO `course_evaluation` VALUES (2, '测试2', 1, 1, NULL, '测试22', '2020-03-29 16:00:00', '2020-03-29 18:00:00', '2020-03-29 03:33:55', '2020-03-29 03:33:55');
INSERT INTO `course_evaluation` VALUES (3, '测试23', 1, 1, NULL, '测试223', '2020-03-29 16:00:00', '2020-03-29 18:00:00', '2020-03-29 03:34:18', '2020-03-29 03:34:18');
INSERT INTO `course_evaluation` VALUES (4, '测试232312', 1, 1, NULL, '测试223213123', '2020-03-29 16:00:00', '2020-03-29 18:00:00', '2020-03-29 03:35:07', '2020-03-29 03:35:07');
INSERT INTO `course_evaluation` VALUES (5, '测试3', 1, 1, NULL, '21312312312', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:35:22', '2020-03-29 03:35:22');
INSERT INTO `course_evaluation` VALUES (6, '测试34', 1, 1, NULL, '21312312312', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:35:42', '2020-03-29 03:35:42');
INSERT INTO `course_evaluation` VALUES (7, '测试34', 1, 1, NULL, '21312312312', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:35:46', '2020-03-29 03:35:46');
INSERT INTO `course_evaluation` VALUES (8, '测试5', 2, 3, NULL, '开始测试5', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:37:11', '2020-03-29 03:37:11');
INSERT INTO `course_evaluation` VALUES (9, '测试7', 1, 1, NULL, '测试赛哈哈哈', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:41:51', '2020-03-29 03:41:51');
INSERT INTO `course_evaluation` VALUES (10, '测试74444', 1, 1, NULL, '测试赛哈哈哈', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:42:04', '2020-03-29 03:42:04');
INSERT INTO `course_evaluation` VALUES (11, '测试74444', 1, 1, NULL, '测试赛哈哈哈', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:42:08', '2020-03-29 03:42:08');
INSERT INTO `course_evaluation` VALUES (12, '测是', 1, 1, NULL, '打打', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:44:26', '2020-03-29 03:44:26');
INSERT INTO `course_evaluation` VALUES (13, '测是', 1, 1, NULL, '打打', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:44:30', '2020-03-29 03:44:30');
INSERT INTO `course_evaluation` VALUES (14, '测是', 1, 1, NULL, '打打', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:44:32', '2020-03-29 03:44:32');
INSERT INTO `course_evaluation` VALUES (15, '测试final', 1, 1, NULL, '三生三世', '2020-03-29 16:02:00', '2020-03-30 18:02:00', '2020-03-29 03:46:52', '2020-03-29 03:46:52');
INSERT INTO `course_evaluation` VALUES (16, '测试3', 1, 1, NULL, '是是是', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:47:16', '2020-03-29 03:47:16');
INSERT INTO `course_evaluation` VALUES (17, '最后一次测试', 1, 1, NULL, '擦擦擦', '2020-03-29 16:00:00', '2020-03-30 16:00:00', '2020-03-29 03:47:45', '2020-03-29 03:47:45');
INSERT INTO `course_evaluation` VALUES (18, '测试6', 1, 1, NULL, '测试', '2020-03-30 13:00:00', '2020-03-30 13:16:00', '2020-03-30 11:22:20', '2020-03-30 11:22:20');
INSERT INTO `course_evaluation` VALUES (19, '测试时间', 1, 1, NULL, '测试', '2020-03-31 01:00:00', '2020-03-31 16:00:00', '2020-03-30 11:24:52', '2020-03-30 11:24:52');
INSERT INTO `course_evaluation` VALUES (20, '测试5', 1, 1, NULL, '', '2020-03-30 16:00:00', '2020-03-31 16:00:00', '2020-03-30 11:25:24', '2020-03-30 11:25:24');
INSERT INTO `course_evaluation` VALUES (21, '测试时间444', 1, 1, NULL, '', '2020-03-30 16:00:00', '2020-03-30 21:00:00', '2020-03-30 15:30:46', '2020-03-30 15:30:46');
INSERT INTO `course_evaluation` VALUES (22, 'final', 1, 1, NULL, '', '2020-03-30 16:00:00', '2020-03-30 16:14:00', '2020-03-30 15:45:12', '2020-03-30 15:45:12');

-- ----------------------------
-- Table structure for course_target
-- ----------------------------
DROP TABLE IF EXISTS `course_target`;
CREATE TABLE `course_target`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionnaire_id` int(11) NULL DEFAULT NULL,
  `title` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `point_id` int(11) NULL DEFAULT NULL COMMENT '指标点序号',
  `options` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选项',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `options_score` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选项得分',
  `total_score` int(11) NULL DEFAULT NULL COMMENT '总分',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '序号',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `问卷`(`questionnaire_id`) USING BTREE,
  INDEX `毕业指标点`(`point_id`) USING BTREE,
  CONSTRAINT `毕业指标点` FOREIGN KEY (`point_id`) REFERENCES `graduation_point` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `问卷` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaire` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for graduation_demand
-- ----------------------------
DROP TABLE IF EXISTS `graduation_demand`;
CREATE TABLE `graduation_demand`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` int(11) NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '毕业要求编号',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '必要要求内容',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `专业`(`major_id`) USING BTREE,
  CONSTRAINT `专业` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of graduation_demand
-- ----------------------------
INSERT INTO `graduation_demand` VALUES (1, 1, '11', '321', NULL, NULL);
INSERT INTO `graduation_demand` VALUES (2, 2, '34', '123131', NULL, NULL);

-- ----------------------------
-- Table structure for graduation_point
-- ----------------------------
DROP TABLE IF EXISTS `graduation_point`;
CREATE TABLE `graduation_point`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `graduation_demand_id` int(11) NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `毕业要求`(`graduation_demand_id`) USING BTREE,
  CONSTRAINT `毕业要求` FOREIGN KEY (`graduation_demand_id`) REFERENCES `graduation_demand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of graduation_point
-- ----------------------------
INSERT INTO `graduation_point` VALUES (1, '11', 1, '离散数学', NULL, NULL);
INSERT INTO `graduation_point` VALUES (2, '22', 1, '高等数学', NULL, NULL);
INSERT INTO `graduation_point` VALUES (4, '32', 2, '222', '2019-12-21 10:47:40', '2019-12-21 10:47:40');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '迭代测试专业1', 'ATX001', '2019-12-09 13:20:56', '2019-12-09 13:22:50');
INSERT INTO `major` VALUES (2, 'test测试专业2', 'ATX002', '2019-12-09 13:20:56', '2019-12-09 13:22:55');
INSERT INTO `major` VALUES (14, '测试专业14', 'ATX014', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (15, '测试专业15', 'ATX015', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (16, '测试专业16', 'ATX016', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (17, '测试专业17', 'ATX017', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (18, '测试专业18', 'ATX018', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (19, '测试专业19', 'ATX019', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (20, '测试专业20', 'ATX020', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (21, '测试专业21', 'ATX021', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (22, '测试专业22', 'ATX022', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (23, '测试专业23', 'ATX023', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (24, '测试专业24', 'ATX024', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (25, '测试专业25', 'ATX025', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (26, '测试专业26', 'ATX026', '2019-12-09 13:20:56', '2019-12-09 13:20:56');
INSERT INTO `major` VALUES (27, '西南区域', '茶园西区', '2020-03-27 00:33:21', '2020-03-27 00:33:21');
INSERT INTO `major` VALUES (28, '西南区域', '广西片区', '2020-03-27 00:33:21', '2020-03-27 00:33:21');
INSERT INTO `major` VALUES (29, '测试专业3', 'ATX003', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (30, '测试专业4', 'ATX004', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (31, '测试专业5', 'ATX005', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (32, '测试专业6', 'ATX006', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (33, '测试专业7', 'ATX007', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (34, '测试专业8', 'ATX008', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (35, '测试专业9', 'ATX009', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (36, '测试专业10', 'ATX010', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (37, '测试专业11', 'ATX011', '2020-03-27 00:36:02', '2020-03-27 00:36:02');
INSERT INTO `major` VALUES (38, '测试专业12', 'ATX012', '2020-03-27 00:36:03', '2020-03-27 00:36:03');
INSERT INTO `major` VALUES (39, '测试专业13', 'ATX013', '2020-03-27 00:36:03', '2020-03-27 00:36:03');
INSERT INTO `major` VALUES (40, '测试', '21312312', '2020-03-29 03:38:54', '2020-03-29 03:38:54');

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_class_id` int(11) NULL DEFAULT NULL,
  `total_score` int(11) NULL DEFAULT NULL,
  `describes` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `课程班级`(`course_class_id`) USING BTREE,
  CONSTRAINT `课程班级` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of questionnaire
-- ----------------------------
INSERT INTO `questionnaire` VALUES (1, '吞吞吐吐', 1, 100, '{\"ops\":[{\"insert\":\"通天塔\\n\"}]}', '2020-03-24 18:38:56', '2020-03-24 18:38:56');
INSERT INTO `questionnaire` VALUES (2, '啊啊啊啊', 1, NULL, '{\"ops\":[{\"insert\":\"阿达\\n\"}]}', '2020-03-29 01:06:48', '2020-03-29 01:06:48');
INSERT INTO `questionnaire` VALUES (3, 'ttttt', 2, 0, '{\"ops\":[{\"insert\":\"阿达\\n\"}]}', '2020-03-29 01:09:07', '2020-03-29 01:09:07');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号或学号',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (3, '123', 'admin', NULL, NULL, NULL);
INSERT INTO `role` VALUES (4, '123', 'teacher', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for select_course
-- ----------------------------
DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_class_id` int(11) NULL DEFAULT NULL COMMENT '课程班级Id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生Id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `学生`(`student_id`) USING BTREE,
  INDEX `对应课号课程`(`course_class_id`) USING BTREE,
  CONSTRAINT `学生` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `对应课号课程` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES (1, 1, 2, NULL, NULL);

-- ----------------------------
-- Table structure for self_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `self_evaluation`;
CREATE TABLE `self_evaluation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NULL DEFAULT NULL,
  `course_evaluation_id` int(11) NULL DEFAULT NULL,
  `course_target_id` int(11) NULL DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价档次',
  `score` int(11) NULL DEFAULT NULL COMMENT '得分',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `课程目标`(`course_target_id`) USING BTREE,
  INDEX `课程评价`(`course_evaluation_id`) USING BTREE,
  CONSTRAINT `self_evaluation_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `课程目标` FOREIGN KEY (`course_target_id`) REFERENCES `course_target` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `课程评价` FOREIGN KEY (`course_evaluation_id`) REFERENCES `course_evaluation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` date NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES (32, '2019秋-2020春', '2019-09-03', '2019-12-09 17:52:15', '2019-12-09 17:52:15');
INSERT INTO `semester` VALUES (33, '2019秋-2021春', '2019-09-04', '2019-12-09 17:52:18', '2019-12-09 17:52:18');
INSERT INTO `semester` VALUES (34, '2019秋-2022春', '2019-09-05', '2019-12-09 17:52:18', '2019-12-09 17:52:18');
INSERT INTO `semester` VALUES (35, '2019秋-2023春', '2019-09-06', '2019-12-09 17:52:19', '2019-12-09 17:52:19');
INSERT INTO `semester` VALUES (36, '2019秋-2024春', '2019-09-07', '2019-12-09 17:52:19', '2019-12-09 17:52:19');
INSERT INTO `semester` VALUES (37, '2019秋-2025春', '2019-09-08', '2019-12-09 17:52:20', '2019-12-09 17:52:20');
INSERT INTO `semester` VALUES (38, '2019秋-2026春', '2019-09-09', '2019-12-09 17:52:20', '2019-12-09 17:52:20');
INSERT INTO `semester` VALUES (39, '2019秋-2027春', '2019-09-10', '2019-12-09 17:52:21', '2019-12-09 17:52:21');
INSERT INTO `semester` VALUES (40, '2019秋-2028春', '2019-09-11', '2019-12-09 17:52:23', '2019-12-09 17:52:23');
INSERT INTO `semester` VALUES (41, '2019秋-2029春', '2019-09-12', '2019-12-09 17:52:24', '2019-12-09 17:52:24');
INSERT INTO `semester` VALUES (42, '2019秋-2030春', '2019-09-13', '2019-12-09 17:52:24', '2019-12-09 17:52:24');
INSERT INTO `semester` VALUES (43, '2019秋-2031春', '2019-09-14', '2019-12-09 17:52:24', '2019-12-09 17:52:24');
INSERT INTO `semester` VALUES (44, '2019秋-2032春', '2019-09-15', '2019-12-09 17:52:24', '2019-12-09 17:52:24');
INSERT INTO `semester` VALUES (45, '2019秋-2033春', '2019-09-16', '2019-12-09 17:52:25', '2019-12-09 17:52:25');
INSERT INTO `semester` VALUES (46, '2019秋-2034春', '2019-09-17', '2019-12-09 17:52:25', '2019-12-09 17:52:25');
INSERT INTO `semester` VALUES (47, '2019秋-2035春', '2019-09-18', '2019-12-09 17:52:25', '2019-12-09 17:52:25');
INSERT INTO `semester` VALUES (48, '2019秋-2036春', '2019-09-19', '2019-12-09 17:52:25', '2019-12-09 17:52:25');
INSERT INTO `semester` VALUES (49, '2019秋-2037春', '2019-09-20', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (50, '2019秋-2038春', '2019-09-21', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (51, '2019秋-2039春', '2019-09-22', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (52, '2019秋-2040春', '2019-09-23', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (53, '2019秋-2041春', '2019-09-24', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (54, '2019秋-2042春', '2019-09-25', '2019-12-09 17:52:26', '2019-12-09 17:52:26');
INSERT INTO `semester` VALUES (55, '2019秋-2043春', '2019-09-26', '2019-12-09 17:52:27', '2019-12-09 17:52:27');
INSERT INTO `semester` VALUES (56, '2019秋-2044春', '2019-09-27', '2019-12-09 17:52:27', '2019-12-09 17:52:27');
INSERT INTO `semester` VALUES (57, '2019秋-2045春', '2019-09-28', '2019-12-09 17:52:29', '2019-12-09 17:52:29');
INSERT INTO `semester` VALUES (58, '2019秋-2046春', '2019-09-29', '2019-12-09 17:52:29', '2019-12-09 17:52:29');
INSERT INTO `semester` VALUES (59, '2019秋-2047春', '2019-09-30', '2019-12-09 17:52:29', '2019-12-09 17:52:29');
INSERT INTO `semester` VALUES (60, '2019秋-2048春', '2019-10-01', '2019-12-09 17:52:29', '2019-12-09 17:52:29');
INSERT INTO `semester` VALUES (61, '2019秋-2049春', '2019-10-02', '2019-12-09 17:52:29', '2019-12-09 17:52:29');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `major_id` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '正常-0',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_id`(`major_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2, '1234', '123456', '测试', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for supporting_course
-- ----------------------------
DROP TABLE IF EXISTS `supporting_course`;
CREATE TABLE `supporting_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `graduation_point_id` int(11) NULL DEFAULT NULL,
  `weight` double(11, 2) NULL DEFAULT NULL,
  `course_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `graduation_point_id`(`graduation_point_id`) USING BTREE,
  INDEX `couse_id`(`course_id`) USING BTREE,
  CONSTRAINT `supporting_course_ibfk_1` FOREIGN KEY (`graduation_point_id`) REFERENCES `graduation_point` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `supporting_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of supporting_course
-- ----------------------------
INSERT INTO `supporting_course` VALUES (75, 2, 2.00, 194, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (76, 1, 3.00, 197, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (77, 1, 5.00, 198, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (78, 2, 6.00, 199, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (80, 2, 8.00, 201, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (81, 1, 9.00, 202, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (82, 2, 10.00, 203, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (83, 1, 11.00, 204, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (84, 2, 12.00, 205, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (85, 1, 13.00, 206, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (86, 2, 14.00, 207, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (87, 1, 15.00, 208, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (88, 2, 16.00, 209, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (89, 1, 17.00, 210, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (90, 2, 18.00, 211, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (91, 1, 19.00, 212, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (92, 2, 20.00, 213, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (93, 1, 21.00, 214, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (94, 2, 22.00, 215, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (95, 1, 23.00, 216, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (96, 2, 24.00, 217, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (97, 1, 25.00, 218, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (98, 2, 26.00, 219, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (99, 1, 27.00, 220, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (100, 2, 28.00, 221, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (101, 1, 29.00, 222, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (102, 2, 30.00, 223, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (103, 1, 31.00, 224, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (104, 2, 32.00, 225, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (105, 1, 33.00, 226, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (106, 2, 34.00, 227, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (107, 1, 35.00, 228, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (108, 2, 36.00, 229, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (109, 1, 37.00, 230, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (110, 2, 38.00, 231, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (111, 1, 39.00, 232, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (112, 2, 40.00, 233, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (113, 1, 41.00, 234, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (114, 2, 42.00, 235, '2019-12-13 17:28:49', '2019-12-13 17:28:49');
INSERT INTO `supporting_course` VALUES (115, 1, 43.00, 191, '2019-12-13 17:28:49', '2019-12-21 11:55:05');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职称',
  `birth` date NULL DEFAULT NULL,
  `graduate_school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `graduate_major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `login_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '123', '123456', NULL, '哈哈啊', 'http://localhost:8888/v1/img/1585242600560hbk8B8n0qia6bAe.JPG', '男', '副教授', '1981-06-09', '桂电呀', '计算机', '2019-12-13 17:31:56', '2019-12-13 17:31:56', NULL);

SET FOREIGN_KEY_CHECKS = 1;
