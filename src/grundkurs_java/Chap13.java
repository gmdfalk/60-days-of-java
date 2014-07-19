package grundkurs_java;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.swing.*;

public class Chap13 {

	public static void main(String[] args) {
		// swing();
		FrameMitText.main(args);
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
