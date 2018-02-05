
import java.util.*;
public class PokerGame {

	public static void main(String[] args) {
		PokerDeck deck = new PokerDeck();
		deck.shuffle();		
		ArrayList<PokerHand> playerscores = new ArrayList<PokerHand>();
		for (int x = 0; x < 8; x++) {
			playerscores.add(new PokerHand());
		}
		for (int x = 0; x < 5; x ++) {
			for (int i = 0; i < 8; i++) {
				playerscores.get(i).addCard(deck.draw());
			}
		}
		ArrayList<PokerHand> playersNames = new ArrayList<PokerHand>(playerscores);
		playersNames.add(0, null);
		playerscores.sort(null);		
		ArrayList<PokerHand> others = new ArrayList<PokerHand>(playerscores);
		int count = 0;
		if (playerscores.get(playerscores.size() - 1).compareTo(playerscores.get(playerscores.size() - 2)) == 0){
			for (int i = playerscores.size() - 1; i >= 2; i--){
				if (playerscores.get(playerscores.size() - 1).compareTo(playerscores.get(playerscores.size() - 2)) == 0){
					count++;
				}
			}
			System.out.println("We have " + count + " ties: ");
			for (int x = others.size() - 1; x >= 0; x--) {
				System.out.println("Player " + winner(playersNames, others) + ", " + others.get(x).toString());
				others.remove(others.size() - 1);
			}
		}else {
			System.out.println("We have 1 winning player(s): " + "Player " + winner(playersNames, playerscores));
			for (int x = others.size() - 1; x >= 0; x--) {
				System.out.println("Player " + winner(playersNames, others) + ", " + others.get(x).toString());
				others.remove(others.size() - 1);
			}
		}
		//for (int i = 8; i <)
	}

	public static String winner(ArrayList<PokerHand> playersNames, ArrayList<PokerHand> playerscores) {
		String winner = "";
		for (int i = playerscores.size() - 1; i >= 0; i --) {
			for(int c = 1; c < 9; c++){
				if (playerscores.get(i).equals(playersNames.get(c))) {
					return winner = "" + c;					
				}
			}
		}
		return "";
	}
}
