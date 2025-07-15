/*
 Navicat Premium Dump SQL

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : graduation_work

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 15/07/2025 17:25:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answers
-- ----------------------------
DROP TABLE IF EXISTS `answers`;
CREATE TABLE `answers`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '回答者（users.id）',
  `quiz_id` bigint UNSIGNED NOT NULL COMMENT '回答的题目（quizzes.id）',
  `chosen_option` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所选答案（A/B/C/D）',
  `is_correct` tinyint(1) NOT NULL COMMENT '是否正确（0/1）',
  `is_answer` tinyint(1) NOT NULL COMMENT '是否已回答（0/1）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_answers_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_answers_quiz`(`quiz_id` ASC) USING BTREE,
  CONSTRAINT `fk_answers_quiz` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_answers_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '答案记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answers
-- ----------------------------
INSERT INTO `answers` VALUES (1, 3, 1, 'B', 1, 1);
INSERT INTO `answers` VALUES (2, 4, 1, 'C', 0, 1);
INSERT INTO `answers` VALUES (3, 3, 2, 'C', 1, 1);
INSERT INTO `answers` VALUES (4, 4, 2, 'A', 0, 1);
INSERT INTO `answers` VALUES (5, 3, 3, 'A', 1, 1);
INSERT INTO `answers` VALUES (6, 4, 3, 'D', 0, 1);

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '听众（users.id）',
  `lecture_id` bigint UNSIGNED NOT NULL COMMENT '场次（lectures.id）',
  `joined_at` datetime NOT NULL COMMENT '加入时间',
  `left_at` datetime NULL DEFAULT NULL COMMENT '离开时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_attendance_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_attendance_lecture`(`lecture_id` ASC) USING BTREE,
  CONSTRAINT `fk_attendance_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_attendance_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '听众参与记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 3, 1, '2025-08-01 09:55:00', '2025-08-01 11:05:00');
INSERT INTO `attendance` VALUES (2, 4, 1, '2025-08-01 10:05:00', '2025-08-01 10:50:00');
INSERT INTO `attendance` VALUES (3, 3, 2, '2025-08-02 14:25:00', NULL);
INSERT INTO `attendance` VALUES (4, 4, 3, '2025-08-03 08:50:00', '2025-08-03 09:30:00');

-- ----------------------------
-- Table structure for discussions
-- ----------------------------
DROP TABLE IF EXISTS `discussions`;
CREATE TABLE `discussions`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论标识',
  `quiz_id` bigint UNSIGNED NOT NULL COMMENT '针对题目（quizzes.id）',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '评论用户（users.id）',
  `parent_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '父评论（NULL 表示顶级）',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_discussions_quiz`(`quiz_id` ASC) USING BTREE,
  INDEX `idx_discussions_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_discussions_parent`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `fk_discussions_parent` FOREIGN KEY (`parent_id`) REFERENCES `discussions` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_discussions_quiz` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_discussions_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '题目讨论区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discussions
-- ----------------------------
INSERT INTO `discussions` VALUES (1, 1, 3, NULL, '我觉得选项 B 有些不够准确，应该再细化一下题干。', '2025-07-15 17:21:07');
INSERT INTO `discussions` VALUES (2, 1, 4, NULL, '这道题出的很及时，帮助我回顾了前面 PPT 的要点。', '2025-07-15 17:21:07');
INSERT INTO `discussions` VALUES (3, 1, 2, 1, '感谢反馈，我会在下次调整提示词，强化选项描述。', '2025-07-15 17:21:07');
INSERT INTO `discussions` VALUES (4, 1, 3, 2, '同感，AI 出题速度很快，体验很好！', '2025-07-15 17:21:07');

-- ----------------------------
-- Table structure for feedbacks
-- ----------------------------
DROP TABLE IF EXISTS `feedbacks`;
CREATE TABLE `feedbacks`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '反馈标识',
  `user_id` bigint UNSIGNED NOT NULL COMMENT '提交者（users.id）',
  `lecture_id` bigint UNSIGNED NOT NULL COMMENT '针对哪场演讲（lectures.id）',
  `feedback_type` enum('too_fast','too_slow','boring','bad_quiz') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '反馈类型',
  `comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '额外说明',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_feedbacks_user`(`user_id` ASC) USING BTREE,
  INDEX `idx_feedbacks_lecture`(`lecture_id` ASC) USING BTREE,
  CONSTRAINT `fk_feedbacks_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_feedbacks_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户对演讲或题目的反馈表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedbacks
-- ----------------------------
INSERT INTO `feedbacks` VALUES (1, 3, 1, 'too_fast', '讲得节奏有点快，跟不上内容更新。', '2025-07-15 17:17:08');
INSERT INTO `feedbacks` VALUES (2, 4, 1, 'boring', '第一节 PPT 太枯燥，缺少实例演示。', '2025-07-15 17:17:08');
INSERT INTO `feedbacks` VALUES (3, 3, 2, 'too_slow', '第二节讲解太慢，希望能加快进度。', '2025-07-15 17:17:08');
INSERT INTO `feedbacks` VALUES (4, 4, 2, 'bad_quiz', 'Pop Quiz题目和PPT内容关联度不高。', '2025-07-15 17:17:08');

-- ----------------------------
-- Table structure for lecture_contents
-- ----------------------------
DROP TABLE IF EXISTS `lecture_contents`;
CREATE TABLE `lecture_contents`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '内容记录标识',
  `lecture_id` bigint UNSIGNED NOT NULL COMMENT '所属演讲（lectures.id）',
  `content_type` enum('ppt','pdf','text','audio') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件类型',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '原始文件名',
  `storage_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存储路径',
  `processed_text` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'OCR/转写后文本',
  `uploaded_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `processed_at` datetime NULL DEFAULT NULL COMMENT '文本处理完成时间',
  `is_download` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许下载（0 否／1 是）',
  `file_length` int NOT NULL DEFAULT 0 COMMENT '文件总页数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_contents_lecture`(`lecture_id` ASC) USING BTREE,
  CONSTRAINT `fk_contents_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '演讲内容文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lecture_contents
-- ----------------------------

-- ----------------------------
-- Table structure for lectures
-- ----------------------------
DROP TABLE IF EXISTS `lectures`;
CREATE TABLE `lectures`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '演讲唯一标识',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '演讲标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '演讲简介',
  `organizer_id` bigint UNSIGNED NOT NULL COMMENT '组织者（users.id）',
  `speaker_id` bigint UNSIGNED NOT NULL COMMENT '演讲者（users.id）',
  `start_time` datetime NOT NULL COMMENT '演讲开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '演讲结束时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房间密码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_lectures_organizer`(`organizer_id` ASC) USING BTREE,
  INDEX `idx_lectures_speaker`(`speaker_id` ASC) USING BTREE,
  CONSTRAINT `fk_lectures_organizer` FOREIGN KEY (`organizer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_lectures_speaker` FOREIGN KEY (`speaker_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '演讲活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lectures
-- ----------------------------
INSERT INTO `lectures` VALUES (1, '如何使用AI出题', '介绍AI Pop Quiz系统的整体架构与原理', 1, 2, '2025-08-01 10:00:00', '2025-08-01 11:00:00', '2025-07-15 17:06:20', '2025-07-15 17:06:20', 'room123');
INSERT INTO `lectures` VALUES (2, 'Vue+SpringBoot实战', '基于Vue与SpringBoot构建互动式课堂应用', 1, 2, '2025-08-02 14:30:00', '2025-08-02 15:30:00', '2025-07-15 17:06:20', '2025-07-15 17:06:20', NULL);
INSERT INTO `lectures` VALUES (3, '用户体验设计分享', '探讨用户调研与产品迭代的方法', 1, 2, '2025-08-03 09:00:00', NULL, '2025-07-15 17:06:20', '2025-07-15 17:06:20', 'UX2025');

-- ----------------------------
-- Table structure for quiz_gen_logs
-- ----------------------------
DROP TABLE IF EXISTS `quiz_gen_logs`;
CREATE TABLE `quiz_gen_logs`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志标识',
  `lecture_id` bigint UNSIGNED NOT NULL COMMENT '所属演讲（lectures.id）',
  `request_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
  `prompt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '发送给 LLM 的提示词',
  `response` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT 'LLM 返回的原始文本',
  `status` enum('success','failed') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行结果',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_logs_lecture`(`lecture_id` ASC) USING BTREE,
  CONSTRAINT `fk_logs_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'AI 出题日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quiz_gen_logs
-- ----------------------------
INSERT INTO `quiz_gen_logs` VALUES (1, 1, '2025-07-15 17:24:31', '请根据前10分钟内容生成一条四选一的选择题。', '题目：Pop Quiz 系统中，哪个模块负责将 PPT 转为可分析的纯文本？\nA. 统计与反馈模块\nB. 文本转写模块\nC. AI 出题模块\nD. 讨论区模块', 'success');
INSERT INTO `quiz_gen_logs` VALUES (2, 2, '2025-07-15 17:24:31', '生成一条关于 Vue+SpringBoot 实战的选择题。', '错误：输入内容过短，无法生成有效题目。', 'failed');
INSERT INTO `quiz_gen_logs` VALUES (3, 3, '2025-07-15 17:24:31', '请为“用户体验设计分享”演讲生成一道难度适中的选择题。', '题目：在用户体验研究中，第四层主要关注什么？\nA. 用户的真实行为\nB. 问卷调查结果\nC. 口头表达意见\nD. 产品界面美观', 'success');

-- ----------------------------
-- Table structure for quiz_options
-- ----------------------------
DROP TABLE IF EXISTS `quiz_options`;
CREATE TABLE `quiz_options`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '选项标识',
  `quiz_id` bigint UNSIGNED NOT NULL COMMENT '所属题目（quizzes.id）',
  `option_label` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项标签（A/B/C/D）',
  `option_text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '选项内容',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_quiz_option`(`quiz_id` ASC, `option_label` ASC) USING BTREE,
  INDEX `idx_options_quiz`(`quiz_id` ASC) USING BTREE,
  CONSTRAINT `fk_options_quiz` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试题选项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quiz_options
-- ----------------------------
INSERT INTO `quiz_options` VALUES (1, 1, 'A', 'AI 自动出题模块');
INSERT INTO `quiz_options` VALUES (2, 1, 'B', '输入收集与文本转写模块');
INSERT INTO `quiz_options` VALUES (3, 1, 'C', '统计与反馈模块');
INSERT INTO `quiz_options` VALUES (4, 1, 'D', '讨论区管理模块');
INSERT INTO `quiz_options` VALUES (5, 2, 'A', '出题时间判断逻辑');
INSERT INTO `quiz_options` VALUES (6, 2, 'B', '提示词生成与发送模块');
INSERT INTO `quiz_options` VALUES (7, 2, 'C', '题目质量检测闭环');
INSERT INTO `quiz_options` VALUES (8, 2, 'D', '答题结果统计模块');
INSERT INTO `quiz_options` VALUES (9, 3, 'A', '听众答题参与率');
INSERT INTO `quiz_options` VALUES (10, 3, 'B', '平均答题速度');
INSERT INTO `quiz_options` VALUES (11, 3, 'C', '题目讨论热度');
INSERT INTO `quiz_options` VALUES (12, 3, 'D', '听众匿名比例');

-- ----------------------------
-- Table structure for quizzes
-- ----------------------------
DROP TABLE IF EXISTS `quizzes`;
CREATE TABLE `quizzes`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '题目标识',
  `lecture_id` bigint UNSIGNED NOT NULL COMMENT '所属演讲（lectures.id）',
  `file_index` int NOT NULL COMMENT '对应的文件/时间段索引（如第几段内容）',
  `question_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题干',
  `correct_option` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '正确选项（A/B/C/D）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出题时间',
  `AI_paring` datetime NOT NULL COMMENT 'AI 解析完成时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_quizzes_lecture`(`lecture_id` ASC) USING BTREE,
  CONSTRAINT `fk_quizzes_lecture` FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '生成的测试题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quizzes
-- ----------------------------
INSERT INTO `quizzes` VALUES (1, 1, 1, 'Pop Quiz 系统中，哪些组件负责将 PPT 转为可分析的纯文本？', 'B', '2025-07-15 17:11:52', '2025-07-15 12:05:00');
INSERT INTO `quizzes` VALUES (2, 1, 2, '在 AI 自动出题模块中，哪个环节用于检测题目“肤浅”并触发迭代？', 'C', '2025-07-15 17:11:52', '2025-07-15 12:10:00');
INSERT INTO `quizzes` VALUES (3, 2, 1, '下列哪项不是演讲者视角统计页面展示的内容？', 'A', '2025-07-15 17:11:52', '2025-07-15 12:15:00');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码（明文，仅测试）',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户角色（organizer/speaker/audience）',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称／匿名时显示',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名显示（0 否／1 是）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'organizer_zl', '123456', 'organizer', '张老师', 0, '2025-07-15 17:04:11', '2025-07-15 17:04:11');
INSERT INTO `users` VALUES (2, 'speaker_li', '123456', 'speaker', '李演讲', 0, '2025-07-15 17:04:11', '2025-07-15 17:04:11');
INSERT INTO `users` VALUES (3, 'audience_xs', '123456', 'audience', '学生甲', 1, '2025-07-15 17:04:11', '2025-07-15 17:04:11');
INSERT INTO `users` VALUES (4, 'audience_ws', '123456', 'audience', '学生乙', 0, '2025-07-15 17:04:11', '2025-07-15 17:04:11');

SET FOREIGN_KEY_CHECKS = 1;
