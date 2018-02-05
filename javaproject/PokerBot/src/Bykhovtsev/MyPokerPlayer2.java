package Bykhovtsev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import pokergame.*;
public class MyPokerPlayer2 implements PokerPlayer {
	private int Chips;
	private String id;	
	private PokerCard one;
	private PokerCard two;
	private int count;
	private double RR;
	private double odds;
	private int potChips;
	private int NumberOfPlayers;
	private boolean checked = false;
	private ArrayList<PokerCard> community;
	public static void main(String[] args) {
		PokerGame.main(new String[]{"Bykhovtsev.MyPokerPlayer",
		"pokerstooges.Curly", "pokerstooges.Moe", "pokerstooges.Larry" });
	}

	@Override
	public void onEvent(PokerEvent e) {
		if (e instanceof PokerNewHandEvent) {
			e = (PokerNewHandEvent) e;
			community = new ArrayList<PokerCard>();
			id = "Mykhailo";
			count = 1;				
		}
		if (e instanceof PokerNewCardEvent){
	        e = (PokerNewCardEvent) e;
	        if (((PokerNewCardEvent) e).faceUp() == false) {
	        	if (count == 1) {
	        		one = ((PokerNewCardEvent) e).card();
	        		count++;
	        	}else if (count == 2) {
	        		two = ((PokerNewCardEvent) e).card();
	        		count = 0;
	        	}
	        }else if (((PokerNewCardEvent) e).faceUp()) {
	        	community.add(((PokerNewCardEvent) e).card());
	        	count++;	        	
	        }
	    }
	}

	@Override
	public int numChips() {
		return this.Chips;
	}

	@Override
	public void collectChips(int numChips) {
		this.Chips -= numChips;		
	}

	@Override
	public void acceptChips(int numChips) {
		this.Chips += numChips;		
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}

	@Override
	public PokerDecision decide(PokerGameDetails game, int betRequiredToCall) {
		this.potChips = game.sizePot();
		this.NumberOfPlayers = game.remainingPlayer().length;	
		if (community.size() >= 3 && checked == false){
			this.RR = (double)ReallyHelp(NumberOfPlayers, one, two, community) / odds(betRequiredToCall);
			checked = true;
		}else {
			checked = false;
			this.RR = 0;
		}
		if(community.size() < 3 && betRequiredToCall < Chips){
			return new PokerDecision(PokerDecision.TYPE.CALL);
		}
		if (this.RR < 0.8) {
			this.RR = 0;
			checked = false;
			return new PokerDecision(PokerDecision.TYPE.FOLD);
		}else if (this.RR < 1.0) {
			this.RR = 0;
			checked = false;
			return new PokerDecision(PokerDecision.TYPE.FOLD);
		}else if (this.RR < 1.3) {
			this.RR = 0;
			checked = false;
			return new PokerDecision(PokerDecision.TYPE.CALL);
		}else if (this.RR >= 1.3 && game.minRaise() + 3 < Chips) {
			this.RR = 0;
			checked = false;
			return new PokerDecision(PokerDecision.TYPE.RAISE, game.minRaise() + 3);
		}else if (betRequiredToCall < Chips){
			return new PokerDecision(PokerDecision.TYPE.CALL);
		}else {
			return new PokerDecision(PokerDecision.TYPE.FOLD);
		}
	}

	@Override
	public PokerHand bestHand() {
		//Finished
		ArrayList<PokerCard> aux = new ArrayList<PokerCard>();
		aux.add(0, one);
		aux.add(1, two);
		aux.addAll(community);
		if (aux.size() == 5) {
			return new PokerHand(aux.toArray(new PokerCard[5]));
		}else if (aux.size() == 6) {
			ArrayList<PokerHand> hands = new ArrayList<PokerHand>();			
			for (int x = 0; x < aux.size() - 1; x++) {
				PokerCard one = aux.remove(x);				
				hands.add(new PokerHand(aux.toArray(new PokerCard[5])));
				aux.add(x, one);				
			}
			Collections.sort(hands);		
			return hands.get(hands.size() - 1);
		}
		ArrayList<PokerHand> hands = new ArrayList<PokerHand>();				
		for (int x = 0; x < aux.size() - 1; x++) {
			PokerCard one = aux.remove(x);
			PokerCard two = aux.remove(x);
			hands.add(new PokerHand(aux.toArray(new PokerCard[5])));
			aux.add(x, one);
			aux.add(x + 1, two);			
		}
		for (int i = aux.size() - 1; i > 2; i--) {		
			for (int g =  i - 1; g > 0; g--) {
				PokerCard one = aux.remove(i);
				PokerCard two = aux.remove(g);
				hands.add(new PokerHand(aux.toArray(new PokerCard[5])));
				aux.add(g, two);
				aux.add(i, one);				
			}			
		}
		Collections.sort(hands);		
		return hands.get(hands.size() - 1);
	}
	
	public double odds(int required) {
		double odds = 0;
		if (required == 0){
			required = 1;
			odds = (double)required/((double)required + (double)this.potChips);
		}else {
			odds = (double)required/((double)required + (double)this.potChips);
		}		
		this.odds = odds;
		return odds;
	}
	
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
				long startTime = System.currentTimeMillis();
				final int loops = 5;
				//creates a new deck of cards.
				ArrayList<PokerCard> cards = makeCards();				
				//removes player's hole cards from the deck
				cards.remove(one);
				cards.remove(two);
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
				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println(totalTime);
				return score/loops;
				
	}
}



