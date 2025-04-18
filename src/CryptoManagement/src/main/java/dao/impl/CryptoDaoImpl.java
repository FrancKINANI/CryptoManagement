package CryptoManagement.src.main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import CryptoManagement.src.main.java.dao.interfaces.CryptoDao;
import CryptoManagement.src.main.java.models.Crypto;

public class CryptoDAOImpl implements CryptoDao {
	// Database connection
	private final Connection connection;
	
	// Constructor
	public CryptoDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void createCrypto(Crypto crypto) throws SQLException {
		String sql = "INSERT INTO cryptocurrencies (symbol, name, quantity, price, date_price) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, crypto.getSymbol());
			stmt.setString(2, crypto.getName());
			stmt.setInt(3, crypto.getQuantity());
			stmt.setDouble(4, crypto.getPrice());
			stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			stmt.executeUpdate();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					crypto.setId(rs.getInt(1));
				}
			}
		}
	}

	@Override
	public Crypto getCryptoById(int id) throws SQLException {
		String sql = "SELECT * FROM cryptocurrencies WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Crypto crypto = new Crypto();
					crypto.setId(rs.getInt("id"));
					crypto.setSymbol(rs.getString("symbol"));
					crypto.setName(rs.getString("name"));
					crypto.setQuantity(rs.getInt("quantity"));
					crypto.setPrice(rs.getDouble("price"));
					crypto.setDatePrice(rs.getDate("date_price"));
					return crypto;
				}
			}
		}
		return null;
	}

	@Override
	public Crypto getCryptoBySymbol(String symbol) throws SQLException {
		String sql = "SELECT * FROM cryptocurrencies WHERE symbol = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, symbol);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Crypto crypto = new Crypto();
					crypto.setId(rs.getInt("id"));
					crypto.setSymbol(rs.getString("symbol"));
					crypto.setName(rs.getString("name"));
					crypto.setQuantity(rs.getInt("quantity"));
					crypto.setPrice(rs.getDouble("price"));
					crypto.setDatePrice(rs.getDate("date_price"));
					return crypto;
				}
			}
		}
		return null;
	}

	@Override
	public List<Crypto> getAllCryptos() throws SQLException {
		String sql = "SELECT * FROM cryptocurrencies";
		try (PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			List<Crypto> cryptos = new ArrayList<>();
			while (rs.next()) {
				Crypto crypto = new Crypto();
				crypto.setId(rs.getInt("id"));
				crypto.setSymbol(rs.getString("symbol"));
				crypto.setName(rs.getString("name"));
				crypto.setQuantity(rs.getInt("quantity"));
				crypto.setPrice(rs.getDouble("price"));
				crypto.setDatePrice(rs.getDate("date_price"));
				cryptos.add(crypto);
			}
			return cryptos;
		}
	}

	@Override
	public void updateCrypto(Crypto crypto) throws SQLException {
		String sql = "UPDATE cryptocurrencies SET symbol = ?, name = ?, quantity = ?, price = ?, date_price = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, crypto.getSymbol());
			stmt.setString(2, crypto.getName());
			stmt.setInt(3, crypto.getQuantity());
			stmt.setDouble(4, crypto.getPrice());
			stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			stmt.setInt(6, crypto.getId());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCrypto(int id) throws SQLException {
		String sql = "DELETE FROM cryptocurrencies WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
