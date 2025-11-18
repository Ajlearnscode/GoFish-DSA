package ds;

import java.util.Scanner;

public  class Human extends Player {

	public Human (String name) {
		super (name, new LinkedList <>(), 0, false);
	}
	//literally just asks what the player wants to take from the oth
	public String chooseRank () {
		Scanner scanner = new Scanner (System.in);
		System.out.println("What rank do you want?");
		String rank = scanner.nextLine();
		return rank;
	}
	
}
