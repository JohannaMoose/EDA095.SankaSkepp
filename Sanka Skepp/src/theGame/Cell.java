package theGame;

public class Cell {
	private int boatCode;
	private int x;
	private int y;
		
	public Cell(int boatCode, int x, int y){
		this.boatCode=boatCode;
		this.x=x;
		this.y=y;
	}
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
