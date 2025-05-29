import java.util.*;


class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    
    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

  
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

   
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient balance. Current balance: " + balance + ", Attempted withdrawal: " + amount);
        }
        balance -= amount;
        System.out.println("Successfully withdrew " + amount + " from account " + accountNumber + ". New balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Successfully deposited " + amount + " into account " + accountNumber + ". New balance: " + balance);
    }

 
    public String toString() {
        return "Account Number: " + accountNumber + ", Holder: " + accountHolder + ", Balance: " + String.format("%.2f", balance);
    }
}

class Bank {
    private BankAccount[] accounts;
    private int numberOfAccounts;
    private final int MAX_ACCOUNTS = 5;

    public Bank() {
        accounts = new BankAccount[MAX_ACCOUNTS];
        numberOfAccounts = 0;
    }

  

    
    public boolean addAccount(BankAccount account) {
        if (numberOfAccounts < MAX_ACCOUNTS) {
            accounts[numberOfAccounts] = account;
            numberOfAccounts++;
            System.out.println("Account " + account.getAccountNumber() + " added successfully.");
            return true;
        } else {
            System.out.println("Bank is full. Cannot add new account for " + account.getAccountHolder());
            return false;
        }
    }

    
    private BankAccount findAccount(int accountNumber) {
        for (int i = 0; i < numberOfAccounts; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null; 
    }

    // o Withdraw money from an account given its account number and amount.
    public void withdrawMoney(int accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Error: Account " + accountNumber + " not found.");
            return;
        }

        try {
            account.withdraw(amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed for account " + accountNumber + ": " + e.getMessage());
        }
    }

 
    public void displayAllAccounts() {
        if (numberOfAccounts == 0) {
            System.out.println("\nNo accounts in the bank.");
            return;
        }
        System.out.println("\n--- All Bank Accounts ---");
        for (int i = 0; i < numberOfAccounts; i++) {
            System.out.println((i + 1) + ". " + accounts[i]);
        }
        System.out.println("-------------------------");
    }
}

 class BankAccountManager {
    public static void main(String[] args) {
        Bank myBank = new Bank();
       myBank.addAccount(new BankAccount(1001, "Alice", 5000.0));
       myBank.addAccount(new BankAccount(1002, "Bob", 3000.0));
        
       System.out.println("\nAttempting to withdraw 6000.0 from account 1001:");
       myBank.withdrawMoney(1001, 6000.0);

       System.out.println("\nAttempting to withdraw 1000.0 from account 1002:");
       myBank.withdrawMoney(1002, 1000.0);

         myBank.displayAllAccounts();
    }
}
