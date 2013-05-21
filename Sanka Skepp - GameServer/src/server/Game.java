package server;

import java.io.IOException;

public class Game extends Thread {

	GameController controller;
	Player player1; 
	Player player2;
	Boolean gameEnd;
	
	public Game(Player player1, Player player2, GameController controller)
	{
		this.player1 = player1; 
		this.player2 = player2;
	}
	
	public void run()
	{
		while(!gameEnd){
		try {
			playersShoot();
			handleResultOfShots();
			if(gameEnd)
			{
				controller.GameEnded(this);
			}
		} catch (IOException e) {
			System.out.println("Problems with shot");
			e.printStackTrace();
		}
		}
	}

	private void playersShoot() throws IOException {
		String shotP1 = player1.getCommand();
		String shotP2 = player2.getCommand();
		
		sendToOponent(shotP1, shotP2);
	}

	private void handleResultOfShots() throws IOException {
		String p1Command = player1.getCommand();
		String p2Command = player2.getCommand();
		
		boolean p1Dead = p1Command.equals("Dead");
		boolean p2Dead = p2Command.equals("Dead");
		
		if(p1Dead && p2Dead){
			sendMsgToBoth("Draw");
			gameEnd = true;
		}else if (p1Dead){
			player1.sendMsgToPlayer("Loser");
			player2.sendMsgToPlayer("Winner");
			gameEnd = true;
		}else if (p2Dead){
			player1.sendMsgToPlayer("Winner");
			player2.sendMsgToPlayer("Loser");
			gameEnd = true;
		} else {
			sendToOponent(p1Command, p2Command);
		}
	}

	private void sendToOponent(String commandFromP1, String commandFromP2) throws IOException {
		player1.sendMsgToPlayer(commandFromP2);
		player2.sendMsgToPlayer(commandFromP1);
	}
	
	private void sendMsgToBoth(String msg) throws IOException
	{
		player1.sendMsgToPlayer(msg);
		player2.sendMsgToPlayer(msg);
	}
	
	public Player getPlayer1()
	{
		return player1;
	}
	
	public Player getPlayer2()
	{
		return player2;
	}
	
}
