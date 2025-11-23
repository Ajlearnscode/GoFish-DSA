package main.java.com.gofish.game;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.datastructures.Node;
import main.java.com.gofish.model.BotPlayer;
import main.java.com.gofish.model.Card;
import main.java.com.gofish.model.HumanPlayer;
import main.java.com.gofish.model.Player;

public class TestPlayer {
	public static void main(String[] args) {

		System.out.println("=== Testing Player Methods ===\n");

		// Test 1: Create players
		testCreatePlayers();

		// Test 2: Test removeCard()
		testRemoveCard();

		// Test 3: Test HumanPlayer.chooseRank() - requires user input
		// testHumanPlayerChooseRank();

		// Test 4: Test BotPlayer.chooseRank()
		testBotPlayerChooseRank();

		System.out.println("\n=== All Tests Complete ===");
	}

	// Test 1: Create players and verify they work
	public static void testCreatePlayers() {
		System.out.println("TEST 1: Creating Players");
		System.out.println("------------------------");

		HumanPlayer human = new HumanPlayer("Alice");
		BotPlayer bot = new BotPlayer("Computer");

		System.out.println("✓ Created HumanPlayer: " + human.getName());
		System.out.println("✓ Created BotPlayer: " + bot.getName());
		System.out.println("✓ Human is bot? " + human.isBot());
		System.out.println("✓ Bot is bot? " + bot.isBot());
		System.out.println();
	}

	// Test 2: Test removeCard() method
	public static void testRemoveCard() {
		System.out.println("TEST 2: Testing removeCard()");
		System.out.println("-----------------------------");

		// Create a player
		HumanPlayer player = new HumanPlayer("TestPlayer");

		// Add some cards to the hand manually
		Card card1 = new Card("7", "Spades", "Black");
		Card card2 = new Card("King", "Hearts", "Red");
		Card card3 = new Card("7", "Hearts", "Red");
		Card card4 = new Card("Ace", "Clubs", "Black");
		Card card5 = new Card("7", "Diamonds", "Red");

		player.getHand().insertAtRear(card1);
		player.getHand().insertAtRear(card2);
		player.getHand().insertAtRear(card3);
		player.getHand().insertAtRear(card4);
		player.getHand().insertAtRear(card5);

		System.out.println("Initial hand:");
		displayHand(player);

		// Test removing "7s"
		System.out.println("\nRemoving all 7s...");
		LinkedList<Card> removedCards = player.removeCard("7");

		System.out.println("\nRemoved cards:");
		displayCards(removedCards);

		System.out.println("\nHand after removal:");
		displayHand(player);

		// Test removing a rank that doesn't exist
		System.out.println("\nRemoving all 3s (doesn't exist)...");
		LinkedList<Card> noCards = player.removeCard("3");
		System.out.println("Removed " + countCards(noCards) + " cards (should be 0)");

		System.out.println();
	}

	// Test 3: Test HumanPlayer.chooseRank() - commented out because it requires
	// user input
	public static void testHumanPlayerChooseRank() {
		System.out.println("TEST 3: Testing HumanPlayer.chooseRank()");
		System.out.println("-----------------------------------------");

		HumanPlayer human = new HumanPlayer("Alice");

		// Add some cards
		human.getHand().insertAtRear(new Card("7", "Spades", "Black"));
		human.getHand().insertAtRear(new Card("King", "Hearts", "Red"));

		System.out.println("Your hand:");
		displayHand(human);

		// Call chooseRank - this will prompt user for input
		String choice = human.chooseRank();
		System.out.println("You chose: " + choice);
		System.out.println();
	}

	// Test 4: Test BotPlayer.chooseRank()
	public static void testBotPlayerChooseRank() {
		System.out.println("TEST 4: Testing BotPlayer.chooseRank()");
		System.out.println("---------------------------------------");

		BotPlayer bot = new BotPlayer("Computer");

		// Add some cards
		bot.getHand().insertAtRear(new Card("7", "Spades", "Black"));
		bot.getHand().insertAtRear(new Card("King", "Hearts", "Red"));
		bot.getHand().insertAtRear(new Card("Ace", "Clubs", "Black"));
		bot.getHand().insertAtRear(new Card("3", "Diamonds", "Red"));

		System.out.println("Bot's hand:");
		displayHand(bot);

		System.out.println("\nTesting random selection (5 times):");
		for (int i = 1; i <= 5; i++) {
			String choice = bot.chooseRank();
			// chooseRank() already prints, so just add test number
			System.out.println("  Test " + i + " complete");
		}

		System.out.println();
	}

	// Helper method: Display a player's hand
	public static void displayHand(Player player) {
		LinkedList<Card> hand = player.getHand();
		if (hand.isEmpty()) {
			System.out.println("  (empty)");
			return;
		}

		Node<Card> curr = hand.getHead();
		int count = 1;
		while (curr != null) {
			Card card = curr.getData();
			System.out.println(
					"  " + count + ". " + card.getRank() + " of " + card.getSuit() + " (" + card.getColour() + ")");
			curr = curr.getNext();
			count++;
		}
	}

	// Helper method: Display cards in a LinkedList
	public static void displayCards(LinkedList<Card> cards) {
		if (cards.isEmpty()) {
			System.out.println("  (none)");
			return;
		}

		Node<Card> curr = cards.getHead();
		int count = 1;
		while (curr != null) {
			Card card = curr.getData();
			System.out.println(
					"  " + count + ". " + card.getRank() + " of " + card.getSuit() + " (" + card.getColour() + ")");
			curr = curr.getNext();
			count++;
		}
	}

	// Helper method: Count cards in a LinkedList
	public static int countCards(LinkedList<Card> cards) {
		int count = 0;
		Node<Card> curr = cards.getHead();
		while (curr != null) {
			count++;
			curr = curr.getNext();
		}
		return count;

	}
}
