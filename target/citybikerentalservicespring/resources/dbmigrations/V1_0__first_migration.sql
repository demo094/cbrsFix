-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bikerentalservicedb
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,''),(2,''),(3,''),(4,NULL),(5,NULL),(6,NULL);
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` decimal(19,2) NOT NULL,
  `time` datetime NOT NULL,
  `bike_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfcaakajwrloo8txkl53kbeb9b` (`bike_id`),
  KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`),
  CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfcaakajwrloo8txkl53kbeb9b` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,6.00,'2016-10-05 16:05:33',2,2),(4,6.00,'2016-10-05 16:37:09',1,2),(5,6.00,'2016-10-05 16:41:09',2,2),(6,0.00,'2016-10-05 16:41:17',2,2),(7,0.00,'2016-10-05 16:53:53',2,2),(8,0.00,'2016-10-10 15:55:15',2,2),(9,0.00,'2016-10-11 09:41:32',1,2),(10,0.00,'2016-10-11 10:01:18',2,2),(11,0.00,'2016-10-11 14:56:21',1,2);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_interval`
--

DROP TABLE IF EXISTS `price_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_interval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` bigint(20) DEFAULT NULL,
  `price` decimal(19,2) NOT NULL,
  `start` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_interval`
--

LOCK TABLES `price_interval` WRITE;
/*!40000 ALTER TABLE `price_interval` DISABLE KEYS */;
INSERT INTO `price_interval` VALUES (1,20,0.00,0),(2,60,1.00,20),(3,120,2.00,60),(4,NULL,3.00,120);
/*!40000 ALTER TABLE `price_interval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_history`
--

DROP TABLE IF EXISTS `rental_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `begin_slot_id` int(11) NOT NULL,
  `bike_id` int(11) NOT NULL,
  `end_slot_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKta3filhdd36y68fl683cvsq0i` (`begin_slot_id`),
  KEY `FKry3b3ii42x7f170i4o06cbjlb` (`bike_id`),
  KEY `FK20xpkg4rvn6l66ack8e0n9651` (`end_slot_id`),
  KEY `FKchic8sx425bch16wqaaeo23xn` (`user_id`),
  CONSTRAINT `FK20xpkg4rvn6l66ack8e0n9651` FOREIGN KEY (`end_slot_id`) REFERENCES `slot` (`id`),
  CONSTRAINT `FKchic8sx425bch16wqaaeo23xn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKry3b3ii42x7f170i4o06cbjlb` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`),
  CONSTRAINT `FKta3filhdd36y68fl683cvsq0i` FOREIGN KEY (`begin_slot_id`) REFERENCES `slot` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_history`
--

LOCK TABLES `rental_history` WRITE;
/*!40000 ALTER TABLE `rental_history` DISABLE KEYS */;
INSERT INTO `rental_history` VALUES (3,'2016-10-05 15:49:37','2016-10-05 16:05:32',1,2,1,2),(4,'2016-10-05 16:18:55','2016-10-05 16:37:09',3,1,2,2),(5,'2016-10-05 16:38:06','2016-10-05 16:41:09',1,2,1,2),(6,'2016-10-05 16:41:14','2016-10-05 16:41:17',1,2,1,2),(7,'2016-10-05 16:53:49','2016-10-05 16:53:53',1,2,1,2),(8,'2016-10-10 15:55:09','2016-10-10 15:55:15',1,2,3,2),(9,'2016-10-11 09:41:26','2016-10-11 09:41:32',2,1,1,2),(10,'2016-10-11 10:01:14','2016-10-11 10:01:18',3,2,2,2),(11,'2016-10-11 14:56:16','2016-10-11 14:56:21',1,1,6,2);
/*!40000 ALTER TABLE `rental_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slot`
--

DROP TABLE IF EXISTS `slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bike_id` int(11) DEFAULT NULL,
  `station_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmnxdq3te3jyut4dltip8bqrbd` (`bike_id`),
  KEY `FKmd5mpewg7bd06ey42jptvatmb` (`station_id`),
  CONSTRAINT `FKmd5mpewg7bd06ey42jptvatmb` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`),
  CONSTRAINT `FKmnxdq3te3jyut4dltip8bqrbd` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slot`
--

LOCK TABLES `slot` WRITE;
/*!40000 ALTER TABLE `slot` DISABLE KEYS */;
INSERT INTO `slot` VALUES (1,NULL,1),(2,2,1),(3,NULL,1),(4,NULL,1),(5,NULL,1),(6,1,2),(7,NULL,2),(8,3,2),(9,NULL,2),(10,NULL,2),(11,NULL,2);
/*!40000 ALTER TABLE `slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'3 Maja 17c','Lublin','3Maja17c','ordinary'),(2,'Nadbystrycka 38','Lublin','Nadbystrzycka38','ordinary');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `body` varchar(255) NOT NULL,
  `expiration_date` datetime NOT NULL,
  `token_type` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (2,'3de58184-f6ab-4a4a-9f26-de93a123132f','2016-10-06 13:24:20',0,2),(3,'3bb12aee-0bd6-4fd9-ab90-6d8001517e6e','2016-10-06 16:43:19',1,2),(4,'6ae66441-4d47-4868-9e66-8fe957ba26a0','2016-10-12 09:49:12',0,3),(5,'8a65759f-5b55-4827-ac5c-16cfe97ecd9b','2016-10-12 10:01:28',1,2);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activated` int(11) NOT NULL,
  `balance` decimal(19,2) NOT NULL,
  `create_time` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `rented_bike_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7t5yrpxjyj3xr7stav3p68b8p` (`rented_bike_id`),
  CONSTRAINT `FK7t5yrpxjyj3xr7stav3p68b8p` FOREIGN KEY (`rented_bike_id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,1,218.21,'2016-10-05 13:24:20','cbrs.testuser1@gmail.com','$2a$10$vE39.IXvuOEXk9BMxqrLb.9/mympxUXuLhf3tU4rlPtyNbRujmsvO',NULL),(3,1,0.00,'2016-10-11 09:49:12','cbrs.testuser1+1@gmail.com','$2a$10$YO300Wfy6S/Qc2g65DUNpu97gNKnTRmZm59gxBMQw1EPr5FcK2kka',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKmknhyioq8hh8seoxe1fy6qo86` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmknhyioq8hh8seoxe1fy6qo86` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,1),(2,2),(3,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-12 11:38:53
