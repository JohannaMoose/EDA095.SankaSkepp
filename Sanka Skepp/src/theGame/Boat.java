package theGame;

public class Boat {
	
	private int size;
	private char tag;
	private boolean isShot = false;
	private Cell startCell;
	private int id;
	
	public Boat(char tag, Cell startCell, int id){
		this.tag=tag;
		this.startCell=startCell;
		this.id=id;
	}
	
	public boolean isShot(){
		return isShot;
	}
	
	public int getId(){
		return id;
	}
	
	public Cell getStartCell(){
		return startCell;
	}
	
	public char getTag(){
		return tag;
	}
	
	public int getSize(){
		return size;
	}
	
}

