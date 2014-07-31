package grundkurs_java;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class Chap20 {

	public static void main(String[] args) {
		// DNSAnfrage.main(new String[] { "www.github.com" });
		// DateTimeServer.main(new String[] { "2222" });
		// DateTimeClient.main(new String[] { "localhost", "2222" });
		// DateTimeMultiServer.main(new String[] { "3333" });
		// MyClient.main(new String[] { "3333" });
		// LiesURL.main(new String[] { "http://github.com" });
	}
}

class CdServer {
	// 20.1
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			// Port-Nummer
			ServerSocket server = new ServerSocket(port); // Server-Socket
			System.out.println("CdServer laeuft"); // Statusmeldung
			while (true) {
				Socket s = server.accept(); // Client-Verbindung akzeptieren
				// Dienst starten
				new CdServerDienst(s).start();
			}
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java CdServer <Port>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CdServerDienst extends Thread {
	// 20.1
	static SimpleDateFormat time = new SimpleDateFormat(
			"'Es ist gerade 'H'.'mm' Uhr.'"), date = new SimpleDateFormat(
			"'Heute ist 'EEEE', der 'dd.MM.yy");
	// Formate fuer den Zeitpunkt20.2 Client/Server-Programmierung

	static int anzahl = 0;
	// Anzahl der Clients insgesamt
	int nr = 0;
	// Nummer des Clients
	Socket s;
	// Socket in Verbindung mit dem Client
	BufferedReader vomClient;
	// Eingabe-Strom vom Client
	PrintWriter zumClient;

	// Ausgabe-Strom zum Client
	public CdServerDienst(Socket s) { // Konstruktor
		try {
			this.s = s;
			nr = ++anzahl;
			vomClient = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			zumClient = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("IO-Error bei Client " + nr);
			e.printStackTrace();
		}
	}

	public void run() { // Methode, die das Protokoll abwickelt
		System.out.println("Protokoll fuer Client " + nr + " gestartet");
		try {
			while (true) {
				zumClient
						.println("Mögliche Befehle: list, tracks <album>, date, time, quit");
				String wunsch = vomClient.readLine(); // vom Client empfangen
				if (wunsch == null || wunsch.equalsIgnoreCase("quit"))
					break;
				Date jetzt = new Date();
				if (wunsch.equalsIgnoreCase("date"))
					zumClient.println(date.format(jetzt));
				else if (wunsch.equalsIgnoreCase("time"))
					zumClient.println(time.format(jetzt));
				else if (wunsch.equalsIgnoreCase("list"))
					File.listRoots();
				else
					zumClient.println(wunsch + "ist als Kommando unzulaessig!");
			}
			s.close();
			// Socket (und damit auch Stroeme) schliessen
		} catch (IOException e) {
			System.out.println("IO-Error bei Client " + nr);
		}
		System.out.println("Protokoll fuer Client " + nr + " beendet");
	}
}

class DateTimeApplet extends JApplet {
	public void init() {
		try {
			Socket socket = new Socket(this.getCodeBase().getHost(), 7777);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			in.readLine();
			out.println("date");
			String s = in.readLine();
			getContentPane().add(new JLabel(s, JLabel.CENTER));
		} catch (IOException e) {
			String s = "Verbindung zum DateTimeServer fehlgeschlagen!";
			getContentPane().add(new JLabel(s, JLabel.CENTER));
		}
	}
}

class LiesURL {
	public static void main(String[] args) {
		try {
			URL u = new URL(args[0]);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					u.openStream()));
			String zeile;
			while ((zeile = in.readLine()) != null)
				System.out.println(zeile);
			in.close();
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java LiesURL <URL>");
		} catch (MalformedURLException me) {
			System.out.println(args[0] + " ist keine zulaessige URL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class MyClient {
	// liest alle vom Server geschickten Daten
	static void zeigeWasKommt(BufferedReader sin) throws IOException {
		String str = null;
		try {
			while ((str = sin.readLine()) != null)
				System.out.println(str);
		} catch (SocketTimeoutException sto) {
		}
	}

	static void zeigePrompt() {
		System.out.print("> ");
		System.out.flush();
	}

	public static void main(String[] args) {
		try {
			System.out.println("Client laeuft. Beenden mit QUIT");
			Socket c = new Socket(args[0], Integer.parseInt(args[1]));
			c.setSoTimeout(500); // setze Timeout auf eine halbe Sekunde
			BufferedReader vomServer = new BufferedReader(
					new InputStreamReader(c.getInputStream()));
			PrintWriter zumServer = new PrintWriter(c.getOutputStream(), true);
			BufferedReader vonTastatur = new BufferedReader(
					new InputStreamReader(System.in));
			String zeile;
			do {
				zeigeWasKommt(vomServer);
				zeigePrompt();
				zeile = vonTastatur.readLine();
				zumServer.println(zeile);
			} while (!zeile.equalsIgnoreCase("quit"));
			c.close();
			// Socket (und damit auch Stroeme) schliessen
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java MyClient <Port-Nummer>");
		} catch (UnknownHostException ux) {
			System.out.println("Kein DNS-Eintrag fuer " + args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class DateTimeMultiServer {
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			// Port-Nummer
			ServerSocket server = new ServerSocket(port); // Server-Socket
			System.out.println("DateTimeServer laeuft"); // Statusmeldung
			while (true) {
				Socket s = server.accept(); // Client-Verbindung akzeptieren
				new DateTimeDienst(s).start();
				// Dienst starten
			}
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java DateTimeServer <Port>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class DateTimeDienst extends Thread {
	static SimpleDateFormat time = new SimpleDateFormat(
			"'Es ist gerade 'H'.'mm' Uhr.'"), date = new SimpleDateFormat(
			"'Heute ist 'EEEE', der 'dd.MM.yy");
	// Formate fuer den Zeitpunkt20.2 Client/Server-Programmierung

	static int anzahl = 0;
	// Anzahl der Clients insgesamt
	int nr = 0;
	// Nummer des Clients
	Socket s;
	// Socket in Verbindung mit dem Client
	BufferedReader vomClient;
	// Eingabe-Strom vom Client
	PrintWriter zumClient;

	// Ausgabe-Strom zum Client
	public DateTimeDienst(Socket s) { // Konstruktor
		try {
			this.s = s;
			nr = ++anzahl;
			vomClient = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			zumClient = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("IO-Error bei Client " + nr);
			e.printStackTrace();
		}
	}

	public void run() { // Methode, die das Protokoll abwickelt
		System.out.println("Protokoll fuer Client " + nr + " gestartet");
		try {
			while (true) {
				zumClient.println("Geben Sie DATE, TIME oder QUIT ein");
				String wunsch = vomClient.readLine(); // vom Client empfangen
				if (wunsch == null || wunsch.equalsIgnoreCase("quit"))
					break;
				// Schleife abbrechen
				Date jetzt = new Date();
				// Zeitpunkt bestimmen
				// vom Client empfangenes Kommando ausfuehren
				if (wunsch.equalsIgnoreCase("date"))
					zumClient.println(date.format(jetzt));
				else if (wunsch.equalsIgnoreCase("time"))
					zumClient.println(time.format(jetzt));
				else
					zumClient.println(wunsch + "ist als Kommando unzulaessig!");
			}
			s.close();
			// Socket (und damit auch Stroeme) schliessen
		} catch (IOException e) {
			System.out.println("IO-Error bei Client " + nr);
		}
		System.out.println("Protokoll fuer Client " + nr + " beendet");
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
