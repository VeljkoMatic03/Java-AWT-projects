package paket;

import java.awt.Graphics;

public abstract class Akter {
	
	private Teren terrain;
	private Pozicija position;
	
	public Akter(Teren t, Pozicija p) {
		terrain = t;
		position = p;
	}
	
	public Pozicija getPosition() {
		return position;
	}

	public abstract void paint(Graphics g, Polje p);

}
