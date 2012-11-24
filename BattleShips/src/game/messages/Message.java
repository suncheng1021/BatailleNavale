package game.messages;

public abstract class Message {
	
	public abstract boolean execute(String string) throws Exception;
	public abstract boolean check(String string) throws Exception;
}
