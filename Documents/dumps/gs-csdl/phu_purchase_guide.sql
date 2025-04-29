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
-- Table structure for table `purchase_guide`
--

DROP TABLE IF EXISTS `purchase_guide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_guide` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_guide`
--

LOCK TABLES `purchase_guide` WRITE;
/*!40000 ALTER TABLE `purchase_guide` DISABLE KEYS */;
INSERT INTO `purchase_guide` VALUES (1,'Thanh toán','Chúng tôi hỗ trợ các phương thức thanh toán như chuyển khoản ngân hàng, ví điện tử (Momo, ZaloPay) và thanh toán khi nhận hàng. Nếu có vấn đề, vui lòng liên hệ CSKH.','thanh toán, momo, zalopay, chuyển khoản, COD','2025-02-13 06:33:53','2025-02-13 06:33:53'),(2,'Thanh toán','Bạn có thể thanh toán qua thẻ tín dụng Visa, MasterCard hoặc qua cổng thanh toán VNPay. Toàn bộ giao dịch đều được mã hóa bảo mật.','visa, mastercard, vnpay, thẻ tín dụng','2025-02-13 06:33:53','2025-02-13 06:33:53'),(3,'Vận chuyển','Chúng tôi giao hàng toàn quốc thông qua các đối tác vận chuyển như Giao Hàng Nhanh, Viettel Post. Thời gian giao hàng từ 2-5 ngày làm việc.','giao hàng, vận chuyển, thời gian giao','2025-02-13 06:33:53','2025-02-13 06:33:53'),(4,'Vận chuyển','Cây cảnh sẽ được đóng gói cẩn thận với hộp bảo vệ chuyên dụng. Chúng tôi đảm bảo cây vẫn còn tươi khi đến tay khách hàng.','đóng gói, bảo vệ cây, vận chuyển cây cảnh','2025-02-13 06:33:53','2025-02-13 06:33:53'),(5,'Đổi trả','Bạn có thể đổi trả sản phẩm trong vòng 7 ngày nếu sản phẩm bị hư hại khi giao hàng. Vui lòng cung cấp hình ảnh sản phẩm để xác minh.','đổi trả, hoàn tiền, bảo hành, cây bị hư','2025-02-13 06:33:53','2025-02-13 06:33:53'),(6,'Đổi trả','Các loại cây cảnh chỉ được đổi trả nếu có lỗi từ nhà cung cấp. Các phụ kiện làm vườn có thể đổi trả nếu còn nguyên vẹn.','đổi cây, lỗi nhà cung cấp, trả hàng','2025-02-13 06:33:53','2025-02-13 06:33:53'),(7,'Hướng dẫn mua hàng','Bạn có thể chọn sản phẩm, thêm vào giỏ hàng và thanh toán trực tuyến. Nếu cần hỗ trợ, hãy liên hệ chúng tôi qua hotline.','cách mua hàng, hướng dẫn đặt hàng, giỏ hàng','2025-02-13 06:33:53','2025-02-13 06:33:53'),(8,'Hướng dẫn mua hàng','Để mua hàng trên website, bạn cần đăng ký tài khoản. Việc này giúp theo dõi đơn hàng và nhận ưu đãi thành viên.','đăng ký tài khoản, mua hàng, theo dõi đơn hàng','2025-02-13 06:33:53','2025-02-13 06:33:53'),(9,'Bảo quản cây cảnh','Sau khi nhận hàng, bạn nên đặt cây ở nơi có ánh sáng nhẹ trong 1-2 ngày để cây thích nghi với môi trường mới.','chăm sóc cây, bảo quản cây, môi trường cây','2025-02-13 06:33:53','2025-02-13 06:33:53'),(10,'Bảo quản cây cảnh','Hạn chế tưới nước quá nhiều ngay sau khi nhận cây. Chỉ tưới một lượng nhỏ để giữ độ ẩm.','tưới nước, cây mới nhận, chăm sóc cây','2025-02-13 06:33:53','2025-02-13 06:33:53'),(11,'Chăm sóc cây','Cây xương rồng và sen đá cần ánh sáng mặt trời trực tiếp ít nhất 4 giờ/ngày. Tránh tưới quá nhiều nước.','xương rồng, sen đá, ánh sáng, nước','2025-02-13 06:33:53','2025-02-13 06:33:53'),(12,'Chăm sóc cây','Cây nội thất như lưỡi hổ và trầu bà thích hợp với ánh sáng gián tiếp, không cần tưới quá thường xuyên.','cây nội thất, lưỡi hổ, trầu bà, ánh sáng','2025-02-13 06:33:53','2025-02-13 06:33:53'),(13,'Phụ kiện làm vườn','Chúng tôi cung cấp các loại chậu cây, phân bón hữu cơ, đất trồng và dụng cụ làm vườn chuyên dụng.','phụ kiện, chậu cây, phân bón, dụng cụ làm vườn','2025-02-13 06:33:53','2025-02-13 06:33:53'),(14,'Phụ kiện làm vườn','Chậu đất nung giúp cây phát triển tốt nhờ khả năng thoát nước tốt hơn so với chậu nhựa.','chậu đất nung, chậu nhựa, thoát nước','2025-02-13 06:33:53','2025-02-13 06:33:53'),(15,'Khuyến mãi','Chúng tôi thường xuyên có chương trình giảm giá vào các dịp lễ. Đăng ký thành viên để nhận thông tin khuyến mãi.','khuyến mãi, giảm giá, thành viên, ưu đãi','2025-02-13 06:33:53','2025-02-13 06:33:53'),(16,'Khuyến mãi','Nếu bạn mua đơn hàng trên 500.000 VNĐ, bạn sẽ được miễn phí vận chuyển toàn quốc.','miễn phí ship, đơn hàng lớn, freeship','2025-02-13 06:33:53','2025-02-13 06:33:53');
/*!40000 ALTER TABLE `purchase_guide` ENABLE KEYS */;
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
