CREATE DATABASE IF NOT EXISTS `assignment_2`;
USE `assignment_2`;

DROP TABLE IF EXISTS `page_role`;
DROP TABLE IF EXISTS `page_privilege`;
DROP TABLE IF EXISTS `website_role`;
DROP TABLE IF EXISTS `website_privilege`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `privilege`;
DROP TABLE IF EXISTS `phone`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `widget`;
DROP TABLE IF EXISTS `page`;
DROP TABLE IF EXISTS `website`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `developer`;
DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developer_key` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_agreement` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
);

CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `visits` int(11) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `website_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
);

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `views` int(11) DEFAULT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `page_website_aggregation` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `widget` (
  `dtype` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `css_class` varchar(45) DEFAULT NULL,
  `css_style` varchar(45) DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `shareble` tinyint(4) DEFAULT NULL,
  `expandable` tinyint(4) DEFAULT NULL,
  `src` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT '2',
  `html` varchar(45) DEFAULT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `widget_page_aggregation` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street1` varchar(45) DEFAULT NULL,
  `street2` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(5) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `address_person_aggregation` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `phone_person_aggregation` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `privilege` (
  `id` varchar(6) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `id` varchar(8) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

CREATE TABLE `website_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privilege` varchar(6) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `website_privilege_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `website_privilege_enumeration` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`),
  CONSTRAINT `website_privilege_website_aggregation` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `website_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(8) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `website_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `website_role_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `website_role_enumeration` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `website_role_website_aggregation` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `page_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privilege` varchar(6) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `page_privilege_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `page_privilege_page_aggregation` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_privilege_enumeration` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`)
);

CREATE TABLE `page_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(8) DEFAULT NULL,
  `developer_id` int(11) NOT NULL,
  `page_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `page_role_developer_aggregation` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
  CONSTRAINT `page_role_page_aggregation` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `page_role_enumeration` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
);
