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
  `email` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `fsausername` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `taxid` bigint(20) DEFAULT NULL,
  `cookingcertificate` enum('Yes','No') DEFAULT NULL,
  `businesscertificate` enum('Yes','No') DEFAULT NULL,
  `validation` enum('Active','Inactive') DEFAULT NULL,
  PRIMARY KEY (`fsausername`),
  UNIQUE KEY `taxid_UNIQUE` (`taxid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fsa`
--

LOCK TABLES `fsa` WRITE;
/*!40000 ALTER TABLE `fsa` DISABLE KEYS */;
INSERT INTO `fsa` VALUES ('ash','Male','1990-01-01','','','','','ash1','ae9ef5e140f92d85ff0fe0b528d041a2',1,'Yes','Yes','Active'),('ashkan','Male','1988-04-19','Iranian','MPS3','ashkan.ee@gmail.com','+491626092491','ash11866','19772e1d7acc5cb84d4a2a45b14e204f',12345678910,'Yes','Yes','Inactive'),('ash','Male','1990-01-01','','','','','ash2','6b7d9c0dc5570483e4ec4075398ab85e',2,'Yes','Yes','Active'),('ash','Male','1990-01-01','','','','','ash3','27ce24963b5084410e389bc250cdae82',3,'Yes','Yes','Inactive'),('ash','Male','1990-01-01','','','','','ash4','255c3109cf58817d3718b8707b890a9a',4,'Yes','Yes','Active'),('ash5','Male','1990-01-01','','','','','ash5','a2f3f476b7c18f833f40b15ed2c035cc',5,'Yes','Yes','Inactive'),('ash','Male','1990-01-01','','','','','ash8','8df6f60c9b6093006b08c78a8efe4708',8,'Yes','Yes','Active'),('Muddasssir','Male','1990-01-01','Indian','MPS3','a@@.com','+442554646','muddassir2324','467a411cac6c16c0ae86c4e49ce83d1a',4497414663,'Yes','Yes','Active');
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

-- Dump completed on 2018-12-03 15:35:08
