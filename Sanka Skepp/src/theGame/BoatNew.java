package theGame;




public class BoatNew {
	private char id;
	private char position;
	private int row;
	private int col;
	private int size;
	private int nbrHits;

	
	
	public BoatNew(int row, int col, char position, int size, char id){
		this.row = row;
		this.col = col;
		this.position = position;
		this.size = size;
		this.nbrHits = size;
		this.id = id;
		}
	
	
	
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	
	public char getPosition(){
		return position;
	}
	
	public int getSize(){
		return size;
	}
	public char getId(){
		return id;
	}
	
	public int getHits(){
		return nbrHits;
	}
	public void oneHit(){
		this.nbrHits = nbrHits - 1;
	}
	
}

