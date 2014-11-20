/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.50-community : Database - template
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`template` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `template`;

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `code` varchar(64) DEFAULT NULL COMMENT '唯一键',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` int(11) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '0' COMMENT '状态(0.正常 1.禁用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`code`,`icon`,`sort`,`pid`,`type`,`url`,`description`,`status`) values (1,'菜单配置',NULL,NULL,NULL,0,0,'menu',NULL,0),(10,'测试2ddddd',NULL,'',1,0,0,'ss','',0),(11,'测试3ddd',NULL,'',1,0,0,'','',0),(12,'测试4',NULL,'',1,0,0,'','',0),(13,'测试5',NULL,'',1,0,0,'','',0),(14,'测试6',NULL,'',1,0,0,'','',0),(15,'测试7',NULL,'',1,0,0,'','',0),(16,'测试8',NULL,'',1,0,0,'','',0),(17,'sdsd',NULL,'',1,0,0,'','',0),(18,'sdsd',NULL,'',1,0,0,'','',0),(19,'sds22',NULL,'',1,0,0,'','',0),(20,'ss',NULL,'',1,0,0,'','',0),(26,'df22',NULL,'',1,10,0,'','',0),(32,'1111111',NULL,'',1,10,0,'','',0),(33,'22222222',NULL,'',1,32,0,'','',0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(64) DEFAULT NULL COMMENT '唯一键',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `state` int(11) DEFAULT '0' COMMENT '状态(0.正常 1.禁用)',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_resource` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `code` varchar(64) DEFAULT NULL COMMENT '唯一键',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `city_id` varchar(32) DEFAULT NULL COMMENT '城市id',
  `province_id` varchar(32) DEFAULT NULL COMMENT '省id',
  `gender` char(2) DEFAULT NULL COMMENT '性别',
  `username` varchar(16) DEFAULT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `salt` varchar(64) DEFAULT NULL COMMENT '加密盐',
  `area` varchar(64) DEFAULT NULL COMMENT '地区字符串',
  `status` int(11) DEFAULT '0' COMMENT '账号状态(0.正常 1.禁用)',
  `name` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`code`,`age`,`phone`,`city_id`,`province_id`,`gender`,`username`,`password`,`regtime`,`salt`,`area`,`status`,`name`) values (1,NULL,NULL,NULL,NULL,NULL,NULL,'admin','c7122a1349c22cb3c009da3613d242ab',NULL,NULL,NULL,0,'韩馨');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

/* Function  structure for function  `getChildrenId` */

/*!50003 DROP FUNCTION IF EXISTS `getChildrenId` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getChildrenId`(rootId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);
SET sTemp = '$';
SET sTempChd = cast(rootId as char);
WHILE sTempChd is not NULL DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT group_concat(id) INTO sTempChd FROM sys_resource where FIND_IN_SET(pid,sTempChd)>0;
END WHILE;
return sTemp;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
