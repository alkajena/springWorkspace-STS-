Drop table authorities;
Drop table user;



CREATE TABLE user (
  name VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (name)
);
  
CREATE TABLE authorities (
  name VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (name) REFERENCES user(name)
);



CREATE UNIQUE INDEX ix_auth_name on authorities (name,authority);