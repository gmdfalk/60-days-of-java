package grundkurs_java;

public class Chap11 {

	public static void main(String[] args) {
		// System.out.println(Jahreszeit.WINTER.ordinal());
		// System.out.println(Jahreszeit.WINTER.equals(Jahreszeit.FRUEHLING));
		// for (Jahreszeit jz : Jahreszeit.values())
		// System.out.println(jz + " hat den Wert " + jz.ordinal());
		// Noten g = Noten.G;
		// System.out.println(g.liegtAufSchwarzerTaste());
		// System.out.println(Noten.AIS.liegtAufSchwarzerTaste());
		StudentNeu Peter = new StudentNeu();
		Peter.setName("Peter Honig");
		Peter.setNummer(12345);
		Peter.setFach(Fach.BWL);
		System.out.println(Peter);
		System.out.println("Regelstudienzeit fuer sein Studium: "
				+ Peter.getFach().regelstudienzeit() + " Semester.");
	}

	public enum Jahreszeit {
		FRUEHLING, SOMMER, HERBST, WINTER;
	}

	public enum Noten {
		C, CIS, D, DIS, E, F, FIS, G, GIS, A, AIS, H;

		public boolean liegtAufSchwarzerTaste() {
			switch (this) {
			case CIS:
			case DIS:
			case FIS:
			case GIS:
			case AIS:
				return true;
			default:
				return false;
			}
		}
	}

}

class EsWarEinmal {
	public static void absatz(Object... elemente) {
		for (Object element : elemente)
			System.out.print(String.valueOf(element));
		System.out.println();
	}

	public static void main(String... args) {
		Nom rotkaeppchen = new Nom(Geschlecht.SAECHLICH, "Rotkaeppchen");
		Nom wolf = new Nom(Geschlecht.MAENNLICH, "Wolf");
		Nom oma = new Nom(Geschlecht.WEIBLICH, "Grossmutter");
		absatz("Es war einmal ",
				rotkaeppchen.mitArtikel(Fall.NOMINATIV, false),
				", das wollte einen Ausflug zu ",
				oma.mitArtikel(Fall.DATIV, true), " machen.");
		absatz("Im Wald jedoch begegnete es ",
				wolf.mitArtikel(Fall.DATIV, false),
				", und damit beginnt unsere schaurige Geschichte...");
	}
}

enum Geschlecht {
	MAENNLICH, WEIBLICH, SAECHLICH;
}

enum Fall {
	NOMINATIV, GENITIV, DATIV, AKKUSATIV;

	public String getBestimmterArtikel(Geschlecht geschlecht) {
		String artikel;
		if (geschlecht == Geschlecht.MAENNLICH)
			artikel = "der";
		else if (geschlecht == Geschlecht.WEIBLICH)
			artikel = "die";
		else
			artikel = "das";
		return artikel;
	}

	public String getUnbestimmterArtikel(Geschlecht geschlecht) {
		String artikel;
		if (geschlecht == Geschlecht.MAENNLICH)
			artikel = "der";
		else if (geschlecht == Geschlecht.WEIBLICH)
			artikel = "die";
		else
			artikel = "das";
		return artikel;
	}
}

enum Fach {
	MATHE, INFORMATIK, ARCHITEKTUR, BWL, BIOLOGIE, GESCHICHTE, GERMANISTIK, POLITOLOGIE, PHYSIK;

	public int regelstudienzeit() {
		switch (this) {
		case MATHE:
		case INFORMATIK:
		case ARCHITEKTUR:
			return 11;
		case BWL:
		case BIOLOGIE:
		case GESCHICHTE:
			return 10;
		case GERMANISTIK:
		case POLITOLOGIE:
		case PHYSIK:
			return 13;
		default:
			return -1;

		}
	}
}

class StudentNeu {
	// 11.1
	/**
	 * Diese Konstante repraesentiert das Phantom des Campus
	 */
	public static final StudentNeu PHANTOM;

	// Neue Konstanten: maennlich/weiblich
	public static final boolean MAENNLICH = true;
	public static final boolean WEIBLICH = false;

	/*
	 * ========= VARIABLEN =========
	 */

	/** Zaehlt die Anzahl der erzeugten StudentNeuenobjekte */
	private static int zaehler = 0;

	/** Der Name des StudentNeuen */
	private String name = "DummyStudentNeu";

	/** Die Matrikelnummer des StudentNeuen */
	private int nummer;

	/** Studienfach des StudentNeuen */
	private Fach fach;

	/** Geburtsjahr eines StudentNeuen */
	private int geburtsjahr;

