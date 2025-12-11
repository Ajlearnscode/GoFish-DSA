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

package main.java.com.gofish.model;

import java.util.Scanner;

import javax.swing.JOptionPane;

import main.java.com.gofish.datastructures.LinkedList;

public class HumanPlayer extends Player {

	//Default
	public HumanPlayer() {
		super();
	}
	
	//primary
	
	public HumanPlayer(String name) {
		super(name, new LinkedList<Card>(), 0, false);
	}
	
	public HumanPlayer (String name, LinkedList <Card> hand, int score, boolean isBot) {
		super(name, hand, score, isBot);
	}
	
	public HumanPlayer(String name, int score, boolean isBot) {
		super( name,  score,  isBot);
	}
	
	public HumanPlayer(HumanPlayer hp) {
		super( hp.name,  hp.score,  hp.isBot);
	}
	

	public HumanPlayer(Player p) {
		super( p.name,  p.score,  p.isBot);
	}

	// literally just asks what the player wants to take from the oth
	@Override
	public String chooseRank() {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("What rank do you want?");
			String rank = scanner.nextLine();
			return rank;
		}
	}

	@Override
	public Player createPlayer() {
		String name = JOptionPane.showInputDialog(null, "Enter your name");
		HumanPlayer player = new HumanPlayer(name);
		return player;
	}

	@Override
	public boolean isBot() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return "\n\nName: " + name +
				"\nHand: [" + hand.toString() +
				"]\nScore: " + score +
				"\nIs Bot?:" + isBot + "\n";
	}

	
	 public void addCard(Card card) {
	        if (card == null) {
	            System.err.println("Error: Cannot add null card to hand");
	            return;
	        }
	        
	        if (hand == null) {
	            System.err.println("Error: Hand is not initialized!");
	            hand = new LinkedList<Card>(); // Emergency fix
	        }
	        
	        hand.insertAtRear(card);
	    }

}
