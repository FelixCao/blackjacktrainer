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



/* FEATURE BACKLOG
 * 
 * ACE Handling
 * Doubling Down
 * Insurance
 * Unit Testing
 * 
 */

//import java.util.ArrayList;
//import java.util.List;

import java.util.Scanner;

public class BlackJackGame {						
	public Player dealer = new Player();
	public Player player1 = new Player();
	public boolean continueGame =  true;
	public  Deck gameDeck = new Deck();
	public int bet=0;
	private int StartingMoney = 1000;
	
	public BlackJackGame(){
	//Initiate Black Jack Game
	//Allow user input for Setup
	// Create dealer and player
	}
	
	
	//TODO: Create Settings Options
	public void initGame() {
		System.out.println("Welcome to Felix's BlackGame V0.3");
		this.player1.setUsersScore(StartingMoney);
	}
	
	
	public void play() throws InterruptedException {
		Scanner read = new Scanner(System.in);
		gameDeck.shuffleDeck();
		//gameDeck.printDeck();
		String nextAction = null;
		
		while(player1.getUsersScore() > 0 && continueGame){ //While Player wants to play && has positive money
			//TODO: Make bet
			System.out.println("You have " + this.player1.getUsersScore()+ " dollars");
			System.out.println("How much do you want you want to bet?:");
			bet = read.nextInt();
			this.player1.subUserScore(bet);
			
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
			
			
			//Play till fold or 21 or bust
			while(player1.findValueofHand() < 21) {
				System.out.println("(H)it or (F)old?");
				//System.out.println(player1.findValueofHand());				
				player1.printHand();	
				//print card
				nextAction = read.next().toLowerCase();
				System.out.println("............");
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
				player1.printHand();
			}
	
			else if(player1.findValueofHand() <= 21){
						System.out.println("Dealer has to beat " + player1.findValueofHand());
						System.out.print("Dealer has ");
						dealer.printHand();  //Dealer Flips over face down Card
			}
			
			//Dealer plays
			while(dealer.findValueofHand() < 17 && player1.findValueofHand() <= 21) {
			//	Thread.sleep(4000);
				System.out.println("");
				dealer.hit(gameDeck, gameDeck.getPositonOfDeck());
				dealer.printHand();
				System.out.println(dealer.findValueofHand());
			}
			
			//TODO: Money Awarded or Taken
			this.decideWinner(dealer.findValueofHand(), player1.findValueofHand());//Decide Winner
			
			
			
			player1.clearHand();
			dealer.clearHand();
			System.out.println("Continue Game? (y/n)");
			System.out.println("=============");
			nextAction = read.next().toLowerCase();
			if(nextAction.startsWith("y")) {
				continueGame = true;
			}
		
			else 
				continueGame = false;
			
		}
	System.out.println("GG");
	System.out.println("You ended with: "+ player1.usersScore + " dollars");
	read.close();
	}
	
	
	
	
	
	public void decideWinner(int dealerScore, int playerScore) {
		if(playerScore >21) {
			System.out.println("You lose this round");
		}
		else if(dealerScore >21 ) {
			System.out.println("You win this round");
			this.player1.addUserScore(bet*2);
		}
			
		else if(playerScore>dealerScore) {
			System.out.println("You win this round");
			this.player1.addUserScore(bet*2);
		}
		else if(playerScore < dealerScore) {
			System.out.println("You lose this round");
		}
		else {
			System.out.println("You tie this round");
			this.player1.addUserScore(bet);
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException
	{
		BlackJackGame bj = new BlackJackGame();
		bj.initGame();
		bj.play();
	}
	
}
