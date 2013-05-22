package server;

import java.util.Vector;

public class GameController {
	private Vector<Player> playersWaiting; 
	private Vector<Player> idlePlayers; 
	private Vector<Game> goingGames;
	
	public GameController()
	{
		playersWaiting = new Vector<Player>();
		idlePlayers = new Vector<Player>();
		goingGames = new Vector<Game>();
		System.out.println("Controller up and running");
	}
	
	public synchronized void addNewIdelPlayer(Player newPlayer)
	{
		idlePlayers.add(newPlayer);
	}
	
	public synchronized void addNewPlayerToPool(Player newPlayer)
	{
		idlePlayers.remove(newPlayer);
		System.out.println("Ny spelare i poolen");
		if(playersWaiting.size() > 0){
			matchWithOpponent(newPlayer);
		}else {
			playersWaiting.add(newPlayer);
		}
	}
	
	private synchronized void matchWithOpponent(Player player)
	{
		Player opponent = playersWaiting.remove(0);
		playersWaiting.trimToSize();
		
		Game game = new Game(player, opponent, this);
		game.start();
		goingGames.add(game);
	}
	
	public synchronized void GameEnded(Game game)
	{
		goingGames.remove(game);
		
		Player player1 = game.getPlayer1();
		Player player2 = game.getPlayer2();
		
		player1.setIdle(true);
		player2.setIdle(true);
		
		idlePlayers.add(player1);
		idlePlayers.add(player2);
	}
	
	public synchronized void disconnectPlayer(Player player)
	{
		idlePlayers.remove(player);
		playersWaiting.remove(player);
		idlePlayers.trimToSize();
		playersWaiting.trimToSize();
		System.out.println("Player disconnected");
	}
}
