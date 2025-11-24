package test.java.com.gofish.test;

import java.util.*;

/**
 * Simple, runnable Go Fish game engine for console (Java).
 * - Supports 2-6 players (mix of humans and bots)
 * - Deals 7 cards for 2 players, otherwise 5 cards
 * - Basic bot behavior: ask random rank from its hand
 * - Tracks "books" (sets of four of a rank) and points
 * - Ends when all 13 books are made or no cards and no hand cards remain
 *
 * To run: javac GoFishGameEngine.java && java GoFishGameEngine
 */
public class GoFishGameEngine2 {
    public static void main(String[] args) {
        Game game = new Game();
        game.setupFromConsole();
        game.start();
    }
}

enum Rank {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

    private final String shortName;
    Rank(String s) { shortName = s; }
    @Override public String toString() { return shortName; }
    public static Rank fromString(String s) {
        for (Rank r : values()) if (r.shortName.equalsIgnoreCase(s) || r.name().equalsIgnoreCase(s)) return r;
        return null;
    }
}

class Card {
    final Rank rank;
    final String suit;
    Card(Rank rank, String suit) { this.rank = rank; this.suit = suit; }
    @Override public String toString() { return rank + "-" + suit; }
}

class Deck {
    private final Deque<Card> cards = new ArrayDeque<>();
    private static final String[] SUITS = {"♣", "♦", "♥", "♠"};
    Deck() { resetAndShuffle(); }
    void resetAndShuffle() {
        cards.clear();
        for (String s : SUITS) for (Rank r : Rank.values()) cards.add(new Card(r, s));
        List<Card> temp = new ArrayList<>(cards);
        Collections.shuffle(temp);
        cards.clear();
        cards.addAll(temp);
    }
    boolean isEmpty() { return cards.isEmpty(); }
    Card draw() { return cards.pollFirst(); }
    int size() { return cards.size(); }
}

class Player {
    final String name;
    final boolean isBot;
    final List<Card> hand = new ArrayList<>();
    final Map<Rank, Integer> bookCounts = new HashMap<>(); // rank -> 1 when book made
    int points = 0;

    Player(String name, boolean isBot) { this.name = name; this.isBot = isBot; }

    void receive(Card c) { if (c != null) hand.add(c); }
    void receiveAll(Collection<Card> cs) { hand.addAll(cs); }
    boolean hasRank(Rank r) { for (Card c : hand) if (c.rank == r) return true; return false; }
    List<Card> giveAllOfRank(Rank r) {
        List<Card> out = new ArrayList<>();
        Iterator<Card> it = hand.iterator();
        while (it.hasNext()) {
            Card c = it.next();
            if (c.rank == r) { out.add(c); it.remove(); }
        }
        return out;
    }
    void sortHand() { hand.sort(Comparator.comparing((Card c) -> c.rank.ordinal()).thenComparing(c->c.suit)); }
    List<Rank> ranksInHand() {
        Set<Rank> s = new LinkedHashSet<>();
        for (Card c : hand) s.add(c.rank);
        return new ArrayList<>(s);
    }
    void checkAndMakeBooks() {
        Map<Rank, Integer> counts = new HashMap<>();
        for (Card c : hand) counts.put(c.rank, counts.getOrDefault(c.rank,0)+1);
        for (Map.Entry<Rank, Integer> e : counts.entrySet()) {
            if (e.getValue() >= 4) {
                // remove four cards of that rank
                int removed = 0;
                Iterator<Card> it = hand.iterator();
                while (it.hasNext() && removed < 4) {
                    if (it.next().rank == e.getKey()) { it.remove(); removed++; }
                }
                bookCounts.put(e.getKey(), 1);
                points += 1; // 1 point per book
                System.out.printf("%s completed a book of %s! (+1 point)\n", name, e.getKey());
            }
        }
    }
    int totalBooks() { return bookCounts.size(); }
    boolean hasNoCards() { return hand.isEmpty(); }

    @Override public String toString() { return name + (isBot?" [BOT]":""); }
}

class Game {
    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private final Scanner scanner = new Scanner(System.in);

