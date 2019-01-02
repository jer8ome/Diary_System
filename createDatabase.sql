-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 diary_system 的数据库结构
CREATE DATABASE IF NOT EXISTS `diary_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `diary_system`;

-- 导出  表 diary_system.diary 结构
CREATE TABLE IF NOT EXISTS `diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `writeDate` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '日期',
  `weather` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '天气',
  `mood` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '心情',
  `title` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '标题',
  `content` varchar(500) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '内容',
  `author` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。
-- 导出  表 diary_system.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '用户名',
  `displayname` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '显示名',
  `password` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '密码',
  `email` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '邮箱',
  `question` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '问题',
  `answer` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '答案',
  `isLogin` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '登录状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
