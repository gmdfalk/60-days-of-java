package grundkurs_java;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.text.*;

import javax.swing.border.*;

public class Chap15 {

	public static void main(String[] args) {
		// Farbwechsel.main(args);
		// Bilderrahmen.main(args);
		// Farbwechsel2.main(args);
		// Farbwechsel4.main(args);
		// CloseToggleButtons.main(args);
		// LookAndFeel.main(args);
		// StoppuhrFrame.main(args);
		// DatumFrame2.main(args);
		// SimpleMenuExample.main(args);
		// FarbenFrame.main(args);
		// RechenFrame.main(args);
		EuroFrame.main(args);
	}

}

class EuroFrame extends JFrame {
	// 15.6
	private Container c;
	private JLabel euroLabel;
	private JComboBox waehrungsBox;
	private JTextField euroField, otherField;
	private EuroConverter converter;

	public EuroFrame() {
		c = getContentPane();
		converter = new EuroConverter();
		euroLabel = new JLabel("Euro");
		euroLabel.setAlignmentX(LEFT_ALIGNMENT);
		euroField = new JTextField();
		waehrungsBox = new JComboBox();
		otherField = new JTextField();
		c.setLayout(new BoxLayout(c, 1));
		c.add(euroLabel);
		c.add(euroField);
		c.add(waehrungsBox);
		c.add(otherField);

		waehrungsBox.addItem("Alles anzeigen");
		waehrungsBox.addItem("Wochentag, Tag und Monat");
		waehrungsBox.addItem("Tag und Monat");
		waehrungsBox.addItemListener(new BoxListener());
	}

