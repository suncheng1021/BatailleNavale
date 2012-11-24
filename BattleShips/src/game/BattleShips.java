package game;

import java.util.Scanner;

import server.Server;


public abstract class BattleShips {

	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		System.out.println("BattleShips");

		System.out.println("What is your nickname ?");
		Scanner scanner = new Scanner(System.in);
		String nickname = scanner.next();
		//scanner.close();
		BattleShips.menu(nickname);
	}

	public static void menu(String nickname) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome " + nickname + ", what do you want to do ?");

		System.out.println("1: Single player");
		System.out.println("2: Create multiplayer game");
		System.out.println("3: Join to multiplayer game");
		System.out.println("4: Quit");

		
		while (scanner.hasNext()) {
			
			int choice = scanner.nextInt();


			switch (choice) {
			case 1:
				BattleShips.singlePlayer(nickname);
				return;
			case 2:
				BattleShips.createMultiplayerGame(nickname);
				return;
			case 3:
				BattleShips.joinMultiplayerGame(nickname);
				return;
			case 4:
				return;
			default:
			}
		}
		//scanner.close();
	}

	public static void singlePlayer(String nickname) {
		System.out.println("Single player");
		try {

			Game player1 = new Game(nickname, new Server(12345), 12345);
			new Thread(player1).start();
			// TODO: lancer un thread Game avec un player joué par l'ordinateur
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createMultiplayerGame(String nickname) {
		System.out.println("Multiplayer game (host)");
		try {
			Game game = new Game(nickname, new Server(12345), 12345);
			new Thread(game).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void joinMultiplayerGame(String nickname) {
		System.out.println("Multiplayer game (client)");
		try {
			System.out.println("Host IP address ?");
			Scanner scanner = new Scanner(System.in);
			Game game = new Game(nickname, scanner.next(), 12345);
			new Thread(game).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
