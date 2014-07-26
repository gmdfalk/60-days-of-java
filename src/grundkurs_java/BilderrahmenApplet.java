/*
 * "Grundkurs Programmieren in Java (6. Auflage, 2011)"
 * 2003-2011, Carl Hanser Verlag
 * Loesungsvorschlag zu Aufgabe 17.1 (Version 2.0)
 * (c) 2003-2011 D. Ratz, J. Scheffler, D. Seese, J. Wiesenberger
 *
 */
package grundkurs_java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class BilderrahmenApplet extends JApplet {
	Container c; // Container dieses Frames
	JMenuBar menuBar; // Menueleiste
	JMenu menu; // Menue
	JMenuItem menuPunkt; // Menue-Eintrag
	JToolBar toolBar; // Werkzeugleiste
	JButton button; // Knoepfe der Werkzeugleiste
	JLabel bildLabel; // Label das im Frame erscheinen soll

	public void init() {
		// Bestimme die Referenz auf den eigenen Container
		c = getContentPane();

		// Erzeuge das Listener-Objekt fuer das Menue
		MenuListener mL = new MenuListener();

		// Erzeuge die Menueleiste.
		menuBar = new JMenuBar();
		// Erzeuge ein Menue
		menu = new JMenu("Bilder");
		menu.setMnemonic(KeyEvent.VK_B);
		// Erzeuge die Menue-Eintraege und fuege sie dem Menue hinzu
		menuPunkt = new JMenuItem("Hund");
		menuPunkt.setMnemonic(KeyEvent.VK_H);
		menuPunkt.addActionListener(mL); // Fuege den Listener hinzu
		menuPunkt.setActionCommand("dog"); // Setze die Aktionsbezeichnung
		menu.add(menuPunkt);
		menuPunkt = new JMenuItem("Katze");
		menuPunkt.setMnemonic(KeyEvent.VK_K);
		menuPunkt.addActionListener(mL); // Fuege den Listener hinzu
		menuPunkt.setActionCommand("cat"); // Setze die Aktionsbezeichnung
		menu.add(menuPunkt);
		menuPunkt = new JMenuItem("Maus");
		menuPunkt.setMnemonic(KeyEvent.VK_M);
		menuPunkt.addActionListener(mL); // Fuege den Listener hinzu
		menuPunkt.setActionCommand("mouse");// Setze die Aktionsbezeichnung
		menu.add(menuPunkt);
		// Fuege das Menue der Menueleiste hinzu
		menuBar.add(menu);
		// Fuegt die Menueleiste dem Frame hinzu
		setJMenuBar(menuBar);

		// Erzeuge das Listener-Objekt fuer die Werkzeugleiste
		ToolBarListener tL = new ToolBarListener();

		// Erzeuge die Werkzeugleiste
		toolBar = new JToolBar("Rahmenfarbe");
		// Erzeuge die Knoepfe
		button = new JButton(createImageIcon("images/rot.gif"));
		button.setToolTipText("roter Rahmen");
		button.addActionListener(tL); // Fuege den Listener hinzu
		button.setActionCommand("rot"); // Setze die Aktionsbezeichnung
		toolBar.add(button);
		button = new JButton(createImageIcon("images/gruen.gif"));
		button.setToolTipText("gruener Rahmen");
		button.addActionListener(tL); // Fuege den Listener hinzu
		button.setActionCommand("gruen"); // Setze die Aktionsbezeichnung
		toolBar.add(button);
		button = new JButton(createImageIcon("images/blau.gif"));
		button.setToolTipText("blauer Rahmen");
		button.addActionListener(tL); // Fuege den Listener hinzu
		button.setActionCommand("blau"); // Setze die Aktionsbezeichnung
		toolBar.add(button);

		// Erzeuge das Label mit Initial-Bild
		bildLabel = new JLabel(createImageIcon("images/dog.gif"));

		// Setze die Initial-Hintergrundfarbe des Bilderrahmens und
		// fuege das Label und die Toolbar dem Container hinzu
		c.setBackground(Color.red);
		c.add(bildLabel, BorderLayout.CENTER);
		c.add(toolBar, BorderLayout.NORTH);
	}

	// Innere Listener-Klasse fuer das Menue
	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Bildauswahl abhaengig von der Aktionsbezeichnung aendern
			bildLabel.setIcon(createImageIcon("images/" + e.getActionCommand()
					+ ".gif"));
		}
	}

	// Innere Listener-Klasse fuer die Toolbar
	class ToolBarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Hintergrundfarbe abhaengig von der Aktionsbezeichnung aendern
			if (e.getActionCommand() == "rot")
				c.setBackground(Color.red);
			else if (e.getActionCommand() == "gruen")
				c.setBackground(Color.green);
			else if (e.getActionCommand() == "blau")
				c.setBackground(Color.blue);
		}
	}

	protected ImageIcon createImageIcon(String path) {
		path = getCodeBase() + path;
		URL imgURL = null;
		try {
			imgURL = new URL(path);
		} catch (MalformedURLException mfue) {
			System.err.println(path + "existiert nicht");
		}
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Kann Datei nicht finden: " + path);
			return null;
		}
	}
}