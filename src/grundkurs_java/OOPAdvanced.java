package grundkurs_java;

import java.util.Iterator;

public class OOPAdvanced {

	public static void main(String[] args) {
		Euro a = new Euro(1000);
		DM b = new DM(1);
		CurrencyCalc calc = new CurrencyCalc(123.4);
		System.out.println(calc.inEuro());
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
		return betrag.euroBetrag();
	}

	public double inLire() {
		return betrag.euroBetrag();
	}

}