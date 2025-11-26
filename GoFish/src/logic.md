Go Fish logic

# 1. Start game

# 2. Initialize Deck 

# 3. Shuffle Deck

# 4. Number of Players
	
# 5. Select Player Type

# 6. Deal Cards
	#if 2 players (deal 7 cards Each)
	#if 3 - 4 players (deal 5 cards each)

# 7. Set Current Player
	#Game Over?
		--> No --> player turn
		--> yes --> Count points
				#Determine Winner
				#End game

# 8. Player Turn

# 9. Ask for Cards

# 10. Check if asked player has card


# 10. Yes? Give Cards
	# Check for sets
	# Any sets?
		#yes? 
		--> Remove Sets from hand
		--> Point Awarded 
		#no?
		-->Next Player's Turn - (set  current player)
		
# 10. No? Draw Card from deck
	#Is Drawn Card a Match?
	#yes - go to (10. Yes)
	#No -> next Player's Turn - (set  current player)
	
	
#####################################################################################################

MODULE #1 :

Start Game :-

-> We need to display the rules of the game before players actually start playing. We can place the rules into a function that is called gameRules(); (returns void) .

→ We need to then initialize the deck of cards

The deck of cards will have various functions:
shuffle(); Responsible for shuffling the deck of cards
isEmpty(); Responsible for checking the number of cards left in the deck to check 
draw(); allows the player to draw from the deck of cars
push(); inserts card into the deck at the initial stage of the game

# Initializing a deck of cards means that we are going to first ensure that we place all 52 card into the stack.
# Then we are to shuffle the deck of cards within the deck class by calling the shuffle function


Phase 2 - Number of players

Two types of players: Bots and Humans
Current Functions: removeCard(), chooseRank()

Needed functions: 

askAnotherPlayer; 
setCurrentPlayer (goes in turn manager)`
addPoints (scoreboard class)
drawCard (already in deck)
Taketurn (turn manager) (possibly player)
Showhand 
Humanplayerchoice
Botplayerchoice
hasCard
transferCard (similar to removeCard)



