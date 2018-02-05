
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class PokerCardTest {

	@Test
	public void testToString() {
		PokerCard one = new PokerCard("Q", "S");
		assertEquals(one.toString(),"QS");		
	}
	
	@Test 
	public void testFactory() {
		assertEquals(PokerCardFactory.create("4H").toString(),"4H");
	}
	
	@Test
	public void testRank() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "Q", "10", "T", "J", "K"));
		for (int x = 0; x < ranks.size(); x++) {
			assertEquals(PokerCard.isRank(ranks.get(x)), true);
		}
		assertEquals(PokerCard.isRank("Z"), false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidArgument() {
		assertEquals(new PokerCard("Q", ""), new IllegalArgumentException());
		assertEquals(new PokerCard("", "S"), new IllegalArgumentException());
	}
	
	@Test
	public void testSuit() {
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H"));
		for (int x = 0; x < suits.size(); x++) {
			assertEquals(PokerCard.isSuit(suits.get(x)), true);
		}
		assertEquals(PokerCard.isSuit("B"), false);
	}

	@Test
	public void PokerHandTest() {
		PokerHand hand = new PokerHand();
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "5", "3", "8", "6"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		for(int x = 0; x < 5; x++) {
		hand.addCard(new PokerCard(ranks.get(x), suits.get(x)));
		}		
		assertEquals(hand.toString(), "2S 3C 5D 6S 8H, HIGH_CARD");

	}
	
	@Test
	public void StraightFlushTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6"));
		ArrayList<String>suits = new ArrayList<String>(Arrays.asList("S", "S", "S", "S", "S"));
		assertEquals(categoryCheck(ranks, suits), "2S 3S 4S 5S 6S, STRAIGHT_FLUSH");
	}
	
	@Test
	public void FourOfAKindTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("4", "2", "2", "2", "2"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		assertEquals(categoryCheck(ranks, suits), "2D 2C 2H 2S 4S, FOUR_OF_A_KIND");
	}
	
	@Test
	public void FourOfAKindTest2() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "2", "2", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		assertEquals(categoryCheck(ranks, suits), "2S 2D 2C 2H 4S, FOUR_OF_A_KIND");
	}
	
	@Test
	public void FullHouseTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "2", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		assertEquals(categoryCheck(ranks, suits), "2S 2D 2C 4H 4S, FULL_HOUSE");
	}
	
	@Test
	public void FullHouseTest2() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "4", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		assertEquals(categoryCheck(ranks, suits), "4S 4H 4C 2S 2D, FULL_HOUSE");
	}
	
	@Test
	public void FlushTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "Q", "8"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "S", "S", "S", "S"));
		assertEquals(categoryCheck(ranks, suits), "2S 3S 4S 8S QS, FLUSH");
	}
	
	@Test
	public void StraightTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("S", "D", "C", "H", "S"));
		assertEquals(categoryCheck(ranks, suits), "2S 3D 4C 5H 6S, STRAIGHT");

	}
	
	@Test
	public void ThreeOfAKindTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("3", "2", "4", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "C", "D"));
		assertEquals(categoryCheck(ranks, suits), "4D 4C 4S 2C 3D, THREE_OF_A_KIND");
	}
	
	@Test
	public void ThreeOfAKindTest2() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "2", "4", "5"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "C", "S"));
		assertEquals(categoryCheck(ranks, suits), "2D 2C 2S 4C 5S, THREE_OF_A_KIND");
	}
	
	@Test
	public void ThreeOfAKindTest3() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "3", "3", "5"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "D", "S", "S"));
		assertEquals(categoryCheck(ranks, suits), "2D 3C 3D 3S 5S, THREE_OF_A_KIND");
	}
	
	@Test
	public void TwoPairTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "3", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "3C 3S 4D 4H 2D, TWO_PAIR");
	}
	
	@Test
	public void TwoPairTest2() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "3", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "2D 2C 4H 4D 3S, TWO_PAIR");
	}
	
	@Test
	public void TwoPairTest3() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "2", "3", "3", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "2D 2C 3S 3D 4H, TWO_PAIR");
	}
	
	/*@Test
	public void HighCardTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "4", "K", "5", "6"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "2D 4C 5D 6H KS, HIGH_CARD");
	}*/
	
	@Test
	public void PairTest() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "4", "6"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "4S 4D 2D 3C 6H, PAIR");
	}
	
	@Test
	public void PairTest2() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "5", "4", "4"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("D", "C", "S", "D", "H"));
		assertEquals(categoryCheck(ranks, suits), "4D 4H 2D 3C 5S, PAIR");
	}
	
	public String categoryCheck(ArrayList<String> ranks, ArrayList<String> suits) {
		PokerHand hand = new PokerHand();
		for(int x = 0; x < 5; x++) {
			hand.addCard(new PokerCard(ranks.get(x), suits.get(x)));
		}
		return hand.toString();
	}
}
