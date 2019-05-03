import java.io.IOException;

/*
 * This class is used to store information about an expense for a pay period 
 * 
 * Expense.java
 */
public class Expense {

	// Declare Variables
	protected double amount; // A double that holds the dollar amount of the expense
	protected String date; // A string that holds the date of the expense
	protected String description; // A string that holds the description of the expense
	protected String payCheckDate; // A string holding the date of the paycheck that this expense is attributed to
	protected String budgetSectionName; // A string holding the name of the budget section this expense is attributed to

	// Construct Constructor
	public Expense(double amount, String date, String description, String payCheckDate, String budgetSectionName) {
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.payCheckDate = payCheckDate;
		this.budgetSectionName = budgetSectionName;
	}

	// Setters & Getters

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * This method is used to return the needed data from the object so it can be
	 * written to a file
	 * 
	 * @param: N/A
	 * 
	 * @return: N/A
	 */
	public String printStringFile() throws IOException {

		return payCheckDate + " % " + budgetSectionName + " % " + amount + " % " + date + " % " + description + "\n";

	}

}
