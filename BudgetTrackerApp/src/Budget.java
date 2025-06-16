import java.util.HashMap;

public class Budget {

    private String budgetName;
    private String username;
    private HashMap<String, Expense> expenses;
    
    public Budget() {
        this.budgetName = null;
        this.username = null;
        this.expenses = new HashMap<>();
    }

    // =====budgetName Methods=====
    /**
     * Sets the budget name for this Budget object.
     * @param budgetName - The name of the budget.
     * @return - The current Budget object for method chaining.
     */
    public Budget withBudgetName(String budgetName) {
        this.budgetName = budgetName;
        return this;
    }
    /**
     * Gets the budget name for this Budget object.
     * @return - The name of the budget.
     */
    public String getBudgetName() {
        return this.budgetName;
    }

    
    // =====expenses Methods=====
    /**
     * Adds an expense to the budget.
     * @param expense - The Expense object to be added.
     * @return - The current Budget object for method chaining.
     */
    public Budget withUsername(String username) {
        this.username = username;
        return this;
    }
    /**
     * Gets the expenses associated with this budget.
     * @return - A HashMap of expenses where the key is the expense ID.
     */
    public String getUsername() {
        return this.username;
    }


    // =====expenses Methods=====
    /**
     * Adds an expense to the budget.
     * @param expense - The Expense object to be added.
     * @return - The current Budget object for method chaining.
     */
    public Budget withExpense(Expense expense) {
        this.expenses.put(expense.getExpenseID(), expense);
        return this;
    }
    /**
     * Gets the expenses associated with this budget.
     * @return - An array of Expense objects.
     */
    public Expense[] getExpenses() {
        return this.expenses.values().toArray(new Expense[0]);
    }
    /**
     * Retrieves an expense by its ID.
     * @param expenseID - The unique identifier for the expense.
     * @return - The Expense object associated with the given ID, or null if not found.
     */
    public Expense getExpense(String expenseID) {
        return this.expenses.get(expenseID);
    }

}
