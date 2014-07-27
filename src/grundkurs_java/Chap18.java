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
		StoppuhrMitThread.main(args);
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
			System.out.print(hms.format(time) + "\b\b\b\b\b\b\b\b");
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