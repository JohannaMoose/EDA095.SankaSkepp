package theGame;



public class GameHandler {

	private GameClient client;
	

	public GameHandler(GameClient client) {
		this.client = client;
	}

	public void startGame() {
	
		play();
	}

	private void play() {

	}

	public boolean checkboat(BoatNew boat) {
		if (boat.getHits() <= 0) {
			return true;
		}
		return false;
	}

	public boolean GameOver() {

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				char temp = client.board.getSpot(i, j);
				if (temp != '0' && temp !='X') {
					return false;
				} else {

				}
			}

		}
		return true;

	}
	public boolean shoot(int row, int col){
		if(client.board.getSpot(row, col) == 'X'){
			System.out.println("Du har redan skjutit där");
			return false;
		}
		
		if(client.board.getSpot(row, col) != '0'){
			for(int i= 0; i < client.board.boatList.size(); i++){
				BoatNew temp = client.board.boatList.get(i);
				if(temp.getId() == client.board.getSpot(row, col)){
					client.board.boatList.get(i).oneHit();
					client.board.setHit(row, col);
					System.out.println("Du träffade");
					if(client.board.boatList.get(i).getHits()== 0){
						System.out.println("Båt " + client.board.boatList.get(i).getId() + " är sänkt!");
					}
					boolean over = GameOver();
					if(over == true){
						System.out.println("Du har träffat alla båtar och har vunnit!");
						return true;
					}
					return true;
				}
			}
			
		}
		System.out.println("Du missade");
		return false;
	}
}
