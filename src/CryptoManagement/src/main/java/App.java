package CryptoManagement.src.main.java;

import CryptoManagement.src.main.java.dao.impl.UserDAOImpl;
import CryptoManagement.src.main.java.dao.impl.WalletDAOImpl;
import CryptoManagement.src.main.java.config.DatabaseConfig;
import CryptoManagement.src.main.java.models.User;
import CryptoManagement.src.main.java.models.Wallet;
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
	}
}