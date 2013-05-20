package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import chat.ChatServer;

public class TestClient {
	
	public static Socket socket;
	public static OutputStream outStream;
	public static BufferedReader inStream;
	
	public static void main(String[] args) {
		
		try {
			connectToServer("localhost");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Scanner scan = new Scanner(System.in);
		while(true){
			String send = scan.nextLine();
			try {
				outStream.write(send.getBytes());
				String recieve = inStream.readLine();
				System.out.println(recieve);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void connectToServer(String ipAddress) throws UnknownHostException, IOException
	{
		socket = new Socket(ipAddress, GameServer.GAME_SERVER_PORT);
		outStream = socket.getOutputStream();
		inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
