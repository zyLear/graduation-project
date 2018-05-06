-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.59 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 internal_control 的数据库结构
CREATE DATABASE IF NOT EXISTS `internal_control` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `internal_control`;


-- 导出  表 internal_control.t_asset 结构
CREATE TABLE IF NOT EXISTS `t_asset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_number` varchar(32) NOT NULL DEFAULT '0',
  `asset_number` varchar(32) NOT NULL,
  `asset_type` varchar(32) NOT NULL,
  `remark` varchar(1024) NOT NULL,
  `prices` double NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project 结构
CREATE TABLE IF NOT EXISTS `t_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_number` varchar(32) NOT NULL,
  `project_name` varchar(64) NOT NULL,
  `applicant` varchar(16) NOT NULL,
  `application_department` varchar(16) NOT NULL,
  `project_content` text NOT NULL,
  `project_budget` double unsigned NOT NULL,
  `project_status` tinyint(4) NOT NULL,
  `approval_comment` varchar(1024) DEFAULT NULL,
  `file_path` varchar(128) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`project_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project_bid 结构
CREATE TABLE IF NOT EXISTS `t_project_bid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid_number` varchar(32) NOT NULL,
  `bidding_number` varchar(32) NOT NULL,
  `bid_company` varchar(32) NOT NULL,
  `bid_content` text NOT NULL,
  `bid_prices` double unsigned NOT NULL,
  `bid_status` tinyint(4) NOT NULL,
  `file_path` varchar(128) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_account` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `index_bidding_number` (`bidding_number`),
  KEY `index_create_account` (`create_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project_bidding 结构
CREATE TABLE IF NOT EXISTS `t_project_bidding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bidding_number` varchar(32) NOT NULL,
  `project_number` varchar(32) NOT NULL,
  `bidding_name` varchar(32) NOT NULL,
  `bidding_content` text NOT NULL,
  `prices` double unsigned NOT NULL,
  `bidding_status` tinyint(4) NOT NULL,
  `file_path` varchar(128) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `bidding_start_time` datetime NOT NULL,
  `bidding_end_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bidding_number` (`bidding_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project_budget 结构
CREATE TABLE IF NOT EXISTS `t_project_budget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_number` varchar(32) NOT NULL,
  `budget_aspect` varchar(32) NOT NULL,
  `budget_content` text NOT NULL,
  `budget_money` double unsigned NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_number_budget_aspect` (`project_number`,`budget_aspect`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project_contract 结构
CREATE TABLE IF NOT EXISTS `t_project_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid_number` varchar(32) NOT NULL,
  `contract_number` varchar(32) NOT NULL,
  `contract_name` varchar(32) NOT NULL,
  `contract_content` text NOT NULL,
  `contract_money` double unsigned NOT NULL,
  `contract_status` tinyint(4) NOT NULL,
  `finish_day` date DEFAULT NULL,
  `file_path` varchar(128) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_contract_number` (`contract_number`),
  UNIQUE KEY `unique_contract_name` (`contract_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_project_contract_item 结构
CREATE TABLE IF NOT EXISTS `t_project_contract_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_number` varchar(32) NOT NULL,
  `item_content` text NOT NULL,
  `item_money` double unsigned NOT NULL,
  `finish_day` date DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。


-- 导出  表 internal_control.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  `authority` tinyint(4) NOT NULL,
  `is_deleted` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_account` (`account`),
  KEY `index_account_password` (`account`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
