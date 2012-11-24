package game.messages;

public class MessageEnd extends Message {

	public boolean execute(String string) {
		System.out.println("You win");
		return true;
	}
	
	public boolean check(String string) {
		return true;
	}

}
