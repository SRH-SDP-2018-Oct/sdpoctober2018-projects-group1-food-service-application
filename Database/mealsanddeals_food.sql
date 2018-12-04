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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `food` (
  `nameofmeal` varchar(45) DEFAULT NULL,
  `foodname` varchar(45) DEFAULT NULL,
  `foodtype` varchar(45) DEFAULT NULL,
  `hotorcold` varchar(45) DEFAULT NULL,
  `available` tinyint(4) DEFAULT NULL,
  `totalamount` tinyint(4) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `deliverytype` enum('Yes','No') DEFAULT NULL,
  `cash` enum('Yes','No') DEFAULT NULL,
  `online` enum('Yes','No') DEFAULT NULL,
  `date` date DEFAULT NULL,
  `dateofadding` datetime DEFAULT NULL,
  `foodid` bigint(20) NOT NULL AUTO_INCREMENT,
  `fsausername` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`foodid`),
  KEY `fsausernamefood_idx` (`fsausername`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES ('Breakfast','chicken','Iranian','Hot',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',1,'ash2'),('Breakfast','rice','Italian','Hot',10,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',2,'ash4'),('Breakfast','rice','Indian','Cold',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',3,'ash2'),('Breakfast','rice','German','Hot',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',4,'ash4'),('Breakfast','rice','Turkish','Hot',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',5,'ash2'),('Breakfast','rice','Indian','Cold',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',6,'ash4'),('Breakfast','rice','Iranian','Cold',10,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',7,'ash4'),('Breakfast','rice','Indian','Hot',0,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',8,'ash2'),('Breakfast','rice','German','Cold',10,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',9,'ash2'),('Breakfast','rice','Indian','Cold',10,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',10,'ash2'),('Breakfast','rice','South Korean','Cold',0,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',11,'ash2'),('Breakfast','rice','South Korean','Hot',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',12,'ash2'),('Breakfast','rice','North Korean','Cold',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',13,'ash4'),('Breakfast','rice','Bla','Cold',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',14,'ash4'),('Breakfast','rice','French','Hot',10,10,10,'Yes','Yes','Yes','2018-12-01','2018-12-01 12:00:00',15,'ash2'),('Breakfast','rice','Indian','Cold',0,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',16,'ash2'),('Breakfast','rice','Indian','Hot',10,10,10,'Yes','Yes','No','2018-12-01','2018-12-01 12:00:00',17,'ash4'),('Lunch','rice','Indian','Hot',2,10,12,'No','Yes','No','2018-12-04','2018-12-04 12:12:12',18,'ash8');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-04 18:35:43
