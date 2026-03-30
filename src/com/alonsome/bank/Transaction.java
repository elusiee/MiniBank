package com.alonsome.bank;

import com.alonsome.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;

public class Transaction {
    private String transactionID;
    private double amount;
    private String user;
    private TransactionType type;
    private double balance;
    private TransactionStatus status;
    private String createdAt;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

    public Transaction(){}

    public Transaction(double amount, String user, TransactionType type, double balance, TransactionStatus status) {
        this.transactionID = generateTransactionID();
        this.amount = amount;
        this.user = user;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public String toString() {
        return "\n=== TRANSACTION HISTORY ===" + "\nTransaction ID: " + transactionID + "\nAmount: " + amount + "\nTransaction Type: " + type + "\nBalance Remaining: " + balance + "\nStatus: " + status + "\nTime: " +  createdAt;
    }

    public static String generateTransactionID() {
        return UUID.randomUUID().toString();
    }

    public String getUser() {
        return user;
    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getTransactionID() {
        return transactionID;
    }

    public TransactionType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public double getAmount() {
        return amount;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void markTransactionAsFailed() {
        this.status = TransactionStatus.FAILED;
    }

    public void markTransactionAsPending() {
        this.status = TransactionStatus.PENDING;
    }

    public void markTransactionAsSuccess() {
        this.status = TransactionStatus.SUCCESS;
    }

    @JsonIgnore
    public boolean isSuccessful() {
        return this.status == TransactionStatus.SUCCESS;
    }

}
