package usisivac;

import java.util.ArrayList;

public class SkupFigura {

	private ArrayList<Figura> lista;
	private Figura tekuca;

	public SkupFigura() {
		lista = new ArrayList<Figura>();
	}

	public void dodaj(Figura f) throws GreskaDodavanje {
		if (lista.contains(f))
			throw new GreskaDodavanje();
		lista.add(f);
	}

	public void setTekuca() {
		if (lista.size() == 0)
			tekuca = null;
		else
			tekuca = lista.get(0);
	}

	public void sledeca() throws GreskaIndeksVanOpsega {
		if (!postojiSledeca())
			throw new GreskaIndeksVanOpsega();
		if (tekuca == null)
			return;
		int index = lista.indexOf(tekuca);
		tekuca = lista.get(index + 1);
	}

	public Figura getTekuca() {
		return tekuca;
	}

	public boolean postojiSledeca() {
		if (tekuca == null)
			return false;
		int index = lista.indexOf(tekuca);
		return !(index + 1 == lista.size());
	}

	public void isprazni() {
		lista.clear();
	}

	public boolean nalaziSe(Figura f) {
		return lista.contains(f);
	}

	public void izbaci(Figura f) {
		lista.remove(f);
	}

	public int brojFigura() {
		return lista.size();
	}

}
