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
/*****************************************************************AI*******************************************************************/
			//Start game loop
			playGame(players, cardDeck, currGamePlayers, scan);
			
			scan.close();
			
		}
	//start main game loop
	public static void playGame(ArrayList<HumanPlayer> players, Deck<Card> cardDeck, LinkedList<HumanPlayer> currGamePlayers, Scanner scan) {
		Node<HumanPlayer> currentPlayerNode = currGamePlayers.getHead();
		boolean gameOver = false;
		int totalBooksCollected = 0;
		
		System.out.println("\n========== GAME START ==========\n");
		
		//Loops until gameOver flag is true
		//Gets current player from the current node
		//Displays whose turn it is
		while(!gameOver) {
			HumanPlayer currentPlayer = currentPlayerNode.getData();
			
			System.out.println("\n" + "=".repeat(60));
			System.out.println("Current Turn: " + currentPlayer.getName());
			System.out.println("=".repeat(60));
			
			//Parts of the player turn logic
			// Check if player's hand is empty - draw 4 cards if deck has cards
			// in the istruction it says Whenever a player’s hand is empty, they can draw four
			//(4) additional cards from the deck until it is empty.
			if(currentPlayer.getHand().isEmpty() && !cardDeck.isEmpty()) {
				System.out.println(currentPlayer.getName() + "'s hand is empty! Drawing 4 cards...");
				for(int i = 0; i < 4 && !cardDeck.isEmpty(); i++) {
					currentPlayer.addCard(cardDeck.drawCard());
				}
			}
			
			// If hand is still empty after trying to draw, skip turn
			if(currentPlayer.getHand().isEmpty()) {
				System.out.println(currentPlayer.getName() + " has no cards and deck is empty. Skipping turn.");
				currentPlayerNode = (currentPlayerNode.getNext() != null) ? currentPlayerNode.getNext() : currGamePlayers.getHead();
				continue;
			}
			
			// Show current player's hand
			showPlayerHand(currentPlayer);
			cardsLeft(cardDeck);
			
			// Check for books before asking
			int booksFound = checkForBooks(currentPlayer);
			totalBooksCollected += booksFound;
			
			// Check if game is over (all 13 books collected)
			if(totalBooksCollected >= 13) { 
				gameOver = true;
				break;
			}
			
			// Ask for card part of flowchart
			boolean keepTurn = askForCard(currentPlayer, players, cardDeck, scan);
			
			// Check for books after getting cards
			booksFound = checkForBooks(currentPlayer);
			totalBooksCollected += booksFound;
			
			// Check if game is over
			if(totalBooksCollected >= 13 || (cardDeck.isEmpty() && allHandsEmpty(players))) {
				gameOver = true;
				break;
			}
			//turn logic
			// Move to next player only if current player didn't get the card they asked for
			if(!keepTurn) {
				currentPlayerNode = (currentPlayerNode.getNext() != null) ? currentPlayerNode.getNext() : currGamePlayers.getHead();
			} else {
				System.out.println("\n" + currentPlayer.getName() + " gets another turn!");
			}
			
			System.out.println("\nPress Enter to continue...");
			scan.nextLine();
		}
		
		// GAME OVER AND SHOWS RESULTS
		displayResults(players);
	}
	
	//Handles asking for cards from other players
	public static boolean askForCard(HumanPlayer currentPlayer, ArrayList<HumanPlayer> playerList, Deck<Card> cardDeck, Scanner scan) {
		// Display available players to ask (excluding current player)
		System.out.println("\nAvailable players to ask:");
		HashMap<Integer, HumanPlayer> playerMap = new HashMap<>();
		int index = 1;  //changed to 1 so it stop ask for player 0
		
		for(HumanPlayer player : playerList) {
			if(!player.getName().equals(currentPlayer.getName())) {
				System.out.println(index + ". " + player.getName() + " [Score: " + player.getScore() + "]");
				playerMap.put(index, player);
				index++;
			}
		}
		
		// Get player choice with better input handling
		int playerChoice = -1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.print("\nSelect player to ask (enter number): ");
			try {
				if(scan.hasNextInt()) {
					playerChoice = scan.nextInt();
					scan.nextLine(); // consume newline
					
					if(playerMap.containsKey(playerChoice)) {
						validInput = true;
					} else {
						System.out.println("Invalid choice! Please select from the list above.");
					}
				} else {
					scan.nextLine(); // consume invalid input
					System.out.println("Please enter a valid number!");
				}
			} catch(Exception e) {
				scan.nextLine(); // clear buffer
				System.out.println("Please enter a valid number!");
			}
		}
		
		HumanPlayer askedPlayer = playerMap.get(playerChoice);
		
		// Get rank to ask for (must be in current player's hand)
		System.out.print("\nWhat rank do you want to ask for? ");
		String rankToAsk = scan.nextLine().trim().toUpperCase();
		
		// Validation - rank must be in current player's hand
		while(!hasRank(currentPlayer, rankToAsk)) {
			System.out.print("You don't have that rank! Choose a rank from your hand: ");
			rankToAsk = scan.nextLine().trim().toUpperCase();
		}
		
		System.out.println("\n" + currentPlayer.getName() + " asks " + askedPlayer.getName() + " for: " + rankToAsk);
		
		// Check if asked player has the rank
		if(hasRank(askedPlayer, rankToAsk)) {
			// Transfer all cards of that rank
			LinkedList<Card> cardsToTransfer = askedPlayer.removeCard(rankToAsk);
			int cardCount = countCards(cardsToTransfer);
			
			System.out.println("Yes: " + askedPlayer.getName() + " has " + cardCount + " card(s) of rank " + rankToAsk + "!");
			
			// Add cards to current player's hand
			Node<Card> curr = cardsToTransfer.getHead();
			while(curr != null) {
				currentPlayer.addCard(curr.getData());
				curr = curr.getNext();
			}
			
			System.out.println(currentPlayer.getName() + " received the cards!");
			return true; // Player gets another turn
			
		} else {
			// Go Fish!
			System.out.println("-> " + askedPlayer.getName() + " says: GO FISH!");
			
			if(!cardDeck.isEmpty()) {
				Card drawnCard = cardDeck.drawCard();
				currentPlayer.addCard(drawnCard);
				System.out.println(currentPlayer.getName() + " drew a card from the deck.");
				
				// Check if drawn card matches the rank asked for
				if(drawnCard.getRank().equals(rankToAsk)) {
					System.out.println("Lucky! You drew the " + rankToAsk + " you asked for! You get another turn!");
					return true; // Player gets another turn
				}
			} else {
				System.out.println("Deck is empty! No card to draw.");
			}
			
			return false; // Turn ends
		}
	}
	//helper method - Validate player has a rank before asking for it
	public static boolean hasRank(HumanPlayer player, String rank) {
		if(player.getHand().isEmpty()) {
			return false;
		}
		
		Node<Card> curr = player.getHand().getHead();
		while(curr != null) {
			if(curr.getData().getRank().equalsIgnoreCase(rank)) {
				return true;
			}
			curr = curr.getNext();
		}
		return false;
	}
	//does 3 things from the flowchart the check for set/books, removes the set from hand (ive checked it works) and award a point 
	public static int checkForBooks(HumanPlayer player) {
		String[] allRanks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		int booksFound = 0;
		
		for(String rank : allRanks) {
			int count = countRankInHand(player, rank);
			
			if(count == 4) {
				// Found a book! Remove all 4 cards
				player.removeCard(rank);
				player.setScore(player.getScore() + 1);
				booksFound++;
				System.out.println("\n " + player.getName() + " collected a BOOK of " + rank + "s! [Score: " + player.getScore() + "]");
			}
		}
		
		return booksFound;
	}
	//Counts how many cards match the given rank part of check for sets logic
	public static int countRankInHand(HumanPlayer player, String rank) {
		int count = 0;
		Node<Card> curr = player.getHand().getHead();
		
		while(curr != null) {
			if(curr.getData().getRank().equalsIgnoreCase(rank)) {
				count++;
			}
			curr = curr.getNext();
		}
		
		return count;
	}
	//helper method count how many cards in a link list 
	public static int countCards(LinkedList<Card> cardList) {
		int count = 0;
		Node<Card> curr = cardList.getHead();
		
		while(curr != null) {
			count++;
			curr = curr.getNext();
		}
		
		return count;
	}
	//gameover check uses arraylist iteration
	public static boolean allHandsEmpty(ArrayList<HumanPlayer> players) {
		for(HumanPlayer player : players) {
			if(!player.getHand().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	//helper method Display current player hand
	public static void showPlayerHand(HumanPlayer player) {
		System.out.println("\n" + player.getName() + "'s Hand:");
		if(player.getHand().isEmpty()) {
			System.out.println("  [Empty]");
		} else {
			Node<Card> curr = player.getHand().getHead();
			System.out.print("  ");
			while(curr != null) {
				System.out.print(curr.getData());
				curr = curr.getNext();
			}
			System.out.println();
		}
	}
	
	public static void displayResults(ArrayList<HumanPlayer> players) {
		System.out.println("\n" + "=".repeat(60));
		System.out.println("GAME OVER!");
		System.out.println("=".repeat(60));
		
		// Find winner
		HumanPlayer winner = players.get(0);
		for(HumanPlayer player : players) {
			System.out.println(player.getName() + " - Score: " + player.getScore() + " books");
			if(player.getScore() > winner.getScore()) {
				winner = player;
			}
		}
		
		System.out.println("\n WINNER: " + winner.getName() + " with " + winner.getScore() + " books!");
	}
	//heloer keeps updating when card get drawn as you will see
	public static void cardsLeft(Deck<Card> cardDeck) {
		System.out.println("Cards remaining in deck: " + cardDeck.cardsRemaining());
	}
	//shows playernum name and initial score
	public static void playerInfo(HumanPlayer hPlayer, int count) {
		System.out.println("\n[Player " + count + "]: " + hPlayer.getName() + " [Score: " + hPlayer.getScore() + "]");
	}
}
	