	class BoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getItem() == "Alles anzeigen")
				;
			else if (e.getItem() == "Wochentag, Tag und Monat")
				;
			else if (e.getItem() == "Tag und Monat")
				;
		}
	}

	public static void main(String[] args) {
		EuroFrame euro = new EuroFrame();
		euro.setTitle("EuroFrame");
		euro.setSize(200, 100);
		euro.setVisible(true);
		euro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class EuroConverter {
	// 15.6
	// Waehrungs-Kennungen
	static final int DEM = 0, ATS = 1, FRF = 2, BEF = 3, LUF = 4, NLG = 5,
			ESP = 6, PTE = 7, ITL = 8, FIM = 9, IEP = 10, GDR = 11;
	// Umrechnungsfaktoren
	private static final double[] faktor = new double[] { 1.95583, 13.7603,
			6.55957, 40.3399, 40.3399, 2.20371, 166.386, 200.482, 1936.27,
			5.94573, 0.787564, 340.750 };
	// Ausgeschriebene Bezeichnungen der Waehrungen
	private static final String[] bezeichnung = new String[] { "Deutsche Mark",
			"Oesterreichische Schilling", "Franzoesische Franc",
			"Belgische Franc", "Luxemburgische Franc",
			"Niederlaendische Gulden", "Spanische Peseten",
			"Portugiesische Escudos", "Italienische Lire", "Finnische Mark",
			"Irische Pfund", "Griechische Drachmen" };

	// liefert die Bezeichnung zur Waehrungs-Kennung ’kennung’
	static String getBezeichnung(int kennung) {
		return bezeichnung[kennung];
	}

	// konvertiert den Euro-Wert ’euro’ in die durch die
	// Waehrungs-Kennung ’kennung’ spezifizierte Waehrung
	static double convertFromEuro(double euro, int kennung) {
		return faktor[kennung] * euro;
	}

	// konvertiert den Wert ’sonst’ der durch die Waehrungs-Kennung
	// ’kennung’ spezifizierten Waehrung in den entsprechenden Euro-Wert
	static double convertToEuro(double sonst, int kennung) {
		return sonst / faktor[kennung];
	}
}

class RechenFrame extends JFrame {
	// 15.5
	private Container c;
	private JButton add, subtract, multiply, divide, deleteall;
	private JLabel operand1, operand2, result;
	private JTextField operand1Field, operand2Field, resultField;

	public RechenFrame() {
		c = getContentPane();
		c.setLayout(new GridLayout(3, 4));
		operand1Field = new JTextField();
		operand2Field = new JTextField();
		resultField = new JTextField();
		resultField.setEditable(false);
		OperatorListener ol = new OperatorListener(operand1Field,
				operand2Field, resultField);
		add = new JButton("Addieren");
		add.setActionCommand("add");
		add.addActionListener(ol);
		subtract = new JButton("Subtrahieren");
		subtract.setActionCommand("subtract");
		subtract.addActionListener(ol);
		multiply = new JButton("Multiplizieren");
		multiply.setActionCommand("multiply");
		multiply.addActionListener(ol);
		divide = new JButton("Dividieren");
		divide.setActionCommand("divide");
		divide.addActionListener(ol);
		deleteall = new JButton("Alles loeschen");
		deleteall.setActionCommand("deleteall");
		deleteall.addActionListener(ol);
		operand1 = new JLabel("Operand 1:");
		operand1.setHorizontalAlignment(JLabel.RIGHT);
		operand2 = new JLabel("Operand 2:");
		operand2.setHorizontalAlignment(JLabel.RIGHT);
		result = new JLabel("Ergebnis:");
		result.setHorizontalAlignment(JLabel.RIGHT);
		c.add(operand1);
		c.add(operand1Field);
		c.add(operand2);
		c.add(operand2Field);
		c.add(add);
		c.add(subtract);
		c.add(multiply);
		c.add(divide);
		c.add(result);
		c.add(resultField);
		c.add(deleteall);
	}

	class OperatorListener implements ActionListener {
		JTextField o1;
		JTextField o2;
		JTextField rf;

		public OperatorListener(JTextField o1, JTextField o2, JTextField rf) {
			this.o1 = o1;
			this.o2 = o2;
			this.rf = rf;
		}

		public void actionPerformed(ActionEvent e) {
			Double result = null;
			try {
				if (e.getActionCommand() == "add")
					result = Double.parseDouble(o1.getText())
							+ Double.parseDouble(o2.getText());
				else if (e.getActionCommand() == "subtract")
					result = Double.parseDouble(o1.getText())
							- Double.parseDouble(o2.getText());
				else if (e.getActionCommand() == "divide")
					result = Double.parseDouble(o1.getText())
							/ Double.parseDouble(o2.getText());
				else if (e.getActionCommand() == "multiply")
					result = Double.parseDouble(o1.getText())
							* Double.parseDouble(o2.getText());
				else if (e.getActionCommand() == "deleteall") {
					o1.setText("");
					o2.setText("");
				}
			} catch (NumberFormatException nfe) {
				System.out.println(nfe);
				resultField.setText("Error");
				return;
			}
			if (result == null)
				resultField.setText("");
			else
				resultField.setText(result.toString());
		}
	}

	public static void main(String[] args) {
		RechenFrame calc = new RechenFrame();
		calc.setTitle("RechenFrame");
		calc.setSize(500, 80);
		calc.setVisible(true);
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class FarbenFrame extends JFrame {
	private Container c;
	private JRadioButton rb1, rb2, rb3;

	public FarbenFrame() {
		rb1 = new JRadioButton("rot");
		rb2 = new JRadioButton("grün");
		rb3 = new JRadioButton("blau");

		ButtonGroup bg = new ButtonGroup();
		bg.add(rb1);
		bg.add(rb2);
		bg.add(rb3);
		rb1.setSelected(true);

		c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(rb1);
		c.add(rb2);
		c.add(rb3);

		c.addMouseListener(new MausLauscher(c, rb1, rb2, rb3));
	}

	public static void main(String[] args) {
		FarbenFrame ff = new FarbenFrame();
		ff.setTitle("bgSwitcher");
		ff.setSize(200, 100);
		ff.setVisible(true);
		ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

// Innere Button-Listener-Klasse
class MausLauscher extends MouseAdapter {
	Container c;
	JRadioButton rb1, rb2, rb3;

	public MausLauscher(Container c, JRadioButton rb1, JRadioButton rb2,
			JRadioButton rb3) {
		this.c = c;
		this.rb1 = rb1;
		this.rb2 = rb2;
		this.rb3 = rb3;
	}

	public void mousePressed(MouseEvent e) {
		float zufall = (float) Math.random();
		if (zufall < 0.2)
			zufall += 0.1;
		System.out.println(zufall);
		if (rb1.isSelected())
			c.setBackground(new Color(zufall, 0, 0));
		else if (rb2.isSelected())
			c.setBackground(new Color(0, zufall, 0));
		else if (rb3.isSelected())
			c.setBackground(new Color(0, 0, zufall));
	}
}

class DatumFrame2 extends JFrame {
	// 15.2
	Container c;
	// Container dieses Frames
	JButton button;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JLabel datumsAnzeige;
	private JLabel beschriftung;
	private Date datum;
	private static final SimpleDateFormat lang = new SimpleDateFormat(
			"EEEE, dd. MMMM yyyy"), mittel = new SimpleDateFormat(
			"EEEE, dd. MMMM"), kurz = new SimpleDateFormat("dd. MMMM");

	// Knopf
	public DatumFrame2() { // Konstruktor
		// Container bestimmen
		c = getContentPane();

		datum = new Date();

		beschriftung = new JLabel("Heutiges Datum:");
		datumsAnzeige = new JLabel(lang.format(datum));

		MenuListener mL = new MenuListener();
		// Erzeuge die Menueleiste.
		menuBar = new JMenuBar();
		// Erzeuge ein Menue
		menu = new JMenu("Formatierung");
		menu.setMnemonic(KeyEvent.VK_B);

		// Erzeuge die Menue-Eintraege und fuege sie dem Menue hinzu
		menuItem = new JMenuItem("Alles anzeigen");
		menuItem.setMnemonic(KeyEvent.VK_H);
		menuItem.addActionListener(mL);
		// Fuege den Listener hinzu
		menuItem.setActionCommand("lang"); // Setze die Aktionsbezeichnung
		menu.add(menuItem);
		menuItem = new JMenuItem("Wochentag, Tag und Monat");
		menuItem.setMnemonic(KeyEvent.VK_K);
		menuItem.addActionListener(mL);
		menuItem.setActionCommand("mittel"); // Setze die Aktionsbezeichnung
		menu.add(menuItem);
		menuItem = new JMenuItem("Tag und Monat");
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.addActionListener(mL);
		menuItem.setActionCommand("kurz"); // Setze die Aktionsbezeichnung
		menu.add(menuItem);

		menuBar.add(menu);
		// Fuegt die Menueleiste dem Frame hinzu

		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.add(menuBar);
		c.add(beschriftung);
		c.add(datumsAnzeige);
		beschriftung.setAlignmentX(Component.CENTER_ALIGNMENT);
		datumsAnzeige.setAlignmentX(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);

	}

	// Innere Button-Listener-Klasse
	// Listener fuer die Combo-Box
	class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "lang")
				datumsAnzeige.setText(lang.format(datum));
			else if (e.getActionCommand() == "mittel")
				datumsAnzeige.setText(mittel.format(datum));
			else if (e.getActionCommand() == "kurz")
				datumsAnzeige.setText(kurz.format(datum));
		}
	}

	public static void main(String[] args) {
		DatumFrame2 datum = new DatumFrame2();
		datum.setTitle("DatumFrame2");
		datum.setSize(200, 50);
		datum.setVisible(true);
		datum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class DatumFrame extends JFrame {
	// 15.1
	Container c;
	// Container dieses Frames
	JButton button;
	private JComboBox formatAuswahl;
	private JLabel datumsAnzeige;
	private JLabel beschriftung;
	private Date datum;
	private static final SimpleDateFormat lang = new SimpleDateFormat(
			"EEEE, dd. MMMM yyyy"), mittel = new SimpleDateFormat(
			"EEEE, dd. MMMM"), kurz = new SimpleDateFormat("dd. MMMM");

	// Knopf
	public DatumFrame() { // Konstruktor
		// Container bestimmen
		c = getContentPane();

		datum = new Date();

		beschriftung = new JLabel("Heutiges Datum:");
		datumsAnzeige = new JLabel(lang.format(datum));

		formatAuswahl = new JComboBox();
		formatAuswahl.addItem("Alles anzeigen");
		formatAuswahl.addItem("Wochentag, Tag und Monat");
		formatAuswahl.addItem("Tag und Monat");
		formatAuswahl.addItemListener(new BoxListener());

		// c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.setLayout(new GridLayout(3, 1));
		c.add(beschriftung);
		c.add(datumsAnzeige);
		c.add(formatAuswahl);
		beschriftung.setHorizontalAlignment(JLabel.CENTER);
		datumsAnzeige.setHorizontalAlignment(JLabel.CENTER);
		// beschriftung.setAlignmentX(Component.CENTER_ALIGNMENT);
		// datumsAnzeige.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

	// Innere Button-Listener-Klasse
	// Listener fuer die Combo-Box
	class BoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getItem() == "Alles anzeigen")
				datumsAnzeige.setText(lang.format(datum));
			else if (e.getItem() == "Wochentag, Tag und Monat")
				datumsAnzeige.setText(mittel.format(datum));
			else if (e.getItem() == "Tag und Monat")
				datumsAnzeige.setText(kurz.format(datum));
		}
	}

	public static void main(String[] args) {
		DatumFrame datum = new DatumFrame();
		datum.setTitle("DatumFrame");
		datum.setSize(200, 100);
		datum.setVisible(true);
		datum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/** Erzeuge ein Swing-Fenster mit Stoppuhrfunktion */
class StoppuhrFrame extends JFrame {
	Container c;
	JButton startButton, stoppButton, neuButton;
	JLabel startZeit, stoppZeit, differenz, status;
	JComboBox ergebnisFormat;
	Date startZeitObj = null, stoppZeitObj = null;
	Font schriftGross = new Font("SansSerif", Font.BOLD, 20),
			schriftKlein = new Font("SansSerif", Font.BOLD, 12);
	SimpleDateFormat form = new SimpleDateFormat("dd.MM.yy, HH:mm:ss:SS");

	public StoppuhrFrame() { // Konstruktor
		c = getContentPane();
		c.setLayout(new GridLayout(4, 2, 5, 10));
		startZeit = new JLabel("--", JLabel.CENTER);
		startZeit.setFont(schriftKlein);
		startZeit.setBorder(new TitledBorder("Startzeit"));
		stoppZeit = new JLabel("--", JLabel.CENTER);
		stoppZeit.setFont(schriftKlein);
		stoppZeit.setBorder(new TitledBorder("Stoppzeit"));
		differenz = new JLabel("--", JLabel.CENTER);
		differenz.setFont(schriftGross);
		differenz.setBorder(new TitledBorder("Laufzeit"));
		KnopfListener kL = new KnopfListener();
		startButton = new JButton("START");
		startButton.setToolTipText("startet die Stoppuhr");
		startButton.addActionListener(kL);
		stoppButton = new JButton("STOPP");
		stoppButton.setToolTipText("stoppt die Stoppuhr");
		stoppButton.addActionListener(kL);
		neuButton = new JButton("NEU");
		neuButton.setToolTipText("loescht alle Felder");
		neuButton.addActionListener(kL);
		ergebnisFormat = new JComboBox();
		ergebnisFormat.addItem("Laufzeit in ms");
		ergebnisFormat.addItem("Laufzeit in min:sec:ms");
		ergebnisFormat.addItemListener(new BoxListener());
		status = new JLabel("START druecken!", JLabel.CENTER);
		status.setFont(schriftGross);
		stoppButton.setEnabled(false);
		neuButton.setEnabled(false);
		c.add(startZeit);
		c.add(startButton);
		c.add(stoppZeit);
		c.add(stoppButton);
		c.add(differenz);
		c.add(neuButton);
		c.add(ergebnisFormat);
		c.add(status);
	}

	// Bestimmung der Laufzeit in ms oder in min:sec:ms als String
	public String differenzString() {
		long diffZeit = (stoppZeitObj.getTime() - startZeitObj.getTime());
		if (ergebnisFormat.getSelectedIndex() == 0)
			return (diffZeit + " ms");
		else {
			long ms = diffZeit % 1000;
			diffZeit = diffZeit / 1000;

			long s = diffZeit % 60;

			diffZeit = diffZeit / 60;

			long m = diffZeit % 60;

			return (m + ":" + s + ":" + ms);

		}
	}

	// Aktualisierung aller Anzeige-Labels und Buttons
	public void anzeigeAktualisieren() {
		if ((startZeitObj != null) && (stoppZeitObj != null)) {
			startButton.setEnabled(false);
			stoppButton.setEnabled(false);
			neuButton.setEnabled(true);
			startZeit.setText(form.format(startZeitObj));
			stoppZeit.setText(form.format(stoppZeitObj));
			differenz.setText(differenzString());
			status.setText("NEU fuer neuen Stoppvorgang!");
			status.setFont(schriftKlein);
		} else if (startZeitObj != null) {
			startButton.setEnabled(false);
			stoppButton.setEnabled(true);
			neuButton.setEnabled(false);
			startZeit.setText(form.format(startZeitObj));
			status.setText("Uhr laeuft!");
			status.setFont(schriftGross);
		} else {
			startButton.setEnabled(true);
			stoppButton.setEnabled(false);
			neuButton.setEnabled(false);
			startZeit.setText("--");
			stoppZeit.setText("--");
			differenz.setText("--");
			status.setText("START druecken!");
			status.setFont(schriftGross);
		}
	}

	// Listener fuer die Buttons
	class KnopfListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startButton)
				startZeitObj = new Date();
			else if (e.getSource() == stoppButton)
				stoppZeitObj = new Date();
			else if (e.getSource() == neuButton) {
				startZeitObj = null;
				stoppZeitObj = null;
			}
			anzeigeAktualisieren();
		}
	}

	// Listener fuer die Combo-Box
	class BoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			anzeigeAktualisieren();
		}
	}

	public static void main(String[] args) {
		StoppuhrFrame fenster = new StoppuhrFrame();
		fenster.setTitle("Stoppuhr");
		fenster.setSize(380, 250);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class LookAndFeel extends JFrame {
	Container c;
	// Container dieses Frames
	JButton b1, b2, b3;
	// Buttons
	JComboBox cb;
	// Combo-Box
	JFrame f = this;

	// Referenz auf dieses Frame
	public LookAndFeel() {
		// Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new FlowLayout());
		// Layout setzen
		// Erzeuge die Buttons und die Combo-Box
		b1 = new JButton("Metal");
		b2 = new JButton("Motif");
		b3 = new JButton("Windows");
		cb = new JComboBox();
		cb.addItem("Metal");
		cb.addItem("Motif");
		cb.addItem("Windows");
		// Fuege die Komponenten dem Frame hinzu
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(cb);
		// Erzeuge den Listener und registriere ihn
		LafListener ll = new LafListener();
		b1.addActionListener(ll);
		b2.addActionListener(ll);
		b3.addActionListener(ll);
		cb.addItemListener(ll);
	}

	// Innere Listener-Klasse
	public class LafListener implements ItemListener, ActionListener {
		//
		String[] laf = { "javax.swing.plaf.metal.MetalLookAndFeel",
				"com.sun.java.swing.plaf.motif.MotifLookAndFeel",
				"com.sun.java.swing.plaf.windows.WindowsLookAndFeel" };

		// Fuer das ItemListener-Interface
		public void itemStateChanged(ItemEvent e) {
			try {
				int i = cb.getSelectedIndex();
				UIManager.setLookAndFeel(laf[i]);
			} catch (Exception ex) {
				System.err.println(ex);
			}
			SwingUtilities.updateComponentTreeUI(f);
		}

		// Fuer das ActionListener-Interface
		public void actionPerformed(ActionEvent e) {
			try {
				int i;
				if (e.getSource() == b1)
					i = 0;
				else if (e.getSource() == b2)
					i = 1;
				else
					i = 2;
				UIManager.setLookAndFeel(laf[i]);
				cb.setSelectedIndex(i);
			} catch (Exception ex) {
				System.err.println(ex);
			}
			SwingUtilities.updateComponentTreeUI(f);
		}
	}

	public static void main(String[] args) {
		LookAndFeel fenster = new LookAndFeel();
		fenster.setTitle("Look and feel einstellen");
		fenster.setSize(250, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class CloseToggleButtons extends JFrame {
	// Extending its adapter instead of implementing a Listener
	Container c;
	JLabel l;
	JToggleButton b1, b2;

	// Container dieses Frames
	// Label
	// Toggle-Buttons
	public CloseToggleButtons() { // Konstruktor
		c = getContentPane();
		// Container bestimmen
		c.setLayout(new FlowLayout());
		// Layout setzen
		// Erzeuge die Label- und Button-Objekte
		l = new JLabel("Zum Schliessen des Fensters "
				+ "beide Schalter aktivieren!");
		b1 = new JToggleButton("Schalter 1");
		b2 = new JToggleButton("Schalter 2");
		// Fuege die Komponenten dem Frame hinzu
		c.add(l);
		c.add(b1);
		c.add(b2);
		// Registriere WindowListener beim Frame
		addWindowListener(new ClosingListener());
	}

	// Innere Listener-Klasse
	public class ClosingListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			if (b1.isSelected() && b2.isSelected()) {
				e.getWindow().dispose();
				System.exit(0);
			} else
				JOptionPane.showMessageDialog(c,
						"Vor dem Schliessen erst beide Schalter aktivieren!");
		}
	}

	public static void main(String[] args) {
		CloseToggleButtons fenster = new CloseToggleButtons();
		fenster.setTitle("CloseToggleButtons");
		fenster.setSize(400, 100);
		fenster.setVisible(true);
		// Setze das Verhalten des Frames beim Schliessen auf "Nichtstun"
		fenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
}

class Bilderrahmen extends JFrame {
	Container c;
	// Container dieses Frames
	JMenuBar menuBar;
	// Menueleiste
	JMenu menu;
	// Menue
	JMenuItem menuItem;
	// Menue-Eintrag
	JToolBar toolBar;
	// Werkzeugleiste
	JButton button;
	// Knoepfe der Werkzeugleiste
	JLabel bildLabel;

	// Label das im Frame erscheinen soll
	public Bilderrahmen() { // Konstruktor
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
		menuItem = new JMenuItem("Hund");
		menuItem.setMnemonic(KeyEvent.VK_H);
		menuItem.addActionListener(mL);
		// Fuege den Listener hinzu
		menuItem.setActionCommand("dog"); // Setze die Aktionsbezeichnung
		menu.add(menuItem);
		menuItem = new JMenuItem("Katze");
		menuItem.setMnemonic(KeyEvent.VK_K);
		menuItem.addActionListener(mL);
		// Fuege den Listener hinzu
		menuItem.setActionCommand("cat"); // Setze die Aktionsbezeichnung
		menu.add(menuItem);
		menuItem = new JMenuItem("Maus");
		menuItem.setMnemonic(KeyEvent.VK_M);
		menuItem.addActionListener(mL);
		// Fuege den Listener hinzu
		menuItem.setActionCommand("mouse");// Setze die Aktionsbezeichnung
		menu.add(menuItem);
		// Fuege das Menue der Menueleiste hinzu
		menuBar.add(menu);
		// Fuegt die Menueleiste dem Frame hinzu
		setJMenuBar(menuBar);
		// Erzeuge das Listener-Objekt fuer die Werkzeugleiste
		ToolBarListener tL = new ToolBarListener();
		// Erzeuge die Werkzeugleiste
		toolBar = new JToolBar("Rahmenfarbe");
		// Erzeuge die Knoepfe
		button = new JButton(new ImageIcon("images/rot.gif"));
		button.setToolTipText("roter Rahmen");
		button.addActionListener(tL);
		// Fuege den Listener hinzu
		button.setActionCommand("rot");
		// Setze die Aktionsbezeichnung
		toolBar.add(button);
		button = new JButton(new ImageIcon("images/gruen.gif"));
		button.setToolTipText("gruener Rahmen");
		button.addActionListener(tL);
		// Fuege den Listener hinzu
		button.setActionCommand("gruen");
		// Setze die Aktionsbezeichnung
		toolBar.add(button);
		button = new JButton(new ImageIcon("images/blau.gif"));
		button.setToolTipText("blauer Rahmen");
		button.addActionListener(tL);
		// Fuege den Listener hinzu
		button.setActionCommand("blau");
		// Setze die Aktionsbezeichnung
		toolBar.add(button);
		// Erzeuge das Label mit Initial-Bild
		bildLabel = new JLabel(new ImageIcon("images/dog.gif"));
		// Setze die Initial-Hintergrundfarbe des Bilderrahmens und
		// fuege das Label und die Toolbar dem Container hinzu
		c.setBackground(Color.RED);
		c.add(bildLabel, BorderLayout.CENTER);
		c.add(toolBar, BorderLayout.NORTH);
	}

	// Innere Listener-Klasse fuer das Menue
	class MenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Bildauswahl abhaengig von der Aktionsbezeichnung aendern
			bildLabel.setIcon(new ImageIcon("images/" + e.getActionCommand()
					+ ".gif"));
		}
	}

	// Innere Listener-Klasse fuer die Toolbar
	class ToolBarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Hintergrundfarbe abhaengig von der Aktionsbezeichnung aendern
			if (e.getActionCommand() == "rot")
				c.setBackground(Color.RED);
			else if (e.getActionCommand() == "gruen")
				c.setBackground(Color.GREEN);
			else if (e.getActionCommand() == "blau")
				c.setBackground(Color.BLUE);
		}
	}

	public static void main(String[] args) {
		Bilderrahmen fenster = new Bilderrahmen();
		fenster.setTitle("Bilderrahmen");
		fenster.setSize(180, 280);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Farbwechsel4 extends JFrame {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public Farbwechsel4() { // Konstruktor
		// Container bestimmen
		c = getContentPane();
		// Button erzeugen und dem Container hinzufuegen
		button = new JButton("Hintergrundfarbe wechseln");
		c.add(button, BorderLayout.NORTH);
		// Listener-Objekt erzeugen und beim Button registrieren
		ButtonListener bL = new ButtonListener(c);
		button.addActionListener(bL);
	}

	public static void main(String[] args) {
		Farbwechsel4 fenster = new Farbwechsel4();
		fenster.setTitle("Farbwechsel");
		fenster.setSize(200, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ButtonListener implements ActionListener {
	Container c;

	// Referenz auf den zu beinflussenden Container
	public ButtonListener(Container c) {
		this.c = c; // Referenz auf den zu beinflussenden Container sichern
	}

	public void actionPerformed(ActionEvent e) {
		// Hintergrundfarbe des Containers zufaellig aendern
		float zufall = (float) Math.random();
		Color grauton = new Color(zufall, zufall, zufall);
		c.setBackground(grauton);
	}
}

class Farbwechsel3 extends JFrame implements ActionListener {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public Farbwechsel3() { // Konstruktor
		// Container bestimmen
		c = getContentPane();
		// Button erzeugen und dem Container hinzufuegen
		button = new JButton("Hintergrundfarbe wechseln");
		c.add(button, BorderLayout.NORTH);
		// Eigenes Objekt beim Button als Listener registrieren
		button.addActionListener(this);
	}

	// Implementierung der Methode des ActionListener-Interface
	public void actionPerformed(ActionEvent e) {
		// Hintergrundfarbe des Containers zufaellig aendern
		float zufall = (float) Math.random();
		Color grauton = new Color(zufall, zufall, zufall);
		c.setBackground(grauton);
	}

	public static void main(String[] args) {
		Farbwechsel3 fenster = new Farbwechsel3();
		fenster.setTitle("Farbwechsel");
		fenster.setSize(200, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Farbwechsel2 extends JFrame {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public Farbwechsel2() { // Konstruktor
		// Container bestimmen
		c = getContentPane();
		// Button erzeugen und dem Container hinzufuegen
		button = new JButton("Hintergrundfarbe wechseln");
		c.add(button, BorderLayout.NORTH);
		// Listener-Objekt erzeugen und beim Button registrieren
		ActionListener bL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hintergrundfarbe des Containers zufaellig aendern
				float zufall = (float) Math.random();
				Color grauton = new Color(zufall, zufall, zufall);
				c.setBackground(grauton);
			}
		}; // Ende der anonymen Klassendefinition
		button.addActionListener(bL);
	}

	public static void main(String[] args) {
		Farbwechsel2 fenster = new Farbwechsel2();
		fenster.setTitle("Farbwechsel");
		fenster.setSize(200, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Farbwechsel extends JFrame {
	Container c;
	// Container dieses Frames
	JButton button;

	// Knopf
	public Farbwechsel() { // Konstruktor
		// Container bestimmen
		c = getContentPane();
		// Button erzeugen und dem Container hinzufuegen
		button = new JButton("Hintergrundfarbe wechseln");
		c.add(button, BorderLayout.NORTH);
		// Listener-Objekt erzeugen und beim Button registrieren
		ButtonListener bL = new ButtonListener();
		button.addActionListener(bL);
	}

	// Innere Button-Listener-Klasse
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Hintergrundfarbe des Containers zufaellig aendern
			float zufall = (float) Math.random();
			Color grauton = new Color(zufall, zufall, zufall);
			c.setBackground(grauton); // Zugriff auf c moeglich, da
		}
		// ButtonListener innere Klasse
	}

	public static void main(String[] args) {
		Farbwechsel fenster = new Farbwechsel();
		fenster.setTitle("Farbwechsel");
		fenster.setSize(200, 100);
		fenster.setVisible(true);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}