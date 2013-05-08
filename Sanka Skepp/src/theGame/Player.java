package theGame;
import java.net.Socket;


public class Player {

	private Socket socket;
	
	public Player(Socket s){
		this.socket=s;
		
	}
	
	public boolean isReady(){
		return true;
		
	}
	
}
