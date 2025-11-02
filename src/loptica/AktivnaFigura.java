package loptica;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AktivnaFigura extends Figura implements Runnable {

	protected int perioda;
	protected Thread nit = new Thread(this);

	public AktivnaFigura(Scena scena, int x, int y, Color color, int ms) {
		super(scena, x, y, color);
		this.perioda = ms;
	}

	public void pokreni() {
		nit.start();
	}

	public void zaustavi() {
		nit.interrupt();
	}

	public void unisti() {
		zaustavi();
		super.unisti();
	}

}
