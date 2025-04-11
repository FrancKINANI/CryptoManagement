package CryptoManagement.src.main.java.dao.impl;

import CryptoManagement.src.main.java.dao.interfaces.WalletDao;
import CryptoManagement.src.main.java.models.Wallet;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletDAOImpl implements WalletDao {
    private final Connection connection;
    
    // Constructor
    public WalletDAOImpl(Connection connection) {
		this.connection = connection;
	}
    
    @Override
    public void createWallet(Wallet wallet) throws SQLException {
        String sql = "INSERT INTO wallets (name, user_id, total_value) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, wallet.getName());
            stmt.setInt(2, wallet.getUserId());
            stmt.setBigDecimal(3, wallet.getTotalValue());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    wallet.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Wallet getWalletById(int id) throws SQLException {
        String sql = "SELECT * FROM wallets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWallet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Wallet> getWalletsByUserId(int userId) throws SQLException {
        List<Wallet> wallets = new ArrayList<>();
        String sql = "SELECT * FROM wallets WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    wallets.add(mapResultSetToWallet(rs));
                }
            }
        }
        return wallets;
    }

    @Override
    public void updateWallet(Wallet wallet) throws SQLException {
        String sql = "UPDATE wallets SET name = ?, total_value = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, wallet.getName());
            stmt.setBigDecimal(2, wallet.getTotalValue());
            stmt.setInt(3, wallet.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteWallet(int id) throws SQLException {
        String sql = "DELETE FROM wallets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateWalletValue(int walletId, double newValue) throws SQLException {
        String sql = "UPDATE wallets SET total_value = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newValue);
            stmt.setInt(2, walletId);
            stmt.executeUpdate();
        }catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    @Override
    public BigDecimal getWalletBalance(int walletId) throws SQLException {
		String sql = "SELECT total_value FROM wallets WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, walletId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getBigDecimal("total_value");
				}
			}
		}
		return null;
	}
    
    private Wallet mapResultSetToWallet(ResultSet rs) throws SQLException {
        Wallet wallet = new Wallet();
        try {
			wallet.setId(rs.getInt("id"));
			wallet.setName(rs.getString("name"));
			wallet.setUserId(rs.getInt("user_id"));
			wallet.setTotalValue(rs.getBigDecimal("total_value"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wallet;
    }
    
    @Override
    public void deleteWalletByUserId(int userId) throws SQLException {
		String sql = "DELETE FROM wallets WHERE user_id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Wallet> getAllWallets() throws SQLException {
		List<Wallet> wallets = new ArrayList<>();
		String sql = "SELECT * FROM wallets";
		try (Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				wallets.add(mapResultSetToWallet(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return wallets;
	}
}
