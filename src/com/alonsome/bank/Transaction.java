package com.alonsome.bank;

import enums.TransactionStatus;
import enums.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.UUID;

public class Transaction {
    private final String transactionID;
    private double amount;
    private TransactionType type;
    private double balance;
    private TransactionStatus status;
    private String createdAt;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

    public Transaction(double amount, TransactionType type, double balance, TransactionStatus status) {
        this.transactionID = getTransactionID();
        this.amount = amount;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.createdAt = LocalDateTime.now().format(formatter);
    }

    public String toString() {
        return "\n=== TRANSACTION HISTORY ===" + "\nTransaction ID: " + transactionID + "\nAmount: " + amount + "\nTransaction Type: " + type + "\nBalance Remaining: " + balance + "\nStatus: " + status + "\nTime: " +  createdAt;
    }

    public static String getTransactionID() {
        return UUID.randomUUID().toString();
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

    public boolean isSuccessful() {
        return this.status == TransactionStatus.SUCCESS;
    }

}
