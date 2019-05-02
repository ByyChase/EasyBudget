import java.util.Scanner;
import java.io.IOException;
import java.util.*;
import java.io.IOException;

public class UserMenu {

	public static void main(String[] args) throws IOException {

		// Declare Variables
		int userChoice; // An interger holding the choice of the user from the menu in the console
		Scanner sc = new Scanner(System.in); // Scanner used to read in the user decisions
		LinkedList<PayCheck> paychecks = new LinkedList<PayCheck>(); // A linked list holding paycheck objects
		boolean cont = true; // Boolean determining if the user wants to do something else inside of the menu
		fileHandling fileHandler = new fileHandling(); // Object for the file handling class to open and read to/from
														// the files

		// Create new files if needed, and check to make sure the old ones are there
		fileHandler.createNewFile();
		fileHandler.readPaycheck(paychecks);
		fileHandler.readBudget(paychecks);
		fileHandler.readExpenses(paychecks);

		// While loop to have the user do multiple entries
		while (cont) {

			// Display the menu to the user to choose
			System.out.print(
					"Please choose one option: \n\n 1) Create new Pay Check \n 2) Split your paycheck up \n 3) Add new expense \n 4) View a Paycheck \n 5) Quit the Program \n\n\tYour Choice: ");
			userChoice = sc.nextInt();

			// Switch Statement for user choice
			switch (userChoice) {

			case 1:

				addNewPayCheck(paychecks);

				break;

			case 2:
				splitPayCheck(paychecks);

				break;

			case 3:

				addNewExpense(paychecks);

				break;

			case 4:

				viewPayCheck(paychecks);

				break;

			case 5:

				System.out.println("\n\nSaving Data\n\n");
				writeDataToFile(paychecks);
				System.out.println("\n\nData Saved Succesfully\n\n");
				System.exit(0);
				break;

			default:
				// Place Holder;
				break;

			}
		}

	}

	public static void addNewPayCheck(LinkedList<PayCheck> paychecks) {

		// Declare Variables
		Scanner sc = new Scanner(System.in); // Scanner used to read in the users data
		double payCheckAmount; // double holding the paycheck amount in dollars entered by the user
		String payCheckDate; // String holding the date of the paycheck entered by the user
		String description; // A description of the paycheck entered by the user

		// Ask the user to enter the information
		System.out.print("\n\nPlease enter the pay check ammount: $");
		payCheckAmount = sc.nextDouble();
		sc.nextLine();

		System.out.print("\nPlease enter the date of the pay check in the MM/DD/YYYY format: ");
		payCheckDate = sc.nextLine();

		System.out.print("\nPlease enter a short description of the pay check: ");
		description = sc.nextLine();

		PayCheck tempVariable = new PayCheck(payCheckAmount, payCheckDate, description); // a temp paycheck object to
																							// add to the linked list

		// Adding the paycheck object to the linked list
		paychecks.add(tempVariable);

		System.out.println("\n\n");

	}

