/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : workflow

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-21 15:31:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  `sta_id` varchar(255) NOT NULL,
  `staId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB9D38A2DB5A26C8F` (`sta_id`),
  CONSTRAINT `FKB9D38A2DB5A26C8F` FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `awd_id` varchar(255) NOT NULL,
  `awd_type` varchar(255) DEFAULT NULL,
  `awd_rank` varchar(255) DEFAULT NULL,
  `awdId` varchar(255) NOT NULL,
  `awdType` varchar(255) DEFAULT NULL,
  `awdRank` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`awd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bk_id` varchar(255) NOT NULL,
  `bk_name` varchar(255) DEFAULT NULL,
  `pub_date` datetime DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `sc_yr` varchar(255) DEFAULT NULL,
  `bk_type` varchar(255) DEFAULT NULL,
  `bk_wd_num` decimal(19,2) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  `bkId` varchar(255) NOT NULL,
  `bkName` varchar(255) DEFAULT NULL,
  `pubDate` datetime DEFAULT NULL,
  `scYr` varchar(255) DEFAULT NULL,
  `bkType` varchar(255) DEFAULT NULL,
  `bkWdNum` decimal(19,2) DEFAULT NULL,
  `deptId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bookawd
-- ----------------------------
DROP TABLE IF EXISTS `bookawd`;
CREATE TABLE `bookawd` (
  `idbs` varchar(255) NOT NULL,
  `bk_id` varchar(255) NOT NULL,
  `awd_id` varchar(255) NOT NULL,
  `bkId` varchar(255) NOT NULL,
  `awdId` varchar(255) NOT NULL,
  PRIMARY KEY (`idbs`),
  KEY `FK3DAEB65961A8C7E` (`awd_id`),
  KEY `FK3DAEB654F330F05` (`bk_id`),
  CONSTRAINT `FK3DAEB654F330F05` FOREIGN KEY (`bk_id`) REFERENCES `book` (`bk_id`),
  CONSTRAINT `FK3DAEB65961A8C7E` FOREIGN KEY (`awd_id`) REFERENCES `award` (`awd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for booksta
