package Startup;

import java.util.Scanner;

import chat.ChatProgram;
import gui.EnemySkeppWindow;
import gui.PlayerSkeppWindow;
import gui.PrepWindow;
import theGame.GameClient;
import theGame.GameHandler;

public class Startup {

	/**
	 * main method for running the full game
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new  Scanner (System.in);
		String name = " " + scan.nextLine();
		scan.close();
		
		GameHandler gh = new GameHandler(new GameClient(name));
		EnemySkeppWindow w2 = new EnemySkeppWindow(gh);
		PlayerSkeppWindow w = new PlayerSkeppWindow(gh);
		PrepWindow prep = new PrepWindow(gh,w);

		new ChatProgram(name);
	}
}
