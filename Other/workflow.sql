/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : workflow

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-02 14:20:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
`account_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pwd`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`privilege`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sta_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`account_id`),
FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FKB9D38A2DB5A26C8F` (`sta_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
`awd_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`awd_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`awd_rank`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`awd_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of award
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS `base`;
CREATE TABLE `base` (
`base_id`  char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`table_name`  varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`keycode`  char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`value`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`base_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of base
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
`bk_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`bk_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pub_date`  datetime NULL DEFAULT NULL ,
`publisher`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isbn`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sc_yr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bk_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bk_wd_num`  decimal(19,2) NULL DEFAULT NULL ,
`dept_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`bk_id`),
FOREIGN KEY (`dept_id`) REFERENCES `base` (`base_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `dept_id` (`dept_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of book
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for bookawd
-- ----------------------------
DROP TABLE IF EXISTS `bookawd`;
CREATE TABLE `bookawd` (
`ba_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`bk_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`awd_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`ba_id`),
FOREIGN KEY (`bk_id`) REFERENCES `book` (`bk_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`awd_id`) REFERENCES `award` (`awd_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK3DAEB65961A8C7E` (`awd_id`) USING BTREE ,
INDEX `FK3DAEB654F330F05` (`bk_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of bookawd
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for booksta
-- ----------------------------
DROP TABLE IF EXISTS `booksta`;
CREATE TABLE `booksta` (
`bs_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`peo_wd_num`  decimal(19,2) NULL DEFAULT NULL ,
`bkcb_role`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`is_tx`  tinyint(4) NULL DEFAULT NULL ,
`bk_score`  decimal(19,2) NULL DEFAULT NULL ,
`bk_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`sta_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`bs_id`),
FOREIGN KEY (`bk_id`) REFERENCES `book` (`bk_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK3DB2E97B5A26C8F` (`sta_id`) USING BTREE ,
INDEX `FK3DB2E974F330F05` (`bk_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of booksta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for confer
-- ----------------------------
DROP TABLE IF EXISTS `confer`;
CREATE TABLE `confer` (
`confer_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`confer_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`confer_nm`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`confer_time`  datetime NULL DEFAULT NULL ,
`confer_addr`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`confer_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of confer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for conferpaper
-- ----------------------------
DROP TABLE IF EXISTS `conferpaper`;
CREATE TABLE `conferpaper` (
`cp_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`cp_score`  decimal(19,2) NULL DEFAULT NULL ,
`pub_date`  date NULL DEFAULT NULL ,
`confer_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`paper_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`cp_id`),
FOREIGN KEY (`confer_id`) REFERENCES `confer` (`confer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK411EA95B2D45A04F` (`paper_id`) USING BTREE ,
INDEX `FK411EA95B24144CA5` (`confer_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of conferpaper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for mag
-- ----------------------------
DROP TABLE IF EXISTS `mag`;
CREATE TABLE `mag` (
`mag_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`mag_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`magsnm`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`issn`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`cn`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`mag_sub`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`fq`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`grade_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`mag_id`),
FOREIGN KEY (`grade_id`) REFERENCES `base` (`base_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `grade_id` (`grade_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of mag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ordactor
-- ----------------------------
DROP TABLE IF EXISTS `ordactor`;
CREATE TABLE `ordactor` (
`idoa`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`order_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`actor_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`role`  int(11) NOT NULL ,
PRIMARY KEY (`idoa`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of ordactor
-- ----------------------------
BEGIN;
INSERT INTO `ordactor` VALUES ('xgfan-341bacae12674e13aac30ac77991cb0f', '341bacae12674e13aac30ac77991cb0f', 'xgfan', 'paper', '1'), ('xgfan-36f2cf1928854250aa10e6321e61404a', '36f2cf1928854250aa10e6321e61404a', 'xgfan', 'paper', '1'), ('xgfan-6ec3eb14e2884c02873ac9328be20f25', '6ec3eb14e2884c02873ac9328be20f25', 'xgfan', 'paper', '1'), ('zss-0cee40d9001146dfb44aded00d9c94bc', '0cee40d9001146dfb44aded00d9c94bc', 'zss', '论文', '1'), ('zss-b2de3e6951fe425d8f7e965458d56e46', 'b2de3e6951fe425d8f7e965458d56e46', 'zss', '论文', '1'), ('zss-b3ae84ec09a54b9bade54dbf69cb2a09', 'b3ae84ec09a54b9bade54dbf69cb2a09', 'zss', '论文', '1'), ('zss-baa8289a37b942c79a595c3b9ca21df4', 'baa8289a37b942c79a595c3b9ca21df4', 'zss', '论文', '1'), ('zss-c07e81fb10c645acbafe37e0767a5f77', 'c07e81fb10c645acbafe37e0767a5f77', 'zss', '论文', '1');
COMMIT;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
`paper_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`paper_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`unit`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`paper_id`),
FOREIGN KEY (`unit`) REFERENCES `base` (`base_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `unit` (`unit`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of paper
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for papermag
-- ----------------------------
DROP TABLE IF EXISTS `papermag`;
CREATE TABLE `papermag` (
`pm_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`col_type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`vol_iss`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bg_page`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`paper_score`  int(11) NULL DEFAULT NULL ,
`pub_date`  date NULL DEFAULT NULL ,
`paper_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`mag_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`pm_id`),
FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`mag_id`) REFERENCES `mag` (`mag_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK42A398476710B82F` (`mag_id`) USING BTREE ,
INDEX `FK42A398472D45A04F` (`paper_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of papermag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for papersta
-- ----------------------------
DROP TABLE IF EXISTS `papersta`;
CREATE TABLE `papersta` (
`ps_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`paper_role`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`gr_score`  int(11) NULL DEFAULT NULL ,
`paper_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`sta_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`ps_id`),
FOREIGN KEY (`paper_id`) REFERENCES `paper` (`paper_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`sta_id`) REFERENCES `staff` (`sta_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK42A3B114B5A26C8F` (`sta_id`) USING BTREE ,
INDEX `FK42A3B1142D45A04F` (`paper_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of papersta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
`sta_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`sta_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`idcard`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`edu`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`position`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`degree`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`stasnm`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`dept_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`rank_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`sta_id`),
FOREIGN KEY (`dept_id`) REFERENCES `base` (`base_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`dept_id`) REFERENCES `base` (`base_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `dept_id` (`dept_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of staff
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order` (
`order_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`actor_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`creator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`finish_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`status`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`order_id`, `actor_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_cc_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`process_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_state`  int(11) NULL DEFAULT NULL ,
`creator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`end_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`expire_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`priority`  int(11) NULL DEFAULT NULL ,
`parent_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`variable`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_hist_order
-- ----------------------------
BEGIN;
INSERT INTO `wf_hist_order` VALUES ('0cee40d9001146dfb44aded00d9c94bc', 'fd7b9ab7525f42bd893551f98978f476', '1', 'zss', '2015-04-01 11:25:07', null, null, null, null, '20150401-11:25:07-376-293', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"}}'), ('341bacae12674e13aac30ac77991cb0f', 'fd7b9ab7525f42bd893551f98978f476', '1', 'xgfan', '2015-04-02 14:02:01', null, null, null, null, '20150402-14:02:01-107-198', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Blank\"}'), ('36f2cf1928854250aa10e6321e61404a', 'fd7b9ab7525f42bd893551f98978f476', '1', 'xgfan', '2015-04-01 08:37:00', null, null, null, null, '20150401-08:37:00-714-24', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Complete\",\"Details\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('6ec3eb14e2884c02873ac9328be20f25', 'fd7b9ab7525f42bd893551f98978f476', '1', 'xgfan', '2015-04-01 08:34:46', null, null, null, null, '20150401-08:34:46-103-807', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Complete\",\"Details\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"}}'), ('b2de3e6951fe425d8f7e965458d56e46', 'fd7b9ab7525f42bd893551f98978f476', '1', 'zss', '2015-04-01 10:52:40', null, null, null, null, '20150401-10:52:40-006-835', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('b3ae84ec09a54b9bade54dbf69cb2a09', 'fd7b9ab7525f42bd893551f98978f476', '1', 'zss', '2015-04-01 10:49:11', null, null, null, null, '20150401-10:49:11-229-289', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('baa8289a37b942c79a595c3b9ca21df4', 'fd7b9ab7525f42bd893551f98978f476', '1', 'zss', '2015-04-01 10:17:46', null, null, null, null, '20150401-10:17:46-770-228', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"}}'), ('c07e81fb10c645acbafe37e0767a5f77', 'fd7b9ab7525f42bd893551f98978f476', '1', 'zss', '2015-04-01 11:24:33', null, null, null, null, '20150401-11:24:33-343-546', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Blank\"}');
COMMIT;

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`order_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`display_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`operator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`finish_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`expire_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`action_url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_type`  int(11) NULL DEFAULT NULL ,
`task_state`  int(11) NULL DEFAULT NULL ,
`perform_type`  int(11) NULL DEFAULT NULL ,
`parent_task_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`variable`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_hist_task
-- ----------------------------
BEGIN;
INSERT INTO `wf_hist_task` VALUES ('0be203e6c7084a98a795f03727f336a5', '0cee40d9001146dfb44aded00d9c94bc', 'Confirm', '确认', 'zss', '2015-04-01 11:25:36', '2015-04-01 11:25:36', null, '', '0', '0', '0', 'd8a564fc200e4f53a1f19859b28a36b0', '{\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"S-ACTOR\":\"zss\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"}}'), ('12e495e7bb614ec6af42af9cf458368b', '6ec3eb14e2884c02873ac9328be20f25', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:35:02', '2015-04-01 08:36:01', null, '', '0', '0', '0', 'c511225b812548a1aa24ffaf7836bf4e', '{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan,fawefawe\"}}'), ('2d36c41dfc034b9dbe8fb92fce0445f1', '36f2cf1928854250aa10e6321e61404a', 'Confirm', '确认', 'xgfan', '2015-04-01 08:37:23', '2015-04-01 08:37:23', null, '', '0', '0', '0', 'd5d0f1d1866549f0b4c5e47572e31602', '{\"S-ACTOR\":\"xgfan\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_2_Confirm\":{\"S-ACTOR\":\"xgfan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('35069f5bd4764774859efb6c0cd03dda', '6ec3eb14e2884c02873ac9328be20f25', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:36:01', '2015-04-01 08:36:11', null, '', '0', '0', '0', '12e495e7bb614ec6af42af9cf458368b', '{\"WF_2_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan,fawefawe\"}}'), ('3fdac783bd194abe84c8c28a5efff72e', 'baa8289a37b942c79a595c3b9ca21df4', 'Submission', '填写申请', 'zss', '2015-04-01 10:17:46', '2015-04-01 10:22:43', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"zss\",\"IsComplete\":\"true\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"}}'), ('54c4f3ee6f3f475bbc9b3b31edc25527', '6ec3eb14e2884c02873ac9328be20f25', 'Confirm', '确认', 'xgfan', '2015-04-01 08:36:38', '2015-04-01 08:36:39', null, '', '0', '0', '0', '6aca00e579ea4d1c956dc6c259e6e19e', '{\"WF_2_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"S-ACTOR\":\"xgfan\",\"WF_4_Confirm\":{\"S-ACTOR\":\"xgfan\"},\"WF_3_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan,fawefawe\"}}'), ('6aca00e579ea4d1c956dc6c259e6e19e', '6ec3eb14e2884c02873ac9328be20f25', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:36:11', '2015-04-01 08:36:38', null, '', '0', '0', '0', '35069f5bd4764774859efb6c0cd03dda', '{\"WF_2_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"S-ACTOR\":\"xgfan\",\"WF_3_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"IsComplete\":\"true\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan,fawefawe\"}}'), ('794820e3745f42ba91a0ab65d7085aec', 'b3ae84ec09a54b9bade54dbf69cb2a09', 'Submission', '填写申请', 'zss', '2015-04-01 10:49:11', '2015-04-01 10:49:34', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"zss\",\"IsComplete\":\"true\",\"WF_0_Submission\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('a8bdacdcbab047fa807e5788d5c48d66', 'baa8289a37b942c79a595c3b9ca21df4', 'Confirm', '确认', 'zss', '2015-04-01 10:22:43', '2015-04-01 10:22:43', null, '', '0', '0', '0', '3fdac783bd194abe84c8c28a5efff72e', '{\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"S-ACTOR\":\"zss\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"}}'), ('b93a8517c8d14b04b7622a1de7028dec', '36f2cf1928854250aa10e6321e61404a', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:37:00', '2015-04-01 08:37:15', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('c511225b812548a1aa24ffaf7836bf4e', '6ec3eb14e2884c02873ac9328be20f25', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:34:46', '2015-04-01 08:35:02', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('d33a97f0cc5e4650b310041bdf7c9674', 'b2de3e6951fe425d8f7e965458d56e46', 'Submission', '填写申请', 'zss', '2015-04-01 10:52:40', '2015-04-01 10:53:06', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"zss\",\"IsComplete\":\"true\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('d5d0f1d1866549f0b4c5e47572e31602', '36f2cf1928854250aa10e6321e61404a', 'Submission', '填写申请', 'xgfan', '2015-04-01 08:37:15', '2015-04-01 08:37:23', null, '', '0', '0', '0', 'b93a8517c8d14b04b7622a1de7028dec', '{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('d8a564fc200e4f53a1f19859b28a36b0', '0cee40d9001146dfb44aded00d9c94bc', 'Submission', '填写申请', 'zss', '2015-04-01 11:25:07', '2015-04-01 11:25:36', null, '', '0', '0', '0', 'start', '{\"S-ACTOR\":\"zss\",\"IsComplete\":\"true\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"}}'), ('f55a4e136ba949a6a5cb2e8a7cd1181a', 'b3ae84ec09a54b9bade54dbf69cb2a09', 'Confirm', '确认', 'zss', '2015-04-01 10:49:34', '2015-04-01 10:49:34', null, '', '0', '0', '0', '794820e3745f42ba91a0ab65d7085aec', '{\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"S-ACTOR\":\"zss\",\"WF_0_Submission\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('f68af7598ef3449b9294ed208ae7b8ff', 'b2de3e6951fe425d8f7e965458d56e46', 'Confirm', '确认', 'zss', '2015-04-01 10:53:06', '2015-04-01 10:53:07', null, '', '0', '0', '0', 'd33a97f0cc5e4650b310041bdf7c9674', '{\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"S-ACTOR\":\"zss\",\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}');
COMMIT;

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor` (
`task_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`actor_id`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`task_id`, `actor_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_hist_task_actor
-- ----------------------------
BEGIN;
INSERT INTO `wf_hist_task_actor` VALUES ('0be203e6c7084a98a795f03727f336a5', 'zss'), ('12e495e7bb614ec6af42af9cf458368b', 'xgfan'), ('2d36c41dfc034b9dbe8fb92fce0445f1', 'xgfan'), ('35069f5bd4764774859efb6c0cd03dda', 'xgfan'), ('3fdac783bd194abe84c8c28a5efff72e', 'zss'), ('54c4f3ee6f3f475bbc9b3b31edc25527', 'xgfan'), ('6aca00e579ea4d1c956dc6c259e6e19e', 'xgfan'), ('794820e3745f42ba91a0ab65d7085aec', 'zss'), ('a8bdacdcbab047fa807e5788d5c48d66', 'zss'), ('b93a8517c8d14b04b7622a1de7028dec', 'xgfan'), ('c511225b812548a1aa24ffaf7836bf4e', 'xgfan'), ('d33a97f0cc5e4650b310041bdf7c9674', 'zss'), ('d5d0f1d1866549f0b4c5e47572e31602', 'xgfan'), ('d8a564fc200e4f53a1f19859b28a36b0', 'zss'), ('f55a4e136ba949a6a5cb2e8a7cd1181a', 'zss'), ('f68af7598ef3449b9294ed208ae7b8ff', 'zss');
COMMIT;

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`version`  int(11) NOT NULL ,
`process_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`creator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`expire_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`last_update_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`last_updator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`priority`  int(11) NULL DEFAULT NULL ,
`parent_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`parent_node_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`variable`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_order
-- ----------------------------
BEGIN;
INSERT INTO `wf_order` VALUES ('0cee40d9001146dfb44aded00d9c94bc', '3', 'fd7b9ab7525f42bd893551f98978f476', 'zss', '2015-04-01 11:25:07', null, '2015-04-01 11:25:36', 'zss', null, null, null, '20150401-11:25:07-376-293', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"}}'), ('341bacae12674e13aac30ac77991cb0f', '1', 'fd7b9ab7525f42bd893551f98978f476', 'xgfan', '2015-04-02 14:02:01', null, '2015-04-02 14:02:01', 'xgfan', null, null, null, '20150402-14:02:01-107-198', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Blank\"}'), ('36f2cf1928854250aa10e6321e61404a', '5', 'fd7b9ab7525f42bd893551f98978f476', 'xgfan', '2015-04-01 08:37:00', null, '2015-04-01 08:37:23', 'xgfan', null, null, null, '20150401-08:37:00-714-24', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Complete\",\"Details\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('6ec3eb14e2884c02873ac9328be20f25', '10', 'fd7b9ab7525f42bd893551f98978f476', 'xgfan', '2015-04-01 08:34:46', null, '2015-04-01 08:36:39', 'xgfan', null, null, null, '20150401-08:34:46-103-807', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\",\"Status\":\"Complete\",\"Details\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"}}'), ('b2de3e6951fe425d8f7e965458d56e46', '4', 'fd7b9ab7525f42bd893551f98978f476', 'zss', '2015-04-01 10:52:40', null, '2015-04-01 10:53:07', 'zss', null, null, null, '20150401-10:52:40-006-835', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('b3ae84ec09a54b9bade54dbf69cb2a09', '3', 'fd7b9ab7525f42bd893551f98978f476', 'zss', '2015-04-01 10:49:11', null, '2015-04-01 10:49:34', 'zss', null, null, null, '20150401-10:49:11-229-289', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"}}'), ('baa8289a37b942c79a595c3b9ca21df4', '3', 'fd7b9ab7525f42bd893551f98978f476', 'zss', '2015-04-01 10:17:46', null, '2015-04-01 10:22:43', 'zss', null, null, null, '20150401-10:17:46-770-228', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Complete\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"}}'), ('c07e81fb10c645acbafe37e0767a5f77', '1', 'fd7b9ab7525f42bd893551f98978f476', 'zss', '2015-04-01 11:24:33', null, '2015-04-01 11:24:33', 'zss', null, null, null, '20150401-11:24:33-343-546', '{\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\",\"Status\":\"Blank\"}');
COMMIT;

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`display_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`instance_url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`type`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  int(11) NULL DEFAULT NULL ,
`content`  longblob NULL ,
`version`  int(11) NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`creator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_process
-- ----------------------------
BEGIN;
INSERT INTO `wf_process` VALUES ('fd7b9ab7525f42bd893551f98978f476', 'basicProcess_Beta', '基础流程Beta', '', null, '1', 0x3C70726F63657373206E616D653D22626173696350726F636573735F426574612220646973706C61794E616D653D22E59FBAE7A180E6B581E7A88B4265746122203E0D0A202020203C7374617274206E616D653D2273746172742220706F7374496E746572636570746F72733D22656E67696E652E66696C7465722E5374617274223E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D225375626D697373696F6E22206E616D653D225374617274657222202F3E0D0A202020203C2F73746172743E0D0A202020203C7461736B206E616D653D225375626D697373696F6E222020646973706C61794E616D653D22E5A1ABE58699E794B3E8AFB7220D0A20202020202020202020706F7374496E746572636570746F72733D22656E67696E652E66696C7465722E5375626D6974222061737369676E6D656E7448616E646C65723D22656E67696E652E706F6C652E506F6C65223E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22536176654F725375626D697422206E616D653D22743273617665222F3E0D0A202020203C2F7461736B3E0D0A202020203C6465636973696F6E206E616D653D22536176654F725375626D69742220657870723D22234973436F6D706C6574653F277332636F6E6669726D273A2773327375626D697373696F6E2722206175746F457865637574653D2259223E0D0A20202020202020203C7472616E736974696F6E20746F3D22436F6E6669726D22206E616D653D227332636F6E6669726D222F3E0D0A20202020202020203C7472616E736974696F6E20746F3D225375626D697373696F6E22206E616D653D2273327375626D697373696F6E222F3E0D0A202020203C2F6465636973696F6E3E0D0A202020203C7461736B206E616D653D22436F6E6669726D2220646973706C61794E616D653D22E7A1AEE8AEA42220706572666F726D547970653D22414C4C22207461736B547970653D224D616A6F72220D0A20202020202020202020706F7374496E746572636570746F72733D22656E67696E652E66696C7465722E436F6E6669726D22203E0D0A20202020202020203C7472616E736974696F6E20746F3D225375626D697422206E616D653D2263327375626D6974222F3E0D0A202020203C2F7461736B3E0D0A202020203C7461736B206E616D653D225375626D69742220646973706C61794E616D653D22E68F90E4BAA42220706572666F726D547970653D22414C4C22207461736B547970653D224D616A6F72222061737369676E6D656E7448616E646C65723D22656E67696E652E706F6C652E506F6C65223E0D0A20202020202020203C7472616E736974696F6E20746F3D22417070726F76616C4279436F6C22206E616D653D227332617070726F76616C222F3E0D0A202020203C2F7461736B3E0D0A202020203C7461736B20206E616D653D22417070726F76616C4279436F6C2220646973706C61794E616D653D22E999A2E7B3BBE5AEA1E6A0B8222061737369676E6D656E7448616E646C65723D22656E67696E652E706F6C652E506F6C65223E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D226465636973696F6E3122206E616D653D226332643122202F3E0D0A202020203C2F7461736B3E0D0A202020203C6465636973696F6E206E616D653D226465636973696F6E312220657870723D22234465634279436F6C3F2764313273273A27643132742722206175746F457865637574653D225922203E0D0A20202020202020203C7472616E736974696F6E20746F3D22417070726F76616C427953636822206E616D653D2264313273222F3E0D0A20202020202020203C7472616E736974696F6E20746F3D225375626D697373696F6E22206E616D653D2264313274222F3E0D0A202020203C2F6465636973696F6E3E0D0A202020203C7461736B206E616D653D22417070726F76616C4279536368220D0A20202020202020202020646973706C61794E616D653D22E983A8E997A8E5AEA1E689B9222061737369676E6D656E7448616E646C65723D22656E67696E652E706F6C652E506F6C65223E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D226465636973696F6E3222206E616D653D227332643222202F3E0D0A202020203C2F7461736B3E0D0A202020203C6465636973696F6E20206E616D653D226465636973696F6E322220657870723D222344656342794465703F2764323265273A27643232742722206175746F457865637574653D225922203E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D22656E6422206E616D653D226432326522202F3E0D0A20202020202020203C7472616E736974696F6E206F66667365743D22302C2D31302220746F3D225375626D697373696F6E222020673D223835362C3631373B3834372C31393522206E616D653D226432327422202F3E0D0A202020203C2F6465636973696F6E3E0D0A202020203C656E64206E616D653D22656E642220706F7374496E746572636570746F72733D22656E67696E652E66696C7465722E436F6D706C657465223E0D0A202020203C2F656E643E0D0A3C2F70726F636573733E, '0', '2015-04-01 08:34:43', null);
COMMIT;

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`process_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`operator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`surrogate`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`odate`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sdate`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`edate`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_surrogate
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task` (
`id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`version`  int(11) NOT NULL ,
`order_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`display_name`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`operator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`finish_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`expire_time`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`action_url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_type`  int(11) NULL DEFAULT NULL ,
`perform_type`  int(11) NULL DEFAULT NULL ,
`parent_task_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`variable`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_task
-- ----------------------------
BEGIN;
INSERT INTO `wf_task` VALUES ('07b664692b7c4b6198c3bd86eb10dfd4', '0', 'b2de3e6951fe425d8f7e965458d56e46', 'Submit', '提交', null, '2015-04-01 10:53:07', null, null, '', '0', '0', 'f68af7598ef3449b9294ed208ae7b8ff', '{\"Status\":\"Complete\",\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"zss\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"},\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"2\",\"paperName\":\"论文\",\"staName\":\"2\",\"issn\":\"1\",\"IsComplete\":\"true\",\"issue\":\"1\",\"WF_Actor\":\"zss\",\"staId\":\"2\",\"bePage\":\"1\",\"mag_name\":\"\",\"vol\":\"1\",\"gradeName\":\"1\",\"S-ACTOR\":\"zss\",\"cn\":\"1\",\"grScore\":\"2\",\"colType\":\"1\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"},\"WF_Type\":\"论文\"}'), ('085b49e04b2b4d8ba2d5bc42882ba8ea', '0', 'baa8289a37b942c79a595c3b9ca21df4', 'Submit', '提交', null, '2015-04-01 10:22:43', null, null, '', '0', '0', 'a8bdacdcbab047fa807e5788d5c48d66', '{\"Status\":\"Complete\",\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"zss\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"},\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"fawe\",\"staName\":\"awefawe\",\"issn\":\"afe\",\"IsComplete\":\"true\",\"issue\":\"trjsae\",\"WF_Actor\":\"zss\",\"staId\":\"\",\"bePage\":\"awe\",\"mag_name\":\"\",\"vol\":\"ewf\",\"gradeName\":\"af\",\"S-ACTOR\":\"zss\",\"cn\":\"rha\",\"grScore\":\"\",\"colType\":\"h\",\"upLoadPaper\":\"faw\",\"unitId\":\"fawe\"},\"WF_Type\":\"论文\"}'), ('85dd8026c779491d8ceb6846fa43adc3', '0', '0cee40d9001146dfb44aded00d9c94bc', 'Submit', '提交', null, '2015-04-01 11:25:36', null, null, '', '0', '0', '0be203e6c7084a98a795f03727f336a5', '{\"Status\":\"Complete\",\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"zss\",\"Details\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"},\"WF_0_Submission\":{\"paperType\":\"01\",\"pubDate\":\"awfe\",\"paperRole\":\"lhuilh\",\"paperName\":\"fawefa\",\"staName\":\"luil\",\"issn\":\"wefa\",\"IsComplete\":\"true\",\"issue\":\"wef\",\"WF_Actor\":\"zss\",\"staId\":\"lhu\",\"bePage\":\"fawefa\",\"mag_name\":\"\",\"vol\":\"feawe\",\"gradeName\":\"fawe\",\"S-ACTOR\":\"zss\",\"cn\":\"efawef\",\"grScore\":\"ilhuil\",\"colType\":\"awe\",\"upLoadPaper\":\"\",\"unitId\":\"fawef\"},\"WF_Type\":\"论文\"}'), ('97b4e108ede842c8900d64f62ceaa7b7', '2', '36f2cf1928854250aa10e6321e61404a', 'Confirm', '确认', '', '2015-04-01 08:37:23', null, null, '', '0', '0', 'd5d0f1d1866549f0b4c5e47572e31602', '{\"Status\":\"Uncomplete\",\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"zhangsan\",\"Details\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"IsComplete\":\"true\",\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_Type\":\"paper\",\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan,zhangsan\"}}'), ('a2ca3ece622945749971e399605f6b81', '0', '341bacae12674e13aac30ac77991cb0f', 'Submission', '填写申请', null, '2015-04-02 14:02:01', null, null, '', '0', '0', 'start', '{\"S-ACTOR\":\"xgfan\",\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"paper\"}'), ('dbd312e91ca84375983e22bca338fbc0', '0', 'b3ae84ec09a54b9bade54dbf69cb2a09', 'Submit', '提交', null, '2015-04-01 10:49:34', null, null, '', '0', '0', 'f55a4e136ba949a6a5cb2e8a7cd1181a', '{\"Status\":\"Complete\",\"WF_1_Confirm\":{\"S-ACTOR\":\"zss\"},\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"zss\",\"Details\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"},\"WF_0_Submission\":{\"paperType\":\"02\",\"pubDate\":\"efawefawef\",\"paperRole\":\"\",\"paperName\":\"论文名称\",\"staName\":\"\",\"IsComplete\":\"true\",\"conferType\":\"12\",\"WF_Actor\":\"zss\",\"conferTime\":\"12\",\"staId\":\"\",\"conferAddr\":\"123\",\"S-ACTOR\":\"zss\",\"grScore\":\"\",\"conferNm\":\"123124\",\"upLoadPaper\":\"faw\",\"unitId\":\"署名单位\"},\"WF_Type\":\"论文\"}'), ('e0cc76ddf8a74e21b7da7926aaa20dd8', '0', 'c07e81fb10c645acbafe37e0767a5f77', 'Submission', '填写申请', null, '2015-04-01 11:24:33', null, null, '', '0', '0', 'start', '{\"S-ACTOR\":\"zss\",\"WF_Col\":\"信息工程学院\",\"WF_Type\":\"论文\"}'), ('e4c31e8cacae4f82979611e0656395ba', '0', '6ec3eb14e2884c02873ac9328be20f25', 'Submit', '提交', null, '2015-04-01 08:36:39', null, null, '', '0', '0', '54c4f3ee6f3f475bbc9b3b31edc25527', '{\"Status\":\"Complete\",\"WF_2_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"WF_Col\":\"信息工程学院\",\"S-ACTOR\":\"xgfan\",\"WF_4_Confirm\":{\"S-ACTOR\":\"xgfan\"},\"WF_3_Submission\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"Details\":{\"awef\":\"fawef\",\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"true\",\"WF_Actor\":\"xgfan\",\"fewaw\":\"aef\"},\"WF_0_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan\"},\"WF_Type\":\"paper\",\"WF_1_Submission\":{\"S-ACTOR\":\"xgfan\",\"IsComplete\":\"false\",\"WF_Actor\":\"xgfan,zhangsan,fawefawe\"}}');
COMMIT;

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor` (
`task_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`actor_id`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`task_id`, `actor_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_task_actor
-- ----------------------------
BEGIN;
INSERT INTO `wf_task_actor` VALUES ('07b664692b7c4b6198c3bd86eb10dfd4', 'zss'), ('085b49e04b2b4d8ba2d5bc42882ba8ea', 'zss'), ('85dd8026c779491d8ceb6846fa43adc3', 'zss'), ('97b4e108ede842c8900d64f62ceaa7b7', 'zhangsan'), ('a2ca3ece622945749971e399605f6b81', 'xgfan'), ('dbd312e91ca84375983e22bca338fbc0', 'zss'), ('e0cc76ddf8a74e21b7da7926aaa20dd8', 'zss'), ('e4c31e8cacae4f82979611e0656395ba', 'xgfan');
COMMIT;

-- ----------------------------
-- Table structure for wf_workitem
-- ----------------------------
DROP TABLE IF EXISTS `wf_workitem`;
CREATE TABLE `wf_workitem` (
`task_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`process_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_no`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`process_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`instance_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`parent_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`creator`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_create_time`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_expire_time`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`order_variable`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_key`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`operator`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_create_time`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_end_time`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_expire_time`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`action_url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`task_type`  int(11) NULL DEFAULT NULL ,
`perform_type`  int(11) NULL DEFAULT NULL ,
`task_variable`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`task_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of wf_workitem
-- ----------------------------
BEGIN;
COMMIT;
