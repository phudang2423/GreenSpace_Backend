-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: phu
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `cart_item_image_url`
--

DROP TABLE IF EXISTS `cart_item_image_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item_image_url` (
  `cart_item_id` bigint NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  KEY `FKeni9pu8ly6mx33aw6ntiy1gge` (`cart_item_id`),
  CONSTRAINT `FKeni9pu8ly6mx33aw6ntiy1gge` FOREIGN KEY (`cart_item_id`) REFERENCES `cart_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item_image_url`
--

LOCK TABLES `cart_item_image_url` WRITE;
/*!40000 ALTER TABLE `cart_item_image_url` DISABLE KEYS */;
INSERT INTO `cart_item_image_url` VALUES (1,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv'),(1,'1hXCGCt2kOVsuGQDpRb7kRyXtn6AVPaFX'),(1,'1TsZ29USfrmVbVs0TC2RmZLO2Hmu0vzGV'),(1,'14uqoK0s5mP_1q8CSZXQup'),(2,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9'),(2,'1eCKmDrpcAFqC7rpZG-0X1GsYmlzzdXYt'),(2,'1ZFY0LHYjiQuPfV5VMEbpU0h0jY1c1o_j');
/*!40000 ALTER TABLE `cart_item_image_url` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 11:25:31
