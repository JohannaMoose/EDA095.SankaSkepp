package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerSkeppPanel {

	private JPanel gridPanel;
	private PrepPhasePanel prepPanel;
	private boolean prep;

	public PlayerSkeppPanel(PrepPhasePanel prepPanel) {
		this.prepPanel = prepPanel;
		
		gridPanel = new JPanel();
		gridPanel.setPreferredSize(new Dimension(300, 300));
		gridPanel.setLayout(new GridLayout(7, 7));
		setCleanBoard();

		prep = true;
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
				}
				tb.setEditable(false);
				gridPanel.add(tb);
			}
		}
	}

	public void prepDone() {
		prep = false;
	}

	private class PrepPhaseAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (prep == true) {
				/**
				 * insert code for a click action on the text field to set a
				 * boat checking the selected type and vertical/horizontal from
				 * the prepPhasePanel and sending it into the game code
				 */
			}
		}

	}
}
