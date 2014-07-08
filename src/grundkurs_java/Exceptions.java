package grundkurs_java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exceptions {

	public static void main(String[] args) {
		int a = 10, b = 0;
		try {
			System.out.println("a/b = " + (a / b));
		} catch (ArithmeticException e) {
			System.out.println("Tried to divide by 0: " + a);
			a = 11;
		}
		// ReadMe.main(args);
		AssertionTest.main(args);
	}

}

class AssertionTest {
	public static double kehrwert(double x) {
		assert x != 0 : "division by 0";
		return 1 / x;

	}

	public static void main(String[] args) {
		double x = 123.45;
		try {
			System.out.println(kehrwert(x));

		} catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
	}
}

class Exueb8 {
	/** Bestimme eine Zufallszahl zwischen 0 und 0.5 */
	public static double gibZufallszahlBisEinhalb() throws Exception {
		double res = Math.random();
		if (res > 0.5)
			throw new Exception("Zahl zu gross");
		return res;
	}

	/** Hauptprogramm */
	public static void main(String[] args) {
		// Bestimme eine Zufallszahl zwischen 0 und 0.5
		double zahl;
		try {
			zahl = gibZufallszahlBisEinhalb();
		}
		// Falls etwas schief geht (Exception)
		// verwende die Zahl 0.5
		catch (Exception e) {
			zahl = 0.5;
		}
		// gib die Zahl auf dem Bildschirm aus
		System.out.println(zahl);
	}
}

class DigitException extends RuntimeException {
	public DigitException(String message) {
		super(message);
	}
}

class ReadMe {

	public static void check(int z) {
		if (z >= '0' && z <= '9') {
			DigitException de = new DigitException(
					"Fehler beim Lesen: Ziffer aufgetreten!");
			throw de;
		}
	}

	public static void main(String[] args) {
		try {
			FileReader filereader = new FileReader("/home/demian/README");

			while (true) {
				int gelesen = filereader.read();
				if (gelesen == -1)
					break;
				check(gelesen);
				System.out.print((char) gelesen);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e);
		} catch (DigitException e) {
			System.out.println(e);
		}
	}
}
