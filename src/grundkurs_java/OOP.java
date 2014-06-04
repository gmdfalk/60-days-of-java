package grundkurs_java;

public class OOP {

	public static void main(String[] args) {
		students();
	}

	private static void students() {
		Student studi = new Student();
		studi.name = "Karla Karlsson";
		studi.number = 12345;
	}

}

class Student {
	public String name;
	public int number;
}