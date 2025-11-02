package paket;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

public class Teren extends Panel {
	
	private int row, col;
	private Polje matrix[][];
	private Pozicija currMarked = null;
	private List<Akter> list = new ArrayList<Akter>();
	
	public Teren(int r, int c) {
		super(new GridLayout(r, c, 1, 1));
		row = r;
		col = c;
		matrix = new Polje[r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				matrix[i][j] = new Prolaz(new Pozicija(i, j), this);
				this.add(matrix[i][j]);
			}
		}
	}
	
	
	
	public void markThisField(Polje p) {
		if(currMarked == null) {
			currMarked = p.getPosition();
			p.markField();
			p.repaint();
			return;
		}
		else {
			if(currMarked == p.getPosition()) {
				currMarked = null;
				p.unmarkField();
				p.repaint();
				return;
			}
			Polje currMarkedField = matrix[currMarked.getRow()][currMarked.getCol()];
			currMarkedField.unmarkField();
			currMarkedField.repaint();
			currMarked = p.getPosition();
			p.markField();
			p.repaint();
			return;
		}
	}
	
	public void replaceCurrMarked(Polje p) {
		if(currMarked == null) return;
		if(!p.akterCanFit() && getAkterOnPos(p.getPosition()) != null) return;
		matrix[currMarked.getRow()][currMarked.getCol()] = p;
		//p.repaint();
		this.removeAll();
		this.fillThis();
		revalidate();
		repaint();
	}
	
	public Polje getPoljeOnPos(Pozicija p) {
		return matrix[p.getRow()][p.getCol()];
	}
	
	public Akter getAkterOnPos(Pozicija p) {
		for(Akter a : list) {
			if(a.getPosition().equals(p)) return a;
		}
		return null;
	}
	
	public void generateRandom() {
		this.removeAll();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				double random = Math.random();
				if(random < 0.3) matrix[i][j] = new Zid(new Pozicija(i, j), this);
				else matrix[i][j] = new Prolaz(new Pozicija(i, j), this);
				this.add(matrix[i][j]);
			}
		}
		list.clear();
		this.revalidate();
		repaint();
	}
	
	public void addAkter(Akter a) throws GreskaOznacenoPolje{
		if(!matrix[currMarked.getRow()][currMarked.getCol()].akterCanFit()) throw new GreskaOznacenoPolje();
		list.add(new GlavniAkter(this, currMarked));
		matrix[currMarked.getRow()][currMarked.getCol()].repaint();
	}
	
	public Pozicija getCurrMarked() {
		return currMarked;
	}
	
	protected void fillThis() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				this.add(matrix[i][j]);
			}
		}
	}

}
