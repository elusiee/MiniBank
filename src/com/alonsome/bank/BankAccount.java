package com.alonsome.bank;

public class BankAccount {

    private double balance;
    private int pin;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " successfully deposited");
        }else{
            System.out.println("Invalid deposit amount: " + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance: " + balance + " please top up");
        }else if(amount <= 0){
            System.out.println("Invalid withdrawal amount: " + amount);
        }else{
            balance -= amount;
            System.out.println(amount + " successfully deposited");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void createPin(int pin){
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
}
