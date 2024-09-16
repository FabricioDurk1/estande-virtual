/** Creates the database */
CREATE DATABASE IF NOT EXISTS estante_virtual;

/** Creates the users table */
CREATE TABLE IF NOT EXISTS users  (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   cpf VARCHAR(11) UNIQUE NOT NULL,
   birth_date DATE NOT NULL,
   role VARCHAR(255) NOT NULL,
   phone_number VARCHAR(11) UNIQUE NOT NULL,
   address_street VARCHAR(255),
   address_number VARCHAR(10),
   address_complement VARCHAR(255),
   address_neighborhood VARCHAR(255),
   address_city VARCHAR(255),
   address_state VARCHAR(2),
   address_zip_code VARCHAR(8)
);

/** Creates the publishers table */
CREATE TABLE IF NOT EXISTS publishers (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL
);

/** Creates the authors table */
CREATE TABLE IF NOT EXISTS authors  (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL
);

/** Creates the books table */
CREATE TABLE IF NOT EXISTS books  (
   id INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL,
   price DECIMAL(10, 2) NOT NULL,
   quantity INT NOT NULL,
   publisher_id INT NOT NULL,
   author_id INT NOT NULL,
   FOREIGN KEY (publisher_id) REFERENCES publishers(id),
   FOREIGN KEY (author_id) REFERENCES authors(id)
);

/** Creates credit cards table */
CREATE TABLE IF NOT EXISTS credit_cards (
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   flag VARCHAR(255) NOT NULL,
   number VARCHAR(16) NOT NULL,
   expiration_date DATE NOT NULL,
   security_code VARCHAR(3) NOT NULL,
   credit_limit DECIMAL(10, 2) NOT NULL,
   user_id INT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users(id)
);