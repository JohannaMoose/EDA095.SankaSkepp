package theGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import GameServer.GameServer;


import chat.ChatServer;


public class GameClient {
	private String userName;
	private Socket socket;
	private OutputStream outStream;//skickar till server 
	private BufferedReader inStream;//data från server
	public BoardNew board;

	
	public GameClient(String userName){
		this.userName = userName;
		board = new BoardNew();
		
		connectToServer();
		System.out.println("Client is running");
	}
	
	public String getUser(){
		return userName;
	}
	
	private void connectToServer(){
	try {
		socket = new Socket("localhost", GameServer.GAME_SERVER_PORT);
		outStream = socket.getOutputStream();
		inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (UnknownHostException e) {
		System.out.println("Error in GameClient");
		e.printStackTrace();
	} catch (IOException e) {
		System.out.println("Error in GameClient");
		e.printStackTrace();
	}
	
}

public void sendShoot(String shoot){
	if(!shoot.endsWith("\r\n"))
	{
		shoot = shoot + "\r\n";
	}
	String stringToSend = shoot;
	try {
		System.out.println("Skickar meddelande till servern: " + stringToSend);
		outStream.write(stringToSend.getBytes());
		outStream.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public String getShoot(){
	try {
		return inStream.readLine();
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}

public void closeServerConnection()
{
	try {
		socket.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public static void main(String[] args){
	GameClient client = new GameClient("Anna");
}
}
