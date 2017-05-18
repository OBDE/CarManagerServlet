-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 08, 2016 at 01:21 PM
-- Server version: 5.5.52-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crud`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE IF NOT EXISTS `cars` (
  `carID` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `carVendor` varchar(255) NOT NULL,
  `carName` varchar(255) NOT NULL,
  `carType` varchar(255) NOT NULL,
  `carYear` int(10) unsigned NOT NULL DEFAULT '0',
  `carHp` int(10) unsigned NOT NULL DEFAULT '0',
  `carCcm` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`carID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`carID`, `userid`, `carVendor`, `carName`, `carType`, `carYear`, `carHp`, `carCcm`) VALUES
(22, 1, 'OPEL', 'ASTRA', 'COUPE', 2004, 125, 1796),
(24, 3, 'OPEL', 'VECTRA', 'SEDAN', 1994, 90, 1796);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `firstName`, `lastName`) VALUES
(1, 'obde', '99A73FD6FE560FA9A89398FCFC9FA51A9E5AA4E8DFE5A43D942A0914A29FDF11', 'Dániel', 'Obernay'),
(2, 'ricsi', '871B32B5F4E1B9AC25237DC7E4E175954C2DC6098AADE48A8ABEFB585CBD53F2', 'Richárd', 'Teudán'),
(3, 'peti', 'BCB1CEBB4BD8D7A53BFB4597C53266B406231CA8C09DA47F0906EABC2EEE5396', 'Péter', 'Czikkely'),
(4, 'nasa', '724F92F8CB38408715702812909881C9089BB6873D204398178DA562BA2720AC', 'Sándor', 'Nagy');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
