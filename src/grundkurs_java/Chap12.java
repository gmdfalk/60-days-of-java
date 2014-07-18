package grundkurs_java;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;
import java.util.*;

public class Chap12 {

	public static void main(String[] args) {
		// StringTiming.main(args);
		// StringManipulation
		// .main("In diesem Grundkurs haben wir so viel gelernt");
		// AutoBoxingDangers.main(args);
		// GrößterGemeinsamerTeiler.main(40, 30);
		// Berechne.main(new String[] { "9", "-", "7" });
		// Faculty.main(args);
		// Binomialkoeffizient.main(args);
		// ProduktSummeDouble.main(args);
		// ProduktSummeBigDec.main(args);
		// long a = 9223372036854775807L; // 2^+-63 (64bit limit)
		// DoubleCalc.main();
		// BigDecimalCalc.main();
		// Formel.main(args);
		// BigNewton.main(args);
		// BigNewton2.main(args);
		// StandardFormat.main(args);
		// MyFormats.main(args);
		// Ausgaben.main(args);
		// FestPunktFormat a = new FestPunktFormat(3);
		// System.out.println(a.format(-100.0));
		// Stoppuhr.main(args);
		// CalArith.main(args);
		// CalStoppuhr.main(args);
		// MyDateFormats.main(args);
		// MyStandardDateFormats.main(args);
		// SimpleTime.main(args);
		// ZahlenMenge.main(args);
		// SortierteZahlenMenge.main(args);
		// ZahlenListe.main(args);
		// SortierteZahlenListe.main(args);
		// Lotto.main(args);
		Primes.main(args);
	}
}

class Primes {
	public static void main(String[] args) {
		int n = 13;
		Collection<Integer> T = new TreeSet<Integer>();
		Collection<Integer> S = new TreeSet<Integer>();
		int p = 2;
		while (p * p < n) {
			for (int i = 2; i < n; i++) {
				S.add(i * p);
			}
			if (p == 2) {
				p = 3;
			} else {
				while (S.contains(p)) {
					p += 2;
				}
			}
		}
		System.out.println(T + " " + S);

	}
}

class Lotto {
	// 12.14

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static void main(String[] args) {
		Collection<Integer> c = new HashSet<Integer>();
		while (c.size() < 7) {
			c.add(randInt(1, 49));
			// c.add(new Integer(1 + (int) (48 * Math.random())));
		}
		System.out.println(c);

	}
}

class SortierteZahlenListe {
	/** Methode zur Ausgabe der Listenelemente */
	public static void printList(List l) {
		System.out.println("Die Liste enthaelt die Elemente");
		for (Iterator i = l.iterator(); i.hasNext();)
			System.out.print(i.next() + " ");
		System.out.println();
		System.out.println();
	}

	/** Aufbau und Modifikation einer Liste */
	public static void main(String[] args) {
		List<Double> l = new ArrayList<Double>();
		l.add(new Double(2.2));
		l.add(new Double(1.1));
		l.add(new Double(3.3));
		l.add(new Double(0.0));
		l.add(new Double(7.7));
		l.add(new Double(3.3));
		printList(l);
		Collections.sort(l);
		printList(l);
		System.out.println("Index des Elements mit Wert 0.0: "
				+ Collections.binarySearch(l, new Double(0.0)));
		System.out.println("Index des Elements mit Wert 2.2: "
				+ Collections.binarySearch(l, new Double(2.2)));
		System.out.println("Index des Elements mit Wert 5.5: "
				+ Collections.binarySearch(l, new Double(5.5)));
	}
}

class ZahlenListe {
	/** Methode zur Ausgabe von Infos ueber eine Collection */
	public static void printInfo(Collection c) {
		System.out.println("Die Liste enthaelt " + c.size() + " Elemente");
		System.out.println("Ist 3.3 in der Liste enthalten? "
				+ c.contains(new Double(3.3)));
		System.out.println("Alle Elemente der Liste:");
		for (Iterator i = c.iterator(); i.hasNext();)
			System.out.print(i.next() + " ");
		System.out.println();
		System.out.println();
	}

