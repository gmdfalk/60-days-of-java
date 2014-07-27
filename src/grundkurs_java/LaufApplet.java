package grundkurs_java;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LaufApplet extends JApplet {
	// 18.3

	private Container c;
	private JComboBox fontBox;
	private JCheckBox letterCheck;

	public void init() {
		c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		fontBox = new JComboBox();
		fontBox.addItem("schwarze Schrift");
		fontBox.addItem("graue Schrift");

		letterCheck = new JCheckBox("Buchstaben");

		c.add(fontBox);
		c.add(letterCheck);
	}
}

class ColorRunButton extends JButton implements Runnable {

	public void run() {
	}

}