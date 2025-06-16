public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private double monthlyIncome;
    private String activeBudget;

    public User() {
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.monthlyIncome = -1;
        this.activeBudget = null;
    }

    // =====username Methods=====
    /**
     * Sets the username for this User object.
     * @param username - The username of the user.
     * @return - The current User object for method chaining.
     */
    public User withUsername(String username) {
        this.username = username;
        return this;
    }
    /**
     * Gets the username for this User object.
     * @return - The username of the user.
     */
    public String getUsername() {
        return this.username;
    }


    // =====password Methods=====
    /**
     * Sets the password for this User object.
     * @param password - The password of the user.
     * @return - The current User object for method chaining.
     */
    public User withPassword(String password) {
        this.password = password;
        return this;
    }
    /**
     * Gets the password for this User object.
     * @return - The password of the user.
     */
    public String getPassword() {
        return this.password;
    }


    // =====Name Methods=====
    /**
     * Sets the first and last name for this User object.
     * @param firstName - The first name of the user.
     * @param lastName - The last name of the user.
     * @return - The current User object for method chaining.
     */
    public User withName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }
    /**
     * Gets the first and last name for this User object.
     * @return - A String array where index 0 is the first name and index 1 is the last name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Gets the last name for this User object.
     * @return - The last name of the user.
     */
    public String getLastName() {
        return this.lastName;
    }


    // =====monthlyIncome Methods=====
    /**
     * Sets the monthly income for this User object.
     * @param monthlyIncome - The monthly income of the user.
     * @return - The current User object for method chaining.
     */
    public User withMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        return this;
    }
    /**
     * Gets the monthly income for this User object.
     * @return - The monthly income of the user.
     */
    public double getMonthlyIncome() {
        return this.monthlyIncome;
    }


    // =====activeBudget Methods=====
    /**
     * Sets the active budget for this User object.
     * @param activeBudget - The name of the active budget.
     * @return - The current User object for method chaining.
     */
    public User withActiveBudget(String activeBudget) {
        this.activeBudget = activeBudget;
        return this;
    }
    /**
     * Gets the active budget for this User object.
     * @return - The name of the active budget.
     *              Null if no active budget is set.
     */
    public String getActiveBudget() {
        return this.activeBudget;
    }
    

}
