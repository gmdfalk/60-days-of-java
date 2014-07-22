package grundkurs_java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chap15 {

	public static void main(String[] args) {
		Farbwechsel.main(args);
	}

}

class Farbwechsel extends JFrame {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public Farbwechsel() { // Konstruktor
		// Container bestimmen
		c = getContentPane();
		// Button erzeugen und dem Container hinzufuegen
		button = new JButton("Hintergrundfarbe wechseln");
		c.add(button, BorderLayout.NORTH);
		// Listener-Objekt erzeugen und beim Button registrieren
		ButtonListener bL = new ButtonListener();
		button.addActionListener(bL);
	}

	// Innere Button-Listener-Klasse
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Hintergrundfarbe des Containers zufaellig aendern
			float zufall = (float) Math.random();
			Color grauton = new Color(zufall, zufall, zufall);
			c.setBackground(grauton); // Zugriff auf c moeglich, da
		}
		// ButtonListener innere Klasse
	}

	public static void main(String[] args) {
		Farbwechsel fenster = new Farbwechsel();
		fenster.setTitle("Farbwechsel");
		fenster.setSize(200, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}