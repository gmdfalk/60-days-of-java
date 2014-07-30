package grundkurs_java;

import java.net.*;

public class Chap20 {

	public static void main(String[] args) {
		DNSAnfrage.main(new String[] { "173.194.70.94" });
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