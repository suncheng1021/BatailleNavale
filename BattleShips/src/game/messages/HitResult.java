package game.messages;

public class HitResult {
	
	private String name;
	private boolean hit, sunk, end;
	
	public HitResult(String name, boolean hit, boolean sunk, boolean end) {
		this.name = name;
		this.hit = hit;
		this.sunk = sunk;
		this.end = end;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getHit() {
		return this.hit;
	}
	
	public boolean getSunk() {
		return this.sunk;
	}
	
	public boolean getEnd() {
		return this.end;
	}

}
