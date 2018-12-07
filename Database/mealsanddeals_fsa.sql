-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: mealsanddeals
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fsa`
--

DROP TABLE IF EXISTS `fsa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fsa` (
  `name` varchar(45) DEFAULT NULL,
  `sex` enum('Male','Female') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `address` text,
  `email` varchar(45) NOT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `fsausername` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `taxid` bigint(20) DEFAULT NULL,
  `cookingcertificate` enum('Yes','No') DEFAULT NULL,
  `businesscertificate` enum('Yes','No') DEFAULT NULL,
  `validation` enum('Active','Inactive') DEFAULT NULL,
  PRIMARY KEY (`fsausername`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `taxid_UNIQUE` (`taxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fsa`
--

LOCK TABLES `fsa` WRITE;
/*!40000 ALTER TABLE `fsa` DISABLE KEYS */;
INSERT INTO `fsa` VALUES ('ash','Male','1990-01-01','','','1','','ash1','ae9ef5e140f92d85ff0fe0b528d041a2',1,'Yes','Yes','Active'),('ashkan','Male','1988-04-19','Iranian','MPS3','2','+491626092491','ash11866','2852f697a9f8581725c6fc6a5472a2e5',12345678910,'Yes','Yes','Active'),('Ashkan','Male','1988-04-19','Iranian','MPS 45','ashkan.esshaghi@gmail.com','456123798','ash3','27ce24963b5084410e389bc250cdae82',5,'Yes','Yes','Active'),('ash','Male','1999-12-30','Iranian','MPS34','ashkan.ess@gmail.com','9','ash4','255c3109cf58817d3718b8707b890a9a',4,'Yes','Yes','Active'),('Ashkan','Male','1880-09-20','Iranian','BS5','ashkan.ee@gmail.com','465','ash7','3d415832391b3f7c0c2f4d735dbe8bbb',9,'Yes','Yes','Active');
/*!40000 ALTER TABLE `fsa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-07 13:06:45
