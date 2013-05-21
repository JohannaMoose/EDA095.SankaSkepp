package gui;

import theGame.GameHandler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnemySkeppWindow {

	private static final int GRID_DIMENSION = 7;
	private static JFrame frame;
	private JPanel gridPanel;
	private static JTextField[][] fields;
	private static JTextField cordsFire;
	private GameHandler game; // GameHandler object for running shoot method for

	public EnemySkeppWindow(GameHandler game) {
		this.game = game;
		frame = new JFrame("Enemy Window");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(300, 300));

		cordsFire = new JTextField("CORDS");
		Font font = new Font("Verdana", Font.BOLD, 14);
		cordsFire.setFont(font);
		cordsFire.setForeground(Color.BLACK);
		cordsFire.setHorizontalAlignment(JTextField.CENTER);

		JButton btnFire = new JButton("Fire!");
		btnFire.addActionListener(new fireBtn());

		JButton time = new JButton("00:00");
		time.setEnabled(true);
		time.setBackground(Color.WHITE);
		time.setFont(font);

		fields = new JTextField[GRID_DIMENSION][GRID_DIMENSION];
		gridPanel.setLayout(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
		setCleanBoard();

		buttonPanel.add(cordsFire);
		buttonPanel.add(btnFire);
		buttonPanel.add(time);
		frame.setLayout(new BorderLayout());
		frame.add(gridPanel);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

		frame.setLocation(270, 150);

	}

	public static void toggleWindow() {
		boolean visible = frame.isVisible();
		if (!visible) {
			cordsFire.setText("CORDS");
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
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

	public void hitOrMiss(boolean shot, int row, int col) {
		JTextField tf = fields[row][col];
		if (shot == true) {
			tf.setText("X");
			tf.setBackground(Color.RED);
		} else {
			tf.setText("O");
			tf.setBackground(Color.CYAN);
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
	
	/**
	 * action for firing a shot at the inputted coordinates and get answer and
	 * draw hit or miss on board
	 * 
	 * @author Bjarni
	 * 
	 */
	private class fireBtn implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			String cords = cordsFire.getText();
			int[] cor = PrepWindow.parseCords(cords);
			if (cor != null) {
				String command = game.fire("" + cor[0] + cor[1]);				//Nytt!!!
				if (command.equals("Winner") || command.equals("Loser")
						|| command.equals("Draw")) {
					messageDialog(command);
				}else if(command.equals("Hit")){
					hitOrMiss(true,cor[0],cor[1]);
				}else{
					hitOrMiss(false,cor[0],cor[1]);
				}
				
			} else {
				// om cor blir null är det felaktigt inmatade koordinater
				errorDialog("Inmatade koordinater felaktiga. Försök igen.");
			}
			cordsFire.setText("");
		}

		private void messageDialog(String message) {
			JOptionPane.showMessageDialog(frame, message, "Game is over.\n Result: " + message,
					JOptionPane.INFORMATION_MESSAGE);
		}

		private void errorDialog(String message) {
			JOptionPane.showMessageDialog(frame, message, "Coordinate Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
