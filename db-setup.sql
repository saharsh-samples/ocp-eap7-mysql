CREATE DATABASE samples;

CREATE USER samples IDENTIFIED BY 'samples';

GRANT ALL PRIVILEGES ON samples.* TO 'samples';

USE samples;

CREATE TABLE sample (
  id CHAR(36) PRIMARY KEY,
  value VARCHAR(1024) NOT NULL
);

