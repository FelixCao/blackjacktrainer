package Blackjack;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
 * starting deck for blackjack
 * need option for discard deck
 */
public class Deck {
	
	static String[] SUITS = { "Clubs", "Diamonds", "Hearts", "Spades"};
	static String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	List<Card> deckofcards = new ArrayList<Card>();
	int positonOfDeck = 0;
	
	public Deck(){
		for(int i = 0; i<RANKS.length;i++){
			for(int j = 0; j<SUITS.length; j++){
				Card newlyGeneratedCard = new Card(RANKS[i],SUITS[j]);
				deckofcards.add(newlyGeneratedCard);
			}
		}
	}
	
	public int getPositonOfDeck() {
		return positonOfDeck;
	}
	
	public void setPositonOfDeck(int positonOfDeck) {
		this.positonOfDeck = positonOfDeck;
	}
	
	public List<Card> getDeckofcards() {
		return deckofcards;
	}
	
	public void shuffleDeck(){
		Collections.shuffle(this.getDeckofcards());
	}
	
	public void printDeck(){
		System.out.println(this.getDeckofcards().toString());
	}
	
/*	public static void main(String[] args){
		Deck testdeck = new Deck();
		testdeck.shuffleDeck();
		testdeck.printDeck();
		testdeck.shuffleDeck();
		testdeck.printDeck();
	}*/
}
