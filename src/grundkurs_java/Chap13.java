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
		FrameOhneInhalt.main(args);
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

class FrameOhneInhalt extends JFrame {
	// Konstruktor fuer unseren Frame
	public FrameOhneInhalt() {
		// Hier werden spaeter die Komponenten hinzugefuegt
	}

	public static void main(String[] args) {
		// Erzeuge eine Instanz unseres Frames
		FrameOhneInhalt fenster = new FrameOhneInhalt();
		// Titelleiste definieren
		fenster.setTitle("Frame ohne Inhalt");
		// Setze die Groesse des Frames
		fenster.setSize(300, 150);
		// Schalte den Frame sichtbar
		fenster.setVisible(true);
		// Setze das Verhalten des Frames beim Schliessen
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
