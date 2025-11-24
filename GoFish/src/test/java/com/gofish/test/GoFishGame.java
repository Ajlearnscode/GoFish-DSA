package test.java.com.gofish.test;

import java.util.*;

public class GoFishGame {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.startGame();
    }
}

// Card class
class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

// Rank enum
enum Rank {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), QUEEN("Q"), KING("K");

    private final String display;

    Rank(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}

// Suit enum
enum Suit {
    HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣"), SPADES("♠");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

// Deck class
class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}

// Player abstract class
abstract class Player {
    protected final String name;
    protected final List<Card> hand;
    protected int books;
    protected boolean isActive;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.books = 0;
        this.isActive = true;
    }

    public void addCard(Card card) {
        if (card != null) {
            hand.add(card);
            sortHand();
        }
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
        sortHand();
    }

    private void sortHand() {
        hand.sort(Comparator.comparing(Card::getRank));
    }

    public List<Card> giveCardsOfRank(Rank rank) {
        List<Card> cardsToGive = new ArrayList<>();
        Iterator<Card> iterator = hand.iterator();

        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getRank() == rank) {
                cardsToGive.add(card);
                iterator.remove();
            }
        }
        return cardsToGive;
    }

    public boolean hasRankInHand(Rank rank) {
        return hand.stream().anyMatch(card -> card.getRank() == rank);
    }

    public int checkAndRemoveBooks() {
        Map<Rank, Integer> rankCount = new HashMap<>();

        for (Card card : hand) {
            rankCount.put(card.getRank(),
                    rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        int booksFound = 0;
        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            if (entry.getValue() == 4) {
                removeBook(entry.getKey());
                booksFound++;
            }
        }
        return booksFound;
    }

    private void removeBook(Rank rank) {
        hand.removeIf(card -> card.getRank() == rank);
        books++;
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    public int getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public int getHandSize() {
        return hand.size();
    }

    public abstract Rank chooseRank();
    public abstract Player choosePlayer(List<Player> availablePlayers);
}

// HumanPlayer class
class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public Rank chooseRank() {
        System.out.println("\n" + name + "'s turn!");
        System.out.println("Your hand: " + formatHand());

        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(),
                    rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        System.out.println("\nAvailable ranks to ask for:");
        List<Rank> availableRanks = new ArrayList<>(rankCount.keySet());
        for (int i = 0; i < availableRanks.size(); i++) {
            System.out.println((i + 1) + ". " + availableRanks.get(i));
        }

        while (true) {
            System.out.print("\nChoose a rank (1-" + availableRanks.size() + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= availableRanks.size()) {
                    return availableRanks.get(choice - 1);
                }
                System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    @Override
    public Player choosePlayer(List<Player> availablePlayers) {
        System.out.println("\nChoose a player to ask:");
        for (int i = 0; i < availablePlayers.size(); i++) {
            Player p = availablePlayers.get(i);
            System.out.println((i + 1) + ". " +
                    p.getName() + " (" + p.getHandSize() + " cards)");
        }

        while (true) {
            System.out.print("\nChoose a player (1-" + availablePlayers.size() + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= availablePlayers.size()) {
                    return availablePlayers.get(choice - 1);
                }
                System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private String formatHand() {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(),
                    rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            sb.append(entry.getKey()).append("(")
              .append(entry.getValue()).append(") ");
        }
        return sb.toString().trim();
    }
}

// BotPlayer class
class BotPlayer extends Player {
    private final Random random;
    private final Map<Player, Set<Rank>> knownCards;

    public BotPlayer(String name) {
        super(name);
        random = new Random();
        knownCards = new HashMap<>();
    }

    @Override
    public Rank chooseRank() {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(),
                    rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        int maxCount = 0;
        List<Rank> bestRanks = new ArrayList<>();

        for (Map.Entry<Rank, Integer> entry : rankCount.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                bestRanks.clear();
                bestRanks.add(entry.getKey());
            } else if (count == maxCount) {
                bestRanks.add(entry.getKey());
            }
        }

        return bestRanks.get(random.nextInt(bestRanks.size()));
    }

    @Override
    public Player choosePlayer(List<Player> availablePlayers) {
        List<Player> playersWithKnowledge = new ArrayList<>();

        for (Player p : availablePlayers) {
            if (knownCards.containsKey(p) &&
                !knownCards.get(p).isEmpty()) {
                playersWithKnowledge.add(p);
            }
        }

        if (!playersWithKnowledge.isEmpty()) {
            return playersWithKnowledge.get(
                    random.nextInt(playersWithKnowledge.size()));
        }

        return availablePlayers.get(
                random.nextInt(availablePlayers.size()));
    }

    public void recordKnownCard(Player player, Rank rank) {
        knownCards.computeIfAbsent(player,
                k -> new HashSet<>()).add(rank);
    }

    public void removeKnownCard(Player player, Rank rank) {
        if (knownCards.containsKey(player)) {
            knownCards.get(player).remove(rank);
        }
    }
}

// GameEngine class
class GameEngine {
    private final Deck deck;
    private final List<Player> players;
    private final Scanner scanner;
    private int currentPlayerIndex;
    private boolean gameOver;

    public GameEngine() {
        deck = new Deck();
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        currentPlayerIndex = 0;
        gameOver = false;
    }

    public void startGame() {
        System.out.println("=== WELCOME TO GO FISH ===\n");

        setupPlayers();
        dealInitialCards();

        System.out.println("\nGame starting!\n");

        playGame();
        endGame();
    }

    private void setupPlayers() {
        System.out.print("Enter number of players (2-6): ");
        int numPlayers;

        while (true) {
            try {
                numPlayers = Integer.parseInt(scanner.nextLine().trim());
                if (numPlayers >= 2 && numPlayers <= 6) {
                    break;
                }
                System.out.print("Please enter 2–6: ");
            } catch (Exception e) {
                System.out.print("Invalid number: ");
            }
        }

        System.out.print("How many human players? (1-" + numPlayers + "): ");
        int numHumans;

        while (true) {
            try {
                numHumans = Integer.parseInt(scanner.nextLine().trim());
                if (numHumans >= 1 && numHumans <= numPlayers) {
                    break;
                }
                System.out.print("Please enter 1–" + numPlayers + ": ");
            } catch (Exception e) {
                System.out.print("Invalid number: ");
            }
        }

        for (int i = 1; i <= numHumans; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) name = "Player " + i;
            players.add(new HumanPlayer(name, scanner));
        }

        for (int i = 1; i <= numPlayers - numHumans; i++) {
            players.add(new BotPlayer("Bot " + i));
        }

        deck.shuffle();
    }

    private void dealInitialCards() {
        int cardsPerPlayer = (players.size() == 2) ? 7 : 5;

        System.out.println("\nDealing " + cardsPerPlayer + " cards each...");

        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player p : players) {
                Card c = deck.drawCard();
                if (c != null) p.addCard(c);
            }
        }

        for (Player p : players) {
            int books = p.checkAndRemoveBooks();
            if (books > 0) {
                System.out.println(p.getName() +
                        " made " + books + " book(s)!");
            }
        }
    }

    private void playGame() {
        while (!gameOver) {
            Player current = players.get(currentPlayerIndex);

            if (current.isEmpty() && !deck.isEmpty()) {
                Card drawn = deck.drawCard();
                current.addCard(drawn);
                System.out.println(current.getName() +
                        " had no cards and drew from deck.");
            }

            if (current.isEmpty()) {
                current.setActive(false);
                System.out.println(current.getName() +
                        " is out of the game.");
                nextPlayer();

                if (checkGameOver()) gameOver = true;
                continue;
            }

            displayGameState();
            executeTurn(current);

            if (checkGameOver()) gameOver = true;
        }
    }

    private void executeTurn(Player currentPlayer) {
        Rank askedRank = currentPlayer.chooseRank();
        List<Player> targets = getAvailablePlayers(currentPlayer);

        if (targets.isEmpty()) {
            System.out.println("No available players!");
            nextPlayer();
            return;
        }

        Player target = currentPlayer.choosePlayer(targets);

        System.out.println("\n" + currentPlayer.getName() +
                " asks " + target.getName() +
                " for " + askedRank + "s");

        List<Card> received = target.giveCardsOfRank(askedRank);

        if (!received.isEmpty()) {
            System.out.println(target.getName() +
                    " gives " + received.size() + " card(s)!");
            currentPlayer.addCards(received);

            int books = currentPlayer.checkAndRemoveBooks();
            if (books > 0) {
                System.out.println(currentPlayer.getName() +
                        " made " + books + " book(s)!");
            }

            if (currentPlayer instanceof BotPlayer) {
                ((BotPlayer) currentPlayer).removeKnownCard(target, askedRank);
            }

            System.out.println(currentPlayer.getName() +
                    " gets another turn!");

        } else {
            System.out.println("Go Fish!");

            if (!deck.isEmpty()) {
                Card drawn = deck.drawCard();
                currentPlayer.addCard(drawn);

                System.out.println(currentPlayer.getName() +
                        " draws a card...");

                int books = currentPlayer.checkAndRemoveBooks();
                if (books > 0) {
                    System.out.println(currentPlayer.getName() +
                            " made " + books + " book(s)!");
                }

                if (drawn.getRank() == askedRank) {
                    System.out.println("Lucky! " +
                            currentPlayer.getName() +
                            " gets another turn!");
                } else {
                    nextPlayer();
                }
            } else {
                System.out.println("Deck is empty!");
                nextPlayer();
            }

            if (target instanceof BotPlayer) {
                ((BotPlayer) target).recordKnownCard(currentPlayer, askedRank);
            }
        }

        try {
            Thread.sleep(1200);
        } catch (Exception ignored) {}
    }

    private void displayGameState() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CURRENT GAME STATE:");
        System.out.println("Deck: " + deck.size() + " cards remaining\n");

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            String marker = (i == currentPlayerIndex) ? " <- CURRENT" : "";
            System.out.println("  " + p.getName() +
                    ": " + p.getHandSize() +
                    " cards, " + p.getBooks() +
                    " books" + marker);
        }
        System.out.println("=".repeat(50));
    }

    private List<Player> getAvailablePlayers(Player current) {
        List<Player> list = new ArrayList<>();
        for (Player p : players) {
            if (p != current && p.isActive() && !p.isEmpty()) {
                list.add(p);
            }
        }
        return list;
    }

    private void nextPlayer() {
        int attempts = 0;

        do {
            currentPlayerIndex =
                    (currentPlayerIndex + 1) % players.size();
            attempts++;

            if (attempts > players.size()) break;

        } while (!players.get(currentPlayerIndex).isActive()
                || players.get(currentPlayerIndex).isEmpty());
    }

    private boolean checkGameOver() {
        int totalBooks = players.stream()
                .mapToInt(Player::getBooks)
                .sum();
        if (totalBooks == 13) return true;

        if (deck.isEmpty()) {
            for (Player p : players) {
                if (!p.isEmpty()) return false;
            }
            return true;
        }

        return false;
    }

    private void endGame() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("GAME OVER!");
        System.out.println("=".repeat(50));
        System.out.println("\nFinal Scores:\n");

        players.sort((a, b) -> b.getBooks() - a.getBooks());

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.println((i + 1) + ". " +
                    p.getName() +
                    ": " + p.getBooks() + " books");
        }

        Player winner = players.get(0);
        System.out.println("\n🏆 " + winner.getName() + " WINS! 🏆");

        System.out.print("\nPlay again? (y/n): ");
        String r = scanner.nextLine().trim().toLowerCase();

        if (r.equals("y") || r.equals("yes")) {
            players.clear();
            currentPlayerIndex = 0;
            gameOver = false;
            startGame();
        } else {
            System.out.println("\nThanks for playing Go Fish!");
            scanner.close();
        }
    }
}
