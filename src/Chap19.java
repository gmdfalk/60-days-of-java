import java.io.*;

public class Chap19 {

	public static void main(String[] args) {
		// Create.main(args);
		WriteToFile.main(args);
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