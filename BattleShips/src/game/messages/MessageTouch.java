package game.messages;

import game.Grid;
import game.Game.GridState;

import java.util.Scanner;

public class MessageTouch extends Message {

	private GridState adverseGrid[][];
	
	public MessageTouch(GridState[][] adverseGrid) {
		this.adverseGrid = adverseGrid;
	}
	
	public void execute(Scanner scanner) {


	}
	
	public boolean execute(String string) throws Exception {
		if (!this.check(new String(string)))
			return false;
		
		Scanner scanner = new Scanner(string);
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		
		this.adverseGrid[x][y] = GridState.Touch;
		
		System.out.println("Touched at " + x + " " + y);
		return true;
	}

	public boolean check(String string) throws Exception {
		Scanner scanner =  new Scanner(string);
		if (!scanner.hasNextInt())
			throw new Exception("TOUCH : parameter 1 (x) is missing");
		int x = scanner.nextInt();
		if ((x<0) || (x>Grid.SIZE))
			throw new Exception("TOUCH : parameter 1 (x) = " + x + " not between 0 and " + Grid.SIZE);
		
		if (!scanner.hasNextInt())
			throw new Exception("TOUCH : parameter 2 (y) is missing");
		int y = scanner.nextInt();
		if ((y<0) || (y>Grid.SIZE))
			throw new Exception("TOUCH : parameter 2 (y) = " + y + " not between 0 and " + Grid.SIZE);
		
		return true;
	}

}
