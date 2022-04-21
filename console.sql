### 数据库设计
###只包含核心的blog type user三张表 其他表暂未设置
/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : blog
 Date: 10/04/2021 10:35:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE vueblog;
use vueblog;
-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`
(
    `id`            bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `title`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Tweets Title',
    `first_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      DEFAULT NULL COMMENT 'First Picture of Tweets, it aims to show random tweets',

    `description`   longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT 'Description',
    `content`       longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT 'Main Body of Tweets',

    `create_time`   datetime(0)                                                   NOT NULL COMMENT 'Time of Creating',
    `update_time`   datetime(0)                                                   NOT NULL COMMENT 'Time of Updating',
    `views`         int(0)                                                        NOT NULL COMMENT 'Frequency of Browse',
    `words`         int(0)                                                        NOT NULL COMMENT 'Word Count',

    `type_id`       bigint(20)                                                    NOT NULL COMMENT 'Tweets Classification ID',
    `user_id`       bigint(20)                                                    NULL DEFAULT NULL COMMENT 'Author_id',
    `status`        tinyint(4)                                                         DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;
INSERT INTO `blog`
VALUES ('1', 'It is about me!!', 'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg', 'guanyuuwp',
        'If you need my information?', '2020-05-22 22:05:49', '2020-05-22 22:05:49', '32', '10', '1', '1', '1');
INSERT INTO `blog`
VALUES ('2', 'Link Exchange', 'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg', 'guanyuuwp',
        'It is about my personal information.', '2020-05-22 22:05:49', '2020-05-22 22:05:49', '32', '10', '1', '1', '1');

INSERT INTO `blog`
VALUES ('5', 'Life is a ocean, only the immovable people could be successful.', '', 'This is the abstract, hey.', 'The content???', '2020-05-21 22:08:42', '2020-05-21 22:08:42', '100',
        '10', '2', '1', '0');
INSERT INTO `blog`
VALUES ('3', 'The most worthy blog project: eblog', 'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg',
        'eblog is a blog studying project which is based on Springboot2.1.2. It aims to make the project more complex that include more knowledge and achieve the aim of studying, which edit this paper of exploring project. The paper includes: self-design Freemarker label, apply shiro+redis to achieve dialogue sharing, redis zset structure to finish the heated debate of this week, t-io+websocket to notify every group in time, rabbitmq+elasticsearch to complete the searching function in blog. There are still so many places to explore!',
        '**Recommend Reading：**\r\n\r\n[Share the source code of developing SpringBoot blog system, and the comprehensive development handbook. Please save it quickly!](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\r\n\r\n[The most 100 valuable Java projects in Github with source code, which includes every technique stack!](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\r\n\r\n[2020 The newest version sloution of company face exam](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)',
        '2020-05-28 09:36:38', '2020-05-28 09:36:38', '111', '10', '1', '1', '0');
INSERT INTO `blog`
VALUES ('4', 'Please subscribe JavaCat official account, reply xshell or navicat to get the corresponding tools',
        'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg',
        'The xshell and navicat could be get directly in this video',
        '### Get the tools\r\n\r\n* xshell 6 free version：Subscribe the official acount：JavaCat, reply xshell to get\r\n* Navicat 11 Chinese version: subscribe official account：JavaCat, reply navicat to get\r\n\r\n official account QR code: \r\n\r\n![JavaCat](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20201020/7fa16a1f957f4cfebe7be1f6675f6f36.png \"JavaCat\")\r\n\r\nScan the QR code directly and reply\r\n\r\n**Recommend reading: **\r\n\r\n[86K watch in Bilibili, comprehensive guidance about SpringBoot+Vue which is seperately](https://mp.weixin.qq.com/s/jGEkHTf2X8l-wUenc-PpEw)\r\n\r\n[Share the source code of SpringBoot development project, and the whole handbook! Please save it](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\r\n\r\n[The most avalable Java projects in Github with source code, includes every technique stacks](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\r\n\r\n[2020 the newest version solutions about exam](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)',
        '2020-10-20 05:05:31', '2020-10-20 05:05:31', '101', '11', '1', '1', '0');
