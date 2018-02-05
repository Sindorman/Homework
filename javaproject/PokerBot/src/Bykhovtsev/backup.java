package Bykhovtsev;
import pokergame.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class backup {

	public static void main(String[] args) {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("C", "D", "H", "S"));
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		PokerCard one = new PokerCard("4D");
		PokerCard two = new PokerCard("4C");
		for (int x = 0; x < 13; x++) {
			for (int y = 0; y < 4; y++) {
				cards.add(new PokerCard(ranks.get(x) + suits.get(y)));
			}
		}
		cards.remove(Collections.binarySearch(cards, one));
		cards.remove(Collections.binarySearch(cards, two));
		int score = 0;
		for (int h = 0; h < 1000; h++) {
			Collections.shuffle(cards);		
			ArrayList<PokerHand> players = new ArrayList<PokerHand>();
			System.out.println(cards);
			ArrayList<PokerCard> hands = new ArrayList<PokerCard>();
			ArrayList<PokerCard> community = new ArrayList<PokerCard>();
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 5; y++) {
					if (x != 2) {
						hands.add(cards.remove(cards.size() - 1));
					}else {
						community.add(cards.remove(cards.size() - 1));
					}
				}
			}
			String playerHand = "";
			for (int x = 0; x < 3; x++) {
				playerHand += community.get(x) + " ";
			}
			playerHand += one.toString() + " " + two.toString();
			PokerHand player = new PokerHand(playerHand);
			for (int x = 0; x < 5; x++) {
				String cards2 = "";
				for (int y = 0; y < 3; y++) {
					cards2 += community.get(y) + " ";
				}
				PokerHand hand = new PokerHand(cards2 + hands.get(x).toString() + " " + hands.get(x + 4).toString());
				players.add(hand);
			}
			players.add(0, player);
			players.sort(null);
			if (players.get(0).equals(player)){
				score++;
			}
			cards.addAll(community);
			cards.addAll(hands);
		}
			System.out.println(score/1000);		
		}
	

	/*public PokerHand bestHand() {
		//Finished
		PokerCard[] mine = new PokerCard[community.size() + 2];
		mine[0] = one;
		mine[1] = two;
		for (int x = 2; x < community.size() + 2; x++) {
			mine[x] = community.get(x-2);
		}
		if (mine.length == 5) {
			return new PokerHand(mine);
		}else if (mine.length == 6) {
			ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
			PokerCard[] aux = new PokerCard[5];
			ArrayList<PokerCard> helper = new ArrayList<PokerCard>(Arrays.asList(mine));
			for (int x = 0; x < community.size() + 1; x++) {
				PokerCard one = helper.remove(x);				
				for (int y = 0; y < helper.size(); y++) {
					aux[y] = helper.get(y);
				}
				helper.add(x, one);				
				hands.add(new PokerHand(aux));
			}
			Collections.sort(hands);		
			return hands.get(hands.size() - 1);
		}
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
		PokerCard[] aux = new PokerCard[5];
		ArrayList<PokerCard> helper = new ArrayList<PokerCard>(Arrays.asList(mine));		
		for (int x = 0; x < community.size() + 1; x++) {
			PokerCard one = helper.remove(x);
			PokerCard two = helper.remove(x);
			for (int y = 0; y < helper.size(); y++) {
				aux[y] = helper.get(y);
			}
			helper.add(x, one);
			helper.add(x + 1, two);
			hands.add(new PokerHand(aux));
		}
		for (int i = community.size() + 1; i > 2; i--) {		
			for (int g =  i - 1; g > 0; g--) {
				PokerCard one = helper.remove(i);
				PokerCard two = helper.remove(g);
				for (int y = 0; y < helper.size(); y++) {
					aux[y] = helper.get(y);
				}
				helper.add(g, two);
				helper.add(i, one);				
				hands.add(new PokerHand(aux));
			}			
		}
		Collections.sort(hands);		
		return hands.get(hands.size() - 1);
	}*/
}
