package paket;

import java.awt.Color;
import java.awt.Graphics;

public class GlavniAkter extends Akter {

	public GlavniAkter(Teren t, Pozicija p) {
		super(t, p);
	}

	@Override
	public void paint(Graphics g, Polje p) {
		g.setColor(Color.YELLOW);
		g.fillOval(0, 0, p.getWidth(), p.getHeight());
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, p.getWidth(), p.getHeight());
	}

}
