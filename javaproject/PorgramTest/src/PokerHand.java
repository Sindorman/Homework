import java.util.ArrayList;
import java.util.Arrays;

public class PokerHand implements Comparable<PokerHand> {

	private PokerCard[] hand = new PokerCard[5];
	private int count = -1;
	private Category category;	
	enum Category {
		HIGH_CARD, 
		PAIR, 
		TWO_PAIR, 
		THREE_OF_A_KIND, 
		STRAIGHT,
		FLUSH, 
		FULL_HOUSE, 
		FOUR_OF_A_KIND, 
		STRAIGHT_FLUSH		
	}
	
	public Category getCategory() { return this.category;}	
	
	@Override
	public int compareTo(PokerHand o) {
		if (!this.category.equals(o.category)) {
			return this.category.ordinal() - o.category.ordinal();
		}else if (this.category.equals(o.category)){
			if (this.category.equals(Category.FULL_HOUSE) ) {
				if (this.hand[2].compareTo(o.hand[2]) == 0) {
					return this.hand[3].compareTo(o.hand[3]);	
				}else {
					return this.hand[2].compareTo(hand[2]);
				}
			}else if(this.category.equals(Category.STRAIGHT_FLUSH) ||this.category.equals(Category.FLUSH) || this.category.equals(Category.STRAIGHT)) {
				for (int x = 4; x >= 0; x--) {
					if (this.hand[x].compareTo(o.hand[x]) != 0) {
						return this.hand[x].compareTo(o.hand[x]);
					}
				}
				return 0;				
			}else if (this.category.equals(Category.FOUR_OF_A_KIND)) {
				if (this.hand[2].compareTo(o.hand[2]) == 0){
					return this.hand[4].compareTo(o.hand[4]);
				}else {
					return this.hand[2].compareTo(o.hand[2]);
				}
			}else if (this.category.equals(Category.THREE_OF_A_KIND)) {
				if (this.hand[2].compareTo(o.hand[2]) == 0) {
					return this.hand[4].compareTo(o.hand[4]);
				}else {
					return this.hand[2].compareTo(hand[2]);
				}
			}else if (this.category.equals(Category.TWO_PAIR)) {
				if (this.hand[3].compareTo(o.hand[3]) == 0) {
					if (this.hand[0].compareTo(o.hand[0]) == 0) {
						return this.hand[4].compareTo(o.hand[4]);
					}
					return this.hand[0].compareTo(o.hand[0]);
				}
			}else if (this.category.equals(Category.PAIR)) {
				if (this.hand[0].compareTo(o.hand[0]) == 0) {
					for (int i = 4; i >= 2; i--) {
						if (this.hand[i].compareTo(o.hand[i]) != 0) {
							return this.hand[i].compareTo(o.hand[i]);
						}
					}
				}else {
					return this.hand[0].compareTo(o.hand[0]);
				}
			}else {
				for (int i = 4; i > 0; i--) {
					if (this.hand[i].compareTo(o.hand[i]) != 0) {
						return this.hand[i].compareTo(o.hand[i]);
					}
				}
				return this.hand[4].compareTo(o.hand[4]);
			}
		}
		return 0;
		
	}
	
	public void addCard(PokerCard card) {		
		hand[count+1] = card;				
		count++;
		if (count == 4) {
			Arrays.sort(hand);
			checkCategory();
		}
	}
	
	public String toString() {
		String ret = "";
		for (int x = 0; x < hand.length - 1; x ++) {
			if (hand[x] == null) {
				
			}else {
			ret += hand[x] + " "; 
			}
		}
		if (hand[hand.length - 1] == null) {
			
		}else {
			ret += hand[hand.length - 1];
		}
		if (category == null) {
			return ret;
		}else {
			return ret + ", " + category;
		}
	}
	
