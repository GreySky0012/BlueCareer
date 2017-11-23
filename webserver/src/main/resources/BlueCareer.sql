-- CREATE TABLE for user
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


-- CREATE TABLE for article
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `article`;
CREATE TABLE article
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title VARCHAR(64) NOT NULL,
  content LONGTEXT NOT NULL ,
  view_count INT,
  job_name VARCHAR(64) NOT NULL
) ENGINE=InnoDB  auto_increment=1001  DEFAULT CHARSET=utf8;

INSERT INTO article (id, title, content, view_count, job_name) VALUES ('1001', 'title', 'content', '300', 'Software Engineer');
INSERT INTO article (id, title, content, view_count, job_name) VALUES ('1002', 'title2', 'content2', '302', 'Software Engineer');

-- CREATE TABLE for job
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `job`;
CREATE TABLE job
(
  job_name VARCHAR(64) PRIMARY KEY NOT NULL,
  duty TEXT NOT NULL,
  requirements TEXT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO job (job_name, duty, requirements) VALUES ('Java', '负责...', '熟悉....，了解...');

-- CREATE TABLE for job
SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `salary`;
CREATE TABLE salary
(
  job_name VARCHAR(64) NOT NULL,
  work_place VARCHAR(64) NOT NULL,
  salary DOUBLE NOT NULL,
  primary key (job_name, work_place)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO salary (job_name, work_place, salary) VALUES ('Java', '上海', '200000');