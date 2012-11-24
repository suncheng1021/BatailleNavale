package game.ships;

public abstract class Ship {
	
	private String name;
	private int size;
	private int x, y;
	private Orientation orientation;
	private boolean hits[];
	
	public enum Orientation {Vertical, Horizontal};
	
	public abstract String toString();
	
	Ship(String name, int size) {
		this.name = name;
		this.size = size;
		this.x = 0;
		this.y = 0;
		this.orientation = Ship.Orientation.Horizontal;
		this.hits = new boolean[this.size];
		for (int i=0; i<this.hits.length; i++)
			this.hits[i]=false;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isAtPosition(int x, int y) {
		if ((this.orientation == Ship.Orientation.Horizontal) && (y == this.y) && (x >= this.x) && (x < (this.x) + size))
			return true;
		else if ((this.orientation == Ship.Orientation.Vertical) && (x == this.x) && (y >= this.y) && (y < (this.y) + size))
			return true;
		return false;
	}
	
	public Ship.Orientation getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(Ship.Orientation orientation) {
		this.orientation = orientation; 
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean hit(int x, int y) {
		if (!this.isAtPosition(x, y))
			return false;
		
		if (this.orientation == Ship.Orientation.Horizontal)
			this.hits[x-this.getX()] = true;
		else
			this.hits[y-this.getY()] = true;
		return true;
		
	}
	
	public boolean isHit(int x, int y) {
		if (!this.isAtPosition(x, y))
			return false;
		
		if (this.orientation == Ship.Orientation.Horizontal)
			return this.hits[x-this.getX()];
		else
			return this.hits[y-this.getY()];
	}
	
	public boolean isSunk() {
		for (int i=0; i<this.hits.length; i++)
			if (!this.hits[i]) return false;
		return true;
	}
	
}
