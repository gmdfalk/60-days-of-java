package grundkurs_java;

import Prog1Tools.Plotter;

public class OOPAdvanced {

	public static void main(String[] args) {
		Vater v = new Vater();
		Vater s = new Sohn(); // but not Sohn s = new Vater();
		v.zeigeVar(); // 1
		((Vater) s).zeigeVar(); // 2 (dynamic binding)
		System.out.println("VATER: " + ((Vater) s).var); // 1 (no dyn binding)
		// note: polymorphism works for methods only.
		Kind k = new Kind();
		Vater vaeterchen = new Vater();
		Sohn soehnchen = new Sohn();
		System.out.println(vaeterchen instanceof Sohn);
		System.out.println(vaeterchen instanceof Vater);
		// System.out.println(vaeterchen instanceof Waehrung); // incompatible
		System.out.println(vaeterchen instanceof Object);
		System.out.println(soehnchen instanceof Sohn);
		System.out.println(soehnchen instanceof Vater);
		// System.out.println(soehnchen instanceof Waehrung);
		System.out.println(soehnchen instanceof Object);

		double d = 3.14;
		Double D = new Double(3.14);
		System.out.println(d + " " + D);
		Lire l = new Lire(1000);
		System.out.println(l);
		// Note: You can prevent instantiation by making the constructor
		// private (apart from defining it abstract, obviously).
		// KreisPlot.main(args);
		Aufzaehlung.main(args);
	}
}

interface Folge {
	/**
	 * Liefert genau dann true, wenn weitere Elemente verfuegbar sind
	 */
	boolean elementeVerfuegbar();

	/** Liefert das naechste Element zurueck */
	Object naechstesElement();
}

class Aufzaehlung {
	/** Feld von Zufallszahlen */
	private double[] zahlen;

	/** Konstruktor. Erzeugt ein Objekt mit n Zufallszahlen */
	public Aufzaehlung(int n) {
		zahlen = new double[n];
		for (int i = 0; i < n; i++)
			zahlen[i] = Math.random();
	}

	/** Gibt die Anzahl der gespeicherten Zahlen aus */
	public int length() {
		return zahlen.length;
	}

	/** Gibt die i-te Zufallszahl zurueck */
	public double getZahl(int i) {
		return zahlen[i];
	}

	public static void main(String[] args) {
		Aufzaehlung zahlen = new Aufzaehlung(10);
		for (int i = 0; i < zahlen.length(); i++) {
			System.out.println(zahlen.getZahl(i));
		}
	}
}

interface Plottable {
	public double inf();

	public double sup();

	public double x(double t);

	public double y(double t);
}

class KreisPlot implements Plottable {
	// 9.7
	// ( x(t) ) <- t
	// ( y(t) ),

	public double inf() {
		return 0;
	}

	public double sup() {
		return 2 + Math.PI;
	}

	public double x(double t) {
		return Math.sin(t);
	}

	public double y(double t) {
		return Math.cos(t);
	}

	public static void main(String[] args) {
		Plotter p = new Plotter(new KreisPlot(), "Kreisplot");
		p.adjustGrid(0.2, 0.2);
		p.showGrid(true);
		p.setNumOfPoints(9);
		p.setVisible(true);
		System.out.print("zum Beenden bitte das ");
		System.out.println("Grafikfenster schliessen.");
	}
}

interface Wertgegenstand {
	// an interface is not a class but it can be "inherited" from with
	// the keyword "implements"
	public Waehrung wert();
}

class Goldbarren implements Wertgegenstand {
	public static double preisProGrammInDollar = 60;
	private double gewicht;

	// constructor
	public Goldbarren(double gewichtInGramm) {
		gewicht = gewichtInGramm;
	}

	// actual interface implementation
	public Waehrung wert() {
		return new USDollar(gewicht * preisProGrammInDollar);
	}

