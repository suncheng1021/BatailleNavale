package server;

import java.io.IOException;
import java.net.Socket;


public class Client implements Runnable {

	private Server server;
	private Socket client;

	Client(Server server, Socket client) {
		this.server = server;
		this.client = client;

		new Thread(this).start();
	}

	public void run() {
		try {
			// Le client nous transmet la taille du message qu'il va envoyer
			
			int size = 0;
			while (size != -1) {
				size = this.client.getInputStream().read();
				
				if (size == 0) continue;

				// On enregistre le message dans un tableau de bytes
				byte b[] = new byte[size];
				size = this.client.getInputStream().read(b, 0, size);
				
				this.server.send(this, b);

			}
		} catch (IOException e) {
			if (!this.server.isClosing()) e.printStackTrace();
		}
		finally {
			System.out.println("Client thread has been terminated");
		}
	}
	
	public void send(byte bytes[]) {
		try {
			this.client.getOutputStream().write(bytes.length);
			this.client.getOutputStream().write(bytes);
			this.client.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}