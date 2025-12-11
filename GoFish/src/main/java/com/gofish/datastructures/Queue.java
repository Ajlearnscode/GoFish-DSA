/*Authors: 
 * Asher Maxwell - 2107584
 * Jhamar Brown - 2209430
 * Tamara Hernandez - 1505635
 * Monique Whittaker - 2104569
 * 
 * Project Title: Go Fish Game
 * Date: December 13, 2025
 * Occurrence: UM1
 * */


package main.java.com.gofish.datastructures;

public class Queue<T> {
	private Node<T> front;
	private Node<T> rear;

	// Default Constructor
	public Queue() {
		front = null;
		rear = null;
	}

	// Getters and Setters
	public Node<T> getFront() {
		return front;
	}

	public void setFront(Node<T> front) {
		this.front = front;
	}

	public Node<T> getRear() {
		return rear;
	}

	public void setRear(Node<T> rear) {
		this.rear = rear;
	}

	// Enqueue Method - Adds an item at the rear (end)
	public void enqueue(T data) {
		Node<T> temp = new Node<>(data);

		if (temp != null) {
			if (front == null) {
				front = temp;
				rear = temp;
			} else {
				rear.setNext(temp);
				rear = temp;
			}
		} else {
			System.out.println("The queue is full. Cannot add anymore items.");
		}
	} // End of enqueue Method

	// Dequeue Method - Removes and returns the item at the front
	public T dequeue() {
		T dataToReturn = null;

		if (front != null) {
			if (front == rear) {
				rear = null;
			}
			Node<T> temp = front;
			dataToReturn = front.getData();
			front = front.getNext();
			temp = null;
		}
		return dataToReturn;
	} // End of dequeue Method

	public T queueFront() {
		T data;

		if (!isEmpty()) {
			data = front.getData();
			return data;
		} else {
			System.err.println("Error, cannot retrieve data from the front of an empty queue.");
			return null;
		}
	}

	// isEmpty Method - Checks if the queue is empty
	public boolean isEmpty() {
		return front == null;
	} // End of isEmpty Method

	// Size Method - Returns the number of items in the queue
	public int size() {
		int count = 0;
		Queue<T> tempQueue = new Queue<>();

		while (front != null) {
			tempQueue.enqueue(dequeue());
			count++;
		}

		// Restore original queue
		while (tempQueue.getFront() != null) {
			enqueue(tempQueue.dequeue());
		}

		return count;
	} // End of size Method

	// Display Method - Prints the queue contents (for debugging)
	public void display() {
		if (front == null) {
			System.out.println("The queue is empty.");
		} else {
			Queue<T> tempQueue = new Queue<>();
			T tempData;

			System.out.print("Queue (front to rear): ");
			while (front != null) {
				tempData = dequeue();
				System.out.print(tempData + " -> ");
				tempQueue.enqueue(tempData);
			}
			System.out.println("null\n");

			// Restore original queue
			while (tempQueue.getFront() != null) {
				enqueue(tempQueue.dequeue());
			}
		}
	} // End of display Method
}
