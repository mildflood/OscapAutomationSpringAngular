-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: OscapAutomation
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CompletedScantbl`
--

DROP TABLE IF EXISTS `CompletedScantbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompletedScantbl` (
  `scanID` int(10) NOT NULL,
  `status` varchar(24) NOT NULL,
  PRIMARY KEY (`scanID`),
  CONSTRAINT `CompletedScantbl_ibfk_1` FOREIGN KEY (`scanID`) REFERENCES `ScheduledScantbl` (`scanID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompletedScantbl`
--

LOCK TABLES `CompletedScantbl` WRITE;
/*!40000 ALTER TABLE `CompletedScantbl` DISABLE KEYS */;
INSERT INTO `CompletedScantbl` VALUES (4,'ready');
/*!40000 ALTER TABLE `CompletedScantbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ScheduledScantbl`
--

DROP TABLE IF EXISTS `ScheduledScantbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ScheduledScantbl` (
  `scanID` int(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `nodename` varchar(15) NOT NULL,
  `monthOfScan` varchar(15) NOT NULL,
  `dayOfMonth` varchar(15) NOT NULL,
  `dayOfWeek` varchar(15) NOT NULL,
  `hourOfScan` varchar(10) NOT NULL,
  `minuteOfScan` varchar(10) NOT NULL,
  `am_pm` varchar(10) NOT NULL,
  `status` varchar(24) NOT NULL,
  PRIMARY KEY (`scanID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ScheduledScantbl`
--

LOCK TABLES `ScheduledScantbl` WRITE;
/*!40000 ALTER TABLE `ScheduledScantbl` DISABLE KEYS */;
INSERT INTO `ScheduledScantbl` VALUES (4,'Jonas','Okwara','Owl','8','19','4','11','30','1','ready');
/*!40000 ALTER TABLE `ScheduledScantbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER scanTrigger
AFTER INSERT ON ScheduledScantbl 
FOR EACH ROW
BEGIN
INSERT INTO CompletedScantbl(scanID, status)
SELECT  scanID, status from ScheduledScantbl; 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `UserAuthtbl`
--

