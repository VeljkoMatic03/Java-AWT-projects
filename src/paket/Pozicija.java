package paket;

public class Pozicija {

	private int row, col;

	public static enum Smer {
		GORE, DOLE, LEVO, DESNO
	}

	public Pozicija(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Pozicija dohvatiPored(Smer s) {
		Pozicija p = new Pozicija(row, col);
		switch (s) {
		case GORE:
			p = new Pozicija(row - 1, col);
		case DOLE:
			p = new Pozicija(row + 1, col);
		case LEVO:
			p = new Pozicija(row, col - 1);
		case DESNO:
			p = new Pozicija(row, col - 1);
		}
		return p;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Pozicija))
			return false;
		Pozicija p = (Pozicija) o;
		return (row == p.getRow() && col == p.getCol());
	}

}
