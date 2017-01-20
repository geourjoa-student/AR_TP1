import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

			Socket client = server.accept();
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			FileOutputStream out = new FileOutputStream(new File("/home/geourjoa/tata"));
			byte buf[] = new byte[1024];
			int n;
			while ((n = in.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			out.close();
			client.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Serveur().start();
	}
}
