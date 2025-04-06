package CryptoManagement.src.main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CryptoManagement.src.main.java.dao.interfaces.ExchangeDao;
import CryptoManagement.src.main.java.models.Exchange;

public class ExchangeDaoImpl implements ExchangeDao {
	private final Connection connection;
	
	public ExchangeDaoImpl(Connection connection) {
		this.connection = connection;
	}
	
	// Implementing the methods from ExchangeDao interface
	@Override
	public void createExchange(Exchange exchange) throws SQLException {
		String sql = "INSERT INTO echanges (source_wallet_id, target_wallet_id, source_crypto_id, target_crypto_id, status, taux_echange, source_amount, target_amount, notes, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, exchange.getSourceWalletId());
			stmt.setInt(2, exchange.getTargetWalletId());
			stmt.setInt(3, exchange.getSourceCryptoId());
			stmt.setInt(4, exchange.getTargetCryptoId());
			stmt.setString(5, exchange.getStatus().name());
			stmt.setBigDecimal(6, exchange.getExchangeRate());
			stmt.setBigDecimal(7, exchange.getSourceAmount());
			stmt.setBigDecimal(8, exchange.getTargetAmount());
			stmt.setString(9, exchange.getNotes());
			stmt.setTimestamp(10, java.sql.Timestamp.valueOf(exchange.getDate()));
			stmt.executeUpdate();
		}
	}

	@Override
	public Exchange getExchangeById(int id) throws SQLException {
		String sql = "SELECT * FROM echanges WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Exchange exchange = new Exchange();
					exchange.setId(rs.getInt("id"));
					exchange.setSourceWalletId(rs.getInt("source_wallet_id"));
					exchange.setTargetWalletId(rs.getInt("target_wallet_id"));
					exchange.setSourceCryptoId(rs.getInt("source_crypto_id"));
					exchange.setTargetCryptoId(rs.getInt("target_crypto_id"));
					exchange.setStatus(Exchange.Status.valueOf(rs.getString("status")));
					exchange.setExchangeRate(rs.getBigDecimal("taux_echange"));
					exchange.setSourceAmount(rs.getBigDecimal("source_amount"));
					exchange.setTargetAmount(rs.getBigDecimal("target_amount"));
					exchange.setNotes(rs.getString("notes"));
					exchange.setDate(rs.getTimestamp("date").toLocalDateTime());
					return exchange;
				}
			}
		}
		return null;
	}

	@Override
	public List<Exchange> getAllExchanges() throws SQLException {
		String sql = "SELECT * FROM echanges";
		try (PreparedStatement stmt = connection.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			List<Exchange> exchanges = new ArrayList<>();
			while (rs.next()) {
				Exchange exchange = new Exchange();
				exchange.setId(rs.getInt("id"));
				exchange.setSourceWalletId(rs.getInt("source_wallet_id"));
				exchange.setTargetWalletId(rs.getInt("target_wallet_id"));
				exchange.setSourceCryptoId(rs.getInt("source_crypto_id"));
				exchange.setTargetCryptoId(rs.getInt("target_crypto_id"));
				exchange.setStatus(Exchange.Status.valueOf(rs.getString("status")));
				exchange.setExchangeRate(rs.getBigDecimal("taux_echange"));
				exchange.setSourceAmount(rs.getBigDecimal("source_amount"));
				exchange.setTargetAmount(rs.getBigDecimal("target_amount"));
				exchange.setNotes(rs.getString("notes"));
				exchange.setDate(rs.getTimestamp("date").toLocalDateTime());
				exchanges.add(exchange);
			}
			return exchanges;
		}
	}

	@Override
	public void updateExchange(Exchange exchange) throws SQLException {
		String sql = "UPDATE echanges SET source_wallet_id = ?, target_wallet_id = ?, source_crypto_id = ?, target_crypto_id = ?, status = ?, taux_echange = ?, source_amount = ?, target_amount = ?, notes = ?, date = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, exchange.getSourceWalletId());
			stmt.setInt(2, exchange.getTargetWalletId());
			stmt.setInt(3, exchange.getSourceCryptoId());
			stmt.setInt(4, exchange.getTargetCryptoId());
			stmt.setString(5, exchange.getStatus().name());
			stmt.setBigDecimal(6, exchange.getExchangeRate());
			stmt.setBigDecimal(7, exchange.getSourceAmount());
			stmt.setBigDecimal(8, exchange.getTargetAmount());
			stmt.setString(9, exchange.getNotes());
			stmt.setTimestamp(10, java.sql.Timestamp.valueOf(exchange.getDate()));
			stmt.setInt(11, exchange.getId());
			stmt.executeUpdate();
		}
	}

	@Override
	public void deleteExchange(int id) throws SQLException {
		String sql = "DELETE FROM echanges WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
	}
}
