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
	 * This class is used to read in paychecks to the paycheck linked list
	 * 
	 * @param: LinkedList<PayCheck> paycheck; an empty paycheck linked list that
	 * will be filled with the data being read in
	 * 
	 * @return: N/A
	 */
	public void readPaycheck(LinkedList<PayCheck> paycheck) throws IOException {
		
		//Declare Variables 
		Scanner sc = new Scanner(payChecks);
		
		while(sc.hasNextLine()) {
			
			//Reading in a new line from the paycheck file
			String temp = sc.nextLine();
			//Splitting the line into an array 
			String[] tempArray = temp.split(" % ");
			
			//Splitting the array into its appropriate temp variables 
			double payCheckAmount = Double.parseDouble(tempArray[0]);
			String paycheckDate = tempArray[1];
			String description = tempArray[2];
			boolean payCheckSplit = Boolean.parseBoolean(tempArray[3]);
			double amountUnSplit = Double.parseDouble(tempArray[4]);
				
			//putting the temp variables into a temp paycheck object 
			PayCheck tempCheck = new PayCheck(payCheckAmount, paycheckDate, description, payCheckSplit, amountUnSplit);
			paycheck.add(tempCheck);
			
			
		}
		
		
	}

}
