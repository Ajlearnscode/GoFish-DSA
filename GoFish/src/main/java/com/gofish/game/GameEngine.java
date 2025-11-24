package main.java.com.gofish.game;

public class GameEngine {

	public static void main(String[] args) {
		
		/* Start Game
		 * 
		 * Set Deck of cards
		 * 
		 * call the shuffle function in the Deck class to randomize the cards
		 * 
		 * Accept the total number of players 
		 * 	--> Check if the player are real or bots
		 * 	
		 *Based on the number of players - we assign cards to each
		 *
		 *	if (players == 2){
		 *	deal 7 cards each
		 *	} else{
		 *
		 *	deal 5 cards each}
		 *
		 *Set current player to player 1\
		 *
		 *currPlayer.chooseRank();
		 *
		 *askedPlayer.checkHand();
		 *
		 * if(askedPlayer.hasCard()){ //only if true
		 * 		askedPlayer.giveCard();
		 * 
		 * 		currPlayer.checkForBooks();
		 * 
		 * 		//for the sets
		 * 		if(has book){
		 * 			currPlayer.removeBook();
		 * 			currPlayer.assignPoints();
		 * 
		 *			checkNumOfBooksMade();
		 *			gameOver();
		 *			player.checkPoints();
		 *			determineWinner();
		 *			playAgain();
		 * 
		 * 
		 * 			currPlayer.continue();
		 *		}else{
		 *			currPlayer = currPlayer.getNext();
		 *		}
		 *resumeGame();
		 *
		 * }else{
		 * 
		 * 	currPlayer.drawCard(); //return card drew
		 * 
		 * 		if(currPlayer.drawCard() makes a set){
		 * 			currPlayer.removeBook();
		 * 			currPlayer.assignPoints();
		 * currPlayer.continue();
		 * 		}else{
					currPlayer = currPlayer.getNext();
		 * 		}
		 * resumeGame();
		 * }
		 *
		 *
		 * 
		 * 
		 * 
		 *
		 * 
		 * 	
		 * 
		 * */
		
	}
	
	public void gameMenu() {
		System.out.println("********Go fish Game********");
		gameRules();
	}
	
	public void gameRules() {
		System.out.println("Game Rules");
	}
}
