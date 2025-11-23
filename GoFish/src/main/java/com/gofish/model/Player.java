package main.java.com.gofish.model;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.datastructures.Node;

public abstract class Player {
	private String name;
	protected LinkedList <Card> hand;
	private int score;
	private boolean isBot;
	
	public Player () {
		name = "";
		hand = new LinkedList<>() ;
		score = 0;
		isBot = false;
	}
	public Player (String name, LinkedList <Card> hand, int score, boolean isBot) {
		this.name = name;
		this.hand = hand;
		this.score = score;
		this.isBot = isBot;
	}
	public Player(Player obj) {
	    this.name = obj.name;
	    this.score = obj.score;
	    this.isBot = obj.isBot;
	    
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Card> getHand() {
		return hand;
	}
	public void setHand(LinkedList<Card> hand) {
		this.hand = hand;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isBot() {
		return isBot;
	}
	public void setBot(boolean isBot) {
		this.isBot = isBot;
	}
	
	public LinkedList<Card> removeCard(String rank) {
	    LinkedList<Card> removedCards = new LinkedList<>();
	    
	    if (!hand.isEmpty()) {
	        Node <Card> curr = hand.getHead(); //changed from just Node
	        Node <Card> prev = null;  //changed from just Node
	        
	        while (curr != null) {
	            if (curr.getData().getRank().equals(rank)) {
	                // Found a matching card
	                removedCards.insertAtRear(curr.getData()); // capture it
	                
	                if (prev == null) { 
	                    // Deleting head node
	                    hand.deleteFromFront();
	                    curr = hand.getHead();
	                } else {
	                    // Deleting middle/end node
	                    prev.setNext(curr.getNext());
	                    curr = curr.getNext();
	                }
	            } else {
	                prev = curr;
	                curr = curr.getNext();
	            }
	        }
	    }
	    
	    return removedCards;
	}
	//So this removes ALL cards of the specified rank, Returns them so they can be given to opponent, Handles head deletion and middle/end deletion, follows the way sir does the delete doesnt do it exactly like when it find 1 card it break it finds all matches and keeps looping like in the game when smb ask for a sepcific card rank you need to give 
	//them all of them. a call like this "LinkedList<Card> removed = removeCard("7");" would go in a game class to call this
	//also need deletefromfront and insertatRear methods in the linkedlist class to work
	
	//what it said about doing it like this:
	//"In your removeCard() method, you're inside the Player class, not inside the LinkedList class. So you can't directly access hand.head to do head = head.getNextNode()"
	//"Head deletion: Use hand.deleteFromFront() (method call) Middle/End deletion: Use prev.setNextNode(curr.getNextNode()) (teacher's direct style)"
	public abstract String chooseRank();
}
