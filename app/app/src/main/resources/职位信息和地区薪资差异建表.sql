-- ----------------------------
-- Table for Job details
-- ----------------------------

DROP TABLE IF EXISTS job_details;
CREATE TABLE job_details (
  job_name VARCHAR(50) NOT NULL,
  job_duty TEXT,
  job_require TEXT,
  PRIMARY KEY  (job_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records for test
-- ----------------------------
INSERT INTO job_details VALUES ('Java开发工程师', '后端开发，实现功能、业务需求等', '掌握数据结构、算法、计算机网络等基础，掌握面对对象知识、Java基础、SSH/SSM框架等');
INSERT INTO job_details VALUES ('web前端工程师', '前端开发，将UI的界面实现在前端', '掌握js、JQuery等前端基础，了解React、Bootstrap等前端框架');

/*---------------- 我是分割线 ---------------------*/

-- ----------------------------
-- Table for Salary difference in position area
-- ----------------------------
DROP TABLE IF EXISTS job_area_pays;
CREATE TABLE job_area_pays (
  job_name VARCHAR(50) NOT NULL,
  work_place VARCHAR(50) NOT NULL,
  salary INT,
  PRIMARY KEY  (job_name, work_place)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records for test
-- ----------------------------
INSERT INTO job_area_pays VALUES ('Java开发工程师', '南京', 8000);
INSERT INTO job_area_pays VALUES ('Java开发工程师', '北京', 12000);
INSERT INTO job_area_pays VALUES ('web前端工程师', '南京',7000);
INSERT INTO job_area_pays VALUES ('web前端工程师', '北京',11000);