	private void checkCategory() {
		if (isStraightFlush()){			
			category = Category.STRAIGHT_FLUSH;						
		}else if (isFourOfKind()) {
			category = Category.FOUR_OF_A_KIND;			
		}else if (isFullHouse()) {
			category = Category.FULL_HOUSE;			
		}else if (isFlush()) {
			category = Category.FLUSH;			
		}else if (isStraight()) {
			category = Category.STRAIGHT;			
		}else if(isThreeOfAKind()) {
			category = Category.THREE_OF_A_KIND;			
		}else if(isTwoPair()) {
			category = Category.TWO_PAIR;			
		}else if(isPair()){
			category = Category.PAIR;			
		}else {
			category = Category.HIGH_CARD;			
		}
	}
	private boolean isStraight() {
		int count = 0;
		for (int x = 0; x < 4; x++) {
			if (hand[x + 1].compareTo(hand[x]) == 1) {
				count += 1;
			}
			
		}
		if (count == 4){
			return true;
		}else {
			return false;
		}
	}
	
	private boolean isStraightFlush() {
		if (isFlush()) {
			return isStraight();
		}else {
			return false;
		}
	}
	
	private boolean isFourOfKind() {		
		for (int x = 0; x < 2; x++) {
			int count = 0;
			for (int y = x; y < x + 3; y++) {
				if (hand[y].compareTo(hand[y + 1]) == 0) {
					count += 1;
				}
			}
			if (count == 3) {
				if (hand[0].compareTo(hand[1]) != 0){
					PokerCard temp = hand[4]; 
					hand[4] = hand[0];
					hand[0] = temp;
				}
				return true;			
			}
		}
		return false;
		
	}
	
	private boolean isFullHouse() {
		if (hand[0].compareTo(hand[1]) == 0 && hand[1].compareTo(hand[2]) == 0) {
			if (hand[3].compareTo(hand[4]) == 0){				
			return true;
			}else {
				return false;
			}
		}else if (hand[2].compareTo(hand[3]) == 0 && hand[3].compareTo(hand[4]) == 0) {
			if (hand[0].compareTo(hand[1]) == 0){
				PokerCard one = hand[0];
				PokerCard two = hand[1];
				hand[0] = hand[4];
				hand[1] = hand[3];
				hand[4] = two;
				hand[3] = one;
				return true;				
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	private boolean isFlush() {
		for (int i = 0; i < 4; i ++) {
			if (!hand[i].getSuit().equals(hand[i + 1].getSuit())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isThreeOfAKind() {
		for (int x = 0; x < 3; x++) {
			int count = 0;
			for (int y = x; y < x + 2; y++) {
				if (hand[y].compareTo(hand[y + 1]) == 0) {
					count += 1;
				}
			}
			if (count == 2) {
				if (hand[2].compareTo(hand[1]) != 0) {
					PokerCard one = hand[0];
					PokerCard two = hand[1];
					hand[0] = hand[4];
					hand[1] = hand[3];
					hand[3] = one;
					hand[4] = two;
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean isTwoPair() {
		if (hand[0].compareTo(hand[1]) == 0 && hand[2].compareTo(hand[3]) == 0) {			
			return true;
		}else if(hand[0].compareTo(hand[1]) == 0 && hand[3].compareTo(hand[4]) == 0) {			
			PokerCard temp = hand[2];
			hand[2] = hand[4];
			hand[4] = temp;
			return true;
		}else if(hand[1].compareTo(hand[2]) == 0 && hand[3].compareTo(hand[4]) == 0) {
			ArrayList<PokerCard> temp = new ArrayList<PokerCard>(Arrays.asList(hand));
			temp.add(temp.get(0));
			temp.remove(0);
			for (int i = 0; i < hand.length; i++) {
				hand[i] = temp.get(i);
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	private boolean isPair() {
		for (int x = 0; x < hand.length -1; x++) {
			if (hand[x].compareTo(hand[x+1]) == 0){
				ArrayList<PokerCard> temp = new ArrayList<PokerCard>(Arrays.asList(hand[x], hand[x + 1]));
				ArrayList<PokerCard> sorted = new ArrayList<PokerCard>();
				for (int i = x - 1; i >= 0; i --) {
					sorted.add(hand[i]);
				}
				for (int h = x + 2; h < hand.length; h ++) {
					sorted.add(hand[h]);
				}
				sorted.sort(null);
				temp.addAll(sorted);
				for (int y = 0; y < hand.length; y++) {
					hand[y] = temp.get(y);
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean isHighCard() {
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].compareTo(new PokerCard("9", "S")) > 0){				
				return true;
			}
		}
		return false;
	}
}
