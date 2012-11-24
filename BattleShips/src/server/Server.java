package server;

import java.io.IOException;
import java.net.ServerSocket;


public class Server implements Runnable {

	private ServerSocket server;
	private Client player1, player2;
	private boolean closing;

	public Server(int port) throws Exception {
		this.closing = false;
		this.server = new ServerSocket(port);
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {

			this.player1 = new Client(this, this.server.accept());
			System.out.println("Player 1 has joined");
			this.player2 = new Client(this, this.server.accept());
			System.out.println("Player 2 has joined");
			
			// Choix aléatoire du joeur à débuter la partie
			int rand = (int) (Math.random() * 2);
			
			// Préparation du message
			String message = "play";
			
			// Envoie du message
			if (rand == 0)
				this.player1.send(message.getBytes());
			else
				this.player2.send(message.getBytes());
				
		} catch (Exception e) {
			if (!this.closing) e.printStackTrace();
		}	
	}

	public void send(Client sender, byte bytes[]) {
		if ((this.player1 == null) || (this.player2 == null)) return;

		if (sender == this.player1)
			this.player2.send(bytes);
		else
			this.player1.send(bytes);
	}

	public boolean isClosing() {
		return this.closing;
	}

	public void close() {
		try {
			this.closing = true;
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
			this.closing = false;
		}
	}
}
