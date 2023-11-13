CREATE DATABASE  IF NOT EXISTS `db_dev_web` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_dev_web`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: db_dev_web
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `transacoes`
--

DROP TABLE IF EXISTS `transacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacoes` (
  `id_transacao` int NOT NULL AUTO_INCREMENT,
  `id_conta_origem` int DEFAULT NULL,
  `id_conta_destino` int DEFAULT NULL,
  `tipo_transacao` int NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `data_hora` datetime NOT NULL,
  PRIMARY KEY (`id_transacao`),
  KEY `id_conta_origem` (`id_conta_origem`),
  KEY `id_conta_destino` (`id_conta_destino`),
  CONSTRAINT `transacoes_ibfk_1` FOREIGN KEY (`id_conta_origem`) REFERENCES `conta` (`id_conta`),
  CONSTRAINT `transacoes_ibfk_2` FOREIGN KEY (`id_conta_destino`) REFERENCES `conta` (`id_conta`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacoes`
--

LOCK TABLES `transacoes` WRITE;
/*!40000 ALTER TABLE `transacoes` DISABLE KEYS */;
INSERT INTO `transacoes` VALUES (1,1,NULL,1,100.00,'2023-11-12 12:54:19'),(2,1,NULL,1,10.00,'2023-11-12 12:58:44'),(3,1,NULL,1,2.50,'2023-11-12 12:59:04'),(4,1,NULL,2,500.00,'2023-11-12 13:02:19'),(6,2,3,3,50.00,'2023-11-12 14:55:52'),(7,3,NULL,4,250.00,'2023-11-12 15:00:36');
/*!40000 ALTER TABLE `transacoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `saque` AFTER INSERT ON `transacoes` FOR EACH ROW BEGIN
    DECLARE saldo_atual DECIMAL(10, 2);

    IF NEW.tipo_transacao = 1 THEN
        -- Obter o saldo atual da conta_origem
        SELECT saldo INTO saldo_atual
        FROM Conta
        WHERE id_conta = NEW.id_conta_origem;

        -- Verificar se o saldo é suficiente
        IF saldo_atual >= NEW.valor THEN
            -- Atualizar o saldo da conta_origem após o saque
            UPDATE Conta
            SET saldo = saldo_atual - NEW.valor
            WHERE id_conta = NEW.id_conta_origem;
        ELSE
            -- Lidar com o caso em que o saldo é insuficiente
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Saldo insuficiente para o saque.';
        END IF;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `deposito` AFTER INSERT ON `transacoes` FOR EACH ROW BEGIN
    DECLARE saldo_atual DECIMAL(10, 2);

    IF NEW.tipo_transacao = 2 THEN
        -- Obter o saldo atual da conta_origem
        SELECT saldo INTO saldo_atual
        FROM Conta
        WHERE id_conta = NEW.id_conta_origem;

            -- Atualizar o saldo da conta_origem após o saque
            UPDATE Conta
            SET saldo = saldo_atual + NEW.valor
            WHERE id_conta = NEW.id_conta_origem;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `transferencia` AFTER INSERT ON `transacoes` FOR EACH ROW BEGIN
    DECLARE saldo_atual_origem DECIMAL(10, 2);
    DECLARE saldo_atual_destino DECIMAL(10, 2);
    
    IF NEW.tipo_transacao = 3 THEN
        -- Obter o saldo atual da conta_origem
        SELECT saldo INTO saldo_atual_origem
        FROM Conta
        WHERE id_conta = NEW.id_conta_origem;

        -- Obter o saldo atual da conta_destino
        SELECT saldo INTO saldo_atual_destino
        FROM Conta
        WHERE id_conta = NEW.id_conta_destino;

        -- Atualizar o saldo da conta_origem após o saque
        UPDATE Conta
        SET saldo = saldo_atual_origem - NEW.valor
        WHERE id_conta = NEW.id_conta_origem;

        -- Atualizar o saldo da conta_destino após o depósito
        UPDATE Conta
        SET saldo = saldo_atual_destino + NEW.valor
        WHERE id_conta = NEW.id_conta_destino;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `investimento` AFTER INSERT ON `transacoes` FOR EACH ROW BEGIN
    DECLARE saldo_atual_origem DECIMAL(10, 2);
    
    IF NEW.tipo_transacao = 4 THEN
        -- Obter o saldo atual da conta_origem
        SELECT saldo INTO saldo_atual_origem
        FROM Conta
        WHERE id_conta = NEW.id_conta_origem;

        -- Atualizar o saldo da conta_origem após o saque
        UPDATE Conta
        SET saldo = saldo_atual_origem - NEW.valor
        WHERE id_conta = NEW.id_conta_origem;

        -- Atualizar o saldo da conta_destino após o depósito
        UPDATE Conta
        SET valInvestido = valInvestido + NEW.valor
        WHERE id_conta = NEW.id_conta_origem;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13 18:27:26
