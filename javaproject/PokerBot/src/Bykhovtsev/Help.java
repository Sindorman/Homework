package Bykhovtsev;
import pokergame.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//class that handles evaluating strength of the hand compared to the rest of the cards and returning the percentage.
public class Help {	
	// Determines best hand out of 7 available cards and returns it.
	public static PokerHand bestHand(ArrayList<PokerCard> possible) {		
		if (possible.size() == 5) {
			return new PokerHand(possible.toArray(new PokerCard[5]));
		}else if (possible.size() == 6) {
			ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
			for (int x = 0; x < possible.size() - 1; x++) {
				PokerCard one = possible.remove(x);				
				hands.add(new PokerHand(possible.toArray(new PokerCard[5])));
				possible.add(x, one);				
			}
			Collections.sort(hands);		
			return hands.get(hands.size() - 1);
		}
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
		for (int x = 0; x < possible.size() - 1; x++) {
			PokerCard one = possible.remove(x);
			PokerCard two = possible.remove(x);
			hands.add(new PokerHand(possible.toArray(new PokerCard[5])));
			possible.add(x, one);
			possible.add(x + 1, two);
			
		}
		for (int i = possible.size() - 1; i > 2; i--) {		
			for (int g =  i - 1; g > 0; g--) {
				PokerCard one = possible.remove(i);
				PokerCard two = possible.remove(g);
				hands.add(new PokerHand(possible.toArray(new PokerCard[5])));
				possible.add(g, two);
				possible.add(i, one);				
			}			
		}
		Collections.sort(hands);		
		return hands.get(hands.size() - 1);
	
	}
	
	// creates deck of all 52 cards and returns the ArrayList of them.
	public static ArrayList<PokerCard> makeCards() {
		ArrayList<String> ranks = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"));
		ArrayList<String> suits = new ArrayList<String>(Arrays.asList("C", "D", "H", "S"));
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		for (int x = 0; x < 13; x++) {
			for (int y = 0; y < 4; y++) {
				cards.add(new PokerCard(ranks.get(x) + suits.get(y)));
			}
		}
		return cards;
	}
	
	public static double ReallyHelp(int NumberOfPlayers, PokerCard one, PokerCard two, ArrayList<PokerCard> community) {
				final int loops = 1000;
				//creates a new deck of cards.
				ArrayList<PokerCard> cards = makeCards();				
				//removes player's hole cards from the deck
				cards.remove(Collections.binarySearch(cards, one));
				cards.remove(Collections.binarySearch(cards, two));
				cards.removeAll(community);
				double score = 0;
				for (int h = 0; h < loops; h++) {
					Collections.shuffle(cards);		
					ArrayList<PokerHand> players = new ArrayList<PokerHand>();			
					ArrayList<PokerCard> hands = new ArrayList<PokerCard>();
					
					//Deals all the cards to the simulation players, and the community cards.
					for (int x = 0; x < 2; x++) {
						for (int y = 0; y < NumberOfPlayers; y++) {							
								hands.add(cards.remove(cards.size() - 1));
								
						}
					}
					ArrayList<PokerCard> playerCards = new ArrayList<PokerCard>();
					playerCards.add(0, one);
					playerCards.add(1, two);
					playerCards.addAll(community);
					
					// Gets the best player hand out of 2 hole cards and 5 community cards.
					PokerHand player = bestHand(playerCards);			
					ArrayList<PokerCard> othersCards = new ArrayList<PokerCard>();
					
					//Makes an ArrayList of best hands of all simulated players.					
					for (int a = 0; a < NumberOfPlayers; a++) {
						othersCards.add(0, hands.get(a));
						othersCards.add(1, hands.get(a + NumberOfPlayers));
						othersCards.addAll(community);
						players.add(bestHand(othersCards));
						othersCards.clear();
					}
					players.add(0, player);	
					
					//sort ArrayList to find which hand is the best
					Collections.sort(players);
					
					//check if player's hand is the best from the others and increment the score.
					if (players.get(players.size() - 1).equals(player)){
						score++;				
					}					
					cards.addAll(hands);
				}
				//Percentage of win with these particular hand.
				return score/loops;
				
	}
}