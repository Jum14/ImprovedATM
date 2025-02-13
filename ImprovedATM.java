import java.util.HashMap;
import java.util.Scanner;

class ATM {
    double balance;
    String accountNumber;
    String pin;

    ATM(String accNum, String accPin, double initialBalance) {
        accountNumber = accNum;
        pin = accPin;
        balance = initialBalance;
    }

    double checkBalance() {
        return balance;
    }

    double deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return balance;
        } else {
            return -1;
        }
    }

    double withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return balance;
        } else {
            return -1;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> users = new HashMap<>();
        HashMap<String, ATM> accounts = new HashMap<>();

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) { // Register
                System.out.print("Enter new Account Number: ");
                String newAccount = scanner.nextLine();
                if (users.containsKey(newAccount)) {
                    System.out.println("There's already an account registered with this number!");
                    continue;
                }
                System.out.print("Enter new PIN: ");
                String newPin = scanner.nextLine();
                users.put(newAccount, newPin);
                accounts.put(newAccount, new ATM(newAccount, newPin, 0));
                System.out.println("Registration Successful!");

            } else if (choice == 2) { // Login
                System.out.print("Enter Account Number: ");
                String enteredAccount = scanner.nextLine();
                System.out.print("Enter PIN: ");
                String enteredPin = scanner.nextLine();

                if (users.containsKey(enteredAccount) && users.get(enteredAccount).equals(enteredPin)) {
                    ATM atm = accounts.get(enteredAccount);
                    System.out.println("Login Successful!");

                    while (true) {
                        System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Logout");
                        System.out.print("Choose an option: ");
                        int option = scanner.nextInt();

                        if (option == 1) {
                            System.out.println("Your balance: $" + atm.checkBalance());
                        } else if (option == 2) {
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            double newBalance = atm.deposit(depositAmount);
                            if (newBalance != -1) {
                                System.out.println("Deposit successful. New balance: $" + newBalance);
                            } else {
                                System.out.println("Invalid deposit amount.");
                            }
                        } else if (option == 3) {
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            double newBalance = atm.withdraw(withdrawAmount);
                            if (newBalance != -1) {
                                System.out.println("Withdrawal successful. New balance: $" + newBalance);
                            } else {
                                System.out.println("Invalid withdrawal amount or insufficient funds.");
                            }
                        } else if (option == 4) {
                            System.out.println("Logged out.");
                            break;
                        } else {
                            System.out.println("Invalid option. Try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid Account Number or PIN. Try again.");
                }

            } else if (choice == 3) { // Exit
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
