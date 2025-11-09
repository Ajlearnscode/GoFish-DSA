package dsa_proj;

public class Node {
	private Card card;
	Node nextNode;
	
	//default
	public Node() {
		this.card = new Card();
		this.nextNode = null;
	}
	
	//primary 1
	public Node(Card card) {
		this.card = new Card(card);
		this.nextNode = null; 
	}
	
	//primary 2 - Accepting an object of the class and an object of the Node class
	public Node(Card card, Node nextNode) {
		this.card = card;
		this.nextNode = nextNode;
	}
	
	//primary constructor 3
	public Node(String rank, String suit, String colour) {
		card = new Card(rank, suit, colour);
		this.nextNode = null;
	}
	
	//copy
	public Node(Node node) {
		this.card = node.card;
		this.nextNode = node.nextNode;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	public String toString() {
		return card.toString();
	}
}

/*1 default - 
 *3 primary
 *1 copy 
 */