	public static void splitPayCheck(LinkedList<PayCheck> paychecks) {

		String checkDate; // The date of the check the user wants to add the expense for
		Scanner sc = new Scanner(System.in); // The scanner used to interact with the user
		int correctCheck = 0; // The index of the check the user wants to add an expense for

		// Ask the user for the date of the paycheck they want to add the expense for
		System.out.print(
				"\n\nPlease enter the date of the paycheck you want to add the expense for in the MM/DD/YYYY format: ");
		checkDate = sc.nextLine();

		// Check the size of the LL paychecks
		int sizePayChecks = paychecks.size();

		// ForLoop to find the index that has the correct paycheck in it
		for (int i = 0; i < sizePayChecks; i++) {
			if (checkDate.equals(paychecks.get(i).getPaycheckDate())) {
				correctCheck = i;
				break;
			}

			if (i == sizePayChecks - 1) {
				System.out.println("\n Sorry, your paycheck was not found in the data base \n\n");
				return;
			}
		}

		// If the paycheck has not been split start the user off fresh
		if (!paychecks.get(correctCheck).isPayCheckSplit()) {

			paychecks.get(correctCheck).setPayCheckSplit(true);

			// Declare Variables
			String sectionName; // The name of the section of split up money
			double sectionAmount; // The amount of money initially alloted towards the section

			// Asking the user to enter the section name
			System.out.print(
					"\nPlease enter the name of the section (Example: 'Clothes', 'Car Payment', or 'Free Spend'): ");
			sectionName = sc.nextLine();

			// Asking the user to enter the section amount
			System.out.print("\nPlease enter the in amount of money you would like to set for this: $");
			sectionAmount = sc.nextDouble();

			// Checking to make sure the user has enough money to allot to that section
			while (paychecks.get(correctCheck).getAmountUnSplit() < sectionAmount) {
				System.out.print("\n\nSorry, you only have $" + paychecks.get(correctCheck).getAmountUnSplit()
						+ " left to split up.\n\nPlease enter an amount smaller than that $: ");
				sectionAmount = sc.nextDouble();
			}

			// Add the new section to the paycheck
			budget temp = new budget(sectionName, sectionAmount, paychecks.get(correctCheck).getPaycheckDate());
			paychecks.get(correctCheck).budget.add(temp);
			paychecks.get(correctCheck).subtractSplitMoney(sectionAmount);

			// printing the new section added
			System.out.print("\n\nHere is how you have the check split so far:\n");

			// Loop printing a toString method from the budget object class
			for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
				System.out.println(paychecks.get(correctCheck).budget.get(i).toString());
			}

			System.out.println("\nTotal unalloted: $" + paychecks.get(correctCheck).getAmountUnSplit());
			sc.nextLine();
			// Asking the user if they would like to split the check anymore
			// Asking the user if they would like to add anymore sections
			System.out.print("\n\nWould you like to add anymore? Please type '(Y)es' or '(N)o': ");
			String addMoreSections = sc.nextLine(); // A string holding a Yes/No value on if the user wants to more
			// sections

			while (addMoreSections.equalsIgnoreCase("Y") || addMoreSections.equalsIgnoreCase("Yes")) {

				// Asking the user to enter the section name
				System.out.print(
						"\n\nPlease enter the name of the section (Example: 'Clothes', 'Car Payment', or 'Free Spend'): ");
				sectionName = sc.nextLine();

				// Asking the user to enter the section amount
				System.out.print("\nPlease enter the in amount of money you would like to set for this: $");
				sectionAmount = sc.nextDouble();

				// Checking to make sure the user has enough money to allot to that section
				while (paychecks.get(correctCheck).getAmountUnSplit() < sectionAmount) {
					System.out.print("\n\nSorry, you only have $" + paychecks.get(correctCheck).getAmountUnSplit()
							+ " left to split up.\n\nPlease enter an amount smaller than that $: ");
					sectionAmount = sc.nextDouble();
				}

				// Add the new section to the paycheck
				temp = new budget(sectionName, sectionAmount, paychecks.get(correctCheck).getPaycheckDate());
				paychecks.get(correctCheck).budget.add(temp);
				paychecks.get(correctCheck).subtractSplitMoney(sectionAmount);

				// printing the new section added
				System.out.print("\n\n Here is how you have the check split so far:\n");

				// Loop printing a toString method from the budget object class
				for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
					System.out.println(paychecks.get(correctCheck).budget.get(i).toString());
				}

				System.out.println("\nTotal unalloted: $" + paychecks.get(correctCheck).getAmountUnSplit());
				sc.nextLine();

				// Asking the user if they would like to split the check anymore
				// Asking the user if they would like to add anymore sections
				System.out.print("Would you like to add anymore? Please type '(Y)es' or '(N)o': ");
				addMoreSections = sc.nextLine(); // A string holding a Yes/No value on if the user wants to more
													// sections

			}

		}

		// If the paycheck has already been split
		else {

			// Show the user what they have already split their check into
			System.out.print("\nThe paycheck has already been split. Here is how you have it split so far:\n\n");

			// Loop printing a toString method from the budget object class
			for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
				System.out.println(paychecks.get(correctCheck).budget.get(i).toString());
			}

			System.out.println("\nTotal unalloted: $" + paychecks.get(correctCheck).getAmountUnSplit());

			// Asking the user if they would like to add anymore sections
			System.out.print("Would you like to add anymore? Please type '(Y)es' or '(N)o': ");
			String addMoreSections = sc.nextLine(); // A string holding a Yes/No value on if the user wants to more
													// sections

			// If the user said yes, add more sections
			if (addMoreSections.equalsIgnoreCase("Y") || addMoreSections.equalsIgnoreCase("Yes")) {

				while (addMoreSections.equalsIgnoreCase("Y") || addMoreSections.equalsIgnoreCase("Yes")) {

					// Declare Variables
					String sectionName; // The name of the section of split up money
					double sectionAmount; // The amount of money initially alloted towards the section

					// Asking the user to enter the section name
					System.out.print(
							"Please enter the name of the section (Example: 'Clothes', 'Car Payment', or 'Free Spend': ");
					sectionName = sc.nextLine();

					// Asking the user to enter the section amount
					System.out.print("Please enter the in amount of money you would like to set for this: $");
					sectionAmount = sc.nextDouble();

					// Checking to make sure the user has enough money to allot to that section
					while (paychecks.get(correctCheck).getAmountUnSplit() < sectionAmount) {
						System.out.print("\n\nSorry, you only have $" + paychecks.get(correctCheck).getAmountUnSplit()
								+ " left to split up.\n\nPlease enter an amount smaller than that $: ");
						sectionAmount = sc.nextDouble();
					}

					// Add the new section to the paycheck
					budget temp = new budget(sectionName, sectionAmount, paychecks.get(correctCheck).getPaycheckDate());
					paychecks.get(correctCheck).budget.add(temp);
					paychecks.get(correctCheck).subtractSplitMoney(sectionAmount);

					// printing the new section added
					System.out.print("\n\nHere is how you have the check split so far:\n");

					// Loop printing a toString method from the budget object class
					for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
						System.out.println(paychecks.get(correctCheck).budget.get(i).toString());
					}

					System.out.println("\nTotal unalloted: $" + paychecks.get(correctCheck).getAmountUnSplit());
					sc.nextLine();

					// Asking the user if they would like to split the check anymore
					// Asking the user if they would like to add anymore sections
					System.out.print("Would you like to add anymore? Please type '(Y)es' or '(N)o': ");
					addMoreSections = sc.nextLine(); // A string holding a Yes/No value on if the user wants to more
														// sections

				}

			}

			// If the user does not want to add anything more, throw them to the main menu
			else {
				return;
			}

		}

	}

	public static void addNewExpense(LinkedList<PayCheck> paychecks) {

		// Declare Variables
		double amount; // The total of the paycheck the user enters
		String date; // The date of the expense the user enters
		String description; // A description of the expense the user enters
		String checkDate; // The date of the check the user wants to add the expense for
		Scanner sc = new Scanner(System.in); // The scanner used to interact with the user
		int correctCheck = 0; // The index of the check the user wants to add an expense for
		String addMoreSections = "Yes"; // Variable used to check if the user wants to add more sections
		int sectionNumber = 0; // Variable that corresponds with what section the user wants to add an expense
								// to
		int sizePayChecks = paychecks.size(); // Variable that stores the size of the paycheck linked list
		Expense temp; // Variable that holds a temp expense object to add to the linked list inside of
						// the budget object

		while (addMoreSections.equalsIgnoreCase("Y") || addMoreSections.equalsIgnoreCase("Yes")) {

			// Ask the user for the date of the paycheck they want to add the expense for
			System.out.print(
					"\n\nPlease enter the date of the paycheck you want to add the expense for in the MM/DD/YYYY format: ");
			checkDate = sc.nextLine();

			// ForLoop to find the index that has the correct paycheck in it
			for (int i = 0; i < sizePayChecks; i++) {
				if (checkDate.equals(paychecks.get(i).getPaycheckDate())) {
					correctCheck = i;
					break;
				}

				if (i == sizePayChecks - 1) {
					System.out.println("\n Sorry, your paycheck was not found in the data base \n\n");
					return;
				}
			}

			// Ask the use what section they would like to take the money out of
			System.out.println("\nWhich section would you like to take the money out of?\n");

			// Loop printing a toString method from the budget object class
			for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
				int displayNumber = i + 1;

				System.out.println(displayNumber + ") " + paychecks.get(correctCheck).budget.get(i).toString());
			}

			// ASking the user to enter in the number that corresponds with the section
			System.out.print("\nPlease enter the number that corosponds with the section: ");
			sectionNumber = sc.nextInt();

			// Checking to make sure the user entered a valid section number
			while (sectionNumber > sizePayChecks) {
				System.out.println("\n\nPlease enter a number smaller than " + sizePayChecks);

				System.out.println("\nHere are your options to pick from: ");

				// Ask the use what section they would like to take the money out of
				System.out.print("Which section would you like to take the money out of?");

				// Loop printing a toString method from the budget object class
				for (int i = 0; i < paychecks.get(correctCheck).budget.size(); i++) {
					System.out.println(i + ") " + paychecks.get(correctCheck).budget.get(i).toString());
				}

			}

			// Ask the user to input information of the expense
			System.out.print("\nPlease input the ammount of the expense: $");
			amount = sc.nextDouble();
			sc.nextLine();

			System.out.print("\nPlease input the date of the expense in the format MM/DD/YYYY: ");
			date = sc.nextLine();

			System.out.print("\nPlease input a short description of the expense: ");
			description = sc.nextLine();

			temp = new Expense(amount, date, description, paychecks.get(correctCheck).getPaycheckDate(),
					paychecks.get(correctCheck).budget.get(sectionNumber).getSectionName());

			paychecks.get(correctCheck).budget.get(sectionNumber).expense.add(temp);

			System.out.println("\n\n");

			System.out.print("Would you like to add anymore? Please type '(Y)es' or '(N)o': ");
			addMoreSections = sc.nextLine(); // A string holding a Yes/No value on if the user wants to more
												// sections
		}

	}

	public static void viewPayCheck(LinkedList<PayCheck> paychecks) {

		// Declare Variables
		String checkDate; // The date of the check the user wants to add the expense for
		Scanner sc = new Scanner(System.in); // The scanner used to interact with the user
		int correctCheck = 0; // The index of the check the user wants to add an expense for

		// Ask the user for the date of the paycheck they want to add the expense for
		System.out.print(
				"\n\nPlease enter the date of the paycheck you want to add the expense for in the MM/DD/YYYY format: ");
		checkDate = sc.nextLine();

		// Check the size of the LL paychecks
		int sizePayChecks = paychecks.size();

		// ForLoop to find the index that has the correct paycheck in it
		for (int i = 0; i < sizePayChecks; i++) {
			if (checkDate.equals(paychecks.get(i).getPaycheckDate())) {
				correctCheck = i;
				break;
			}

			if (i == sizePayChecks - 1) {
				System.out.println("\n Sorry, your paycheck was not found in the data base \n\n");
				return;
			}
		}

		System.out.println(paychecks.get(correctCheck).toString());

	}

	public static void writeDataToFile(LinkedList<PayCheck> paychecks) throws IOException {

		// Decalre Variables
		fileHandling fileHandler = new fileHandling(); // Object for the file handling class to open and read to/from
														// the files

		// Calling methods to write data out of the program
		fileHandler.WritePayChecks(paychecks);
		fileHandler.WriteExpenses(paychecks);
		fileHandler.WriteBudget(paychecks);

	}

}
