/*
MySQL Backup
Database: spring
Backup Time: 2019-06-06 13:46:59
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_config`;
DROP TABLE IF EXISTS `sys_depart_role`;
DROP TABLE IF EXISTS `sys_department`;
DROP TABLE IF EXISTS `sys_dict`;
DROP TABLE IF EXISTS `sys_file`;
DROP TABLE IF EXISTS `sys_i18n_messgae`;
DROP TABLE IF EXISTS `sys_menu`;
DROP TABLE IF EXISTS `sys_message`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_role_menu`;
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL,
  `conf_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `conf_key` varchar(50) COLLATE utf8_bin NOT NULL,
  `conf_value` varchar(500) COLLATE utf8_bin NOT NULL,
  `conf_type` int(10) NOT NULL,
  `status` int(10) NOT NULL,
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `create_user_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `sys_depart_role` (
  `id` int(11) NOT NULL,
  `depart_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `version_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`,`depart_id`),
  KEY `key_depart_role_id` (`depart_id`,`role_id`),
  CONSTRAINT `key_depart_role_id` FOREIGN KEY (`depart_id`, `role_id`) REFERENCES `sys_depart_role` (`role_id`, `depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门角色关联关系表';
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `status` int(10) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `leader` int(11) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  `version_no` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门表';
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL,
  `key_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '字典键值',
  `dict_value` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '字典值',
  `status` int(10) NOT NULL COMMENT '状态 0:已停用 1:正常',
  `category` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类别',
  `dict_type` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '字典类型',
  `sort_num` int(10) NOT NULL COMMENT '字典排序',
  `is_default` int(10) NOT NULL DEFAULT '0' COMMENT '是否为默认值 0:否 1:是',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_user_id` int(11) NOT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '文件名称',
  `file_size` int(50) NOT NULL DEFAULT '0' COMMENT '文件大小 以B为单位',
  `biz_type` int(11) NOT NULL DEFAULT '0' COMMENT '文件类型   1:图片  2:音频  3:视频',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0:停用  1:启用',
  `path` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户\n',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `thumbnail_path` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图路径',
  `linked_url` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '外链',
  `is_banner` int(11) DEFAULT '0' COMMENT '是否是轮播图  0:否, 1:是',
  `version_no` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_file_id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='文件表';
CREATE TABLE `sys_i18n_messgae` (
  `id` int(11) NOT NULL,
  `key` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '键名称',
  `context` varchar(4000) COLLATE utf8_bin NOT NULL COMMENT '文本',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 0:未生效 1:已生效',
  `remark` varchar(4000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `domain` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '域值',
  `create_time` datetime DEFAULT NULL,
  `create_user_id` int(11) NOT NULL,
  `version_no` int(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `search_text` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '搜索文本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统消息表';
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `path` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单路径',
  `status` int(10) NOT NULL COMMENT '状态 0 已停用 1 正常',
  `parent_id` int(11) NOT NULL COMMENT '菜单父id',
  `menu_type` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '菜单类型 M 菜单，D 目录  B 按钮',
  `visible` int(1) NOT NULL COMMENT '是否可见 0 不可见 1 可见',
  `sort_num` int(10) NOT NULL COMMENT '排序字段',
  `permission` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限字符',
  `icon` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `version_no` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';
CREATE TABLE `sys_message` (
  `id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `context` varchar(4000) COLLATE utf8_bin NOT NULL COMMENT '内容',
  `remark` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '发送状态  SUCCESS:发送成功，FAIL:发送失败，SENDING:发送中，SENDED:已送达',
  `biz_type` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '业务类型：ANNOUNCE 公告，REMIND 提醒，MESSAGE 私信',
  `type` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '消息类型：SMS: 短信，MAIL:邮件  NOTICE:站内信',
  `linked_url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '外链',
  `to_user` int(11) DEFAULT NULL COMMENT '接收消息的用户',
  `mongo_obj_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '关联的mongoDB中的id',
  `create_user_id` int(11) NOT NULL COMMENT '发送用户',
  `version_no` int(10) NOT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消息表';
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL COMMENT '角色id',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `status` int(11) NOT NULL COMMENT '状态 0:停用,1:启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `meun_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`meun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `is_admin` int(11) DEFAULT '0' COMMENT '是否是管理员  0:不是  1:是',
  `salt` varchar(10) NOT NULL COMMENT '随机盐',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态 0:停用  1:正常  2:已删除',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0:男 1:女',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `version_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户表';
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user_id` int(11) DEFAULT NULL,
  `version_no` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`role_id`),
  KEY `key_role_user_id` (`role_id`,`user_id`),
  CONSTRAINT `key_role_user_id` FOREIGN KEY (`role_id`, `user_id`) REFERENCES `sys_user_role` (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关联表';
BEGIN;
LOCK TABLES `sys_config` WRITE;
DELETE FROM `sys_config`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_depart_role` WRITE;
DELETE FROM `sys_depart_role`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_department` WRITE;
DELETE FROM `sys_department`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_dict` WRITE;
DELETE FROM `sys_dict`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_file` WRITE;
DELETE FROM `sys_file`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_i18n_messgae` WRITE;
DELETE FROM `sys_i18n_messgae`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_menu` WRITE;
DELETE FROM `sys_menu`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_message` WRITE;
DELETE FROM `sys_message`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_role` WRITE;
DELETE FROM `sys_role`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_role_menu` WRITE;
DELETE FROM `sys_role_menu`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_user` WRITE;
DELETE FROM `sys_user`;
INSERT INTO `sys_user` (`id`,`name`,`password`,`is_admin`,`salt`,`status`,`sex`,`email`,`phone`,`description`,`create_time`,`update_time`,`update_user_id`,`create_user_id`,`version_no`) VALUES (0, 'syler', '123456', 1, 'ec1aa70a3e', 1, 1, NULL, NULL, '系统管理员', NULL, NULL, NULL, NULL, NULL),(9, 'syler', 'syler2', 0, '', 0, 1, NULL, NULL, '描述description', NULL, NULL, NULL, NULL, NULL),(10, 'wudi1', '234567', 0, '', 0, 1, NULL, NULL, '描述444', NULL, NULL, NULL, NULL, NULL),(11, 'wudi', '789', 0, '', 0, 1, NULL, NULL, '描述1', NULL, NULL, NULL, NULL, NULL),(12, 'wudi', '321456', 0, '', 0, 1, NULL, NULL, '描述测试', NULL, NULL, NULL, NULL, NULL),(13, 'wudi', '123456', 0, '', 0, 1, NULL, NULL, '描述1', NULL, NULL, NULL, NULL, NULL),(14, 'wudi', '123456', 0, '', 0, 1, NULL, NULL, '描述1', NULL, NULL, NULL, NULL, NULL),(15, 'syler', '123456', 0, '', 0, 1, NULL, NULL, '描述1', NULL, NULL, NULL, NULL, NULL);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `sys_user_role` WRITE;
DELETE FROM `sys_user_role`;
UNLOCK TABLES;
COMMIT;