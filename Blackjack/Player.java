package Blackjack;

import java.util.ArrayList;
import java.util.List;

/*contains methods and variables related to blackjack player 
 * used for both dealer and players
 * Holds card value
 * @TODO fix ACE to have both 1 and 11
 */

public class Player {
	List<Card> userHand = new ArrayList<Card>();
	int usersScore=0;
	

	public List<Card> getUserHand() {
		return userHand;
	}

	public void setUserHand(List<Card> userHand) {
		this.userHand = userHand;
	}

	public int getUsersScore() {
		return usersScore;
	}

	public void setUsersScore(int usersScore) {
		this.usersScore = usersScore;
	}
	public void addUserScore(int money) {
		this.setUsersScore(this.getUsersScore()+money);
	}
	
	public void subUserScore(int money) {
		this.setUsersScore(this.getUsersScore()-money);
	}
	
	public void printHand(){										//Print User Hand
		System.out.println(userHand);
	}
	
	//Can be cleaned?
	public int findValueofHand(){					//Return User Score
		int sum = 0;
		for(int i=0; i<this.userHand.size(); i++){
			if(this.userHand.get(i).rank.equals("Ace")) {
				
			}
			sum += assignValue(this.userHand.get(i));
		//	System.out.println(hand.get(i).toString());
		}		
		return sum;
	}
	
	
	
	
	public void hit(Deck mainDeck, int itr){												//Add Card to UserHand
		System.out.print("Dealer Dealt ");
		System.out.println(mainDeck.getDeckofcards().get(itr));
		userHand.add(mainDeck.getDeckofcards().get(itr));
		itr++;
		mainDeck.setPositonOfDeck(itr);
	}
	
	//@@ MAKE FOR 2 hards
	public void dealCards(Deck mainDeck, int itr){									//Add 2 card from deck to user hand at itr to start round
		userHand.add(mainDeck.getDeckofcards().get(itr));
		itr++;
		mainDeck.setPositonOfDeck(itr);
	}

	public void clearHand(){
		userHand.clear();
	}
	
	public int assignValue(Card acard){
		int value;
		switch (acard.rank) {
	    case "2": 	value = 2;
	    			break;
	    case "3":	value = 3;
	    			break;
	    case "4":	value = 4;
	                break;
	    case "5": 	value = 5;
	    			break;
	    case "6": 	value = 6;
					break;
	    case "7": 	value = 7;
					break;
	    case "8": 	value = 8;
					break;
	    case "9": 	value = 9;
					break;
	    case "10": 	value = 10;
	    			break;
	    case "Jack": value = 10;
					break;
	    case "Queen": value = 10;
					break;
	    case "King": value =10;
					break;
	    case "Ace": value = 1;
					break;
	    default: 	value = 0;
	                break;
	}
		return value;
	}


}
