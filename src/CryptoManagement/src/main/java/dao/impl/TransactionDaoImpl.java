package CryptoManagement.src.main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import CryptoManagement.src.main.java.dao.interfaces.TransactionDao;
import CryptoManagement.src.main.java.models.Transaction;

public class TransactionDAOImpl implements TransactionDao {
	private final Connection connection;
	
	public TransactionDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	// Implement methods for transaction management
	@Override
	public void createTransaction(Transaction transaction) throws SQLException {
		 String sql = "INSERT INTO transactions (wallet_id, crypto_id, quantity, price, date) VALUES (?, ?, ?, ?, ?)";
		 try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			 stmt.setInt(1, transaction.getWalletId());
			 stmt.setInt(2, transaction.getCryptoId());
			 stmt.setBigDecimal(3, transaction.getQuantity());
			 stmt.setBigDecimal(4, transaction.getPricePerUnit());
			 stmt.setTimestamp(5, Timestamp.valueOf(transaction.getDate()));
			 stmt.executeUpdate();

			 try (ResultSet rs = stmt.getGeneratedKeys()) {
				 if (rs.next()) {
					 transaction.setId(rs.getInt(1));
				 }
			 }
		 }
	}

	@Override
	public Transaction getTransactionById(int id) throws SQLException {
		String sql = "SELECT * FROM transactions WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Transaction transaction = new Transaction();
					transaction.setId(rs.getInt("id"));
					transaction.setWalletId(rs.getInt("wallet_id"));
					transaction.setCryptoId(rs.getInt("crypto_id"));
					transaction.setType(Transaction.TransactionType.valueOf(rs.getString("type")));
					transaction.setQuantity(rs.getBigDecimal("quantity"));
					transaction.setPricePerUnit(rs.getBigDecimal("price"));
					transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
					return transaction;
				}
			}
		}
		return null;
	}

	@Override
	public List<Transaction> getTransactionsByWalletId(int walletId) throws SQLException {
		String sql = "SELECT * FROM transactions WHERE wallet_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, walletId);
			try (ResultSet rs = stmt.executeQuery()) {
				List<Transaction> transactions = new ArrayList<>();
				while (rs.next()) {
					Transaction transaction = new Transaction();
					transaction.setId(rs.getInt("id"));
					transaction.setWalletId(rs.getInt("wallet_id"));
					transaction.setCryptoId(rs.getInt("crypto_id"));
					transaction.setType(Transaction.TransactionType.valueOf(rs.getString("type")));
					transaction.setQuantity(rs.getBigDecimal("quantity"));
					transaction.setPricePerUnit(rs.getBigDecimal("price"));
					transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
					transactions.add(transaction);
				}
				return transactions;
			}
		}
	}

	@Override
	public void updateTransaction(Transaction transaction) throws SQLException {
		String sql = "UPDATE transactions SET wallet_id = ?, crypto_id = ?, type = ?, quantity = ?, price = ?, date = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, transaction.getWalletId());
			stmt.setInt(2, transaction.getCryptoId());
			stmt.setString(3, transaction.getType().name());
			stmt.setBigDecimal(3, transaction.getQuantity());
			stmt.setBigDecimal(4, transaction.getPricePerUnit());
			stmt.setTimestamp(5, Timestamp.valueOf(transaction.getDate()));
			stmt.setInt(6, transaction.getId());
			stmt.executeUpdate();
		}
	}

	@Override
	public void deleteTransaction(int id) throws SQLException {
		String sql = "DELETE FROM transactions WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}

	@Override
	public List<Transaction> getAllTransactions() throws SQLException {
		String sql = "SELECT * FROM transactions";
		try (PreparedStatement stmt = connection.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			List<Transaction> transactions = new ArrayList<>();
			while (rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(rs.getInt("id"));
				transaction.setWalletId(rs.getInt("wallet_id"));
				transaction.setCryptoId(rs.getInt("crypto_id"));
				transaction.setType(Transaction.TransactionType.valueOf(rs.getString("type")));
				transaction.setQuantity(rs.getBigDecimal("quantity"));
				transaction.setPricePerUnit(rs.getBigDecimal("price"));
				transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
				transactions.add(transaction);
			}
			return transactions;
		}
	}

}
