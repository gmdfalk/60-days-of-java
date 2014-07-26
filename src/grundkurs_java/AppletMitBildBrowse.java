package grundkurs_java;

import java.awt.*;
import javax.swing.*;
import java.net.*;

/** Erzeuge ein einfaches Applet mit einem Bild-Label */
public class AppletMitBildBrowse extends JApplet {
	Container c;
	// Container dieses Applets
	JLabel lab;

	// Label das im Applet erscheinen soll
	public void init() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		// Container bestimmen
		// Layout setzen17.5 Sicherheitseinschr ̈ankungen bei Applets
		// Bildobjekt erzeugen
		URL bildURL = createImageURL("kitten.jpg");
		Icon bild = new ImageIcon(bildURL);
		// Label mit Text und Bild beschriften
		lab = new JLabel("Spotty", bild, JLabel.CENTER);
		// Text unter das Bild setzen
		lab.setHorizontalTextPosition(JLabel.CENTER);
		lab.setVerticalTextPosition(JLabel.BOTTOM);
		// Fuege das Label dem Applet hinzu
		c.add(lab);
	}

	public URL createImageURL(String file) {
		String path = getCodeBase() + file;
		try {
			return new URL(path);
		} catch (MalformedURLException mfue) {
			System.err.println(path + " hat nicht die Form einer URL");
			return null;
		}
	}

}
