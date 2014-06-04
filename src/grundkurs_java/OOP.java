package grundkurs_java;

public class OOP {

	public static void main(String[] args) {
		Student studi = Student.createStudent();
		students(studi);
	}

	private static void students(Student studi) {
		studi.setName("Karla Karlsson");
		studi.setNumber(12345);
		System.out.println(studi.validateNumber());
		System.out.println(studi); // every object has toString()
		System.out.println(Student.getCounter()); // every object has toString()
	}
}

class Student {

	// encapsulation.
	private String name;
	private int number;
	private final int yearOfBirth;
	private static int counter = 0;
	public static final Student PHANTOM;

	static {
		// Constructor (static initializer) for static class objects.
		PHANTOM = new Student(1735);
		PHANTOM.setName("Erik le Phant");
		PHANTOM.number = -12345; // illegal value so we manipulate the object
									// directly instead of the reference.
		counter = 0; // can't avoid the constructor so we have to reset here.
	}

	public Student() {
		// Constructor (no arguments), redirect to overloaded constructor.
		// Restricted to constructors, must be first argument, only once.
		this(1970);
	}

	public Student(int yearOfBirth) {
		// constructor
		this.yearOfBirth = yearOfBirth;
		counter++;
	}

	public static int getCounter() {
		return counter;
	}

	public static Student createStudent() {
		// counter++;
		return new Student();
	}

	public String toString() {
		return name + " (" + number + ')';
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int num) {
		int oldNumber = number;
		number = num;
		if (!validateNumber())
			number = oldNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String str) {
		this.name = str;
	}

	public boolean validateNumber() {
		return (number >= 10000 && number <= 99999 && number % 2 != 0);
	}
}

class Test extends OOP {
	public static void main(String[] args) {
		students(); // not visible
	}
}