package grundkurs_java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/**
 * Erzeuge ein Applet mit einem Button, der in der Lage ist, die vom Browser
 * angezeigte Seite zu wechseln
 */
public class GoogleButtonApplet extends JApplet {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public void init() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		button = new JButton("Zu Google surfen");
		c.add(button);
		ButtonListener bL = new ButtonListener();
		button.addActionListener(bL);
	}

	// Innere Button-Listener-Klasse
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// URL festlegen und anzeigen lassen
			try {
				URL google = new URL("http://www.google.de");
				getAppletContext().showDocument(google);
			} catch (MalformedURLException mfue) {
				System.err.println(mfue);
			}
		}
	}
}