package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Player extends Thread {
	private Socket socket;
	private OutputStream toPlayer;
	private BufferedReader fromPlayer;
	private Boolean idle;
	private GameController controller;

	public Player(Socket playerSocket, GameController controller) {
		socket = playerSocket;
		this.controller = controller;
		try {
			toPlayer = playerSocket.getOutputStream();
			fromPlayer = new BufferedReader(new InputStreamReader(
					playerSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		idle = true;
	}

	public void run() {
		System.out.println("Ny spelare");
		while (true) {
			while (idle) {
				try {
					String command = fromPlayer.readLine();

					if (command.equals("New")) {
						idle = false;
						controller.addNewPlayerToPool(this);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setIdle(boolean idle) {
		this.idle = idle;
	}

	public String getCommand() throws IOException {
		String temp = fromPlayer.readLine();
		System.out.println("Incoming command " + temp);
		return temp;
	}

	public void sendMsgToPlayer(String message) throws IOException {
		toPlayer.write(message.getBytes());
		toPlayer.flush();
	}

	public Socket getPlayersSocket() {
		return socket;
	}
}
