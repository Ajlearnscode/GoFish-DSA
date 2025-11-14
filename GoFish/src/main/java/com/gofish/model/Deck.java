package main.java.com.gofish.model;

import java.util.Random;

public class Deck {

  private Stack Card_Deck = new Stack(); //= Attribute Card Deck

  public void shuffle (){ //========>  Method to shuffle card deck
    // Step 1 : Create temporary Stacks for shuffleing
    Stack tempStack_1 = new Stack();
    Stack tempStack_2 = new Stack();
    Stack tempStack_3 = new Stack();

    // Step 2: Create an instance of the random class and relevant variables for randomization of the shuffle
    Random r= new Random();
    int min_iterationLimit = 10; // Inclusive lower bound of iterations 
    int max_iterationLimit = 50; // Inclusive upper bound
    int iterationLimit  =  random.nextInt(max - min + 1) + min; // Generates a random number between the min and max limits for the shuffle loop

    // Step 3: Begin the Shuffle loop
    for (int i=0; i<= iterationLimit; i++){
      //Sub-Step 3.1: Empty the card deck into 3 stacks at random
      while(Card_Deck.isempty()==false){ // Empty the Card deck randomly into other stacks
        Card card = Card_Deck.pop(); // Remove a card from the Deck
        int deck_number = random.nextInt(3) + 1; // Select a temporary deck number at random to put the card
        if(deck_number ==1){
          tempStack_1.push(card); // Put the card in stack 1
        } else if(deck_number == 2){
          tempStack_2.push(card); // Put the card in stack 2
        }else {
          tempStack_3.push(card); // Put the card in stack 3
        }
      }

      // Sub-Step 3.2: Put Cards from stack 1 back on the Deck 
      while(tempStack_1.isempty()==false){
        Card card = tempStack_1.pop();// remove card from the temporary deck
        Card_Deck.push(card); // put it back in the main deck
      }

      // Sub-Step 3.3: Put Cards from stack 2 back on the Deck 
      while(tempStack_2.isempty()==false){
        Card card = tempStack_2.pop();// remove card from the temporary deck
        Card_Deck.push(card); // put it back in the main deck
      }

      // Sub-Step 3.4: Put Cards from stack 3 back on the Deck 
      while(tempStack_3.isempty()==false){
        Card card = tempStack_3.pop();// remove card from the temporary deck
        Card_Deck.push(card); // put it back in the main deck
      }  
    } // End of Shuffle For loop
      
    
  }


}
