CREATE DATABASE  IF NOT EXISTS `gym_app` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gym_app`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gym_app
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `catalogo`
--

DROP TABLE IF EXISTS `catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo` (
  `id_catalogo` bigint NOT NULL AUTO_INCREMENT,
  `bl` bit(1) DEFAULT NULL,
  `descripcion` varchar(100) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_modif` datetime(6) DEFAULT NULL,
  `imagen_catalogo` float DEFAULT NULL,
  `nombre` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`id_catalogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo`
--

LOCK TABLES `catalogo` WRITE;
/*!40000 ALTER TABLE `catalogo` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_tipo_catalogo`
--

DROP TABLE IF EXISTS `catalogo_tipo_catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogo_tipo_catalogo` (
  `catalogos_id_catalogo` bigint NOT NULL,
  `tipo_catalogo_id_tipo_catalogo` int NOT NULL,
  PRIMARY KEY (`catalogos_id_catalogo`,`tipo_catalogo_id_tipo_catalogo`),
  KEY `FK2wp0lenv5ljl9r8cgpavhbt3p` (`tipo_catalogo_id_tipo_catalogo`),
  CONSTRAINT `FK2wp0lenv5ljl9r8cgpavhbt3p` FOREIGN KEY (`tipo_catalogo_id_tipo_catalogo`) REFERENCES `tipo_catalogo` (`id_tipo_catalogo`),
  CONSTRAINT `FKn3mjm82se6huv116bpv2ci3gw` FOREIGN KEY (`catalogos_id_catalogo`) REFERENCES `catalogo` (`id_catalogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_tipo_catalogo`
--

LOCK TABLES `catalogo_tipo_catalogo` WRITE;
/*!40000 ALTER TABLE `catalogo_tipo_catalogo` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogo_tipo_catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_biometricos`
--

DROP TABLE IF EXISTS `datos_biometricos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_biometricos` (
  `id_datosbio` bigint NOT NULL AUTO_INCREMENT,
  `bl` bit(1) DEFAULT NULL,
  `imc` float DEFAULT NULL,
  `altura` float DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_modif` datetime(6) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  PRIMARY KEY (`id_datosbio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_biometricos`
--

LOCK TABLES `datos_biometricos` WRITE;
/*!40000 ALTER TABLE `datos_biometricos` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos_biometricos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `nombre_role` varchar(255) COLLATE utf8mb3_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_catalogo`
--

DROP TABLE IF EXISTS `tipo_catalogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_catalogo` (
  `id_tipo_catalogo` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(255) COLLATE utf8mb3_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_tipo_catalogo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_catalogo`
--

LOCK TABLES `tipo_catalogo` WRITE;
/*!40000 ALTER TABLE `tipo_catalogo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_catalogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `bl` tinyint(1) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `apellidos` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `aprobado` bit(1) DEFAULT NULL,
  `ciudad` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `direccion` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_modif` datetime(6) DEFAULT NULL,
  `foto_perfil` varchar(255) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `nombre` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `password` varchar(60) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `provincia` varchar(50) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `username` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `id_datosbio` bigint DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FKckdfe5p0b3gdf2hlqk20iafee` (`id_datosbio`),
  CONSTRAINT `FKckdfe5p0b3gdf2hlqk20iafee` FOREIGN KEY (`id_datosbio`) REFERENCES `datos_biometricos` (`id_datosbio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,0,1,'Admin',_binary '\0','','',NULL,NULL,NULL,'','Admin','$2a$10$Mq3pL66rVzIxGeSRJ7QduOKbLxSFNNDQepKtntGrltAXHdFVyXS62','','admin',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_roles` (
  `usuarios_id_usuario` bigint NOT NULL,
  `roles_id_role` int NOT NULL,
  PRIMARY KEY (`usuarios_id_usuario`,`roles_id_role`),
  KEY `FK2apms1tvoihs2o4yjfs7jx3o3` (`roles_id_role`),
  CONSTRAINT `FK2apms1tvoihs2o4yjfs7jx3o3` FOREIGN KEY (`roles_id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `FKjuhhf0f970o2hpqcdx0pvnq72` FOREIGN KEY (`usuarios_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-13 12:12:26
