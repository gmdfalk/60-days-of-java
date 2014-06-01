package grundkurs_java;

public class Methods {

	public static void main(String[] args) {
		System.out.println(fxn(4.0, 8.0, 4));
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
