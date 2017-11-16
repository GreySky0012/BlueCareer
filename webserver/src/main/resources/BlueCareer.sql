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
    access_key VARCHAR(64),
    image_path VARCHAR(64),
    career_message VARCHAR(1024)
) ENGINE=InnoDB  auto_increment=1001  DEFAULT CHARSET=utf8;

INSERT INTO user (username, realname, password, email, qq, access_key, image_path, career_message) VALUES ('testUser', 'Hu Hao Ran', '123456', 'mail@example.com', '123456789', 'abcdefg', '/image/mail@example.com.jpg', 'Software Engineer');