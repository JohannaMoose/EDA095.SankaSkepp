package theGame;

public class Boat {
	
	private int size;
	private char tag;
	private boolean isShot = false;
	
	public Boat(char tag){
		this.tag=tag;
	}
	
	public boolean isShot(){
		return isShot;
	}
	
	public char getTag(){
		return tag;
	}
	
	public int getSize(){
		return size;
	}
	
}

