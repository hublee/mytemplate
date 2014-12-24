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
  `parent_id` bigint(20) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_parent_ids` (`parent_ids`(255)),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='区域表';

/*Data for the table `sys_area` */

insert  into `sys_area`(`id`,`parent_id`,`parent_ids`,`code`,`name`,`type`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,0,'0,','100000','中国','1','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(2,1,'0,1,','110000','北京市','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(3,2,'0,1,2,','110101','东城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,2,'0,1,2,','110102','西城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,2,'0,1,2,','110103','朝阳区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,2,'0,1,2,','110104','丰台区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(7,2,'0,1,2,','110105','海淀区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(8,1,'0,1,','370000','山东省','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(9,8,'0,1,8,','370531','济南市','3','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(10,9,'0,1,8,9,','370532','高新区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(11,9,'0,1,8,9,','370533','历城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(12,9,'0,1,8,9,','370534','历下区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(13,9,'0,1,8,9,','370535','天桥区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(14,9,'0,1,8,9,','370536','槐荫区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`label`,`value`,`type`,`description`,`sort`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,'正常','0','del_flag','删除标记',10,'1','2013-05-27 08:00:00','1','2014-12-20 11:51:01',NULL,'0'),(2,'删除','1','del_flag','删除标记',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(3,'显示','1','show_hide','显示/隐藏',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,'隐藏','0','show_hide','显示/隐藏',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,'是','1','yes_no','是/否',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,'否','0','yes_no','是/否',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(17,'国家','1','sys_area_type','区域类型',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(18,'省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(19,'地市','3','sys_area_type','区域类型',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(20,'区县','4','sys_area_type','区域类型',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(21,'公司','1','sys_office_type','机构类型',60,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(22,'部门','2','sys_office_type','机构类型',70,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(23,'一级','1','sys_office_grade','机构等级',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(24,'二级','2','sys_office_grade','机构等级',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(25,'三级','3','sys_office_grade','机构等级',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(26,'四级','4','sys_office_grade','机构等级',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(27,'所有数据','1','sys_data_scope','数据范围',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(28,'所在公司及以下数据','2','sys_data_scope','数据范围',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(29,'所在公司数据','3','sys_data_scope','数据范围',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(30,'所在部门及以下数据','4','sys_data_scope','数据范围',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(31,'所在部门数据','5','sys_data_scope','数据范围',50,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(32,'仅本人数据','8','sys_data_scope','数据范围',90,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(33,'按明细设置','9','sys_data_scope','数据范围',100,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(34,'系统管理','1','sys_user_type','用户类型',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(35,'部门经理','2','sys_user_type','用户类型',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(36,'普通用户','3','sys_user_type','用户类型',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(62,'接入日志','1','sys_log_type','日志类型',30,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(63,'异常日志','2','sys_log_type','日志类型',40,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(64,'单表增删改查','single','prj_template_type','代码模板',10,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(65,'所有entity和dao','entityAndDao','prj_template_type','代码模板',20,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0');

/*Table structure for table `sys_office` */

DROP TABLE IF EXISTS `sys_office`;

CREATE TABLE `sys_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(20) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `area_id` bigint(20) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) DEFAULT NULL COMMENT '机构类型',
  `grade` char(1) DEFAULT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_parent_ids` (`parent_ids`(255)),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='机构表';

/*Data for the table `sys_office` */

insert  into `sys_office`(`id`,`parent_id`,`parent_ids`,`area_id`,`code`,`name`,`type`,`grade`,`address`,`zip_code`,`master`,`phone`,`fax`,`email`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,0,'0,',2,'100000','北京市总公司','1','1','',NULL,'','','','','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0'),(2,1,'0,1,',2,'100001','公司领导','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(3,1,'0,1,',2,'100002','人力部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,1,'0,1,',2,'100003','市场部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,1,'0,1,',2,'100004','技术部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,1,'0,1,',2,'100005','研发部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(7,1,'0,1,',8,'200000','山东省分公司','1','2','','','','','','','1','2013-05-27 08:00:00','2','2014-11-23 22:00:25','','0'),(8,7,'0,1,7,',8,'200001','公司领导','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(9,7,'0,1,7,',8,'200002','综合部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(10,7,'0,1,7,',8,'200003','市场部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(11,7,'0,1,7,',8,'200004','技术部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(12,7,'0,1,7,',9,'201000','济南市分公司','1','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(13,12,'0,1,7,12,',9,'201001','公司领导','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(14,12,'0,1,7,12,',9,'201002','综合部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(15,12,'0,1,7,12,',9,'201003','市场部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(16,12,'0,1,7,12,',9,'201004','技术部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(17,12,'0,1,7,12,',11,'201010','济南市历城区分公司','1','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(18,17,'0,1,7,12,17,',11,'201011','公司领导','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(19,17,'0,1,7,12,17,',11,'201012','综合部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(20,17,'0,1,7,12,17,',11,'201013','市场部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(21,17,'0,1,7,12,17,',11,'201014','技术部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(22,12,'0,1,7,12,',12,'201020','济南市历下区分公司','1','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(23,22,'0,1,7,12,22,',12,'201021','公司领导','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(24,22,'0,1,7,12,22,',12,'201022','综合部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(25,22,'0,1,7,12,22,',12,'201023','市场部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0'),(26,22,'0,1,7,12,22,',12,'201024','技术部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `pos` bigint(20) DEFAULT NULL COMMENT '权限位,相当于对权限分组,从0开始',
  `common` char(1) DEFAULT '0' COMMENT '是否是公共资源(0.不是 1.是)',
  `code` bigint(20) DEFAULT NULL COMMENT '权限码 1<<n',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` char(1) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态(0.正常 1.禁用)',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '父级集合',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`pos`,`common`,`code`,`icon`,`sort`,`parent_id`,`type`,`url`,`description`,`status`,`parent_ids`,`create_date`,`update_date`,`create_by`,`update_by`) values (1,'菜单配置',0,'0',1,'fa fa-calculator',1,188,'0','menu','','0','0,188,',NULL,NULL,NULL,NULL),(181,'区域管理',0,'0',128,'fa fa-globe',1,189,'0','area','','0','0,189,',NULL,NULL,NULL,NULL),(188,'系统设置',0,'0',16384,'fa fa-cog',1,0,'0','','','0','0,',NULL,NULL,NULL,NULL),(189,'机构用户',0,'0',32768,'fa fa-group',1,0,'0','','','0','0,',NULL,NULL,NULL,NULL),(190,'字典管理',0,'0',65536,'fa fa-area-chart',1,188,'0','dict','','0','0,188,',NULL,NULL,NULL,NULL),(192,'机构管理',0,'0',131072,'fa fa-codepen',1,189,'0','office','','0','0,189,',NULL,NULL,NULL,NULL),(193,'用户管理',0,'0',262144,'',1,189,'0','','','0','0,189,',NULL,NULL,NULL,NULL),(194,'角色管理',0,'0',524288,'fa fa-graduation-cap',1,189,'0','role','','0','0,189,',NULL,NULL,NULL,NULL),(195,'日志查询',0,'0',1048576,'',1,188,'0','','','0','0,188,',NULL,NULL,NULL,NULL),(196,'关于帮助',0,'0',2097152,'',1,0,'0','','','0','0,',NULL,NULL,NULL,NULL),(197,'项目首页',0,'0',4194304,'',1,196,'0','','','0','0,196,',NULL,NULL,NULL,NULL),(198,'项目支持',0,'0',8388608,'',1,196,'0','','','0','0,196,',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` bigint(20) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`office_id`,`name`,`data_scope`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,1,'系统管理员','1','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(2,1,'公司管理员','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(3,1,'本公司管理员','3','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,1,'部门管理员','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,1,'本部门管理员','5','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,1,'普通用户','8','1','2013-05-27 08:00:00','2','2014-10-12 21:20:45',NULL,'0'),(7,7,'山东省管理员','9','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');

