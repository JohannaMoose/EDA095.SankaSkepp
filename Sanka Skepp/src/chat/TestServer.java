package chat;

import java.io.IOException;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new ChatServer();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
