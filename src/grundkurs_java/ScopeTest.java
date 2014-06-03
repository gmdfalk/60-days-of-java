package grundkurs_java;

import java.util.ArrayList;

import grundkurs_java.Methods;

public class ScopeTest {

	public static void main(String[] args) {
		Methods m = new Methods();
		System.out.println(m.recursePalindrome("asdf"));
		ScopeTest st = new ScopeTest();
		st.Foo();
		Echolot.main(args);
	}

	private ArrayList<Person> people;

	public void Foo() {
		this.people = new ArrayList<Person>();
		Person hans = new Person();
		addItem(hans);
	}

	public void addItem(Person newMember) {
		people.add(newMember);
	}
}

class Person {

}

class Echolot {

	public static double g = 9.81;

	// a)
	public static double f(double x) {
		return Math.pow(x, 2) - g * (x - 1 + Math.exp(-x));
	}

	// b)
	public static double fs(double x) {
		return 2 * x - g * (1 - Math.exp(-x));
	}

	// c)
	public static double newton(double x0, double eps) {

		double xAlt, xNeu = x0;
		int k = 0;

		do {
			k++;
			xAlt = xNeu;
			xNeu = xAlt - f(xAlt) / fs(xAlt);
		} while ((Math.abs(xNeu - xAlt) > eps * Math.abs(xNeu)) && (k < 50));

		if (Math.abs(xNeu - xAlt) > eps * Math.abs(xNeu))
			return -1;
		else
			return xNeu;
	}

	// d)
	public static double tiefe(double t, double x) {
		return g * (x * t + Math.exp(-x * t) - 1) / Math.pow(x, 2);
	}

	// e)
	public static void main(String[] args) {
		double x0 = 10;
		double eps = 1e-7;
		double x = newton(x0, eps);
		if (x > 0)
			System.out.println("Tiefe des Meeres: " + tiefe(10, x) + " Meter");
		else
			System.out.println("Newton-Verfahren konvergiert nicht!");
	}
}