INSERT INTO `blog`
VALUES ('7', 'Do you really master the Singleton Pattern?', 'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg',
        'The Singleton Pattern is the smallest number of code, but it does not mean that is the simplest. If we want to use Singleton Pattern well, we need to spend a lot of time. This paper will summarize the common Singleton Patterns in Java. If it has erros, please correct it.',
        '> Author：The lion eat orange source: http://www.tekbroaden.com/singleton-java.html\n\n\nThe Singleton Pattern is the smallest number of code, but it does not mean that is the simplest. If we want to use Singleton Pattern well, we need to spend a lot of time. \n\neagerly way\n===\n\n By definition, it is the first time to create the object. The code is here：\n\n```\npublic class Singleton {  \n    private static Singleton = new Singleton();\n    private Singleton() {}\n    public static getSignleton(){\n        return singleton;\n    }\n}\n\n```\n\nThe advantage are easy to edit, but it cannot postpone to create object. But we hope that we the object could delay loading, so we need the way below:  \n',
        '2020-05-22 00:42:44', '2020-05-22 00:42:44', '10', '324', '1', '1', '0');
INSERT INTO `blog`
VALUES ('9', 'Understand the four types of isolation of Mysql@', '',
        'Transactions are a series of operations in application. All the operations could be finished, else the editing operation will be cancelled. This is called the atomicity of transaction. All the operations in a transaction need to be finished or cancelled. \n\nThere are types of completement of transction, when the operations in transaction are finished, it will be uploaded. If one of the steps are fail, it will start the roll operation, which is return all the steps. ',
        '### What is transaction  \n\n> Transactions are a series of operations in application. All the operations could be finished, else the editing operation will be cancelled. This is called the atomicity of transaction. This is the atomicity. All the operations in a transaction need to be finished or cancelled. \n> \n> There are two types of completement of transactions, when the operations in transaction are finished, it will be uploaded.  \n\n**Transaction ACID**\n\nFour characteristics: Atomicity, Consistency, Isolation, Durability. \n\n> 1, Atomicity: transactions are unit of SQL logic, which include the do and do not. \n> \n> 2, Consistency: the execution of transaction should make the state from one to another consistent state. Thus, the SQL are always in consistent state. If there exists errors in database, all the edit will be executed in physical memory. Thus, the current database is in incorrect state, or the inconsistent state. ',
        '2020-05-22 22:04:46', '2020-05-22 22:04:46', '323', '101', '1', '1', '0');
INSERT INTO `blog`
VALUES (null, 'Blog project eblog guidance video was uploaded. It is about 17 hours! ',
        'https://cdn.jsdelivr.net/gh/Naccl/blog-resource/blogHosting/2020/12/B01/001.jpg',
        '1. There is no update in Mook. Almost all the new sources need to be paied.\n2. There are so many videos in Bilibili. We could judge that whether the video is valuable according to the number of collection.\n3. Actually, I do not study it by myself about the Bilibili culture. Hahahaha! \n4. Bilibili videos have less advertisement. And up videoer work hard about it. ',
        'ok, let back to the eblog project, source code, paper and video, which are visible now. Please star it in Github and subscribe it in Bilibili. \n\neblog source code: https://github.com/MarkerHub/eblog\n\nclick here: [10 comprehensive development papers](https://mp.weixin.qq.com/mp/homepage?__biz=MzIwODkzOTc1MQ==&hid=1&sn=8e512316c3dfe140e636d0c996951166)\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/c290d945b7d24c79b172759bdb5b94e0.png)\n\nGuidance video:(Please subscribe me)\n\nhttps://www.bilibili.com/video/BV1ri4y1x71A\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/983b5abc1c934360a1a1362347a275f7.png)\n\nThere still have bugs. I need to edit it some places. \n\nSubscribe my Bilibili account, I am happy if you have done that. Hahaha! \n\nThere are new videos: \n\n1. Build the front and behind end. \n2. Shiro guidance video. \n3. SpringBoot2.2.6 the newest guidance',
        '2020-05-22 22:05:49', '2020-05-22 22:05:49', '32', '10', '1', '1', '0');


