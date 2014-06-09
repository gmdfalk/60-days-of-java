package grundkurs_java;

public class OOP {

	public static void main(String[] args) {
		Student studi = new Student("Charles Dickens", 12345, 7, 1812, true);
		// students(studi);
		// Sub s = new Sub();
		// SpecialStudent a = new SpecialStudent("SS", 12345, 1, 1812, false);
		// SpecialStudent b = new SpecialStudent();
		// System.out.println(studi);
		// a.setNummer(1234790); // invalid nummer
		// a.setNummer(1848500); // valid nummer
		// a.setNummer(111111); // valid nummer
		// System.out.println(a);
		// Hund.main(args);
		// TestZwei.main(args);
		// Fehler2.main(args);
		// Fehler4.main(args);
		// Fehler6.main(args);
		// TennisSpieler t = new TennisSpieler();
		// TennisSpieler u = new TennisSpieler();
		// Human herbert = new Human(42, "Herbert", "Landser", true);
		// Human marianne = new Human();
		// System.out.println(Human.getCounter());
		// System.out.println(herbert);
		// Point a = new Point(0.0, 0.0);
		// Point b = new Point(4.0, 3.0);
		// Distance d = new Distance(a, b);
		// System.out.println(d.toString());
		// System.out.println(d.getLength());
		// AchJa.main(args);
	}

	private static void students(Student studi) {
		System.out.println(studi.validateNummer());
		System.out.println(studi); // every object has toString()
		System.out.println(Student.getZaehler()); // every object has toString()
	}
}

class Patient {
	public String name;
	public int age;
	private Patient previous;
	private int number;
	public static int nextNumber = 1;

	public Patient(String name, int age) {
		this.name = name;
		this.age = age;
		previous = this;
		number = nextNumber;
		nextNumber += 1;
	}

	public boolean isFirst() {
		if (previous == null)
			return true;
		return false;
	}

	public int getNumber() {
		return number;
	}

	public Patient(String name, int age, Patient previous) {
		this(name, age); // call 2-arg constructor.
		this.previous = previous;
	}

	public int ageDifference(int age) {
		return Math.abs(age - this.age);
	}
}

class AchJa {
	// 8.11
	public int x;
	static int ach;

	int ja(int i, int j) {
		int y;
		if ((i <= 0) || (j <= 0) || (i % j == 0) || (j % i == 0)) {
			System.out.print(i + j);
			return i + j;
		} else {
			x = ja(i - 2, j);
			System.out.print(" + ");
			y = ja(i, j - 2);
			return x + y;
		}
	}

	public static void main(String[] args) {
		int n = 5, k = 2;
		AchJa so = new AchJa();
		System.out.print("ja(" + n + "," + k + ") = ");
		ach = so.ja(n, k);
		System.out.println(" = " + ach);
	}
}

class Distance {
	private Point p;
	private Point q;

	public Distance(Point p, Point q) {
		this.p = p;
		this.q = q;
	}

	public double getLength() {
		return Math.sqrt(Math.pow((p.getX() - q.getX()), 2)
				+ Math.pow(p.getY() - q.getY(), 2));
	}

	public String toString() {
		return p.toString() + " " + q.toString();
	}
}

class Point {
	// 8.10
	private double x, y;

	public Point() {

	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String toString() {
		return x + "," + y;
	}

}

class Human {
	private int age;
	private static int counter = 0;
	private String firstName;
	private String lastName;
	private boolean male = false;
	private int sum = 0;

	public Human() {
		counter++;
	}

	public Human(int age, String firstName, String lastName, boolean male) {
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.male = male;
		// this(firstName, lastName, male);
		counter++;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static int getCounter() {
		return counter;
	}

	public boolean isMale() {
		return male;
	}

	public boolean olderThan(Human h) {
		return (age > h.getAge());
	}

	public String toString() {
		String gender = "female";
		if (male)
			gender = "male";
		return firstName + " " + lastName + ", " + age + ", " + gender + ", "
				+ counter;
	}
}

class TennisSpieler {
	// 8.8
	public String name;
	public int alter;
	public int startNummer;
	public static int folgeNummer = 0;
	public TennisSpieler verfolger = null;

