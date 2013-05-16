package gui;
import theGame.GameHandler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PrepWindow {

	private GameHandler game;
	private PlayerSkeppWindow playerWindow;
	private JRadioButton battleshipButton, frigateButton, ekaButton, vertical,
			horizontal;
	private JTextField cords;
	private static JFrame frame;

	public PrepWindow(GameHandler game, PlayerSkeppWindow playerWindow) {
		this.game = game;
		this.playerWindow = playerWindow;

		frame = new JFrame("Preperation Window");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// create radio buttons
		battleshipButton = new JRadioButton("B , size 4");
		frigateButton = new JRadioButton("F , size 3");
		ekaButton = new JRadioButton("E , size 2");

		horizontal = new JRadioButton("H");
		vertical = new JRadioButton("V");

		battleshipButton.setSelected(true);
		horizontal.setSelected(true);

		// put the radio buttons in groups
		ButtonGroup ships = new ButtonGroup();
		ships.add(battleshipButton);
		ships.add(frigateButton);
		ships.add(ekaButton);

		ButtonGroup align = new ButtonGroup();
		align.add(horizontal);
		align.add(vertical);

		// create and add into the ship radio button panel
		JPanel shipRadio = new JPanel(new GridLayout(0, 1));
		shipRadio.add(battleshipButton);
		shipRadio.add(frigateButton);
		shipRadio.add(ekaButton);

		// create and add into the ship alignment panel
		JPanel alignPanel = new JPanel(new GridLayout(0, 1));
		alignPanel.add(horizontal);
		alignPanel.add(vertical);

		// create the set panel with the coordinate input and set button
		JPanel setPanel = new JPanel(new GridLayout(0, 1));

		Font font = new Font("Verdana", Font.BOLD, 14);
		cords = new JTextField("CORDS");
		cords.setFont(font);
		cords.setForeground(Color.BLACK);
		cords.setHorizontalAlignment(JTextField.CENTER);

		JButton setBtn = new JButton("Set Boat");
		setBtn.addActionListener(new setBoatBtn());

		setPanel.add(cords);
		setPanel.add(setBtn);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(shipRadio, BorderLayout.LINE_START);
		contentPanel.add(alignPanel, BorderLayout.CENTER);
		contentPanel.add(setPanel, BorderLayout.LINE_END);
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		frame.setContentPane(contentPanel);
		contentPanel.setOpaque(true);
		frame.setPreferredSize(new Dimension(250, 150));
		frame.setLocation(930, 150);
		frame.pack();
		frame.setVisible(true);

	}

	public static void toggleWindow() {
		boolean visible = frame.isVisible();
		if (!visible) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}

	private class setBoatBtn implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			char align, boatCode;
			int size;
			if (vertical.isSelected()) {
				align = 'V';
			} else {
				align = 'H';
			}
			if (battleshipButton.isSelected()) {
				boatCode = 'B';
				size = 4;
			} else if (frigateButton.isSelected()) {
				boatCode = 'F';
				size = 3;
			} else {
				boatCode = 'E';
				size = 2;
			}
			int[] cor = parseCords(cords.getText());
			playerWindow.placeBoat(size, boatCode, align, cor[0], cor[1]);
			cords.setText("");
		}

		/**
		 * method for parsing input as A1 or 1A into integer coordinates
		 * 
		 * @param cordString
		 *            cord string gotten from the text field
		 * @return vector of 2 cords, on place 0 is row and 1 is col
		 */
		private int[] parseCords(String cordString){			
			int[] cor = new int[2];
			char[] chars = cordString.toCharArray();
			if(chars.length>2){
				return null;
			}
			for(char c: chars){
				switch(c){
				case '1':
					cor[0] = 1;
					break;
				case '2':
					cor[0] = 2;
					break;
				case '3':
					cor[0] = 3;
					break;
				case '4':
					cor[0] = 4;
					break;
				case '5':
					cor[0] = 5;
					break;
				case '6':
					cor[0] = 6;
					break;
				case 'A':
					cor[1] = 1;
					break;
				case 'B':
					cor[1] = 2;
					break;
				case 'C':
					cor[1] = 3;
					break;
				case 'D':
					cor[1] = 4;
					break;
				case 'E':
					cor[1] = 5;
					break;
				case 'F':
					cor[1] = 6;
					break;
				}	
			}
			return cor;
		}
	}

}
