package loptica;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura {

	private int width, height;
	private static char oznaka = 'I';

	public Igrac(Scena scena, int x, int y, int width, int height) {
		super(scena, x, y - 50, Color.BLUE);
		this.width = width;
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x - width / 2, this.y - height / 2, width, height);
	}

	@Override
	public char dohvatiOznaku() {
		return oznaka;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void ispaliLopticu() {
		new Loptica(this.scena, this.x, this.y - height, 10, height / 2);
	}

	public void pomeri(int dx, int dy) {
		int levoX = this.x - width / 2;
		int desnoX = this.x + width / 2;
		if (levoX + dx <= 0)
			return;
		if (desnoX + dx >= scena.getWidth())
			return;
		x += dx;
		y += dy;
		scena.repaint();
	}

}
