#Rules of the game

#Go Fish – Official Game Rules (Data Structures Project)

#1. Overview

Go Fish is a card matching game in which players attempt to collect sets of matching card ranks by requesting cards from opponents or drawing from the deck. In our project implementation, the player competes against other players but not the computer.

#2. Components

Standard 52-card deck.

2–4 Human players.

#3. Initial Deal

The number of cards dealt depends on the number of players:

2 players: deal seven (7) cards to each player.

3–4 players: deal five (5) cards to each player.

Place the remaining cards face down as the draw deck.

#4. Turn Manager

On a player’s turn:

#4.1 Asking for a Rank

The active player selects one opponent and asks for all cards of a specific rank (e.g., “Do you have any sevens?”).

A player may only request a rank that they already possess at least one copy of in their hand.

#4.2 Opponent Response

If the opponent has one or more cards of the requested rank:

They must hand over all cards of that rank.

The asking player adds these cards to their hand and takes another turn immediately.

If the opponent has none:

The opponent says “Go fish”, and the asking player must draw the top card from the deck.

#4.3 Drawing From the Deck

If the drawn card matches the requested rank, the player:

Adds the card to their hand.

Continues their turn.

If it does not match, the turn ends.

#5. Forming Sets

Books (Traditional Rules)

A book is a set of four (4) cards of the same rank.

When a player forms a book:

They remove all four cards from their hand.

They score 1 point.

#6. Empty Hand Rule

If a player’s hand becomes empty and cards remain in the deck:

They draw four (5) cards from the deck (or fewer if fewer remain).

If the deck is empty, the player simply waits for the opponent’s turn.

#7. Ending the Game

The game ends when either:

All 13 books have been completed or the deck is empty and all players’ hands are empty.

#8. Determining the Winner

Under the book system: the player with the most books wins.

#9. Detailed Summary of the game's flow 

--> Ask for a rank you already hold.

--> If the opponent has it, take the cards and continue your turn.

--> If not,  Go Fish by drawing a card.

--> If the drawn card matches your request you continue, otherwise, your turn ends.

--> Create books when possible (4 matching cards).

--> Draw five cards whenever your hand becomes empty (if the deck permits).

--> Continue until all sets are formed or all cards are gone.

--> Highest score wins. Good Game!

#####################################################################################################################################################################################

#Data Structures Choices

The fundamental data structures were employed in the creation of our Go Fish game, such as: Linked List, Stacks, Queues and Node.

Let’s begin with the stack, the linked list, and the unique deck structure based on stacks, because of its Last-In-First-Out (LIFO) behavior, which mimics how a real deck functions, players always draw the card from the top. 

The stack was selected to represent the main deck of cards. This format enables effective operations like drawing cards using constant-time push and pop operations and shuffling, which divides the deck into multiple temporary stacks and recombines them. Since hands in Go Fish continuously expand and contract as players draw cards, pass cards, or form books, the linked list was used to store and manage each player's hand.

A linked list is perfect for determining whether a player has the desired rank while playing because it allows for quick insertion, deletion, and sequential searching. Additionally, it manages dynamic hand sizes more effectively than static arrays. 

Lastly, the game employs a unique deck structure using stacks that arrange the entire set of 52 cards and facilitate operations like initialization, shuffling, counting the number of cards left, and drawing cards by combining stack operations in a controlled, object-oriented way. Together, these data structures, stacks for realistic card handling, linked lists for flexible player hands, and the deck class for centralized card management were chosen because they accurately and efficiently model the game's mechanics, producing a neat, effective, and well-organized implementation.

Continuing on with the Node and Queue: The Node class is used to represent each card in a player’s hand. According to the game rules,  in a two-player game, each player receives seven cards. In the program, these cards are represented as nodes that are linked together to form a single player’s hand, as described in the LinkedList class.

The Queue class manages the number of players participating in the game. As the name suggests, a queue follows the First-In, First-Out (FIFO) principle. In a Go Fish game, the first player to enter their name is the first player to take a turn. The enqueue method handles adding players to the game queue, while the dequeue method removes them once their turn is finished.

After a player completes their turn, regardless of whether they were successful, they are removed from the queue so the next player can proceed. According to the rules of Go Fish, a player asks another for a specific card. If the card is received, they take another turn. If not, they draw from the deck; if the drawn card is the one they asked for, they continue. If not, their turn ends, and they are dequeued.
