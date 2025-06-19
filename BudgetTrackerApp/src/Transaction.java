import java.sql.Date;

public class Transaction {

    private String xactID;
    private String expenseID;
    //private String belongsTo; // What if I don't need this?
    private String xactName;
    private String expenseName;
    //private String budgetName; // Might not need this either
    private double amount;
    private Date date;

    public String notes;

    public Transaction() {
        this.xactID = null;
        this.expenseID = null;
        //this.belongsTo = null;
        this.xactName = null;
        this.expenseName = null;
        //this.budgetName = null;
        this.amount = -1;
        this.date = null;
        this.notes = null;
    }


    // =====xactID Methods=====
    /**
     * Sets the xactID for this Transaction object.
     * The xactID is a unique identifier for the transaction.
     * @param xactID - The unique identifier for the transaction.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withXactID(String xactID) {
        this.xactID = xactID;
        return this;
    }
    /**
     * Gets the xactID for this Transaction object.
     * @return - The unique identifier for the transaction.
     */
    public String getXactID() {
        return this.xactID;
    }


    // =====expenseID Methods=====
    /**
     * Sets the expenseID for this Transaction object.
     * The expenseID is a unique identifier for the associated expense.
     * @param expenseID - The unique identifier for the expense.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withExpenseID(String expenseID) {
        this.expenseID = expenseID;
        return this;
    }
    /**
     * Gets the expenseID for this Transaction object.
     * @return - The unique identifier for the associated expense.
     */
    public String getExpenseID() {
        return this.expenseID;
    }


    /*
    // =====belongsTo Methods=====
    /**
     * Sets the belongsTo for this Transaction object.
     * The belongsTo field indicates which user the transaction is associated with.
     * @param belongsTo - The identifier for the budget or user.
     * @return - The current Transaction object for method chaining.
     *\/
    public Transaction withBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
        return this;
    }
    /**
     * Gets the belongsTo for this Transaction object.
     * @return - The username for the user associated with the transaction.
     *\/
    public String getBelongsTo() {
        return this.belongsTo;
    }
    */


    // =====xactName Methods=====
    /**
     * Sets the xactName for this Transaction object.
     * The xactName is the name of the transaction.
     * @param xactName - The name of the transaction.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withXactName(String xactName) {
        this.xactName = xactName;
        return this;
    }
    /**
     * Gets the xactName for this Transaction object.
     * @return - The name of the transaction.
     */
    public String getXactName() {
        return this.xactName;
    }


    // =====expenseName Methods=====
    /**
     * Sets the expenseName for this Transaction object.
     * The expenseName is the name of the expense associated with the transaction.
     * @param expenseName - The name of the expense.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withExpenseName(String expenseName) {
        this.expenseName = expenseName;
        return this;
    }
    /**
     * Gets the expenseName for this Transaction object.
     * @return - The name of the expense associated with the transaction.
     */
    public String getExpenseName() {
        return this.expenseName;
    }


    /*
    // =====budgetName Methods=====
    /**
     * Sets the budgetName for this Transaction object.
     * The budgetName is the name of the budget associated with the transaction.
     * @param budgetName - The name of the budget.
     * @return - The current Transaction object for method chaining.
     *\/
    public Transaction withBudgetName(String budgetName) {
        this.budgetName = budgetName;
        return this;
    }
    /**
     * Gets the budgetName for this Transaction object.
     * @return - The name of the budget associated with the transaction.
     *\/
    public String getBudgetName() {
        return this.budgetName;
    }
    */


    // =====amount Methods=====
    /**
     * Sets the amount for this Transaction object.
     * The amount is the monetary value of the transaction.
     * @param amount - The amount of the transaction.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withAmount(double amount) {
        this.amount = amount;
        return this;
    }
    /**
     * Gets the amount for this Transaction object.
     * @return - The monetary value of the transaction.
     */
    public double getAmount() {
        return this.amount;
    }


    // =====date Methods=====
    /**
     * Sets the date for this Transaction object.
     * The date indicates when the transaction occurred.
     * @param date - The date of the transaction.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withDate(Date date) {
        this.date = date;
        return this;
    }
    /**
     * Gets the date for this Transaction object.
     * @return - The date when the transaction occurred.
     */
    public Date getDate() {
        return this.date;
    }


    // =====notes Methods=====
    /**
     * Sets the notes for this Transaction object.
     * The notes field can contain additional information about the transaction.
     * @param notes - The notes for the transaction.
     * @return - The current Transaction object for method chaining.
     */
    public Transaction withNotes(String notes) {
        this.notes = notes;
        return this;
    }
    /**
     * Gets the notes for this Transaction object.
     * @return - Additional information about the transaction.
     */
    public String getNotes() {
        return this.notes;
    }

}
