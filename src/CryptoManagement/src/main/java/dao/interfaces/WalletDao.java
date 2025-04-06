package CryptoManagement.src.main.java.dao.interfaces;
import CryptoManagement.src.main.java.models.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface WalletDao {
    void createWallet(Wallet wallet) throws SQLException;
    Wallet getWalletById(int id) throws SQLException;
    List<Wallet> getWalletsByUserId(int userId) throws SQLException;
    void updateWallet(Wallet wallet) throws SQLException;
    void deleteWallet(int id) throws SQLException;
    void updateWalletValue(int walletId, double newValue) throws SQLException;
    void deleteWalletByUserId(int userId) throws SQLException;
    List<Wallet> getAllWallets() throws SQLException;
    BigDecimal getWalletBalance(int walletId) throws SQLException;
}