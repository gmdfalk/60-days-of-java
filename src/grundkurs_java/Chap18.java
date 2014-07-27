package grundkurs_java;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chap18 {

	public static void main(String[] args) {
		// MehrmalsP.main(args);
		// MehrmalsT.main(args);
		// TVProgAuslosung.main(args);
		// MehrmalsR.main(args);
		// TVProgAuslosungMitRunnable.main(args);
		// StoppuhrMitThread.main(args);
		// FigurenThreads1.main(args);
		// EVTest3.main(args);
		// UseTerminals.main(args);
		EVTest4.main(args);
	}

}

// 18.2
class EVTest4 {
	public static void main(String args[]) {
		BessererWert w = new BessererWert();
		Erzeuger e1 = new Erzeuger(w), e2 = new Erzeuger(w), e3 = new Erzeuger(
				w);
		Verbraucher v1 = new Verbraucher(w), v2 = new Verbraucher(w), v3 = new Verbraucher(
				w);
		e1.start();
		e2.start();
		e3.start();
		v1.start();
		v2.start();
		v3.start();
	}
}

class BessererWert extends Wert {
	private boolean verfuegbar = false;

	public synchronized int get() {
		while (!verfuegbar)
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		verfuegbar = false;
		notify();
		System.out.println("Verbraucher get: " + wert);
		return wert;
	}

	public synchronized void put(int w) {
		while (verfuegbar)
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		wert = w;
		System.out.println("Erzeuger put: " + wert);
		verfuegbar = true;
		notify();
	}
}

// 18.1
class KonzertDaten {
	private int sitzPlatz = 0;

	// without synchronized here, two terminals using the same KonzertDaten
	// instance will sell overlapping seats
	synchronized int freierPlatz() {
		int n = sitzPlatz;
		try { // simuliere Datenbankabfragen
			Thread.sleep((int) (Math.random() * 100));
		} catch (InterruptedException ie) {
		}
		return sitzPlatz = n + 1;
	}
}

class KartenTerminal extends Thread {
	private KonzertDaten daten;

	KartenTerminal(String name, KonzertDaten daten) {
		super(name);
		this.daten = daten;
	}

	public void run() {
		for (int i = 0; i < 100; i++)
			System.out.println(getName() + ": Sitzplatz " + daten.freierPlatz()
					+ " verkauft");
	}
}

class UseTerminals {
	public static void main(String[] args) {
		KonzertDaten daten = new KonzertDaten();
		KartenTerminal t1 = new KartenTerminal("Karten-Terminal 1", daten), t2 = new KartenTerminal(
				"Karten-Terminal 2", daten);
		t1.start();
		t2.start();
	}
}

// Erzeuger-Verbraucher-Problem
class EVTest3 {
	public static void main(String args[]) {
		KlemmWert w = new KlemmWert();
		Erzeuger e = new Erzeuger(w);
		Verbraucher v = new Verbraucher(w);
		e.start();
		v.start();
	}
}

class EVTest2 {
	public static void main(String args[]) {
		GuterWert w = new GuterWert();
		Erzeuger e = new Erzeuger(w);
		Verbraucher v = new Verbraucher(w);
		e.start();
		v.start();
	}
}

class EVTest1 {
	public static void main(String args[]) {
		SchlechterWert w = new SchlechterWert();
		Erzeuger e = new Erzeuger(w);
		Verbraucher v = new Verbraucher(w);
		e.start();
		v.start();
	}
}

class KlemmWert extends Wert {
	// causes deadlock, all threads are just waiting forever
	// (Philosophenproblem)
	public synchronized int get() {
		try {
			wait();
		} catch (InterruptedException ie) {
		}
		notify();
		System.out.println("Wert verbraucht!");
		return wert;
	}

	public synchronized void put(int w) {
		wert = w;
		System.out.println("Wert erzeugt!");
		notify();
		try {
			wait();
		} catch (InterruptedException ie) {
		}
	}
}

class GuterWert extends Wert {
	// using notify, synchronized and wait to avoid timing issues
	private boolean verfuegbar = false;

	public synchronized int get() {
		if (!verfuegbar)
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		verfuegbar = false;
		notify();
		System.out.println("Verbraucher get: " + wert);
		return wert;
	}

