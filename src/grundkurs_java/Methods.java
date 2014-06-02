package grundkurs_java;

public class Methods {

	public static void main(String[] args) {
		// System.out.println(fxn(4.0, 8.0, 4));
		// System.out.println(max(2, 4.0));
		// variableArgs(1, 2, 3, 4, 5);
		// visibility();
		System.out.println(tangens(9.48));
		int[] a = { 1, 2, 3, 4, 5, 6 };
		reverseArray(a);
		reverseArrayInPlace(a);
	}

	private static void reverseArrayInPlace(int[] a) {
		int temp, len = a.length - 1;
		for (int i = 0; i < a.length / 2; i++) {
			temp = a[i];
			a[i] = a[len];
			a[len] = temp;
			len -= 1;
		}
		for (int i : a)
			System.out.println(i);
	}

	private static void reverseArray(int[] a) {
		int len = a.length;
		int[] b = new int[len];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[len - 1];
			len -= 1;
		}
		for (int i : b)
			System.out.println(i);

	}

	private static double tangens(double d) {
		return Math.sin(d) / Math.cos(d);
	}

	// class variables
	static int a = 1, b = 2, c = 3;

	static int m(int a) {
		// local variable
		int b = 20;
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		return 100;
	}

	public static void visibility() {
		// formal variable
		int a = 1000;
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("m(c)= " + m(c));
	}

	public static void variableArgs(int... args) {
		int sum = 0;
		System.out.println(args.getClass().getName());
		for (int arg : args) {
			sum += arg;
		}
		System.out.println(sum);
	}

	public static int max(int x, int y) {
		return (x > y) ? x : y;
	}

	public static double max(double x, double y) {
		// overloading max
		System.out.println("double");
		return (x > y) ? x : y;
	}

	public static double f(double x, int n) {
		double result, product = 1.0;
		for (int i = 0; i < 2 * n; i++) {
			product *= x;
		}
		result = product + n * n - n * x;
		return result;
	}

	private static double fxn(double l, double r, int n) {
		double c, left, right, center, mean;
		left = f(l, n);
		right = f(r, n);
		c = (left + right) / 2.0;
		center = f(c, n);
		mean = (left + right + center) / 3.0;
		return mean;
	}

}
