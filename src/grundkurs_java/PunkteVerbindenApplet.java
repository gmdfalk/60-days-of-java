package grundkurs_java;

import java.awt.*;
import javax.swing.*;

public class PunkteVerbindenApplet extends JApplet {
	Container c;
	// Container dieses Applets
	Zeichenbrett z;

	// Zeichenbrett zum Linien-Malen
	public void init() {
		// Bestimme die Referenz auf den eigenen Container17.4 Details zur
		// HTML-Einbettung
		c = getContentPane();
		// Erzeuge neues Zeichenbrett und fuege es dem Frame hinzu
		z = new Zeichenbrett();
		c.add(z);
	}

}
