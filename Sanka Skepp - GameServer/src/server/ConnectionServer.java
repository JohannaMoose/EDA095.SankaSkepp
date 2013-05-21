package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer extends Thread {
	ServerSocket socket;
	GameController controller; 
	
	public ConnectionServer(GameController controller)
	{
		
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
