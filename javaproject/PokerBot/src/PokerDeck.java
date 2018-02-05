
import java.util.*;

public class PokerDeck {

	private ArrayList<PokerCard> deck = new ArrayList<PokerCard>();
	
	public PokerDeck() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("C", "D", "H", "S"));
		for (int x = 0; x < 13; x++) {
			for (int y = 0; y < 4; y++){
			deck.add(new PokerCard(ranks.get(x), suits.get(y)));
			}
		}
		
	
	}
	
	public String toString() {
		return this.deck.toString();
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public PokerCard draw() {
		PokerCard temp = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return temp;
	}
	
	public void draw(PokerHand targetHand) {
		PokerCard temp = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		targetHand.addCard(temp);
	}

}
