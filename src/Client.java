import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client extends Thread {

	Socket server;
	int serverPort = 1234;
	String serverHost = "";

	@Override
	public void run() {

		try {
			server = new Socket(serverHost, serverPort);
			
			System.out.println("Connected to " + server.getInetAddress() + "\n");

			ObjectInputStream ois = new ObjectInputStream(server.getInputStream());

			Date date = (Date) ois.readObject();

			System.out.println("Server said :" + date);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void main(String[] args) {
		new Client().start();
	}

}
