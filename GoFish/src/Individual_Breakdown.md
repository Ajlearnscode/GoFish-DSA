#Date: November 9, 2025
#Version: 0.0.1 (see below for what each number represents)
# 0 (Number that represents when we have completed a set of task given on a particular date)
# .0 (Number that represents the project that runs successfully when all items at that given point have been done)
# .1 (The patch number -> when we have collaboratively implemented a piece of code or fixed an error)



## 																		PHASE 1

#Everyone

#Note:  MANDATORY!!
I would advise us to get the hard and most important part out of the way first. We should complete the following before moving to phase 2! - We can leave BST for last in phase 1 until we have successfully ensured that the other three custom data structures work.
*********************************************************************************************************************************************

##Linked List: (We can attack this first since we all have some understanding of how this should be implemented)
#Method	Description
insertAtFront(data)	Inserts a new node at the beginning.
insertAtEnd(data)	Inserts a new node at the end.
insertAtPosition(data, position)	Inserts a new node at a specific index.
#we should also the constructors that take each attribute as arguments like what sir did
deleteFromFront()	Removes the first node.
deleteFromEnd()	Removes the last node.
deleteAtPosition(position)	Removes a node from a specific position.
search(data)	Searches for a node containing the given data.
#search(*the attributes of the data class*) Searches for the node with the specific attributes
isEmpty()	Checks if the list is empty.
size()	Returns the number of elements in the list.
display()	Prints or returns all elements in order.
reverse()	Reverses the list (optional but common).
get(index) We can implement a method that deals with positioning, will discuss later
getHead()

*********************************************************************************************************************************************
##Stacks: 
#Method	Description
push(data)	Adds an item to the top.
pop()	Removes and returns the top item.
peek()	Returns the top item without removing it.
isEmpty()	Checks if the stack is empty.
size()	Returns how many items are in the stack.
display()	Prints the stack contents (optional for debugging).
*********************************************************************************************************************************************
##Queues:
#Method	Description
enqueue(data)	Adds an item at the rear (end).
dequeue()	Removes and returns the item at the front.
peek() or front()	Returns the item at the front without removing it.
isEmpty()	Checks if the queue is empty.
size()	Returns the number of items in the queue.
display()	Prints the contents from front to rear (optional).
*********************************************************************************************************************************************


##BST:
#NOTE:A BST is a hierarchical structure where each node has up to two children, and:
#left child < parent < right child

#Method	Description
insert(value)	Inserts a new node in its correct position.
search(value)	Finds whether a value exists in the tree.
delete(value)	Removes a node while maintaining BST properties.
findMin()	Returns the minimum value in the tree.
findMax()	Returns the maximum value in the tree.
isEmpty()	Checks if the tree is empty.
height()	Returns the height of the tree.
inorderTraversal()	Visits nodes in sorted order (Left–Root–Right).
preorderTraversal()	Visits Root–Left–Right.
postorderTraversal()	Visits Left–Right–Root.
levelOrderTraversal()	Visits nodes level by level (using a queue).
clear()	Removes all nodes (optional).
#---------------------------------------------------------------------------------------------------------------


## 																		PHASE 2

#---------------------------------------------------------------------------------------------------------------

#Asher & Jhamar

Player Class: (should be an abstract class)

Attributes: 
String- name (can just be a first name)
LinkedList<Card> - hand (this means that our hand is going to be a linked list, and the data type of that linked list is card)
int- Score (the number of books will be sent here)
boolean - isBot (is the player real or a bot) 

Methods:

addCard() - Asher
removeCard - Jhamar
checkForPairs - Asher
chooseRank() - Jhamar (according to the guideline this should be an abstract method whose return type is a string, this mothen will be overriden by the two different types of players (human & bot)
#---------------------------------------------------------------------------------------------------------------

#Tamara, Monique & Latanya

Deck Class: (uses the stack concept to you will have to use the stack class to perform operations in this class)

Attributes:
Stack<Card> - cards (thss means that cards(variable name) is going to be a Stack(our class) that has a data type of Card(class))

Methods:
shuffle() - Monique
drawCard() - Tamara
isEmpty() - Latanya
#---------------------------------------------------------------------------------------------------------------


## 																		PHASE 3 (To be continued)

#---------------------------------------------------------------------------------------------------------------

