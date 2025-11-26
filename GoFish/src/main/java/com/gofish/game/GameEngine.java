package main.java.com.gofish.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.model.BotPlayer;
import main.java.com.gofish.model.Card;
import main.java.com.gofish.model.Deck;
import main.java.com.gofish.model.HumanPlayer;
import main.java.com.gofish.model.Player;

public class GameEngine {

	public static void main(String[] args) {

		//welcome(); -->function that display the group members names 
		//gameRules(); --> Call the function that displays the games rules
		
		//Card card = new Card();
		//card.initializeCards();
		//card.showStack();
		//gameMenu();
		//Deck cardDeck = new Deck();
		//cardDeck.shuffle();
		
		//Debugging 2: Checking if users are created and returned
		GameEngine.startGame();
		

		
	}
	
	public static void gameMenu() {
		System.out.println("********Go fish Game********");
		gameRules();
	}
	
	public static void gameRules() {
		System.out.println("Game Rules");
	}
	
	//only allows human players for now
	/*public static void startGame() {
		Scanner scan = new Scanner(System.in); //instantiating our scanner object
		
		//Instantiating a new ArrayList that contains the players of the game;
		ArrayList<HumanPlayer> players = new ArrayList<>(); //key value pairs used to track the player and their scores
		HumanPlayer player = new HumanPlayer();
		
		System.out.print("\nHow many players (2 - 4): ");
		int numPlayers = scan.nextInt();
		//new Scanner(System.in);
		
		while(numPlayers < 2 || numPlayers > 4) { 
			System.out.println("Invalid number of players. (2 - 4) players only.\nTyr again: ");
			numPlayers = scan.nextInt();
		}
		
		for(int i = 1; i <= numPlayers; i++) {
			
			players.add(new HumanPlayer(player.createPlayer()));
		}
		
		System.out.print(players.toString());
	}*/
	
	//only allows human players for now
		public static void startGame() {
			Scanner scan = new Scanner(System.in); //instantiating our scanner object
			HumanPlayer player = new HumanPlayer();
			BotPlayer botplayer = new BotPlayer();
			int numPlayers = 0;
			
			//Instantiating a new ArrayList that contains the players of the game;
			ArrayList<HumanPlayer> players = new ArrayList<>(); 
			
			//Instantiating a new ArrayList that contains the players of the game;
			ArrayList<BotPlayer> botPlayers = new ArrayList<>(); 
			
			
			//get the number of players and their type
			System.out.print("\nHow many players (2 - 4): ");
			 numPlayers = scan.nextInt();
			 scan.nextLine();
			
			//Validation Check
			while(numPlayers < 2 || numPlayers > 4) { 
				System.out.println("\nInvalid number of players. (2 - 4) players only.\nTry again: ");
				numPlayers = scan.nextInt();
				scan.nextLine();
			}//end of validation
			
			for (int i = 1; i <= numPlayers; i++) {
				
				System.out.print("\nIs player " + i + " a human? (Y/N): ");
				String ans = scan.nextLine().trim().toLowerCase();
				;
				
				//Validation Check
				while(!ans.trim().equals("y") && !ans.trim().equals("n") ) { 
					System.out.println("\nInvalid choice. (Y/y - yes, N/n - No)\nTry again: ");
					ans = scan.nextLine().trim().toLowerCase();
					
				}//end of validation
				
				switch(ans){
				case "y": 
					players.add(new HumanPlayer(player.createPlayer()));
					break;
				case "n": 
					System.out.println("\nBOT LOGIC NOT YET READY");
					return;
				}
				
			}
			
			//Debugging Console
			System.out.print("Game Players" + players.toString());
			
			
			//Deck Implementation
			Deck cardDeck = new Deck(); //initialize the deck of cards that will be used
			cardDeck.shuffle(); //shuffle the deck of cards
			LinkedList<Card> hand = new LinkedList<>();
			
			if(numPlayers == 2) {
				//deal 7 cards per player
				for( HumanPlayer hPlayer: players) {
					
					for(int i = 0; i <= 6; i++) {
						Card dealtCard = cardDeck.drawCard();   // pop from deck
						hPlayer.addCard(dealtCard);
					}
					System.out.println(hPlayer.getHand().toString());
				}

			}else if(numPlayers > 2 && numPlayers <= 4) {
				//deal 5 cards each
				for( HumanPlayer hPlayer: players) {
					for(int i = 0; i <= 4; i++) {
						Card dealtCard = cardDeck.drawCard();   // pop from deck
						hPlayer.addCard(dealtCard);
					}
					System.out.println(hPlayer.getHand().toString());
				}
			}
			//we pop from the deck and we store that card into the players hand.
			
			
			
		}
	
	
}
