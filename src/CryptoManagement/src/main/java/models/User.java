package CryptoManagement.src.main.java.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private List<Wallet> wallets;

    public User() {
        this.wallets = new ArrayList<>();
    }

    public User(String username, String passwordHash, String email) {
        this();
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Wallet> getWallets() { return wallets; }
    public void addWallet(Wallet wallet) { this.wallets.add(wallet); }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + "]";
    }
}