package usisivac;

import java.awt.Color;
import java.awt.Graphics;

public class Usisivac extends Figura {

	private static int poluprecnik = 15;

	public Usisivac(int x, int y) {
		super(x, y);
	}

	@Override
	public int getPoluprecnik() {
		return poluprecnik;
	}

	@Override
	public void paint(Graphics g) {
		Color prevC = g.getColor();
		g.setColor(Color.RED);
		int x1, x2, x3, y1, y2, y3;
		x1 = (int) (x - poluprecnik * Math.sin(Math.PI / 3));
		y1 = (int) (y + poluprecnik * Math.sin(Math.PI / 6));
		x2 = (int) (x + poluprecnik * Math.sin(Math.PI / 3));
		y2 = y1;
		x3 = x;
		y3 = (int) (y - poluprecnik);
		int Xs[] = { x1, x2, x3 };
		int Ys[] = { y1, y2, y3 };
		g.drawPolygon(Xs, Ys, 3);
		g.fillPolygon(Xs, Ys, 3);
		g.setColor(prevC);
	}

	public int getPomeraj() {
		return poluprecnik / 2;
	}

}
