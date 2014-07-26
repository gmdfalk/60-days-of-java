package grundkurs_java;

import java.text.*;

public class Utils {
	// Methode zur Konvertierung des Euro-Werts 'euroWert'
	// in die durch 'waehrung' spezifizierte Waehrung

	public static double convertTo(String waehrung, double euroWert) {
		if (waehrung.equals("Mark"))
			return 1.95583 * euroWert;
		else if (waehrung.equals("Schilling"))
			return 13.7603 * euroWert;
		else
			// waehrung.equals("Gulden")
			return 2.20371 * euroWert;
	}

	// DecimalFormat-Objekt zur Darstellung
	// von Zahlen mit zwei Nachkommastellen

	public static DecimalFormat zweiNachKomma = new DecimalFormat("#.00");

}
