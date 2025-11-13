Card
Fields: rank, suit, color

Methods: toString(), equals()
#-------------------------------------------------------------------------------------

Deck (uses Stack)
Fields: Stack<Card> cards

Methods: shuffle(), drawCard(), isEmpty()
#-------------------------------------------------------------------------------------

Player (abstract)
Fields: name, LinkedList<Card> hand, int score, boolean isBot

Methods: addCard(), removeCard(), checkForPairs(), abstract String chooseRank()
#-------------------------------------------------------------------------------------

HumanPlayer extends Player
Takes input from console for rank & opponent.
#-------------------------------------------------------------------------------------

BotPlayer extends Player
Random or memory-based rank selection.
#-------------------------------------------------------------------------------------

GameEngine
Fields: Queue<Player> turnOrder, Stack<Card> deck, Player[] players, int[] scores


Methods: initializeGame(), playRound(), checkEndCondition()
#-------------------------------------------------------------------------------------

ScoreBoard
Methods: updateScore(), displayScores()
#-------------------------------------------------------------------------------------

BST
Used for AI memory or log (store ranks).
#-------------------------------------------------------------------------------------




