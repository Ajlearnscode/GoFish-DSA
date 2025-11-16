package ds;
/*
public class LinkedList  {

	private Node head;
	
	public LinkedList() {
		head = null;
	}
	public LinkedList (Node head) {
		this.head = head;
	}
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	
	public void insertAtFront(Card dataToInsert) {
		
		Node temp  = new Node();
		
		if ( temp != null) {
			temp.setData(dataToInsert);
			temp.setNextNode(null);
			if ( head == null){
				head = temp;
			}else {
				temp.setNextNode(head);
			}
		}
	}
	
	public void insertAtRear(Card dataToInsert) {
		
		Node temp1  = new Node();
		Node temp2;
		if ( temp1 != null) {
			temp1.setData(dataToInsert);
			temp1.setNextNode(null);
			if ( head == null){
				head = temp1;
			}else {
				temp2 = head;
				while (temp2.getNextNode() != null){
					temp2 = temp2.getNextNode();
				}
				temp2.setNextNode(temp1);
			}
		}
	}
	
	public Card deleteFromFront() {
	    Card dataToReturn = null;
	    Node temp;
	    
	    if (head != null) {
	        temp = head;
	        dataToReturn = temp.getData();  
	        head = head.getNextNode();       
	        temp = null;                     
	    }
	    
	    return dataToReturn;
	}
	
	public Card deleteFromEnd() {
	    Card dataToReturn = null;
	    Node temp1;
	    Node temp2;
	    
	    if (head != null) {
	        // If only one node in the list
	        if (head.getNextNode() == null) {
	            dataToReturn = head.getData();
	            head = null;
	        } else {
	            // Traverse to second-to-last node
	            temp1 = head;
	            while (temp1.getNextNode().getNextNode() != null) {
	                temp1 = temp1.getNextNode();
	            }
	            // temp1 is now at second-to-last node
	            temp2 = temp1.getNextNode();  // temp2 points to last node
	            dataToReturn = temp2.getData();  // Capture data from last node
	            temp1.setNextNode(null);  // Remove last node
	            temp2 = null;
	        }
	    }
	    
	    return dataToReturn;
	}
	
	public Card Search(Card data) {
	    Node curr = head;
	    Card card = new Card();
	    
	    while (curr != null) {
	        if (curr.getData().getRank().equals(data.getRank()) && 
	            curr.getData().getSuit().equals(data.getSuit()) && 
	            curr.getData().getColour().equals(data.getColour())) {
	            return curr.getData();
	        }
	        curr = curr.getNextNode();
	    }  
	    return card;
	}
	
	public boolean isEmpty () {
		if (head == null) {
			return true;
		}
		return false;
	}
	
	public void Display () {
		Node curr = head ;
		while ( curr != null) {
			System.out.println(curr.getData());
		}
		System.out.println("End");
	}
	
	
	
	
	
	
	
	
	
	*/
	//Didnt add: insertAtPosition(data, position), deleteAtPosition(position), reverse()	Reverses the list (optional but common).
	//get(index) We can implement a method that deals with positioning, will discuss later
	//getHead()
	
	
	
public class LinkedList<T> {  // ← Add <T> here!

    private Node<T> head;  // ← Add <T> here!

    public LinkedList() {
        head = null;
    }
    
    public LinkedList(Node<T> head) {  // ← Add <T> here!
        this.head = head;
    }
    
    public Node<T> getHead() {  // ← Add <T> here!
        return head;
    }
    
    public void setHead(Node<T> head) {  // ← Add <T> here!
        this.head = head;
    }

    public void insertAtFront(T dataToInsert) {  // ← Change Card to T
        Node<T> temp = new Node<>();  // ← Add <T>
        
        if (temp != null) {
            temp.setData(dataToInsert);
            temp.setNextNode(null);
            if (head == null) {
                head = temp;
            } else {
                temp.setNextNode(head);
                head = temp;  
            }
        }
    }

    public void insertAtRear(T dataToInsert) {  // ← Change Card to T
        Node<T> temp1 = new Node<>();  // ← Add <T>
        Node<T> temp2;  // ← Add <T>
        
        if (temp1 != null) {
            temp1.setData(dataToInsert);
            temp1.setNextNode(null);
            if (head == null) {
                head = temp1;
            } else {
                temp2 = head;
                while (temp2.getNextNode() != null) {
                    temp2 = temp2.getNextNode();
                }
                temp2.setNextNode(temp1);
            }
        }
    }

    public T deleteFromFront() {  // ← Change Card to T
        T dataToReturn = null;
        Node<T> temp;  // ← Add <T>

        if (head != null) {
            temp = head;
            dataToReturn = temp.getData();
            head = head.getNextNode();
            temp = null;
        }

        return dataToReturn;
    }

    public T deleteFromEnd() {  // ← Change Card to T
        T dataToReturn = null;
        Node<T> temp1;  // ← Add <T>
        Node<T> temp2;  // ← Add <T>

        if (head != null) {
            if (head.getNextNode() == null) {
                dataToReturn = head.getData();
                head = null;
            } else {
                temp1 = head;
                while (temp1.getNextNode().getNextNode() != null) {
                    temp1 = temp1.getNextNode();
                }
                temp2 = temp1.getNextNode();
                dataToReturn = temp2.getData();
                temp1.setNextNode(null);
                temp2 = null;
            }
        }

        return dataToReturn;
    }

    public T Search(T data) {  // ← This might need adjustment based on how you compare
        Node<T> curr = head;

        while (curr != null) {
            if (curr.getData().equals(data)) {  // Use equals instead
                return curr.getData();
            }
            curr = curr.getNextNode();
        }
        return null;
    }

    public boolean isEmpty() {
    	if (head == null) {
			return true;
		}
		return false;
	}

    public void Display() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.println(curr.getData());
            curr = curr.getNextNode();
        }
        System.out.println("End");
    }
}
	
	
	
	
	
	
	
