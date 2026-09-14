import java.util.Scanner;
class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
class ATM {
    private BankAccount account;
    private Scanner scanner;
    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }
    public void start() {
        int choice;
        System.out.println("=================================");
        System.out.println("          WELCOME TO ATM");
        System.out.println("=================================");
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    System.out.println("\nThank you for using the ATM.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }
    private void displayMenu() {
        System.out.println("\n----------- ATM MENU -----------");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }
    private void checkBalance() {
        System.out.printf("\nCurrent Balance: ₹%.2f\n", account.getBalance());
    }
    private void depositMoney() {
        System.out.print("\nEnter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        if (account.deposit(amount)) {
            System.out.printf("₹%.2f deposited successfully.\n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    private void withdrawMoney() {
        System.out.print("\nEnter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.printf("₹%.2f withdrawn successfully.\n", amount);
        } else {
            System.out.println("Transaction failed. " + "Check the amount or available balance.");
        }
    }
}
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);
        ATM atm = new ATM(account);
        atm.start();
    }
}