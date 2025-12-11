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

package main.java.com.gofish.driver;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import main.java.com.gofish.game.GameEngine;

public class GoFishGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Attributes 
	JLabel titleLabel;
	JButton backBtn;
	JButton nextBtn;
	JButton playGameBtn;
	JTextArea textArea;
	JScrollPane scrollPane;
	
	
	//End of attributes

	//Default Constructor
	public GoFishGUI() {
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		    this.setSize(500, 500);
		    this.setLayout(new BorderLayout());
		    this.setLocationRelativeTo(null);

		    //Title Label
		    titleLabel = new JLabel("Group 6 Go Fish Game");
		    titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		    // Text Area and Scroll Pane
		    textArea = new JTextArea();
		    textArea.setLineWrap(true);
		    textArea.setWrapStyleWord(true);
		    textArea.setText(gameMenu());
		    textArea.setCaretPosition(0);
		    textArea.setEditable(false);

		    scrollPane = new JScrollPane(textArea);

		    // Buttons panel
		    JPanel buttonPanel = new JPanel(new FlowLayout());
		    backBtn = new JButton("<Back");
		    nextBtn = new JButton("Next>");
		    playGameBtn = new JButton("Play Game!");

		    buttonPanel.add(backBtn);
		    buttonPanel.add(nextBtn);
		    buttonPanel.add(playGameBtn);

		    // Add components
		    this.add(titleLabel, BorderLayout.NORTH);
		    this.add(scrollPane, BorderLayout.CENTER);
		    this.add(buttonPanel, BorderLayout.SOUTH);
		  

		    // to add action listeners 
		    configureActions();
	}
	
	private void configureActions() {

	    nextBtn.addActionListener(e -> {
	        textArea.setText(gameRules());
	        textArea.setCaretPosition(0);
	    });

	    backBtn.addActionListener(e -> {
	        textArea.setText(gameMenu());
	        textArea.setCaretPosition(0);
	    });
	    
	    playGameBtn.addActionListener(e ->{
	    	this.setVisible(false);
	    	GameEngine.startGame();
	    });
	}

	/*
	 * public JPanel showMenu() {
	 * 
	 * JPanel menuPanel = new JPanel(); menuPanel.setLayout(new BorderLayout());
	 * 
	 * textArea = new JTextArea(10, 40); textArea.setText(gameMenu());
	 * 
	 * menuPanel.add(textArea, BorderLayout.CENTER);
	 * 
	 * return menuPanel; }
	 */
	
	
	public static String gameMenu() {
	
		return "\n\n********Go fish Game********\n" +
				"Group Members: " +
				"\n*Asher Maxwell - 2107584" + 
				"\n*Jhamar Brown - 2209430" +
				"\n*Tamara Hernandez - 1505635" +
				"\n*Monique Whittaker - 2104569" +
				"\n\nWelcome to Group 6 Go Fish game presentation. We hope that you will enjoy the game.\n";
	}
	
	public static String gameRules() {
		return "#Rules of the game\n"
				+ "\n"
				+ "#Go Fish – Official Game Rules (Data Structures Project)\n"
				+ "\n"
				+ "#1. Overview\n"
				+ "\n"
				+ "Go Fish is a card matching game in which players attempt to collect sets of matching card ranks by requesting cards from opponents or drawing from the deck. In our project implementation, the player competes against other players but not the computer.\n"
				+ "\n"
				+ "#2. Components\n"
				+ "\n"
				+ "Standard 52-card deck.\n"
				+ "\n"
				+ "2–4 Human players.\n"
				+ "\n"
				+ "#3. Initial Deal\n"
				+ "\n"
				+ "The number of cards dealt depends on the number of players:\n"
				+ "\n"
				+ "2 players: deal seven (7) cards to each player.\n"
				+ "\n"
				+ "3–4 players: deal five (5) cards to each player.\n"
				+ "\n"
				+ "Place the remaining cards face down as the draw deck.\n"
				+ "\n"
				+ "#4. Turn Manager\n"
				+ "\n"
				+ "On a player’s turn:\n"
				+ "\n"
				+ "#4.1 Asking for a Rank\n"
				+ "\n"
				+ "The active player selects one opponent and asks for all cards of a specific rank (e.g., “Do you have any sevens?”).\n"
				+ "\n"
				+ "A player may only request a rank that they already possess at least one copy of in their hand.\n"
				+ "\n"
				+ "#4.2 Opponent Response\n"
				+ "\n"
				+ "If the opponent has one or more cards of the requested rank:\n"
				+ "\n"
				+ "They must hand over all cards of that rank.\n"
				+ "\n"
				+ "The asking player adds these cards to their hand and takes another turn immediately.\n"
				+ "\n"
				+ "If the opponent has none:\n"
				+ "\n"
				+ "The opponent says “Go fish”, and the asking player must draw the top card from the deck.\n"
				+ "\n"
				+ "#4.3 Drawing From the Deck\n"
				+ "\n"
				+ "If the drawn card matches the requested rank, the player:\n"
				+ "\n"
				+ "Adds the card to their hand.\n"
				+ "\n"
				+ "Continues their turn.\n"
				+ "\n"
				+ "If it does not match, the turn ends.\n"
				+ "\n"
				+ "#5. Forming Sets\n"
				+ "\n"
				+ "Books (Traditional Rules)\n"
				+ "\n"
				+ "A book is a set of four (4) cards of the same rank.\n"
				+ "\n"
				+ "When a player forms a book:\n"
				+ "\n"
				+ "They remove all four cards from their hand.\n"
				+ "\n"
				+ "They score 1 point.\n"
				+ "\n"
				+ "#6. Empty Hand Rule\n"
				+ "\n"
				+ "If a player’s hand becomes empty and cards remain in the deck:\n"
				+ "\n"
				+ "They draw four (5) cards from the deck (or fewer if fewer remain).\n"
				+ "\n"
				+ "If the deck is empty, the player simply waits for the opponent’s turn.\n"
				+ "\n"
				+ "#7. Ending the Game\n"
				+ "\n"
				+ "The game ends when either:\n"
				+ "\n"
				+ "All 13 books have been completed or the deck is empty and all players’ hands are empty.\n"
				+ "\n"
				+ "#8. Determining the Winner\n"
				+ "\n"
				+ "Under the book system: the player with the most books wins.\n"
				+ "\n"
				+ "#9. Detailed Summary of the game's flow \n"
				+ "\n"
				+ "--> Ask for a rank you already hold.\n"
				+ "\n"
				+ "--> If the opponent has it, take the cards and continue your turn.\n"
				+ "\n"
				+ "--> If not,  Go Fish by drawing a card.\n"
				+ "\n"
				+ "--> If the drawn card matches your request you continue, otherwise, your turn ends.\n"
				+ "\n"
				+ "--> Create books when possible (4 matching cards).\n"
				+ "\n"
				+ "--> Draw five cards whenever your hand becomes empty (if the deck permits).\n"
				+ "\n"
				+ "--> Continue until all sets are formed or all cards are gone.\n"
				+ "\n"
				+ "--> Highest score wins. Good Game!";
	}
	
	//Testing to start the game as a desktop app after exporting as a runnable jar file
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> {
	        GoFishGUI gui = new GoFishGUI();
	        gui.setVisible(true);
	    });
	}

}
