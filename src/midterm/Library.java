/* Westley W Williams
 *  CIS 2212-800 Java I FlexPace
 *  Assignment - Midterm Project - Personal Lending Library
 *  Finished February 5th, 2021
 */
package midterm;

import java.util.Scanner;

//library class which contains the main method
public class Library {
	private int numberOfItems;// this will go up or down based on the number of items stored by the user
	private static final int LIBRARY_SIZE = 100;// This is the size of the library itself
	private MediaItem[] items = new MediaItem[LIBRARY_SIZE];// creation of the items array object from the MediaItem class. It
	// holds instances of the MediaItem class
	

	// main method
	public static void main(String[] args) {
		Library libraryObject = new Library();// creation of a library object to access instance data and methods
		int userInput = 0;// initialize the userInput
		Scanner mainScanner = new Scanner(System.in);// creation of scanner to read in data
		// do loop
		do {
			// displayMenu is an instance method, so we need to access it through an object,
			// libraryObject
			userInput = libraryObject.displayMenu();// this variable holds user input now
			// switch statement that is controlled by the user input
			switch (userInput) {
			case 1:
				System.out.println("What is the title?");// queries user
				String title = mainScanner.nextLine();// input string
				System.out.println("What is the format?");// queries user
				String format = mainScanner.nextLine();// input string
				libraryObject.addNewItem(title, format);// call the method to add a new item to the library. we used the
				// name and format for storage
				break;// break off to the bottom of the switch statement
			case 2:
				System.out.println("Which item (enter the title)?");// query of user
				title = mainScanner.nextLine();// user provides the title
				if (libraryObject.checkInLibrary(title) == 1) {
					System.out.println("Who are you loaning it to?");// query the user
					String name = mainScanner.nextLine();// user provides name of media item
					System.out.println("When did you loan it to them?");// another query
					String date = mainScanner.nextLine();// we get the date the media item was loaned
					libraryObject.markItemOnLoan(title, name, date);// program marks the item as loaned
				} else {

				}
				break;
			case 3:// in case 3, we are looking to list the items of the library
				String[] stringArray = libraryObject.listAllItems();
				// we use a loop to print all the items of the library
				// string array is a reference but one can still use the length method for the
				// size of the object the reference variable points to
				for (int i = 0; i < stringArray.length; i++) {
					System.out.println(stringArray[i]);
				}
				break;
			case 4:
				// in this case, we want have a media item returned and we want to mark it as
				// such
				System.out.println("Which item (enter the title)?");
				title = mainScanner.nextLine();
				libraryObject.markItemReturned(title);// called method markItemReturned
				break;
			case 5:
				System.out.println("Goodbye!");// assuming the user enters 5, we exit the program
				System.exit(0);// this was a simple way to exit the program. Is this bad programming practice,
				// though?
			default:
				System.out.println("Invalid input, system exiting.");
				System.exit(0);// again, we do the same as in case 5, but this time if the user enters
				// something outside of the range between 1 and 5.
			}
		} while (userInput != 5);// keep in the do while loop if the user input doesn't equal 5

	}// end of main method

	// new method
	int displayMenu() {
		final String menuOfOptions = "" + "1. Add new item\r\n" + "2. Mark an item as on loan\r\n"
				+ "3. List all items\r\n" + "4. Mark an item as returned\r\n" + "5. Quit\r\n";
		// new scanner for this method should help me avoid issues
		Scanner newScanner = new Scanner(System.in);
		System.out.println(menuOfOptions);// print the menu for the user
		System.out.println("What would you like to do?");// query the user intent

		int userInput = newScanner.nextInt();
		return userInput;// return the userInput to the main method
	}// end of method

	void addNewItem(String title, String format) {
		items[numberOfItems++] = new MediaItem(title, format);
	}// end of method

	// new method
	int checkInLibrary(String title) {
		// loop through the item list
		for (int i = 0; i < numberOfItems; i++) {
			// if the input title matches the stored title, then we know it's in the library
			if (title.equals(items[i].getTitle())) {
				// go back to the main method and continue querying the user
				return 1;
			} else if (i == (numberOfItems - 1) && !title.equals(items[numberOfItems - 1].getTitle())) {
				// unable to find the title in the library
				System.out.println("I'm sorry, I couldn't find this item in the library");
				return -1;
			} else {
				// nothing needed in here
			}

		}
		return 0;

	}// end of method

	// new method
	void markItemOnLoan(String title, String name, String date) {
		int i = 0;
		do {
			if (title.equals(items[i].getTitle())) {
				items[i].markOnLoan(name, date);
				// this else if statement tests to see if we are at the end of items array and
				// the last item doesn't match the title
			} else if (i == (numberOfItems - 1) && !title.equals(items[numberOfItems - 1].getTitle())) {
				System.out.println("Error, item seems not to exist.");
			} else {
				// empty so far, nothing needed here
			}
			i++;

		} while (!title.equals(items[i - 1].getTitle()) && i < numberOfItems);// both of these conditions must be true.
		// We
		// can't be at the end of our list
		// and we must not have a title that equals the current title in the items array
		// (our library)
	}// end of method

	// new method
	String[] listAllItems() {
		String[] stringArray = new String[numberOfItems];// new array
		String concatString;// I will use this to concatenate strings
		for (int i = 0; i < numberOfItems; i++) {
			concatString = items[i].getTitle().concat(" (" + items[i].getFormat().toUpperCase() + ")");
			// assuming this item is loaned out, we will also tack on the info on who has it
			// and when it was loaned
			if (items[i].isOnLoan()) {
				// combine the strings if the media item has been loaned out
				concatString = concatString.concat(" loaned to ").concat(items[i].getLoanedTo()).concat(" on ")
						.concat(items[i].getDateLoaned());
			} else {
				// nothing placed in here so far. Nothing needed so far
			}

			stringArray[i] = concatString;// set the concatString variable equal to the respective element of our array
		}

		return stringArray;// return the string array.
	}// end of method

	// new method
	void markItemReturned(String title) {
		int i = 0;
		// do loop
		// notice we use the equals method instead of ==
		// == was not working for me
		do {
			if (title.equals(items[i].getTitle())) {
				items[i].markReturned();// call the method to say that the item was returned
			} else if (i == (numberOfItems - 1) && !title.equals(items[numberOfItems - 1].getTitle())) {
				System.out.println("Error, could not find the correct item in the array");
			} else {
				// nothing in here as of now.
			}
			i++;// count one up
		} while (i < numberOfItems && !title.equals(items[i - 1].getTitle()));
		// this do while loop will execute so long as we are not at the end of the array
		// and our title argument does not equal the title stored in the library
	}
}
