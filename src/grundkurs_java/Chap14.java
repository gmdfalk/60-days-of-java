package grundkurs_java;

import java.awt.*;
import javax.swing.*;

public class Chap14 {

	public static void main(String[] args) {
		// FrameMitTextUndToolTip.main(args);
		// FrameMitSchwarzemLabel.main(args);
		FrameMitMonospacedText.main(args);
	}

}

class FrameMitMonospacedText extends JFrame {
	Container c;
	// Container dieses Frames
	JLabel textLabel; // Label das im Frame erscheinen soll

	public FrameMitMonospacedText() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		// Konstruktor
		// Container bestimmen
		// Layout setzen
		// Erzeuge das Labelobjekt mit Uebergabe des Labeltextes
		textLabel = new JLabel("Monospaced Text");
		// Setze die Schriftart fuer die Labelschriftart
		textLabel.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 30));
		// Fuege das Label dem Frame hinzu
		c.add(textLabel);
	}

	public static void main(String[] args) {
		FrameMitMonospacedText fenster = new FrameMitMonospacedText();
		fenster.setTitle("Frame mit monospaced Text");
		fenster.setSize(300, 80);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitSchwarzemLabel extends JFrame {
	Container c;
	// Container dieses Frames
	FarbigesLabel schwarzesLabel; // Label, das im Frame erscheinen soll

	public FrameMitSchwarzemLabel() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		// Konstruktor
		// Container bestimmen
		// Layout setzen
		// Erzeuge das Labelobjekt mit Uebergabe des Labeltextes
		schwarzesLabel = new FarbigesLabel("schwarzes Label", new Color(255,
				255, 255), Color.BLACK);
		// Fuege das Label dem Frame hinzu
		c.add(schwarzesLabel);
	}

	public static void main(String[] args) {
		FrameMitSchwarzemLabel fenster = new FrameMitSchwarzemLabel();
		fenster.setTitle("Frame mit schwarzem Label");
		fenster.setSize(300, 60);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FarbigesLabel extends JLabel {
	public FarbigesLabel(String text, Color fG, Color bG) { // Konstruktor
		// Uebergabe des Labeltextes an den Super-Konstruktor
		super(text);
		// Setze den Hintergrund des Labels auf undurchsichtig
		setOpaque(true);
		// Setze die Farbe der Beschriftung des Labels
		setForeground(fG);
		// Setze die Hintergrundfarbe des Labels
		setBackground(bG);
	}
}

class FrameMitTextUndToolTip extends JFrame {
	Container c;
	// Container dieses Frames
	JLabel beschriftung; // Label das im Frame erscheinen soll

	public FrameMitTextUndToolTip() { // Konstruktor
		// Bestimme die Referenz auf den eigenen Container
		c = getContentPane();
		// Setze das Layout
		c.setLayout(new FlowLayout());
		// Erzeuge das Labelobjekt mit Uebergabe des Labeltextes
		beschriftung = new JLabel("Label-Text im Frame");
		// Fuege das Label dem Frame hinzu
		c.add(beschriftung);
		// Fuege dem Label einen Tooltip hinzu
		beschriftung.setToolTipText("Des isch nur en Tescht!");
	}

	public static void main(String[] args) {
		FrameMitTextUndToolTip fenster = new FrameMitTextUndToolTip();
		fenster.setTitle("Frame mit Text im Label mit Tooltip");
		fenster.setSize(400, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}