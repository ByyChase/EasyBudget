import java.io.*;
import java.util.*;

public class fileHandling {

	// Declare Variables
	File payChecks = new File("C:\\Users\\Chase\\Documents\\paychecks.txt"); // A text file that holds data for
																				// paychecks
	File budget = new File("C:\\Users\\Chase\\Documents\\budget.txt"); // A text file that holds data for budgets
	File expense = new File("C:\\Users\\Chase\\Documents\\expense.txt"); // A text file that holds data for expenses

	// Construct constructor
	public fileHandling() {

	}

	/*
	 * A method used to test that the text files needed for the program have been
	 * made or are already present
	 * 
	 * @param: N/A
	 * 
	 * @return: N/A
	 */
	public void createNewFile() {

		// Trying to create files for data storage
		try {

			// Declare Variables
			boolean expenseCheck = expense.createNewFile(); // A boolean that checks to see if the expense file is
															// created
			boolean budgetCheck = budget.createNewFile(); // A boolean that checks to see if the budget file is created
			boolean payChecksCheck = payChecks.createNewFile(); // A boolean that checks to see if the paychecks file is
																// created

			// Checking to see if the files were created, were already there, or if
			// something failed
			if (expenseCheck && budgetCheck && payChecksCheck) {
				System.out.println("File being created\n\n");
			}

			else {
				// Telling the user the files have been made before and that they are present
				// for the program
				System.out.println("File loading\n\n");
			}

		}

		// If the files can not be created or loaded, an error gets thrown and the
		// program exits
		catch (IOException e) {

			// Write the program is ending in the console
			System.out.println("\n\nProblem Found \n\nExiting Program");
			System.exit(0);

		}

	}

	/*
	 * This method is used to write the paychecks to a text file to be saved
	 * 
	 * @param: LinkedList<PayCheck> paycheck; A linked list holding the paychecks
	 * that the user has entered
	 * 
	 * @return: N/A
	 */
	public void WritePayChecks(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		FileWriter fw = new FileWriter(payChecks); // A file writer object used to write to the payChecks file

		// Clearing the text file so it can be written cleanly when the program is ended
		fw.flush();

		// For loop used to iterate through the paychecks
		for (int i = 0; i < paycheck.size(); i++) {

			// Writing the paycheck data to the file
			fw.write(paycheck.get(i).printStringFile());

		}

		// Closing the file
		fw.close();

	}

	/*
	 * This method is used to write the budgets to a text file to be saved
	 * 
	 * @param: LinkedList<PayCheck> paycheck; A linked list holding the paychecks
	 * that the user has entered
	 * 
	 * @return: N/A
	 */
	public void WriteBudget(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		FileWriter fw = new FileWriter(budget); // A file writer object used to write to the payChecks file

		// Clearing the text file so it can be written cleanly when the program is ended
		fw.flush();

		// For loop used to iterate through the paychecks
		for (int i = 0; i < paycheck.size(); i++) {

			// For loop used to iterate through the budgets
			for (int k = 0; k < paycheck.get(i).getBudget().size(); k++) {

				// Writing the budgets to the file
				fw.write(paycheck.get(i).getBudget().get(k).printStringFile());

			}
		}

		// Closing the file
		fw.close();

	}

	/*
	 * This method is used to write the expenses to a text file to be saved
	 * 
	 * @param: LinkedList<PayCheck> paycheck; A linked list holding the paychecks
	 * that the user has entered
	 * 
	 * @return: N/A
	 */
	public void WriteExpenses(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		FileWriter fw = new FileWriter(expense); // A file writer object used to write to the payChecks file

		// Clearing the text file so it can be written cleanly when the program is ended
		fw.flush();

		// For loop used to iterate through the paychecks
		for (int i = 0; i < paycheck.size(); i++) {

			// For loop used to iterate through the budgets
			for (int k = 0; k < paycheck.get(i).getBudget().size(); k++) {

				// For loop used to iterate through the budgets
				for (int z = 0; z < paycheck.get(i).getBudget().get(k).getExpense().size(); z++) {

					// Writing the expenses to the file
					fw.write(paycheck.get(i).getBudget().get(k).getExpense().get(z).printStringFile());
				}

			}
		}

		// Closing the file
		fw.close();

	}