	/** Aufbau und Modifikation einer Collection */
	public static void main(String[] args) {
		Collection<Double> c = new ArrayList<Double>();
		c.add(new Double(1.1));
		c.add(new Double(2.2));
		c.add(new Double(3.3));
		c.add(new Double(0.0));
		c.add(new Double(3.3));
		c.add(new Double(4.4));
		printInfo(c);
		c.remove(new Double(3.3));
		c.remove(new Double(0.0));
		c.remove(new Double(4.4));
		printInfo(c);
	}
}

class SortierteZahlenMenge {
	/** Methode zur Ausgabe von Infos ueber eine Collection */
	public static void printInfo(Collection c) {
		System.out.println("Die Menge enthaelt " + c.size() + " Elemente");
		System.out.println("Ist 3.3 in der Menge enthalten? "
				+ c.contains(new Double(3.3)));
		System.out.println("Alle Elemente der Menge:");
		for (Iterator i = c.iterator(); i.hasNext();)
			System.out.print(i.next() + " ");
		System.out.println();
		System.out.println();
	}

	/** Aufbau und Modifikation einer Collection */
	public static void main(String[] args) {
		Collection<Double> c = new TreeSet<Double>();
		c.add(new Double(1.1));
		c.add(new Double(2.2));
		c.add(new Double(3.3));
		c.add(new Double(0.0));
		c.add(new Double(3.3));
		c.add(new Double(4.4));
		printInfo(c);
		c.remove(new Double(3.3));
		c.remove(new Double(0.0));
		c.remove(new Double(4.4));
		printInfo(c);
	}
}

class ZahlenMenge {
	/** Methode zur Ausgabe von Infos ueber eine Collection */
	public static void printInfo(Collection c) {
		System.out.println("Die Menge enthaelt " + c.size() + " Elemente");
		System.out.println("Ist 3.3 in der Menge enthalten? "
				+ c.contains(new Double(3.3)));
		System.out.println("Alle Elemente der Menge:");
		for (Iterator i = c.iterator(); i.hasNext();)
			System.out.print(i.next() + " ");
		System.out.println();
		System.out.println();
	}

	/** Aufbau und Modifikation einer Collection */
	public static void main(String[] args) {
		Collection<Double> c = new HashSet<Double>();
		c.add(new Double(1.1));
		c.add(new Double(2.2));
		c.add(new Double(3.3));
		c.add(new Double(0.0));
		c.add(new Double(3.3));
		c.add(new Double(4.4));
		printInfo(c);
		c.remove(new Double(3.3));
		c.remove(new Double(0.0));
		c.remove(new Double(4.4));
		printInfo(c);
	}
}

class SimpleTime {
	// 12.13
	public static void main(String[] args) {
		SimpleDateFormat eins = new SimpleDateFormat(
				"'Heute ist 'EEEE', der 'd.' 'MMMM.");
		SimpleDateFormat zwei = new SimpleDateFormat(
				"'Die Uhr zeigt gerade: 'HH' Uhr und 'MM' Minuten.'");
		Date d = new Date();
		System.out.println(eins.format(d));
		System.out.println(zwei.format(d));
	}
}

class MyStandardDateFormats {
	// Verschiedene Stanard-Formate als Konstanten definieren
	public static final DateFormat eins = DateFormat.getDateInstance(),
			zwei = DateFormat.getDateInstance(DateFormat.SHORT),
			drei = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE),
			vier = DateFormat.getTimeInstance(), fuen = DateFormat
					.getTimeInstance(DateFormat.LONG), sech = DateFormat
					.getTimeInstance(DateFormat.FULL, Locale.US),
			sieb = DateFormat.getDateTimeInstance(), acht = DateFormat
					.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT),
			neun = DateFormat.getDateTimeInstance(DateFormat.LONG,
					DateFormat.LONG, Locale.ITALY);

	// Methode zur formatierten Ausgabe
	public static void println(Date d, DateFormat f) {
		System.out.println(f.format(d));
	}

	// Einige Tests
	public static void main(String[] args) {
		try {
			Date d = acht.parse("11.11.2004 11:11");
			println(d, eins);
			println(d, zwei);
			println(d, drei);
			println(d, vier);
			println(d, fuen);
			println(d, sech);
			println(d, sieb);
			println(d, acht);
			println(d, neun);
		} catch (ParseException pe) {
			System.out.println(pe);
		}
	}
}

