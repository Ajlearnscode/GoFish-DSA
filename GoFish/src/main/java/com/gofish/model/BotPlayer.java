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

import java.util.Random;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.datastructures.Node;

public class BotPlayer extends Player {

	
	public BotPlayer() {
		super();
	}
	public BotPlayer(String name) {
		super(name, new LinkedList<>(), 0, false);
	}
	
	public BotPlayer (String name, LinkedList <Card> hand, int score, boolean isBot) {
		super(name, hand, score, isBot);
	}
	
	public BotPlayer(String name, int score, boolean isBot) {
		super( name,  score,  isBot);
	}
	
	public BotPlayer(BotPlayer hp) {
		super( hp.name,  hp.score,  hp.isBot);
	}
	

	public BotPlayer(Player p) {
		super( p.name,  p.score,  p.isBot);
	}

	@Override
	public String chooseRank() {
		if (hand.isEmpty()) {
			return null;
		}
		//Counts how many card the bot has same way sir count node set up
		int handSize = 0;
		Node <Card> curr = hand.getHead();
		while (curr != null) {
			handSize ++;
			curr = curr.getNext();
		}
		//randomize from how big the hand is and pick one
		Random rand = new Random();
		int randomIndex = rand.nextInt(handSize);
		
		//go to it
		curr = hand.getHead();
		for(int i = 0; i < randomIndex; i++) {
			curr = curr.getNext();
		}
		//get the rank and return it so it can see it
		String rank = curr.getData().getRank();
		
		System.out.println("The bot asks for: "+ rank);
		return rank;
	}

	@Override
	public Player createPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//@Override
	//public String toString() {
	//	return "Name: " + name +
		//		"\nScore: " + score +
		//		"\nIs Bot?:" + isBot + "\n";
	//}

}
