import java.util.LinkedList;

public class findCorrect {

	// Construct Constructor
	public findCorrect() {

	}

	/*
	 * This method will be used to find the correct paycheck. Finding the correct
	 * paycheck is done by searching for the using the date entered by the user
	 * 
	 * @Param: Linkedlist paychecks; linked list holding paycheck objects
	 * 
	 * @Param: String paycheckDate; The date of the paycheck the user is looking for
	 * 
	 * @return: int; Integer holding the correct possition in the linked list for
	 * the right paycheck
	 */
	public int findPaycheck(LinkedList<PayCheck> paychecks, String paycheckDate) {

		for (int i = 0; i < paychecks.size(); i++) {
			if (paycheckDate.equals(paychecks.get(i).getPaycheckDate())) {
				return i;

			}

			if (i == paychecks.size() - 1) {
				System.out.println("\n Sorry, your paycheck was not found in the data base \n\n");
				return -1;
			}
		}

		// Return statement if the program gets outside of the loop
		return -1;

	}

	/*
	 * This method will be used to find the correct budget obect in the budget
	 * linked list stored inside of the paycheck object. This is done by going
	 * through the paycheck object and checking the budget for the correct budget
	 * section name and paycheck date
	 * 
	 * @Param: Linkedlist paychecks; linked list holding paycheck objects
	 * 
	 * @Param: String paycheckDate; The date of the paycheck the user is looking for
	 * 
	 * @Param: String budgetSectionName; The name of the
	 * 
	 * @return: int; Integer holding the correct position in the linked list for the
	 * budgetSection
	 */
	public int findBudgetSection(LinkedList<PayCheck> paychecks, String payCheckDate, String budgetSectionName) {

		// For loop finding the correct paycheck and budget section that the expense
		// corosponds to
		for (int i = 0; i < paychecks.size(); i++) {
			for (int k = 0; k < paychecks.get(i).getBudget().size(); k++) {

				// If the paycheck date and section name match that of the expense
				if (payCheckDate.equals(paychecks.get(i).getBudget().get(k).getPayCheckDate())
						&& budgetSectionName.equals(paychecks.get(i).getBudget().get(k).getSectionName())) {
					return k;

				}

			}
		}

		// Return statement for if the program gets outside of the loop
		return -1;

	}

}

//findCorrect findCorrectLocation = new findCorrect(); // Object used to find correct locations inside of linked
// lists

// call the findPaycheck method to get the correct paycheck date
// correctCheck = findCorrectLocation.findPaycheck(paychecks, checkDate);
