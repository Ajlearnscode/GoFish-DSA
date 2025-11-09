package dsa_proj;

public class Player {

	private Card cards;
	private int books;
	private int playerId;
	
	//Default
	public Player() {
		this.cards = new Card();
		this.books = 0;
		this.playerId = 0;
	}
	
	//Primary
	public Player(Card cards, int books, int playerId) {
		this.cards = cards;
		this.books = books;
		this.playerId = playerId;
	}
	
	//Copy
	public Player(Player p) {
		this.cards = p.cards;
		this.books = p.books;
		this.playerId = p.playerId;
	}

	public Card getCards() {
		return cards;
	}

	public void setCards(Card cards) {
		this.cards = cards;
	}

	public int getBooks() {
		return books;
	}

	public void setBooks(int books) {
		this.books = books;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	
	//one that displays the winning player
	
	public String toStringWinner() {
		return	"Player Id: " + playerId +
				"\nBooks: " + books + "\n";
	}
	
	public String toString() {
		return "Cards: " + cards +
				"\nBooks: " + books +
				"\nPlayer Id: " + playerId + "\n";
	}
	
	//--------------------------METHODS-----------------------------------
	
	//used to distribute the correct number of cards to each player at the start of the game
	public void distributeCards() {
		
		//1..Check if the stack of Cards are empty
	}
	
	//allows the player to draw from the beck of cards when necessary
	public void drawFromDeck() {
		
	}
	
	
}


/*
 * players = p1 p2 p3 p4 
 * 
 * start = p1;
 * 
 * p1.drawFromDeck();
 * 
 * 
 * */
 