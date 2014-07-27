package grundkurs_java;

public class Chap18 {

	public static void main(String[] args) {
		MehrmalsP.main(args);
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public void start() {
		run();
	}
}