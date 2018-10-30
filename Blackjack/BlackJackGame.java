package Blackjack;
/*
 * Goals:
 * Create a blackjack game that allows corrects for wrong move and counts cards
 * Reason:
 * To show my development as a programmer. Over time I will improve the code as i learn better programming practices
 * 
 * Rules
 * https://wizardofodds.com/games/blackjack/basics/
 * http://www.bicyclecards.com/how-to-play/blackjack/
 * contains the rules of the game
 * and overall flow of the game
 * How money flows and is bet

 * http://www.online-casinos.com/blackjack/blackjack_chart.asp
 */



/* FEATURE BACKLOG 
 * 	Natural Aces
 *  Doubling on Zero
 *  Always can double
 * 	Splitting Pairs
 *  Code Polish
 * 	Insurance
 *  Pretty Up I/O
 *  Add Chart
 *  card counting
 * 	Unit Testing
 *  Play statistics
 *  Auto play
 *  settings
 *  Smart Shuffling
 *  need option for card in play and outof play control
 * 
 * FINISHED FEATURES
 *  Ace Handling
 * 	Doubling Down
 * 	Itr at 45 needs shuffle
 */


import java.util.Scanner;

public class BlackJackGame {						
	public Player dealer = new Player();
	public Player player1 = new Player();
	public boolean continueGame =  true;
	public boolean continueRound = true;
	public  Deck gameDeck = new Deck();
	public int bet=0;
	private int StartingMoney = 200;
	
	public BlackJackGame(){
		int numberOfDecks;
		
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
			System.out.println(gameDeck.positonOfDeck);
			if(gameDeck.getPositonOfDeck()>40) {
				gameDeck.shuffleDeck();
				gameDeck.setPositonOfDeck(0);
				System.out.println("Shuffled ");
			}
			
			//Make bet
			continueRound = true;
			System.out.println("You have " + this.player1.getUsersScore()+ " dollars");
			System.out.println("How much do you want you want to bet?:");
			//TODO Error catching for int?
			bet = read.nextInt();
			while(bet>this.player1.getUsersScore()) {
				System.out.println("Not enough Money, reenter value");
				bet = read.nextInt();
			}
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
			
			
			//Play till fold or 21 or bustcontinueRound
			while(player1.findValueofHand() < 21 && continueRound) {
				System.out.println("(H)it or (F)old? or (D)ouble");
				//System.out.println(player1.findValueofHand());				
				player1.printHand();	
				//print card
				nextAction = read.next().toLowerCase();
				System.out.println("............");
				if(nextAction.startsWith("h"))
					player1.hit(gameDeck, gameDeck.getPositonOfDeck());
				else if(nextAction.startsWith("d")) {
					player1.hit(gameDeck, gameDeck.getPositonOfDeck());
					this.player1.subUserScore(bet);
					bet += bet;
					continueRound = false;
				}
				else if(nextAction.startsWith("f"))
					continueRound = false;
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
				System.out.println("");
				dealer.hit(gameDeck, gameDeck.getPositonOfDeck());
				dealer.printHand();
				System.out.println(dealer.findValueofHand());
			}
			
			//TODO: Money Awarded or Taken
			this.decideWinner(dealer.findValueofHand(), player1.findValueofHand());//Decide Winner
			
			
			
			player1.clearHand();
			dealer.clearHand();
			continueGame = decideContinue(read);
		}
	System.out.println("GG");
	System.out.println("You ended with: "+ player1.usersScore + " dollars");
	read.close();
	}
	
	
	public boolean decideContinue(Scanner read) {
		System.out.println("Continue Game? (y/n)");
		System.out.println("=============");
		String nextAction = read.next().toLowerCase();
		if(nextAction.startsWith("y")) {
			return  true;
		}
		else if(nextAction.startsWith("n"))
			return  false;
		else {
			System.out.println("Enter Correct Value");
			return decideContinue(read);
		}
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
