import java.io.IOException;
import java.util.*;

public class PayCheck {

	// Declare Variables
	protected double payCheckAmount; // double holding the ammount of the paycheck
	protected LinkedList<budget> budget = new LinkedList<budget>(); // linked list holding budget objects
	protected String paycheckDate; // String holding the dat of the paycheck
	protected String description; // String holding the description of the paycheck
	protected boolean payCheckSplit; // Boolean showing if the paycheck has been split before
	protected double amountUnSplit; // double holding the amount of money that has not been budgeted

	// Construct Constructor from with fields
	public PayCheck(double payCheckAmount, String paycheckDate, String description) {
		super();
		this.payCheckAmount = payCheckAmount;
		this.paycheckDate = paycheckDate;
		this.description = description;
		payCheckSplit = false;
		amountUnSplit = payCheckAmount;
	}

	// Constructor with all fields
	public PayCheck(double payCheckAmount, String paycheckDate, String description, boolean payCheckSplit,
			double amountUnSplit) {
		super();
		this.payCheckAmount = payCheckAmount;
		this.paycheckDate = paycheckDate;
		this.description = description;
		this.payCheckSplit = payCheckSplit;
		this.amountUnSplit = amountUnSplit;
	}

	// Construct Constructor with no fields
	public PayCheck() {
		// This takes up a lot of space for what it is
	}

	// Setters & Getters

	public double getPayCheckAmount() {
		return payCheckAmount;
	}

	public void setPayCheckAmount(double payCheckAmount) {
		this.payCheckAmount = payCheckAmount;
	}

	public LinkedList<budget> getBudget() {
		return budget;
	}

	public void setBudget(LinkedList<budget> budget) {
		this.budget = budget;
	}

	public String getPaycheckDate() {
		return paycheckDate;
	}

	public void setPaycheckDate(String paycheckDate) {
		this.paycheckDate = paycheckDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPayCheckSplit() {
		return payCheckSplit;
	}

	public void setPayCheckSplit(boolean payCheckSplit) {
		this.payCheckSplit = payCheckSplit;
	}

	public double getAmountUnSplit() {
		return amountUnSplit;
	}

	public void setAmountUnSplit(double amountUnSplit) {
		this.amountUnSplit = amountUnSplit;
	}

	public void subtractSplitMoney(double amount) {
		amountUnSplit = amountUnSplit - amount;
	}

	@Override
	public String toString() {
		return "\n\nPay Check total: $" + payCheckAmount + "\nPay Check Date: " + paycheckDate
				+ "\nPay Check Description: " + description + "\n" + "Money UnBudgeted: $" + amountUnSplit + "\n\n";
	}

	public String printStringFile() throws IOException {
		return payCheckAmount + " % " + paycheckDate + " % " + description + " % " + payCheckSplit + " % "
				+ amountUnSplit + "\n";
	}

}
