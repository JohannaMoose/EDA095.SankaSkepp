package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	private ServerSocket socket;
	private ArrayList<ChatServerThread> clientConnections;
	public static int CHAT_SERVER_PORT = 31345;

	public ChatServer() throws IOException {
		socket = new ServerSocket(CHAT_SERVER_PORT);
		clientConnections = new ArrayList<ChatServerThread>();

		runServer();
	}

	private void runServer() {
		while (true) {
			try {
				System.out.println("Server startar");
				Socket connection = socket.accept();
				ChatServerThread chatThread = new ChatServerThread(connection,
						this);
				clientConnections.add(chatThread);
				chatThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessageToClients(String message) {
		for (ChatServerThread t : clientConnections) {
			t.sendMessageToClient(message);
		}
	}

	public void removeClientFromServer(ChatServerThread clientConnection) {
		
		clientConnections.remove(clientConnection);
	}

	public void closeServer() {
		try {
			for (ChatServerThread t : clientConnections) {
				removeClientFromServer(t);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
