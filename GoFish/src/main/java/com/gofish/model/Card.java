package main.java.com.gofish.model;

import main.java.com.gofish.datastructures.Node;
import main.java.com.gofish.datastructures.Stack;

public class Card {
	// Attributes
	private String rank;
	private String suit;
	private String colour;

	// Default
	public Card() {
		this.rank = "?";
		this.suit = "?";
		this.colour = "?";
	}

	// primary
	public Card(String rank, String suit, String colour) {
		this.rank = rank;
		this.suit = suit;
		this.colour = colour;
	}

	// Copy
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

	@Override
	public String toString() {
		return  rank + " " + suit + " " + colour + ", ";
	}
	
	
	public static Stack<Card> initializeCards() {
		Stack <Card> mainStack = new Stack<>();
		//A
		mainStack.push(new Card( "A",  "♦", "Red"));
		mainStack.push(new Card( "A",  "♣", "Black"));
		mainStack.push(new Card( "A",  "♥", "Red"));
		mainStack.push(new Card( "A",  "♠", "Black"));
		//2s
		mainStack.push(new Card( "2",  "♦", "Red"));
		mainStack.push(new Card( "2",  "♣", "Black"));
		mainStack.push(new Card( "2",  "♥", "Red"));
		mainStack.push(new Card( "2",  "♠", "Black"));
		//3s
		mainStack.push(new Card( "3",  "♦", "Red"));
		mainStack.push(new Card( "3",  "♣", "Black"));
		mainStack.push(new Card( "3",  "♥", "Red"));
		mainStack.push(new Card( "3",  "♠", "Black"));
		//4s
		mainStack.push(new Card( "4",  "♦", "Red"));
		mainStack.push(new Card( "4",  "♣", "Black"));
		mainStack.push(new Card( "4",  "♥", "Red"));
		mainStack.push(new Card( "4",  "♠", "Black"));
		//5s
		mainStack.push(new Card( "5",  "♦", "Red"));
		mainStack.push(new Card( "5",  "♣", "Black"));
		mainStack.push(new Card( "5",  "♥", "Red"));
		mainStack.push(new Card( "5",  "♠", "Black"));
		//6s
		mainStack.push(new Card( "6",  "♦", "Red"));
		mainStack.push(new Card( "6",  "♣", "Black"));
		mainStack.push(new Card( "6",  "♥", "Red"));
		mainStack.push(new Card( "6",  "♠", "Black"));
		//7s
		mainStack.push(new Card( "7",  "♦", "Red"));
		mainStack.push(new Card( "7",  "♣", "Black"));
		mainStack.push(new Card( "7",  "♥", "Red"));
		mainStack.push(new Card( "7",  "♠", "Black"));
		//8s
		mainStack.push(new Card( "8",  "♦", "Red"));
		mainStack.push(new Card( "8",  "♣", "Black"));
		mainStack.push(new Card( "8",  "♥", "Red"));
		mainStack.push(new Card( "8",  "♠", "Black"));
		//9s
		mainStack.push(new Card( "9",  "♦", "Red"));
		mainStack.push(new Card( "9",  "♣", "Black"));
		mainStack.push(new Card( "9",  "♥", "Red"));
		mainStack.push(new Card( "9",  "♠", "Black"));
		//10s
		mainStack.push(new Card( "10",  "♦", "Red"));
		mainStack.push(new Card( "10",  "♣", "Black"));
		mainStack.push(new Card( "10",  "♥", "Red"));
		mainStack.push(new Card( "10",  "♠", "Black"));
		//Js
		mainStack.push(new Card( "J",  "♦", "Red"));
		mainStack.push(new Card( "J",  "♣", "Black"));
		mainStack.push(new Card( "J",  "♥", "Red"));
		mainStack.push(new Card( "J",  "♠", "Black"));
		//Qs
		mainStack.push(new Card( "Q",  "♦", "Red"));
		mainStack.push(new Card( "Q",  "♣", "Black"));
		mainStack.push(new Card( "Q",  "♥", "Red"));
		mainStack.push(new Card( "Q",  "♠", "Black"));
		//Ks
		mainStack.push(new Card( "K",  "♦", "Red"));
		mainStack.push(new Card( "K",  "♣", "Black"));
		mainStack.push(new Card( "K",  "♥", "Red"));
		mainStack.push(new Card( "K",  "♠", "Black"));
		
		return mainStack;
	}
	
	public void showStack() {
		Stack<Card> mainStack = initializeCards();
		
		//for (int i = 0; i <= mainStack.getItemCount(); i++) {
			System.out.println(mainStack);
	//	}
	}

}
