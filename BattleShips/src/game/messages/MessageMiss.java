package game.messages;

import game.Grid;
import game.Game.GridState;

import java.util.Scanner;

public class MessageMiss extends Message {

	private GridState adverseGrid[][];
	
	public MessageMiss(GridState[][] adverseGrid) {
		this.adverseGrid = adverseGrid;
	}
	
	public boolean execute(String string) throws Exception {
		if (!this.check(new String(string)))
			return false;
		
		Scanner scanner = new Scanner(string);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		
		System.out.println("Missed at " + x + " " + y);
		
		this.adverseGrid[x][y] = GridState.Miss;
		return true;
	}
	
	public boolean check(String string) throws Exception {
		Scanner scanner =  new Scanner(string);
		if (!scanner.hasNextInt())
			throw new Exception("MISS : parameter 1 (x) is missing");
		int x = scanner.nextInt();
		if ((x<0) || (x>Grid.SIZE))
			throw new Exception("MISS : parameter 1 (x) = " + x + " not between 0 and " + Grid.SIZE);
		
		if (!scanner.hasNextInt())
			throw new Exception("MISS : parameter 2 (y) is missing");
		int y = scanner.nextInt();
		if ((y<0) || (y>Grid.SIZE))
			throw new Exception("MISS : parameter 2 (y) = " + y + " not between 0 and " + Grid.SIZE);
		
		return true;
	}

}
