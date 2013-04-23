package chat;


import java.util.Scanner;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new  Scanner (System.in);
		
		String name = " " + scan.nextLine();
		scan.close();
		new ChatProgram(name);
		
		
	}

}
