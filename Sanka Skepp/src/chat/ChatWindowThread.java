package chat;

import gui.ChatWindow;

public class ChatWindowThread extends Thread {
	private ChatClient client;
	private int funktion;
	private ChatWindow window;

	public static int SEND_TO_SERVER=1; 
	public static int WRITE_TO_WINDOW = 2; 
	
	public ChatWindowThread(ChatClient client, int funktion, ChatWindow window){
		this.client = client;
		this.funktion = funktion;
		this.window = window; 
	}
	
	public void run(){
		if(funktion == SEND_TO_SERVER)
			readFromChat();
		else if(funktion == WRITE_TO_WINDOW)
			writeChat();
		else 
			System.out.println("Kommando inte igenkänt");
	}
	
	private void readFromChat(){
		while(true)
		{
			String msg = window.GetMessageToSendToServer();
			window.resetWindow();
			if(msg != null)
			{
				System.out.println("Skickar msg: " + msg);
				client.sendMessage(msg);
			}
		}
	}
	
	private void writeChat(){
		while(true)
		{
			String msg = client.getMessage();
			if(msg != null)
			{
				window.PutMessageInWindow(msg);
			}
		}
	}

}
