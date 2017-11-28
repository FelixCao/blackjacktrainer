package Blackjack;
/*
 * Goals:
 * Create a BJ game that allows corrects for wrong move and counts cards
 * Reason:
 * To show my development as a programmer. Over time I will improve the code as i learn better programming practices
 * 
 * Rules
 * https://wizardofodds.com/games/blackjack/basics/
 * http://www.bicyclecards.com/how-to-play/blackjack/
 * contains the rules of the game
 * and overall flow of the game
 * How money flows and is bet
 * need option for card in play and outof play control
 */

//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class BlackJackGame {
	//(Use Dictionary for setting)?
	
	
	public BlackJackGame(){
	//Initiate Black Jack Game
	//Allow user input for Setup
	// Create dealer and player
	}
	

	public static void main(String[] args){
		Deck gameDeck = new Deck();
		System.out.println("Welcome");
	//	BlackJackGame bjGame = new BlackJackGame();
		Scanner read = new Scanner(System.in);
		Player dealer = new Player();
		Player player1 = new Player();
		gameDeck.shuffleDeck();
		//gameDeck.printDeck();
		System.out.println("Welcome to Felix's BlackGame V0.3");

		//While Player wants to play && has positive money
			//Make bet
		player1.dealCards(gameDeck, gameDeck.getPositonOfDeck());
		player1.dealCards(gameDeck, gameDeck.getPositonOfDeck());
		dealer.dealCards(gameDeck, gameDeck.getPositonOfDeck());
		dealer.dealCards(gameDeck, gameDeck.getPositonOfDeck());

		System.out.println("=============Player Hand=============");
		player1.printHand();
		System.out.println();
		System.out.println("=============Dealer Hand=============");
		System.out.println(dealer.getUserHand().get(0) + ", FACEDOWN CARD");
		System.out.println();
		
		String nextAction = null;

		//Play till fold or 21 or bust
		while(player1.findValueofHand() < 21) {// || !nextAction.equals("f")) {
			System.out.println("(H)it or (F)old?");
			System.out.println(player1.findValueofHand());				
			player1.printHand();
			//print card
			nextAction = read.nextLine();
			nextAction.toLowerCase();
			if(nextAction.startsWith("h"))
				player1.hit(gameDeck, gameDeck.getPositonOfDeck());
			else if(nextAction.startsWith("f"))
					break;
			else 
				System.out.println("Please Enter Correct Value");
		}
		if (player1.findValueofHand() > 21) {
			System.out.println("Bust");
			System.out.println(player1.findValueofHand());
			player1.printHand();}

		else if(player1.findValueofHand() <= 21){
					System.out.println("Dealer has to beat " + player1.findValueofHand());
					System.out.print("Dealer has " );
					//Dealer Flips over facedown Card
					dealer.printHand(); 
		}
		
		//Dealer plays
		while(dealer.findValueofHand() < 17) {
			System.out.println("............");
			dealer.hit(gameDeck, gameDeck.getPositonOfDeck());
			dealer.printHand();
			System.out.println(dealer.findValueofHand());
		}
		
		//Decide Winner
		// Change money
		
		read.close();
	}
}