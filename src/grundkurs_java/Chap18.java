package grundkurs_java;

public class Chap18 {

	public static void main(String[] args) {
		// MehrmalsP.main(args);
		// MehrmalsT.main(args);
		TVProgAuslosung.main(args);
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
			try {
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
			}
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
}