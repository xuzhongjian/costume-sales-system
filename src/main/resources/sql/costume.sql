SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `costume_type`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `type_name`  varchar(64)      NOT NULL COMMENT '类型名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8 COMMENT ='服装类型';

-- ----------------------------
-- Table structure for class_room
-- ----------------------------
DROP TABLE IF EXISTS `class_room`;
CREATE TABLE `class_room`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`  tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`   datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `building_id` int(11)          NOT NULL COMMENT '所属教学楼id',
    `sort_number` int(11)          NOT NULL COMMENT '在教学楼内的id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 342
  DEFAULT CHARSET = utf8 COMMENT ='教室';

-- ----------------------------
-- Table structure for cou_stu_rel
-- ----------------------------
DROP TABLE IF EXISTS `cou_stu_rel`;
CREATE TABLE `cou_stu_rel`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `stu_id`     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '学生id',
    `cou_id`     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 39
  DEFAULT CHARSET = utf8 COMMENT ='课程-学生关联表';

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`   tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`    datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `sort_number`  int(11) unsigned NOT NULL DEFAULT '0' COMMENT '课程序号,名称相同的课此字段相同',
    `course_name`  varchar(64)      NOT NULL COMMENT '课程名称',
    `teacher_name` varchar(64)               DEFAULT NULL COMMENT '任课老师',
    `opening`      int(11)          NOT NULL DEFAULT '0' COMMENT '开设单位',
    `course_desc`  varchar(255)              DEFAULT NULL COMMENT '课程简介',
    `stu_capacity` int(11)                   DEFAULT '100' COMMENT '容量',
    `stu_size`     int(11)                   DEFAULT '0' COMMENT '当前人数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 39
  DEFAULT CHARSET = utf8 COMMENT ='所有的课程';

-- ----------------------------
-- Table structure for course_time
-- ----------------------------
DROP TABLE IF EXISTS `course_time`;
CREATE TABLE `course_time`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `course_id`  int(11)          NOT NULL COMMENT '课程',
    `period_id`  int(11)                   DEFAULT '0' COMMENT '上课时间',
    `room_id`    int(11)                   DEFAULT '0' COMMENT '上课地点',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 71
  DEFAULT CHARSET = utf8 COMMENT ='课程上课时间';

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `parent_id`  int(11)          NOT NULL DEFAULT '0' COMMENT '父级id',
    `major_name` varchar(255)              DEFAULT NULL COMMENT '专业名称',
    `major_desc` varchar(255)              DEFAULT NULL COMMENT '专业描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 117
  DEFAULT CHARSET = utf8 COMMENT ='所有专业';

-- ----------------------------
-- Table structure for period
-- ----------------------------
DROP TABLE IF EXISTS `period`;
CREATE TABLE `period`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`   tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`    datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `week`         tinyint(4)       NOT NULL DEFAULT '0' COMMENT '周几',
    `period_begin` varchar(255)              DEFAULT NULL COMMENT '起始时间',
    `period_end`   varchar(255)              DEFAULT NULL COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 86
  DEFAULT CHARSET = utf8 COMMENT ='时间段';

-- ----------------------------
-- Table structure for rec_course
-- ----------------------------
DROP TABLE IF EXISTS `rec_course`;
CREATE TABLE `rec_course`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`   tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`    datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `major_id`     int(11)                   DEFAULT NULL COMMENT '专业id',
    `cou_sort_num` int(11)                   DEFAULT NULL COMMENT '课程sort_number',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8 COMMENT ='推荐选课';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `class_id`   int(11)          NOT NULL DEFAULT '0' COMMENT '班级id',
    `stu_name`   varchar(255)              DEFAULT NULL COMMENT '学生名称',
    `stu_sex`    tinyint(4)                DEFAULT '1' COMMENT '性别 0女,1男',
    `account`    varchar(64)               DEFAULT '29999',
    `password`   varchar(255)              DEFAULT 'pwd68680',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 139
  DEFAULT CHARSET = utf8 COMMENT ='所有的学生';

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`  tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`   datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `major_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '专业id',
    `sort_number` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '班级序号',
    `grade`       int(11)          NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1020
  DEFAULT CHARSET = utf8 COMMENT ='所有班级';


SET FOREIGN_KEY_CHECKS = 1;
