
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

class User 
{
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String pin, double balance) 
    {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() 
    {
        return userId;
    }

    public String getPin() 
    {
        return pin;
    }

    public double getBalance() 
    {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() 
    {
        return transactionHistory;
    }

    public void addToTransactionHistory(String transaction) 
    {
        transactionHistory.add(transaction);
    }

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }
}

class ATM 
{
    private Map<String, User> users;
    private User currentUser;

    public ATM() 
    {
        users = new HashMap<>();
         users.put("vaibhav", new User("vaibhav", "1234", 7000.0));
        users.put("ashok", new User("ashok", "5678", 3000.0));
        users.put("ram", new User("ram", "1357", 5000.0));
    }

    public boolean authenticateUser(String userId, String pin) 
    {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) 
        {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void displayMenu() 
    {
        System.out.println("ATM Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }

    public void performTransaction(int choice) 
    {
        switch (choice) 
        {
            case 1:
                displayTransactionHistory();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transfer();
                break;
            case 5:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayTransactionHistory() 
    {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.getTransactionHistory()) 
        {
            System.out.println(transaction);
        }
    }

    private void withdraw() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= currentUser.getBalance()) 
        {
            currentUser.setBalance(currentUser.getBalance() - amount);
            String transaction = "Withdrawal of $" + amount;
            currentUser.addToTransactionHistory(transaction);
            System.out.println("Withdrawal successful. Remaining balance: " + currentUser.getBalance());
        } else 
        {
            System.out.println("Invalid amount or insufficient balance. Please try again.");
        }
    }

    private void deposit() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentUser.setBalance(currentUser.getBalance() + amount);
            String transaction = "Deposit of $" + amount;
            currentUser.addToTransactionHistory(transaction);
            System.out.println("Deposit successful. New balance: " + currentUser.getBalance());
        } else 
        {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private void transfer() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the recipient's User ID: ");
        String recipientId = scanner.next();

        User recipient = users.get(recipientId);

        if (recipient != null && !recipient.getUserId().equals(currentUser.getUserId())) 
        {
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();

            if (amount > 0 && amount <= currentUser.getBalance()) 
            {
                currentUser.setBalance(currentUser.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);

                String transaction = "Transfer of $" + amount + " to " + recipient.getUserId();
                currentUser.addToTransactionHistory(transaction);

                String recipientTransaction = "Received $" + amount + " from " + currentUser.getUserId();
                recipient.addToTransactionHistory(recipientTransaction);

                System.out.println("Transfer successful.");
                System.out.println("Your new balance: " + currentUser.getBalance());
            } else 
            {
                System.out.println("Invalid amount or insufficient balance. Please try again.");
            }
        } else 
        {
            System.out.println("Invalid recipient User ID. Please try again.");
        }
    }
}

class task3
{
    public static void main(String[] args) 
    {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (atm.authenticateUser(userId, pin)) 
        {
            while (true) 
            {
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                atm.performTransaction(choice);
            }
        } else 
        {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}