DROP TABLE IF EXISTS `UserAuthtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserAuthtbl` (
  `userID` int(12) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserAuthtbl`
--

LOCK TABLES `UserAuthtbl` WRITE;
/*!40000 ALTER TABLE `UserAuthtbl` DISABLE KEYS */;
INSERT INTO `UserAuthtbl` VALUES (560789864,'Daniel','Okwara','okwarad','chuchu123'),(560789865,'Mary','Jane','janem','jane1234'),(560789866,'John','Doe','jdoe','jdoe1234'),(560789867,'Jonas','Okwara','okwaraj','chu218chu');
/*!40000 ALTER TABLE `UserAuthtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserRoletbl`
--

DROP TABLE IF EXISTS `UserRoletbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserRoletbl` (
  `userID` int(12) NOT NULL,
  `username` varchar(20) NOT NULL,
  `rolename` varchar(20) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserRoletbl`
--

LOCK TABLES `UserRoletbl` WRITE;
/*!40000 ALTER TABLE `UserRoletbl` DISABLE KEYS */;
INSERT INTO `UserRoletbl` VALUES (560789864,'okwarad','user'),(560789865,'janem','admin'),(560789866,'jdoe','user'),(560789867,'okwaraj','admin');
/*!40000 ALTER TABLE `UserRoletbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `completedScheduledScanResults`
--

DROP TABLE IF EXISTS `completedScheduledScanResults`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `completedScheduledScanResults` (
  `scanID` int(10) NOT NULL AUTO_INCREMENT,
  `nodename` varchar(15) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`scanID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `completedScheduledScanResults`
--

LOCK TABLES `completedScheduledScanResults` WRITE;
/*!40000 ALTER TABLE `completedScheduledScanResults` DISABLE KEYS */;
INSERT INTO `completedScheduledScanResults` VALUES (4,'sben1a003','Scan Succeeded'),(5,'sben2a004','Scan Succeeded'),(6,'owl','Scan Succeeded'),(7,'falcon','Scan Succeeded'),(8,'osprey','Scan Succeeded'),(9,'owl','Scan Succeeded'),(10,'osprey','Scan Succeeded');
/*!40000 ALTER TABLE `completedScheduledScanResults` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scanDatatbl`
--

DROP TABLE IF EXISTS `scanDatatbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scanDatatbl` (
  `scanID` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `nodename` varchar(30) NOT NULL,
  `scanstatus` varchar(30) NOT NULL,
  `scandate` varchar(30) NOT NULL,
  `scantime` varchar(30) NOT NULL,
  PRIMARY KEY (`scanID`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scanDatatbl`
--

LOCK TABLES `scanDatatbl` WRITE;
/*!40000 ALTER TABLE `scanDatatbl` DISABLE KEYS */;
INSERT INTO `scanDatatbl` VALUES (77,'Daniel','Okwara','osprey','Succeessful','Thu Jun 21, 2018','10:01 AM'),(78,'Daniel','Okwara','lion','Succeessful','Thu Jun 21, 2018','10:01 AM'),(150,'Daniel','Okwara','hawk','Succeessful','Tue Jun 26, 2018','9:45 AM'),(274,'Daniel','Okwara','owl','Succeessful','Sat Jul 14, 2018','3:49 AM'),(275,'Daniel','Okwara','osprey','Succeessful','Sat Jul 14, 2018','3:49 AM'),(276,'Jonas','Okwara','hawk','Successful','Thu Aug 23, 2018','12:19 PM'),(277,'Jonas','Okwara','owl','Successful','Thu Aug 23, 2018','1:37 PM'),(278,'Daniel','Okwara','hawk','Successful','Mon Aug 27, 2018','3:40 AM'),(279,'Daniel','Okwara','owl','Successful','Mon Aug 27, 2018','3:40 AM'),(280,'Jonas','Okwara','owl','Successful','Thu Aug 30, 2018','9:05 AM'),(281,'Jonas','Okwara','lion','Successful','Thu Aug 30, 2018','9:05 AM'),(282,'Jonas','Okwara','owl','Successful','Thu Aug 30, 2018','11:39 AM'),(283,'Jonas','Okwara','osprey','Successful','Thu Aug 30, 2018','11:39 AM'),(284,'Jonas','Okwara','owl','Scan Failed:','Thu Aug 30, 2018','5:04 PM'),(285,'Jonas','Okwara','owl','Scan Failed:','Thu Aug 30, 2018','5:06 PM'),(286,'Jonas','Okwara','owl','Successful','Thu Aug 30, 2018','5:09 PM'),(287,'Jonas','Okwara','falcon','Scan Failed:','Thu Aug 30, 2018','5:55 PM'),(288,'Jonas','Okwara','falcon','Scan Failed:','Thu Aug 30, 2018','5:58 PM'),(289,'Jonas','Okwara','falcon','Successful','Thu Aug 30, 2018','6:04 PM'),(290,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','6:34 PM'),(291,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','6:36 PM'),(292,'Jonas','Okwara','owl','Successful','Thu Aug 30, 2018','6:44 PM'),(293,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','6:50 PM'),(294,'Jonas','Okwara','falcon','Scan Failed:','Thu Aug 30, 2018','6:50 PM'),(295,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','6:50 PM'),(296,'Jonas','Okwara','owl','Successful','Thu Aug 30, 2018','6:52 PM'),(297,'Jonas','Okwara','osprey','Scan Failed:','Thu Aug 30, 2018','7:07 PM'),(298,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','7:07 PM'),(299,'Jonas','Okwara','lion','Scan Failed:','Thu Aug 30, 2018','7:08 PM'),(300,'Jonas','Okwara','falcon','Successful','Thu Aug 30, 2018','7:26 PM'),(301,'Jonas','Okwara','lion','Successful','Thu Aug 30, 2018','7:28 PM'),(302,'Jonas','Okwara','osprey','Successful','Thu Aug 30, 2018','7:31 PM'),(303,'Jonas','Okwara','owl','Scan Failed:','Wed Sep 5, 2018','11:01 AM'),(304,'Jonas','Okwara','owl','Successful','Wed Sep 5, 2018','10:33 PM'),(305,'Jonas','Okwara','sben1a003','Scan Failed:','Wed Sep 5, 2018','10:36 PM'),(306,'Jonas','Okwara','sben2a004','Scan Failed:','Wed Sep 5, 2018','10:37 PM'),(307,'Jonas','Okwara','sben1a003','Successful','Wed Sep 5, 2018','10:49 PM'),(308,'Jonas','Okwara','sben2a004','Successful','Wed Sep 5, 2018','10:49 PM'),(309,'Jonas','Okwara','sben1a003','Scan Failed:','Wed Sep 5, 2018','11:07 PM'),(310,'Jonas','Okwara','sben1a003','Scan Failed:','Wed Sep 5, 2018','11:09 PM'),(311,'Jonas','Okwara','sben1a003','Scan Failed:','Wed Sep 5, 2018','11:10 PM'),(312,'Jonas','Okwara','sben1a003','Scan Failed:','Wed Sep 5, 2018','11:10 PM'),(313,'Jonas','Okwara','sben1a003','Successful','Wed Sep 5, 2018','11:13 PM'),(314,'Jonas','Okwara','sben2a004','Successful','Wed Sep 5, 2018','11:14 PM'),(315,'Jonas','Okwara','sben2a004','Successful','Wed Sep 5, 2018','11:16 PM'),(316,'Jonas','Okwara','sben1a003','Successful','Wed Sep 5, 2018','11:28 PM'),(317,'Jonas','Okwara','sben2a004','Successful','Wed Sep 5, 2018','11:37 PM'),(318,'Jonas','Okwara','sben1a003','Successful','Thu Sep 6, 2018','12:02 AM'),(319,'Jonas','Okwara','sben1a003','Successful','Thu Sep 6, 2018','9:01 AM'),(320,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:08 AM'),(321,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:41 AM'),(322,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:43 AM'),(323,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:45 AM'),(324,'Jonas','Okwara','sben1a003','Scan Failed:','Thu Sep 6, 2018','9:48 AM'),(325,'Jonas','Okwara','sben1a003','Successful','Thu Sep 6, 2018','9:54 AM'),(326,'Jonas','Okwara','sben1a003','Successful','Thu Sep 6, 2018','9:54 AM'),(327,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:55 AM'),(328,'Jonas','Okwara','sben1a003','Successful','Thu Sep 6, 2018','9:56 AM'),(329,'Jonas','Okwara','sben2a004','Successful','Thu Sep 6, 2018','9:56 AM'),(330,'Jonas','Okwara','falcon','Successful','Thu Sep 6, 2018','9:56 AM'),(331,'Jonas','Okwara','sben1a003','Scan Succeeded On:','Mon Sep 10, 2018','12:21 PM'),(332,'Jonas','Okwara','sben2a004','Scan Succeeded On:','Mon Sep 10, 2018','12:21 PM'),(333,'Jonas','Okwara','sben2a004','Scan Succeeded On:','Sat Sep 15, 2018','5:32 AM'),(334,'Jonas','Okwara','sben1a003','Scan Succeeded:','Wed Sep 19, 2018','12:48 PM'),(335,'Jonas','Okwara','sben2a004','Scan Succeeded:','Wed Sep 19, 2018','12:48 PM');
/*!40000 ALTER TABLE `scanDatatbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-19 14:55:03
