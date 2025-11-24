package main.java.com.gofish.model;

import java.util.Random;

import main.java.com.gofish.datastructures.LinkedList;
import main.java.com.gofish.datastructures.Node;

public class BotPlayer extends Player {

	
	public BotPlayer (String name ) {
		super (name, new LinkedList<> (), 0, true);
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

}
