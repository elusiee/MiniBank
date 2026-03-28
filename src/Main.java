import com.alonsome.bank.BankAccount;
import com.alonsome.bank.Transaction;
import enums.TransactionStatus;
import enums.TransactionType;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount acc = new BankAccount(1000);
        Transaction tx;
        acc.createPin(2313);
        int pin = acc.getPin();

        boolean running = true;

        while (running) {
            System.out.println("\n=== ATM MENU ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("Please enter your choice: ");

            try{
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Please enter your pin: ");
                        int cbPin = sc.nextInt();
                        if (cbPin == pin) {
                            System.out.println("Balance is " + acc.getBalance());
                        }else{
                            System.out.println("Invalid pin, please try again");
                        }
                    break;
                    case 2:
                        System.out.println("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        acc.deposit(amount);
                        tx = new Transaction(amount, TransactionType.CREDIT, acc.getBalance(), TransactionStatus.SUCCESS);
                        System.out.println(tx.toString());
                    break;
                    case 3:
                        System.out.println("Please enter your pin: ");
                        int wtPin = sc.nextInt();
                        if (wtPin == pin) {
                            System.out.println("Enter amount to withdraw: ");
                            double withdraw = sc.nextDouble();
                            acc.withdraw(withdraw);
                            tx = new Transaction(withdraw, TransactionType.DEBIT, acc.getBalance(), TransactionStatus.SUCCESS);
                            System.out.println(tx.toString());
                        }else{
                            System.out.println("Invalid pin, please try again");
                        }
                    break;
                    case 4: running = false;
                            System.out.println("Bye!");
                    break;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
                sc.nextLine();
            }
        }

        sc.close();
    }
}