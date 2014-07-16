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
		ProduktSummeDouble.main(args);
		ProduktSummeBigDec.main(args);
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