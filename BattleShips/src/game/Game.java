package game;

import game.messages.*;
import game.player.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import server.Server;

public class Game implements Runnable {

	private Socket socket; // pour se communiquer avec le serveur
	private Server server; // null si la partie ne créer pas le serveur 
	private Grid grid; // grille du joueur
	private GridState adverseGrid[][]; // grille adverse
	private boolean closing; // si on est entrain de quitter le jeux (utile pour ne pas lever une Exception de this.socket.getInputStream() en fermant la socket en fin de partie)
	private HashMap<String, Message> messages; // messages du protocoles
	private Player player;

	// Etat de la gille adverse
	public enum GridState {Water, Miss, Touch};

	Game(String nickname, Server server, String host, int port) throws Exception {
		this.closing = false;
		this.server = server;
		this.socket = new Socket(host, port);
		this.grid = new Grid();
		this.player = new Player(this, nickname);
		this.adverseGrid = new GridState[Grid.SIZE][Grid.SIZE];

		// Initialise le tableau de la grille adverse
		for (int i=0 ;i<Grid.SIZE; i++)
			for (int j=0; i<Grid.SIZE; i++)
				this.adverseGrid[i][j] = GridState.Water;

		// Ajoute les messages du protocole
		this.messages = new HashMap<String, Message>();
		this.messages.put("hit", new MessageHit(this, grid));
		this.messages.put("name", new MessageName(this.player.getNickname()));
		this.messages.put("miss", new MessageMiss(this.adverseGrid));
		this.messages.put("touch", new MessageTouch(this.adverseGrid));
		this.messages.put("sink", new MessageSink());
		this.messages.put("end", new MessageEnd());
		this.messages.put("play", new MessagePlay(this.player));
	}
	
	// Utilisé pour joindre une partie multijoueur
	Game(String nickname, String host, int port) throws Exception {
		this(nickname, null,  host,  port); 
	}

	// Utilisé pour créer une partie multijoueur
	Game(String nickname,  Server server, int port) throws Exception {
		this(nickname, server,  "127.0.0.1",  port); 
	}

	// On écoute le socket et on traite les messages reçus
	public void run() {

		// Le serveur nous transmet la taille du message qu'il va envoyer
		int size = 0;
		while (size != -1) {
			try {
				size = this.socket.getInputStream().read();

				if (size == 0) continue;
				// On enregistre le message dans un tableau de bytes
				byte b[] = new byte[size];

				size = this.socket.getInputStream().read(b, 0, size);
				// Pour convertir le tableau de byte en String on passe par un StringBuffer
				StringBuffer buffer = new StringBuffer();
				for (int i=0; i<size; i++)
					buffer.append((char) b[i]); // convertir les byte en char

				// On décode le message
				this.decode(buffer.toString());

			} catch (Exception e) {
				if (!this.closing) e.printStackTrace();
			}
		}
		System.out.println("Game thread has been terminated");
	}

	// Envoyer un message au serveur
	public void send(String string) {
		try {
			Scanner scanner = new Scanner(new String(string));
			String message = scanner.next();


			if (message.equals("quit"))
				this.close();
			else if (!this.messages.keySet().contains(message))
				throw new Exception("Unknown message: " + message);
			else {
				this.messages.get(message).check(scanner.nextLine());

				this.socket.getOutputStream().write(string.getBytes().length);
				this.socket.getOutputStream().write(string.getBytes());
				this.socket.getOutputStream().flush();
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Décode un message
	public void decode(String string) {
		Scanner scanner = new Scanner(string);
		String message = scanner.next();
		try {
			if (!this.messages.keySet().contains(message))
				throw new Exception("Unknown message: " + message);
			else
				this.messages.get(message).execute(scanner.hasNextLine()?scanner.nextLine():"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	// Quitte la partie
	public void close() {
		try {
			this.closing = true;
			this.socket.close();
			if (this.server != null) this.server.close();
			BattleShips.menu(this.player.getNickname());

		} catch (IOException e) {
			e.printStackTrace();
			this.closing = false;
		}
	}

	public Player getPlayer() {
		return this.player;
	}

}
