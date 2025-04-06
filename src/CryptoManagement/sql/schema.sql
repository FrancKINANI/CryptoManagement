-- Création de la base de données
CREATE DATABASE crypto_management;
USE crypto_management;

-- Table Utilisateurs
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Table Portefeuilles
CREATE TABLE wallets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table Cryptomonnaies (référence statique)
CREATE TABLE cryptocurrencies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(10) UNIQUE NOT NULL, -- Ex: BTC, ETH
    name VARCHAR(50) NOT NULL
);

-- Table Transactions (manuel)
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    wallet_id INT NOT NULL,
    crypto_id INT NOT NULL,
    type ENUM('ACHAT', 'VENTE', 'ENVOI'),
    quantity DECIMAL(18, 8) NOT NULL,
    price DECIMAL(18, 8) NOT NULL, -- Prix saisi manuellement
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (crypto_id) REFERENCES cryptocurrencies(id)
);
