package grundkurs_java;

import java.util.*;
import java.text.*;
import javax.swing.*;

/* Thread-Klasse zur dynamischen Zeitanzeige **/
public class AnzeigeThread extends Thread {
	JLabel anzeigeLabel; // Zeitanzeige-Label

	public AnzeigeThread(JLabel anzeigeLabel) { // Konstruktor
		this.anzeigeLabel = anzeigeLabel;
	}

	public String differenzString(Date startZeitObj,
	// Formatierung
			Date aktuelleZeitObj) {
		String anz_m, anz_s, anz_hs;
		long diffZeit = (aktuelleZeitObj.getTime() - startZeitObj.getTime());
		long hs = (diffZeit % 1000) / 10;
		if (hs < 10)
			anz_hs = "0" + hs;
		else
			anz_hs = "" + hs;
		diffZeit = diffZeit / 1000;
		long s = diffZeit % 60;
		if (s < 10)
			anz_s = "0" + s;
		else
			anz_s = "" + s;
		diffZeit = diffZeit / 60;
		long m = diffZeit % 60;
		if (m < 10)
			anz_m = "0" + m;
		else
			anz_m = "" + m;
		return (anz_m + ":" + anz_s + ":" + anz_hs);
	}

	public void run() { // run-Methode
		Date startZeitObj = new Date();
		while (true) {
			if (isInterrupted())
				break;
			Date aktuelleZeitObj = new Date();
			anzeigeLabel
					.setText(differenzString(startZeitObj, aktuelleZeitObj));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}