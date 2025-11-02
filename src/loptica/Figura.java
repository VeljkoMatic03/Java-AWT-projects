package loptica;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {

	protected Scena scena;
	protected int x, y;
	protected Color color;

	public Figura(Scena scena, int x, int y, Color color) {
		this.scena = scena;
		this.x = x;
		this.y = y;
		this.color = color;
		scena.dodajFiguru(this);
	}

	public void unisti() {
		scena.ukloniFiguru(this);
	}

	public abstract void paint(Graphics g);

	public abstract char dohvatiOznaku();

	public void pomeri(int dx, int dy) {
		x += dx;
		y += dy;
	}
}
