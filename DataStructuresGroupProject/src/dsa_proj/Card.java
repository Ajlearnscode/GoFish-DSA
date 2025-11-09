package dsa_proj;

public class Card {
	//Attributes
	private String rank;
	private String suit;
	private String colour;
	
	//Default 
	public Card() {
		this.rank = "?";
		this.suit = "?";
		this.colour = "?";
	}
	
	//primary
	public Card(String rank, String suit, String colour) {
		this.rank = rank;
		this.suit = suit;
		this.colour = colour;
	}
	
	//Copy
	public Card(Card card) {
		this.rank = card.rank;
		this.suit = card.suit;
		this.colour = card.colour;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return this.suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getColour() {
		return this.colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	
	public String toString() {
		return "Rank: " + rank + 
				"\nSuit: " + suit +
				"\nColour: " + colour + "\n";
	}
	
	
	
}



/*
 * Original Info - User
 * Name: Asher
 * Email: ashermaxwell6@gmail.com
 * Password: 12345
 * 
 * Updated Info - User 
 * 
 * Name: Asher
 * Email: ashermaxwell6@gmail.com
 * Password: love
 * 
 * */
