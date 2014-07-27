package grundkurs_java;

import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/** Erzeuge ein Swing-Fenster mit Stoppuhrfunktion */
public class StoppuhrFrameThread extends JFrame {
	Container c;
	JButton startButton, stoppButton, neuButton;
	JLabel laufZeit;
	Font schriftGross = new Font("SansSerif", Font.BOLD, 20);
	boolean startPressed, stoppPressed;

	public StoppuhrFrameThread() { // Konstruktor
		c = getContentPane();
		c.setLayout(new GridLayout(2, 2, 5, 10));
		laufZeit = new JLabel("00:00:00", JLabel.CENTER);
		laufZeit.setFont(schriftGross);
		laufZeit.setBorder(new TitledBorder("Laufzeit in mm:ss:hs"));
		KnopfListener kL = new KnopfListener();
		startButton = new JButton("START");
		startButton.setToolTipText("startet die Stoppuhr");
		startButton.addActionListener(kL);
		stoppButton = new JButton("STOPP");
		stoppButton.setToolTipText("stoppt die Stoppuhr");
		stoppButton.addActionListener(kL);
		neuButton = new JButton("NEU");
		neuButton.setToolTipText("setzt Stoppuhr zurueck");
		neuButton.addActionListener(kL);
		stoppButton.setEnabled(false);
		neuButton.setEnabled(false);
		c.add(laufZeit);
		c.add(startButton);
		c.add(neuButton);
		c.add(stoppButton);
	}

	public void anzeigeAktualisieren() {
		if ((startPressed) && (stoppPressed)) {
			startButton.setEnabled(false);
			stoppButton.setEnabled(false);
			neuButton.setEnabled(true);
		} else if (startPressed) {
			startButton.setEnabled(false);
			stoppButton.setEnabled(true);
			neuButton.setEnabled(false);
		} else {
			startButton.setEnabled(true);
			stoppButton.setEnabled(false);
			neuButton.setEnabled(false);
			laufZeit.setText("00:00:00");
		}
	}

	// Listener fuer die Buttons
	class KnopfListener implements ActionListener {
		Thread t;

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startButton) {
				startPressed = true;
				t = new AnzeigeThread(laufZeit);
				t.start();
			} else if (e.getSource() == stoppButton) {
				stoppPressed = true;
				t.interrupt();
			} else if (e.getSource() == neuButton) {
				startPressed = false;
				stoppPressed = false;
			}
			anzeigeAktualisieren();
		}
	}

	public static void main(String[] args) {
		StoppuhrFrameThread fenster = new StoppuhrFrameThread();
		fenster.setTitle("Stoppuhr");
		fenster.setSize(300, 150);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}