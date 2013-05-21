package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Player {
	private Socket socket; 
	private OutputStream toPlayer; 
	private BufferedReader fromPlayer; 
	private Boolean idle;
	private GameController controller;
	
	public Player(Socket playerSocket, GameController controller)
	{
		socket = playerSocket; 
		try {
			toPlayer = playerSocket.getOutputStream();
			fromPlayer = new BufferedReader(new InputStreamReader(
					playerSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		idle = true;
		runIdle();
	}
	
	private void runIdle()
	{
		while(idle)
		{
			try {
				String command = fromPlayer.readLine();
				if(command == "New")
				{
					idle = false;
					controller.addNewPlayerToPool(this);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getCommand() throws IOException
	{
		return fromPlayer.readLine();
	}
	
	public void sendMsgToPlayer(String message) throws IOException
	{
		toPlayer.write(message.getBytes());
	}
	
	public Socket getPlayersSocket()
	{
		return socket;
	}
}
