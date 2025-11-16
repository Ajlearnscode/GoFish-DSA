package ds;

/*public class Node {

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
*/

public class Node<T> {  // ← Add <T>
    private T data;  // ← Change Card to T
    private Node<T> nextNode;  // ← Add <T>
    
    public Node() {
        this.data = null;
        this.nextNode = null;
    }
    
    public Node(T data) {  // ← Change Card to T
        this.data = data;
        this.nextNode = null;
    }
    
    
    public T getData() {  // ← Change Card to T
        return data;
    }
    
    public void setData(T data) {  // ← Change Card to T
        this.data = data;
    }
    
    public Node<T> getNextNode() {  // ← Add <T>
        return nextNode;
    }
    
    public void setNextNode(Node<T> nextNode) {  // ← Add <T>
        this.nextNode = nextNode;
    }
}
