package GameServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;



public class NewConnectionThread extends Thread{

	ServerSocket socket;
	
	Vector<Socket> connections;
	
	public NewConnectionThread(ServerSocket server){
		socket = server;
		connections = new Vector<Socket>();
	}
	
	public void run() {
		while (true) {
			try {
				Socket player = socket.accept();
				connections.add(player);
			} catch (IOException e) {
				System.out.println("Problem accepting new connection");
			}
		}
	}
	
	public Vector<Socket> getConnections(){
		return connections;
	}
	
	public void clearConnections(){
		connections.removeAllElements();
	}
}