    void setupFromConsole() {
        System.out.println("Welcome to Go Fish - Console Edition");
        int numPlayers = readIntInRange("Enter number of players (2-6): ", 2, 6);
        for (int i = 1; i <= numPlayers; i++) {
            System.out.printf("Is player %d a bot? (y/n): ", i);
            boolean isBot = readYesNo();
            String name;
            if (isBot) {
                name = "Bot-" + i;
            } else {
                System.out.printf("Enter name for player %d: ", i);
                name = scanner.nextLine().trim();
                if (name.isEmpty()) name = "Player-" + i;
            }
            players.add(new Player(name, isBot));
        }
        deck.resetAndShuffle();
        int toDeal = (players.size() == 2) ? 7 : 5;
        for (int i = 0; i < toDeal; i++) for (Player p : players) p.receive(deck.draw());
        for (Player p : players) p.sortHand();
        System.out.println("Dealt cards. Let's play!\n");
    }

    void start() {
        // initial check for immediate books
        for (Player p : players) p.checkAndMakeBooks();
        while (!isGameOver()) {
            Player curr = players.get(currentPlayerIndex);
            System.out.println("-----------------------------------------");
            System.out.printf("It's %s's turn.\n", curr);
            if (curr.isBot) doTurnBot(curr);
            else doTurnHuman(curr);

            // after each successful change, check for books for all players
            for (Player p : players) p.checkAndMakeBooks();

            // advance to next player only if the turn did not continue
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

            // skip players with no cards if deck empty
            if (deck.isEmpty()) {
                // if a player has no cards, they are skipped automatically in isGameOver checks
            }
        }
        endGameSummary();
    }

    private void doTurnHuman(Player curr) {
        if (curr.hasNoCards()) {
            if (!deck.isEmpty()) {
                Card c = deck.draw();
                curr.receive(c);
                System.out.printf("%s had no cards and drew %s from deck.\n", curr.name, c);
            } else {
                System.out.printf("%s has no cards and deck is empty. Skipping turn.\n", curr.name);
                return;
            }
        }

        boolean continueTurn = true;
        while (continueTurn) {
            showPlayerHand(curr);
            int askedIndex = choosePlayerToAsk(curr);
            if (askedIndex == -1) { System.out.println("No valid player to ask. Ending turn."); return; }
            Player asked = players.get(askedIndex);
            Rank rank = chooseRankToAsk(curr);
            System.out.printf("%s asks %s for rank %s\n", curr.name, asked.name, rank);
            if (asked.hasRank(rank)) {
                List<Card> transferred = asked.giveAllOfRank(rank);
                curr.receiveAll(transferred);
                curr.sortHand();
                System.out.printf("%s gave %d card(s) of %s to %s.\n", asked.name, transferred.size(), rank, curr.name);
                curr.checkAndMakeBooks();
                // continueTurn remains true
            } else {
                System.out.printf("%s says: 'Go Fish!'\n", asked.name);
                Card drawn = deck.draw();
                if (drawn != null) {
                    curr.receive(drawn);
                    System.out.printf("%s drew %s from the deck.\n", curr.name, drawn);
                    // if drawn card matches rank asked, they continue
                    if (drawn.rank == rank) {
                        System.out.println("Lucky draw! You get another turn.");
                        curr.checkAndMakeBooks();
                        continue; // keep turn
                    } else {
                        curr.checkAndMakeBooks();
                        continueTurn = false;
                    }
                } else {
                    System.out.println("Deck is empty. Turn ends.");
                    continueTurn = false;
                }
            }
            // if player has emptied hand and deck still has cards, they draw one to continue playing
            if (curr.hasNoCards() && !deck.isEmpty()) {
                Card c = deck.draw();
                curr.receive(c);
                System.out.printf("%s had no cards and drew %s to continue playing.\n", curr.name, c);
            }
            // in human loop, break if they choose to stop (we let rules decide continuation)
            if (!continueTurn) break;
        }
    }

