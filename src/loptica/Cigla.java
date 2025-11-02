package loptica;

import java.awt.Color;
import java.awt.Graphics;

public class Cigla extends AktivnaFigura implements Runnable {

	private boolean pogodjena = false;
	private static char oznaka = 'C';
	private int width, height;

	public Cigla(Scena scena, int x, int y, int ms, int width, int height) {
		super(scena, x, y, Color.RED, ms);
		this.width = width;
		this.height = height;
	}

	public boolean jePogodjena() {
		return pogodjena;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		int nx = x - width / 2;
		int ny = y - height / 2;
		g.fillRect(nx, ny, width, height);
	}

	@Override
	public char dohvatiOznaku() {
		return this.oznaka;
	}

	private void promeniBoju() {
		this.color = Color.GRAY;
	}

	public void run() {
		try {
			while (!nit.isInterrupted()) {
				synchronized (this) {
					while (!pogodjena)
						wait();
				}
				Thread.sleep(this.perioda);
				pomeri(0, 5);
				scena.repaint();
			}
		} catch (InterruptedException e) {
		}
	}

	public synchronized void pogodi() {
		promeniBoju();
		pogodjena = true;
		scena.repaint();
		notifyAll();
	}

	public void pomeri(int dx, int dy) {
		super.pomeri(dx, dy);
		if (this.y > scena.getHeight()) {
			nit.interrupt();
			scena.ukloniFiguru(this);
		}
	}
}
