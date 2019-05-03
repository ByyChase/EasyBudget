import java.io.IOException;
import java.util.*;

public class budget {

	// Declare Variables
	protected String sectionName; // String holding the name of the budget section
	protected double sectionAmount; // double holding the amount of money the section was originally alloted
	protected double amountLeft; // double holding the amount of money that has not been spent yet out of the
									// section
	protected LinkedList<Expense> expense = new LinkedList<Expense>(); // Linkned list holding expense objects
	protected String payCheckDate; // String holding the date of the paycheck

	// Construct Constructor
	public budget(String sectionName, double sectionAmount, String payCheckDate) {
		this.sectionName = sectionName;
		this.sectionAmount = sectionAmount;
		this.amountLeft = sectionAmount;
		this.payCheckDate = payCheckDate;

	}

	// Construct Full Constructor
	public budget(String sectionName, double sectionAmount, double amountLeft, String payCheckDate) {
		super();
		this.sectionName = sectionName;
		this.sectionAmount = sectionAmount;
		this.amountLeft = amountLeft;
		this.payCheckDate = payCheckDate;
	}

	// Setters & Getters

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public double getSectionAmount() {
		return sectionAmount;
	}

	public void setSectionAmount(double sectionAmount) {
		this.sectionAmount = sectionAmount;
	}

	public double getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(double amountLeft) {
		this.amountLeft = amountLeft;
	}

	public LinkedList<Expense> getExpense() {
		return expense;
	}

	public void setExpense(LinkedList<Expense> expense) {
		this.expense = expense;
	}
	
	public String getPayCheckDate() {
		return payCheckDate;
	}

	@Override
	public String toString() {
		return sectionName + "\nTotal amount alloted: $" + sectionAmount + "\nTotal amount left: $" + amountLeft
				+ "\n\n";
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

		return payCheckDate + " % " + sectionName + " % " + sectionAmount + " % " + amountLeft + "\n";

	}
}
