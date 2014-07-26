package grundkurs_java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/**
 * Erzeuge ein Applet, das die Beschriftungen des Border-Layouts als Parameter
 * uebergeben bekommt
 */
public class ParameterApplet extends JApplet {
	Container c;
	JLabel lab;
	String[] param = { "north", "east", "south", "west", "center" };
	String[] ort = { BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.SOUTH,
			BorderLayout.WEST, BorderLayout.CENTER };

	public void init() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		for (int i = 0; i < 5; i++) {
			lab = new JLabel(getParameter(param[i]), JLabel.CENTER);
			c.add(lab, ort[i]);
		}
	}
}
