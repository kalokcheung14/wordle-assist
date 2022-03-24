package com.wordle_assist;

import java.util.*;

public class Word{
	// Check if input is valid integer
	protected static int validateIntChoice(String choiceStr, int minValue, int maxValue) {
		 try {
			 // Check if the input is integer by integer parsing
			 int choice = Integer.parseInt(choiceStr);
			 
			 // Make sure the input integer is in range
			 if (choice < minValue || choice > maxValue) {
				 // Otherwise return -1
				 return -1;
			 } else {
				 // Return the choice as integer if it is valid
				 return choice;
			 }
		 } catch (Exception e) {
			 System.out.println("Wrong choice. Please select again.");
			 System.out.println();
		 }
		// Return -1 if the input is not integer
		return -1;
	}
	
	protected static char validateAlphabet(char alphabet) {
		// Convert the alphabet to lower case
		char c = alphabet;
		
		// Check whether the input is an alphabet
		// if not, return empty string
		// Otherwise just return the string
		if (c < 'a' || c > 'z') {
			return '@';
		} else {
			return c;
		}
	}
	
	protected static void addAlphabetInPosition(Scanner input, GuessRule rule, boolean correctPosition) {
		int position = -1;
		
		// Loop the input prompt for alphabets in correct position
 		do {
	 		System.out.print("First, enter the position of the alphabet: ");
	 		// Take the position as input first
	 		position = validateIntChoice(input.nextLine(), 0, GuessRule.NUM_OF_CHAR);
	 		
	 		// If the position is -1 (e.g. invalid), continue the loop
	 		if (position == -1) {
	 			continue;
	 		} else if (position == 0) {
	 			// If input is 0, cancel the process
	 			System.out.println("Cancelled");
	 			break;
	 		}
	 		
	 		System.out.print("Second, enter the alphabet: ");
	 		
	 		// Take the second input
	 		char inputChar = input.nextLine().toLowerCase().charAt(0);
	 		if (inputChar == '0') {
	 			// If input is 0, cancel the process
	 			System.out.println("Cancelled");
	 			break;
	 		}
	 		
	 		// Check if the last input is valid
	 		char alphabet = validateAlphabet(inputChar);
	 		
	 		// If the input in not valid, loop back
	 		if (alphabet == '@') {
	 			continue;
	 		}
	 		
	 		String message;
	 		
	 		// Add the character at the corresponding position in the array
	 		if (correctPosition) {
	 			rule.addCorrectAlphabet(position - 1, alphabet);
	 			message = "Character %s at position %d added\n";
			} else {
				rule.addMisplacedAlphabet(position - 1, alphabet);
				message = "Character %s not at position %d added\n";
			}
	 		
	 		// Display the position and the character input, along with the type of input
	 		System.out.printf(message, alphabet, position);
 		} while (position != 0);
	}
	
	protected static void addUnusedAlphabets(Scanner input, GuessRule rule) {
		char alphabet = '@';
		
		do {
			System.out.print("Input unused characters: ");
			String inputStr = input.nextLine().toLowerCase();
			
			
			if (inputStr.equals("0")) {
				// Leave loop when input is 0
				break;
			}
			
			// Loop through all the characters in the input
			for (int i = 0; i < inputStr.length(); i++) { 
				char inputChar = inputStr.charAt(i);
				// Validate alphabet
				alphabet = validateAlphabet(inputChar);
				
				if (alphabet == '@') {
					// Continue if the character is invalid
					continue;
				} else {
					// Add the valid character into unused alphabet list
					rule.addUnusedAlphabet(alphabet);
					System.out.printf("Character %s is added as unused character\n", alphabet);
				}
			}
		} while (alphabet != '0');
	}
	
	public static void main(String []args){
		// initiate a guess rule object
		GuessRule rule = new GuessRule();
    	 
		// Set the default value of choice to -1
    	int choice = -1;
    	 
    	while (choice != 0) {
    		// Display the menu
    		System.out.println("Welcome to Wordle Assist. Please choose an action:");
	    	System.out.println("(1) Add alphabets in correct position");
	    	System.out.println("(2) Add alphabets in wrong position");
	    	System.out.println("(3) Add unused alphabets");
	    	System.out.println("(4) Show guess list");
	    	System.out.println("(0) Exit");
	    	System.out.print("Your choice: ");
	    	 
	    	Scanner input = new Scanner(System.in);

	    	// Get the choice from the input
	    	String choiceStr = input.nextLine();
	    	 	 
	    	// Check if the choice is valid
	    	choice = validateIntChoice(choiceStr, 0, 4);
	   
	    	switch (choice) {
	    		case -1:
	    			// If the choice is invalid, -1 is returned so just loop back to the beginning
	    	 		continue;
	    	 	case 0:
	    	 		// Terminate if the choice is 0
	    	 		System.out.println("Goodbye!");
	    	 		break;
	    	 	case 1:
	    	 		// Add a correct alphabets if the choice is 1
	    	 		addAlphabetInPosition(input, rule, true);
	    	 		break;
	    	 	case 2:
	    	 		// Add a misplaced alphabets if the choice is 2
	    	 		addAlphabetInPosition(input, rule, false);
	    	 		break;
	    	 	case 3:
	    	 		// Add unused alphabets
	    	 		addUnusedAlphabets(input, rule);
	    	 		break;
	    	 	case 4:
	    	 		// Display a list of guess after applying the rule
	    	 		ArrayList<String> matchedWords = rule.showGuessList();
	    	 		for (String word: matchedWords) {
	    	 			System.out.println(word);
	    	 		}
	    	 		break;
	    	}
    	}
    }
}