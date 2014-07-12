package grundkurs_java;

public class Chap11 {
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

	public static void main(String[] args) {
		// System.out.println(Jahreszeit.WINTER.ordinal());
		// System.out.println(Jahreszeit.WINTER.equals(Jahreszeit.FRUEHLING));
		// for (Jahreszeit jz : Jahreszeit.values())
		// System.out.println(jz + " hat den Wert " + jz.ordinal());
		// Noten g = Noten.G;
		// System.out.println(g.liegtAufSchwarzerTaste());
		// System.out.println(Noten.AIS.liegtAufSchwarzerTaste());
		// StudentNeu Peter = new StudentNeu();
		// Peter.setName("Peter Honig");
		// Peter.setNummer(12345);
		// Peter.setFach(Fach.BWL);
		// System.out.println(Peter);
		// System.out.println("Regelstudienzeit fuer sein Studium: "
		// + Peter.getFach().regelstudienzeit() + " Semester.");
		// System.out.println(('a' == 'a') && (2 > 3)); // false
		// System.out.println(("ab" + "cd") == "abcd"); // true
		// System.out.println((10 / 4 == 1) || ('a' == 'b')); // false
		// System.out.println((13 / 3 - 3) * 123456789123L); // 123456789123
		// System.out.println('Q' != 'q'); // true
		// EsWarEinmal.main(args);
		Socke s1 = new Socke();
		Socke s2 = new Socke();
		Ohrring o1 = new Ohrring();
		Ohrring o2 = new Ohrring();

		// without generics (not type safe)
		Paar sockenPaar = new Paar(s1, s2);
		System.out.println("1. Paar: " + sockenPaar);
		Paar ohrringPaar = new Paar(o1, o2);
		System.out.println("2. Paar: " + ohrringPaar);
		Socke s = (Socke) sockenPaar.getL();
		System.out.println("Links in Paar 1: " + s);

		// using generics
		GenPaar<Socke> genSockenPaar = new GenPaar<Socke>(s1, s2);
		System.out.println("1. Paar: " + genSockenPaar);
		GenPaar<Ohrring> genOhrringPaar = new GenPaar<Ohrring>(o1, o2);
		System.out.println("2. Paar: " + genOhrringPaar);
		Socke gS = genSockenPaar.getL();
		System.out.println(gS);

		TollesPaarTestWild2.main(args);
		GenPaarTestWild1.main(args);
		GenPaarTestWild2.main(args);
		GenPaarTest3.main(args);
		GenPaarTest5.main(args);
		// type mismatch
		// TierKaefig<Tier> kaefig = new TierKaefig<Katze>();
		// TierKaefig<Hund> kaefig = new TierKaefig<Tier>();
		// TierKaefig<?> kaefig = new TierKaefig<Katze>();
		// kaefig.setInsasse(new Katze());
		// not type safe
		// TiersKaefig kaefig = new TiersKaefig();
		// kaefig.setInsasse(new Hunds());
		// Tierleben.main(args);
		RateMal.main(args);
		// System.out.println(Boolean.TRUE.compareTo(Boolean.FALSE) > 0);
		// System.out.println("welt".compareTo("schoene")); // 4
		// System.out.println("welt".compareTo("du")); // 19
		// System.out.println("welt".compareTo("hallo")); // 15
		// System.out.println("schoene".compareTo("du")); // 15
		// System.out.println("schoene".compareTo("hallo")); // 11
		// System.out.println("du".compareTo("hallo")); // -4
	}
}

// 11.6
class RateMal {
	public static void ausgabe(Object... eingabe) {
		System.out.print("Ausgabe: ");
		for (Object o : eingabe)
			System.out.print(o + " ");
		System.out.println();
	}

	public static <T extends Comparable> T[] tueWas(T... eingabe) {
		eingabe = eingabe.clone();
		for (int i = eingabe.length - 1; i > 0; i--)
			for (int j = 0; j < i; j++)
				if (eingabe[j].compareTo(eingabe[j + 1]) > 0) {
					T tmp = eingabe[j];
					eingabe[j] = eingabe[j + 1];
					eingabe[j + 1] = tmp;
				}
		return eingabe;
	}

	public static void main(String[] args) {
		ausgabe(tueWas(Boolean.TRUE, Boolean.FALSE)); // Ausgabe: false true
		ausgabe(tueWas("welt", "schoene", "du", "hallo")); // Ausgabe: du hallo
															// schoene
															// welt
	}
}

// 11.5
interface Tier {
}

interface Haustier extends Tier {
}

interface Wildtier extends Tier {
}

class Katz implements Tier {
	public String toString() {
		return getClass().getName();
	}
}

class Hauskatze extends Katz implements Haustier {
}

class Wildkatze extends Katz implements Wildtier {
}

class Tierleben {

	// public static void gibAus(Object tier) {
	// System.out.println("Objekt: " + tier);
	// }

	// public static void gibAus(Katz tier) {
	// System.out.println("Katz: " + tier);
	// }

	public static <T> void gibAus(T tier) {
		System.out.println("Unbekannt: " + tier);
	}

	public static <T extends Tier> void gibAus(T tier) {
		System.out.println("Tier: " + tier);
	}

	public static <T extends Haustier> void gibAus(T tier) {
		System.out.println("Haustier: " + tier);
	}

	public static void main(String... args) {
		gibAus("Amoebe");
		gibAus(new Katz()); // Unbekannt: grundkurs_java.Katz
		gibAus(new Hauskatze()); // Haustier:
		gibAus(new Wildkatze()); // Tier:
	}
}

// 11.4
class TiersKaefig<E> {
	private E insasse;

	public void setInsasse(E x) {
		insasse = x;
	}

	public E getInsasse() {
		return insasse;
	}
}

class Tiers {
}