-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
    `id`        bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `type`
VALUES ('1', 'Technique Blogs');
INSERT INTO `type`
VALUES ('2', 'Interview');
INSERT INTO `type`
VALUES ('3', 'Random Writting');
INSERT INTO `type`
VALUES ('4', 'Note Taking');


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint(0)                                                     NOT NULL AUTO_INCREMENT,
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'User Name',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Password',
    `nickname`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Nickname',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Portrait',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Mail',
    `status`      int(5)                                                        NOT NULL,
    `create_time` datetime(0)                                                   NOT NULL COMMENT 'Create Time',
    `update_time` datetime(0)                                                   NOT NULL COMMENT 'Update Time',

    `role`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Access Authority',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `user`
VALUES ('1', 'root', '96e79218965eb72c92a549dd5a330112', 'root',
        'https://img.lanrentuku.com/img/allimg/1612/14831720501619.jpg', 'admin@naccl.top', '1', '2020-04-20 10:44:01',
        '2020-04-20 10:44:01', 'role_root');
INSERT INTO `user`
VALUES ('2', 'admin', '96e79218965eb72c92a549dd5a330112', 'Admin',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        'admin@naccl.top', '1', '2020-04-20 10:44:01', '2020-04-20 10:44:01', 'role_admin');
INSERT INTO `user`
VALUES ('3', 'user', '96e79218965eb72c92a549dd5a330112', 'User',
        'https://img.lanrentuku.com/img/allimg/1612/14831720501619.jpg', 'admin@naccl.top', '1', '2020-04-20 10:44:01',
        '2020-04-20 10:44:01', 'role_user');

INSERT INTO `user`
VALUES ('4', 'Visitor', '96e79218965eb72c92a549dd5a330112', 'vistor',
        'https://img.lanrentuku.com/img/allimg/1612/14831720501619.jpg', 'admin@naccl.top', '1', '2020-04-20 10:44:01',
        '2020-04-20 10:44:01', 'role_guest');
-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`                      bigint(0)                                                     NOT NULL AUTO_INCREMENT,
    `nickname`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Nickname',
    `email`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Mail',
    `content`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Comment',
    `avatar`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Portrait',
    `create_time`             datetime(0)                                                   NULL DEFAULT NULL COMMENT 'Comment Time',
    `ip`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Comment ip Address',
    `is_admin_comment`        int(1)                                                        NOT NULL COMMENT 'Author Reply',
    `is_published`            bit(1)                                                        NOT NULL DEFAULT TRUE COMMENT 'Public or Private',
    `blog_id`                 bigint(255)                                                   NULL DEFAULT NULL COMMENT 'Paper Belonging',
    `parent_comment_id`       bigint(0)                                                     NULL DEFAULT NULL COMMENT 'Parent Comment id，-1Root Comment',
    `website`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Personal Web',
    `parent_comment_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Nickname reply',
    `qq`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'If the comment nickname is QQ account, we need to make it to QQ account and back-up in QQ. ',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

