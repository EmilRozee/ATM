import java.util.Scanner;

public class ATMApp {
    private Account account;
    private Scanner scanner;

    public ATMApp() {
        // Hardcoded account for demo: account number 12345, pin 6789, balance 1000
        account = new Account("12345", "6789", 1000.0);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM Machine!");
        if (login()) {
            showMenu();
        } else {
            System.out.println("Too many failed attempts. Exiting.");
        }
    }

    private boolean login() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter Account Number: ");
            String accNum = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            if (account.authenticate(accNum, pin)) {
                System.out.println("Login successful!\n");
                return true;
            } else {
                System.out.println("Invalid account number or PIN. Try again.\n");
                attempts++;
            }
        }
        return false;
    }

    private void showMenu() {
        while (true) {
            System.out.println("ATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleWithdraw();
                    break;
                case "2":
                    handleDeposit();
                    break;
                case "3":
                    System.out.printf("Current Balance: $%.2f\n\n", account.getBalance());
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (account.withdraw(amount)) {
                System.out.printf("Withdrawal successful. New Balance: $%.2f\n\n", account.getBalance());
            } else {
                System.out.println("Insufficient balance or invalid amount.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.\n");
        }
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (account.deposit(amount)) {
                System.out.printf("Deposit successful. New Balance: $%.2f\n\n", account.getBalance());
            } else {
                System.out.println("Invalid amount.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.\n");
        }
    }
} 