class MyDateFormats {
	// Verschiedene Formate als Konstanten definieren
	public static final SimpleDateFormat eins = new SimpleDateFormat(
			"dd.MM.yyyy' um 'HH:mm:ss:S"), zwei = new SimpleDateFormat(
			"EE, MMM d, ''yy"), drei = new SimpleDateFormat("H:mm"),
			vier = new SimpleDateFormat("H' Uhr und 'm' Minuten'"),
			fuen = new SimpleDateFormat("d. MMMM yyyy' 'HH:mm"),
			sech = new SimpleDateFormat("EE, d. MMM yyyy HH:mm:ss"),
			sieb = new SimpleDateFormat("yyMMddHHmmssS");

	// Methode zur formatierten Ausgabe
	public static void println(Date d, SimpleDateFormat f) {
		System.out.println(f.format(d));
	}

	// Einige Tests
	public static void main(String[] args) {
		Date d = new Date();
		println(d, eins);
		println(d, zwei);
		println(d, drei);
		println(d, vier);
		println(d, fuen);
		println(d, sech);
		println(d, sieb);
	}
}

class CalStoppuhr {
	public static void main(String[] args) {
		// Auf Betaetigen der Eingabetaste warten12.5 Die Klassen Date und
		// Calendar
		// Aktuellen Zeitpunkt im Calendar-Objekt start festhalten
		Calendar start = Calendar.getInstance();
		// Zeitpunkt ausgeben
		System.out.println("Startzeitpunkt: " + start.get(Calendar.HOUR_OF_DAY)
				+ ":" + start.get(Calendar.MINUTE) + ":"
				+ start.get(Calendar.SECOND) + ":"
				+ start.get(Calendar.MILLISECOND));
		System.out.println();
		// Statusmeldung anzeigen
		System.out.println("Die Stoppuhr laeuft ...");
		System.out.println();
		// Auf Betaetigen der Eingabetaste warten
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Aktuellen Zeitpunkt im Calendar-Objekt stopp festhalten
		Calendar stopp = Calendar.getInstance();
		// Zeitpunkt ausgeben
		System.out.println("Stoppzeitpunkt: " + stopp.get(Calendar.HOUR_OF_DAY)
				+ ":" + stopp.get(Calendar.MINUTE) + ":"
				+ stopp.get(Calendar.SECOND) + ":"
				+ stopp.get(Calendar.MILLISECOND));
		System.out.println();
		// Laufzeit als Differenz von stopp und start bestimmen
		long laufzeit = stopp.getTimeInMillis() - start.getTimeInMillis();
		// Laufzeit ausgeben
		System.out.println("Gesamtlaufzeit: " + laufzeit + " ms");
		// Laufzeit als Zeitpunkt darstellen
		stopp.setTimeInMillis(laufzeit);
		// Zeitpunkt ausgeben
		System.out.println("Gesamtlaufzeit (min:sec:ms): "
				+ stopp.get(Calendar.MINUTE) + ":" + stopp.get(Calendar.SECOND)
				+ ":" + stopp.get(Calendar.MILLISECOND));
	}
}

class CalArith {
	/** Methode zur Ausgabe eines Zeitpunkts */
	public static void drucke(Calendar t) {
		System.out.println("Zeitpunkt: " + t.get(Calendar.DAY_OF_MONTH) + "."
				+ (t.get(Calendar.MONTH) + 1) + "." + t.get(Calendar.YEAR)
				+ ", " + t.get(Calendar.HOUR_OF_DAY) + ":"
				+ t.get(Calendar.MINUTE) + " Uhr");
	}

	/** Test-Methode */
	public static void main(String[] args) {
		// Aktuellen Zeitpunkt erzeugen380
		Calendar zeit = Calendar.getInstance();
		// Zeitpunkt ausgeben
		drucke(zeit);
		// Zeitpunkt 27 Tage in die Zukunft verlegen
		zeit.add(Calendar.DAY_OF_MONTH, 27);
		// Zeitpunkt ausgeben
		drucke(zeit);
		// Zeitpunkt 4 Jahre in die Vergangenheit verlegen
		zeit.add(Calendar.YEAR, -4);
		// Zeitpunkt ausgeben
		drucke(zeit);
		// Zeitpunkt auf den 11.11.1111, 11.11 Uhr
		zeit.set(1111, 10, 11, 11, 11);
		// Zeitpunkt ausgeben
		drucke(zeit);
	}
}

