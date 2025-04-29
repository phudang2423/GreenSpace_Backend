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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint NOT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `stock_quantity` int DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `original_price` double DEFAULT NULL,
  `folder_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (52,'cay-trau-ba','Cây leo, cây cảnh nội thất','2024-12-11 14:38:55.891120','Cây trầu bà (Epipremnum aureum)là một loại cây cảnh phổ biến được trồng trong nhà hoặc ngoài trời. Cây trầu bà có khả năng sống tốt trong môi trường thiếu ánh sáng và có thể chịu được điều kiện môi trường khắc nghiệt, là một trong những cây dễ chăm sóc nhất. Đây là loài cây có sức sống mạnh mẽ và phát triển nhanh, với lá có hình trái tim, màu xanh sáng và đôi khi có những mảng vàng đặc trưng, tạo nên vẻ đẹp rất thu hút.','Cây Trầu Bà',200000,100,'cây cảnh, cây leo, cây nội thất','2025-04-11 21:45:33.165586',300000,NULL),(53,'cay-kim-tien','Cây cảnh, cây nội thất','2024-12-11 14:44:24.511188','Cây Kim Tiền (Zamioculcas zamiifolia) là loài cây cảnh phổ biến trong các không gian văn phòng và gia đình. Cây có lá mọc đối, dày và bóng, tạo cảm giác tươi mới. Đây là loài cây dễ chăm sóc và sống lâu, phù hợp với nhiều môi trường ánh sáng yếu hoặc bóng râm. Cây Kim Tiền được cho là mang lại may mắn và tài lộc cho gia chủ.','Cây Kim Tiền',150000,200,'cây cảnh, cây nội thất, cây may mắn','2024-12-11 15:38:40.423933',200000,NULL),(54,'cay-lan-ho-diep','Cây cảnh, hoa lan','2024-12-11 14:44:29.900390','Cây Lan Hồ Điệp (Phalaenopsis) là một trong những loại lan phổ biến nhất, được biết đến với vẻ đẹp rực rỡ và màu sắc phong phú. Cây lan này dễ trồng và chăm sóc, thích hợp để trưng bày trong các không gian sống hoặc văn phòng. Lan Hồ Điệp thường ra hoa vào mùa đông và có thể kéo dài từ vài tuần đến vài tháng.','Cây Lan Hồ Điệp',320000,50,'hoa lan, cây cảnh, cây nội thất','2024-12-11 15:51:37.397608',350000,NULL),(55,'cay-cau-tieu-tram','Cây cảnh, cây nội thất','2024-12-11 14:44:34.487278','Cây Cau Tiểu Trâm (Chamaedorea elegans) là một loại cây cảnh nội thất rất được ưa chuộng. Với lá dài, màu xanh mướt và hình dáng thẳng đứng, cây Cau Tiểu Trâm mang lại vẻ đẹp tươi mới cho không gian. Đây là loại cây dễ chăm sóc, phát triển tốt trong môi trường ánh sáng yếu và không cần tưới nước quá nhiều.','Cây Cau Tiểu Trâm',120000,120,'cây cảnh, cây nội thất, cây văn phòng','2024-12-11 16:03:02.156966',150000,NULL),(56,'xeng-mini','dụng cụ, dụng cụ đào đất','2024-12-11 14:45:39.609326','Xẻng mini là dụng cụ làm vườn nhỏ gọn và tiện lợi, phù hợp cho các công việc chăm sóc cây cảnh và làm vườn trong không gian nhỏ. Với thiết kế thông minh và chất liệu bền bỉ, xẻng mini là người bạn đồng hành lý tưởng cho những người yêu thích làm vườn.','Xẻng làm vườn mini',50000,50,'Phụ kiện, làm vườn, cuốc xẻng, nhỏ, Dụng cụ làm vườn','2025-01-14 20:08:58.661626',55000,NULL),(57,'gang-tay-lam-vuon','Phụ kiện làm vườn, bảo vệ tay','2024-12-11 14:45:55.604891','Găng tay làm vườn giúp bảo vệ tay khỏi bụi bẩn, gai cây và các chất gây kích ứng khi làm vườn. Với chất liệu bền, thoáng khí, găng tay này mang lại cảm giác thoải mái khi sử dụng.','Găng tay làm vườn',80000,150,'Bảo vệ tay, Găng tay, Phụ kiện','2025-01-14 20:16:48.091141',80000,NULL),(58,'chau-cay-xi-mang','Phụ kiện, Chậu cây','2024-12-11 14:46:02.086136','Chậu cây xi măng được thiết kế chắc chắn và hiện đại, phù hợp cho các loại cây cảnh trong nhà và ngoài trời. Chậu có khả năng giữ độ ẩm cho cây, giúp cây phát triển tốt.','Chậu cây xi măng',220000,100,'Phụ kiện, Chậu cây','2025-01-14 20:24:23.753216',220000,NULL),(59,'binh-phun-suong','Phụ kiện, Bình phun, Dụng cụ làm vườn','2024-12-11 14:46:08.198263','Bình phun sương giúp làm ẩm không khí và cung cấp độ ẩm cho cây trồng. Sản phẩm này đặc biệt hữu ích trong việc chăm sóc cây cảnh trong môi trường khô hoặc khi trồng cây trong nhà.','Bình phun sương',43000,200,'Bình phun, Chăm sóc cây, Dụng cụ làm vườn','2025-01-14 20:27:08.788211',43000,NULL),(102,'cay-lam-y',NULL,'2024-12-19 16:18:40.796510','Lá: Màu xanh đậm, hình bầu dục, mặt lá bóng và gân nổi rõ. Hoa: Hoa trắng hình trái tim, thường mọc trên cuống dài, bền màu và lâu tàn. Chiều cao: Từ 30-60 cm, thích hợp đặt trong nhà hoặc văn phòng.','Cây Lan Ý',130000,20,'cây cảnh, cây nội thất, cây văn phòng, cây phong thủy','2024-12-20 14:24:36.037608',150000,NULL),(152,'cay-cau-canh','cây cảnh','2024-12-20 15:10:24.931860','','Cây Cau Cảnh',200000,30,'cây cảnh, cây phong thủy, cây nội thất','2024-12-20 15:12:00.212164',220000,NULL),(153,'cay-mai-van-phuc','cây cảnh','2024-12-20 15:16:55.777711','a','Cây Mai Vạn Phúc',60000,29,'cây cảnh, cây ngoài trời','2025-03-04 18:37:44.788928',75000,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
