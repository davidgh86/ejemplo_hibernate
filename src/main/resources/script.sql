-- Adminer 4.8.1 MySQL 8.0.28 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
  `id_dpto` int NOT NULL AUTO_INCREMENT,
  `nom_dpto` char(32) DEFAULT NULL,
  `id_sede` int DEFAULT NULL,
  PRIMARY KEY (`id_dpto`),
  KEY `FK1rd4p6fop72hn5hryxddl56i5` (`id_sede`),
  CONSTRAINT `FK1rd4p6fop72hn5hryxddl56i5` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `departamento` (`id_dpto`, `nom_dpto`, `id_sede`) VALUES
(2,	'departamento2sede1',	1),
(3,	'departamento3sede1',	1),
(4,	'departamento1sede2',	2),
(5,	'departamento2sede2',	2),
(6,	'departamento3sede2',	2);

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `dni` char(9) NOT NULL,
  `nom_empleado` char(40) DEFAULT NULL,
  `id_dpto` int DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `FKppk4vfoe40yj5hofrmuetjtje` (`id_dpto`),
  CONSTRAINT `FKppk4vfoe40yj5hofrmuetjtje` FOREIGN KEY (`id_dpto`) REFERENCES `departamento` (`id_dpto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `empleado` (`dni`, `nom_empleado`, `id_dpto`) VALUES
('10000000F',	'empleado5sede1',	2),
('10000000G',	'empleado6sede1',	2),
('10000000H',	'empleado7sede1',	2),
('10000000I',	'empleado8sede1',	2),
('10000000J',	'empleado9sede1',	3),
('10000000K',	'empleado10sede1',	3),
('10000000L',	'empleado11sede1',	3),
('10000000M',	'empleado12sede1',	3),
('20000000B',	'empleado1sede2',	4),
('20000000C',	'empleado2sede2',	4),
('20000000D',	'empleado3sede2',	4),
('20000000E',	'empleado4sede2',	4),
('20000000F',	'empleado5sede2',	5),
('20000000G',	'empleado6sede2',	5),
('20000000H',	'empleado7sede2',	5),
('20000000I',	'empleado8sede2',	5),
('20000000J',	'empleado9sede2',	6),
('20000000K',	'empleado10sede2',	6),
('20000000L',	'empleado11sede2',	6),
('20000000M',	'empleado12sede2',	6);

DROP TABLE IF EXISTS `empleados_datos_prof`;
CREATE TABLE `empleados_datos_prof` (
  `dni` char(9) NOT NULL,
  `categoria` char(2) DEFAULT NULL,
  `sueldo_bruto_anual` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `empleados_datos_prof` (`dni`, `categoria`, `sueldo_bruto_anual`) VALUES
('10000000B',	'A',	10000.00),
('10000000C',	'B',	75000.00),
('10000000D',	'C',	20000.00),
('10000000E',	'D',	25000.00),
('10000000F',	'E',	30000.00),
('10000000G',	'F',	35000.00),
('10000000H',	'G',	40000.00),
('10000000I',	'H',	45000.00),
('10000000J',	'I',	50000.00),
('10000000K',	'J',	55000.00),
('10000000L',	'K',	60000.00),
('10000000M',	'L',	65000.00),
('20000000B',	'A',	10000.00),
('20000000C',	'B',	15000.00),
('20000000D',	'C',	20000.00),
('20000000E',	'D',	25000.00),
('20000000F',	'E',	30000.00),
('20000000G',	'F',	35000.00),
('20000000H',	'G',	40000.00),
('20000000I',	'H',	45000.00),
('20000000J',	'I',	50000.00),
('20000000K',	'J',	55000.00),
('20000000L',	'K',	60000.00),
('20000000M',	'L',	65000.00);

DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto` (
  `id_proy` int NOT NULL AUTO_INCREMENT,
  `f_fin` date DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `nom_proy` char(20) DEFAULT NULL,
  PRIMARY KEY (`id_proy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `proyecto` (`id_proy`, `f_fin`, `fechaInicio`, `nom_proy`) VALUES
(1,	'2020-02-11',	'2020-01-10',	'proyectoNuevo'),
(2,	'2020-03-11',	'2020-01-11',	'proyecto2sede1'),
(3,	'2020-04-11',	'2020-01-12',	'proyecto3sede1'),
(4,	'2020-04-11',	'2020-01-12',	'proyecto3sede2'),
(5,	'2020-03-11',	'2020-01-11',	'proyecto2sede2'),
(6,	'2020-02-11',	'2020-01-10',	'proyecto1sede2');

DROP TABLE IF EXISTS `proyecto_sede`;
CREATE TABLE `proyecto_sede` (
  `id_sede` int NOT NULL,
  `id_proy` int NOT NULL,
  PRIMARY KEY (`id_sede`,`id_proy`),
  KEY `FK286pp47331t0d75l62mks33tt` (`id_proy`),
  CONSTRAINT `FK286pp47331t0d75l62mks33tt` FOREIGN KEY (`id_proy`) REFERENCES `proyecto` (`id_proy`),
  CONSTRAINT `FKtctxxjbvmpdhlq8pcgwgutdiv` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `proyecto_sede` (`id_sede`, `id_proy`) VALUES
(1,	1),
(1,	2),
(1,	3),
(2,	4),
(2,	5),
(2,	6);

DROP TABLE IF EXISTS `sede`;
CREATE TABLE `sede` (
  `id_sede` int NOT NULL AUTO_INCREMENT,
  `nom_sede` char(20) NOT NULL,
  PRIMARY KEY (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `sede` (`id_sede`, `nom_sede`) VALUES
(1,	'sede1'),
(2,	'sede2');

-- 2022-02-13 11:01:58