class Stoppuhr {
	public static void main(String[] args) {
		// Auf Betaetigen der Eingabetaste warten
		// Aktuellen Zeitpunkt im Date-Objekt start festhalten
		Date start = new Date();
		// Zeitpunkt ausgeben
		System.out.println("Startzeitpunkt: " + start);
		System.out.println();
		// Statusmeldung anzeigen
		System.out
				.println("Die Stoppuhr laeuft. Enter drücken, um die Stoppuhr zu beenden.");
		System.out.println();
		// Auf Betaetigen der Eingabetaste warten
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Aktuellen Zeitpunkt im Date-Objekt stopp festhalten
		Date stopp = new Date();
		// Zeitpunkt ausgeben
		System.out.println("Stoppzeitpunkt: " + stopp);
		System.out.println();
		// Laufzeit als Differenz von stopp und start bestimmen
		long laufzeit = stopp.getTime() - start.getTime();
		// Laufzeit ausgeben
		System.out.println("Gesamtlaufzeit: " + laufzeit + " ms");
	}
}

class FestPunktFormat {
	// 12.9 + 12.10
	DecimalFormat f1, f2, f3;

	public FestPunktFormat(int decimalPoints) {
		String dPoints = "";
		if (decimalPoints > 0 && decimalPoints < 13) {
			for (int i = 0; i < decimalPoints; i++)
				dPoints += "0";
		} else {
			System.out
					.println("Decimal points have to be between 1 and 12. Choosing 9.");
			dPoints = "000000000";
		}
		f1 = new DecimalFormat("0." + dPoints);
		f2 = new DecimalFormat(" 0." + dPoints);
		f3 = new DecimalFormat("+0." + dPoints);
	}

	public String format(double x) {
		String result;
		if (x < 0)
			result = f1.format(x);
		else if (x == 0)
			result = f2.format(x);
		else
			result = f3.format(x);
		return result;
	}
}

class Ausgaben {
	public static void main(String[] args) {
		String format = "Ergebnis der Division: % 15.10e\n";
		System.out.printf(format, 3.5 / 7.1);
		System.out.printf(format, -2 / 3.0);
		System.out.printf(format, 123597.3 / 4);
	}
}

class MyFormats {
	// Verschiedene Formate als Konstanten definieren
	public static final DecimalFormat kurz = new DecimalFormat("0.0"),
			lang = new DecimalFormat("00000.00000000000"),
			euro = new DecimalFormat("EUR #0.00"), wiss = new DecimalFormat(
					"#.#E000"), naja = new DecimalFormat("#,###,##0.00"),
			proz = new DecimalFormat("Anteilig: 0.0%");

	// Methode zur formatierten Ausgabe
	public static void println(double d, DecimalFormat f) {
		System.out.println(f.format(d));
	}

	// Einige Tests
	public static void main(String[] args) {
		double x = 987.654321;
		double y = 0.12345678;
		println(x, kurz);
		println(x, lang);
		println(x, euro);
		println(x, wiss);
		println(x, naja);
		println(x, proz);
		println(y, kurz);
		println(y, lang);
		println(y, euro);
		println(y, wiss);
		println(y, naja);
		println(y, proz);
	}
}

class StandardFormat {
	public static void main(String[] args) {
		double x = 1e-15;
		// NumberFormat nf = NumberFormat.getInstance();
		// nf.setMinimumFractionDigits(7);
		// nf.setMaximumFractionDigits(7);
		DecimalFormat f2 = new DecimalFormat("Wert: ###,###.#####");
		for (int i = 1; i <= 15; i++) {
			// System.out.println(nf.format(x));
			// System.out.printf("%.9f", x);
			// System.out.println();
			System.out.println(f2.format(x));
			x = 111 * x;
		}
	}
}

// 12.8
class BigNewton {
	public static BigDecimal zwei = new BigDecimal("2");

	public static BigDecimal f(BigDecimal x) {
		// berechnet f(x)
		return (x.multiply(x)).subtract(zwei);
		// x*x - 2
	}

	public static BigDecimal fstrich(BigDecimal x) { // berechnet f'(x)
		return x.multiply(zwei);
		// x*2
	}

