package theGame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class PlayerClient {
	private String name;
	
	private Socket socket;
	private boolean isReady=false;
	private String serverName;
	private int portNbr;

	public PlayerClient(Socket s, String serverName, int portNbr, String name){
		this.socket=s;
		this.name=name;
		this.serverName = serverName;
		this.portNbr=portNbr;
		try
		{
			System.out.println("Connecting to " + serverName
					+ " on port " + portNbr);
			Socket client = new Socket(serverName, portNbr);
			System.out.println("Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out =
					new DataOutputStream(outToServer);

			out.writeUTF("Hello from "
					+ client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in =
					new DataInputStream(inFromServer);
			System.out.println("Server says " + in.readUTF());
			client.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}



	public boolean isReady(){
		return isReady;

	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}


	//Todo
	public String getEnemyName(){
		return name;
	}

}
