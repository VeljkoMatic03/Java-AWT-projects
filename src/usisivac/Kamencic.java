package usisivac;

import java.awt.Color;
import java.awt.Graphics;

public class Kamencic extends Figura {
	
	private static int poluprecnik = 5;

	public Kamencic(int x, int y) {
		super(x, y);
	}

	@Override
	public int getPoluprecnik() {
		return poluprecnik;
	}

	@Override
	public void paint(Graphics g) {
		Color prevC = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x-poluprecnik, y-poluprecnik, 2*poluprecnik, 2*poluprecnik);
		g.setColor(prevC);
	}
	
}