	public static BigDecimal fbig(BigDecimal x) {
		BigDecimal x2 = x.multiply(x);
		return x2.multiply(x2).subtract(BigDecimal.valueOf(3).multiply(x2))
				.subtract(BigDecimal.valueOf(10));
		// x*x*x*x - x*x*3 - 10
	}

	public static BigDecimal fstrichbig(BigDecimal x) { // berechnet f'(x)
		BigDecimal x2 = x.multiply(x);
		return BigDecimal.valueOf(4).multiply(x).multiply(x2)
				.subtract(BigDecimal.valueOf(6).multiply(x));
		// x*2
	}

	public static void main(String[] args) {
		System.out.println("Wurzel-2-Berechnung mit Newton-Verfahren");
		String start = "13";
		int stellen = 50;
		BigDecimal xAlt, xNeu = new BigDecimal(start);
		BigDecimal fx, fsx;
		int runden = BigDecimal.ROUND_HALF_DOWN;
		int k = 0;
		System.out.println("x = " + xNeu);
		do {
			// Newton-Iteration
			k = k + 1;
			xAlt = xNeu;
			fx = fbig(xAlt);
			fsx = fstrichbig(xAlt);
			xNeu = xAlt.subtract(fx.divide(fsx, stellen, runden));
			System.out.println("x = " + xNeu);
		} while (!(xNeu.compareTo(xAlt) == 0) && (k < 100));
	}
}

// 12.7
class DoubleCalc {
	static double a = 1.0 / 107751.0;
	static double y = 35675640.0;
	static double x = 192119201.0;
	static double y2 = y * y;
	static double y4 = y2 * y2;
	static double x2 = x * x;
	static double x4 = x2 * x2;

	public static void main() {
		double z;
		z = a
				* ((1682 * x * y4) + (3 * x2 * x) + (29 * x * y2)
						- (2 * x4 * x) + 832);
		System.out.println(z);
	}
}

class Formel {

	/** Berechnung mit double-Werten */
	public static double berechne(double x, double y) {
		double xh2 = x * x;
		double yh2 = y * y;
		return (1682 * x * yh2 * yh2 + 3 * xh2 * x + 29 * x * yh2 - 2 * xh2
				* xh2 * x + 832) / 107751;
	}

	/** Berechnung mit BigDecimal-Werten */
	public static BigDecimal berechne(BigDecimal x, BigDecimal y) {
		BigDecimal xh2 = x.multiply(x);
		BigDecimal yh2 = y.multiply(y);
		return (BigDecimal.valueOf(1682).multiply(x).multiply(yh2)
				.multiply(yh2))
				.add(BigDecimal.valueOf(3).multiply(xh2).multiply(x))
				.add(BigDecimal.valueOf(29).multiply(x).multiply(yh2))
				.subtract(
						BigDecimal.valueOf(2).multiply(xh2).multiply(xh2)
								.multiply(x)).add(BigDecimal.valueOf(832))
				.divide(BigDecimal.valueOf(107751), BigDecimal.ROUND_HALF_UP);
	}

	public static void main(String[] args) {
		double z = berechne(192119201, 35675640);
		System.out.println("Variante 1: z = " + z);
		BigDecimal z2 = berechne(BigDecimal.valueOf(192119201),
				BigDecimal.valueOf(35675640));
		System.out.println("Variante 2: z = " + z2);
	}

}

class BigDecimalCalc {
	static BigDecimal a = BigDecimal.valueOf(1.0).divide(
			BigDecimal.valueOf(107751.0), BigDecimal.ROUND_HALF_UP);
	static BigDecimal y = BigDecimal.valueOf(35675640.0);
	static BigDecimal x = BigDecimal.valueOf(192119201.0);
	static BigDecimal y2 = y.multiply(y);
	static BigDecimal y4 = y2.multiply(y2);
	static BigDecimal x2 = x.multiply(x);
	static BigDecimal x3 = x2.multiply(x);
	static BigDecimal x4 = x2.multiply(x2);
	static BigDecimal x5 = x4.multiply(x);
	static BigDecimal xy2 = x.multiply(y2);
	static BigDecimal xy4 = x.multiply(y4);
	static BigDecimal s1 = xy4.multiply(BigDecimal.valueOf(1682));
	static BigDecimal s2 = x3.multiply(BigDecimal.valueOf(3));
	static BigDecimal s3 = xy2.multiply(BigDecimal.valueOf(29));
	static BigDecimal s4 = x5.multiply(BigDecimal.valueOf(2));
	static BigDecimal s5 = BigDecimal.valueOf(832);

