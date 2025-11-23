package main.java.com.gofish.datastructures;

public class Node<T> {

	//Attributes
	private T data;
	private Node<T> next;
	
	//Default Constructor
	public Node() {
		this.data = null;
		this.next = null;
	}
	
	//Primary 1 --
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	
	//Copy Constructor
	public Node(Node<T> n) {
		this.data = n.data;
		this.next = n.next;
	}

	//Setters and getters
	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
	
	
}
