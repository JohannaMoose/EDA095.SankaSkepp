package game_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class FindOpponent {
	Socket connectionToServer;
	BufferedReader fromServer;
	
	public FindOpponent(String serverAddress)
	{
		try {
			connectionToServer = new Socket(serverAddress, 
					GameServerConfig.GAME_SERVER_PORT);
			fromServer = new BufferedReader
					(new InputStreamReader(connectionToServer.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Invalid address for the server");
		} catch (IOException e) {
			System.out.println("Problem with connecting to the server");
		}
	}
	
	public String getOpponentsIPaddressFromServer()
	{
		try {
			String message = fromServer.readLine();
			if (message != null) {
				if(message.startsWith("Opponent"))
				{
					message = message.substring(
							GameServerConfig.FOUND_OPPONENT_COMMAND.length());
					String[] msgParts = message.split(" = ");
					if(msgParts[0].equals("IP"))
						return msgParts[1];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
}