	public static void main() {
		BigDecimal z, parens;
		parens = s1.add(s2.add(s3.subtract(s4.add(s5))));
		z = a.multiply(parens);
		System.out.println(z);
	}
}

class ProduktSummeBigDec {
	// double can only handle 15 places so for some cases we need BigDecimal
	public static void main(String[] args) {
		BigDecimal[] x = new BigDecimal[6];
		BigDecimal[] y = new BigDecimal[6];
		BigDecimal p, s;
		x[0] = new BigDecimal("1e16");
		x[1] = new BigDecimal("0.1223");
		x[2] = new BigDecimal("1e14");
		x[3] = new BigDecimal("1e15");
		x[4] = new BigDecimal("3.0");
		x[5] = new BigDecimal("-1e12");
		y[0] = new BigDecimal("1e20");
		y[1] = new BigDecimal("2.0");
		y[2] = new BigDecimal("-1e22");
		y[3] = new BigDecimal("1e9");
		y[4] = new BigDecimal("0.2111");
		y[5] = new BigDecimal("1e12");
		s = new BigDecimal("0");
		System.out.println("s = " + s);
		for (int i = 0; i < 6; i++) {
			p = x[i].multiply(y[i]);
			System.out.println(" + " + p + " liefert");
			s = s.add(p);
			System.out.println("s = " + s);
		}
	}
}

class ProduktSummeDouble {
	public static void main(String[] args) {
		double[] x = new double[6];
		double[] y = new double[6];
		double p, s;
		x[0] = 1e16;
		x[1] = 0.1223;
		x[2] = 1e14;
		x[3] = 1e15;
		x[4] = 3.0;
		x[5] = -1e12;
		y[0] = 1e20;
		y[1] = 2.0;
		y[2] = -1e22;
		y[3] = 1e9;
		y[4] = 0.2111;
		y[5] = 1e12;
		s = 0;
		System.out.println("s = " + s);
		for (int i = 0; i < 6; i++) {
			p = x[i] * y[i];
			System.out.println(" + " + p + " liefert");
			s = s + p;
			System.out.println("s = " + s);
		}
	}
}

interface BinomialWrapper {
	BigInteger call(int m, int k);
}

class Binomialkoeffizient {

	public static BigInteger standard(int m, int k) {
		BigInteger bigM = BigInteger.valueOf(m);
		BigInteger bigK = BigInteger.valueOf(k);
		BigInteger differenz = bigM.subtract(bigK);
		return Faculty.iterative(bigM)
				.divide((Faculty.iterative(bigK).multiply(Faculty
						.iterative(differenz))));
	}

	public static BigInteger alternative(int m, int k) {
		BigInteger bigM = BigInteger.valueOf(m);
		BigInteger bigK = BigInteger.valueOf(k);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger result = one;
		for (BigInteger i = one; i.compareTo(bigK) <= 0; i = i.add(one)) {
			result = result.multiply(bigM).divide(i);
			bigM = bigM.subtract(one);
		}
		return result;
	}

	public static void timeIt(BinomialWrapper bw, long iterations) {
		long startTime = System.nanoTime();
		for (int i = 0; i < iterations; i++)
			bw.call(6, 3);
		long endTime = System.nanoTime();
		long differenceNano = endTime - startTime;
		double differenceSecs = differenceNano / 1000000000.0;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(7);
		System.out.println(iterations + " iterations: "
				+ nf.format(differenceSecs) + "s " + "(" + differenceNano
				+ " nanoseconds, " + bw);
	}

	public static void main(String[] args) {
		int iterations = 1000;
		timeIt(new BinomialWrapper() {

			@Override
			public BigInteger call(int m, int k) {
				return standard(m, k);
			}
		}, iterations);
		timeIt(new BinomialWrapper() {

			@Override
			public BigInteger call(int m, int k) {
				return alternative(m, k);
			}
		}, iterations);
	}

}

class Faculty {
	// 12.5
	public static void main(String[] args) {
		BigInteger n = new BigInteger("4");
		System.out.println(iterative(n));
		System.out.println(recursive(n));
	}

