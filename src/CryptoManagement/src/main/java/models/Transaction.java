package CryptoManagement.src.main.java.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType { ACHAT, VENTE, ENVOI }
    
    private int id;
    private LocalDateTime date;
    private TransactionType type;
    private BigDecimal quantity;
    private BigDecimal pricePerUnit;
    private int walletId;
    private int cryptoId;

    public Transaction() {}

    public Transaction(TransactionType type, BigDecimal quantity, BigDecimal pricePerUnit) {
        this.date = LocalDateTime.now();
        this.type = type;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public int getWalletId() { return walletId; }
    public void setWalletId(int walletId) { this.walletId = walletId; }

    public int getCryptoId() { return cryptoId; }
    public void setCryptoId(int cryptoId) { this.cryptoId = cryptoId; }

    public BigDecimal getTotalAmount() {
        return quantity.multiply(pricePerUnit);
    }

    @Override
    public String toString() {
        return "Transaction [date=" + date + ", type=" + type + ", amount=" + getTotalAmount() + "]";
    }
}