/*Table structure for table `sys_role_office` */

DROP TABLE IF EXISTS `sys_role_office`;

CREATE TABLE `sys_role_office` (
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `office_id` bigint(20) NOT NULL COMMENT '机构编号',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

/*Data for the table `sys_role_office` */

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_resource` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` bigint(20) NOT NULL COMMENT '归属公司',
  `office_id` bigint(20) NOT NULL COMMENT '归属部门',
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`username`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`company_id`,`office_id`,`username`,`password`,`no`,`name`,`email`,`phone`,`mobile`,`user_type`,`login_ip`,`login_date`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,1,1,'thinkgem','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0001','Thinkgem','thinkgem@163.com','8675','8675',NULL,'127.0.0.1','2014-10-05 18:53:52','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','最高管理员','0'),(2,1,1,'admin','86f3059b228c8acf99e69734b6bb32cc','0002','管理员','thinkgem@163.com','8675','8675',NULL,'0:0:0:0:0:0:0:1','2014-12-21 19:16:11','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','管理员','0'),(3,1,3,'bj_zhb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0003','综合部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','综合部','0'),(4,1,4,'bj_scb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0004','市场部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','市场部','0'),(5,1,5,'bj_jsb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0005','技术部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','技术部','0'),(6,1,6,'bj_yfb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0006','研发部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','研发部','0'),(7,7,8,'sd_admin','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0007','山分领导','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','山东省分公司领导','0'),(8,7,9,'sd_zhb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0008','山分综合部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','山东省分公司综合部','0'),(9,7,10,'sd_scb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0009','山分市场部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','山东省分公司市场部','0'),(10,7,11,'sd_jsb','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0010','山东省分公司技术部','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','山东省分公司技术部','0'),(11,12,13,'sdjn_admin','c810e6915e66726c720c20b125568f3abdae500aefc9240e4e0bc2b4','0011','济分公司领导','thinkgem@163.com','8675','8675','','0:0:0:0:0:0:0:1','2014-11-23 21:55:41','1','2013-05-27 08:00:00','2','2014-11-23 21:55:27','济南分公司领导','0'),(12,12,18,'sdjnlc_admin','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0012','济分历城领导','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','济南市历城区分公司领导','0'),(13,22,23,'sdjnlx_admin','02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032','0013','济分历下领导','thinkgem@163.com','8675','8675',NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','济南市历下区分公司领导','0'),(14,22,24,'test','25a251792f44cf460aac78cb922f15814bd69eb8ac263681afaa319b','0014','济分历下综合部','thinkgem@163.com','8675','8675','','0:0:0:0:0:0:0:1','2014-10-12 21:21:23','1','2013-05-27 08:00:00','2','2014-10-12 21:21:15','济南市历下区分公司综合部','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
