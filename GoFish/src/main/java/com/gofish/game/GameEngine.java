package main.java.com.gofish.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.datastructures.Node;
import main.java.com.gofish.datastructures.Stack;
import main.java.com.gofish.datastructures.TurnManager;
import main.java.com.gofish.model.BotPlayer;
import main.java.com.gofish.model.Card;
import main.java.com.gofish.model.Deck;
import main.java.com.gofish.model.HumanPlayer;
import main.java.com.gofish.model.Player;

public class GameEngine {

	private static int count = 1;
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
		
		//Deck.showDeck();

		
	}
	
	public static void gameMenu() {
		System.out.println("********Go fish Game********");
		gameRules();
	}
	
	public static void gameRules() {
		System.out.println("Game Rules");
	}
	
	
	
	//only allows human players for now
		public static void startGame() {
			Scanner scan = new Scanner(System.in); //instantiating our scanner object
			//HumanPlayer player = new HumanPlayer();
			//BotPlayer botplayer = new BotPlayer();
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
					Player newPlayer = new HumanPlayer().createPlayer();
	                players.add((HumanPlayer) newPlayer);
	                break;
				case "n": 
					System.out.println("\nBOT LOGIC NOT YET READY");
					return;
				}
				
			}
			
			//Debugging Console
			//System.out.print("Game Players" + players.toString());
			
			
			//Deck Implementation
			Deck<Card> cardDeck = new Deck<Card>(); //initialize the deck of cards that will be used
			cardDeck.shuffle();
			//We want to check the number of cards before
			
			LinkedList<HumanPlayer> currGamePlayers = new LinkedList<>();
			
			 //creating a linked list object named hand of data type 'Card'
			
			if(numPlayers == 2) { //check if the nu8mber of active players equal to two
				
				//deal 7 cards per player
				for( HumanPlayer hPlayer: players) { //for each for loop 
					currGamePlayers.insertAtRear(hPlayer);
					for(int i = 0; i < 7; i++) { //we basically want to iterate 7 times
						Card dealtCard = cardDeck.drawCard();   // pop from deck
						hPlayer.addCard(dealtCard);
						
					}
					
					playerInfo(hPlayer, count++);
					//hPlayer.getHand().display();  //DISPLAYS THE PLAYER'S HAND
					System.out.println("##############################################################################################\n");
				}

			}else if(numPlayers > 2 && numPlayers <= 4) {
				//deal 5 cards each
				for( HumanPlayer hPlayer: players) {
					currGamePlayers.insertAtRear(hPlayer);
					for(int i = 0; i < 5; i++) {
						Card dealtCard = cardDeck.drawCard();   // pop from deck
						hPlayer.addCard(dealtCard);
					}
					playerInfo(hPlayer, count++);
					//hPlayer.getHand().display(); //DISPLAYS THE PLAYER'S HAND
					
					System.out.println("##############################################################################################\n");
				}
			}
			//we pop from the deck and we store that card into the players hand.
			
			//We want to check the number of cards after
			
			//Debugging Console
			//System.out.print("\nGame Players" + players.toString());
			
			//System.out.println(currGamePlayers.toString()); //list all the players of the game
			TurnManager<HumanPlayer> currPlayer= new TurnManager<HumanPlayer>(); //used to control the Current player at a given point
		
			//We are setting the head of the Turn Manager object 'currPlayer' to be the first player in the gamePlayers list
			currPlayer.setHead(currGamePlayers.getHead());
			//Set the current player to first and display who is the current player
			currPlayer.setCurrentPlayerToFirst(currPlayer.getHead());
			
			cardsLeft(cardDeck);
			
			//ASK FOR CARD
			System.out.println("NEWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
			askForCard(currPlayer, players );
			
			
			
			
			
		}
	
		//NOT WORKING - November 29, 2025: 1:42 am
		public static void askForCard(TurnManager<HumanPlayer> currPlayer, ArrayList<HumanPlayer> playerList ) {
			
			//Current player should ask any player of their choice for a card.
			
			//1. We can display the current game players to be asked, but excluding the current player.
			HashMap<String, Integer> playerMap = new HashMap<>();
			Node<TurnManager<HumanPlayer>> work;
			
			int playerTracker = 0;
			
			//String info = currPlayer.getCurrentPlayer(work);
			//System.out.println(info.toString());
			
			for (HumanPlayer player: playerList) {
				
				playerMap.put(player.getName(), playerTracker);
				
				
				//if(!player.getName().contains(currPlayer.getCurrentPlayerTruth(currPlayer))) {
					//System.out.println(playerMap.getOrDefault(player.getName(), playerTracker));
					System.out.println(playerTracker + " " + player.getName());
				//}
				playerTracker++;
			}
		
			
			//2. We should display the user's hand to let them know the cards they have in their possession.
			//3. Allow the player to ask the select player[index] for a card that they have in their hand ONLY 
				//3.1 make sure that the logic allows only for the card in the current player's hand can be requested
				//3.2 Do validation checking to ensure that if the player enters an invalid card, they will stay in the while loop until....
			//4. Write the logic that allows the asked player to check their hand if they have the card.
				//4.1 If the card is not present --> command the current player to go fish
				//4.2 if the card is present --> pop the cards that match the rank out of their hand ...
				// call checkForSets(); make sure that the logic uses the pop method and any other important methods within its body.
			
			
			
		}
		
		
		public static void cardsLeft(Deck<Card> cardDeck) {
			System.out.println("Remaining Cards: " + cardDeck.cardsRemaining());  
		}
		
		public static void playerInfo(HumanPlayer hPlayer, int count) {
			System.out.println("\n[Player " + count++ + "]: " + hPlayer.getName() + " [Score: " + hPlayer.getScore() + "]\n");
		}
	
}
