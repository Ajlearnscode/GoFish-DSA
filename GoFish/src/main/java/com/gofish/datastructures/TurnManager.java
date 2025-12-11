/*Authors: 
 * Asher Maxwell - 2107584
 * Jhamar Brown - 2209430
 * Tamara Hernandez - 1505635
 * Monique Whittaker - 2104569
 * 
 * Project Title: Go Fish Game
 * Date: December 13, 2025
 * Occurrence: UM1
 * */

package main.java.com.gofish.datastructures;

import main.java.com.gofish.model.HumanPlayer;
import main.java.com.gofish.model.Player;

//import main.java.com.gofish.datastructures.Node;

public class TurnManager<T> {
    private Node<T> head;
   // private Node<T> currentPlayer; // Tracks current player for turns
    
    // Default Constructor
    public TurnManager() {
        this.head = null;
       // this.currentPlayer = null;
    }
    
    // Primary Constructor
    public TurnManager(Node<T> head) {
        this.head = head;
        //this.currentPlayer = null;
    }
    
    // Getters and Setters
    public Node<T> getHead() {
        return head;
    }
    
    public void setHead(Node<T> head) {
        this.head = head;
    }
    
    
    public T setCurrentPlayerToFirst(Node<T> currentPlayer) {
        if (head == null) {
            System.err.println("Cannot set current player: List is empty!");
            currentPlayer = null;
            return null;
        }
        
        currentPlayer = head;
        System.err.println("\n\nCurrent player set to first player " + currentPlayer.getData().toString());
        return currentPlayer.getData();
    }
    

    public T nextPlayer(Node<T> currentPlayer) {
        if (head == null) {
            System.err.println("Cannot move to next player: List is empty!");
            return null;
        }
        
        if (currentPlayer == null) {
            // If currentPlayer hasn't been initialized, start at head
            System.err.println("Current player was null, starting at first player.");
            currentPlayer = head;
            return currentPlayer.getData();
        }
        
        // Move to next player
        if (currentPlayer.getNext() != null) {
            // There's a next player in the list
            currentPlayer = currentPlayer.getNext();
        } else {
            // We're at the last player, wrap around to the first player
            currentPlayer = head;
            System.out.println("Turn wrapped around to first player.");
        }
        
        return currentPlayer.getData();
    }

    
    public T getCurrentPlayer(Node<T> currentPlayer) {
    	
        if (currentPlayer == null) {
            System.err.println("Current player has not been set! Call setCurrentPlayerToFirst() first.");
            return null;
        }
        return currentPlayer.getData();
    }
    
    public T getCurrentPlayerTruth(Node<T> currentPlayer) {
    	
        if (currentPlayer == null) {
            System.err.println("Current player has not been set! Call setCurrentPlayerToFirst() first.");
            return null;
        }
        

        return currentPlayer.getData();
    }
    

    public boolean hasCurrentPlayer(LinkedList<HumanPlayer> currentPlayer) {
    	
        return currentPlayer != null;
    }
    

    public void resetCurrentPlayer(LinkedList<HumanPlayer> currentPlayer) {
    	
        currentPlayer = null;
        System.out.println("Current player has been reset.");
    }
    

    @SuppressWarnings("unchecked")
	public Node<T> getCurrentPlayerNode(Node<HumanPlayer> currentPlayer) {
        return (Node<T>) currentPlayer;
    }
}