	public static BigInteger iterative(BigInteger n) {
		BigInteger result = n;
		while (n.longValue() > 1) {
			n = n.subtract(new BigInteger("1"));
			result = result.multiply(n);
		}
		return result;
	}

	public static BigInteger recursive(BigInteger n) {
		if (n.longValue() < 2) {
			return BigInteger.valueOf(1);
		}
		return n.multiply(recursive(n.subtract(BigInteger.valueOf(1))));
	}
}

class Berechne {

	private final static String HILFE = "Anwendung: java Berechne <zahl1> +|-|*|/ <zahl2>";

	public static void main(String[] args) {

		// Stimmt die Zahl der Argumente?
		if (args.length != 3) {
			System.out.println(HILFE);
			return;
		}

		// Ist das erste Argument gueltig?
		double z1 = 0;
		try {
			z1 = Double.parseDouble(args[0]);
		} catch (NumberFormatException e) {
			System.out.println("zahl1 ungueltig");
			return;
		}

		// Ist das zweite Argument gueltig?
		args[1] = args[1].trim();
		if (!Arrays.asList(new String[] { "+", "-", "*", "/" }).contains(
				args[1])) {
			System.out.println("operator ungueltig: " + args[1]);
			System.out.println(HILFE);
			return;
		}

		// Ist das dritte Argument gueltig?
		double z2 = 0;
		try {
			z2 = Double.parseDouble(args[2]);
		} catch (NumberFormatException e) {
			System.out.println("zahl2 ungueltig");
			return;
		}

		// Fuehre die Operation aus
		switch (args[1].charAt(0)) {
		case '+':
			System.out.println(z1 + z2);
			break;
		case '-':
			System.out.println(z1 - z2);
			break;
		case '*':
			System.out.println(z1 * z2);
			break;
		case '/':
			System.out.println(z1 / z2);
			break;
		}

	}
}

class GrößterGemeinsamerTeiler {
	// 12.3
	public static void main(int a, int b) {
		int z = a;
		int n = b;
		while (z != n) {
			if (z > n)
				z = z - n;
			else
				n = n - z;
		}
		System.out.println("ggT(" + a + "," + b + ") = " + z);
	}
}

class AutoBoxingDangers {
	public static void main(String[] args) {
		// true, false, byte, chars (unicode to 007F), int and short from -128
		// to 127 is always the same Object/reference
		Double u = 1.0;
		Double v = 1.0;
		System.out.println(u == v);
		Integer i = 126;
		Integer j = 126;
		System.out.println(i == j);
		i++;
		j++;
		System.out.println(i == j);
		i++;
		j++;
		System.out.println(i == j);
		i = new Integer("1");
		j = new Integer("1");
		System.out.println(i == j);
	}
}

class StringManipulation {
	// 12.2
	public static void main(String sentence) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < sentence.length(); i++) {
			if (sentence.charAt(i) == 'a' || sentence.charAt(i) == 'e'
					|| sentence.charAt(i) == 'E' || sentence.charAt(i) == 'A') {
				continue;
			} else if (sentence.charAt(i) == 'i' || sentence.charAt(i) == 'o'
					|| sentence.charAt(i) == 'I' || sentence.charAt(i) == 'O') {
				result.append(sentence.charAt(i));
				result.append(sentence.charAt(i));
			} else if (sentence.charAt(i) == 'u') {
				result.append("x");
			} else if (sentence.charAt(i) == 'U') {
				result.append("X");
			} else {
				result.append(sentence.charAt(i));
			}
		}
		System.out.println(result);
	}
}

class StringTiming {
	// 12.1
	public static void nobuffer(int iterations) {
		final long startTime = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < iterations; i++)
			str = str + "x";
		System.out.println("nobuffer ran for "
				+ (System.currentTimeMillis() - startTime) + " seconds " + "("
				+ iterations + " iterations).");
	}

	public static void buffer(int iterations) {
		// Uses only 1 String-Buffer
		final long startTime = System.currentTimeMillis();
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < iterations; i++)
			buf = buf.append("x");
		System.out.println("buffer ran for "
				+ (System.currentTimeMillis() - startTime) + " seconds " + "("
				+ iterations + " iterations).");
	}

	public static void main(String[] args) {
		nobuffer(10000);
		buffer(10000);
	}
}