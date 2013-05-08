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
	

}
