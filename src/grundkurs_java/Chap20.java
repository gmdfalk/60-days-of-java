package grundkurs_java;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Chap20 {

	public static void main(String[] args) {
		DNSAnfrage.main(new String[] { "www.github.com" });
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
			time = new SimpleDateFormat("’Es ist gerade ’H’.’mm’ Uhr.’"),
			date = new SimpleDateFormat("’Heute ist ’EEEE’, der ’dd.MM.yy");
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
