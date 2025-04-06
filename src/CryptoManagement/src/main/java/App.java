package CryptoManagement.src.main.java;

import CryptoManagement.src.main.java.dao.impl.UserDAOImpl;
import CryptoManagement.src.main.java.dao.impl.CryptoDAOImpl;
import CryptoManagement.src.main.java.dao.impl.ExchangeDAOImpl;
import CryptoManagement.src.main.java.dao.impl.TransactionDAOImpl;
import CryptoManagement.src.main.java.dao.impl.WalletDAOImpl;
import CryptoManagement.src.main.java.config.DatabaseConfig;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.models.Wallet;
import CryptoManagement.src.main.java.models.Crypto;
import CryptoManagement.src.main.java.models.Exchange;
import CryptoManagement.src.main.java.models.Transaction;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.math.BigDecimal;

public class App {
	public static void main(String[] args) throws IOException, SQLException {
		Connection conn = DatabaseConfig.getConnection();
		UserDAOImpl userDao = new UserDAOImpl(conn);
		WalletDAOImpl walletDao = new WalletDAOImpl(conn);
		CryptoDAOImpl cryptoDao = new CryptoDAOImpl(conn);
		ExchangeDAOImpl exchangeDao = new ExchangeDAOImpl(conn);
		TransactionDAOImpl transactionDao = new TransactionDAOImpl(conn);

		User newUser = new User("john_doe", "secure123", "john@example.com");
		userDao.createUser(newUser);

		Wallet wallet = new Wallet("Mon portefeuille", newUser.getId(), BigDecimal.valueOf(500));
		walletDao.createWallet(wallet);

		List<Wallet> wallets = walletDao.getWalletsByUserId(newUser.getId());
		wallets.forEach(System.out::println);
		
		Wallet wallet1 = new Wallet("Mon portefeuille Crypto", 2, BigDecimal.valueOf(1000));
		walletDao.createWallet(wallet1);

		List<Wallet> wallets1 = walletDao.getWalletsByUserId(2);
		wallets1.forEach(System.out::println);
		
		Crypto crypto = new Crypto("LINK", "Chainlink");
		cryptoDao.createCrypto(crypto);
		Crypto crypto1 = new Crypto("SUI", "Sui");
		cryptoDao.createCrypto(crypto1);
		
		List<Crypto> cryptos = cryptoDao.getAllCryptos();
		cryptos.forEach(System.out::println);
		
		String notes = "Exchange de test";
		Exchange exchange = new Exchange(1, 2, 1, 2, BigDecimal.valueOf(100), BigDecimal.valueOf(0.5), notes);
		exchangeDao.createExchange(exchange);
		List<Exchange> exchanges = exchangeDao.getAllExchanges();
		exchanges.forEach(System.out::println);
		
		Transaction transaction = new Transaction(1, 1, Transaction.TransactionType.ACHAT, BigDecimal.valueOf(10), BigDecimal.valueOf(100));
		transactionDao.createTransaction(transaction);
		List<Transaction> transactions = transactionDao.getAllTransactions();
		transactions.forEach(System.out::println);
		
		// Close the connection
		conn.close();
	}
}