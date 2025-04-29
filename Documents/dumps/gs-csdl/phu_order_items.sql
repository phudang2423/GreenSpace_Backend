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
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(105,200000,52,NULL,2,42,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(106,200000,52,NULL,2,43,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(107,200000,52,NULL,2,44,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(108,200000,52,NULL,2,45,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(109,200000,52,NULL,2,46,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(110,200000,52,NULL,2,47,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(111,200000,52,NULL,2,48,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(112,200000,52,NULL,2,49,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(113,200000,52,NULL,2,50,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(114,200000,52,NULL,2,51,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(115,60000,153,NULL,1,52,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep','Cây Mai Vạn Phúc'),(116,320000,54,NULL,1,53,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(117,320000,54,NULL,1,54,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(118,130000,102,NULL,1,55,'1BpYz7A-T3IqGpbnb--kUN87Fp7jnSdjm','Cây Lan Ý'),(119,320000,54,NULL,1,56,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(120,120000,55,NULL,1,57,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(121,150000,53,NULL,1,58,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9','Cây Kim Tiền'),(122,150000,53,NULL,1,59,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9','Cây Kim Tiền'),(123,320000,54,NULL,1,59,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(124,120000,55,NULL,1,60,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(125,120000,55,NULL,1,61,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(126,60000,153,NULL,1,62,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep','Cây Mai Vạn Phúc'),(127,120000,55,NULL,1,63,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(128,150000,53,NULL,1,64,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9','Cây Kim Tiền'),(129,200000,52,NULL,1,64,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(130,80000,57,NULL,1,65,'https://phukiendamai.com/upload_file/avatar/7jYuRIObGPzxUGttgcprNek0YLLuqldeu4cfV7lz.webp','Găng tay làm vườn'),(131,50000,56,NULL,1,65,'1wNXGL15v2sMg9H3G18PuH9RY6wrA5P7U','Xẻng làm vườn mini'),(132,200000,52,NULL,1,66,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(133,150000,53,NULL,1,66,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9','Cây Kim Tiền'),(134,320000,NULL,NULL,4,67,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(135,200000,52,NULL,199,68,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà'),(136,120000,55,NULL,2,69,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(137,320000,54,NULL,4,69,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(138,130000,102,NULL,3,69,'1BpYz7A-T3IqGpbnb--kUN87Fp7jnSdjm','Cây Lan Ý'),(139,60000,153,NULL,7,69,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep','Cây Mai Vạn Phúc'),(140,60000,153,NULL,2,70,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep','Cây Mai Vạn Phúc'),(141,60000,153,NULL,1,71,'1zIAW6UYFDAo3QWmPPYG9Fy958rfjwuep','Cây Mai Vạn Phúc'),(142,320000,54,NULL,2,71,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(143,150000,53,NULL,1,71,'1A5PVE_jHe1aZ6GXNUL0_ydtFB384etV9','Cây Kim Tiền'),(144,120000,55,NULL,1,72,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(145,320000,54,NULL,1,73,'1FMb9b7IjWMoQG3gdEJIKr8rucUA9vdyl','Cây Lan Hồ Điệp'),(146,120000,55,NULL,1,74,'1944ybvSbYYgdgdajagVewoPmOpTzmMTa','Cây Cau Tiểu Trâm'),(147,200000,52,NULL,6,75,'1Do_dttUyG_J8rS4Tmy1FjxzN4we2B3Gv','Cây Trầu Bà');
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
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
