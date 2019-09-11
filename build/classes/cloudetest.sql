use cloudtest;

CREATE TABLE `department` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `departmentName` varchar(100) NOT NULL COMMENT '部门名称',
  `parentId` int(10) NOT NULL COMMENT '上一级ID',
  `level` tinyint(1) DEFAULT NULL COMMENT '1,2,3 三级',
  `listOrder` int(10) NOT NULL COMMENT '排序',
  `adduid` int(10) DEFAULT NULL,
  `createTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT department UNIQUE (departmentName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '工号',
  `avatar` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `emailCode` varchar(100) DEFAULT NULL,
  `password` varchar(100) NOT NULL COMMENT '密码',
  `username` varchar(40) DEFAULT NULL COMMENT '真实姓名',
  `departmentId` int(10) NOT NULL COMMENT '部门ID',
  `lastLoginTime` timestamp DEFAULT NULL COMMENT '最后登录时间戳',
  `freeze` tinyint(1) DEFAULT NULL COMMENT '0是默认,1是冻结',
  `adduid` int(10) DEFAULT NULL COMMENT '添加人',
  `createTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `users_department_fk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT users UNIQUE (account,email,emailCode,username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleName` varchar(100) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `adduid` int(10) DEFAULT NULL COMMENT '添加人',
  `createTime` timestamp DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT role UNIQUE (roleName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `users_role`(
	`userId` int(10) NOT NULL,
	`roleId` int(10) NOT NULL,
	PRIMARY KEY (`userId`,`roleId`),
	KEY `userId` (`userId`),
	CONSTRAINT `users_role_fk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`), 
	CONSTRAINT `users_role_fk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `permission`(
	`id` int(10) NOT NULL AUTO_INCREMENT,
	`permissionName` varchar(100) NOT NULL,
	`permissionDesc` varchar(200) NOT NULL,
	`types` tinyint(1) NOT NULL,
	`partentId` int(10) NOT NULL,
	`url` varchar(100) NOT NULL,
	`method` varchar(20) NOT NULL,
	`listorder` int(10) DEFAULT 0,
	`display` tinyint(1) NOT NULL,
	`iconname` varchar(50) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT permission UNIQUE (permissionName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role_permission`(
	`roleId` int(10) NOT NULL,
	`permissionId` int(10) NOT NULL,
	PRIMARY KEY (`roleId`,`permissionId`),
	CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`), 
	CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