	public synchronized void put(int w) {
		if (verfuegbar)
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		wert = w;
		System.out.println("Erzeuger put: " + wert);
		verfuegbar = true;
		notify();
	}
}

class SchlechterWert extends Wert {
	// no wait or notify, uncontrolled at runtime, timing issues inc
	public synchronized int get() {
		System.out.println("Verbraucher get: " + wert);
		return wert;
	}

	public synchronized void put(int w) {
		wert = w;
		System.out.println("Erzeuger put: " + wert);
	}
}

abstract class Wert {
	protected int wert;

	abstract public int get();

	abstract public void put(int w);
}

class Erzeuger extends Thread {
	Wert w;

	public Erzeuger(Wert w) {
		this.w = w;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			w.put(i);
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
		}
	}
}

class Verbraucher extends Thread {
	Wert w;

	public Verbraucher(Wert w) {
		this.w = w;
	}

	public void run() {
		int v;
		for (int i = 0; i < 5; i++) {
			v = w.get();
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
		}
	}
}

// Read-write problem: (fix is "synchronized")
class FigurenThreads2 {
	public static void main(String[] args) {
		GuteFigur f = new GuteFigur();
		Schreiber s = new Schreiber(f);
		Leser l = new Leser(f);
		s.setDaemon(true);
		s.start();
		l.start();
	}
}

class FigurenThreads1 {
	public static void main(String[] args) {
		SchlechteFigur f = new SchlechteFigur();
		Schreiber s = new Schreiber(f);
		Leser l = new Leser(f);
		s.setDaemon(true);
		s.start();
		l.start();
	}
}

class GuteFigur extends Figur {
	synchronized public void setPosition(char x, int y) {
		this.x = x;
		MachMal.eineSekundeLangGarNichts();
		this.y = y;
	}

	synchronized public String getPosition() {
		MachMal.eineSekundeLangGarNichts();
		return "(" + x + "," + y + ")";
	}
}

class SchlechteFigur extends Figur {
	public void setPosition(char x, int y) {
		this.x = x;
		MachMal.eineSekundeLangGarNichts();
		this.y = y;
	}

	public String getPosition() {
		MachMal.eineSekundeLangGarNichts();
		return "(" + x + "," + y + ")";
	}
}

abstract class Figur {
	protected char x;
	protected int y;

	abstract public void setPosition(char x, int y);

	abstract public String getPosition();
}

class Schreiber extends Thread {
	Figur f;

	public Schreiber(Figur f) {
		this.f = f;
	}

	public void run() {
		while (true) {
			int z = (int) (Math.random() * 8); // 0 .. 7
			char x = (char) ('A' + z);
			// A .. H
			int y = 1 + z;
			// 1 .. 818.4 Thread-Synchronisation und -Kommunikation
			f.setPosition(x, y);
		}
	}
}

class Leser extends Thread {
	Figur f;

	public Leser(Figur f) {
		this.f = f;
	}

	public void run() {
		for (int i = 1; i <= 30; i++) {
			System.out.print(f.getPosition() + " ");
			if (i % 10 == 0)
				System.out.println();
		}
	}
}

class StoppuhrMitThread {
	public static void main(String[] args) {
		// Auf Betaetigen der Eingabetaste warten
		// Aktuellen Zeitpunkt im Date-Objekt start festhalten
		System.out.println("Starten mit Eingabetaste");
		MachMal.wartenAufEingabe();

		Date start = new Date();
		// Zeitpunkt ausgeben
		System.out.println("Startzeitpunkt: " + start);
		System.out.println();
		System.out.println("Stoppuhr anhalten mit Eingabetaste!");
		// Anzeige-Thread starten
		Thread t = new UhrzeitThread();
		t.start();
		// Auf Betaetigen der Eingabetaste warten
		MachMal.wartenAufEingabe();
		// Aktuellen Zeitpunkt im Date-Objekt stopp festhalten
		Date stopp = new Date();
		// Anzeige-Thread anhalten
		t.interrupt();
		// Zeitpunkt ausgeben
		System.out.println("Stoppzeitpunkt: " + stopp);
		System.out.println();
		// Laufzeit als Differenz von stopp und start bestimmen
		long laufzeit = stopp.getTime() - start.getTime();
		// Laufzeit ausgeben
		System.out.println("Gesamtlaufzeit: " + laufzeit + " ms");
	}
}

