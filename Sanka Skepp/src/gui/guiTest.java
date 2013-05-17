package gui;

import theGame.GameHandler;

public class guiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameHandler gh = new GameHandler(null);

		EnemySkeppWindow w2 = new EnemySkeppWindow(gh);
		PlayerSkeppWindow w = new PlayerSkeppWindow(gh);
		PrepWindow prep = new PrepWindow(gh,w);
	}

}
