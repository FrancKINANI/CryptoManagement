package CryptoManagement.src.main.java.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import CryptoManagement.src.main.java.models.Transaction;

public interface TransactionDao {
		// Define methods for transaction management
	void createTransaction(Transaction transaction) throws SQLException;
	Transaction getTransactionById(int id) throws SQLException;
	List<Transaction> getTransactionsByWalletId(int walletId) throws SQLException;
	void updateTransaction(Transaction transaction) throws SQLException;
	void deleteTransaction(int id) throws SQLException;
	List<Transaction> getAllTransactions() throws SQLException;
}
