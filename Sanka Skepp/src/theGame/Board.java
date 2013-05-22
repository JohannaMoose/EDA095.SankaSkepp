package theGame;


public class Board {


	private Cell[][] board;

	public Board(){
		board = new Cell[6][6];
	}

	public void insertBoat(Boat b){

		int temp = b.getSize();
		int tempX = b.getStartCell().getX();
		int tempY = b.getStartCell().getY();

		if(b.getTag()=='v'){
			for(int i = 0; i<temp; i++){
				board[tempX][tempY]= new Cell(b.getId(), tempX, tempY);
				tempX++;
			}

		}else{
			for(int i = 0; i<temp; i++){
				board[tempX][tempY]= new Cell(b.getId(), tempX, tempY);
				tempY++;
			}
		}

	}



}
