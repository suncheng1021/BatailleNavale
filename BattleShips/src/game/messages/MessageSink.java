package game.messages;

import java.util.Scanner;

public class MessageSink extends Message {
	
	public boolean execute(String string) throws Exception {
		if (!this.check(string))
			return false;
		
		Scanner scanner = new Scanner(string);
		System.out.println(scanner.next() + " is sinking.");
		return true;
	}

	public boolean check(String string) throws Exception {
		Scanner scanner =  new Scanner(new String(string));
		if (!scanner.hasNext())
			throw new Exception("SINK : parameter 1 (name) is missing");
		return true;
	}

}
