package grundkurs_java;

import java.awt.*;
import javax.swing.*;

public class Chap14 {

	public static void main(String[] args) {
		// FrameMitTextUndToolTip.main(args);
		// FrameMitSchwarzemLabel.main(args);
		// FrameMitMonospacedText.main(args);
		// FrameMitFlowLayout.main(args);
		// FrameMitBorderLayout.main(args);
		// FrameMitGridLayout.main(args);
		// FrameMitBild.main(args);
		// FrameMitButtons.main(args);
		FrameMitToggleButtons.main(args);
	}
}

class FrameMitToggleButtons extends JFrame {
	Container c;
	// Container dieses Frames
	// Feld fuer Toggle-Buttons, die im Frame erscheinen sollen
	JToggleButton b[] = new JToggleButton[4];

	public FrameMitToggleButtons() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		// Konstruktor
		// Container bestimmen
		// Layout setzen
		// Erzeuge die Button-Objekte
		for (int i = 0; i < 4; i++) {
			b[i] = new JToggleButton("Schalter " + (i + 1));
			b[i].setFont(new Font("SansSerif", Font.ITALIC, 24));
		}
		b[0].setSelected(true);
		b[2].setSelected(true);
		// Fuege die Buttons dem Frame hinzu
		for (int i = 0; i < 4; i++) {
			c.add(b[i]);
		}
	}

	public static void main(String[] args) {
		FrameMitToggleButtons fenster = new FrameMitToggleButtons();
		fenster.setTitle("Frame mit Buttons");
		fenster.setSize(330, 130);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitButtons extends JFrame {
	Container c;
	// Container dieses Frames
	// Feld fuer Buttons, die im Frame erscheinen sollen
	JButton b[] = new JButton[4];

	public FrameMitButtons() {
		// Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new FlowLayout());
		// Layout setzen
		// Erzeuge die Button-Objekte
		for (int i = 0; i < 4; i++) {
			b[i] = new JButton("Taste " + (i + 1));
			b[i].setFont(new Font("SansSerif", Font.ITALIC, 24));
		}
		// Fuege die Buttons dem Frame hinzu
		for (int i = 0; i < 4; i++) {
			c.add(b[i]);
		}
	}

	public static void main(String[] args) {
		FrameMitButtons fenster = new FrameMitButtons();
		fenster.setTitle("Frame mit Buttons");
		fenster.setSize(250, 130);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitBild extends JFrame {
	Container c;
	// Container dieses Frames
	JLabel lab;

	// Label das im Frame erscheinen soll
	public FrameMitBild() { // Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new FlowLayout());
		// Layout setzen
		// Bildobjekt erzeugen
		Icon bild = new ImageIcon("kitten.jpg");
		// Label mit Text und Bild beschriften
		lab = new JLabel("Spotty", bild, JLabel.CENTER);
		// Text unter das Bild setzen
		lab.setHorizontalTextPosition(JLabel.CENTER);
		lab.setVerticalTextPosition(JLabel.BOTTOM);
		// Fuege das Label dem Frame hinzu
		c.add(lab);
	}

	public static void main(String[] args) {
		FrameMitBild fenster = new FrameMitBild();
		fenster.setTitle("Label mit Bild und Text");
		fenster.setSize(250, 185);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitGridLayout extends JFrame {
	Container c;
	// Container dieses Frames
	// Feld fuer Labels, die im Frame erscheinen sollen
	FarbigesLabel fl[] = new FarbigesLabel[6];

	public FrameMitGridLayout() {
		// Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new GridLayout(2, 3, 10, 40)); // Layout setzen
		/* Erzeuge die Labelobjekte mit Text und Farbe */
		for (int i = 0; i < 6; i++) {
			int rgbFg = 255 - i * 50;
			int rgbBg = i * 50;
			fl[i] = new FarbigesLabel("Nummer " + (i + 1), new Color(rgbFg,
					rgbFg, rgbFg), new Color(rgbBg, rgbBg, rgbBg));
			fl[i].setFont(new Font("Serif", Font.ITALIC, 10 + i * 3));
		}
		// Fuege die Labels dem Frame hinzu
		for (int i = 0; i < 6; i++) {
			c.add(fl[i]);
		}
	}

	public static void main(String[] args) {
		FrameMitGridLayout fenster = new FrameMitGridLayout();
		fenster.setTitle("Frame mit Grid-Layout");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitBorderLayout extends JFrame {
	Container c;
	// Container dieses Frames
	// Labelfeld fuer Label, die im Frame erscheinen sollen430
	FarbigesLabel fl[] = new FarbigesLabel[5];

	public FrameMitBorderLayout() {
		// Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new BorderLayout());
		// Layout setzen
		/* Erzeuge die Labelobjekte mit Text und Farbe */
		fl[0] = new FarbigesLabel("Norden", Color.BLACK, Color.WHITE);
		fl[1] = new FarbigesLabel("Sueden", Color.WHITE, Color.LIGHT_GRAY);
		fl[2] = new FarbigesLabel("Osten", Color.WHITE, Color.GRAY);
		fl[3] = new FarbigesLabel("Westen", Color.WHITE, Color.DARK_GRAY);
		fl[4] = new FarbigesLabel("Zentrum", Color.WHITE, Color.BLACK);
		for (int i = 0; i < 5; i++) {
			// Setze die Schriftart der Labelbeschriftung
			fl[i].setFont(new Font("SansSerif", Font.BOLD, 14));
			// Setze die horizontale Position des Labeltextes auf dem Label
			fl[i].setHorizontalAlignment(JLabel.CENTER);
		}
		// Fuege die Labels dem Frame hinzu
		c.add(fl[0], BorderLayout.NORTH);
		c.add(fl[1], BorderLayout.SOUTH);
		c.add(fl[2], BorderLayout.EAST);
		c.add(fl[3], BorderLayout.WEST);
		c.add(fl[4], BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		FrameMitBorderLayout fenster = new FrameMitBorderLayout();
		fenster.setTitle("Frame mit Border-Layout");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FrameMitFlowLayout extends JFrame {
	Container c;
	// Container dieses Frames
	// Feld fuer Labels, die im Frame erscheinen sollen
	FarbigesLabel fl[] = new FarbigesLabel[4];

	public FrameMitFlowLayout() {
		// Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new FlowLayout());
		// Layout setzen
		// Erzeuge die Labelobjekte mit Uebergabe der Labeltexte
		for (int i = 0; i < 4; i++) {
			int rgbFg = 255 - i * 80;
			// Farbwert fuer Vordergrund
			int rgbBg = i * 80;
			// Farbwert fuer Hintergrund
			fl[i] = new FarbigesLabel("Nummer " + (i + 1), new Color(rgbFg,
					rgbFg, rgbFg), new Color(rgbBg, rgbBg, rgbBg));
			fl[i].setFont(new Font("Serif", Font.ITALIC, 28));
		}
		// Fuege die Labels dem Frame hinzu
		for (int i = 0; i < 4; i++) {
			c.add(fl[i]);
		}
	}

	public static void main(String[] args) {
		FrameMitFlowLayout fenster = new FrameMitFlowLayout();
		fenster.setTitle("Frame mit Flow-Layout");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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