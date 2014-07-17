package grundkurs_java;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.math.BigDecimal;

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
		BigNewton.main(args);
		// BigNewton2.main(args);
	}
}

class BigNewton2 {
	public static BigDecimal drei = new BigDecimal("3");
	public static BigDecimal vier = new BigDecimal("4");
	public static BigDecimal sechs = new BigDecimal("6");
	public static BigDecimal zehn = new BigDecimal("10");

	public static BigDecimal f(BigDecimal x) { // berechnet f(x)
		BigDecimal xh2 = x.multiply(x);
		return xh2.multiply(xh2).subtract(drei.multiply(xh2)).subtract(zehn);
	}

	public static BigDecimal fstrich(BigDecimal x) { // berechnet f'(x)
		BigDecimal xh2 = x.multiply(x);
		return vier.multiply(x).multiply(xh2).subtract(sechs.multiply(x));
	}

	public static void main(String[] args) {
		System.out.println("Newton-Verfahren fuer x^4-3x^2-10");

		String start = "13";
		int stellen = 50;

		BigDecimal xAlt, xNeu = new BigDecimal(start);
		BigDecimal fx, fsx;
		int runden = BigDecimal.ROUND_HALF_DOWN;
		int k = 0;
		System.out.println("x = " + xNeu);
		do { // Newton-Iteration
			k = k + 1;
			xAlt = xNeu;
			fx = f(xAlt);
			fsx = fstrich(xAlt);
			xNeu = xAlt.subtract(fx.divide(fsx, stellen, runden));
			System.out.println("x = " + xNeu);
		} while (!(xNeu.compareTo(xAlt) == 0) && (k < 100));
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

	public static BigDecimal fstrich(BigDecimal x) { // berechnet f’(x)
		return x.multiply(zwei);
		// x*2
	}

	public static BigDecimal fbig(BigDecimal x) {
		BigDecimal x2 = x.multiply(x);
		return x2.multiply(x2).subtract(BigDecimal.valueOf(3).multiply(x2))
				.subtract(BigDecimal.valueOf(10));
		// x*x*x*x - x*x*3 - 10
	}

	public static BigDecimal fstrichbig(BigDecimal x) { // berechnet f’(x)
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