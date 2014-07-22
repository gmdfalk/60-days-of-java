package grundkurs_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Chap16 {

	public static void main(String[] args) {
		Zeichnung.main(args);
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