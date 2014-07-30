package grundkurs_java;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Chap20 {

	public static void main(String[] args) {
		// DNSAnfrage.main(new String[] { "www.github.com" });
		// DateTimeServer.main(new String[] { "2222" });
		DateTimeClient.main(new String[] { "localhost", "2222" });
	}
}

class DateTimeClient {
	public static void main(String[] args) {
		String hostName = ""; // Rechner-Name bzw. -Adresse
		int port;
		// Port-Nummer
		Socket c = null;
		// Socket fuer die Verbindung zum Server
		try {
			hostName = args[0];
			port = Integer.parseInt(args[1]);
			c = new Socket(hostName, port);
			BufferedReader vomServer = new BufferedReader(
					new InputStreamReader(c.getInputStream()));
			PrintWriter zumServer = new PrintWriter(c.getOutputStream(), true);
			BufferedReader vonTastatur = new BufferedReader(
					new InputStreamReader(System.in));
			// Protokoll abwickeln20.2 Client/Server-Programmierung
			System.out.println("Server " + hostName + ":" + port + " sagt:");
			String text = vomServer.readLine(); // vom Server empfangen
			System.out.println(text);
			// auf die Konsole schreiben
			text = vonTastatur.readLine();
			// von Tastatur lesen
			zumServer.println(text);
			// zum Server schicken
			text = vomServer.readLine();
			// vom Server empfangen
			System.out.println(text);
			// auf die Konsole schreiben
			// Socket (und damit auch Stroeme) schliessen
			c.close();
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf:");
			System.out.println("java DateTimeClient <HostName> <PortNr>");
		} catch (UnknownHostException ue) {
			System.out.println("Kein DNS-Eintrag fuer " + hostName);
		} catch (IOException e) {
			System.out.println("IO-Error");
		}
	}
}

class DateTimeServer {
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			// Port-Nummer
			ServerSocket server = new ServerSocket(port); // Server-Socket
			System.out.println("DateTimeServer laeuft");
			// Statusmeldung
			Socket s = server.accept();
			// Client-Verbindung akzeptieren
			new DateTimeProtokoll(s).transact();
			// Protokoll abwickeln
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java DateTimeServer <Port-Nr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class DateTimeProtokoll {
	static SimpleDateFormat
	// Formate fuer den Zeitpunkt
			time = new SimpleDateFormat("'Es ist gerade 'H'.'mm' Uhr.'"),
			date = new SimpleDateFormat("'Heute ist 'EEEE', der 'dd.MM.yy");
	Socket s;
	BufferedReader vomClient;
	PrintWriter zumClient;

	// Socket in Verbindung mit dem Client
	// Eingabe-Strom vom Client
	// Ausgabe-Strom zum Client
	public DateTimeProtokoll(Socket s) { // Konstruktor
		try {
			this.s = s;
			vomClient = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			zumClient = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("IO-Error");
			e.printStackTrace();
		}
	}

	public void transact() {
		// Methode, die das Protokoll abwickelt
		System.out.println("Protokoll gestartet");
		try {
			zumClient.println("Geben Sie DATE oder TIME ein");
			String wunsch = vomClient.readLine();
			// v. Client empfangen
			Date jetzt = new Date();
			// Zeitpunkt bestimmen
			// vom Client empfangenes Kommando ausfuehren
			if (wunsch.equalsIgnoreCase("date"))
				zumClient.println(date.format(jetzt));
			else if (wunsch.equalsIgnoreCase("time"))
				zumClient.println(time.format(jetzt));
			else
				zumClient.println(wunsch + " ist als Kommando unzulaessig!");
			s.close();
			// Socket (und damit auch Stroeme) schliessen
		} catch (IOException e) {
			System.out.println("IO-Error");
		}
		System.out.println("Protokoll beendet");
	}
}

class DNSAnfrage {
	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName(args[0]);
			System.out.println("Angefragter Name: " + args[0]);
			System.out.println("IP-Adresse: " + ip.getHostAddress());
			System.out.println("Host-Name: " + ip.getHostName());
		} catch (ArrayIndexOutOfBoundsException aex) {
			System.out.println("Aufruf: java DNSAnfrage <hostname>");
		} catch (UnknownHostException uex) {
			System.out.println("Kein DNS-Eintrag fuer " + args[0]);
		}
	}
}