	/*
	 * This method is used to read in paychecks to the paycheck linked list
	 * 
	 * @param: LinkedList<PayCheck> paycheck; an empty paycheck linked list that
	 * will be filled with the data being read in
	 * 
	 * @return: N/A
	 */
	public void readPaycheck(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		Scanner sc = new Scanner(payChecks); // Scanner used to read in from the payChecks text file
		String temp; // A string used to hold the data read in from the file temporarily
		String[] tempArray; // A String array used to temporarily hold data from the text file
		double payCheckAmount; // A double holding the amount of the paycheck
		String paycheckDate; // A string holding the date of the paycheck
		String description; // A string holding a description of the paycheck
		boolean payCheckSplit; // A boolean stating if the paycheck has been split or not
		double amountUnSplit; // A double

		// A while loop that runs while the text file still has data in it
		while (sc.hasNextLine()) {

			// Reading in a new line from the paycheck file
			temp = sc.nextLine();
			// Splitting the line into an array
			tempArray = temp.split(" % ");

			// Splitting the array into its appropriate temp variables
			payCheckAmount = Double.parseDouble(tempArray[0]);
			paycheckDate = tempArray[1];
			description = tempArray[2];
			payCheckSplit = Boolean.parseBoolean(tempArray[3]);
			amountUnSplit = Double.parseDouble(tempArray[4]);

			// putting the temp variables into a temp paycheck object
			try {
				PayCheck tempCheck = new PayCheck(payCheckAmount, paycheckDate, description, payCheckSplit,
						amountUnSplit);
				paycheck.add(tempCheck);
			}

			catch (Error e) {
				System.out.println("\n\n ERROR \n paychecks.txt failed to read \n\n");
			}

			// Adding the paycheck to the paycheck Linked List

		}

	}

	/*
	 * This method is used to read in budgets to the budget linked list. The method
	 * is only used when opening the program and loading the users data into
	 * 
	 * @param: LinkedList<PayCheck> paycheck; A filled linked list holding pay check
	 * data that the budgets will be added to
	 * 
	 * @return: N/A
	 */
	public void readBudget(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		Scanner sc = new Scanner(budget);
		int correctCheck = 0;

		// While loop to run scanner until the file is empty
		while (sc.hasNext()) {

			// Reading in a new line from the budget file
			String temp = sc.nextLine();
			// Splitting the line into an array
			String[] tempArray = temp.split(" % ");

			// Splitting the array into its appropriate temp variables
			String payCheckDate = tempArray[0];
			String sectionName = tempArray[1];
			double sectionAmount = Double.parseDouble(tempArray[2]);
			double ammountLeft = Double.parseDouble(tempArray[3]);

			for (int i = 0; i < paycheck.size(); i++) {
				if (payCheckDate.equals(paycheck.get(i).getPaycheckDate())) {
					correctCheck = i;
					break;
				}

				if (i == paycheck.size() - 1) {
					System.out.println("\n ERROR \n budget.txt failed to read \n\n");
					return;
				}
			}

			// Creating a temp budget object
			budget tempBudget = new budget(sectionName, sectionAmount, ammountLeft, payCheckDate);

			// Adding the temp budget object to the budget linked list inside of the
			// paycheck object
			paycheck.get(correctCheck).budget.add(tempBudget);

		}

	}

	/*
	 * This class is used to read in expenses to the expense linked list
	 * 
	 * @param: LinkedList<PayCheck> paycheck; A filled linked list holding pay check
	 * data that the budgets will be added to
	 * 
	 * @return: N/A
	 */
	public void readExpenses(LinkedList<PayCheck> paycheck) throws IOException {

		// Declare Variables
		Scanner sc = new Scanner(expense); // Scanner used to read in data from the file
		int correctCheck = 0; // Integer used to store the correct check from the linked list that the espense
								// corosponds to
		int correctBudget = 0; // Integer used to store the correct budget from the linked list inside of the
								// paycheck object that the correctCheck integer corosponds to
		double amount; // Double holding the amount of the expense read in from the file
		String date; // String holding the date of the expense from the file
		String description; // String holding a description of the expense from the file
		String payCheckDate; // String holding the date of the paycheck that the expense corosponds to
		String budgetSectionName; // String holding the name of the budget section that the expense corosponds to

		// While loop to run scanner until the file is empty
		while (sc.hasNext()) {

			// Reading in a new line from the Expense file
			String temp = sc.nextLine();
			// Splitting the line into an array
			String[] tempArray = temp.split(" % ");

			// Splitting Array into temp variabes
			date = tempArray[0];
			description = tempArray[1];
			amount = Double.parseDouble(tempArray[2]);
			payCheckDate = tempArray[3];
			budgetSectionName = tempArray[4];

			// For loop finding the correct paycheck and budget section that the expense
			// corosponds to
			for (int i = 0; i < paycheck.size(); i++) {
				for (int k = 0; k < paycheck.get(i).getBudget().size(); k++) {

					// If the paycheck date and section name match that of the expense
					if (payCheckDate.equals(paycheck.get(i).getPaycheckDate())
							&& budgetSectionName.equals(paycheck.get(i).getBudget().get(k).getSectionName())) {
						correctCheck = i;
						correctBudget = k;
						break;
					}

					// if (k == paycheck.get(i).getBudget().size() - 1) {
					// System.out.println("\n ERROR \n expense.txt failed to read \n\n");
					// return;
					// }

				}
			}

			Expense tempExpense = new Expense(amount, date, description, payCheckDate, budgetSectionName); // A temp
																											// expense
																											// object
																											// used to
																											// add the
																											// data back
																											// into the
																											// linked
																											// list
																											// isnide of
																											// the
																											// budget
																											// class

			// Adding the temp expense object to the linked list
			paycheck.get(correctCheck).getBudget().get(correctBudget).expense.add(tempExpense);
		}

	}

}
