package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Igra extends Frame {
	
	private Teren terrain = new Teren(6, 12);
	private Button postavi = new Button("Postavi");
	private Button nasumicno = new Button("Nasumicno");
	private CheckboxGroup grupa = new CheckboxGroup();
	private Checkbox zid = new Checkbox("Zid", false, grupa);
	private Checkbox prolaz = new Checkbox("Prolaz", false, grupa);
	private Checkbox akter = new Checkbox("Akter", false, grupa);
	
	private void populateWindow() {
		this.setLayout(new BorderLayout());
		this.add(terrain, BorderLayout.CENTER);
		Panel desno = new Panel(new GridLayout(3, 1));
		Panel dugmad = new Panel(new FlowLayout());
		dugmad.add(zid);
		dugmad.add(prolaz);
		dugmad.add(akter);
		desno.add(dugmad);
		desno.add(postavi);
		desno.add(nasumicno);
		this.add(desno, BorderLayout.EAST);
	}
	
	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		postavi.addActionListener(e -> {
			if(zid.getState()) {
				terrain.replaceCurrMarked(new Zid(terrain.getCurrMarked(), terrain));
			}
			if(prolaz.getState()) {
				terrain.replaceCurrMarked(new Prolaz(terrain.getCurrMarked(), terrain));
			}
			if(akter.getState()) {
				try {
					terrain.addAkter(new GlavniAkter(terrain, terrain.getCurrMarked()));
				} catch (GreskaOznacenoPolje e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		
		nasumicno.addActionListener(e -> {
			terrain.generateRandom();
		});
	}
	
	public Igra() {
		super("Igra");
		requestFocus();
		//setSize(800, 350);
		populateWindow();
		addListeners();
		setPreferredSize(new Dimension(800, 350));
		setResizable(true);
		pack();
		setVisible(true);
	}
	
	public static void main(String arg[]) {
		new Igra();
	}

}