	public static Waehrung gesamtwert(Wertgegenstand[] objekte) {
		double summe = 0;
		for (Wertgegenstand w : objekte)
			summe += w.wert().dollarBetrag();
		return new USDollar(summe);
	}
}

class Kruegerrand extends Waehrung implements Wertgegenstand {
	private static double kurs;
	private double wert;

	public Kruegerrand(double wert) {
		this.wert = wert;
	}

	public double dollarBetrag() {
		return wert * kurs;

	}

	public static void setKurs(double Kurs) {
		kurs = Kurs;
	}

	public Waehrung wert() {
		return this;
	}
}

class Papa {
	// prevent polymorphing/overloading/mutating this method
	public final void singe() {
		System.out.println("La la la la la ...");
	}
}

class Kind extends Papa {
	// public void singe() {
	// System.out.println("Do Re Mi Fa So ...");
	// }
}

class Vater {
	/** Eine oeffentliche Variable var */
	public int var;

	/** Konstruktor */
	public Vater() {
		var = 1;
	}

	/** Ausgabe des Variableninhalts */
	public void zeigeVar() {
		System.out.println("VATER: " + var);
	}
}

class Sohn extends Vater {
	/** Eine oeffentliche Variable var */
	public int var;

	/** Konstruktor */
	public Sohn() {
		var = 2;
	}

	/** Ausgabe des Variableninhalts */
	public void zeigeVar() {
		System.out.println("SOHN: " + var);
	}
}

// abstract = not instantiable. used often to inherit from.
// final = not inheritable (just not mutable, in general).
abstract class Waehrung {
	public abstract double dollarBetrag();
}

class USDollar extends Waehrung {
	private double betrag;

	public USDollar(double betrag) {
		this.betrag = betrag;
	}

	public double dollarBetrag() {
		return betrag;
	}
}

class Yen extends Waehrung {

	private static double kurs;

	private double betrag;

	public Yen(double betrag) {
		this.betrag = betrag;
	}

	public double dollarBetrag() {
		return betrag * kurs;
	}

	public static void setKurs(double Kurs) {
		kurs = Kurs;
	}
}

/** Die Waehrung Europas */
class Euro extends Waehrung {
	private static double kurs = 1;
	private double betrag;

	/** Konstruktor */
	public Euro(double betrag) {
		this.betrag = betrag;
	}

	public double dollarBetrag() {
		return betrag * kurs;
	}

	public double euroBetrag() {
		return betrag;
	}

	public String toString() {
		return euroBetrag() + "€";
	}

	public static void setEuroKurs(double Kurs) {
		kurs = Kurs;
	}
}

class DM extends Euro {

	public DM(double betrag) {
		super(betrag / 1.95583);
	}

	public DM(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 1.95583;
	}
}

class Lire extends Euro {

	public Lire(double betrag) {
		super(betrag / 1936.27);
	}

	public Lire(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 1936.27;
	}
}

class Franc extends Euro {

	public Franc(double betrag) {
		super(betrag / 6.55957);
	}

	public Franc(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 6.55957;
	}

	// overwriting java.lang.Object methods
	public String toString() {
		return "$" + dollarBetrag();
	}

	// Note: When overloading "equals()" also overwrite "hashCode()"
	public boolean equals(Object obj) {
		if (obj instanceof Waehrung)
			return this.dollarBetrag() == ((Waehrung) obj).dollarBetrag();
		else
			return super.equals(obj);
	}

	public int hashCode() {
		return (int) (dollarBetrag() * 100);
	}
}

class CurrencyCalc {
	public DM betrag;

	public CurrencyCalc(double betrag) {
		this.betrag = new DM(betrag);
	}

	public double inEuro() {
		return betrag.euroBetrag();
	}

	public double inFranc() {
		Franc f = new Franc(betrag);
		return f.waehrungsBetrag();
	}

	public double inLire() {
		Lire L = new Lire(betrag);
		return L.waehrungsBetrag();
	}

}