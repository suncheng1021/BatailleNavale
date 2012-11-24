package game.player;

import game.Game;

import java.util.Scanner;

public class Player {

	private Game game;
	private String nickname;
	
	public Player(Game game, String nickname) {
		this.game = game;
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public void play() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("play:>");
		while (!scanner.hasNextLine());
		this.game.send(scanner.hasNextLine()?scanner.nextLine():"");
		//scanner.close();
		
	}

}
