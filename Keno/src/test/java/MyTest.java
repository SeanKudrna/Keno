import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.DisplayName;

//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	@Test
	// Test to check that 20 numbers are drawn
	void draw20NumsTest() {
		assertEquals(20, KenoTestMethods.drawNumsNoDuplicates().size());
	}
	
	@Test
	// Test to check that there are no duplicates in drawn numbers
	void draw20NoDuplicatesTest() {
		Set<Integer> copyDrawnNums = new HashSet<Integer>();
		for (int i = 0; i < KenoTestMethods.drawNumsNoDuplicates().size(); i++) {
			copyDrawnNums.add(KenoTestMethods.drawNumsNoDuplicates().get(i));
		}
		
		assertEquals(copyDrawnNums.size(), KenoTestMethods.drawNumsNoDuplicates().size());
	}
	
	@Test
	// Test to check that there are no numbers over 80 are drawn
	void drawNums80OrUnder() {
		boolean detectedOver80 = false;
		for (int i = 0; i < KenoTestMethods.drawNumsNoDuplicates().size(); i++) {
			if (KenoTestMethods.drawNumsNoDuplicates().get(i) > 80)
				detectedOver80 = true;
		}
		
		assertFalse(detectedOver80);
	}
	
	@Test
	// Test to check that there are no numbers over 80 are drawn
	void drawNums1OrAbove() {
		boolean detectedUnder1 = false;
		for (int i = 0; i < KenoTestMethods.drawNumsNoDuplicates().size(); i++) {
			if (KenoTestMethods.drawNumsNoDuplicates().get(i) < 1)
				detectedUnder1 = true;
		}
		
		assertFalse(detectedUnder1);
	}
	
	@Test
	// Tests to calculate winnings 1 spot 1 matches
	void calcWinningsOneSpotOneMatchTest() {
		assertEquals(KenoTestMethods.calculateWinnings(1, 1), 2);
	}
	
	@Test
	// Tests to calculate winnings 4 spot 2 matches
	void calcWinningsFourSpotTwoMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(2, 4), 1);
	}
	
	@Test
	// Tests to calculate winnings 4 spot 3 matches
	void calcWinningsFourSpotThreeMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(3, 4), 5);
	}
	
	@Test
	// Tests to calculate winnings 4 spot 4 matches
	void calcWinningsFourSpotFourMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(4, 4), 75);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 4 matches
	void calcWinningsEightSpotFourMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(4, 8), 2);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 5 matches
	void calcWinningsEightSpotFiveMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(5, 8), 12);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 6 matches
	void calcWinningsEightSpotSixMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(6, 8), 50);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 7 matches
	void calcWinningsEightSpotSevenMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(7, 8), 750);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 8 matches
	void calcWinningsEightSpotEightMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(8, 8), 10000);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 0 matches
	void calcWinningsTenSpotZeroMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(0, 10), 5);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 5 matches
	void calcWinningsTenSpotFiveMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(5, 10), 2);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 6 matches
	void calcWinningsTenSpotSixMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(6, 10), 15);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 7 matches
	void calcWinningsTenSpotSevenMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(7, 10), 40);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 8 matches
	void calcWinningsTenSpotEightMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(8, 10), 450);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 6 matches
	void calcWinningsTenSpotNineMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(9, 10), 4250);
	}
	
	@Test
	// Tests to calculate winnings 10 spot 10 matches
	void calcWinningsTenSpotTenMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(10, 10), 100000);
	}
	
	@Test
	// Tests to calculate winnings 8 spot 0 matches (outside bounds)
	void calcWinningsEightSpotZeroMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(0, 8), 0);
	}
	
	@Test
	// Tests to calculate winnings 4 spot 0 matches (outside bounds)
	void calcWinningsFourSpotZeroMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(0, 4), 0);
	}
	
	@Test
	// Tests to calculate winnings 1 spot 0 matches (outside bounds)
	void calcWinningsOneSpotZeroMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(0, 1), 0);
	}
	
	@Test
	// Tests to calculate winnings 10 spot other matches (Matches outside bounds)
	void calcWinnings10SpotOtherMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(11, 10), 0);
	}
	
	@Test
	// Tests to calculate winnings other spot other matches (Matches and Spots outside bounds)
	void calcWinningsOtherSpotOtherMatchesTest() {
		assertEquals(KenoTestMethods.calculateWinnings(11, 11), 0);
	}
	
	
	
	
	

}
