package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatServerThread extends Thread {
	Socket connection;
	ChatServer server;
	OutputStream toClient;
	BufferedReader fromClient;
	char firstCharInString;

	public ChatServerThread(Socket connection, ChatServer server) {
		this.connection = connection;
		this.server = server;

		try {
			fromClient = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			toClient = connection.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		while (IsAlive()) {
			try {
				String message = fromClient.readLine();
				if (message != null) {
					server.sendMessageToClients(message);
				}
				Thread.yield();
			} catch (IOException e) {
			}

		}
		killThread();
	}

	public void killThread() {
		try{
			connection.close();
			server.removeClientFromServer(this);
		}catch(IOException e){
			
		}
	}
	
	private boolean IsAlive(){
		int a;
		try {
			a = connection.getInputStream().read();
			if(a != -1){
				firstCharInString = (char)a;
				return true;
			}
			else{
				return false;
			}
		} catch (IOException e) {
			System.out.println("Problem checking if client is alive");
			return false;
		}
		
		
		
	}

	public void sendMessageToClient(String message) {
		if(!message.endsWith("\n")){
			message = message + "\n";
		}
		try {
			toClient.write(message.getBytes());
			toClient.flush();
		} catch (IOException e) {
			if(!IsAlive()){
				killThread();
				System.out.println("Client is disconnected");
			}
		}
	}
}
