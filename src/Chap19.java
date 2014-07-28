import java.io.*;

public class Chap19 {

	public static void main(String[] args) {
		// Create.main(args);
		// WriteToFile.main(args);
		// BufferedWriteToFile.main(args);
		ZahlenSumme.main(args);
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