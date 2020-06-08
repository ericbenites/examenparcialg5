-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: parcialsw2
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `fotos`
--

DROP TABLE IF EXISTS `fotos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotos` (
  `idfotos` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `producto_idproducto` int(11) NOT NULL,
  PRIMARY KEY (`idfotos`),
  KEY `fk_fotos_producto2_idx` (`producto_idproducto`),
  CONSTRAINT `fk_fotos_producto2` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotos`
--

LOCK TABLES `fotos` WRITE;
/*!40000 ALTER TABLE `fotos` DISABLE KEYS */;
INSERT INTO `fotos` VALUES (1,'/img/productoFotos/','bg-img.jpg',1),(2,'/img/productoFotos/','chompaPrueba.jpg',2),(3,'/img/productoFotos/','A_ti_no_te_sale.jpg',3),(4,'/img/productoFotos/','A_ti_no_te_sale.jpg',4),(5,'/img/productoFotos/','A_ti_no_te_sale.jpg',5),(6,'/img/productoFotos/','makita.png',6),(7,'/img/productoFotos/','makita.png',7),(8,'/img/productoFotos/','makita.png',8);
/*!40000 ALTER TABLE `fotos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idpedidos` int(11) NOT NULL AUTO_INCREMENT,
  `codigopedido` varchar(45) NOT NULL,
  `totalpagado` double NOT NULL,
  `fechadecompra` varchar(45) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idpedidos`),
  KEY `fk_pedidos_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_pedidos_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'PE050620201',5.7,'05/06/2020',3);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `codigoproducto` varchar(4) NOT NULL,
  `nombreproducto` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) DEFAULT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'GAL','galleta',0.7,40,'chocolate y vainilla'),(2,'AZU','azucar',5,12,'solo rubia por kilo'),(3,'MEN','menestra',1,50,'Toda menestra a un precio'),(4,'GAS','gaseosa',2,20,'Inka kola no mÃƒÂ¡s'),(5,'AGU','agua',1,50,'San Mateo malantial'),(6,'CHU','chupetin',0.5,25,'Solo de chapulin'),(7,'ASD','ASDF',2,1,'SDS'),(8,'DSA','ASDFG',3,2,'SDSG');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_has_pedidos`
--

DROP TABLE IF EXISTS `producto_has_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_has_pedidos` (
  `producto_idproducto` int(11) NOT NULL,
  `pedidos_idpedidos` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`producto_idproducto`,`pedidos_idpedidos`),
  KEY `fk_producto_has_pedidos_pedidos1_idx` (`pedidos_idpedidos`),
  KEY `fk_producto_has_pedidos_producto1_idx` (`producto_idproducto`),
  CONSTRAINT `fk_producto_has_pedidos_pedidos1` FOREIGN KEY (`pedidos_idpedidos`) REFERENCES `pedidos` (`idpedidos`),
  CONSTRAINT `fk_producto_has_pedidos_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_has_pedidos`
--

LOCK TABLES `producto_has_pedidos` WRITE;
/*!40000 ALTER TABLE `producto_has_pedidos` DISABLE KEYS */;
INSERT INTO `producto_has_pedidos` VALUES (1,1,2),(2,1,3);
/*!40000 ALTER TABLE `producto_has_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'administrador'),(2,'gestor'),(3,'registrado');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `confirmarcontrasena` varchar(100) NOT NULL,
  `rol_idrol` int(11) NOT NULL,
  `enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `fk_usuario_rol_idx` (`rol_idrol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Gabriel','Castillo','74861939','gabriel@pucp.edu.pe','$2y$12$yTb4EGz3.31XZPJgPWWwt.UTsv/MG8/pSl2LZWEkC1bFSrn0XqTVm','$2y$12$yTb4EGz3.31XZPJgPWWwt.UTsv/MG8/pSl2LZWEkC1bFSrn0XqTVm',1,1),(2,'Sergio','Muro','12345678','sergio@pucp.edu.pe','$2y$12$a0887j7rwlG1hH1OsPvjnOEyYD7SilAUeQqtJhccnoz.3TVzmFYlG','$2y$12$a0887j7rwlG1hH1OsPvjnOEyYD7SilAUeQqtJhccnoz.3TVzmFYlG',2,1),(3,'Eric','Benites','87654321','eric@pucp.edu.pe','$2y$12$xbUl6uoZF0rh5AATiiX1XORNuNOjbwrRab7/FVOZ8LvICkDXLJ3.2','$2y$12$xbUl6uoZF0rh5AATiiX1XORNuNOjbwrRab7/FVOZ8LvICkDXLJ3.2',3,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-08  5:55:33
