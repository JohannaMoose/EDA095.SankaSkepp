package gui;
import theGame.GameHandler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnemySkeppWindow {

	private static final int GRID_DIMENSION = 7;
	private JFrame frame;
	private JPanel gridPanel;
	private JTextField[][] fields;
	private GameHandler game; // GameHandler object for running shoot method for

	public EnemySkeppWindow(GameHandler game) {
		this.game = game;
		frame = new JFrame("Enemy Window");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(300, 300));

		JTextField cordsFire = new JTextField("CORDS");
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
		
		hitOrMiss(true,1,1);
		hitOrMiss(false,1,2);
	}

	public void toggleWindow() {
		boolean visible = frame.isVisible();
		if(!visible){
			frame.setVisible(true);
		}else{
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

	public void hitOrMiss(boolean shot, int y, int x) {
		JTextField tf = fields[y][x];
		if (shot == true) {
			tf.setText("X");
			tf.setBackground(Color.RED);
		} else {
			tf.setText("O");
			tf.setBackground(Color.CYAN);
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

		}
	}
}
