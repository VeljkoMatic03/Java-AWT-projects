package loptica;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IgraLoptica extends Frame {
	private Scena scena = new Scena();
	private Panel centar = new Panel(new BorderLayout());

	private void populateWindow() {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				new Cigla(scena, j * this.getWidth() / 5 + this.getWidth() / 10, i * 30 + 15 + i, 100,
						this.getWidth() / 5 - 1, 30);
			}
		}

		new Igrac(scena, this.getWidth() / 2, this.getHeight() - 20, 70, 20);
		scena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scena.dohvatiIgraca().ispaliLopticu();
			}
		});

		scena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					scena.dohvatiIgraca().pomeri(-10, 0);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					scena.dohvatiIgraca().pomeri(10, 0);
				}

			}
		});

		centar.add(scena, BorderLayout.CENTER);
	}

	public IgraLoptica() {
		super("Loptica");

		setSize(500, 500);

		populateWindow();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.zaustaviScenu();
				dispose();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					scena.dohvatiIgraca().pomeri(-10, 0);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					scena.dohvatiIgraca().pomeri(10, 0);
				}

			}
		});

		scena.pokreniScenu();

		add(centar);

		setResizable(false);

		setVisible(true);

	}

	public static void main(String[] args) {
		new IgraLoptica();
	}
}
