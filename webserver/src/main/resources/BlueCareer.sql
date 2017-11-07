-------CREATE TABLE for user
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `user`;
CREATE TABLE user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    realname VARCHAR(64),
    password VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    qq VARCHAR(64),
    access_key VARCHAR(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;