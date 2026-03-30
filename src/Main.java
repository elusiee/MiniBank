import com.alonsome.auth.User;
import com.alonsome.bank.BankAccount;
import com.alonsome.bank.Transaction;
import com.alonsome.extras.HashUtil;
import com.alonsome.extras.JsonHandler;
import enums.TransactionStatus;
import enums.TransactionType;

import java.util.Scanner;

public class Main {
    static boolean running = true;

    public static void main(String[] args) {
        JsonHandler jsonHandler = new JsonHandler();
        Scanner sc = new Scanner(System.in);

        while (running) {
            options();
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Your Username: ");
                        String username = sc.next();
                        System.out.print("Enter Your Pin: ");
                        String pin = sc.next();

                        User loggedInUser = authenticate(username, pin, jsonHandler);

                        if (loggedInUser != null) {
                            showAtmMenu(loggedInUser, jsonHandler, sc);
                        } else {
                            System.out.println("Invalid login details, please try again.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Your Username: ");
                        String newUsername = sc.next();
                        System.out.print("Enter Your Pin: ");
                        int newPin = sc.nextInt();
                        String hashedPin = HashUtil.hashPin(String.valueOf(newPin));
                        System.out.print("Enter Your Balance: ");
                        double balance = sc.nextDouble();

                        User user = new User(newUsername, hashedPin, balance);
                        new BankAccount(user.getBalance());
                        jsonHandler.saveUser(user);
                        System.out.println("Account created successfully.");
                        break;

                    case 3:
                        running = false;
                        System.out.println("Bye!");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // clear bad input
            }
        }

        sc.close();
    }

    private static User authenticate(String username, String pin, JsonHandler jsonHandler) {
        User user = jsonHandler.loadUser(username);

        String enteredPinHash = HashUtil.hashPin(pin);

        if (user != null && username.equals(user.getUsername())) {
            assert enteredPinHash != null;
            if (enteredPinHash.equals(user.getPin())) {
                return user; // login successful
            }
        }

        return null; // login failed
    }

    private static void showAtmMenu(User user, JsonHandler jsonHandler, Scanner sc) {
        BankAccount acc = new BankAccount(user.getBalance());
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n=== ATM MENU - CURRENTLY WORKS FOR " + user.getUsername() + " ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Please enter your choice: ");

            int authChoice = sc.nextInt();
            Transaction tx;

            switch (authChoice) {
                case 1:
                    System.out.println("Balance is " + acc.getBalance());
                    break;

                case 2:
                    try {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        acc.deposit(amount);

                        tx = new Transaction(
                                amount,
                                user.getUsername(),
                                TransactionType.CREDIT,
                                acc.getBalance(),
                                TransactionStatus.SUCCESS
                        );

                        jsonHandler.updateUser(user.getUsername(), acc.getBalance());
                        jsonHandler.saveToFile(tx);

                        System.out.println("Deposit successful. New balance: " + acc.getBalance());
                    } catch (Exception e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Please enter your pin: ");
                        String wtPin = sc.next();

                        if (wtPin.equals(user.getPin())) {
                            System.out.print("Enter amount to withdraw: ");
                            double withdraw = sc.nextDouble();
                            acc.withdraw(withdraw);

                            tx = new Transaction(
                                    withdraw,
                                    user.getUsername(),
                                    TransactionType.DEBIT,
                                    acc.getBalance(),
                                    TransactionStatus.SUCCESS
                            );

                            jsonHandler.updateUser(user.getUsername(), acc.getBalance());
                            jsonHandler.saveToFile(tx);

                            System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
                        } else {
                            System.out.println("Invalid pin, please try again");
                        }
                    } catch (Exception e) {
                        System.out.println("Something happened while trying to record transaction: " + e.getMessage());
                    }
                    break;

                case 4:
                    loggedIn = false; // go back to login page
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void options() {
        System.out.println("\n=== ATM MENU - Please kindly Login/Create Account ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
}