class UhrzeitThread extends Thread {
	public static final SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");

	public void run() {
		System.out.println();
		while (true) {
			if (isInterrupted()) {
				System.out.println();
				break;
			}
			Date time = new Date();
			System.out.println(hms.format(time));
			try {
				sleep(1000);
			} catch (InterruptedException ie) {
				interrupt();
			}
		}
	}
}

class TVProgAuslosungMitRunnable {
	public static void main(String[] args) {
		TVProgRunnable t1 = new TVProgRunnable("Wer wird Millionaer?");
		TVProgRunnable t2 = new TVProgRunnable("Enterprise");
		TVProgRunnable t3 = new TVProgRunnable("Nils Holgersson");
		t1.start();
		t2.start();
		t3.start();
	}
}

class TVProgRunnable implements Runnable {
	// Instanzvariable als Referenz auf den eigentlichen Thread
	Thread t;

	// Konstruktor
	public TVProgRunnable(String name) {
		// Erzeuge eine Thread, der mit dem eigenen Objekt verbunden ist
		t = new Thread(this, name);
	}

	// start-Methode des Runnable-Objekts startet den eigentlichen Thread
	public void start() {
		t.start();
	}

	// run-Methode (Schleife mit Zufalls-Wartezeiten)
	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(Thread.currentThread().getName() + " zum " + i
					+ ". Mal");
			MachMal.zufaelligGarNichts();
		}
		System.out.println(Thread.currentThread().getName() + " FERTIG!");
	}
}

class MehrmalsR {
	public static void main(String[] args) {
		Runnable r1 = new ABCRunnable(), r2 = new ABCRunnable();
		Thread t1 = new Thread(r1), t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}

class ABCRunnable implements Runnable {
	public void run() {
		for (char b = 'A'; b <= 'Z'; b++) {
			// Gib den Buchstaben aus
			System.out.print(b);
			// Verbringe eine Sekunde mit "Nichtstun"
			MachMal.eineSekundeLangGarNichts();
		}
	}
}

class TVProgAuslosung {
	public static void main(String[] args) {
		TVProgThread t1 = new TVProgThread("Wer wird Millionaer?");
		TVProgThread t2 = new TVProgThread("Enterprise");
		TVProgThread t3 = new TVProgThread("Nils Holgersson");
		t1.start();
		t2.start();
		t3.start();
	}
}

class TVProgThread extends Thread {
	// Konstruktor
	public TVProgThread(String name) {
		super(name);
	}

	// run-Methode (Schleife mit Zufalls-Wartezeiten)
	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(getName() + " zum " + i + ". Mal");
			MachMal.zufaelligGarNichts();
		}
		System.out.println(getName() + " FERTIG!");
	}
}

class MehrmalsT {
	public static void main(String[] args) {
		ABCThread t1 = new ABCThread(), t2 = new ABCThread();
		t1.start();
		t2.start();
	}
}

class ABCThread extends Thread {
	public void run() {
		for (char b = 'A'; b <= 'Z'; b++) {
			// Gib den Buchstaben aus
			System.out.print(b);
			// Verbringe eine Sekunde mit "Nichtstun"
			MachMal.eineSekundeLangGarNichts();
		}
	}
}

class MehrmalsP {
	public static void main(String[] args) {
		ABCPrinter p1 = new ABCPrinter(), p2 = new ABCPrinter();
		p1.start();
		p2.start();
	}
}

class ABCPrinter {
	public void run() {
		for (char b = 'A'; b <= 'Z'; b++) {
			// Gib den Buchstaben aus
			System.out.print(b);
			// Verbringe eine Sekunde mit "Nichtstun"
			MachMal.eineSekundeLangGarNichts();
		}
	}

	public void start() {
		run();
	}
}

class MachMal {
	public static void eineSekundeLangGarNichts() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void wartenAufEingabe() {
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zufaelligGarNichts() {
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}