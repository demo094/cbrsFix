DROP TABLE IF EXISTS `price_interval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_interval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `end` bigint(20) DEFAULT NULL,
  `price` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_interval`
--

LOCK TABLES `price_interval` WRITE;
/*!40000 ALTER TABLE `price_interval` DISABLE KEYS */;
INSERT INTO `price_interval` VALUES (1,20,0.00),(2,60,1.00),(3,120,2.00),(4,NULL,3.00);
/*!40000 ALTER TABLE `price_interval` ENABLE KEYS */;
UNLOCK TABLES;
