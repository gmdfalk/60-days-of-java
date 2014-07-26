package grundkurs_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class EuroApplet2 extends JApplet {

	private Container c;
	private JLabel euroLabelText; // Der Text "Euro"
	private JTextField euroWert; // Der Eingabe-Wert in Euro
	private JComboBox waehrung; // Die Ziel-W�hrung
	private JLabel andererWert; // Der Ausgabe-Wert
	private JCheckBox runden; // Rundungsmodus an/aus

	public void init() {
		c = getContentPane();
		c.setFont(new Font("SansSerif", Font.BOLD, 20));
		c.setLayout(new GridLayout(0, 1));

		c.add(euroLabelText = new JLabel("Euro"));
		c.add(euroWert = new JTextField(""));
		c.add(waehrung = new JComboBox());
		c.add(andererWert = new JLabel(""));
		c.add(runden = new JCheckBox("auf 2 Stellen runden"));

		euroWert.setBackground(Color.YELLOW);
		andererWert.setOpaque(true);
		andererWert.setBackground(Color.YELLOW);

		waehrung.addItem("Mark");
		waehrung.addItem("Schilling");
		waehrung.addItem("Gulden");

		euroWert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				wandle();
			}
		});

		waehrung.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				wandle();
			}
		});
	}

	public void wandle() {
		double wert;
		try {
			wert = Double.parseDouble(euroWert.getText());
		} catch (NumberFormatException nfe) {
			wert = 0;
		}
		wert = Utils.convertTo((String) waehrung.getSelectedItem(), wert);
		if (runden.isSelected())
			andererWert.setText(Utils.zweiNachKomma.format(wert));
		else
			andererWert.setText("" + wert);
	}

}
