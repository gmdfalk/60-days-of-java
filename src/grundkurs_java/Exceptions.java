package grundkurs_java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exceptions {

	public static void main(String[] args) {
		int a = 10, b = 0;
		try {
			System.out.println("a/b = " + (a / b));
		} catch (ArithmeticException e) {
			System.out.println("Tried to divide by 0: " + a);
			a = 11;
		}
		ReadMe.main(args);
	}

}

class ReadMe {
	public static void main(String[] args) {
		try {
			FileReader filereader = new FileReader("/home/demian/RADME");

			while (true) {
				int gelesen = filereader.read();
				if (gelesen == -1)
					break;
				System.out.print((char) gelesen);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
