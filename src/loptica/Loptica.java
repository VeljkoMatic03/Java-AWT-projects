package loptica;

import java.awt.Color;
import java.awt.Graphics;

public class Loptica extends AktivnaFigura implements Runnable {

	private double pocetnaBrzina;
	private double dx;
	private double dy;
	private int precnik;
	private static char oznaka = 'L';
	private Thread nit = new Thread(this);
	private int counter = 0;

	public Loptica(Scena scena, int x, int y, int ms, int precnik) {
		super(scena, x, y, Color.GREEN, ms);
		this.precnik = precnik;
		pocetnaBrzina = 5 + Math.random();
		dx = Math.random() * 2 - 1;
		dy = Math.random() - 1;
		nit.start();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillOval(x, y, precnik, precnik);
	}

	@Override
	public char dohvatiOznaku() {
		return oznaka;
	}

	private synchronized void provera() {
		if (x <= precnik) {
			// udarila u levu ivicu
			dx = -dx;
			return;
		} else if (x >= scena.getWidth() - precnik) {
			// udarila u desnu ivicu
			dx = -dx;
			return;
		} else if (y <= precnik) {
			dy = -dy;
			return;
		} else if (y >= scena.getHeight() - precnik) {
			nit.interrupt();
			this.unisti();
			return;
		} else if (presekIgrac()) {
			dx = -dx;
			dy = -dy;
			return;
		}
		presekCigla();
	}

	private synchronized boolean presekIgrac() {
		int igracX, igracY, igracW, igracH;
		igracX = scena.dohvatiIgraca().x;
		igracY = scena.dohvatiIgraca().y;
		igracW = scena.dohvatiIgraca().getWidth();
		igracH = scena.dohvatiIgraca().getHeight();
		return ((Math.abs(this.y - igracY) < (this.precnik + igracH / 2))
				&& (Math.abs(this.x - igracX) < (this.precnik + igracW / 2)));
	}

	private synchronized void presekCigla() {
		int i = 0;
		try {
			while (true) {
				Figura f = scena.dohvatiFiguru(i);
				i++;
				if (!(f instanceof Cigla)) {
					continue;
				}
				Cigla c = (Cigla) f;
				if (c.jePogodjena())
					continue;
				int ciglaX, ciglaY, ciglaW, ciglaH;
				ciglaX = c.x;
				ciglaY = c.y;
				ciglaW = c.getWidth();
				ciglaH = c.getHeight();
				if ((Math.abs(this.y - ciglaY) < this.precnik + ciglaH / 2)
						&& (Math.abs(this.x - ciglaX) < this.precnik + ciglaW / 2)) {
					c.pogodi();
					dy = -dy;
					return;
				}
			}
		} catch (GVanOpsega e) {
		}
	}

	public void run() {
		try {
			while (!nit.isInterrupted()) {
				Thread.sleep(this.perioda);
				provera();
				counter++;
				if (counter == 100) {
					counter = 0;
					pocetnaBrzina *= 1.1;
				}
				pomeri((int) (dx * pocetnaBrzina), (int) (dy * pocetnaBrzina));
				scena.repaint();
			}
		} catch (InterruptedException e) {
		}
	}

	public void zaustavi() {
		nit.interrupt();
	}

}
