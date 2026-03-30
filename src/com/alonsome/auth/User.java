package com.alonsome.auth;

import com.alonsome.bank.BankAccount;

public class User {
    private String username;
    private String pin;
    private double balance;
    private BankAccount bankAccount;

    public User(){}
    public User(String username, String pin,  double balance) {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
        this.bankAccount = new BankAccount(balance);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
