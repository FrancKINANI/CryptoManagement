package CryptoManagement.src.main.java.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Exchange {
	public enum Status { COMPLETED, PENDING, FAILED };
	
    private int id;
    private LocalDateTime date;
    private int sourceWalletId;
    private int targetWalletId;
    private int sourceCryptoId;
    private int targetCryptoId;
    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;
    private BigDecimal exchangeRate;
    private String notes;
    private Status status;

    public Exchange() {
        this.date = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public Exchange(int sourceWalletId, int targetWalletId, 
                   int sourceCryptoId, int targetCryptoId,
                   BigDecimal sourceAmount, BigDecimal exchangeRate) {
        this();
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.sourceCryptoId = sourceCryptoId;
        this.targetCryptoId = targetCryptoId;
        this.sourceAmount = sourceAmount;
        this.exchangeRate = exchangeRate;
        this.targetAmount = sourceAmount.multiply(exchangeRate);
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getSourceWalletId() {
        return sourceWalletId;
    }

    public void setSourceWalletId(int sourceWalletId) {
        this.sourceWalletId = sourceWalletId;
    }

    public int getTargetWalletId() {
        return targetWalletId;
    }

    public void setTargetWalletId(int targetWalletId) {
        this.targetWalletId = targetWalletId;
    }

    public int getSourceCryptoId() {
        return sourceCryptoId;
    }

    public void setSourceCryptoId(int sourceCryptoId) {
        this.sourceCryptoId = sourceCryptoId;
    }

    public int getTargetCryptoId() {
        return targetCryptoId;
    }

    public void setTargetCryptoId(int targetCryptoId) {
        this.targetCryptoId = targetCryptoId;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
        calculateTargetAmount();
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
        calculateExchangeRate();
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
        calculateTargetAmount();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status.equals(Status.COMPLETED) || status.equals(Status.PENDING) || status.equals(Status.FAILED)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Statut d'échange invalide");
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private void calculateTargetAmount() {
        if (exchangeRate != null && sourceAmount != null) {
            this.targetAmount = sourceAmount.multiply(exchangeRate);
        }
    }

    private void calculateExchangeRate() {
        if (sourceAmount != null && targetAmount != null 
            && sourceAmount.compareTo(BigDecimal.ZERO) != 0) {
        	this.exchangeRate = targetAmount.divide(sourceAmount, 8, RoundingMode.HALF_UP);
        }
    }

    public void completeExchange() {
        this.status = Status.COMPLETED;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Exchange [id=" + id + ", date=" + date + 
               ", sourceCryptoId=" + sourceCryptoId + 
               ", targetCryptoId=" + targetCryptoId +
               ", amount=" + sourceAmount + " → " + targetAmount + 
               ", rate=" + exchangeRate + ", status=" + status + "]";
    }
}