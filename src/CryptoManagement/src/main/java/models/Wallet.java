package CryptoManagement.src.main.java.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private int id;
    private String name;
    private int userId;
    private List<Transaction> transactions;
    private BigDecimal totalValue;

    public Wallet() {
        this.transactions = new ArrayList<>();
        this.totalValue = BigDecimal.ZERO;
    }

    public Wallet(String name, int userId, BigDecimal totalValue) {
        this();
        this.name = name;
        this.userId = userId;
        this.totalValue = totalValue;
    }

    public Wallet(String string, int i, int j) {
    	this();
    	this.name = string;
    	this.userId = i;
    	this.totalValue = BigDecimal.valueOf(j);
	}

	// Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<Transaction> getTransactions() { return transactions; }

    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
    
    public void setTransactions(List<Transaction> transactions) {
    	this.transactions = transactions;
    }
     
    @Override
    public String toString() {
        return "Wallet [id=" + id + ", name=" + name + ", totalValue=" + totalValue + "]";
    }
}