-- ----------------------------
DROP TABLE IF EXISTS `booksta`;
CREATE TABLE `booksta` (
  `idbs` varchar(255) NOT NULL,
  `peo_wd_num` decimal(19,2) DEFAULT NULL,
  `bkcb_role` varchar(255) DEFAULT NULL,
  `is_tx` tinyint(4) DEFAULT NULL,
  `bk_score` decimal(19,2) DEFAULT NULL,
  `bk_id` varchar(255) NOT NULL,
  `sta_id` varchar(255) NOT NULL,
  `peoWdNum` decimal(19,2) DEFAULT NULL,
  `bkcbRole` varchar(255) DEFAULT NULL,
  `isTx` tinyint(4) DEFAULT NULL,
  `bkScore` decimal(19,2) DEFAULT NULL,
  `bkId` varchar(255) NOT NULL,
  `staId` varchar(255) NOT NULL,
  PRIMARY KEY (`idbs`),
  KEY `FK3DB2E97B5A26C8F` (`sta_id`),
  KEY `FK3DB2E974F330F05` (`bk_id`),
  CONSTRAINT `FK3DB2E974F330F05` FOREIGN KEY (`bk_id`) REFERENCES `book` (`bk_id`),
  CONSTRAINT `FK3DB2E97B5A26C8F` FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for confer
-- ----------------------------
DROP TABLE IF EXISTS `confer`;
CREATE TABLE `confer` (
  `confer_id` varchar(255) NOT NULL,
  `confer_type` varchar(255) DEFAULT NULL,
  `confer_nm` varchar(255) DEFAULT NULL,
  `confer_time` datetime DEFAULT NULL,
  `confer_addr` varchar(255) DEFAULT NULL,
  `conferId` varchar(255) NOT NULL,
  `conferType` varchar(255) DEFAULT NULL,
  `conferNm` varchar(255) DEFAULT NULL,
  `conferTime` datetime DEFAULT NULL,
  `conferAddr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`confer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conferpaper
-- ----------------------------
DROP TABLE IF EXISTS `conferpaper`;
CREATE TABLE `conferpaper` (
  `idpc` varchar(255) NOT NULL,
  `cp_score` decimal(19,2) DEFAULT NULL,
  `pub_date` date DEFAULT NULL,
  `confer_id` varchar(255) NOT NULL,
  `paper_id` varchar(255) NOT NULL,
  `cpScore` decimal(19,2) DEFAULT NULL,
  `pubDate` date DEFAULT NULL,
  `conferId` varchar(255) NOT NULL,
  `paperId` varchar(255) NOT NULL,
  PRIMARY KEY (`idpc`),
  KEY `FK411EA95B2D45A04F` (`paper_id`),
  KEY `FK411EA95B24144CA5` (`confer_id`),
  CONSTRAINT `FK411EA95B24144CA5` FOREIGN KEY (`confer_id`) REFERENCES `confer` (`confer_id`),
  CONSTRAINT `FK411EA95B2D45A04F` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for mag
-- ----------------------------
DROP TABLE IF EXISTS `mag`;
CREATE TABLE `mag` (
  `mag_id` varchar(255) NOT NULL,
  `mag_name` varchar(255) DEFAULT NULL,
  `magsnm` varchar(255) DEFAULT NULL,
  `issn` varchar(255) DEFAULT NULL,
  `cn` varchar(255) DEFAULT NULL,
  `mag_sub` varchar(255) DEFAULT NULL,
  `fq` varchar(255) DEFAULT NULL,
  `grade_id` varchar(255) DEFAULT NULL,
  `magId` varchar(255) NOT NULL,
  `magName` varchar(255) DEFAULT NULL,
  `magSub` varchar(255) DEFAULT NULL,
  `gradeId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ordactor
-- ----------------------------
DROP TABLE IF EXISTS `ordactor`;
CREATE TABLE `ordactor` (
  `idoa` varchar(255) NOT NULL,
  `idorder` varchar(255) NOT NULL,
  `idactor` varchar(255) NOT NULL,
  `role` int(255) NOT NULL,
  PRIMARY KEY (`idoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paper_id` varchar(255) NOT NULL,
  `paper_name` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `paperId` varchar(255) NOT NULL,
  `paperName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for papermag
-- ----------------------------
DROP TABLE IF EXISTS `papermag`;
CREATE TABLE `papermag` (
  `idpm` varchar(255) NOT NULL,
  `col_type` varchar(255) DEFAULT NULL,
  `vol_iss` varchar(255) DEFAULT NULL,
  `bg_page` varchar(255) DEFAULT NULL,
  `paper_score` int(11) DEFAULT NULL,
  `pub_date` date DEFAULT NULL,
  `paper_id` varchar(255) NOT NULL,
  `mag_id` varchar(255) NOT NULL,
  `colType` varchar(255) DEFAULT NULL,
  `volIss` varchar(255) DEFAULT NULL,
  `bgPage` varchar(255) DEFAULT NULL,
  `paperScore` int(11) DEFAULT NULL,
  `pubDate` date DEFAULT NULL,
  `paperId` varchar(255) NOT NULL,
  `magId` varchar(255) NOT NULL,
  PRIMARY KEY (`idpm`),
  KEY `FK42A398476710B82F` (`mag_id`),
  KEY `FK42A398472D45A04F` (`paper_id`),
  CONSTRAINT `FK42A398472D45A04F` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FK42A398476710B82F` FOREIGN KEY (`mag_id`) REFERENCES `mag` (`mag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for papersta
-- ----------------------------
DROP TABLE IF EXISTS `papersta`;
CREATE TABLE `papersta` (
  `idps` varchar(255) NOT NULL,
  `paper_role` varchar(255) DEFAULT NULL,
  `gr_score` int(11) DEFAULT NULL,
  `paper_id` varchar(255) NOT NULL,
  `sta_id` varchar(255) NOT NULL,
  `paperRole` varchar(255) DEFAULT NULL,
  `grScore` int(11) DEFAULT NULL,
  `paperId` varchar(255) NOT NULL,
  `staId` varchar(255) NOT NULL,
  PRIMARY KEY (`idps`),
  KEY `FK42A3B114B5A26C8F` (`sta_id`),
  KEY `FK42A3B1142D45A04F` (`paper_id`),
  CONSTRAINT `FK42A3B1142D45A04F` FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`),
  CONSTRAINT `FK42A3B114B5A26C8F` FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `sta_id` varchar(255) NOT NULL,
  `sta_name` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `edu` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `stasnm` varchar(255) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  `rank_id` varchar(255) DEFAULT NULL,
  `staId` varchar(255) NOT NULL,
  `staName` varchar(255) DEFAULT NULL,
  `deptId` varchar(255) DEFAULT NULL,
  `rankId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order` (
  `order_id` varchar(32) NOT NULL,
  `actor_id` varchar(32) NOT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `finish_time` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order` (
  `id` varchar(32) NOT NULL,
  `process_id` varchar(32) DEFAULT NULL,
  `order_state` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `expire_time` varchar(50) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `order_no` varchar(50) DEFAULT NULL,
  `variable` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `task_name` varchar(100) DEFAULT NULL,
  `display_name` varchar(200) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `finish_time` varchar(50) DEFAULT NULL,
  `expire_time` varchar(50) DEFAULT NULL,
  `action_url` varchar(200) DEFAULT NULL,
  `task_type` int(11) DEFAULT NULL,
  `task_state` int(11) DEFAULT NULL,
  `perform_type` int(11) DEFAULT NULL,
  `parent_task_id` varchar(32) DEFAULT NULL,
  `variable` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor` (
  `task_id` varchar(32) NOT NULL,
  `actor_id` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order` (
  `id` varchar(32) NOT NULL,
  `version` int(11) NOT NULL,
  `process_id` varchar(32) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `expire_time` varchar(50) DEFAULT NULL,
  `last_update_time` varchar(50) DEFAULT NULL,
  `last_updator` varchar(50) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `parent_node_name` varchar(50) DEFAULT NULL,
  `order_no` varchar(50) DEFAULT NULL,
  `variable` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `display_name` varchar(200) DEFAULT NULL,
  `instance_url` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `content` longblob,
  `version` int(11) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate` (
  `id` varchar(32) NOT NULL,
  `process_name` varchar(100) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `surrogate` varchar(50) DEFAULT NULL,
  `odate` varchar(50) DEFAULT NULL,
  `sdate` varchar(50) DEFAULT NULL,
  `edate` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task` (
  `id` varchar(32) NOT NULL,
  `version` int(11) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `task_name` varchar(100) DEFAULT NULL,
  `display_name` varchar(200) DEFAULT NULL,
  `operator` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `finish_time` varchar(50) DEFAULT NULL,
  `expire_time` varchar(50) DEFAULT NULL,
  `action_url` varchar(200) DEFAULT NULL,
  `task_type` int(11) DEFAULT NULL,
  `perform_type` int(11) DEFAULT NULL,
  `parent_task_id` varchar(32) DEFAULT NULL,
  `variable` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor` (
  `task_id` varchar(32) NOT NULL,
  `actor_id` varchar(50) NOT NULL,
  PRIMARY KEY (`task_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wf_workitem
-- ----------------------------
DROP TABLE IF EXISTS `wf_workitem`;
CREATE TABLE `wf_workitem` (
  `task_id` varchar(255) NOT NULL,
  `process_id` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  `process_name` varchar(255) DEFAULT NULL,
  `instance_url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `order_create_time` varchar(255) DEFAULT NULL,
  `order_expire_time` varchar(255) DEFAULT NULL,
  `order_variable` varchar(255) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_key` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `task_create_time` varchar(255) DEFAULT NULL,
  `task_end_time` varchar(255) DEFAULT NULL,
  `task_expire_time` varchar(255) DEFAULT NULL,
  `action_url` varchar(255) DEFAULT NULL,
  `task_type` int(11) DEFAULT NULL,
  `perform_type` int(11) DEFAULT NULL,
  `task_variable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
