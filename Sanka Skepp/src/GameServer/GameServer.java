package GameServer;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class GameServer {
	
	/**
	 * skapa en PlayerThread klass som håller spelarnas sockets när de inte är i en GameThread
	 * detta istället för sockets.
	 */
	static Vector<Socket> waitingPlayers;
	private Vector<Socket> playersForGames;
	static Vector<GameThread> gamesInProgress;

	NewConnectionThread conn;
	
	ServerSocket socket;

	public static int GAME_SERVER_PORT = 31245;

	public GameServer() {
		waitingPlayers = new Vector<Socket>();
		playersForGames = new Vector<Socket>();
		gamesInProgress = new Vector<GameThread>();
		try {
			socket = new ServerSocket(GAME_SERVER_PORT);
		} catch (IOException e) {
			System.out.println("Server couldn't connect to port: "
					+ GAME_SERVER_PORT);
		}
		System.out.println("Server is up and running");
		conn = new NewConnectionThread(socket);
		conn.start();
		serverRunning();
	}

	@SuppressWarnings("deprecation")
	public static void gameEnd(GameThread game, Socket player1, Socket player2) {
		waitingPlayers.add(player1);
		waitingPlayers.add(player2);
		gamesInProgress.remove(game);
		game.stop();
	}

	private void serverRunning() {
		while (true) {
			updatePlayerList();
			Iterator<Socket> iter = waitingPlayers.iterator();
			while (iter.hasNext()) {
				Socket player = iter.next();
				try {
					BufferedReader pIn = new BufferedReader(
							new InputStreamReader(player.getInputStream()));
					String command = null;
					if (pIn.ready()) {
						command = pIn.readLine();
					}
					if (command != null && command.equals("New")) {
						waitingPlayers.remove(player);
						playersForGames.add(player);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Iterator<Socket> gameIter = playersForGames.iterator();
			while (gameIter.hasNext()) {
				Socket player1 = gameIter.next();
				if (gameIter.hasNext()) {
					Socket player2 = gameIter.next();
					gamesInProgress.add(new GameThread(player1, player2));
					playersForGames.remove(player1);
					playersForGames.remove(player2);
				}
			}
		}
	}
	
	public void updatePlayerList(){
		waitingPlayers.addAll(conn.getConnections());
		conn.clearConnections();
	}
}