class Katzes extends Tiers {
}

class Hunds extends Tiers {
}

// \\

class GenPaarTest5 {
	public static <T, S extends T> GenPaar<T> linksPaar(GenPaar<T> x,
			GenPaar<S> y) {
		return new GenPaar<T>(x.getL(), y.getL());
	}

	public static void main(String[] args) {
		Hose h1 = new Hose();
		Hose h2 = new Hose();
		Jeans j1 = new Jeans();
		Jeans j2 = new Jeans();
		GenPaar<Hose> p1 = new GenPaar<Hose>(h1, h2);
		GenPaar<Jeans> p2 = new GenPaar<Jeans>(j1, j2);
		System.out.println(linksPaar(p1, p2));
	}
}

class GenPaarTest3 {
	// without wildcard
	public static <T> boolean linksGleichRechts(GenPaar<T> x) {
		return x.getL().equals(x.getR());
	}

	// with wildcard
	public static boolean linksGleichRechts2(GenPaar<?> x) {
		return x.getL().equals(x.getR());
	}

	public static <T> T links(GenPaar<T> x) {
		return x.getL();
	}

	public static <T> T rechts(GenPaar<T> x) {
		return x.getR();
	}

	public static void main(String[] args) {
		Hose h1 = new Hose();
		Jeans j1 = new Jeans();
		GenPaar<Hose> p1 = new GenPaar<Hose>(h1, j1);
		System.out.println(linksGleichRechts(p1));
		System.out.println(links(p1));
		System.out.println(rechts(p1));
	}
}

class Jeans extends Hose {
	public String toString() {
		return "Jeans";
	}
}

class Chinos extends Hose {
	public String toString() {
		return "Chino";
	}
}

class GenPaarTestWild2 {
	// lower bound wildcard
	public static void genPaarAusgeben2(GenPaar<? super Hose> gp) {
		System.out.println(gp);
	}

	public static void main(String[] args) {
		Kleidung k1 = new Kleidung();
		Kleidung k2 = new Kleidung();
		GenPaar<Kleidung> p1 = new GenPaar<Kleidung>(k1, k2);
		genPaarAusgeben2(p1);
	}
}

class GenPaarTestWild1 {
	// upper bound wildcard
	public static void genPaarAusgeben1(GenPaar<? extends Hose> gp) {
		System.out.println(gp);
	}

	public static void main(String[] args) {
		Jeans j1 = new Jeans();
		Jeans j2 = new Jeans();
		GenPaar<Jeans> p1 = new GenPaar<Jeans>(j1, j2);
		genPaarAusgeben1(p1);
		Chinos c1 = new Chinos();
		Chinos c2 = new Chinos();
		GenPaar<Chinos> p2 = new GenPaar<Chinos>(c1, c2);
		genPaarAusgeben1(p2);
	}
}

class TollesPaarTestWild2 {
	public static void paarAusgeben2(TollesPaar<?> tp) {
		System.out.println(tp);
	}

	public static void main(String[] args) {
		Hose ho1 = new Hose();
		Hose ho2 = new Hose();
		TollesPaar<Hose> p1 = new TollesPaar<Hose>(ho1, ho2);
		paarAusgeben2(p1);
	}
}

class Kleidung {
}

class Hemd extends Kleidung {
	public String toString() {
		return "Hemd";
	}
}

class Hose extends Kleidung {
	public String toString() {
		return "Hose";
	}
}

class TollesPaar<T extends Kleidung> {
	private T l, r;

	public TollesPaar(T l, T r) {
		this.l = l;
		this.r = r;
	}

	public T getL() {
		return l;
	}

	public T getR() {
		return r;
	}

	public String toString() {
		return "(l,r) = (" + l + "," + r + ")";
	}
}

class Ohrring {
	public String toString() {
		return "Ohrring";
	}
}

class Socke {
	public String toString() {
		return "Socke";
	}
}

// generics (type safe, since Java 5)
class GenPaar<T> {
	private T l, r;

	public GenPaar(T l, T r) {
		this.l = l;
		this.r = r;
	}

	public T getL() {
		return l;
	}

	public T getR() {
		return r;
	}

	public String toString() {
		return "(l,r) = (" + l + "," + r + ")";
	}
}

// generics (without type safety)
class Paar {
	private Object l, r;

	public Paar(Object l, Object r) {
		this.l = l;
		this.r = r;
	}

	public Object getL() {
		return l;
	}

	public Object getR() {
		return r;
	}

	public String toString() {
		return "(l,r) = (" + l + "," + r + ")";
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

class Nom {
	public String name;
	public Geschlecht geschlecht;

	public Nom(Geschlecht geschlecht, String name) {
		this.name = name;
		this.geschlecht = geschlecht;
	}

	public String mitArtikel(Fall fall, boolean bestimmt) {
		final String artikel = (bestimmt ? fall
				.getBestimmterArtikel(geschlecht) : fall
				.getUnbestimmterArtikel(geschlecht));
		return artikel + " " + name;
	}
}

enum Geschlecht {
	MAENNLICH, WEIBLICH, SAECHLICH;
}

enum Fall {
	NOMINATIV, GENITIV, DATIV, AKKUSATIV;

	private final static String[] ARTIKEL = { "der", "die", "das", "des",
			"der", "des", "dem", "der", "dem", "den", "die", "das", "ein",
			"eine", "ein", "eines", "einer", "eines", "einem", "einer",
			"einem", "einen", "eine", "ein" };

	public String getBestimmterArtikel(Geschlecht geschlecht) {
		return ARTIKEL[geschlecht.ordinal() + this.ordinal() * 3];
	}

	public String getUnbestimmterArtikel(Geschlecht geschlecht) {
		return ARTIKEL[12 + geschlecht.ordinal() + this.ordinal() * 3];
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