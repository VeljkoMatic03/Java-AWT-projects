package usisivac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulacija extends Frame {

	private Scena scena;

	public Simulacija() {

		super("Simulacija");

		setSize(500, 500);

		scena = new Scena(this);

		setLayout(new BorderLayout());

		setResizable(false);
		populateWindow();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.nit.interrupt();
				dispose();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					scena.togglePause();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					scena.nit.interrupt();
					dispose();
				}
			}
		});

		setVisible(true);
	}

	private void populateWindow() {
		scena.requestFocus();
		scena.setBackground(Color.GRAY);
		this.add(scena, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Simulacija();
	}

}