	public TennisSpieler() {
		startNummer = folgeNummer++;
		System.out.println(startNummer);
		System.out.println(folgeNummer);
	}

	public TennisSpieler(String name, int alter) {
		this.name = name;
		this.alter = alter;
		startNummer = folgeNummer++;
	}

	// Name des Spielers
	// Alter in Jahren ̈
	public int altersDifferenz(int alter) {
		return Math.abs(alter - this.alter);
	}
}

class Super {
	public String x = "Vor Super-Konstruktor";

	public Super() {
		System.out.println("Super-Konstruktor gestartet.");
		System.out.println("x = " + x);
		x = "nach Super-Konstruktor";
		System.out.println("Super-Konstruktor beendet.");
		System.out.println("x = " + x);
	}
}

class Sub extends Super {
	/** Eine weitere oeffentliche Instanzvariable */
	public String y = "vor Sub-Konstruktor";

	/** Ein argumentloser Konstruktor */
	public Sub() {
		System.out.println("Sub-Konstruktor gestartet.");
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		x = "nach Sub-Konstruktor";
		y = "nach Sub-Konstruktor";
		System.out.println("Sub-Konstruktor beendet.");
		System.out.println("x = " + x);
		System.out.println("y = " + y);
	}
}

class SpecialStudent extends Student {
	// 8.3

	public SpecialStudent() {
		super(1970);
	}

	public SpecialStudent(int yearOfBirth) {
		super(yearOfBirth);
	}

	public SpecialStudent(String name, int nummer, int fach, int geburtsjahr) {
		super(name, nummer, fach, geburtsjahr);
	}

	public SpecialStudent(String name, int nummer, int fach, int geburtsjahr,
			boolean geschlecht) {
		super(name, nummer, fach, geburtsjahr, geschlecht);
	}

	public boolean validateNummer() {
		int num, counter, crossSum;
		int[] digits = new int[7];
		int[] factors = { 2, 1, 4, 3, 2, 1 };
		num = getNummer();
		crossSum = 0;
		counter = 7;
		while (num > 0) {
			digits[--counter] = num % 10;
			num /= 10;
		}
		if (counter > 0) {
			System.out.println("Invalid length");
			return false;
		}
		for (int i = 0; i < 6; i++)
			crossSum += digits[i] * factors[i];
		if (digits[6] != crossSum % 10) {
			System.out.println(digits[6] + " != " + crossSum % 10);
			System.out.println("Invalid nummer " + getNummer());
			return false;
		}
		System.out.println("Valid nummer " + getNummer());
		return true;
	}
}

class Student {
	// 8.2

	/*
	 * ========== KONSTANTEN ==========
	 */

	/**
	 * Diese Konstante symbolisiert das Studienfach Mathematik
	 */
	public static final int MATHEMATIKSTUDIUM = 1;

	/**
	 * Diese Konstante symbolisiert das Studienfach Informatik
	 */
	public static final int INFORMATIKSTUDIUM = 2;

	/**
	 * Diese Konstante symbolisiert das Studienfach Architektur
	 */
	public static final int ARCHITEKTURSTUDIUM = 3;

	/**
	 * Diese Konstante symbolisiert das Studienfach der
	 * Wirtschaftswissenschaften
	 */
	public static final int WIRTSCHAFTLICHESSTUDIUM = 4;

	/**
	 * Diese Konstante symbolisiert das Studienfach Biologie
	 */
	public static final int BIOLOGIESTUDIUM = 5;

	/**
	 * Diese Konstante symbolisiert das Studienfach Geschichte
	 */
	public static final int GESCHICHTSSTUDIUM = 6;

	/**
	 * Diese Konstante symbolisiert das Studienfach Germanistik
	 */
	public static final int GERMANISTIKSTUDIUM = 7;

	/**
	 * Diese Konstante symbolisiert das Studienfach Politologie
	 */
	public static final int POLITOLOGIESTUDIUM = 8;

	/**
	 * Diese Konstante symbolisiert das Studienfach Physik
	 */
	public static final int PHYSIKSTUDIUM = 9;

	/**
	 * Diese Konstante repraesentiert das Phantom des Campus
	 */
	public static final Student PHANTOM;

