import java.util.*;

public class PokerCard implements Comparable<PokerCard> {
	private String rank;
	private String suit;
	public ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"));
	
	
	public PokerCard(String rank, String suit) {
		if (isRank(rank)){
			this.rank = rank;
			if(rank.equals("10")) {
				this.rank = "T";
			}
		} else {
			throw new IllegalArgumentException();
		}
		if (isSuit(suit)){
			this.suit = suit;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public static boolean isRank(String rank){
		if (rank.equals("A") || rank.equals("T") || rank.equals("Q") || rank.equals("J") || rank.equals("K")){
		return true;
		}		
		for (int x = 2; x <= 10; x++){			
			if (rank.equals("" + x)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSuit(String suit){
		if (suit.equals("S") || suit.equals("D") || suit.equals("H") || suit.equals("C")){
			return true;
		}else {
			return false;
		}
	}
	public String getSuit() { return this.suit;}
	public String toString() {
		return "" + this.rank + this.suit;
	}

	@Override
	public int compareTo(PokerCard arg0) {
		return this.ranks.indexOf(this.rank) - arg0.ranks.indexOf(arg0.rank);
	}
}
