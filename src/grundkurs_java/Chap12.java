package grundkurs_java;

public class Chap12 {

	public static void main(String[] args) {
		StringOps.main(args);
	}

}

class StringOps {

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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