	/** Geschlecht eines StudentNeuen */
	private boolean geschlecht = true; // 'true' = maennlich, 'false' = weiblich

	/*
	 * ========= METHODEN =========
	 */

	/** Gib den Namen des StudentNeuen als String zurueck */
	public String getName() {
		return this.name;
	}

	/** Setze den Namen des StudentNeuen auf einen bestimmten Wert */
	public void setName(String name) {
		this.name = name;
	}

	/** Gib die Matrikelnummer des StudentNeuen als Integer zurueck */
	public int getNummer() {
		return nummer;
	}

	/** Setze die Matrikelnummer des StudentNeuen auf einen bestimmten Wert */
	public void setNummer(int n) {
		int alteNummer = nummer;
		nummer = n;
		if (!validateNummer()) { // neue Nummer ist nicht gueltig
			nummer = alteNummer;
		}
	}

	/** Gib das Studienfach des StudentNeuen als Integer zurueck */
	public Fach getFach() {
		return fach;
	}

	/**
	 * Setze das Studienfach des StudentNeuen auf einen bestimmten Wert
	 */
	public void setFach(Fach fach) {
		this.fach = fach;
	}

	/** Gib das Geburtsjahr des StudentNeuen als Integer zurueck */
	public int getGeburtsjahr() {
		return geburtsjahr;
	}

	/**
	 * Pruefe die Matrikelnummer des StudentNeuen auf ihre Gueltigkeit
	 */
	public boolean validateNummer() {
		return (nummer >= 10000 && nummer <= 99999 && nummer % 2 != 0);
	}

	/** Gib eine textuelle Beschreibung dieses StudentNeuen aus */
	public String toString() {
		String res = name + " (" + nummer + ")\n";

		if (geschlecht)
			res += " (m) ";
		else
			res += " (w) ";

		switch (fach) {
		case MATHE:
			return res + "  ein Mathestudent " + "(oder auch zwei, oder drei).";
		case INFORMATIK:
			return res + "  ein Informatikstudent.";
		case ARCHITEKTUR:
			return res + "  angehender Architekt.";
		case BWL:
			return res + "  ein Wirtschaftswissenschaftler.";
		case BIOLOGIE:
			return res + "  Biologie ist seine Staerke.";
		case GESCHICHTE:
			return res + "   sollte Geschichte nicht mit Geschichten "
					+ "verwechseln.";
		case GERMANISTIK:
			return res + "  wird einmal Germanist gewesen geworden sein.";
		case POLITOLOGIE:
			return res + "  kommt bestimmt einmal in den Bundestag.";
		case PHYSIK:
			return res + "  studiert schon relativ lange Physik.";
		default:
			return res + "  keine Ahnung, was der Mann studiert.";
		}
	}

	/** Gib die Zahl der erzeugten StudentNeuenobjekte zurueck */
	public static int getZaehler() {
		return zaehler;
	}

	/** Erzeugt ein neues StudentNeuenobjekt */
	public static StudentNeu createStudentNeu() {
		return new StudentNeu();
	}

	/*
	 * ============= KONSTRUKTOREN =============
	 */

	/** Argumentloser Konstruktor */
	public StudentNeu() {
		this(1970);
	}

	/** Konstruktor, bei dem sich das Geburtsjahr setzen laesst. */
	public StudentNeu(int geburtsjahr) {
		zaehler++;
		this.geburtsjahr = geburtsjahr;
		this.name = "Namenlos";
	}

	// ** Aufgabe 8.1: Konstruktor, der alle Instanzenvariablen setzen kann
	public StudentNeu(String name, int nummer, Fach fach, int geburtsjahr) {

		this(geburtsjahr); // ruft den Konstruktor 'public StudentNeu(int
							// geburtsjahr)'
		this.name = name;
		this.fach = fach;
		this.nummer = nummer;
	}

	// *** Aufgabe 8.2: Konstruktor mit Beruecksichtigung des Geschlechts
	public StudentNeu(String name, int nummer, Fach fach, int geburtsjahr,
			boolean geschlecht) {

		this(name, nummer, fach, geburtsjahr);
		this.geschlecht = geschlecht;

	}

	public StudentNeu(int geburtsjahr, boolean geschlecht) {

		this(geburtsjahr);
		this.geschlecht = geschlecht;

	}

	/*
	 * ==================== STATISCHE INITIALISIERUNG ====================
	 */

	static {
		// Erzeuge das PHANTOM-Objekt
		PHANTOM = new StudentNeu(1735);
		PHANTOM.name = "Erik le Phant";
		PHANTOM.nummer = -12345;
		// Setze den Zaehler wieder zurueck
		zaehler = 0;
	}
}