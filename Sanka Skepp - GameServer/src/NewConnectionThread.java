import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class NewConnectionThread extends Thread{

	ServerSocket socket;
	
	public NewConnectionThread(ServerSocket server){
		socket = server;
	}
	
	public void run() {
		while (true) {
			try {
				Socket player = socket.accept();
				GameServer.newConnection(player);
			} catch (IOException e) {
				System.out.println("Problem accepting new connection");
			}
		}
	}
}