    private void doTurnBot(Player bot) {
        if (bot.hasNoCards()) {
            if (!deck.isEmpty()) {
                Card c = deck.draw();
                bot.receive(c);
                System.out.printf("%s drew a card from the deck.\n", bot.name);
            } else { System.out.printf("%s has no cards and deck empty. Skipping.\n", bot.name); return; }
        }
        boolean continueTurn = true;
        Random rnd = new Random();
        while (continueTurn) {
            // bot chooses a random player (not itself) who has cards, prefer those with cards
            List<Integer> possible = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) if (i != currentPlayerIndex && !players.get(i).hasNoCards()) possible.add(i);
            if (possible.isEmpty()) {
                // everyone else has no cards -> draw
                Card d = deck.draw();
                if (d != null) { bot.receive(d); System.out.printf("%s drew a card.\n", bot.name); }
                return;
            }
            int targetIdx = possible.get(rnd.nextInt(possible.size()));
            Player target = players.get(targetIdx);
            // choose rank - prefer ranks in hand, random among them
            List<Rank> ranks = bot.ranksInHand();
            Rank rank = ranks.get(rnd.nextInt(ranks.size()));
            System.out.printf("%s asks %s for %s\n", bot.name, target.name, rank);
            if (target.hasRank(rank)) {
                List<Card> transferred = target.giveAllOfRank(rank);
                bot.receiveAll(transferred);
                bot.sortHand();
                System.out.printf("%s gave %d card(s) of %s to %s.\n", target.name, transferred.size(), rank, bot.name);
                bot.checkAndMakeBooks();
                // continue
            } else {
                System.out.printf("%s says: 'Go Fish!' to %s\n", target.name, bot.name);
                Card drawn = deck.draw();
                if (drawn != null) {
                    bot.receive(drawn);
                    System.out.printf("%s drew from deck.\n", bot.name);
                    if (drawn.rank == rank) {
                        System.out.printf("%s drew the asked rank and continues.\n", bot.name);
                        bot.checkAndMakeBooks();
                        continue; // bot continues
                    } else {
                        bot.checkAndMakeBooks();
                        continueTurn = false;
                    }
                } else {
                    System.out.println("Deck empty. Bot turn ends.");
                    continueTurn = false;
                }
            }
            if (bot.hasNoCards() && !deck.isEmpty()) {
                Card c = deck.draw();
                bot.receive(c);
            }
        }
    }

    private int choosePlayerToAsk(Player curr) {
        System.out.println("Choose a player to ask:");
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == curr) continue;
            System.out.printf("%d) %s (cards: %d)\n", i, players.get(i).name, players.get(i).hand.size());
        }
        while (true) {
            System.out.print("Enter player index: ");
            String line = scanner.nextLine().trim();
            try {
                int idx = Integer.parseInt(line);
                if (idx >= 0 && idx < players.size() && players.get(idx) != curr) return idx;
            } catch (Exception e) { }
            System.out.println("Invalid selection. Try again.");
        }
    }

    private Rank chooseRankToAsk(Player curr) {
        System.out.println("Choose a rank to ask from your hand:");
        List<Rank> ranks = curr.ranksInHand();
        for (int i = 0; i < ranks.size(); i++) System.out.printf("%d) %s\n", i, ranks.get(i));
        while (true) {
            System.out.print("Enter index (or enter rank text like A, K, 10): ");
            String line = scanner.nextLine().trim();
            // try index
            try {
                int idx = Integer.parseInt(line);
                if (idx >= 0 && idx < ranks.size()) return ranks.get(idx);
            } catch (Exception ignored) {}
            // try rank text
            Rank r = Rank.fromString(line.toUpperCase());
            if (r != null && curr.hasRank(r)) return r;
            System.out.println("Invalid rank. Choose one you have in hand.");
        }
    }

    private void showPlayerHand(Player p) {
        p.sortHand();
        System.out.printf("%s's hand (%d cards): ", p.name, p.hand.size());
        for (Card c : p.hand) System.out.print(c + " ");
        System.out.println();
    }

    private boolean readYesNo() {
        while (true) {
            String s = scanner.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no")) return false;
            System.out.print("Please enter y or n: ");
        }
    }

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.printf("Please enter an integer between %d and %d.\n", min, max);
        }
    }

    private boolean isGameOver() {
        int totalBooks = 0;
        for (Player p : players) totalBooks += p.totalBooks();
        if (totalBooks >= 13) return true; // all books made
        // also finish if deck empty and all players have no cards
        if (deck.isEmpty()) {
            boolean anyHasCards = false;
            for (Player p : players) if (!p.hasNoCards()) { anyHasCards = true; break; }
            if (!anyHasCards) return true;
        }
        return false;
    }

    private void endGameSummary() {
        System.out.println("========================================");
        System.out.println("Game Over! Final Scores:");
        players.sort(Comparator.comparingInt((Player p) -> p.points).reversed());
        for (Player p : players) System.out.printf("%s - Points: %d, Books: %d\n", p.name, p.points, p.totalBooks());
        Player winner = players.get(0);
        System.out.printf("\nWinner: %s\n", winner.name);
        System.out.println("Thanks for playing!");
    }
}

