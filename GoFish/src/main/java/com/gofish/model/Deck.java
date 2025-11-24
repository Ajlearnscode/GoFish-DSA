package main.java.com.gofish.model;

import java.util.Random;

import main.java.com.gofish.datastructures.Stack;

public class Deck {
	//deck should have a total number of 52 cards
	private Stack <Card> Card_Deck = new Stack<Card>(); //= Attribute Card Deck

	  public void shuffle (){ //========>  Method to shuffle card deck
	    // Step 1 : Create temporary Stacks for shuffling
	    Stack <Card> tempStack_1 = new Stack<>();
	    Stack <Card> tempStack_2 = new Stack<>();
	    Stack <Card> tempStack_3 = new Stack<>();

	    // Step 2: Create an instance of the random class and relevant variables for randomization of the shuffle
	    Random r= new Random();
	    int min = 10; // Inclusive lower bound of iterations 
	    int max = 50; // Inclusive upper bound
	    int iterationLimit  =  r.nextInt(max - min + 1) + min; // Generates a random number between the min and max limits for the shuffle loop

	    // Step 3: Begin the Shuffle loop
	    for (int i=0; i<= iterationLimit; i++){
	      //Sub-Step 3.1: Empty the card deck into 3 stacks at random
	      while(Card_Deck.isEmpty()==false){ // Empty the Card deck randomly into other stacks
	        Card card = Card_Deck.pop(); // Remove a card from the Deck
	        int deck_number = r.nextInt(3) + 1; // Select a temporary deck number at random to put the card
	        if(deck_number ==1){
	          tempStack_1.push(card); // Put the card in stack 1
	        } else if(deck_number == 2){
	          tempStack_2.push(card); // Put the card in stack 2
	        }else {
	          tempStack_3.push(card); // Put the card in stack 3
	        }
	      }

	      // Sub-Step 3.2: Put Cards from stack 1 back on the Deck 
	      while(tempStack_1.isEmpty()==false){
	        Card card = tempStack_1.pop();// remove card from the temporary deck
	        Card_Deck.push(card); // put it back in the main deck
	      }

	      // Sub-Step 3.3: Put Cards from stack 2 back on the Deck 
	      while(tempStack_2.isEmpty()==false){
	        Card card = tempStack_2.pop();// remove card from the temporary deck
	        Card_Deck.push(card); // put it back in the main deck
	      }

	      // Sub-Step 3.4: Put Cards from stack 3 back on the Deck 
	      while(tempStack_3.isEmpty()==false){
	        Card card = tempStack_3.pop();// remove card from the temporary deck
	        Card_Deck.push(card); // put it back in the main deck
	      }  
	    } // End of Shuffle For loop
	      
	    
	  }
	//  Removes and returns the top card from the deck basically a stack pop
	  public Card drawCard() {
		    Card cardToReturn = null;
		    
		    if (!Card_Deck.isEmpty()) {
		        cardToReturn = Card_Deck.pop();  // Remove card from top of deck
		    } else {
		        System.err.println("Error: Deck is empty. Cannot draw a card.");
		    }
		    
		    return cardToReturn;
		} // End of drawCard Method


		// isEmpty() - Checks if the deck is empty
		public boolean isEmpty() {
		    return Card_Deck.isEmpty();  // Uses Stack isEmpty() method
		}

}
