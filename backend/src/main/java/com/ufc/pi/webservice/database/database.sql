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