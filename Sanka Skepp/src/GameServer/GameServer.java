package GameServer;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class GameServer {
	Vector<Socket> waitingPlayers;
	ServerSocket socket;
	
	public static int GAME_SERVER_PORT = 31245;
	
	public GameServer()
	{
		waitingPlayers = new Vector<Socket>();
		try {
			socket = new ServerSocket(GAME_SERVER_PORT);
		} catch (IOException e) {
			System.out.println("Server couldn't connect to port: " + GAME_SERVER_PORT);
		}
		System.out.println("Server is up and running");
		letUsersConnect();
	}
	
	private void letUsersConnect()
	{
		while(true)
		{
			try {
				Socket player = socket.accept();
				if(waitingPlayers.size() > 1)
					matchWithPlayer(player);
				else 
					waitingPlayers.add(player);
			} catch (IOException e) {
				System.out.println("Problem accepting new connection");
			}
		}
	}
	
	private void matchWithPlayer(Socket player1)
	{
		Socket player2 = waitingPlayers.get(0);
		waitingPlayers.remove(0);
		informPlayerAboutOpponent(player1, player2);
		informPlayerAboutOpponent(player2, player1);
	}
	
	private void informPlayerAboutOpponent(Socket player, Socket opponent)
	{
		BufferedOutputStream toPlayer;
		try {
			toPlayer = new BufferedOutputStream(
					player.getOutputStream());
			toPlayer.write(getConnectionForOpponent(opponent));
		} catch (IOException e) {
			System.out.println("Problem with the stream to inform player about opponent");
		}
	}
	
	private byte[] getConnectionForOpponent(Socket opponent)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Opponent:");
		sb.append("IP = ");
		sb.append(opponent.getInetAddress().getAddress());
		
		return sb.toString().getBytes();
	}
}
