package grundkurs_java;

import java.util.regex.Pattern;

public class Chap12 {

	public static void main(String[] args) {
		StringTiming.main(args);
		StringManipulation
				.main("In diesem Grundkurs haben wir so viel gelernt");
		AutoBoxingDangers.main(args);
	}

}

class AutoBoxingDangers {
	public static void main(String[] args) {
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