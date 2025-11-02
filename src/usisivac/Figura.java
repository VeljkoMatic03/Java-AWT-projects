package usisivac;

import java.awt.Graphics;

public abstract class Figura {

	protected int x, y;

	public Figura(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract int getPoluprecnik();

	public abstract void paint(Graphics g);

	public int getRastojanje(Figura f) {
		return (int) (Math.sqrt(Math.pow((this.x - f.x), 2) + Math.pow((this.y - f.y), 2)));
	}

	public boolean preklapaSe (Figura f) {
		return getRastojanje(f) < (getPoluprecnik() + f.getPoluprecnik());
	}
	
	public boolean obuhvata (Figura f) {
		return getRastojanje(f) <= (getPoluprecnik() - f.getPoluprecnik());
	}

}
