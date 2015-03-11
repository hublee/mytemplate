/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.19 : Database - template
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
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_parent_ids` (`parent_ids`(255)),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='区域表';

/*Data for the table `sys_area` */

insert  into `sys_area`(`id`,`parent_id`,`parent_ids`,`code`,`name`,`type`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`icon`) values (1,0,'0,','100000','中国','1','1','2013-05-27 08:00:00','2,超级管理员','2015-02-28 20:37:03','','0','fa fa-institution'),(2,1,'0,1,','110000','北京市','2','1','2013-05-27 08:00:00','22','2015-01-20 22:15:47','','0','fa fa-smile-o'),(3,2,'0,1,2,','110101','东城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(4,2,'0,1,2,','110102','西城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(5,2,'0,1,2,','110103','朝阳区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(6,2,'0,1,2,','110104','丰台区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(7,2,'0,1,2,','110105','海淀区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(8,1,'0,1,','370000','山东省','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(9,8,'0,1,8,','370531','济南市','3','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(10,9,'0,1,8,9,','370532','高新区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(11,9,'0,1,8,9,','370533','历城区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(12,9,'0,1,8,9,','370534','历下区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(13,9,'0,1,8,9,','370535','天桥区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(14,9,'0,1,8,9,','370536','槐荫区','4','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00','','0',NULL),(15,1,'0,1,','110000x','测试导入','2','2,超级管理员','2015-01-31 20:49:31','22','2015-01-31 20:49:31','','1','fa fa-smile-o'),(16,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 20:57:38','22','2015-01-31 20:57:38','','1','fa fa-smile-o'),(17,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 20:59:10','22','2015-01-31 20:59:10','','1','fa fa-smile-o'),(18,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:00:07','22','2015-01-31 21:00:07','','1','fa fa-smile-o'),(19,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:01:37','22','2015-01-31 21:01:37','','1','fa fa-smile-o'),(20,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:04:52','22','2015-01-31 21:04:52','','1','fa fa-smile-o'),(21,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:08:07','22','2015-01-31 21:08:07','','1','fa fa-smile-o'),(22,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:09:53','22','2015-01-31 21:09:53','','1','fa fa-smile-o'),(23,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:11:53','22','2015-01-31 21:11:53','','1','fa fa-smile-o'),(24,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 21:13:31','22','2015-01-31 21:13:31','','1','fa fa-smile-o'),(25,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:15:36','22','2015-01-31 22:15:36','','1','fa fa-smile-o'),(26,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:16:18','22','2015-01-31 22:16:18','','1','fa fa-smile-o'),(27,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:16:53','22','2015-01-31 22:16:53','','1','fa fa-smile-o'),(28,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:17:20','22','2015-01-31 22:17:20','','1','fa fa-smile-o'),(29,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:18:00','22','2015-01-31 22:18:00','','1','fa fa-smile-o'),(30,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:19:39','22','2015-01-31 22:19:39','','1','fa fa-smile-o'),(31,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:21:10','22','2015-01-31 22:21:10','','1','fa fa-smile-o'),(32,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:21:37','22','2015-01-31 22:21:37','','1','fa fa-smile-o'),(33,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-01-31 22:23:28','22','2015-01-31 22:23:28','','1','fa fa-smile-o'),(34,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-02-02 01:27:54','22','2015-02-02 01:27:54','','1','fa fa-smile-o'),(35,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-02-02 01:28:44','22','2015-02-02 01:28:44','','1','fa fa-smile-o'),(36,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-02-02 01:30:40','22','2015-02-02 01:30:40','','0','fa fa-smile-o'),(37,1,'0,1,','110000','测试导入','2','2,超级管理员','2015-02-02 01:31:00','22','2015-02-02 01:31:00','','0','fa fa-smile-o');

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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`label`,`value`,`type`,`description`,`sort`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (1,'正常','0','del_flag','删除标记',10,'1','2013-05-27 08:00:00','2,超级管理员','2015-02-28 23:07:13',NULL,'0'),(2,'删除','1','del_flag','删除标记',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(3,'显示','1','show_hide','显示/隐藏',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(4,'隐藏','0','show_hide','显示/隐藏',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(5,'是','1','yes_no','是/否',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(6,'否','0','yes_no','是/否',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(17,'国家','1','sys_area_type','区域类型',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(18,'省份、直辖市','2','sys_area_type','区域类型',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(19,'地市','3','sys_area_type','区域类型',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(20,'区县','4','sys_area_type','区域类型',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(22,'部门','2','sys_office_type','机构类型',70,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(23,'一级','1','sys_office_grade','机构等级',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(24,'二级','2','sys_office_grade','机构等级',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(25,'三级','3','sys_office_grade','机构等级',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(26,'四级','4','sys_office_grade','机构等级',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(27,'所有数据','1','sys_data_scope','数据范围',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(28,'所在公司及以下数据','2','sys_data_scope','数据范围',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(29,'所在公司数据','3','sys_data_scope','数据范围',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(30,'所在部门及以下数据','4','sys_data_scope','数据范围',40,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(31,'所在部门数据','5','sys_data_scope','数据范围',50,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(32,'仅本人数据','8','sys_data_scope','数据范围',90,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(33,'按明细设置','9','sys_data_scope','数据范围',100,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(34,'系统管理','1','sys_user_type','用户类型',10,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(35,'部门经理','2','sys_user_type','用户类型',20,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(36,'普通用户','3','sys_user_type','用户类型',30,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0'),(62,'操作日志','1','sys_log_type','日志类型',30,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(63,'异常日志','2','sys_log_type','日志类型',40,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(64,'单表增删改查','single','prj_template_type','代码模板',10,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(65,'所有entity和dao','entityAndDao','prj_template_type','代码模板',20,'1','2013-06-03 08:00:00','1','2013-06-03 08:00:00',NULL,'0'),(66,'公司','1','sys_office_type','',1,NULL,'2015-01-10 22:15:43',NULL,'2015-01-10 22:15:43',NULL,'0');

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`type`,`create_by`,`create_date`,`remote_addr`,`user_agent`,`request_uri`,`method`,`params`,`exception`,`description`) values (20,'2','2,超级管理员','2015-03-01 14:23:57','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/area/save','POST','[参数1，类型:SysArea，值:{\"updateBy\":\"2,超级管理员\",\"id\":1,\"parentId\":0,\"icon\":\"fa fa-institution\",\"parentIds\":\"0,\",\"oldParentIds\":\"0,\",\"name\":\"中国\",\"remarks\":\"\",\"code\":\"100000\",\"type\":\"1\",\"updateDate\":1425191037467}]','java.lang.ArithmeticException: / by zero',NULL),(21,'2','2,超级管理员','2015-03-01 14:24:42','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/area/save','POST','[参数1，类型:SysArea，值:{\"updateBy\":\"2,超级管理员\",\"id\":1,\"parentId\":0,\"icon\":\"fa fa-institution\",\"parentIds\":\"0,\",\"oldParentIds\":\"0,\",\"name\":\"中国\",\"remarks\":\"\",\"code\":\"100000\",\"type\":\"1\",\"updateDate\":1425191082730}]','java.lang.ArithmeticException: / by zero',NULL),(22,'2','2,超级管理员','2015-03-01 14:25:53','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/area/save','POST','[参数1，类型:SysArea，值:{\"updateBy\":\"2,超级管理员\",\"id\":1,\"parentId\":0,\"icon\":\"fa fa-institution\",\"parentIds\":\"0,\",\"oldParentIds\":\"0,\",\"name\":\"中国\",\"remarks\":\"\",\"code\":\"100000\",\"type\":\"1\",\"updateDate\":1425191153080}]','java.lang.ArithmeticException: / by zero',NULL),(23,'2','2,超级管理员','2015-03-01 14:30:23','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/area/save','POST','[参数1，类型:SysArea，值:{\"updateBy\":\"2,超级管理员\",\"id\":1,\"parentId\":0,\"icon\":\"fa fa-institution\",\"parentIds\":\"0,\",\"oldParentIds\":\"0,\",\"name\":\"中国\",\"remarks\":\"\",\"code\":\"100000\",\"type\":\"1\",\"updateDate\":1425191423732}]','java.lang.ArithmeticException: / by zero',NULL),(24,'2','2,超级管理员','2015-03-01 14:39:04','0:0:0:0:0:0:0:1','Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/area/save','POST','[参数1，类型:SysArea，值:{\"updateBy\":\"2,超级管理员\",\"id\":1,\"parentId\":0,\"icon\":\"fa fa-institution\",\"parentIds\":\"0,\",\"oldParentIds\":\"0,\",\"name\":\"中国\",\"remarks\":\"\",\"code\":\"100000\",\"type\":\"1\",\"updateDate\":1425191923185}]','java.lang.ArithmeticException: / by zero',NULL),(25,'2','2,超级管理员','2015-03-10 13:23:13','127.0.0.1','Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36','/mytemplate/test/office/delete','POST','[参数1，类型:Long，值:49]','java.lang.IllegalArgumentException: Cannot find cache named \'sysOffice\' for CacheEvictOperation[public int com.template.web.sys.service.SysOfficeService.deleteOfficeByRootId(java.lang.Long)] caches=[sysOffice] | key=\'\'allOffice\'\' | keyGenerator=\'\' | cacheManager=\'\' | cacheResolver=\'\' | condition=\'\',false,false',NULL);

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
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_parent_ids` (`parent_ids`(255)),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='机构表';

