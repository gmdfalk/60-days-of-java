import java.io.*;
import java.util.Scanner;

public class Chap19 {

	public static void main(String[] args) {
		// Create.main(args);
		// WriteToFile.main(args);
		// BufferedWriteToFile.main(args);
		// ZahlenSumme.main(args);
		// try {
		// PrintWriting.main(args);
		// } catch (IOException e) {
		// }
		// InTools.main(args);
		ObjectWrite.main(args);
	}

}

class ObjectWrite {
	public static void main(String[] summand) {
		try {
			// Dateiname fuer die Speicherung festlegen
			String dateiname = "MeineDaten.dat";
			// Datenstrom zum Schreiben in die Datei erzeugen
			FileOutputStream datAus = new FileOutputStream(dateiname);
			// Objektstrom darueber legen
			ObjectOutputStream oAus = new ObjectOutputStream(datAus);
			// Testdatensaetze erzeugen
			int anzahl = 2; // Anzahl der Datensaetze
			Datensatz a = new Datensatz(99, 56, "Coca Cola");
			Datensatz b = new Datensatz(111, 1234.79, "Fahrrad");
			// Datensaetze in die Datei schreiben
			oAus.writeInt(anzahl);
			// Anzahl der Datensaetze
			oAus.writeObject(a);
			// Datensatz 1
			oAus.writeObject(b);
			// Datensatz 2
			// Dateistrom schliessen
			oAus.close();
			System.out.println(anzahl + " Datensaetze in die Datei "
					+ dateiname + " geschrieben");
			System.out.println(a);
			System.out.println(b);
		} catch (Exception e) {
			System.out.println("Fehler beim Schreiben: " + e);
		}
	}
}

class Datensatz implements Serializable {
	public int nr;
	// Nummer des Datensatzes
	public double wert; // Wert des Datensatzes
	public String kom; // Kommentar

	public Datensatz(int nr, double wert, String kom) { // Konstruktor
		this.nr = nr;
		this.wert = wert;
		this.kom = kom;
	}

	public String toString() {
		// Erzeugung einer String-Darstellung
		return "Nr. " + nr + ": " + wert + " (" + kom + ")";
	}
}

class DataWriteAndRead {
	// Speichert elementare Werte in einer Datei und liest sie wieder ein
	public static void main(String[] args) {
		try {
			File datei = new File("binaer.dat");
			FileOutputStream out = new FileOutputStream(datei);
			DataOutputStream dout = new DataOutputStream(out);
			dout.writeInt(1);
			dout.writeDouble(2.3);
			dout.writeChar('a');
			dout.writeBoolean(true);
			dout.close();
			FileInputStream in = new FileInputStream(datei);
			DataInputStream din = new DataInputStream(in);
			System.out.println("int: " + din.readInt());
			System.out.println("double: " + din.readDouble());
			System.out.println("char: " + din.readChar());
			System.out.println("boolean: " + din.readBoolean());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

class Eingaben {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int i;
		double d;
		boolean b;
		System.out.print("i = ");
		i = in.nextInt();
		System.out.print("d = ");
		d = in.nextDouble();
		System.out.print("b = ");
		b = in.nextBoolean();
		System.out.println("i = " + i);
		System.out.println("d = " + d);
		System.out.println("b = " + b);
	}
}

class InTools {
	// Gepufferter Eingabestrom ueber den Standardeingabestrom System.in
	public static BufferedReader in = new BufferedReader(new InputStreamReader(
			System.in));

	// Methode zum Einlesen von double-Werten
	public static double readDouble() {
		double erg = 0;
		try {
			erg = Double.parseDouble(in.readLine());
		} catch (Exception e) {
			System.out.println(e);
		}
		return erg;
	}

	// main-Methode
	public static void main(String[] args) {
		System.out.print("double-Wert eingeben: d = ");
		double d = readDouble();
		System.out.println("d = " + d + " wurde eingelesen");
	}
}

class PrintWriting {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter p = new PrintWriter(System.out);
		PrintWriter pf = new PrintWriter(System.out, true);
		pf.print(1);
		pf.print('a');
		in.readLine();
		// Enter-Taste druecken
		pf.println();
		in.readLine();
		// Enter-Taste druecken
		pf.println("pf ist fertig!");
		p.print(3.2);
		p.print(true);
		p.println();
		p.println("p ist fertig!");
		in.readLine();
		// Enter-Taste druecken
		p.flush();
	}
}

class ZahlenSumme {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		System.out.println("Addiere alle Zahlen in einer Zeichenfolge");
		System.out.println("(Eingabe mit STOP abschliessen)");
		System.out.println();
		StringBuffer woerter = new StringBuffer(); // zum Woerter sammeln
		double sum = 0.0;
		// zum Zahlen summieren
		int tokenType;
		// Typ des Tokens
		boolean stop = false;
		// Flag fuer Schleife
		try {
			do {
				switch (tokenType = st.nextToken()) { // naechstes Token
				case StreamTokenizer.TT_NUMBER:
					// ist Zahl
					sum += st.nval;
					// summiere Wert
					break;
				case StreamTokenizer.TT_WORD:
					// ist Wort
					if (!(stop = st.sval.equals("STOP")))
						// falls nicht STOP
						woerter.append(st.sval);
					// Wort anhaengen
					break;
				}
			} while (!stop);
			System.out.println();
			System.out.println("Summe aller Zahlen: " + sum);
			System.out.println("Text: " + woerter.toString());
		} catch (IOException e) {
			System.out.println(e);
		}
		;
	}
}

