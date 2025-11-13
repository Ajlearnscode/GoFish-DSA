package ds;

public class Node {

	private Card data;
	private Node nextNode;
	
	public Node () {
		data = new Card ();
		nextNode = null;
	}
	public Node (Card data, Node nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}
	
	public Node (Card obj) {
		data = new Card (obj);
		nextNode = null;
	}
	
	public Node ( String rank, String suit, String colour) {
		data = new Card (rank, suit, colour);
		nextNode = null;
	}
	
	public Node (Node obj) {
		this.data = data;
		this.nextNode = nextNode;
	}
	public Card getData() {
		return data;
	}
	public void setData(Card data) {
		this.data = data;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
}
