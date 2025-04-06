package CryptoManagement.src.main.java.services;

import java.sql.SQLException;
import CryptoManagement.src.main.java.models.Wallet;
import CryptoManagement.src.main.java.dao.interfaces.WalletDao;

public class WalletService {
	private final WalletDAO walletDAO;

	public void createWallet(Wallet wallet) throws SQLException {
		walletDAO.createWallet(wallet);
	}

	public void getWalletBalance(Wallet wallet) {
		walletDAO.getWalletBalance(wallet);
	}

	public void transferFunds() {
		// Logic to transfer funds between wallets
		
	}
	
	public void getTransactionHistory() {
		// Logic to retrieve transaction history for a wallet
	}
	
	public void deleteWallet() {
		// Logic to delete a wallet
	}

}
