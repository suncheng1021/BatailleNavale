package game.messages;

import game.player.Player;

public class MessagePlay extends Message {

	private Player player;
	
	public MessagePlay(Player player) {
		this.player = player;
	}
	
	public boolean execute(String string) {
		this.player.play();
		return true;
	}
	
	public boolean check(String string) {
		return true;
	}
}
