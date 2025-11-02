package paket;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Polje extends Canvas {

	protected Color color;
	protected Pozicija position;
	protected Teren terrain;
	protected boolean marked;
	protected int width = 50;
	protected int height = 50;

	public Polje(Color c, Pozicija p, Teren t) {
		color = c;
		position = p;
		terrain = t;
		marked = false;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				terrain.markThisField(Polje.this);
			}
		});
	}

	public Pozicija getPosition() {
		return position;
	}

	public abstract boolean akterCanFit();

	public void markField() {
		marked = true;
	}

	public void unmarkField() {
		marked = false;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		if (marked) {
			g.setColor(Color.RED);
			g.drawRect(0, 0, width, height);
		} else {
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, width, height);
		}
		Akter a = terrain.getAkterOnPos(position);
		if (a != null) {
			a.paint(g, this);
		}
	}

}
