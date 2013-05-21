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
	}
	
	public void addNewIdelPlayer(Player newPlayer)
	{
		idlePlayers.add(newPlayer);
	}
	
	public void addNewPlayerToPool(Player newPlayer)
	{
		idlePlayers.remove(newPlayer);
		
		if(playersWaiting.size() > 1){
			matchWithOpponent(newPlayer);
		}else {
			playersWaiting.add(newPlayer);
		}
	}
	
	private void matchWithOpponent(Player player)
	{
		Player opponent = playersWaiting.remove(0);
		playersWaiting.trimToSize();
		
		Game game = new Game(player, opponent, this);
		game.run();
		goingGames.add(game);
	}
	
	public void GameEnded(Game game)
	{
		goingGames.remove(game);
		idlePlayers.add(game.getPlayer1());
		idlePlayers.add(game.getPlayer2());
	}
}
