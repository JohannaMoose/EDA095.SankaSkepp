package gui;
import theGame.GameHandler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerSkeppWindow {

	private static final int GRID_DIMENSION = 7;
	private JFrame frame;
	private JPanel gridPanel;
	private JTextField[][] fields;
	private GameHandler game; // GameHandler object for running getShoot method
								// for getting a shot from opponent
	private EnemySkeppWindow enemyWindow;
	
	public PlayerSkeppWindow(GameHandler game, EnemySkeppWindow enemyWindow) {
		this.game = game;
		this.enemyWindow = enemyWindow;
		frame = new JFrame("Player Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(300, 300));

		fields = new JTextField[GRID_DIMENSION][GRID_DIMENSION];
		gridPanel.setLayout(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
		setCleanBoard();

		frame.setLayout(new BorderLayout());
		frame.add(gridPanel);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.setJMenuBar(createMenuBar());
		
		frame.pack();
		frame.setVisible(true);

		frame.setResizable(false);
		frame.setLocation(600, 150);
		

		placeBoat(4, 'B', 'H', 1, 3);
	}

	public void setCleanBoard() {
		for (int i = 0; i < GRID_DIMENSION; i++) {
			for (int j = 0; j < GRID_DIMENSION; j++) {

				JTextField tb = new JTextField();
				Font font = new Font("Verdana", Font.BOLD, 14);
				tb.setFont(font);
				tb.setForeground(Color.BLACK);
				tb.setHorizontalAlignment(JTextField.CENTER);

				char c = (char) (64 + j);
				if (i == 0 && j > 0) {
					tb.setText("" + c);
				} else if (j == 0 && i > 0) {
					tb.setText("" + i);
				}
				tb.setBackground(Color.WHITE);

				tb.setEditable(false);

				fields[i][j] = tb;

				gridPanel.add(tb);
			}
		}
	}

	public JMenuBar createMenuBar()
	{ 
		// create the menu bar
		JMenuBar menuBar = new JMenuBar();

		JMenu menu;//menu
		JMenu sub;
		JMenuItem item;
		
		// build the Game menu
		menu = new JMenu("Game");
		menuBar.add(menu);
	
		sub = new JMenu("View");		
		menu.add(sub);
		
		//submenu of View
		item = new JMenuItem("Toggle Enemy Window");	
		item.addActionListener(new toggleEnemy());
		sub.add(item);
		
		item = new JMenuItem("Toggle Chat Window");
		sub.add(item);
		
		item = new JMenuItem("Toggle Prep Window");
		item.addActionListener(new togglePrep());
		sub.add(item);
		
		menu.add(sub);	
		return menuBar;
	}
	
	public void hitOrMiss(boolean shot, int row, int col) {
		JTextField tf = fields[row][col];
		if (shot == true) {
			tf.setBackground(Color.RED);
		} else {
			tf.setText("O");
		}
	}

	/**
	 * method for removing a boat from the board when placing a boat
	 * 
	 * @param id
	 */
	private void removeBoatFromBoard(char id) {
		for (int i = 1; i < GRID_DIMENSION; i++) {
			for (int j = 1; j < GRID_DIMENSION; j++) {
				JTextField tf = fields[i][j];
				String text = tf.getText();
				if (text.equals("" + id)) {
					tf.setText(" ");
					tf.setBackground(Color.WHITE);
				}
			}
		}
	}

	/**
	 * change to in param Boat instead of all single parameters. and change if
	 * statements to match
	 * 
	 * @param size
	 * @param boatCode
	 * @param align
	 * @param col
	 * @param row
	 */
	public void placeBoat(int size, char boatCode, char align, int row, int col ) {
		removeBoatFromBoard(boatCode);
		if (col > 0 && col < 7 && row < 7 && row > 0) {
			if (align == 'H' && (col + size - 1) < GRID_DIMENSION) {
				for (int i = 0; i < size; i++) {
					JTextField tf = fields[row][col + 1];
					tf.setText("" + boatCode);
					tf.setBackground(Color.GRAY);
				}
			} else if (align == 'V' && (row + size - 1) < GRID_DIMENSION) {
				for (int i = 0; i < size; i++) {
					JTextField tf = fields[row + i][col];
					tf.setText("" + boatCode);
					tf.setBackground(Color.GRAY);
				}
			} else {
				/**
				 * insert dialog message for error
				 */
				System.out.println("FEL DEN HAMNAR UTANFÖR");
			}
		} else {
			/**
			 * insert dialog box for error
			 */
			System.out.println("FEL: inmatad kordinat utanför brädet");
		}
	}
	
	private class toggleEnemy implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			enemyWindow.toggleWindow();
		}
	}
	
	private class togglePrep implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			PrepWindow.toggleWindow();
		}
	}
}
