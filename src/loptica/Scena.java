package loptica;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

public class Scena extends Canvas {

	private ArrayList<Figura> lista = new ArrayList<Figura>();
	private Igrac igrac = null;

	public Scena() {
	}

	public void dodajFiguru(Figura f) {
		if (f instanceof Igrac) {
			if (igrac != null)
				return;
			igrac = (Igrac) f;
		}
		lista.add(f);
		repaint();
	}

	public Figura dohvatiFiguru(int index) throws GVanOpsega {
		if (index < 0 || index >= lista.size())
			throw new GVanOpsega();
		return lista.get(index);
	}

	public boolean ukloniFiguru(Figura f) {
		return lista.remove(f);
	}

	public void pokreniScenu() {
		for (Figura f : lista) {
			if (f instanceof AktivnaFigura)
				((AktivnaFigura) f).pokreni();
		}
	}

	public void zaustaviScenu() {
		for (Figura f : lista) {
			if (f instanceof AktivnaFigura) {
				((AktivnaFigura) f).zaustavi();
			}
		}
	}

	public void paint(Graphics g) {
		for (Figura f : lista) {
			f.paint(g);
		}
	}

	public Igrac dohvatiIgraca() {
		return this.igrac;
	}

}
