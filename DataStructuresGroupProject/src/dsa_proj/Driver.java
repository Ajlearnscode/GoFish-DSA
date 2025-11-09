package dsa_proj;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		
		//Create the node List
		List<Node> nodeList = new ArrayList<>();
		
		//Let us use each constructor to populate the array list
		//(name of the list).add(new Class(pass any arguments));
		
		
		//We are adding a new node that uses the default constructor
		//Therefore, the node will be empty
		nodeList.add(new Node());
		
		//We are creating a Card object  and instantiating it with a default constructor 
		Card card1 = new Card();
		//We are adding a new node that uses the default constructor
		//Therefore, the node will be empty
		nodeList.add(new Node(card1));
		
		
		//Creating a card object and instantiating it with our own predefined values
		Card card2 = new Card("Ace", "Spades", "Black");
		//We are now creating an empty node
		Node node  = new Node();
		//We are adding the new node that accepts the card object and the empty node we created
		nodeList.add(new Node(card2, node));
		
		//We are adding a new node to the list that uses one of the primary constructors within the node class
		//This constructor takes the attributes of the data class (Card class) as its parameters/arguments
		nodeList.add(new Node("Queen", "Diamond", "Red"));
		
		//Creating a card object and instantiating it with our own predefined values
		Card card4 = new Card("King", "Heart", "Red");
		//We are creating a node object using one of the primary constructors that take a card object and a node object
		Node node2 = new Node(card4, node);
		//We are add a new node to the list using the copy constructor of the node class
		nodeList.add(new Node(node2));
		
		
		//Accessing a Node inside the list
		for (int i = 0; i < nodeList.size(); i++) {
			Node n = nodeList.get(i);
			System.out.println("Value: \n" + n.toString());
		}
		
		System.out.println("\nSize:" + nodeList.size());
		
	}

}
