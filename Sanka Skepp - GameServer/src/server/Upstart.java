package server;

public class Upstart {
	
	public static void main(String[] args)
	{
		GameController controller = new GameController();
		ConnectionServer connection = new ConnectionServer(controller);
		connection.run();
		
		ChatServer chat = new ChatServer();
		chat.run();
	}
}
