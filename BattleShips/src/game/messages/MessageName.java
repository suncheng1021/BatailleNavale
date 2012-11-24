package game.messages;

import java.util.Scanner;

public class MessageName extends Message {

	private String nickname;
	
	public MessageName(String nickname) {
		this.nickname = nickname;
	}
	
	public boolean execute(String string) throws Exception {
		if (!this.check(string))
			return false;
		
		Scanner scanner = new Scanner(string);
		System.out.println(this.nickname + " vs " + scanner.next());
		return true;
	}

	public boolean check(String string) throws Exception {
		Scanner scanner =  new Scanner(new String(string));
		if (!scanner.hasNext())
			throw new Exception("NAME : parameter 1 (name) is missing");
		return true;
	}

}
