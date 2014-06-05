package grundkurs_java;

public class OOP {

	public static void main(String[] args) {
		Student studi = new Student("Charles Dickens", 12345, 7, 1812, true);
		students(studi);
		// Sub s = new Sub();
	}

	private static void students(Student studi) {
		System.out.println(studi.validateNummer());
		System.out.println(studi); // every object has toString()
		System.out.println(Student.getZaehler()); // every object has toString()
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
