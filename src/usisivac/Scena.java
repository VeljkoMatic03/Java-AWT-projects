package usisivac;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Scena extends Canvas implements Runnable {

	private Simulacija simulacija;
	private Usisivac usisivac;
	Thread nit = new Thread(this);
	private boolean isPaused = true;
	private SkupFigura skup = new SkupFigura();
	private Kamencic tekuciKamencic = null;

	public Scena(Simulacija s) {
		simulacija = s;
		usisivac = new Usisivac(simulacija.getWidth() / 2, simulacija.getHeight() / 2);
		requestFocus();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					getParent().requestFocus();
					synchronized (this) {
						skup.dodaj(new Kamencic(e.getX(), e.getY()));
					}
					Scena.this.proveraKamen();
				} catch (GreskaDodavanje e1) {
				}
			}
		});

		nit.start();
	}

	private synchronized void proveraKamen() {
		if (skup.brojFigura() == 0) {
			isPaused = true;
			return;
		}
		int rastojanje;
		skup.setTekuca();
		rastojanje = skup.getTekuca().getRastojanje(usisivac);
		tekuciKamencic = (Kamencic) skup.getTekuca();
		do {
			try {
				skup.sledeca();
			} catch (GreskaIndeksVanOpsega e) {
			}
			if (skup.getTekuca().getRastojanje(usisivac) < rastojanje) {
				rastojanje = skup.getTekuca().getRastojanje(usisivac);
				tekuciKamencic = (Kamencic) skup.getTekuca();
			}
		} while (skup.postojiSledeca());
		proceed();
	}

	@Override
	public void run() {

		try {
			while (!nit.isInterrupted()) {
				synchronized (this) {
					while (isPaused)
						wait();
				}
				pomeriUsisivac();
				repaint();
				Thread.sleep(50);
			}

		} catch (InterruptedException e) {
		}

	}

	private void pomeriUsisivac() {

		if (tekuciKamencic == null) {
			isPaused = true;
			return;
		}

		if (usisivac.obuhvata(tekuciKamencic)) {
			skup.izbaci(tekuciKamencic);
			proveraKamen();
		}

		else if (Math.abs(tekuciKamencic.x - usisivac.x) >= usisivac.getPomeraj()) {
			usisivac.x += (tekuciKamencic.x > usisivac.x ? usisivac.getPomeraj() : -usisivac.getPomeraj());
		}

		else if (Math.abs(tekuciKamencic.y - usisivac.y) >= usisivac.getPomeraj()) {
			usisivac.y += (tekuciKamencic.y > usisivac.y ? usisivac.getPomeraj() : -usisivac.getPomeraj());
		}

	}

	public void paint(Graphics g) {
		try {
			if (skup.brojFigura() == 0) {
				usisivac.paint(g);
				return;
			}
			for (skup.setTekuca();; skup.sledeca()) {
				skup.getTekuca().paint(g);
				if (!skup.postojiSledeca())
					break;
			}

		} catch (GreskaIndeksVanOpsega e) {
		}
		usisivac.paint(g);
	}

	synchronized void proceed() {
		isPaused = false;
		notifyAll();
	}

	synchronized void togglePause() {
		if (isPaused == true && skup.brojFigura() != 0) {
			proceed();
		} else if (isPaused == false) {
			isPaused = true;
		}
	}

}