	// Neue Konstanten: maennlich/weiblich
	public static final boolean MAENNLICH = true;
	public static final boolean WEIBLICH = false;

	/*
	 * ========= VARIABLEN =========
	 */

	/** Zaehlt die Anzahl der erzeugten Studentenobjekte */
	private static int zaehler = 0;

	/** Der Name des Studenten */
	private String name = "DummyStudent";

	/** Die Matrikelnummer des Studenten */
	private int nummer;

	/** Studienfach des Studenten */
	private int fach;

	/** Geburtsjahr eines Studenten */
	private int geburtsjahr;

	/** Geschlecht eines Studenten */
	private boolean geschlecht = true; // 'true' = maennlich, 'false' = weiblich

	/*
	 * ========= METHODEN =========
	 */

	/** Gib den Namen des Studenten als String zurueck */
	public String getName() {
		return this.name;
	}

	/** Setze den Namen des Studenten auf einen bestimmten Wert */
	public void setName(String name) {
		this.name = name;
	}

	/** Gib die Matrikelnummer des Studenten als Integer zurueck */
	public int getNummer() {
		return nummer;
	}

	/** Setze die Matrikelnummer des Studenten auf einen bestimmten Wert */
	public void setNummer(int n) {
		int alteNummer = nummer;
		nummer = n;
		if (!validateNummer()) { // neue Nummer ist nicht gueltig
			nummer = alteNummer;
		}
	}

	/** Gib das Studienfach des Studenten als Integer zurueck */
	public int getFach() {
		return fach;
	}

	/**
	 * Setze das Studienfach des Studenten auf einen bestimmten Wert
	 */
	public void setFach(int fach) {
		this.fach = fach;
	}

	/** Gib das Geburtsjahr des Studenten als Integer zurueck */
	public int getGeburtsjahr() {
		return geburtsjahr;
	}

	/**
	 * Pruefe die Matrikelnummer des Studenten auf ihre Gueltigkeit
	 */
	public boolean validateNummer() {
		return (nummer >= 10000 && nummer <= 99999 && nummer % 2 != 0);
	}

	/** Gib eine textuelle Beschreibung dieses Studenten aus */
	public String toString() {
		String res = name + " (" + nummer + ")\n";

		if (geschlecht)
			res += " (m) ";
		else
			res += " (w) ";

		switch (fach) {
		case MATHEMATIKSTUDIUM:
			return res + "  ein Mathestudent " + "(oder auch zwei, oder drei).";
		case INFORMATIKSTUDIUM:
			return res + "  ein Informatikstudent.";
		case ARCHITEKTURSTUDIUM:
			return res + "  angehender Architekt.";
		case WIRTSCHAFTLICHESSTUDIUM:
			return res + "  ein Wirtschaftswissenschaftler.";
		case BIOLOGIESTUDIUM:
			return res + "  Biologie ist seine Staerke.";
		case GESCHICHTSSTUDIUM:
			return res + "   sollte Geschichte nicht mit Geschichten "
					+ "verwechseln.";
		case GERMANISTIKSTUDIUM:
			return res + "  wird einmal Germanist gewesen geworden sein.";
		case POLITOLOGIESTUDIUM:
			return res + "  kommt bestimmt einmal in den Bundestag.";
		case PHYSIKSTUDIUM:
			return res + "  studiert schon relativ lange Physik.";
		default:
			return res + "  keine Ahnung, was der Mann studiert.";
		}
	}

	/** Gib die Zahl der erzeugten Studentenobjekte zurueck */
	public static int getZaehler() {
		return zaehler;
	}

	/** Erzeugt ein neues Studentenobjekt */
	public static Student createStudent() {
		return new Student();
	}

	/*
	 * ============= KONSTRUKTOREN =============
	 */

	/** Argumentloser Konstruktor */
	public Student() {
		this(1970);
	}

	/** Konstruktor, bei dem sich das Geburtsjahr setzen laesst. */
	public Student(int geburtsjahr) {
		zaehler++;
		this.geburtsjahr = geburtsjahr;
		this.name = "Namenlos";
	}

	// ** Aufgabe 8.1: Konstruktor, der alle Instanzenvariablen setzen kann
	public Student(String name, int nummer, int fach, int geburtsjahr) {

		this(geburtsjahr); // ruft den Konstruktor 'public Student(int
							// geburtsjahr)'
		this.name = name;
		this.fach = fach;
		this.nummer = nummer;
	}

