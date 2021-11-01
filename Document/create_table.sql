DROP DATABASE IF EXISTS `easy_db`;
CREATE DATABASE IF NOT EXISTS `easy_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use easy_db;
CREATE TABLE user
(
    `id`             INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `username`       VARCHAR(25)                 NOT NULL COMMENT '用户昵称',
    `realname`       VARCHAR(25)                 NOT NULL COMMENT '真实姓名',
    `student_number` VARCHAR(25)                 NOT NULL UNIQUE COMMENT '学生学号',
    `college_id`     INT UNSIGNED                NOT NULL COMMENT '学院ID',
    `password`       VARCHAR(30)                 NOT NULL COMMENT 'MD5加密过的用户密码',
    `email`          VARCHAR(35)                 NOT NULL COMMENT '电子邮箱',
    `phone`          VARCHAR(11)                 NOT NULL UNIQUE COMMENT '手机号码',
    `sex`            INT UNSIGNED                NOT NULL COMMENT '性别',
    `birth`          DATE                        NOT NULL COMMENT '生日',
    `is_open_zone`   INT UNSIGNED DEFAULT 0 COMMENT '是否开放空间',
    `level`          INT UNSIGNED DEFAULT 0 COMMENT '用户权限',
    `user_avatar`    TEXT                        NOT NULL COMMENT '用户头像',
    `post_number`    INT UNSIGNED DEFAULT 0 COMMENT '发帖数量',
    `notice`         VARCHAR(255) COMMENT '空间公告',
    `intro`          VARCHAR(255) COMMENT '个人简介',
    `create_time`    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    `update_time`    TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE college_table
(
    `id`           INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `college_name` VARCHAR(25)                 NOT NULL,
    `create_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE apply_create
(
    `id`                  INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `u_id`                INT UNSIGNED                NOT NULL COMMENT '申请人ID',
    `name`                VARCHAR(30)                 NOT NULL COMMENT '社团名字',
    `logo`                TEXT COMMENT 'base64编码的社团头像',
    `intro`               VARCHAR(255)                NOT NULL COMMENT '社团简介',
    `parent_organization` VARCHAR(25)                 NOT NULL COMMENT '上级组织',
    `is_approved`         INT UNSIGNED                NOT NULL,
    `create_time`         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time`         TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE association_user
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `a_id`        INT UNSIGNED                NOT NULL COMMENT '协会ID',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '用户ID',
    `is_admin`    INT UNSIGNED                NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE apply_join_association
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `a_id`        INT UNSIGNED                NOT NULL COMMENT '协会ID',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '用户ID',
    `note`        VARCHAR(255)                NOT NULL COMMENT '申请时的备注',
    `is_approved` INT UNSIGNED                NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE apply_commond
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `a_id`        INT UNSIGNED                NOT NULL COMMENT '协会ID',
    `title`       VARCHAR(120)                NOT NULL COMMENT '标题',
    `content_id`  INT UNSIGNED                NOT NULL COMMENT '内容ID' `is_approved` INT UNSIGNED NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE apply_content
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `aa_id`       INT UNSIGNED                NOT NULL,
    `content`     TEXT                        NOT NULL COMMENT '审批的内容',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE mail
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `from_id`     INT UNSIGNED                NOT NULL COMMENT '发件人',
    `to_id`       INT UNSIGNED                NOT NULL COMMENT '收件人',
    `title`       VARCHAR(120)                NOT NULL COMMENT '标题',
    `content`     VARCHAR(255)                NOT NULL COMMENT '邮件内容',
    `is_read`     INT UNSIGNED DEFAULT 0,
    `is_system`   INT UNSIGNED DEFAULT 0,
    `mail_type`   INT UNSIGNED DEFAULT 0,
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE join_action
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `act_id`      INT UNSIGNED                NOT NULL COMMENT '活动ID',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE action
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `a_id`        INT UNSIGNED                NOT NULL COMMENT '协会ID',
    `title`       VARCHAR(120)                NOT NULL COMMENT '标题',
    `content`     TEXT                        NOT NULL COMMENT '活动内容',
    `start_time`  DATETIME  DEFAULT CURRENT_TIME,
    `end_time`    DATETIME                    NOT NULL,
    `is_approved` INT UNSIGNED                NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE association
(
    `id`                  INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `leader_id`           INT UNSIGNED                NOT NULL COMMENT '负责人ID',
    `name`                VARCHAR(120)                NOT NULL COMMENT '社团名字',
    `logo`                TEXT                        NOT NULL COMMENT '社团头像',
    `intro`               VARCHAR(120)                NOT NULL COMMENT '社团简介',
    `parent_organization` VARCHAR(120)                NOT NULL COMMENT '上级组织',
    `create_time`         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time`         TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE post
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `a_id`        INT UNSIGNED                NOT NULL COMMENT '协会ID,为0表示公共论坛',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '用户ID',
    `title`       VARCHAR(120)                NOT NULL COMMENT '标题',
    `content_id`  INT UNSIGNED                NOT NULL COMMENT '内容对应ID',
    `tags`        VARCHAR(255)                NOT NULL COMMENT '逗号分隔标签',
    `post_type`   INT UNSIGNED                NOT NULL COMMENT '帖子类型，是一个整数型',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE comments
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `p_id`        INT UNSIGNED                NOT NULL COMMENT '帖子ID',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '发表人用户ID',
    `content`     VARCHAR(255)                NOT NULL COMMENT '内容对应ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE favorite_table
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `p_id`        INT UNSIGNED                NOT NULL COMMENT '帖子ID',
    `u_id`        INT UNSIGNED                NOT NULL COMMENT '用户ID',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE TABLE post_content
(
    `id`          INT UNSIGNED AUTO_INCREMENT NOT NULL UNIQUE,
    `p_id`        INT UNSIGNED                NOT NULL COMMENT '帖子ID',
    `content`     TEXT                        NOT NULL COMMENT '内容富文本',
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
INSERT INTO college_table(college_name)
VALUES ('信息技术学院'),
       ('财贸学院'),
       ('应用外语学院'),
       ('体育学院'),
       ('马克思主义学院'),
       ('机电技术学院'),
       ('轻化工技术学院'),
       ('瓦特工坊学院'),
       ('管理学院'),
       ('历史学院'),
       ('嘉然学院');