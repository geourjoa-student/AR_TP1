/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.printing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Classe de service fournissant toutes les interactions (read, write) en mode
 * TCP.
 *
 * @author Morat
 * @author gattazr
 */
class TCP {
	private static final int MAX_LEN_BUFFER = 1024;

	/**
	 *
	 * @param soc
	 *            th socket
	 * @return string data
	 * @throws IOException
	 */
	static String readData(Socket soc) throws IOException {
		// -----------------------------------------------------------------------------
		DataInputStream wDataIn = new DataInputStream(soc.getInputStream());
		int wLen = wDataIn.readInt();
		System.out.println("readData : wLen = " + wLen);

		int wNextReadLength;
		byte[] wBytes = new byte[MAX_LEN_BUFFER];
		String wStr = new String();

		int wLeft = wLen;
		while (wLeft > 0) {
			System.out.println("readData : wLeft = " + wLeft);
			if (wLeft > MAX_LEN_BUFFER) {
				wNextReadLength = MAX_LEN_BUFFER;
			} else {
				wNextReadLength = wLeft;
			}
			wLeft -= wDataIn.read(wBytes, 0, wNextReadLength);
			wStr.concat(wBytes.toString());
		}

		return wStr;
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @return the JobKey
	 * @throws IOException
	 */
	static JobKey readJobKey(Socket soc) throws IOException {
		// -----------------------------------------------------------------------------
		DataInputStream wDataIn = new DataInputStream(soc.getInputStream());
		int wLength = wDataIn.readInt();

		byte[] wBytes = new byte[wLength];

		int wRead = 0;
		while (wRead < wLength) {
			wRead += wDataIn.read(wBytes, wRead, wLength - wRead);
		}

		return new JobKey(wBytes);
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @return the JobState
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	static JobState readJobState(Socket soc) throws IOException, ClassNotFoundException {
		// -----------------------------------------------------------------------------
		// A COMPLETER
		return null;
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @return the notification
	 * @throws IOException
	 */
	static Notification readProtocole(Socket soc) throws IOException {
		// -----------------------------------------------------------------------------
		DataInputStream wDataIn = new DataInputStream(soc.getInputStream());
		int wOrdinal = wDataIn.readInt();

		return Notification.values()[wOrdinal];
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @param fis
	 *            the input stream ti transfert
	 * @param len
	 *            th len of the input stream
	 * @throws IOException
	 */
	static void writeData(Socket soc, InputStream fis, int len) throws IOException {
		// -----------------------------------------------------------------------------
		DataOutputStream wDataOut = new DataOutputStream(soc.getOutputStream());
		int wRead = 0;
		int wNextReadLength = 0;
		byte[] wBytes = new byte[MAX_LEN_BUFFER];

		int wLeft = len;
		while (wLeft > 0) {
			if (wLeft > MAX_LEN_BUFFER) {
				wNextReadLength = MAX_LEN_BUFFER;
			} else {
				wNextReadLength = wLeft;
			}
			wRead = fis.read(wBytes, 0, wNextReadLength);
			wDataOut.write(wBytes, 0, wRead);
			wLeft -= wRead;
		}
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @param key
	 *            the JobKey to write
	 * @throws IOException
	 */
	static void writeJobKey(Socket soc, JobKey key) throws IOException {
		// -----------------------------------------------------------------------------
		DataOutputStream wDataOut = new DataOutputStream(soc.getOutputStream());
		byte[] wData = key.marshal();

		wDataOut.writeInt(wData.length);
		wDataOut.write(wData);
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @param jobs
	 *            the JobState
	 * @throws IOException
	 */
	static void writeJobState(Socket soc, JobState jobs) throws IOException {
		// -----------------------------------------------------------------------------
		// A COMPLETER
	}

	/**
	 *
	 * @param soc
	 *            the socket
	 * @param not
	 *            the notification
	 * @throws IOException
	 */
	static void writeProtocole(Socket soc, Notification not) throws IOException {
		// -----------------------------------------------------------------------------
		DataOutputStream wDataOut = new DataOutputStream(soc.getOutputStream());

		wDataOut.writeInt(not.ordinal());
	}
}
