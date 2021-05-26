DROP DATABASE IF EXISTS mspr;
CREATE DATABASE mspr CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE mspr;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `firstName` varchar(255),
    `lastName` varchar(255),
    `loginEmail` varchar(255),
    `passwd` varchar(255),
    `role` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `organization` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `companyName` varchar(255),
    `streetAdress1` varchar(255),
    `streetAdress2` varchar(255),
    `streetAdress3` varchar(255),
    `cityName` varchar(255),
    `postalCode` varchar(255),
    `country` varchar(255),
    `phoneNumber` varchar(255),
    `email` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `contact` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `firstName` varchar(255),
    `lastName` varchar(255),
    `phoneNumber` varchar(255),
    `email` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `purchase` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `reference` varchar(255),
    `dateOfOrder` datetime,
    `comment` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `reference` varchar(255),
    `description` varchar(255),
    `unitPriceBeforeTax` float,
    `taxRate` float,
    `quantityAvailable` int,
    `isSellable` boolean
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product_in_purchase` (
    `quantity` int(20) NOT NULL,
    `price` float NOT NULL,
    PRIMARY KEY (`quantity`, `price`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `category` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