class BufferedWriteToFile {
	// Liest alle Zeichen aus br und schreibt sie in bw
	public static void br2bw(BufferedReader br, BufferedWriter bw)
			throws IOException {
		String z;
		// Zeile
		while ((z = br.readLine()) != null) { // lesen, Stromende pruefen,
			bw.write(z);
			// ausgeben und
			bw.newLine();
			// Zeilenwechsel ausgeben
		}
		br.close();
		bw.close();
	}

	// Liest Zeilen von der Tastatur und speichert sie in einer Datei
	public static void main(String[] args) {
		try {
			File datei = new File(args[0]);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			BufferedWriter out = new BufferedWriter(new FileWriter(datei));
			System.out.println("Geben Sie jetzt den Text ein.");
			System.out.println("(Ende/Speichern mit Ctrl-Z bzw. Strg-Z)");
			System.out.println();
			br2bw(in, out);
			in = new BufferedReader(new FileReader(datei));
			out = new BufferedWriter(new OutputStreamWriter(System.out));
			System.out.println();
			System.out.println("Der in " + args[0] + " gespeicherte Text:");
			System.out.println();
			br2bw(in, out);
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java BufferedWriteToFile <Datei>");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

class Create {
	public static void main(String[] args) {
		try {
			File f = new File(args[0]);
			// Verzeichnis
			File g = new File(args[0] + "/" + args[1]);
			// Datei
			File h = new File(args[0] + "/" + args[1] + ".txt"); // Datei
			if (f.exists()) {
				System.out.println("Verzeichnis oder Datei " + args[0]
						+ " existiert bereits");
				return;
			}
			f.mkdir();
			// Verzeichnis anlegen
			g.createNewFile();
			// Datei anlegen
			h.createNewFile();
			// Datei anlegen
			String[] dateien = f.list(); // Verzeichniseintraege aufzaehlen
			System.out.println("Dateien im Verzeichnis " + args[0] + ":");
			for (int i = 0; i < dateien.length; i++)
				System.out.println(dateien[i]);
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java Create <Verzeichnis> <Datei>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class WriteToFile {
	// Liest alle Zeichen aus r und schreibt sie in w
	public static void r2w(Reader r, Writer w) throws IOException {
		int c;
		// Zeichen
		while ((c = r.read()) != -1)
			// lesen und auf Strom-Ende testen
			w.write(c);
		// ausgeben
		r.close();
		w.close();
	}

	// Liest Zeichen von der Tastatur und speichert sie in einer Datei
	public static void main(String[] args) {
		try {
			File datei = new File(args[0]);
			Reader in = new InputStreamReader(System.in);
			Writer out = new FileWriter(datei);
			System.out.println("Geben Sie jetzt den Text ein.");
			System.out.println("(Ende/Speichern mit Ctrl-Z bzw. Strg-Z)");
			System.out.println();
			r2w(in, out);
			in = new FileReader(datei);
			out = new OutputStreamWriter(System.out);
			System.out.println();
			System.out.println("Der in " + args[0] + " gespeicherte Text:");
			System.out.println();
			r2w(in, out);
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Aufruf: java WriteToFile <Datei>");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}