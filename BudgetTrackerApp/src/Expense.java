import java.sql.Date;

public class Expense {

    private String expenseID;
    private String expsenseName;
    private String belongsTo;
    private int priorityLevel;
    private int commitment;
    private int spent;
    private boolean isStatic;
    private Date dateBegan;

    public Expense() {
        this.expenseID = null;
        this.expsenseName = null;
        this.belongsTo = null;
        this.priorityLevel = -1;
        this.commitment = -1;
        this.spent = -1;
        this.isStatic = false;
        this.dateBegan = null;
    }

    // =====expenseID Methods=====
    /**
     * Sets the expenseID for this Expense object.
     * @param expenseID - The unique identifier for the expense.
     * @return - The current Expense object for method chaining.
     */
    public Expense withExpenseID(String expenseID) {
        this.expenseID = expenseID;
        return this;
    }
    /**
     * Gets the expenseID for this Expense object.
     * @return - The unique identifier for the expense.
     */
    public String getExpenseID() {
        return this.expenseID;
    }


    //=====expenseName Methods=====
    /**
     * Sets the name of the expense.
     * @param expsenseName - The name of the expense.
     * @return - The current Expense object for method chaining.
     */
    public Expense withExpsenseName(String expsenseName) {
        this.expsenseName = expsenseName;
        return this;
    }
    /**
     * Gets the name of the expense.
     * @return - The name of the expense.
     */
    public String getExpsenseName() {
        return this.expsenseName;
    }


    //=====belongsTo Methods=====
    /**
     * Sets the username to the account which this expense belongs.
     * @param belongsTo - The username of the account.
     * @return - The current Expense object for method chaining.
     */
    public Expense withBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
        return this;
    }
    /**
     * Gets the username of the account to which this expense belongs.
     * @return - The budget or category name.
     */
    public String getBelongsTo() {
        return this.belongsTo;
    }


    //=====priorityLevel Methods=====
    /**
     * Sets the priority level of the expense.
     * Priority levels are typically used to indicate the importance or urgency of the expense.
     * @param priorityLevel - The priority level of the expense, where a lower number indicates higher priority
     *                       (e.g., 1 for highest priority, 2 for medium priority, etc.).
     * @return - The current Expense object for method chaining.
     */
    public Expense withPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
        return this;
    }
    /**
     * Gets the priority level of the expense.
     * @return - The priority level of the expense, where a lower number indicates higher priority.
     */
    public int getPriorityLevel() {
        return this.priorityLevel;
    }


    //=====commitment Methods=====
    /**
     * Sets the commitment amount for the expense.
     * The commitment amount typically represents the budgeted or planned amount for the expense.
     * @param commitment - The commitment amount for the expense, must be a non-negative integer.
     * @return - The current Expense object for method chaining.
     * @throws IllegalArgumentException If the commitment is negative.
     */
    public Expense withCommitment(int commitment) {
        if (commitment < 0) {
            throw new IllegalArgumentException("Commitment must be a non-negative integer.");
        }
        this.commitment = commitment;
        return this;
    }
    /**
     * Gets the commitment amount for the expense.
     * @return - The commitment amount for the expense, or -1 if not set.
     */
    public int getCommitment() {
        return this.commitment;
    }
    /**
     * Alters the commitment amount for the expense by a specified amount.
     * This method allows you to increase or decrease the commitment amount.
     * @param amount - The amount to alter the commitment by, can be positive or negative.
     * @return - The new commitment amount after alteration.
     * @throws IllegalArgumentException If the resulting commitment would be negative.
     */
    public int alterCommitment(int amount) {
        if (this.commitment + amount < 0) {
            throw new IllegalArgumentException("Commitment cannot be negative after alteration.");
        }
        this.commitment += amount;
        return this.commitment;
    }


    //=====spent Methods=====
    /**
     * Sets the amount spent for the expense.
     * @param spent - The amount spent for the expense, must be a non-negative integer.
     * @return - The current Expense object for method chaining.
     * @throws IllegalArgumentException If the spent amount is negative.
     */
    public Expense withSpent(int spent) {
        if (spent < 0) {
            throw new IllegalArgumentException("Spent amount must be a non-negative integer.");
        }
        this.spent = spent;
        return this;
    }
    /**
     * Gets the amount spent for the expense.
     * @return - The amount spent for the expense, or -1 if not set.
     */
    public int getSpent() {
        return this.spent;
    }
    /**
     * Alters the amount spent for the expense by a specified amount.
     * This method allows you to increase or decrease the spent amount.
     * @param amount - The amount to alter the spent by, can be positive or negative.
     * @return - The new spent amount after alteration.
     * @throws IllegalArgumentException If the resulting spent amount would be negative.
     */
    public int alterSpent(int amount) {
        if (this.spent + amount < 0) {
            throw new IllegalArgumentException("Spent amount cannot be negative after alteration.");
        }
        this.spent += amount;
        return this.spent;
    }


    //=====isStatic Methods=====
    /**
     * Sets whether the expense is static.
     * A static expense is typically one that does not change over time, such as a fixed monthly bill.
     * @param isStatic - True if the expense is static, false otherwise.
     * @return - The current Expense object for method chaining.
     */
    public Expense withIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }
    /**
     * Gets whether the expense is static.
     * A static expense is typically one that does not change over time, such as a fixed monthly bill.
     * @return - True if the expense is static, false otherwise.
     */
    public boolean getIsStatic() {
        return this.isStatic;
    }


    //=====dateBegan Methods=====
    /**
     * Sets the date when the expense began.
     * This is typically the date when the expense was first incurred or started.
     * @param dateBegan - The date when the expense began, represented as an java.sql.Date object.
     * @return - The current Expense object for method chaining.
     */
    public Expense withDateBegan(Date dateBegan) {
        this.dateBegan = dateBegan;
        return this;
    }
    /**
     * Gets the date when the expense began.
     * This is typically the date when the expense was first incurred or started.
     * @return - The date when the expense began, represented as an java.sql.Date object.
     *          If not set, returns null.
     */
    public Date getDateBegan() {
        return this.dateBegan;
    }

}
