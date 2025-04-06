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

-- Insérer des échanges
INSERT INTO echanges (source_wallet_id, target_wallet_id, source_crypto_id, target_crypto_id, status, quantity, tauxEchange, source_price, target_price, notes, price) VALUES
(1, 2, 1, 2, 'COMPLETED', 0.1, 0.05, 30000.00, 2000.00, 'Échange BTC vers ETH', 1500.00),
(2, 1, 2, 1, 'PENDING', 1.0, 0.05, 2000.00, 30000.00, 'Échange ETH vers BTC', 10000.00);
