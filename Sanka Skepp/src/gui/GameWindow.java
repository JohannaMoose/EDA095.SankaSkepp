package gui;
import theGame.Game;

import javax.swing.JFrame;


/**
 *  class for setting up the game window and all panels
 * @author Bjarni
 *
 */
public class GameWindow extends JFrame{
	
	private Game game;
	
	public GameWindow(Game game){
		this.game = game;
		setup();
	}
	
	private void setup(){
		
	}
}
