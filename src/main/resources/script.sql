SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

CREATE DATABASE proyectos;
USE proyectos;

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE `departamento` (
  `id_depto` int(11) NOT NULL AUTO_INCREMENT,
  `nom_depto` char(32) NOT NULL,
  `id_sede` int(11) NOT NULL,
  PRIMARY KEY (`id_depto`),
  KEY `id_sede` (`id_sede`),
  CONSTRAINT `departamento_ibfk_1` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `dni` char(9) NOT NULL,
  `nom_emp` char(40) NOT NULL,
  `id_dpto` int(11) NOT NULL,
  PRIMARY KEY (`dni`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `empleado_datos_prof` (`dni`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `empleado_datos_prof`;
CREATE TABLE `empleado_datos_prof` (
  `dni` char(9) NOT NULL,
  `categoria` char(2) NOT NULL,
  `sueldo_bruto_anual` decimal(8,2) NOT NULL,
  KEY `dni` (`dni`),
  CONSTRAINT `empleado_datos_prof_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `empleado` (`dni`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto` (
  `id_proy` int(11) NOT NULL AUTO_INCREMENT,
  `f_inicio` date NOT NULL,
  `f_fin` date NOT NULL,
  `nom_proy` char(20) NOT NULL,
  PRIMARY KEY (`id_proy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `proyecto_sede`;
CREATE TABLE `proyecto_sede` (
  `id_proy` int(11) NOT NULL,
  `id_sede` int(11) NOT NULL,
  KEY `id_proy` (`id_proy`),
  KEY `id_sede` (`id_sede`),
  CONSTRAINT `proyecto_sede_ibfk_1` FOREIGN KEY (`id_proy`) REFERENCES `proyecto` (`id_proy`),
  CONSTRAINT `proyecto_sede_ibfk_2` FOREIGN KEY (`id_sede`) REFERENCES `sede` (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `sede`;
CREATE TABLE `sede` (
  `id_sede` int(11) NOT NULL AUTO_INCREMENT,
  `nom_sede` char(20) NOT NULL,
  PRIMARY KEY (`id_sede`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;