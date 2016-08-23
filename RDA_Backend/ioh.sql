-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2016 at 10:36 PM
-- Server version: 10.1.8-MariaDB
-- PHP Version: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ioh`
--
CREATE DATABASE IF NOT EXISTS `ioh` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ioh`;

-- --------------------------------------------------------

--
-- Table structure for table `rda_table_1`
--

CREATE TABLE IF NOT EXISTS `rda_table_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nutrients` varchar(150) NOT NULL,
  `rda_lower` decimal(10,0) NOT NULL,
  `rda_upper` decimal(10,0) NOT NULL,
  `id_calories_a_gm` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rda_table_1`
--

INSERT INTO `rda_table_1` (`id`, `nutrients`, `rda_lower`, `rda_upper`, `id_calories_a_gm`) VALUES
(1, 'Carbs', '40', '60', 2),
(2, 'Protiens', '10', '35', 1),
(3, 'Total Fat', '20', '35', 3),
(20, 'Saturated Fat', '0', '8', 3),
(21, 'PUFA', '0', '10', 3),
(22, 'MUFA', '0', '12', 3),
(23, 'Trans Fat', '0', '1', 3),
(24, 'Sugar', '0', '5', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rda_table_2`
--

CREATE TABLE IF NOT EXISTS `rda_table_2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nutrients` varchar(150) NOT NULL,
  `quantity_in_gms` decimal(10,0) NOT NULL,
  `calories_in_kcal` decimal(10,0) NOT NULL,
  `id_nutrients` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_ nutrients` (`id_nutrients`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rda_table_2`
--

INSERT INTO `rda_table_2` (`id`, `nutrients`, `quantity_in_gms`, `calories_in_kcal`, `id_nutrients`) VALUES
(3, 'Carbs', '1', '4', 1),
(4, 'Protiens', '1', '4', 2),
(5, 'Fat', '1', '9', 3),
(6, 'Alcohol', '1', '7', 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
