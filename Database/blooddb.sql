-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 29, 2014 at 06:28 AM
-- Server version: 5.6.12
-- PHP Version: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `blooddb`
--
CREATE DATABASE IF NOT EXISTS `blooddb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `blooddb`;

-- --------------------------------------------------------

--
-- Table structure for table `bloodbank`
--

CREATE TABLE IF NOT EXISTS `bloodbank` (
  `ID` int(10) NOT NULL DEFAULT '0',
  `tabname` varchar(25) NOT NULL,
  `tabaddr` varchar(120) NOT NULL,
  `tabdistr` varchar(20) NOT NULL,
  `Pincode` varchar(7) NOT NULL,
  `tabph` varchar(10) NOT NULL,
  `tabage` int(10) NOT NULL,
  `tabbloodgrp` varchar(20) NOT NULL,
  `tabgender` varchar(10) NOT NULL,
  `tablast` varchar(15) NOT NULL,
  `tabregtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tagblockrate` int(2) NOT NULL,
  `tabimei` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bloodbank`
--

INSERT INTO `bloodbank` (`ID`, `tabname`, `tabaddr`, `tabdistr`, `Pincode`, `tabph`, `tabage`, `tabbloodgrp`, `tabgender`, `tablast`, `tabregtime`, `tagblockrate`, `tabimei`) VALUES
(0, 'Apollo blood Bank', 'srinagar mahesh veedu..mass house', 'salem', '625531', '2787877777', 21, 'A1+ve', 'Male', '22/2/2006', '2014-03-28 18:06:32', 1, '0000000000000'),
(0, 'Apollo blood Bank', 'srinagar mahesh veedu..mass house', 'salem', '625531', '2787877777', 21, 'A1+ve', 'Male', '22/2/2006', '2014-03-28 18:06:55', 1, '0000000000000');

-- --------------------------------------------------------

--
-- Table structure for table `regtable`
--

CREATE TABLE IF NOT EXISTS `regtable` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `tabname` varchar(25) NOT NULL,
  `tabaddr` varchar(120) NOT NULL,
  `tabdistr` varchar(20) NOT NULL,
  `Pincode` varchar(7) NOT NULL,
  `tabph` varchar(10) NOT NULL,
  `tabage` int(2) NOT NULL,
  `tabbloodgrp` varchar(20) NOT NULL,
  `tabgender` varchar(10) NOT NULL,
  `tablast` date NOT NULL,
  `tabregtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tagblockrate` int(2) NOT NULL,
  `tabimei` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `regtable`
--

INSERT INTO `regtable` (`ID`, `tabname`, `tabaddr`, `tabdistr`, `Pincode`, `tabph`, `tabage`, `tabbloodgrp`, `tabgender`, `tablast`, `tabregtime`, `tagblockrate`, `tabimei`) VALUES
(1, 'mohan', 'N0.8, new south strret ,BKpatti', 'theni', '625531', '7200801025', 19, 'A1+ve', 'Male', '2009-03-22', '2014-03-28 22:58:00', 0, '000000000000000'),
(2, 'Arun', 'Aishwarya complex,Kuamran street', 'Coimbatore', '634565', '9578178060', 21, 'A1+ve', 'Male', '2013-03-22', '2014-03-28 22:58:59', 1, '000000000000000'),
(3, 'Rukmani', 'No,8,Rangarajan theru', 'theni', '625531', '9976740491', 44, 'A1+ve', 'Female', '1994-04-30', '2014-03-28 23:00:05', 0, '000000000000000'),
(4, 'Sekar', 'No,8 Kumaran street', 'Theni', '625531', '9443373878', 55, 'AB-ve', 'Male', '2014-03-22', '2014-03-28 23:01:08', 0, '000000000000000'),
(5, 'Aathi', 'No,7,New north street', 'Madurai', '625541', '9332211212', 19, 'A1+ve', 'Male', '2012-02-22', '2014-03-28 23:02:15', 0, '000000000000000'),
(6, 'Kumar', 'Srirangam,No,8,rankam street.', 'Madurai', '625543', '9876543210', 21, 'A1+ve', 'Male', '2013-02-22', '2014-03-28 23:23:09', 0, '000000000000000');

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `blocker` ON SCHEDULE EVERY 1 SECOND STARTS '2014-03-27 15:25:51' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM regtable where tagblockrate>2$$

CREATE DEFINER=`root`@`localhost` EVENT `e_daily` ON SCHEDULE EVERY 1 SECOND STARTS '2014-03-27 15:20:20' ON COMPLETION NOT PRESERVE ENABLE COMMENT 'Saves total number of sessions then clears the table each day' DO BEGIN
       DELETE FROM regtable where tagblockrate>=3;
      END$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
