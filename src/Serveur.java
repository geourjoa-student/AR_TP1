import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Serveur extends Thread {

	int port = 1234;

	@Override
	public void run() {
		ServerSocket server;

		try {
			server = new ServerSocket(port);
			
			Socket client;

			client = server.accept();
			System.out.println("Client " + client.getInetAddress() + " connecté.\n");
			
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			
			Date date = new Date();
			
			oos.writeObject(date);
			
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		new Serveur().start();
	}
}
