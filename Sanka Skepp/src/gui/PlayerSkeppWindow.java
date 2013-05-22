package gui;
import theGame.BoatNew;
import theGame.GameClient;
import theGame.GameHandler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayerSkeppWindow {

	private static final int GRID_DIMENSION = 7;
	private static JFrame frame;
	private JPanel gridPanel;
	private static JTextField[][] fields;
	private GameHandler game; // GameHandler object for running getShoot method
								// for getting a shot from opponent
	
	public PlayerSkeppWindow(GameHandler game) {
		this.game = game;
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

		JMenu game;//menu
		JMenu view;
		JMenuItem item;
	
		/**
		 * to do
		 * add code for having other options in the menu
		 * eg. "Give Up", "Find New Opponent", "Change Username"
		 */
		// build the Game menu
		game = new JMenu("Game");
		menuBar.add(game);
	
		view = new JMenu("View");		
		menuBar.add(view);

		item = new JMenuItem("Find New Opponent");
		item.addActionListener(new newOpponent());
		game.add(item);
		
		item = new JMenuItem("Surrender");
		item.addActionListener(new surrender());
		game.add(item);
		
		//menu options for View
		item = new JMenuItem("Toggle Enemy Window");	
		item.addActionListener(new toggleEnemy());
		view.add(item);
		
		item = new JMenuItem("Toggle Chat Window");
		view.add(item);
		
		item = new JMenuItem("Toggle Prep Window");
		item.addActionListener(new togglePrep());
		view.add(item);
		
		
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
	public void placeBoat(BoatNew boat) {
		removeBoatFromBoard(boat.getId());
		if (boat.getCol() > 0 && boat.getCol() < 7 && boat.getRow() < 7 && boat.getRow() > 0) {
			if (boat.getPosition() == 'H' && (boat.getCol() + boat.getSize() - 1) < GRID_DIMENSION) {
				for (int i = 0; i < boat.getSize(); i++) {
					JTextField tf = fields[boat.getRow()][boat.getCol() + i];
					tf.setText("" + boat.getId());
					tf.setBackground(Color.GRAY);
				}
			} else if (boat.getPosition() == 'V' && (boat.getRow() + boat.getSize() - 1) < GRID_DIMENSION) {
				for (int i = 0; i < boat.getSize(); i++) {
					JTextField tf = fields[boat.getRow() + i][boat.getCol()];
					tf.setText("" + boat.getId());
					tf.setBackground(Color.GRAY);
				}
			} else {
				// dialog popup error
				errorDialog("Delar av båten hamnar utanför brädet. Försök en annan koordinat.");
			}
		} else {
			// dialog popup error
			errorDialog("Den inmatade koordinaten finns ej på brädet. Försök en annan koordinat.");
			System.out.println("FEL: inmatad kordinat utanför brädet");
		}
	}
	
	public static void cleanBoard(){
		for(int i = 1; i < 7; i++){
			for(int j = 1; j < 7; j++){
				fields[i][j].setText(" ");
				fields[i][j].setBackground(Color.WHITE);
			}
		}
	}
	
	public static void errorDialog(String message){
		JOptionPane.showMessageDialog(frame,
			    message,
			    "Coordinate Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private class toggleEnemy implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			EnemySkeppWindow.toggleWindow();
		}
	}
	
	private class togglePrep implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			PrepWindow.toggleWindow();
		}
	}
	
	private class newOpponent implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			GameClient.sendCommand("New");
			PlayerSkeppWindow.cleanBoard();
			EnemySkeppWindow.cleanBoard();
			
		}
	}
	
	private class surrender implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			GameClient.sendCommand("Dead");
		}
	}
}
