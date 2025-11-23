package main.java.com.gofish.model;

import java.util.Scanner;

import main.java.com.gofish.datastructures.LinkedList;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name, new LinkedList<>(), 0, false);
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

}
