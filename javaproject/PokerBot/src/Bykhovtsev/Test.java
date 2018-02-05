package Bykhovtsev;
import pokergame.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {

	public static void main(String[] args) {
		int required = 20;
		int potChips = 160;
		double odds= (double)required/((double)required + (double)potChips);
		System.out.println(odds);
		System.out.println(0.204/0.0);
		final int loops = 1000;
		final int numberOfPlayers = 5;
		//creates a new deck of cards.
		ArrayList<PokerCard> cards = makeCards();
		PokerCard one = new PokerCard("QD");
		PokerCard two = new PokerCard("QC");
		//removes player's hole cards from the deck
		cards.remove(Collections.binarySearch(cards, one));
		cards.remove(Collections.binarySearch(cards, two));
		double score = 0;
		for (int h = 0; h < loops; h++) {
			Collections.shuffle(cards);		
			ArrayList<PokerHand> players = new ArrayList<PokerHand>();			
			ArrayList<PokerCard> hands = new ArrayList<PokerCard>();
			ArrayList<PokerCard> community = new ArrayList<PokerCard>();
			
			//Deals all the cards to the simulation players, and the community cards.
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < numberOfPlayers; y++) {
					if (x != 2) {
						hands.add(cards.remove(cards.size() - 1));
					}else {
						community.add(cards.remove(cards.size() - 1));
					}
				}
			}
			PokerCard[] playerCards = new PokerCard[7];
			playerCards[0] = one;
			playerCards[1] = two;
			for (int x = 2; x < 7; x++) {
				playerCards[x] = community.get(x - 2);
			}
			
			// Gets the best player hand out of 2 hole cards and 5 community cards.
			PokerHand player = bestHand(playerCards);			
			PokerCard[] othersCards = new PokerCard[7];
			//Makes an ArrayList of best hands of all simulated players.
			for (int a = 0; a < numberOfPlayers; a++) {
				othersCards[0] = hands.get(a);
				othersCards[1] = hands.get(a + 5 );
				othersCards[2] = community.get(0);
				othersCards[3] = community.get(1);
				othersCards[4] = community.get(2);
				othersCards[5] = community.get(3);
				othersCards[6] = community.get(4);
				players.add(bestHand(othersCards));
			}
			players.add(0, player);	
			//sort ArrayList to find which hand is the best
			Collections.sort(players);
			//check if player's hand is the best from the others and increment the score.
			if (players.get(players.size() - 1).equals(player)){
				score++;				
			}
			cards.addAll(community);
			cards.addAll(hands);
		}
		//Percentage of win with these particular hand.
		double HandStrength = score / loops;
		System.out.println("Hand Strength is: " + HandStrength);
		System.out.println("The odds are: " + HandStrength/0.333);
		}
	
	// Determines best hand out of 7 available cards and returns it.
	public static PokerHand bestHand(PokerCard[] possible) {
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
		PokerCard[] aux = new PokerCard[5];
		ArrayList<PokerCard> helper = new ArrayList<PokerCard>(Arrays.asList(possible));		
		for (int x = 0; x < 6; x++) {
			PokerCard one = helper.remove(x);
			PokerCard two = helper.remove(x);
			for (int y = 0; y < helper.size(); y++) {
				aux[y] = helper.get(y);
			}
			helper.add(x, one);
			helper.add(x + 1, two);
			hands.add(new PokerHand(aux));
		}
		for (int i = 0; i < 5; i++) {		
			for (int g = i + 1; g < 6; g++) {
				PokerCard one = helper.remove(i);
				PokerCard two = helper.remove(g);
				for (int y = 0; y < helper.size(); y++) {
					aux[y] = helper.get(y);
				}
				helper.add(i, one);
				helper.add(g, two);
				hands.add(new PokerHand(aux));
			}			
		}
		Collections.sort(hands);		
		return hands.get(hands.size() - 1);
	}
	
	public static PokerHand bestHand2(PokerCard[] possible) {
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
		PokerCard[] aux = new PokerCard[5];
		for (int g = 0; g < 3; g++) {
			aux[g] = possible[g];
			aux[g + 1] = possible[g + 1];
			for (int k = 6; k > g + 2; k--) {
				aux[k - 2] = possible[k];
			}
			hands.add(new PokerHand(aux));
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
}
