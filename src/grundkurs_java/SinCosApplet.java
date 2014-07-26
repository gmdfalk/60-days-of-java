package grundkurs_java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SinCosApplet extends JApplet {

	private Container c;
	private JTextField argument;
	private JButton executeButton;
	private JCheckBox sinCheckbox;
	private JCheckBox cosCheckbox;
	private ButtonGroup funktion = new ButtonGroup();
	private JLabel argumentLabel, resultLabel;

	public void init() {
		c = getContentPane();
		c.setFont(new Font("Courier", Font.BOLD, 28));
		c.setLayout(new GridLayout(2, 3));
		c.add(argumentLabel = new JLabel("Argument", JLabel.CENTER));
		sinCheckbox = new JCheckBox("SINUS", true);
		funktion.add(sinCheckbox);
		c.add(sinCheckbox);
		executeButton = new JButton("Berechne");
		c.add(executeButton);
		executeButton.addActionListener(new ExecuteListener());
		argument = new JTextField(4);
		c.add(argument);
		cosCheckbox = new JCheckBox("COSINUS", false);
		funktion.add(cosCheckbox);
		c.add(cosCheckbox);
		c.add(resultLabel = new JLabel(""));
		resultLabel.setOpaque(true);
		resultLabel.setBackground(Color.WHITE);
	}

	class ExecuteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				double arg = Double.valueOf(argument.getText()).doubleValue();
				double result;
				if (sinCheckbox.isSelected())
					result = Math.sin(arg);
				else
					result = Math.cos(arg);
				resultLabel.setText("" + result);
			} catch (Exception ex) {
				resultLabel.setText("Unzulaessige Eingabe!");
			}
		}
	}
}
