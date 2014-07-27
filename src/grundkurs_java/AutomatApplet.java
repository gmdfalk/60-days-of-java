package grundkurs_java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/** Applet mit einfacher Spielautomaten-Funktionalitaet */
public class AutomatApplet extends JApplet {
	Container c;
	ColorRunLabel rotAnzeige, gelbAnzeige, gruenAnzeige;
	StartStopButton rotKnopf, gelbKnopf, gruenKnopf;

	public void init() {
		c = getContentPane();
		c.setLayout(new GridLayout(2, 3, 5, 5));
		c.add(rotAnzeige = new ColorRunLabel(Color.RED));
		c.add(gelbAnzeige = new ColorRunLabel(Color.YELLOW));
		c.add(gruenAnzeige = new ColorRunLabel(Color.GREEN));
		c.add(rotKnopf = new StartStopButton(Color.RED));
		c.add(gelbKnopf = new StartStopButton(Color.YELLOW));
		c.add(gruenKnopf = new StartStopButton(Color.GREEN));
		rotKnopf.addActionListener(new KnopfListener(rotAnzeige, rotKnopf));
		gruenKnopf
				.addActionListener(new KnopfListener(gruenAnzeige, gruenKnopf));
		gelbKnopf.addActionListener(new KnopfListener(gelbAnzeige, gelbKnopf));
	}
}

class KnopfListener implements ActionListener {
	ColorRunLabel crl;
	StartStopButton ssb;

	KnopfListener(ColorRunLabel crl, StartStopButton ssb) {
		this.crl = crl;
		this.ssb = ssb;
	}

	public void actionPerformed(ActionEvent e) {
		if (ssb.isStart()) // falls Start-Knopf
			crl.start();
		// Thread des Labels starten
		else
			// andernfalls
			crl.stop();
		// Thread des Labels abbrechen
		ssb.switchText(); // Beschriftung des Buttons wechseln
	}
}

class StartStopButton extends JButton {
	public StartStopButton(Color c) {
		setBackground(c);
		setFont(new Font("Arial", Font.PLAIN, 25));
		setText("START");
	}

	public boolean isStart() {
		return getText().equals("START");
	}

	public void switchText() {
		if (isStart())
			setText("STOP");
		else
			setText("START");
	}
}

class ColorRunLabel extends JLabel implements Runnable {
	private boolean running = false;

	public ColorRunLabel(Color c) {
		setOpaque(true);
		setBackground(c);
		setFont(new Font("Arial", Font.BOLD, 50));
		setHorizontalAlignment(JLabel.CENTER);
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	public void run() {
		while (running) {
			setText("" + (int) (10 * Math.random()));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}