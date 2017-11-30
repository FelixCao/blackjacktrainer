package Blackjack;
/*
 * Class for a single Card
 */
public class Card {
	String suit;
	String rank;
	
	public Card(String cardRank, String cardSuit){
	this.rank = cardRank;
	this.suit = cardSuit;
	}
	public String toString() {
		return this.getRank() +" of "+ this.getSuit();
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	

}
