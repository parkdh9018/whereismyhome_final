-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: happyhouse
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `commentno` int NOT NULL AUTO_INCREMENT,
  `articleno` int NOT NULL,
  `comment` text NOT NULL,
  `userid` varchar(20) NOT NULL,
  `regtime` datetime NOT NULL,
  PRIMARY KEY (`commentno`),
  KEY `comment_to_board_fk` (`userid`),
  CONSTRAINT `comment_to_board_fk` FOREIGN KEY (`userid`) REFERENCES `board` (`userid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,1,'댓글','ssafy','2022-11-23 11:09:02'),(8,1,'dsd','ssafy','2022-11-23 11:18:23'),(9,1,'dsd','ssafy','2022-11-23 11:19:42'),(11,1,'uyu','ssafy','2022-11-23 11:30:48'),(12,1,'rtr','ssafy','2022-11-23 11:31:04'),(13,1,'cdc','ssafy','2022-11-23 11:35:01'),(14,1,'eeee','ssafy','2022-11-23 11:35:05'),(15,1,'eee','ssafy','2022-11-23 11:35:07'),(16,1,'cscs','ssafy','2022-11-23 11:39:51'),(17,1,'qqqqqqq','ssafy','2022-11-23 11:39:56'),(18,1,'uu','ssafy','2022-11-23 11:41:12'),(19,1,'ds','ssafy','2022-11-23 11:47:54'),(20,1,'sss','ssafy','2022-11-23 11:47:59'),(21,1,'jj','ssafy','2022-11-23 11:50:03'),(22,1,'temp','ssafy','2022-11-23 11:52:14'),(23,1,'temp2','ssafy','2022-11-23 11:54:22'),(24,1,'temp3','ssafy','2022-11-23 11:54:35'),(25,1,'temp4','ssafy','2022-11-23 11:55:23'),(26,1,'ㅎㄹ','ssafy','2022-11-23 12:08:14'),(27,1,'ㅗ호','ssafy','2022-11-23 12:09:05'),(28,1,'ㅁㄴㅇㄹ','ssafy','2022-11-23 12:11:48'),(29,1,'ㅂㅈㄷㅂㅈㄷ','ssafy','2022-11-23 12:12:12'),(30,1,'ㅂㅈㄷㅂㄷㅈ','ssafy','2022-11-23 12:12:21'),(31,1,'ㅂㅈㄷㅈㅈㅈ','ssafy','2022-11-23 12:13:06'),(32,1,'ㅁㄴㅇㅁㄴㅇ','ssafy','2022-11-23 12:56:38'),(33,1,'ㅁㄴㅇ','ssafy','2022-11-23 13:00:59'),(34,1,'ㅂㅈㄷㄱㄷㄷ','ssafy','2022-11-23 13:06:42'),(35,1,'1234','ssafy','2022-11-23 13:08:35'),(36,1,'4234','ssafy','2022-11-23 13:10:17'),(37,1,'334124','ssafy','2022-11-23 13:10:50'),(38,1,'110','ssafy','2022-11-23 13:11:44'),(39,1,'gbhnjm,./','ssafy','2022-11-23 13:21:16'),(40,1,'dwd','ssafy','2022-11-23 13:33:24'),(41,1,'dsds','ssafy','2022-11-23 13:34:08'),(42,1,'[ppp','ssafy','2022-11-23 13:34:43'),(43,1,'egge','ssafy','2022-11-23 13:46:07'),(44,1,'fefe','ssafy','2022-11-23 13:46:13'),(45,1,'ewe','ssafy','2022-11-23 13:54:07'),(46,1,'ssss','ssafy','2022-11-23 14:26:10'),(47,1,'sds','ssafy','2022-11-23 14:28:43'),(48,1,'xsdsdsssssssssssssss','ssafy','2022-11-23 14:29:26'),(51,1,'댓글','ssafy','2022-11-23 15:38:37'),(52,1,'댓글','ssafy','2022-11-23 15:47:50'),(54,3,'vcv','ssafy','2022-11-24 14:51:11'),(55,3,'sdsd','ssafy','2022-11-24 14:55:50'),(56,3,'ㅊㅊ','ssafy','2022-11-24 15:28:08'),(57,3,'ㅎㅎㅎ홓','ssafy','2022-11-24 15:31:45'),(58,7,'ㅇㅇㅇ','ssafy','2022-11-24 16:14:56'),(59,8,'fgfg','admin','2022-11-24 17:28:11');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-24 17:59:29
