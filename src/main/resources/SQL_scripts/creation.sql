DROP DATABASE IF EXISTS mspr;
CREATE DATABASE mspr CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE mspr;

SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';
SET time_zone = '+00:00';

CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255),
    `last_name` varchar(255),
    `login_email` varchar(255),
    `passwd` varchar(255),
    `role` varchar(255)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `organization` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `company_name` varchar(255) UNIQUE,
    `street_address1` varchar(255),
    `street_address2` varchar(255),
    `street_address3` varchar(255),
    `city_name` varchar(255),
    `postal_code` varchar(255),
    `country` varchar(255),
    `phone_number` varchar(255),
    `email` varchar(255),
    `user_id` bigint(20),
    CONSTRAINT fk_user FOREIGN KEY (`user_id`)
    REFERENCES `user`(`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `contact` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255),
    `last_name` varchar(255),
    `phone_number` varchar(255),
    `email` varchar(255),
    `organization_id` bigint(20),
    CONSTRAINT fk_organization FOREIGN KEY (`organization_id`)
    REFERENCES `organization`(`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `purchase` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `reference` varchar(255) UNIQUE,
    `date_of_order` datetime,
    `comment` varchar(255),
    `seller_id` bigint(20),
    `contact_id` bigint(20),
    CONSTRAINT fk_seller FOREIGN KEY (`seller_id`)
    REFERENCES `user`(`id`),
    CONSTRAINT fk_contact FOREIGN KEY (`contact_id`)
    REFERENCES `contact`(`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `category` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255) UNIQUE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `reference` varchar(255) UNIQUE,
    `description` varchar(255),
    `unit_price_before_tax` float,
    `tax_rate` float,
    `quantity_available` int,
    `is_sellable` boolean,
    `category_id` bigint(20),
    CONSTRAINT fk_category FOREIGN KEY (`category_id`)
    REFERENCES `category`(`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `product_in_purchase` (
    `purchase_id` bigint(20),
    `product_id` bigint(20),
    `quantity` int(20) NOT NULL,
    `price` float NOT NULL,
    PRIMARY KEY (`quantity`, `price`),
    CONSTRAINT fk_purchase FOREIGN KEY (`purchase_id`)
    REFERENCES `purchase`(`id`),
    CONSTRAINT fk_product FOREIGN KEY (`product_id`)
    REFERENCES `product`(`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


