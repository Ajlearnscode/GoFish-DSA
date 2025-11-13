package ds;

public class Card {

	private String rank;
    private String suit;
    private String colour;
    
    // Default constructor
    public Card() {
        this.rank = "";
        this.suit = "";
        this.colour = "";
    }
    
    // Parameterized constructor
    public Card(String rank, String suit, String colour) {
        this.rank = rank;
        this.suit = suit;
        this.colour = colour;
    }
    
    // Copy constructor
    public Card(Card other) {
        this.rank = other.rank;
        this.suit = other.suit;
        this.colour = other.colour;
    }
    
    // Getters
    public String getRank() {
        return rank;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getColour() {
        return colour;
    }
    
    // Setters
    public void setRank(String rank) {
        this.rank = rank;
    }
    
    public void setSuit(String suit) {
        this.suit = suit;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
}
