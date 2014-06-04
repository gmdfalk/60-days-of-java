package grundkurs_java;

public class OOP {

	public static void main(String[] args) {
		Student studi = new Student();
		students(studi);
		System.out.println(studi.getName());
	}

	private static void students(Student studi) {
		studi.setName("Karla Karlsson");
		studi.setNumber(12345);
	}
}

class Student {
	private String name;
	private int number;

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int num) {
		this.number = num;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String str) {
		this.name = str;
	}
}

class Test extends OOP {
	public static void main(String[] args) {
		students(); // not visible
	}
}