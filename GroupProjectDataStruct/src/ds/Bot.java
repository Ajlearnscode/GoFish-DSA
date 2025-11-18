package ds;

import java.util.Random;

public  class Bot extends Player {

	public Bot (String name ) {
		super (name, new LinkedList<> (), 0, true);
	}
	
	public String chooseRank() {
		if (hand.isEmpty()) {
			return null;
		}
		//Counts how many card the bot has same way sir countnode set up
		int handSize = 0;
		Node <Card> curr = hand.getHead();
		while (curr != null) {
			handSize ++;
			curr = curr.getNextNode();
		}
		//randomize from how big the hand is and pick one
		Random rand = new Random();
		int randomIndex = rand.nextInt(handSize);
		
		//go to it
		curr = hand.getHead();
		for(int i = 0; i < randomIndex; i++) {
			curr = curr.getNextNode();
		}
		//get the rank and return it so it can see it
		String rank = curr.getData().getRank();
		
		System.out.println("The bot asks for: "+ rank);
		return rank;
	}
	
	
}
