drop schema fmcdata;
create schema fmcdata;
use fmcdata;

CREATE TABLE addresses (
  id bigint NOT NULL UNIQUE AUTO_INCREMENT,
  street_no varchar(255),
  street_name varchar(255),
  city varchar(255),
  state varchar(255),
  post_code varchar(255),
  country varchar(255),
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id bigint NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
  email varchar(255) NOT NULL UNIQUE,
  name varchar(255) NOT NULL,
  birth_date varchar(255) NOT NULL,
  job varchar(255) NOT NULL,
  phone_no varchar(255) NOT NULL,
  address_id bigint,
  FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE accounts (
  id bigint NOT NULL UNIQUE AUTO_INCREMENT,
  acc_type varchar(255),
  acc_number varchar(255),
  acc_name varchar(255),
  balance varchar(255),
  curr_date varchar(255),
  user_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

