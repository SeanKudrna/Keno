import java.util.ArrayList;
import java.util.Random;

public class KenoTestMethods {
	
	// Instantiate temporary lists to store random numbers in and keep track of matches
	static ArrayList <Integer> drawnNumbers = new ArrayList<Integer>();
	static ArrayList <Integer> winnings = new ArrayList<Integer>();
	
	// This is a copy of the code in the main Keno program that draws 20 random numbers without duplicates.
	// It has been copied here and placed in a while loop (as opposed to pauseAnimation recursive start function() for testing purposes)
	// Code is the same and hence the unitTest result for this function would be the same for the javaFX snippet as well (if it was able to be tested)
	public static ArrayList<Integer> drawNumsNoDuplicates() {
		
		while (drawnNumbers.size() != 20) {
			int random;
			Random rand = new Random();
			random = rand.nextInt(80) + 1;
			// If number has already been selected, run loop again
			if (!drawnNumbers.contains(random)) {
			
				// Add selection to array, set styling
				drawnNumbers.add(random);
			}
		}
		return drawnNumbers;
	}
	
	// This is a copy of the code in the main Keno program that displays winnings
	// It has been copied here and been edited to return winnings (as opposed to output to textField for testing purposes)
	// Code is the same and hence the unitTest result for this function would be the same for the javaFX snippet as well (if it was able to be tested)
	public static int calculateWinnings(int matches, int numSpots) {
		// numSpots and matches have been parameterized as opposed to entered via JavaFX button
		// for testing purposes. Code functionality and logic remains the same.
		if (numSpots == 1) {
			// 1 spot game with 1 match
			if (matches == 1) {
				winnings.add(2);
				return 2;
			} else {
				winnings.add(0);
				return 0;
			}
		} else if (numSpots == 4) {
			// 4 spot game with 2 matches
			if (matches == 2) {
				winnings.add(1);
				return 1;
				// 4 spot game with 3 matches
			} else if (matches == 3) {
				winnings.add(5);
				return 5;
				// 4 spot game with 4 matches
			} else if (matches == 4) {
				winnings.add(75);
				return 75;
			}  else {
				winnings.add(0);
				return 0;
			}
		} else if (numSpots == 8) {
			// 8 spot game with 4 matches
			if (matches == 4) {
				winnings.add(2);
				return 2;
			// 8 spot game with 5 matches
			} else if (matches == 5) {
				winnings.add(12);
				return 12;
			// 8 spot game with 6 matches
			} else if (matches == 6) {
				winnings.add(50);
				return 50;
			// 8 spot game with 7 matches
			} else if (matches == 7) {
				winnings.add(750);
				return 750;
			// 8 spot game with 8 matches
			} else if (matches == 8) {
				winnings.add(10000);
				return 10000;
			}  else {
				winnings.add(0);
				return 0;
			}
		} else if (numSpots == 10) {
			// 10 spot game with 4 matches
			if (matches == 0) {
				winnings.add(5);
				return 5;
			// 10 spot game with 5 matches
			} else if (matches == 5) {
				winnings.add(2);
				return 2;
			// 10 spot game with 6 matches
			} else if (matches == 6) {
				winnings.add(15);
				return 15;
			// 10 spot game with 7 matches
			} else if (matches == 7) {
				winnings.add(40);
				return 40;
			// 10 spot game with 8 matches
			} else if (matches == 8) {
				winnings.add(450);
				return 450;
			// 10 spot game with 0 matches
			} else if (matches == 9) {
				winnings.add(4250);
				return 4250;
			// 10 spot game with 10 matches
			} else if (matches == 10) {
				winnings.add(100000);
				return 100000;
		    }  else {
				winnings.add(0);
				return 0;
			}
		}
		else
			return 0;
	}
}
