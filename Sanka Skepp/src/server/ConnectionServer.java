package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer extends Thread {
	ServerSocket socket;
	GameController controller; 
	
	public static int GAME_SERVER_PORT = 31245;
	
	public ConnectionServer(GameController controller)
	{
		this.controller = controller; 
		try {
			socket = new ServerSocket(GAME_SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while(true)
		{
			try {
				Socket connection = socket.accept();
				controller.addNewIdelPlayer(new Player(connection, controller));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
