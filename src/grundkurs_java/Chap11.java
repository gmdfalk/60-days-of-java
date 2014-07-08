package grundkurs_java;

public class Chap11 {

	public static void main(String[] args) {
		System.out.println(Jahreszeit.WINTER.ordinal());
		System.out.println(Jahreszeit.WINTER.equals(Jahreszeit.FRUEHLING));
		for (Jahreszeit jz : Jahreszeit.values())
			System.out.println(jz + " hat den Wert " + jz.ordinal());
		Noten g = Noten.G;
		System.out.println(g.liegtAufSchwarzerTaste());
		System.out.println(Noten.AIS.liegtAufSchwarzerTaste());
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
