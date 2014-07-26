package grundkurs_java;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class Applets extends JApplet {
	Container c;
	// Container dieses Applets17.1 Erstellen und Ausf uhren
	JLabel beschriftung;

	// Label das im Applet erscheinen soll
	// init-Methode fuer unser Applet mit Textlabel
	public void init() {
		// Bestimme die Referenz auf den eigenen Container
		c = getContentPane();
		// Setze das Layout
		c.setLayout(new FlowLayout());
		// Erzeuge das Labelobjekt mit Uebergabe des Labeltextes
		beschriftung = new JLabel("Label-Text im Applet");
		// Fuege das Label dem Applet hinzu
		c.add(beschriftung);
	}
}
