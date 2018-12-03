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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `orderid` bigint(20) NOT NULL AUTO_INCREMENT,
  `ordertime` date DEFAULT NULL,
  `deliverytype` varchar(45) DEFAULT NULL,
  `foodprice` float DEFAULT NULL,
  `deliverycharge` float DEFAULT NULL,
  `totalamount` tinyint(4) DEFAULT NULL,
  `orderlocation` text,
  `paymenttype` varchar(45) DEFAULT NULL,
  `fsausername` varchar(45) DEFAULT NULL,
  `customerusername` varchar(45) DEFAULT NULL,
  `foodid` bigint(20) DEFAULT NULL,
  `ranking` enum('Ranked','Unranked') DEFAULT NULL,
  `status` enum('Open','Colosed') DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `fsausernameorder_idx` (`fsausername`),
  KEY `customerusernameorder_idx` (`customerusername`),
  KEY `foodidorder_idx` (`foodid`),
  CONSTRAINT `customerusernameorder` FOREIGN KEY (`customerusername`) REFERENCES `customer` (`customerusername`),
  CONSTRAINT `foodidorder` FOREIGN KEY (`foodid`) REFERENCES `food` (`foodid`),
  CONSTRAINT `fsausernameorder` FOREIGN KEY (`fsausername`) REFERENCES `fsa` (`fsausername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-03 15:35:09
