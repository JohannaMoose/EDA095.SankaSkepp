package theGame;

public class GameHandler {
	
	private Board board;
	private PlayerClient pc;
	
	
	public GameHandler(PlayerClient pc, Board board){
		
	}
	
	public boolean isReady(){
		if(pc.isReady()){
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
