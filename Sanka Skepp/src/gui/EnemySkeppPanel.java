package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnemySkeppPanel {

	private JPanel gridPanel;
	
	public EnemySkeppPanel() {
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(300, 300));
		gridPanel.setLayout(new GridLayout(7, 7));
		setCleanBoard();
	}

	public void setCleanBoard() {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				JTextField tb = new JTextField();
				Font font = new Font("Verdana", Font.BOLD, 14);
				tb.setFont(font);
				tb.setForeground(Color.BLACK);
				tb.setHorizontalAlignment(JTextField.CENTER);
				tb.setBackground(Color.WHITE);
				if (j == 0 && i > 0) {
					int nbr = i + 1;
					String s = "" + nbr;
					tb.setText(s);
				} else if (i == 0 && j > 0) {
					int nbr = j - 1;
					char c = (char) ('A' + nbr);
					tb.setText("" + c);
				} else {
					tb.setText(" ");
				}
				tb.setEditable(false);
				gridPanel.add(tb);
			}
		}
	}
	
	public void hit(int x, int y){
		JLabel label = (JLabel) gridPanel.getComponentAt(x, y);
		label.setText("X");
		/**
		 * maybe say if a ship has been sunk or not. discuss??
		 */
	}
	
	public void miss(int x, int y){
		JLabel label = (JLabel) gridPanel.getComponentAt(x, y);
		label.setText("O");
	}
}
