-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Steven Tsar'),(2,'Agada Kris'),(3,'John Talking'),(4,'Ray Redebery'),(5,'Jack Paris'),(6,'Daniel Dego'),(7,'Leo Hudoy'),(8,'Gavriil Markiz'),(9,'Micheal Hok'),(10,'Regin Hok'),(11,'Stiv Froi'),(12,'John Loyd'),(13,'John Mitchin');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baskets`
--

DROP TABLE IF EXISTS `baskets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baskets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `books_id` int NOT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_baskets_books1_idx` (`books_id`),
  KEY `fk_baskets_users1_idx` (`users_id`),
  CONSTRAINT `fk_baskets_books1` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  CONSTRAINT `fk_baskets_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
/*!40000 ALTER TABLE `baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_genres`
--

DROP TABLE IF EXISTS `book_genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `locals_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_genres_locals1_idx` (`locals_id`),
  CONSTRAINT `fk_book_genres_locals1` FOREIGN KEY (`locals_id`) REFERENCES `locals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_genres`
--

LOCK TABLES `book_genres` WRITE;
/*!40000 ALTER TABLE `book_genres` DISABLE KEYS */;
INSERT INTO `book_genres` VALUES (1,'Detective',1),(2,'Fantasy',1),(3,'Adventures',1),(4,'Novel',1),(5,'Non-fiction',1),(6,'Детектив',2),(7,'Фантастика',2),(8,'Приключения',2),(9,'Роман',2),(10,'Научно-популярное',2);
/*!40000 ALTER TABLE `book_genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `price` int NOT NULL,
  `count` int NOT NULL,
  `languages_id` int NOT NULL,
  `genre_id` int NOT NULL,
  `publishers_id` int NOT NULL,
  `locals_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_books_languages_idx` (`languages_id`),
  KEY `fk_books_book_genres1_idx` (`genre_id`),
  KEY `fk_books_publishers1_idx` (`publishers_id`),
  KEY `fk_books_locals1_idx` (`locals_id`),
  CONSTRAINT `fk_books_book_genres1` FOREIGN KEY (`genre_id`) REFERENCES `book_genres` (`id`),
  CONSTRAINT `fk_books_languages` FOREIGN KEY (`languages_id`) REFERENCES `languages` (`id`),
  CONSTRAINT `fk_books_locals1` FOREIGN KEY (`locals_id`) REFERENCES `locals` (`id`),
  CONSTRAINT `fk_books_publishers1` FOREIGN KEY (`publishers_id`) REFERENCES `publishers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Книга заблуждений','В этой книге авторы вам расскажут о \nраспространенных заблуждениях которые не принято обсуждать в\nобществе. Книга завоевала сердца читателей за смелые идеи авторов.',2000,23,2,10,6,2),(2,'Как там Иммунитет','Вы знаете, что иммунная система — отдельный орган? И эта книга рассказывает о нем все, что известно на сегодняшний день врачам и ученым.',1999,17,2,10,6,2),(3,'Триста лет одиночества','Роман писателя Гавриила Маркиза, одно из наиболее характерных и популярных произведений в направлении магического реализма.',2500,27,2,9,7,2),(4,'Анна Карина','Роман Льва Худого о трагической любви замужней дамы Анны Карины и блестящего офицера Алексея',1799,31,2,9,5,2),(5,'Мир и мир','Роман-эпопея Льва Худого, описывающий русское общество в эпоху войн против Наполеона в 1805—1812 годах.',3150,50,2,9,5,2),(6,'Робин Крузо','Книга написана как автобиография морского путешественника и плантатора Робина Крузо, желавшего ещё более разбогатеть скорым и нелегальным\n путём, но в результате кораблекрушения попавшего на необитаемый остров, где провёл 28 лет.',2249,29,2,8,6,2),(7,'Морской лев','Книга Джека Лондона, написанная им в 1904 году.',1599,43,2,8,6,2),(8,'400 градусов по кельвину','Книга описывает американское общество близкого будущего, \nв котором книги находятся под запретом; пожарные сжигают любые найденные книги.',2099,34,2,8,6,2),(9,'Боббиты','Продолжение знаменитой серии Талкинга, в этот раз описывающий молодость дяди Бильбо.',6000,9,2,7,8,2),(10,'Мир колец','Знаменитый на весь мир как прародитель фэнтези, роман повествует о приключениях трех хоббитов и их друзей.',5000,11,2,7,8,2),(11,'Расследование в Северном Экспрессе','Детективный роман английской писательницы Агады Крис, написанный в 1933 году.',3400,44,2,6,7,2),(12,'Мистер Тойота','Детективный роман писателя Стива Царя, опубликованный 3 июня 2014 года',4100,15,2,6,7,2),(13,'Book of misconceptions','In this book, the authors will tell you about\ncommon misconceptions that are not customary to discuss in\nsociety. The book won the hearts of readers for the bold ideas of the authors.',2000,23,1,5,1,1),(14,'How the Immunity is doing','Do you know that the immune system is a separate organ? And this book tells about him everything that is known today to doctors and scientists.',1999,17,1,5,1,1),(15,'Three hundred years of solitude','A novel by the writer Gavriil Markiz, one of the most characteristic and popular works in the direction of magical realism.',2500,27,1,4,2,1),(16,'Anna Karina','Novel by Leo Khudoy about the tragic love of a married lady Anna Karina and a brilliant officer Alexei',1799,31,1,4,4,1),(17,'Peace and peace','Novel epic by Leo Khudoy, describing Russian society in the era of the wars against Napoleon in 1805-1812.',3150,50,1,4,4,1),(18,'Robin Kruzo','The book was written as an autobiography of the sea traveler and planter Robin Kruzo, who wanted to get even richer quickly and illegally.\n by, but as a result of a shipwreck, he ended up on a desert island, where he spent 28 years.',2249,29,1,3,1,1),(19,'Sea Lion','Book by Jack London, written by him in 1904.',1599,43,1,3,1,1),(20,'400 degrees Kelvin','The book describes the American society of the near future,\nin which books are banned; firefighters burn any books they find.',2099,34,1,3,1,1),(21,'Bobbits','Continuation of the famous Talking series, this time describing the youth of Uncle Bilbo.',6000,9,1,2,3,1),(22,'World of the rings','World famous as the father of fantasy, Novel tells the adventures of three hobbits and their friends.',5000,11,1,2,3,1),(23,'Investigation in Northern Express','Detective Novel by the English writer Agada Kris, written in 1933.',3400,44,1,1,2,1),(24,'Mr. Toyota','Detective Novel by writer Steve Tzar, published June 3, 2014',4100,15,1,1,2,1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books_authors`
--

DROP TABLE IF EXISTS `books_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books_authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `authors_id` int NOT NULL,
  `books_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_books_authors_authors1_idx` (`authors_id`),
  KEY `fk_books_authors_books1_idx` (`books_id`),
  CONSTRAINT `fk_books_authors_authors1` FOREIGN KEY (`authors_id`) REFERENCES `authors` (`id`),
  CONSTRAINT `fk_books_authors_books1` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books_authors`
--

LOCK TABLES `books_authors` WRITE;
/*!40000 ALTER TABLE `books_authors` DISABLE KEYS */;
INSERT INTO `books_authors` VALUES (1,1,24),(2,1,12),(3,2,23),(4,2,11),(5,3,21),(6,3,22),(7,3,9),(8,3,10),(9,4,20),(10,4,8),(11,5,19),(12,5,7),(13,6,18),(14,6,6),(15,7,16),(16,7,17),(17,7,4),(18,7,5),(19,8,15),(20,8,3),(21,9,14),(22,10,14),(23,9,2),(24,10,2),(25,11,13),(26,12,13),(27,13,13),(28,11,1),(29,12,1),(30,13,1);
/*!40000 ALTER TABLE `books_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `locals_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_countries_locals1_idx` (`locals_id`),
  CONSTRAINT `fk_countries_locals1` FOREIGN KEY (`locals_id`) REFERENCES `locals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'USA',1),(2,'UK',1),(3,'Germany',1),(4,'Russia',1),(5,'США',2),(6,'Великобритания',2),(7,'Германия',2),(8,'Россия',2);
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (1,'English'),(2,'Русский');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locals`
--

DROP TABLE IF EXISTS `locals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `short_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locals`
--

LOCK TABLES `locals` WRITE;
/*!40000 ALTER TABLE `locals` DISABLE KEYS */;
INSERT INTO `locals` VALUES (1,'en_US','English'),(2,'ru_RU','Русский');
/*!40000 ALTER TABLE `locals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `cost` int NOT NULL,
  `orders_id` int NOT NULL,
  `books_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_items_orders1_idx` (`orders_id`),
  KEY `fk_order_items_books1_idx` (`books_id`),
  CONSTRAINT `fk_order_items_books1` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  CONSTRAINT `fk_order_items_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `finish_time` datetime NOT NULL,
  `total_cost` int NOT NULL,
  `status_id` int NOT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_status1_idx` (`status_id`),
  KEY `fk_orders_users1_idx` (`users_id`),
  CONSTRAINT `fk_orders_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `countries_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_publishers_countries1_idx` (`countries_id`),
  CONSTRAINT `fk_publishers_countries1` FOREIGN KEY (`countries_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'Mary-Webter Press',1),(2,'Focusford Press',2),(3,'Haifman und Camp',3),(4,'MoscowPress',4),(5,'МоскваИзд',8),(6,'Мэривебтер',5),(7,'Изд Фокусфорд',6),(8,'Хайфман и Камп',7);
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `locals_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_status_locals1_idx` (`locals_id`),
  CONSTRAINT `fk_status_locals1` FOREIGN KEY (`locals_id`) REFERENCES `locals` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'OK',1),(2,'Processing',1),(3,'Error',1),(4,'OK',2),(5,'Обрабатывается',2),(6,'Ошибка',2);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `birthday` date NOT NULL,
  `phonenumber` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `is_banned` tinyint NOT NULL,
  `is_admin` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@mysite.keklol','e66055e8e308770492a44bf16e875127','Admin','Architect','2003-01-01','+77771234568','Almaty, Kazakhstan',0,1),(2,'average_user@mysite.keklol','d51bf662015b99e3998ef9fb2546099f','John','Doe','1990-05-20','+77778654321','Almaty, Kazakhstan',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-20 20:32:55