INSERT INTO `comment`
VALUES ('1', 'Mingyu', '8949048964@qq.com', 'Test1',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-22 22:05:49', '101,2,2,1', '0',TRUE, '19', '-1', '32', 'Edwin Van der Sar', '894904866');
INSERT INTO `comment`
VALUES ('2', 'Mingyu', '8949048964@qq.com', 'Test11',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-23 22:05:49', '101,2,2,1', '1', TRUE,'19', '1', '32', 'Edwin Van der Sar', '894904866');
INSERT INTO `comment`
VALUES ('3', 'Mingyu', '8949048964@qq.com', 'Test12',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-24 22:05:49', '101,2,2,1', '0', TRUE,'19', '1', '32', 'Edwin Van der Sar', '894904866');
INSERT INTO `comment`
VALUES ('4', 'Mingyu', '8949048964@qq.com', 'Test2',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-25 22:05:49', '101,2,2,1', '1',TRUE, '19', '-1', '32', 'Edwin Van der Sar', '894904866');
INSERT INTO `comment`
VALUES ('5', 'Mingyu', '8949048964@qq.com', 'Test21',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-26 22:05:49', '101,2,2,1', '0',TRUE, '19', '4', '32', 'Edwin Van der Sar', '894904866');
INSERT INTO `comment`
VALUES ('6', 'Mingyu', '8949048964@qq.com', 'Test3',
        'https://hbimg.huabanimg.com/8c7e98227a291c52e3951931b7a07158eec2acda27a8b-87wdWD_fw658/format/webp',
        '2020-05-27 22:05:49', '101,2,2,1', '1', TRUE,'19', '-1', '32', 'Edwin Van der Sar', '894904866');


# -- Table structure for visit_log
# -- ----------------------------
# DROP TABLE IF EXISTS `visit_log`;
# CREATE TABLE `visit_log`  (
#                               `id` bigint(0) NOT NULL AUTO_INCREMENT,
#                               `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Port',
#                               `class_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request method',
#                               `args` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Parameter',
#                               `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
#                               `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Source IP',
#                               `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Operate System',
#                               `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Browser',
#                               `create_time` datetime(0) NOT NULL COMMENT 'Visit Time',
#                               `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
#                               PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
#




-- ----------------------------
-- Table structure for visit_log
-- ----------------------------
DROP TABLE IF EXISTS `visit_log`;
CREATE TABLE `visit_log`  (
                              `id` bigint(0) NOT NULL AUTO_INCREMENT,
                              `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visitor Identification Code',
                              `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Code',
                              `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Method',
                              `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Parameters',
                              `behavior` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visit Behavior',
                              `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visit Content',
                              `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remark',
                              `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
                              `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Source IP',
                              `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Operate System',
                              `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Browser',
                              `times` int(0) NOT NULL COMMENT 'Request Times (ms)',
                              `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'Visit Time',
                              `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

# -- ----------------------------
# -- Table structure for friend
# -- ----------------------------
# DROP TABLE IF EXISTS `friend`;
# CREATE TABLE `friend`  (
#                            `id` bigint(0) NOT NULL AUTO_INCREMENT,
#                            `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Nickname',
#                            `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Statement',
#                            `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'site',
#                            `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Portrait',
#                            `is_published` bit(1) NOT NULL COMMENT 'Public or Secret',
#                            `views` int(0) NULL DEFAULT NULL  COMMENT 'Click Times',
#                            `create_time` datetime(0) NOT NULL COMMENT 'Create Time',
#                            PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
                           `id` bigint(0) NOT NULL AUTO_INCREMENT,
                           `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Nickname',
                           `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Statement',
                           `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'site',
                           `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Portrait',
                           `is_published` bit(1) NOT NULL DEFAULT 1 COMMENT 'Public or Secret',
                           `views` int(0) NOT NULL DEFAULT 0 COMMENT 'Click Times',
                           `create_time` datetime(0)  NULL  DEFAULT NULL COMMENT 'Create Time',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



INSERT INTO `friend` VALUES ('1','SuiNian\'s Blog','When you simile to me,  I am so caught up in this','https://blog.nianbroken.top','https://blog.nianbroken.top/favicon.png',1,'0','2020-05-05 08:05:49');
INSERT INTO `friend` VALUES ('2','Listen so carefully','Record, feel, express','https://blog.tdrme.cn','https://cdn.jsdelivr.net/gh/tdrme/tdrme.github.io@master/20210504090204404.png',1,'0','2020-05-05 08:05:49');

INSERT INTO `friend` VALUES ('3','Sunset personal Tweet','When KobeBryant died,a piece of me died.','http://www.twilightjy.com/','https://cdn.jsdelivr.net/gh/yubifeng/blog-resource/bloghosting/website/friend/myavatar.jpg',1,'0','2020-05-05 08:05:49');

INSERT INTO `friend` VALUES ('4','Mountain','At the age of difficulty. Shape is broken','http://www.lemjuice.cn/','http://www.lemjuice.cn/00000147.png',1,'0','2020-05-05 08:05:49');

INSERT INTO `friend` VALUES ('5','ZhaoQuinn Blog','Love technology, love lives.','https://zhaoq.me','https://zhaoq.me/usr/uploads/2020/03/2395074381.jpg',1,'0','2020-05-05 08:05:49');



-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor`  (
                            `id` bigint(0) NOT NULL AUTO_INCREMENT,
                            `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Visitor Identification Code',
                            `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
                            `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Source IP',
                            `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Operate System',
                            `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Browser',
                            `create_time` datetime(0) NOT NULL COMMENT 'First Visit Time',
                            `last_time` datetime(0) NOT NULL COMMENT 'Last Visit Time',
                            `pv` int(0) NULL DEFAULT NULL COMMENT 'Visit Page',
                            `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;










# -- Table structure for blog_tag
# -- ----------------------------
# DROP TABLE IF EXISTS `blog_tag`;
# CREATE TABLE `blog_tag`  (
#                              `blog_id` bigint(20) NOT NULL,
#                              `tag_id` bigint(20) NOT NULL
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
# INSERT INTO `blog_tag` VALUES ('1','2');
#
# INSERT INTO `blog_tag` VALUES ('1','3');
# INSERT INTO `blog_tag` VALUES ('2','3');
# INSERT INTO `blog_tag` VALUES ('3','3');
# INSERT INTO `blog_tag` VALUES ('7','3');
# INSERT INTO `blog_tag` VALUES ('9','3');
# INSERT INTO `blog_tag` VALUES ('10','3');
#
#
# -- ----------------------------
# -- Table structure for tag
# -- ----------------------------
# DROP TABLE IF EXISTS `tag`;
# CREATE TABLE `tag`  (
#                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
#                         `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
#                         PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
# INSERT INTO `tag` VALUES ('1','python');
# INSERT INTO `tag` VALUES ('2','c++ ');
# INSERT INTO `tag` VALUES ('3','spring');

# -- ----------------------------
# -- Table structure for site_setting
# -- ----------------------------
# DROP TABLE IF EXISTS `site_setting`;
# CREATE TABLE `site_setting`  (
#                                  `id` bigint(0) NOT NULL AUTO_INCREMENT,
#                                  `name_en` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#                                  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#                                  `value` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
#                                  `type` int(0) NULL DEFAULT NULL COMMENT '1Foundamental Setting, 2page footer, 3data card, 4Friends Information',
#                                  PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
# INSERT INTO `site_setting` VALUES (1, 'webTitleSuffix', 'Suffix of Web Title', ' - Naccl\'s Blog', 1);
# INSERT INTO `site_setting` VALUES (2, 'blogName', 'Blog Name', 'Naccl\'s Blog', 1);
# INSERT INTO `site_setting` VALUES (3, 'footerImgTitle', 'Title of Footer', 'Phone', 1);
# INSERT INTO `site_setting` VALUES (4, 'footerImgUrl', 'URL Picture', '/img/qr.png', 1);
# INSERT INTO `site_setting` VALUES (5, 'copyright', 'Copyright', '{\"title\":\"Copyright © 2019 - 2020\",\"siteName\":\"NACCL\'S BLOG\"}', 1);
# INSERT INTO `site_setting` VALUES (6, 'beian', 'ICP', '', 1);
# INSERT INTO `site_setting` VALUES (7, 'badge', 'logos', '{\"title\":\"Spring Boot Powered\",\"url\":\"https://spring.io/projects/spring-boot/\",\"subject\":\"Powered\",\"value\":\"Spring Boot\",\"color\":\"blue\"}', 2);
# INSERT INTO `site_setting` VALUES (8, 'badge', 'logos', '{\"title\":\"Vue.js Client - Side rendering\",\"url\":\"https://cn.vuejs.org/\",\"subject\":\"SPA\",\"value\":\"Vue.js\",\"color\":\"brightgreen\"}', 2);
# INSERT INTO `site_setting` VALUES (9, 'badge', 'logos', '{\"title\":\"UI Framework Semantic-UI\",\"url\":\"https://semantic-ui.com/\",\"subject\":\"UI\",\"value\":\"Semantic-UI\",\"color\":\"semantic-ui\"}', 2);
# INSERT INTO `site_setting` VALUES (10, 'badge', 'logos', '{\"title\":\"AliYun Provide Servers of service\",\"url\":\"https://www.aliyun.com/\",\"subject\":\"VPS & DNS\",\"value\":\"Aliyun\",\"color\":\"blueviolet\"}', 2);
# INSERT INTO `site_setting` VALUES (11, 'badge', 'logos', '{\"title\":\"jsDelivr provide CDN service\",\"url\":\"https://www.jsdelivr.com/\",\"subject\":\"CDN\",\"value\":\"jsDelivr\",\"color\":\"orange\"}', 2);
# INSERT INTO `site_setting` VALUES (12, 'badge', 'logos', '{\"title\":\"GitHub provide service\",\"url\":\"https://github.com/\",\"subject\":\"OSS\",\"value\":\"GitHub\",\"color\":\"github\"}', 2);
# INSERT INTO `site_setting` VALUES (13, 'badge', 'logos', '{\"title\":\"CC BY 4.0 International license agreement for licensing\",\"url\":\"https://creativecommons.org/licenses/by/4.0/\",\"subject\":\"CC\",\"value\":\"BY 4.0\",\"color\":\"lightgray\"}', 2);
# INSERT INTO `site_setting` VALUES (14, 'avatar', 'picture path', '/img/avatar.jpg', 3);
# INSERT INTO `site_setting` VALUES (15, 'name', 'Nickname', 'Naccl', 3);
# INSERT INTO `site_setting` VALUES (16, 'rollText', 'roll', '\"Brilliance over seas; \",\"Brilinance over seas. \"', 3);
# INSERT INTO `site_setting` VALUES (17, 'github', 'GitHub path', 'https://github.com/Naccl', 3);
# INSERT INTO `site_setting` VALUES (18, 'qq', 'QQ link', 'http://sighttp.qq.com/authd?IDKEY=', 3);
# INSERT INTO `site_setting` VALUES (19, 'bilibili', 'bilibili link', 'https://space.bilibili.com/', 3);
# INSERT INTO `site_setting` VALUES (20, 'netease', 'Wangyi Music', 'https://music.163.com/#/user/home?id=', 3);
# INSERT INTO `site_setting` VALUES (21, 'email', 'email', 'mailto:i@naccl.top', 3);
# INSERT INTO `site_setting` VALUES (22, 'favorite', 'DIY', '{\"title\":\"favorite Cartoon📺\",\"content\":\"ID:INVADED, NO GAME NO LIFE, Classroom of the Elite, Kaguya-sama: Love Is War, Rascal Does Not Dream of a Dreaming Girl, Re0, Haven't You Heard? I'm Sakamoto, OVERLORD, Scum's Wish, Death Note, DARLING in the FRANXX, Demon Slayer: Kimetsu no Yaiba\"}', 3);
# INSERT INTO `site_setting` VALUES (23, 'favorite', 'DIY', '{\"title\":\"favorite Girls 🤤\",\"content\":\"Frenda, Umaru, Shokuhou Misaki, Saten Ruiko, Sakurajima Mai, Kirisaki Chitoge, 02, Asuna, Kousaka kirino, Gokou Ruri, Yasuraoka Hanabi, Isshiki Iroha, Tenma Gabriel White \"}', 3);
# INSERT INTO `site_setting` VALUES (24, 'favorite', 'DIY', '{\"title\":\"favorite games 🎮\",\"content\":\"Stellaris, GTA5, A Fistful Of Dollars, Assassin's Creed, Warcraft, LOL, PUBG\"}', 3);
# INSERT INTO `site_setting` VALUES (25, 'reward', 'reward path', '/img/reward.jpg', 1);
# INSERT INTO `site_setting` VALUES (26, 'commentAdminFlag', 'Bloger comment', 'gugu', 1);
# INSERT INTO `site_setting` VALUES (27, 'friendContent', 'friend conten information', 'Random order with no limitations. Welcome to exchange~(￣▽￣)~*\n\n* Nickname: Naccl\n* one sentence: Brilliance over seas. \n* Webpage: [https://naccl.top](https://naccl.top)\n* portrait URL：[https://naccl.top/img/avatar.jpg](https://naccl.top/img/avatar.jpg)\n\nBased on personal hobby to add friend, and please reply to me to add link. And the content will not be reomved. \n\n', 4);
# INSERT INTO `site_setting` VALUES (28, 'friendCommentEnabled', 'Comment Switch', '1', 4);
#
#

#
#
# -- ----------------------------
# -- Table structure for visit_log
# -- ----------------------------
# DROP TABLE IF EXISTS `visit_log`;
# CREATE TABLE `visit_log`  (
#                               `id` bigint(0) NOT NULL AUTO_INCREMENT,
#                               `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visitor Identification Code',
#                               `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Code',
#                               `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Method',
#                               `param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Request Parameters',
#                               `behavior` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visit Behavior',
#                               `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Visit Content',
#                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Remarks',
#                               `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
#                               `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Source IP',
#                               `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Operate System',
#                               `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Browser',
#                               `times` int(0) NOT NULL COMMENT 'Request Time',
#                               `create_time` datetime(0) NOT NULL COMMENT 'Visit Time',
#                               `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
#                               PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
#
# -- ----------------------------
# -- Table structure for visitor
# -- ----------------------------
# DROP TABLE IF EXISTS `visitor`;
# CREATE TABLE `visitor`  (
#                             `id` bigint(0) NOT NULL AUTO_INCREMENT,
#                             `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Visitor Identification Code',
#                             `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
#                             `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Source IP',
#                             `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Operate System',
#                             `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Browser',
#                             `create_time` datetime(0) NOT NULL COMMENT 'First Visit Time',
#                             `last_time` datetime(0) NOT NULL COMMENT 'Last Visit Time',
#                             `pv` int(0) NULL DEFAULT NULL COMMENT 'Visit Page',
#                             `user_agent` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'User Agent',
#                             PRIMARY KEY (`id`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;









# INSERT INTO `friend` VALUES ('1','SuiNian\'s Blog','When you simile to me,  I am so caught up in this','https://blog.nianbroken.top','https://blog.nianbroken.top/icon-fa-gem.png',1,'0','2020-05-05 08:05:49');
# INSERT INTO `friend` VALUES ('2','SuiNian\'s Blog','When you simile to me,  I am so caught up in this','https://blog.nianbroken.top','https://blog.nianbroken.top/icon-fa-gem.png',1,'0','2020-05-05 08:05:49');
# INSERT INTO `friend` VALUES ('3','Listen so carefully','Record, feel, express','https://blog.tdrme.cn','https://cdn.jsdelivr.net/gh/tdrme/tdrme.github.io@master/20210504090204404.png',1,'0','2020-05-05 08:05:49');
# INSERT INTO `friend` VALUES ('4','Listen so carefully','Record, feel, express','https://blog.tdrme.cn','https://cdn.jsdelivr.net/gh/tdrme/tdrme.github.io@master/20210504090204404.png',1,'0','2020-05-05 08:05:49');

