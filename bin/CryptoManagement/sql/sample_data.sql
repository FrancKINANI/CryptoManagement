-- Insérer des utilisateurs
INSERT INTO users (username, password_hash, email) VALUES
('user1', 'hashedpassword1', 'user1@example.com'),
('user2', 'hashedpassword2', 'user2@example.com');

-- Insérer des portefeuilles
INSERT INTO wallets (user_id, name) VALUES
(1, 'Portefeuille Principal'),
(2, 'Portefeuille Secondaire');

-- Insérer des cryptomonnaies
INSERT INTO cryptocurrencies (symbol, name) VALUES
('BTC', 'Bitcoin'),
('ETH', 'Ethereum'),
('LTC', 'Litecoin');

-- Insérer des transactions
INSERT INTO transactions (wallet_id, crypto_id, type, quantity, price) VALUES
(1, 1, 'ACHAT', 0.5, 30000.00),
(2, 2, 'VENTE', 2.0, 2000.00);
