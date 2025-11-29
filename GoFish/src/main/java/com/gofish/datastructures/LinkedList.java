package main.java.com.gofish.datastructures;

public class LinkedList<T> {

	private Node<T> head;

	// Default Constructor -
	public LinkedList() {
		this.head = null;
	}

	// Primary Constructor -
	public LinkedList(Node<T> head) {
		this.head = head;
	}

	// Setters and getters -
	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return  "" + head ;
	}

	public void insertAtFront(T data) {
		Node<T> temp = new Node<>();
		if (temp != null) {

			temp.setData(data);
			temp.setNext(null);

			if (head == null) {
				head = temp;

			} else {
				temp.setNext(head);
				head = temp;
			}
		}
	}

	public void insertAtRear(T data) {
		Node<T> temp1 = new Node<T>();
		Node<T> temp2;

	//	if (temp1 != null) {
			temp1.setData(data);
			temp1.setNext(null);
			if (head == null) {
				head = temp1;
			} else {
				temp2 = head;
				while (temp2.getNext() != null) {
					temp2 = temp2.getNext();
				}
				temp2.setNext(temp1);
			}
	//	}
	}

	public void insertAtPosition(T data, int position) {

	}

	public T deleteFromFront() {
		T data = null;
		Node<T> temp;

		if (head != null) {
			temp = head;
			data = temp.getData();
			head = head.getNext();
			temp = null;
		}

		return data;
	}

	public T deleteFromRear() {
		T data = null;
		Node<T> temp1; // ← Add <T>
		Node<T> temp2; // ← Add <T>

		if (head != null) {
			if (head.getNext() == null) {
				data = head.getData();
				head = null;
			} else {
				temp1 = head;
				while (temp1.getNext().getNext() != null) {
					temp1 = temp1.getNext();
				}
				temp2 = temp1.getNext();
				data = temp2.getData();
				temp1.setNext(null);
				temp2 = null;
			}
		}

		return data;
	}

	public void deleteAtPosition(int position) {

	}

	public T search(T data) {

		Node<T> curr = head;

		while (curr != null) {
			if (curr.getData().equals(data)) {
				return curr.getData();
			}
			curr = curr.getNext();
		}
		return null;
	}

	public boolean isEmpty() {

		if (head == null) {
			return true;
		}
		return false;
	}

	public boolean isFull() {

		return false;
	}

	public int count() {
	    Node<T> curr = head;
	    int count = 0;
	    
	    while (curr != null) {
	        curr = curr.getNext(); // ← FIXED: Actually move to next node!
	        count++;
	    }
	    
	    // Debug (optional - remove in production)
	    System.out.println("The number of nodes in the list: " + count);
	    return count;
	}

	public void display() {
		Node<T> curr = head;
		while (curr != null) {
			System.out.print(curr.getData());
			curr = curr.getNext();
		}
		System.out.println("End");
	}

	public void reverse() {
		
	}

	public int getIndex() {

		return 0;
	}
}

/*
 * insertAtFront(data) Inserts a new node at the beginning. (done)
 * insertAtEnd(data) Inserts a new node at the end. insertAtPosition(data,
 * position) Inserts a new node at a specific index. #we should also the
 * constructors that take each attribute as arguments like what sir did
 * deleteFromFront() Removes the first node. deleteFromEnd() Removes the last
 * node. deleteAtPosition(position) Removes a node from a specific position.
 * search(data) Searches for a node containing the given data. #search(*the
 * attributes of the data class*) Searches for the node with the specific
 * attributes isEmpty() Checks if the list is empty. size() Returns the number
 * of elements in the list. display() Prints or returns all elements in order.
 * reverse() Reverses the list (optional but common). get(index) We can
 * implement a method that deals with positioning, will discuss later getHead()
 * 
 */
