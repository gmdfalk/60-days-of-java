package grundkurs_java;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;

public class Chap13 {

	public static void main(String[] args) {
		// swing();
		// FrameMitText.main(args);
		ZweiFrames.main(args);
	}

	public static void swing() {
		// Erzeuge ein Fenster-Objekt
		JFrame fenster = new JFrame();
		// Setze den Titel des Fensters
		fenster.setTitle("Mein erstes Swing-Fenster");
		// Setze die Groesse des Fensters
		fenster.setSize(300, 150);
		// Stelle das Fenster dar
		fenster.setVisible(true);
		// Setze das Verhalten des Frames beim Schliessen
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void awt() {
		Frame fenster = new Frame();
		fenster.setTitle("Bla");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
	}
}

class ZweiFrames {
	// 13.1
	public static void main(String[] args) {
		FrameMitText fenster = new FrameMitText();
		fenster.setTitle("Das erste Fenster");
		fenster.setSize(400, 250);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Scanner in = new Scanner(System.in);
		System.out.println("Neuer Fenster-Titel: ");
		String s = in.next();
		fenster.setTitle(s);
		System.out.println("<-'");

		System.out.println("Neue Fenster-Breite: ");
		int b = in.nextInt();
		System.out.println("Neue Fenster-Hoehe: ");
		int h = in.nextInt();
		fenster.setSize(b, h);
		System.out.println("<-'");

		System.out.println("Fenster unsichtbar machen mit Eingabetaste: ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		fenster.setVisible(false);
		System.out.println("<-'");

		System.out.println("Fenster wieder sichtbar machen mit Eingabetaste: ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fenster.setVisible(true);
		System.out.println("<-'");

		System.out
				.println("Fenster auf die Koordinaten (300,10) verschieben mit Eingabetaste: ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fenster.setLocation(300, 10);
		System.out.println("<-'");

		System.out.println("Noch ein Fenster erzeugen mit Eingabetaste: ");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FrameMitText fenster2 = new FrameMitText();
		fenster2.setTitle("Das zweite Fenster");
		fenster2.setSize(300, 150);
		fenster2.setVisible(true);
		System.out.println("<-'");
	}
}

class FrameMitText extends JFrame {
	Container c;
	// Container dieses Frames
	JLabel beschriftung; // Label, das im Frame erscheinen soll

	public FrameMitText() {
		// Konstruktor414
		// Bestimme die Referenz auf den eigenen Container
		c = getContentPane();
		// Setze das Layout
		c.setLayout(new FlowLayout());
		// Erzeuge das Labelobjekt mit Uebergabe des Labeltextes
		beschriftung = new JLabel("Label-Text im Frame");
		// Fuege das Label dem Frame hinzu
		c.add(beschriftung);
	}

	public static void main(String[] args) {
		FrameMitText fenster = new FrameMitText();
		fenster.setTitle("Frame mit Text im Label");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class SidebarProcessBuilder {
	public void example(String[] args) throws IOException {
		Process process;
		process = new ProcessBuilder("C:\\PathToExe\\MyExe.exe", "param1",
				"param2").start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.printf("Output of running %s is:", Arrays.toString(args));

		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}
