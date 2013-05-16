package theGame;

import java.util.ArrayList;



public class BoardNew {

	private char[][] bord;
	public ArrayList<BoatNew> boatList;

	public BoardNew() {
		bord = new char[7][7];
		for(int i = 0; i<7; i++){
			for(int j = 0; j<7; j++){
				bord[i][j]='0';
			}
		}
		boatList = new ArrayList<BoatNew>();
	}

	public boolean insertBoat(BoatNew boat) {
		int row = boat.getRow();
		int col = boat.getCol();
		if (boat.getPosition() == 'H') {
			if ((col + boat.getSize()) <= 6) {
				for (int j = col; j < (col + boat.getSize()); j++) {
					if (bord[row][j] != '0') {
						System.out.println("Båten får inte vara över en annan båt.");
						return false;
					} else {

					}
				}
				for (int j = col; j < (col + boat.getSize()); j++) {
					bord[row][j] = boat.getId();
				}
				System.out.println("Båten är placerad");
				boatList.add(boat);
				return true;
			}
			System.out.println("Båten är utanför spelplanen");
			return false;
		} else {
			if ((row + boat.getSize()) <= 6) {
				for (int j = row; j < (row + boat.getSize()); j++) {
					if (bord[j][col] != '0') {
						System.out.println("Båten får inte vara över en annan båt.");
						return false;
					} else {

					}
				}
				for (int j = row; j < (row + boat.getSize()); j++) {
					bord[j][col] = boat.getId();
					
				}
				boatList.add(boat);
				System.out.println("Båten är placerad");
				return true;
			}
			System.out.println("Båten är utanför spelplanen");
			return false;
		}

	}
	public void delBoat(BoatNew boat){
		if(boat.getPosition() == 'H'){
			for(int i = boat.getCol(); i < (boat.getCol() + boat.getSize()); i++){
				bord[boat.getRow()][i] = '0';
				}
			}else{
				for(int i = boat.getRow(); i < (boat.getRow() + boat.getSize()); i++){
					bord[i][boat.getCol()] = '0';
				}
		}
	}
	
	public char getSpot(int row, int col){
		char spot = bord[row][col];
		return spot;
	}
	public void setHit(int row, int col){
		bord[row][col] = 'X';
	}
}
