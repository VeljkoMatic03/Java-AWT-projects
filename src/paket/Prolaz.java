package paket;

import java.awt.Color;

public class Prolaz extends Polje {

	public Prolaz(Pozicija p, Teren t) {
		super(Color.LIGHT_GRAY, p, t);
	}

	@Override
	public boolean akterCanFit() {
		return true;
	}

}
