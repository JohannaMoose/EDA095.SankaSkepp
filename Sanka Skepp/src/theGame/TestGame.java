package theGame;

public class TestGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameClient client = new GameClient("Kristina");
		GameHandler g = new GameHandler(client);
		BoatNew b1 = new BoatNew(2, 2, 'H', 2, 'A');
		BoatNew b2 = new BoatNew(4, 4, 'v', 2, 'B');
		
		BoatNew b3 = new BoatNew(6, 6, 'V', 2, 'C');
		BoatNew b4 = new BoatNew(4, 4, 'v', 2, 'B');
		
		
		
		client.board.insertBoat(b1);
		client.board.insertBoat(b2);
		
		client.board.insertBoat(b3);
		client.board.insertBoat(b4);
		
		for(int i=0; i<7; i++){
			System.out.println(" ");
			for(int j=0; j<7; j++){
				char temp = client.board.getSpot(i, j);
				System.out.print(temp + " ");
			}
		}
		
		g.shoot(1, 2);
		
		g.shoot(2, 2);
		
		for(int i=0; i<7; i++){
			System.out.println(" ");
			for(int j=0; j<7; j++){
				char temp = client.board.getSpot(i, j);
				System.out.print(temp + " ");
			}
	}
		g.shoot(2, 3);
		
		for(int i=0; i<7; i++){
			System.out.println(" ");
			for(int j=0; j<7; j++){
				char temp = client.board.getSpot(i, j);
				System.out.print(temp + " ");
			}
	
	}
		g.shoot(4, 4);
		g.shoot(5, 4);
		
		for(int i=0; i<7; i++){
			System.out.println(" ");
			for(int j=0; j<7; j++){
				char temp = client.board.getSpot(i, j);
				System.out.print(temp + " ");
			}
	}
}
}