	// *** Aufgabe 8.2: Konstruktor mit Beruecksichtigung des Geschlechts
	public Student(String name, int nummer, int fach, int geburtsjahr,
			boolean geschlecht) {

		this(name, nummer, fach, geburtsjahr);
		this.geschlecht = geschlecht;

	}

	public Student(int geburtsjahr, boolean geschlecht) {

		this(geburtsjahr);
		this.geschlecht = geschlecht;

	}

	/*
	 * ==================== STATISCHE INITIALISIERUNG ====================
	 */

	static {
		// Erzeuge das PHANTOM-Objekt
		PHANTOM = new Student(1735);
		PHANTOM.name = "Erik le Phant";
		PHANTOM.nummer = -12345;
		// Setze den Zaehler wieder zurueck
		zaehler = 0;
	}
}

class Maus {
	Maus() {
		System.out.println("Maus");
	}
}

class Katze {
	Katze() {
		System.out.println("Katze");
	}
}

class Ratte extends Maus {
	Ratte() {
		System.out.println("Ratte");
	}
}

class Fuchs extends Katze {
	Fuchs() {
		System.out.println("Fuchs");
	}
}

class Hund extends Fuchs {
	Maus m = new Maus();
	Ratte r = new Ratte();

	Hund() {
		System.out.println("Hund");
	}

	public static void main(String[] args) {
		// Superclass constructor is always called first.
		new Hund();
	}
}

class Eins {
	public long f;
	public static long g = 2;

	public Eins(long f) {
		this.f = f;
	}

	public Object clone() {
		return new Eins(f + g);
	}
}

class Zwei {
	public Eins h;

	public Zwei(Eins eins) {
		h = eins;
	}

	public Object clone() {
		return new Zwei(h);
	}
}

class TestZwei {
	public static void main(String[] args) {
		Eins e1 = new Eins(1), e2;
		// e1.f = 1, e1.g = 2
		Zwei z1 = new Zwei(e1), z2;
		//
		System.out.print(Eins.g + "-");
		// 2-
		System.out.println(z1.h.f);
		// 1
		e2 = (Eins) e1.clone();
		z2 = (Zwei) z1.clone();
		e1.f = 4;
		Eins.g = 5;
		System.out.print(e2.f + "-");
		// 3-
		System.out.print(e2.g + "-");
		// 5-
		System.out.print(z1.h.f + "-");
		// 4-
		System.out.print(z2.h.f + "-");
		// 4-
		System.out.println(z2.h.g);
		// 5
	}
}

class Fehler2 {
	/** Private Instanzvariable */
	private String name;

	/** Konstruktor */
	public Fehler2(String name) {
		this.name = name;
	}

	/** String-Ausgabe */
	public String toString() {
		return "Name = " + name;
	}

	/** Hauptprogramm */
	public static void main(String[] args) {
		System.out.println(new Fehler2("Testname"));
	}
}

class Fehler4 {
	/** Private Instanzvariable */
	private String name;

	/** Konstruktor */
	public Fehler4(String nom) {
		name = nom;
	}

	/** String-Ausgabe */
	public String toString() {
		return "Name = " + name;
	}

	/** Hauptprogramm */
	public static void main(String[] args) {
		System.out.println(new Fehler4("Testname"));
	}
}

class Fehler5 {
	/** Private Instanzvariable */
	private String name;

	/** Konstruktor */
	public void Fehler5(String name) {
		this.name = name;
	}

	/** String-Ausgabe */
	public String toString() {
		return "Name = " + name;
	}

	/** Hauptprogramm */
	public static void main(String[] args) {
		// System.out.println(new Fehler5("Testname"));
	}
}

class Fehler6 {
	/** Private Instanzvariable */
	private String name;

	/** Konstruktor */
	public Fehler6() {
	}

	/** String-Ausgabe */
	public String toString() {
		return "Name = " + name;
	}

	/** Hauptprogramm */
	public static void main(String[] args) {
		Fehler6 variable = new Fehler6();
		variable.name = "Testname";
		System.out.println(variable);
	}
}
