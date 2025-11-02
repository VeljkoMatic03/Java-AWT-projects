package paket;

import java.awt.Color;

public class Zid extends Polje {

	public Zid(Pozicija p, Teren t) {
		super(Color.DARK_GRAY, p, t);
	}

	@Override
	public boolean akterCanFit() {
		return false;
	}

}
