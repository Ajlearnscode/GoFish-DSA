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
import java.awt.List;

import main.java.com.gofish.model.Card;


public class Stack<T> extends List{
	private static final long serialVersionUID = 1L;
	private Node<T> top;

	// Default Constructor
	public Stack() {
		top = null;
	}

	// Getters and Setters
	public Node<T> getTop() {
		return top;
	}

	public void setTop(Node<T> top) {
		this.top = top;
	}

	// Push Method - Adds an item to the top
	public void push(T data) {
		Node<T> temp = new Node<>(data);

		if (temp != null) {
			if (top == null) {
				top = temp;
			} else {
				temp.setNext(top);
				top = temp;
			}
		} else {
			System.err.println("Failed to create and add a new node to the Stack!");
		}
	} // End of push Method

	// Pop Method - Removes and returns the top item
	
	public T pop() {
	    T result = null;
	    
	    if (top == null) {
	        System.err.println("The stack is empty, therefore no Node can be removed.");
	    } else {
	        result = top.getData();   // Get the data
	        top = top.getNext();      // Move top to next node
	    }
	    return result;
	}

	// Peek Method - Returns the top item without removing it
	public T peek() {
		if (top == null) {
			System.out.println("The stack is empty, cannot return any value.");
			return null;
		} else {
			return top.getData();
		}
	} // End of peek Method

	// isEmpty Method - Checks if the stack is empty
	public boolean isEmpty() {
		return top == null;
	} // End of isEmpty Method

	// Size Method - Returns how many items are in the stack
	public int stackSize() {
		int count = 0;

		if (top != null) {
			Stack<T> tempStack = new Stack<>();
			T tempData;

			while (top != null) {
				count++;
				tempData = pop();
				tempStack.push(tempData);
			}

			// Restore original stack
			while (tempStack.getTop() != null) {
				tempData = tempStack.pop();
				push(tempData);
			}
		}
		return count;
	} // End of size Method

	// Display Method - Prints the stack contents (for debugging)
	public void display() {
		if (top == null) {
			System.out.println("The stack is empty. No Node in this Stack!");
		} else {
			Stack<T> tempStack = new Stack<>();
			T tempData;

			System.out.print("Stack (top to bottom): ");
			while (top != null) {
				tempData = pop();
				System.out.print(tempData + " -> ");
				tempStack.push(tempData);
			}
			System.out.println("null\n");

			// Restore original stack
			while (tempStack.getTop() != null) {
				tempData = tempStack.pop();
				push(tempData);
			}
		}
	} // End of display Method
	
	@Override
	//AI HELP
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Card Stack: \n");

	    Node<T> current = top;
	    while (current != null) {
	        sb.append(current.getData());
	        if (current.getNext() != null) sb.append("\n");
	        current = current.getNext();
	        
	    }

	    sb.append(" ");
	    return sb.toString();
	}

}
