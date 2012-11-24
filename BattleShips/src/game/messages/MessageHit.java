package game.messages;

import java.util.Scanner;

import game.Grid;
import game.Game;

public class MessageHit extends Message {

	private Game game;
	private Grid grid;

	public MessageHit(Game game, Grid grid) {
		this.game = game;
		this.grid = grid;
	}

	public boolean execute(String string) throws Exception {
		if (!this.check(new String(string)))
			return false;
		
		Scanner scanner = new Scanner(string);
		
		int x = scanner.nextInt();
		int y = scanner.nextInt();

		System.out.println("Hit " + x + " " + y);
		
		HitResult res = this.grid.hit(x, y);
		
		if (res.getHit())
			this.game.send("touch " + x + " " + y);
		else 
			this.game.send("miss " + x + " " + y);
		
		if (res.getSunk())
			this.game.send("sink " + res.getName());
		
		if (res.getEnd())
			this.game.send("end");
		else 
			this.game.getPlayer().play();
		
		return true;
	}
	
	public boolean check(String string) throws Exception {
		Scanner scanner =  new Scanner(string);
		if (!scanner.hasNextInt())
			throw new Exception("HIT : parameter 1 (x) is missing");
		int x = scanner.nextInt();
		if ((x<0) || (x>Grid.SIZE))
			throw new Exception("HIT : parameter 1 (x) = " + x + " not between 0 and " + Grid.SIZE);
		
		if (!scanner.hasNextInt())
			throw new Exception("HIT : parameter 2 (y) is missing");
		int y = scanner.nextInt();
		if ((y<0) || (y>Grid.SIZE))
			throw new Exception("HIT : parameter 2 (y) = " + y + " not between 0 and " + Grid.SIZE);
		
		return true;
	}
}
