package theGame;
import java.net.Socket;


public class PlayerClient {
	private String name;
	private Socket socket;
	private boolean isReady=false;
	
	public PlayerClient(Socket s){
		this.socket=s;
		
	}
	
	public boolean isReady(){
		return isReady;
		
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	
	//Todo
	public String getEnemyName(){
		return name;
	}
	
}
