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
-- Table structure for table `product_image_url`
--

DROP TABLE IF EXISTS `product_image_url`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image_url` (
  `product_product_id` bigint NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  KEY `FKhv2xlrfp6unt0yl50awn1x6re` (`product_product_id`),
  CONSTRAINT `FKhv2xlrfp6unt0yl50awn1x6re` FOREIGN KEY (`product_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image_url`
--

LOCK TABLES `product_image_url` WRITE;
/*!40000 ALTER TABLE `product_image_url` DISABLE KEYS */;
INSERT INTO `product_image_url` VALUES (53,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9'),(53,'1eCKmDrpcAFqC7rpZG-0X1GsYmlzzdXYt'),(53,'1ZFY0LHYjiQuPfV5VMEbpU0h0jY1c1o_j'),(54,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl'),(54,'1rkXZ5y7iGctSEKzm_GX0tkqqxi1HrWfc'),(54,'1rkXZ5y7iGctSEKzm_GX0tkqqxi1HrWfc'),(54,'1rkXZ5y7iGctSEKzm_GX0tkqqxi1HrWfc'),(55,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa'),(55,'1Ka8479KlAgWySdSBYa2kmyFGO4vjJLvv'),(55,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa'),(102,'1BpYz7A-T3IqGpbnb--kUN87Fp7jnSdjm'),(102,'1y6-3JGWer6hFZeJCw4UVfe6im5F-XR0l'),(102,'1dXtr9htI8AfMITxoJM085p-VMKfcQrVe'),(152,'1IzVCKAMETp6GBDSfk6dAcAduPn_zCoUN'),(152,'1IzVCKAMETp6GBDSfk6dAcAduPn_zCoUN'),(152,'1IzVCKAMETp6GBDSfk6dAcAduPn_zCoUN'),(56,'1wNXGL15v2sMg9H3G18PuH9RY6wrA5P7U'),(56,'1wNXGL15v2sMg9H3G18PuH9RY6wrA5P7U'),(56,'1wNXGL15v2sMg9H3G18PuH9RY6wrA5P7U'),(57,'https://phukiendamai.com/upload_file/avatar/7jYuRIObGPzxUGttgcprNek0YLLuqldeu4cfV7lz.webp'),(57,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ-GjmQpguNenb5J0qRgjLV0jVcM7gtPSZog&s'),(57,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShqGYltwGblYQtblzQ_JqGGxh_AyAa0QrbVw&s'),(58,'https://i.imgur.com/mno123.jpg'),(58,'https://i.imgur.com/pqr456.jpg'),(58,'https://i.imgur.com/stu789.jpg'),(59,'https://i.imgur.com/vwx123.jpg'),(59,'https://i.imgur.com/yzab456.jpg'),(59,'https://i.imgur.com/cde789.jpg'),(153,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep'),(153,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep'),(153,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep'),(52,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv'),(52,'1hXCGCt2kOVsuGQDpRb7kRyXtn6AVPaFX'),(52,'1TsZ29USfrmVbVs0TC2RmZLO2Hmu0vzGV');
/*!40000 ALTER TABLE `product_image_url` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 11:25:32
