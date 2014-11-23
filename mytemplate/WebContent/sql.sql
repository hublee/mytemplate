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

/*Table structure for table `sys_area` */

DROP TABLE IF EXISTS `sys_area`;

CREATE TABLE `sys_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_parent_ids` (`parent_ids`(255)),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='区域表';

/*Data for the table `sys_area` */

insert  into `sys_area`(`id`,`parent_id`,`parent_ids`,`code`,`name`,`type`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,'0','0,','100000','中国','1','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(2,'1','0,1,','110000','北京市','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(3,'2','0,1,2,','110101','东城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,'2','0,1,2,','110102','西城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,'2','0,1,2,','110103','朝阳区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,'2','0,1,2,','110104','丰台区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(7,'2','0,1,2,','110105','海淀区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(8,'1','0,1,','370000','山东省','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(9,'8','0,1,2,','370531','济南市','3','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(10,'8','0,1,2,','370532','历城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(11,'8','0,1,2,','370533','历城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(12,'8','0,1,2,','370534','历下区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(13,'8','0,1,2,','370535','天桥区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(14,'8','0,1,2,','370536','槐荫区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(83,'14','0,1,2,14,','','00','3','1','2014-10-05 17:29:10','1','2014-10-05 17:29:10','','0');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `pos` bigint(20) DEFAULT NULL COMMENT '权限位,相当于对权限分组,从0开始',
  `common` int(11) DEFAULT '0' COMMENT '是否是公共资源(0.不是 1.是)',
  `code` bigint(20) DEFAULT NULL COMMENT '权限码 1<<n',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序号',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` int(11) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '0' COMMENT '状态(0.正常 1.禁用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`pos`,`common`,`code`,`icon`,`sort`,`pid`,`type`,`url`,`description`,`status`) values (1,'菜单配置',0,0,1,'fa-caret-right',11,0,0,'menu','',0),(75,'机构用户',0,0,2,'',1,0,0,'','',0),(76,'区域管理',0,0,4,'',1,75,0,'area','',0);

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

insert  into `sys_user`(`id`,`code`,`age`,`phone`,`city_id`,`province_id`,`gender`,`username`,`password`,`regtime`,`salt`,`area`,`status`,`name`) values (1,NULL,NULL,NULL,NULL,NULL,NULL,'admin','86f3059b228c8acf99e69734b6bb32cc',NULL,NULL,NULL,0,'韩馨');

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
