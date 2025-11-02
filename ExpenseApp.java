import java.util.ArrayList;
import java.util.Scanner;

// This class represents a single expense entry
class Expense {
    String detail;   // Description or name of the expense
    double cost;     // Amount spent
    String date;     // Date of the expense

    // Constructor to initialize an expense
    Expense(String detail, double cost, String date) {
        this.detail = detail;
        this.cost = cost;
        this.date = date;
    }

    // Converts the Expense object into a readable string format
    @Override
    public String toString() {
        return date + " | " + detail + " : ₹" + String.format("%.2f", cost);
    }
}

// Main class that manages the Expense Tracker application
public class ExpenseApp {
    private ArrayList<Expense> expenseList;  // List to store all expenses
    private Scanner input;                   // Scanner for user input

    // Constructor to initialize the app
    public ExpenseApp() {
        expenseList = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // Method to record a new expense from user input
    public void recordExpense() {
        System.out.print("Enter expense detail (e.g., Food, Travel): ");
        String detail = input.nextLine();

        // Validate amount input
        double cost = 0;
        while (true) {
            System.out.print("Enter amount: ");
            try {
                cost = Double.parseDouble(input.nextLine());
                if (cost < 0) {
                    System.out.println("⚠️ Amount cannot be negative. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        System.out.print("Enter date (dd/mm/yyyy): ");
        String date = input.nextLine();

        // Create a new expense and add it to the list
        Expense exp = new Expense(detail, cost, date);
        expenseList.add(exp);

        System.out.println("✅ Expense added successfully!\n");
    }

    // Method to display all recorded expenses
    public void showExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("⚠️ No expenses recorded yet.");
            return;
        }

        System.out.println("\n---- Your Expenses ----");
        for (Expense exp : expenseList) {
            System.out.println(exp);
        }
    }

    // Method to calculate and display the total of all expenses
    public void showTotal() {
        if (expenseList.isEmpty()) {
            System.out.println("⚠️ No expenses available.");
            return;
        }

        double total = 0;
        for (Expense exp : expenseList) {
            total += exp.cost;
        }
        System.out.println("\n💰 Total Expenses = ₹" + String.format("%.2f", total));
    }

    // Method to display the highest (maximum) expense
    public void showMaxExpense() {
        if (expenseList.isEmpty()) {
            System.out.println("⚠️ No expenses available.");
            return;
        }

        Expense maxExp = expenseList.get(0);
        for (Expense exp : expenseList) {
            if (exp.cost > maxExp.cost) {
                maxExp = exp;
            }
        }

        System.out.println("\n🔺 Highest Expense: " + maxExp);
    }

    // Main menu loop for user interaction
    public void startApp() {
        while (true) {
            System.out.println("\n==== Expense Tracker Menu ====");
            System.out.println("1. Add Expense");
            System.out.println("2. Show All Expenses");
            System.out.println("3. Show Total Expenses");
            System.out.println("4. Show Biggest Expense");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number (1-5).");
                continue;
            }

            switch (choice) {
                case 1 -> recordExpense();
                case 2 -> showExpenses();
                case 3 -> showTotal();
                case 4 -> showMaxExpense();
                case 5 -> {
                    System.out.println("👋 Exiting... Thank you for using Expense Tracker!");
                    return;
                }
                default -> System.out.println("❌ Invalid option, please try again.");
            }
        }
    }

    // Entry point of the program
    public static void main(String[] args) {
        ExpenseApp app = new ExpenseApp();
        app.startApp();
    }
}
