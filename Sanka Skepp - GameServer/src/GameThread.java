import java.net.*;
import java.io.*;

public class GameThread extends Thread {
	OutputStream p1Out;
	OutputStream p2Out;
	BufferedReader p1In;
	BufferedReader p2In;
	private boolean gameEnd;
	Socket player1; 
	Socket player2;

	public GameThread(Socket player1, Socket player2) {
		try {
			p1Out = player1.getOutputStream();
			p2Out = player2.getOutputStream();
			p1In = new BufferedReader(new InputStreamReader(
					player1.getInputStream()));
			p2In = new BufferedReader(new InputStreamReader(
					player2.getInputStream()));
		} catch (IOException e) {
			System.out.println("Problem setting up streams to players");
			e.printStackTrace();
		}
		
		this.player1 = player1; 
		this.player2 = player2;
		gameEnd = false;
	}

	public void run() {
		try {	
		while (true) {
				String p1Shot;
				
					p1Shot = p1In.readLine();
				String p2Shot = p2In.readLine();

				p1Out.write(p2Shot.getBytes());
				p2Out.write(p1Shot.getBytes());
				
				String p1Command = p1In.readLine();
				String p2Command = p2In.readLine();
				
				boolean p1Dead = p1Command.equals("Dead");
				boolean p2Dead = p2Command.equals("Dead");
				if(p1Dead && p2Dead){
					p2Out.write("Draw".getBytes());
					p1Out.write("Draw".getBytes());
					gameEnd = true;
				}else if (p1Dead){
					p1Out.write("Loser".getBytes());
					p2Out.write("Winner".getBytes());
					gameEnd = true;
				}else if (p2Dead){
					p1Out.write("Winner".getBytes());
					p2Out.write("Loser".getBytes());
					gameEnd = true;
				} else {
					p1Out.write(p2Command.getBytes());
					p2Out.write(p1Command.getBytes());
				}
				if(gameEnd){
					GameServer.gameEnd(this, player1, player2);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
