import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

			FileInputStream inf = new FileInputStream(new File("/home/geourjoa/toto"));
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			
			byte buf[] = new byte[1024];
			int n;
			while ((n = inf.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			inf.close();
			out.close();
			server.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Client().start();
	}

}
