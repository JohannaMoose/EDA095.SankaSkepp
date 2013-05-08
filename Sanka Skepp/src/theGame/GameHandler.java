package theGame;
public class GameHandler {
	
	private Board board;
	private Player p1;
	private Player p2;
	
	
	public GameHandler(Player p1, Player p2, Board board){
		
	}
	
	public boolean isReady(){
		if(p1.isReady() && p2.isReady()){
			return true;
		}
		return false;
	}
	
	/**
	 * method for getting the enemy name
	 * from the klient perhaps?
	 */
	public String getEnemyName(){
		return "lala";
	}
	
	/**
	 * method for getting the players name
	 * from klient perhaps?
	 * @return
	 */
	public String getPlayerName(){
		return "lulu";
	}
	

}
