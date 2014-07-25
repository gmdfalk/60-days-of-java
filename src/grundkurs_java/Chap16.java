package grundkurs_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chap16 {

	public static void main(String[] args) {
		// Zeichnung.main(args);
		// PunkteVerbinden.main(args);
		// NewButtonFrame1.main(args);
		// NewButtonFrame2.main(args);
		// Punkt.main(args);
	}
}

class Dreieck implements GeoObjekt {
	// 16.3
	public void drehen(double phi) {
	};

	public void zeichnen(Graphics g, int xNull, int yNull) {
	};

}

class Strecke implements GeoObjekt {
	// 16.2
	private Punkt p, q;

	public Strecke(Punkt p, Punkt q) {
		this.p = p;
		this.q = q;
	}

	public void drehen(double phi) {
		p.drehen(phi);
		q.drehen(phi);

	}

	public void zeichnen(Graphics g, int xNull, int yNull) {
		g.drawLine(xNull + (int) this.p.getX(), yNull + (int) this.p.getY(),
				xNull + (int) this.q.getX(), yNull + (int) this.q.getY());
	}
}

interface GeoObjekt {
	// 16.2
	public void drehen(double phi);

	// dreht das Objekt um den Winkel phi
	public void zeichnen(Graphics g, int xNull, int yNull);
	// zeichnet das Objekt auf der Zeichenebene
	// xNull und yNull sind die Koordinaten des Ursprungs
	// (Nullpunkts) des verwendeten Koordinatensystems
}

class Punkt {
	// 16.1
	private double x, y;

	public Punkt(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void drehen(double phi) {
		double xAlt = x;
		x = x * Math.cos(phi) - y * Math.sin(phi);
		y = xAlt * Math.sin(phi) + y * Math.cos(phi);
	}

	public static void main(String[] args) {

	}
}

class NewButtonFrame2 extends JFrame {
	Container c;
	JButton b;

	public NewButtonFrame2() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		b = new JButton("Drueck mich!");
		b.addActionListener(new ButtonBearbeiter());
		c.add(b);
	}

	class ButtonBearbeiter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.add(b = new JButton("noch einer"));
			b.revalidate();
		}
	}

	public static void main(String[] args) {
		JFrame fenster = new NewButtonFrame2();
		fenster.setTitle("Buttons hinzufuegen");
		fenster.setSize(500, 300);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class NewButtonFrame1 extends JFrame {
	// new buttons only show after resizing (no revalidation)
	Container c;
	JButton b;

	public NewButtonFrame1() {
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		b = new JButton("Drueck mich!");
		b.addActionListener(new ButtonBearbeiter());
		c.add(b);
	}

	class ButtonBearbeiter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.add(new JButton("noch einer"));
			c.repaint();
		}
	}

	public static void main(String[] args) {
		JFrame fenster = new NewButtonFrame1();
		fenster.setTitle("Buttons hinzufuegen");
		fenster.setSize(500, 300);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class PunkteVerbinden extends JFrame {
	Container c;
	// Container dieses Frames
	Zeichenbrett z;

	// Zeichenbrett zum Linien Malen16.1 Zeichnen in Swing-Komponenten
	public PunkteVerbinden() { // Konstruktor
		c = getContentPane();
		// Container bestimmen
		z = new Zeichenbrett();
		// Zeichenbrett erzeugen
		c.add(z);
		// und dem Frame hinzufuegen
	}

	public static void main(String[] args) {
		PunkteVerbinden fenster = new PunkteVerbinden();
		fenster.setTitle("Punkte verbinden");
		fenster.setSize(250, 200);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Zeichenbrett extends JPanel {
	private int[] x, y; // Koordinaten der Maus-Klicks
	private int n;

	// Anzahl Klicks
	public Zeichenbrett() {
		// Konstruktor
		n = 0;
		x = new int[1000];
		y = new int[1000];
		addMouseListener(new ClickBearbeiter());
	}

	public void paintComponent(Graphics g) {
		g.drawPolyline(x, y, n);
	}

	// Innere Listener-Klasse fuer Maus-Ereignisse
	class ClickBearbeiter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			x[n] = e.getX(); // speichere x-Koordinate
			y[n] = e.getY(); // speichere y-Koordinate
			n++;
			// erhoehe Anzahl Klicks
			repaint();
			// Neuzeichnen der Komponente beim
			// Repaint-Manager anfordern
		}
	}
}

class Zeichnung extends JFrame {
	Container c;
	// Container dieses Frames
	ZeichenPanel z;

	// Zeichnung auf dem Zeichen-Panel
	public Zeichnung() {
		// Konstruktor
		c = getContentPane();
		z = new ZeichenPanel(); // Erzeuge neue Zeichnung
		c.add(z);
		// und fuege sie dem Frame hinzu
	}

	public static void main(String[] args) { // main-Methode
		Zeichnung fenster = new Zeichnung();
		fenster.setTitle("Zeichnung");
		fenster.setSize(200, 200);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ZeichenPanel extends JPanel {
	public void paintComponent(Graphics g) {
		g.drawLine(10, 10, 30, 20);
		int[] x = { 30, 40, 60, 70 };
		int[] y = { 5, 5, 30, 5 };
		g.drawPolyline(x, y, 4);
		g.drawRect(10, 50, 20, 10);
		x = new int[] { 130, 140, 160, 170 };
		y = new int[] { 5, 25, 30, 35 };
		g.drawPolygon(x, y, 4);
		g.drawOval(110, 60, 30, 15);
		g.drawArc(70, 40, 30, 20, 0, 110);
		g.drawString("Wow!", 40, 90);
		g.fillRect(10, 130, 20, 10);
		x = new int[] { 130, 140, 160, 170 };
		y = new int[] { 105, 135, 130, 155 };
		g.fillPolygon(x, y, 4);
		g.fillOval(60, 130, 30, 30);
		g.fillArc(150, 70, 40, 30, 0, -45);
	}
}