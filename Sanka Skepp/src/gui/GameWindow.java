package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import theGame.GameHandler;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * class for setting up the game window and all panels
 * 
 * @author Bjarni
 * 
 */
public class GameWindow extends JFrame {

	private GameHandler game;
	private EnemySkeppPanel enemyPanel;
	private PrepPhasePanel prepPanel;
	private PlayerSkeppPanel playerPanel;
	private PlayerBottomPanel bottomPanel;
	private JLabel playerName;
	private JLabel enemyName;

	public GameWindow(GameHandler game) {
		super("Battleships");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.game = game;
		
		setup();
		
		this.setSize(700, 500);
		// pack();
		setVisible(true);
	}

	/**
	 * initial setup of the game window
	 */
	@SuppressWarnings("deprecation")
	private void setup() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2,3));
		this.add(mainPanel);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 2;
		c.gridheight = 3;

		c.gridx = 0;
		c.gridy = 1;
		enemyPanel = new EnemySkeppPanel();
		mainPanel.add(enemyPanel, c);
		enemyPanel.hide();
		prepPanel = new PrepPhasePanel();
		mainPanel.add(prepPanel, c);

		c.gridx = 1;
		playerPanel = new PlayerSkeppPanel(prepPanel);
		mainPanel.add(playerPanel);

		c.gridy = 2;
		c.gridx = 0;
		bottomPanel = new PlayerBottomPanel(enemyPanel);
		mainPanel.add(enemyPanel, c);

		c.gridy = 0;
		c.gridx = 0;
		enemyName = new JLabel("Enemy");
		mainPanel.add(enemyName, c);

		c.gridx = 1;
		playerName = new JLabel("Player");
		mainPanel.add(playerName, c);
	}

	/**
	 * method for setting the names of the players at the top of the window
	 */
	public void setPlayerNames() {
		enemyName.setText(game.getEnemyName());
		playerName.setText(game.getPlayerName());
	}
}
