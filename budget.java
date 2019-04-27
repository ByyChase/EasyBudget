import java.io.IOException;
import java.util.*;

public class budget {

	// Declare Variables
	protected String sectionName;
	protected double sectionAmount;
	protected double amountLeft;
	protected LinkedList<Expense> expense = new LinkedList<Expense>();
	protected String payCheckDate;

	// Construct Constructor
	public budget(String sectionName, double sectionAmount, String payCheckDate) {
		this.sectionName = sectionName;
		this.sectionAmount = sectionAmount;
		this.amountLeft = sectionAmount;
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
		
		return payCheckDate + " % " + sectionName + " % " +  sectionAmount + " % " + amountLeft + "\n";

	}
}
