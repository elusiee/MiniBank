package com.alonsome.extras;

import com.alonsome.auth.User;
import com.alonsome.bank.Transaction;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {
    private static final String TX_FILE_PATH = "db/transactions.json";
    private static final String ACCOUNT_FILE_PATH = "db/users.json";
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    File txFile = new File(TX_FILE_PATH);
    File userFile = new File(ACCOUNT_FILE_PATH);

    public void saveToFile(Transaction tx) {
        List<Transaction> transactions = new ArrayList<>();
        try{
            if (txFile.exists() && txFile.length() > 0) {
                transactions = mapper.readValue(txFile, new TypeReference<List<Transaction>>() {});
            }
            transactions.add(tx);
            mapper.writeValue(this.txFile, transactions);
        }catch(Exception e){
            System.out.println("Error in JsonHandler.saveToFile: " + e.getMessage());
        }
    }

    public Transaction loadFromFile() {
        try {
            if (!txFile.exists() || txFile.length() == 0) {
                System.out.println("No transactions found.");
                return null;
            }

            List<Transaction> transactions = mapper.readValue(
                    txFile,
                    new TypeReference<List<Transaction>>() {}
            );

            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
                return null;
            }

            return transactions.getLast();

        } catch (Exception e) {
            System.out.println("Error in JsonHandler.loadFromFile: " + e.getMessage());
            return null;
        }
    }

    public void saveUser(User user) {

        List<User> users = new ArrayList<>();
        try{
            if (userFile.exists() && userFile.length() > 0) {
                users = mapper.readValue(userFile, new TypeReference<List<User>>() {});
            }
            users.add(user);
            mapper.writeValue(this.userFile, users);
        }catch(Exception e){
            System.out.println("Error in JsonHandler.saveToFile: " + e.getMessage());
        }

    }

    public void updateUser(String username, double balance) {
        try{
            if (userFile.exists() && userFile.length() > 0) {
                List<User> users = mapper.readValue(
                        userFile,
                        new TypeReference<List<User>>() {}
                );

                for (User user : users) {
                    if (user.getUsername().equalsIgnoreCase(username)) {
                        user.setBalance(balance);
                        mapper.writeValue(this.userFile, users);
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Error in JsonHandler.updateUser: " + e.getMessage());
        }
    }

    public User loadUser(String username) {
        try {
            if (!userFile.exists() || userFile.length() == 0) {
                System.out.println("No Users found.");
                return null;
            }

            List<User> users = mapper.readValue(
                    userFile,
                    new TypeReference<List<User>>() {}
            );

            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return user;
                }
            }

            if (users.isEmpty()) {
                System.out.println("No Users found.");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error in JsonHandler.loadUser: " + e.getMessage());
            return null;
        }

        return null;
    }
}
