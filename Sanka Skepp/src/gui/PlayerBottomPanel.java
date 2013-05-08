package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PlayerBottomPanel {
/**
 * panel class for the coordinate fire text box, fire button
 * timer lable for showing time left of the round
 * 
 */
	private EnemySkeppPanel enemyPanel;
	
	public PlayerBottomPanel(EnemySkeppPanel enemyPanel){
		this.enemyPanel = enemyPanel;
	}
	
	private class fireButton implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			/**
			 * code for sending coordiantes into game code for and recieving a hit or miss order
			 * 
			 * and telling the enemy panel to order a hit or miss accordingly
			 */
		}
		
	}
}