/*Data for the table `sys_office` */

insert  into `sys_office`(`id`,`parent_id`,`parent_ids`,`area_id`,`code`,`name`,`type`,`grade`,`address`,`zip_code`,`master`,`phone`,`fax`,`email`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`,`icon`) values (1,0,'0,',2,'100000','北京市总公司','1','1','',NULL,'','','','','1','2013-05-27 08:00:00','2,超级管理员','2015-02-28 20:49:57','','0','fa fa-bicycle'),(2,1,'0,1,',2,'100001','公司领导','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(3,1,'0,1,',2,'100002','人力部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(4,1,'0,1,',2,'100003','市场部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(5,1,'0,1,',2,'100004','技术部','2','4','',NULL,'','','','','1','2013-05-27 08:00:00','22','2015-01-24 16:39:03','','0',''),(6,1,'0,1,',2,'100005','研发部','2','1',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0',NULL),(7,1,'0,1,',3,'200000','山东省分公司','1','2','','','','','','','1','2013-05-27 08:00:00','2','2014-11-23 22:00:25','','0',NULL),(8,7,'0,1,7,',8,'200001','公司领导','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(9,7,'0,1,7,',8,'200002','综合部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(10,7,'0,1,7,',8,'200003','市场部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(11,7,'0,1,7,',8,'200004','技术部','2','2',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(12,7,'0,1,7,',9,'201000','济南市分公司','1','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(13,12,'0,1,7,12,',9,'201001','公司领导','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(14,12,'0,1,7,12,',9,'201002','综合部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(15,12,'0,1,7,12,',9,'201003','市场部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(16,12,'0,1,7,12,',9,'201004','技术部','2','3',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(17,12,'0,1,7,12,',11,'201010','济南市历城区分公司','1','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(18,17,'0,1,7,12,17,',11,'201011','公司领导','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(19,17,'0,1,7,12,17,',11,'201012','综合部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(20,17,'0,1,7,12,17,',11,'201013','市场部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(21,17,'0,1,7,12,17,',11,'201014','技术部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(22,12,'0,1,7,12,',12,'201020','济南市历下区分公司','1','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(23,22,'0,1,7,12,22,',12,'201021','公司领导','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(24,22,'0,1,7,12,22,',12,'201022','综合部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(25,22,'0,1,7,12,22,',12,'201023','市场部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(26,22,'0,1,7,12,22,',12,'201024','技术部','2','4',NULL,NULL,NULL,NULL,NULL,NULL,'1','2013-05-27 08:00:00','2','2014-11-23 22:00:25',NULL,'0',NULL),(27,5,'0,1,5,',2,NULL,'技术1部门','2',NULL,'',NULL,'','','','',NULL,'2015-01-11 15:19:53',NULL,'2015-01-11 15:19:53','','0',NULL),(28,0,'0,',1,NULL,'测试公司名字一定要长','1',NULL,'',NULL,'','','','',NULL,'2015-01-11 23:48:22',NULL,'2015-01-12 21:46:32','','1',NULL),(29,28,'0,28,',1,NULL,'测试部门1','2',NULL,'',NULL,'','','','',NULL,'2015-01-11 23:48:35',NULL,'2015-01-11 23:48:35','','1',NULL),(30,10,'0,1,7,10,',1,NULL,'市场子部门','2',NULL,'',NULL,'','','','',NULL,'2015-01-13 22:56:14',NULL,'2015-01-13 22:56:14','','0',NULL),(35,0,'0,',1,NULL,'fsdfsdf','2','1','',NULL,'','','','',NULL,'2015-01-14 23:13:43',NULL,'2015-01-14 23:13:43','','0',NULL),(36,35,'0,35,',1,NULL,'2222','2','2','',NULL,'','','','','2','2015-01-18 20:29:53','2','2015-01-18 20:34:08','','0',NULL),(37,1,'0,1,',2,NULL,'测测','2','2','',NULL,'','','','','22','2015-01-24 15:19:09',NULL,'2015-01-24 15:19:09','','0',''),(38,5,'0,1,5,',2,NULL,'sdsd','2','3','',NULL,'','','','','22','2015-01-24 17:08:50',NULL,'2015-01-24 17:08:50','','0',''),(39,35,'0,35,',1,NULL,'sssddd','2','2','',NULL,'','','','','2','2015-01-24 17:35:09',NULL,'2015-01-24 17:35:09','','0',''),(40,1,'0,1,',2,NULL,'测试','2','2','',NULL,'','','','','22','2015-01-25 10:23:15',NULL,'2015-01-25 10:23:15','','1',''),(41,1,'0,1,',2,NULL,'aaaa','2','2','',NULL,'','','','','22','2015-01-25 21:34:43',NULL,'2015-01-25 21:34:43','','0',''),(42,1,'0,1,',2,NULL,'aaaa','2','2','',NULL,'','','','','22','2015-01-25 21:37:13',NULL,'2015-01-25 21:37:13','','1',''),(43,1,'0,1,',2,NULL,'ffffddd','2','2','',NULL,'','','','','22','2015-01-25 21:37:48',NULL,'2015-01-25 21:37:48','','0',''),(45,1,'0,1,',2,NULL,'测试自动赋权','2','2','',NULL,'','','','','22','2015-01-27 20:02:50',NULL,'2015-01-27 20:02:50','','0',''),(46,1,'0,1,',2,NULL,'cc22','2','2','',NULL,'','','','','22','2015-01-27 20:19:45',NULL,'2015-01-27 20:19:45','','0',''),(47,0,'0,',1,NULL,'sss','1','1','',NULL,'','','','','2','2015-01-28 21:46:00',NULL,'2015-01-28 21:46:00','','1',''),(48,1,'0,1,',1,NULL,'dd','1','2','',NULL,'','','','','22','2015-01-28 22:33:04',NULL,'2015-01-28 22:33:04','','0',''),(49,0,'0,',1,NULL,'xcxzcxc','2','1','',NULL,'','','','','22','2015-01-28 22:55:37',NULL,'2015-01-28 22:55:37','','1','');

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
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`pos`,`common`,`code`,`icon`,`sort`,`parent_id`,`type`,`url`,`description`,`status`,`parent_ids`,`create_date`,`update_date`,`create_by`,`update_by`,`del_flag`) values (1,'菜单配置',0,'0',1,'fa fa-calculator',1,188,'0','menu','','0','0,188,',NULL,NULL,NULL,NULL,'0'),(181,'区域管理',0,'0',128,'fa fa-globe',1,189,'0','area','','0','0,189,',NULL,NULL,NULL,NULL,'0'),(188,'系统管理',0,'0',16384,'fa fa-cog',1,0,'0','','','0','0,',NULL,'2015-03-10 17:44:18',NULL,'2,超级管理员','0'),(189,'机构用户',0,'0',32768,'fa fa-group',2,0,'0','','','0','0,',NULL,'2015-01-18 11:00:57',NULL,NULL,'0'),(190,'字典管理',0,'0',65536,'fa fa-area-chart',1,188,'0','dict','','0','0,188,',NULL,NULL,NULL,NULL,'0'),(192,'机构管理',0,'0',131072,'fa fa-codepen',1,189,'0','office','','0','0,189,',NULL,NULL,NULL,NULL,'0'),(193,'用户管理',0,'0',262144,'fa fa-users',1,189,'0','sysuser','','0','0,189,',NULL,'2015-01-13 21:15:06',NULL,NULL,'0'),(194,'角色管理',0,'0',524288,'fa fa-graduation-cap',2,189,'0','role','','0','0,189,',NULL,NULL,NULL,NULL,'0'),(195,'日志查询',0,'0',1048576,'fa fa-copy',1,188,'0','syslog','','0','0,188,',NULL,NULL,NULL,NULL,'0'),(200,'测试',0,'0',16777216,'',7,0,'0','test','','0','0,',NULL,'2015-03-03 23:30:40',NULL,'2,超级管理员','0'),(203,'搜索按钮',0,'0',134217728,'fa fa-angellist',1,181,'1','sys:area:find','这是一个按钮级别的示例，页面为添加，请添加@if(auth.hasPermission(\"sys:area:find\")){}测试','0','0,189,181,','2015-01-20 20:50:16','2015-01-20 20:57:38','22','22','0'),(204,'系统监控',NULL,'0',NULL,'fa fa-binoculars',6,0,'0','','','0','0,','2015-03-03 20:11:10','2015-03-11 13:22:48','2,超级管理员','2,超级管理员','0'),(205,'ehcache监控',NULL,'0',NULL,'',1,204,'0','monitor/ehcache','','0','0,204,','2015-03-03 20:11:19','2015-03-04 14:02:39','2,超级管理员','2,超级管理员','0'),(206,'jvm监控',NULL,'0',NULL,'',1,204,'0','monitor/jvm','','0','0,204,','2015-03-09 15:25:36','2015-03-09 15:26:04','2,超级管理员','2,超级管理员','0'),(207,'执行sql',NULL,'0',NULL,'',1,204,'0','monitor/db/sql','','0','0,204,','2015-03-09 17:02:36','2015-03-09 17:02:36','2,超级管理员',NULL,'0'),(208,'数据库监控',NULL,'0',NULL,'',1,204,'0','druid','','0','0,204,','2015-03-10 17:05:19','2015-03-10 17:05:19','2,超级管理员',NULL,'0');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`office_id`,`name`,`data_scope`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (15,1,'平民','9',NULL,'2015-01-17 20:36:23','22','2015-01-25 10:22:54',NULL,'0'),(16,1,'大臣','1',NULL,'2015-01-18 13:01:02','2','2015-01-28 22:32:39',NULL,'0'),(17,27,'ceshi','9','22','2015-01-24 20:53:58',NULL,'2015-01-24 20:53:58',NULL,'0'),(18,1,'测试1','9','22','2015-01-25 21:33:16',NULL,'2015-01-25 21:33:16',NULL,'0');

/*Table structure for table `sys_role_office` */

DROP TABLE IF EXISTS `sys_role_office`;

CREATE TABLE `sys_role_office` (
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `office_id` bigint(20) NOT NULL COMMENT '机构编号',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色-机构';

/*Data for the table `sys_role_office` */

insert  into `sys_role_office`(`role_id`,`office_id`,`id`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`) values (15,1,1,NULL,NULL,NULL,NULL,'0'),(15,2,2,NULL,NULL,NULL,NULL,'0'),(15,3,3,NULL,NULL,NULL,NULL,'0');

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1323 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`id`,`role_id`,`resource_id`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`) values (823,15,188,NULL,NULL,NULL,NULL,'0'),(824,15,1,NULL,NULL,NULL,NULL,'0'),(825,15,190,NULL,NULL,NULL,NULL,'0'),(826,15,195,NULL,NULL,NULL,NULL,'0'),(827,15,189,NULL,NULL,NULL,NULL,'0'),(828,15,181,NULL,NULL,NULL,NULL,'0'),(829,15,203,NULL,NULL,NULL,NULL,'0'),(830,15,192,NULL,NULL,NULL,NULL,'0'),(831,15,193,NULL,NULL,NULL,NULL,'0'),(832,15,194,NULL,NULL,NULL,NULL,'0'),(833,15,200,NULL,NULL,NULL,NULL,'0'),(996,18,188,NULL,NULL,NULL,NULL,'0'),(997,18,1,NULL,NULL,NULL,NULL,'0'),(998,18,190,NULL,NULL,NULL,NULL,'0'),(999,18,195,NULL,NULL,NULL,NULL,'0'),(1000,18,189,NULL,NULL,NULL,NULL,'0'),(1001,18,181,NULL,NULL,NULL,NULL,'0'),(1002,18,203,NULL,NULL,NULL,NULL,'0'),(1003,18,192,NULL,NULL,NULL,NULL,'0'),(1004,18,193,NULL,NULL,NULL,NULL,'0'),(1005,18,194,NULL,NULL,NULL,NULL,'0'),(1313,16,188,NULL,NULL,NULL,NULL,'0'),(1314,16,1,NULL,NULL,NULL,NULL,'0'),(1315,16,190,NULL,NULL,NULL,NULL,'0'),(1316,16,195,NULL,NULL,NULL,NULL,'0'),(1317,16,189,NULL,NULL,NULL,NULL,'0'),(1318,16,181,NULL,NULL,NULL,NULL,'0'),(1319,16,192,NULL,NULL,NULL,NULL,'0'),(1320,16,193,NULL,NULL,NULL,NULL,'0'),(1321,16,194,NULL,NULL,NULL,NULL,'0'),(1322,16,200,NULL,NULL,NULL,NULL,'0');

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
  `user_type` char(1) DEFAULT '0' COMMENT '用户类型(0.普通 1.系统超级管理员)',
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`company_id`,`office_id`,`username`,`password`,`no`,`name`,`email`,`phone`,`mobile`,`user_type`,`login_ip`,`login_date`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values (2,1,1,'admin','86f3059b228c8acf99e69734b6bb32cc','0002','超级管理员','thinkgem@163.com','8675','8675','1','127.0.0.1','2015-03-11 13:27:43','1','2013-05-27 08:00:00','2,超级管理员','2015-03-11 13:27:43','管理员','0'),(22,1,1,'ceshi1','d851ea96c7f9d003938f562957be5f60','','测试1','','','','0','127.0.0.1','2015-03-10 17:41:27',NULL,'2015-01-17 19:14:05','22,测试1','2015-03-10 17:41:27','','0'),(23,1,6,'ceshi3','053d1c300518bcefb75352d022f45d00','','韩流','','','','0',NULL,NULL,'22','2015-01-25 12:13:04',NULL,'2015-01-25 12:13:04','','0'),(24,1,27,'ceshi4','7f8c872d354b49473259f0900113eec5','','王五误','','','','0',NULL,NULL,'22','2015-01-25 13:30:35',NULL,'2015-01-25 13:30:35','','0'),(25,1,2,'ceshi5','d3c884a0dbee705ecd52ce0811fa80d7','','陈留','','','','0',NULL,NULL,'22','2015-01-25 21:38:37',NULL,'2015-01-25 21:38:37','','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`role_id`,`user_id`,`create_by`,`create_date`,`update_by`,`update_date`,`del_flag`) values (59,15,23,NULL,NULL,NULL,NULL,'0'),(67,16,22,NULL,NULL,NULL,NULL,'0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
