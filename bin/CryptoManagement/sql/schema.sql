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

CREATE TABLE echanges (
	id INT PRIMARY KEY AUTO_INCREMENT,
    source_wallet_id INT NOT NULL,
    target_wallet_id INT NOT NULL,
    source_crypto_id INT NOT NULL,
    target_crypto_id INT NOT NULL,
    status ENUM('COMPLETED', 'PENDING', 'FAILED'),
    quantity DECIMAL(18, 8) NOT NULL,
    tauxEchange DECIMAL(18, 8) NOT NULL, -- Taux de change entre les cryptomonnaies
    source_price DECIMAL(18, 8) NOT NULL, -- Prix de la cryptomonnaie source
    target_price DECIMAL(18, 8) NOT NULL, -- Prix de la cryptomonnaie cible
    notes TEXT, -- Notes sur l'échange
    price DECIMAL(18, 8) NOT NULL, -- Prix saisi manuellement
    date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (source_wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (target_wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (source_crypto_id) REFERENCES cryptocurrencies(id),
    FOREIGN KEY (target_crypto_id) REFERENCES cryptocurrencies(id)
);