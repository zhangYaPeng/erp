
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) DEFAULT '0' COMMENT '父节点',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源url',
  `icon` varchar(100) DEFAULT NULL COMMENT '资源图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序号',
  `resource_type` int(11) DEFAULT '1' COMMENT '资源类型，1：链接；2：按钮',
  `app_type` int(11) DEFAULT '1' COMMENT '资源所属应用，1：pc，2：移动端',
  `state` int(11) DEFAULT '1' COMMENT '状态：0：禁用；1：启用',
  `deleted` int(11) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限-资源表';

-- ----------------------------
--  Records of `sys_resource`
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource` VALUES ('1', '0', '权限设置', '', '&#xe63c;', '1', '1', '1', '1', '0', '2019-01-03 17:06:03', '2019-01-08 15:37:16', null), ('2', '1', '资源管理', '/back/resource/view/list', null, '1', '1', '1', '1', '0', '2019-01-03 17:08:15', '2019-01-03 17:08:18', null), ('3', '1', '角色管理', '/back/role/view/list', null, '2', '1', '1', '1', '0', '2019-01-03 17:09:05', '2019-01-03 17:09:07', null), ('4', '1', '用户管理', '/back/user/view/list', '', '3', '1', '1', '1', '0', '2019-01-03 17:09:38', '2019-01-08 16:37:32', null), ('5', '0', '个人中心', '', '&#xe61d;', '2', '1', '1', '1', '0', '2019-01-03 17:12:06', '2019-01-08 16:35:34', null), ('6', '5', '修改密码', '/back/password/view/update', null, '1', '1', '1', '1', '0', '2019-01-03 17:12:54', '2019-01-03 17:12:59', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_resource_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_role`;
CREATE TABLE `sys_resource_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `resource_id` int(11) NOT NULL DEFAULT '0' COMMENT '父节点',
  `role_id` int(11) NOT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_resourceid_roleid` (`resource_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限-资源角色表';

-- ----------------------------
--  Records of `sys_resource_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_resource_role` VALUES ('45', '1', '1'), ('46', '2', '1'), ('47', '3', '1'), ('48', '4', '1'), ('49', '5', '1'), ('50', '6', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role` varchar(100) DEFAULT NULL COMMENT '保留字段，用于程序判断',
  `state` int(11) DEFAULT '1' COMMENT '状态：0：禁用；1：启用',
  `deleted` int(11) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限-角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '', '1', '0', '2019-01-03 18:16:27', '2019-01-09 15:31:04', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色名称',
  `user_id` int(11) NOT NULL COMMENT '保留字段，用于程序判断',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限-角色用户表';

-- ----------------------------
--  Records of `sys_role_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实名字',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `pic` varchar(1024) DEFAULT NULL COMMENT '头像路径',
  `state` int(11) DEFAULT '1' COMMENT '状态：0：禁用；1：启用',
  `deleted` int(11) DEFAULT '0' COMMENT '是否删除：0：否；1：是',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限-后台用户';

-- ----------------------------
--  Records of `sys_user` 密码：1qaz2wsx
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', 'dc19358da7f265a295cb08a970026350', '超级管理员', '18501961674', null, '1', '0', '2019-01-02 11:37:46', '2019-01-02 11:37:51', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
