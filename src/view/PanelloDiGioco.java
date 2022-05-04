package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
import controllo.*;
import entity.Player;
import oggetto.SuperOggetto;
import tile.CollisionChecker;
import tile.TileManager;

@SuppressWarnings("serial")
public class PanelloDiGioco extends JPanel implements Runnable {

	// Dimensioni generali
	public Dimensioni dim;

	// FPS
	private int fps = 60;

	public Thread gameThread;
	public Player player;
	public TileManager tm;
	public CollisionChecker cChecker;
	public SuperOggetto ogg[];

	public PanelloDiGioco(Dimensioni dimensioni, Comandi comandi) {

		this.dim = dimensioni;
		this.player = new Player(this, comandi);
		this.tm = new TileManager(this,"/maps/mappa.txt");
		this.cChecker = new CollisionChecker(this, comandi);
		this.ogg = new SuperOggetto[10];

		this.setPreferredSize(new Dimension(dim.lunghezzaInGioco, dim.altezzaInGioco));
		this.setBackground(Color.decode("#567F31"));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		
		inizia();
	}

	public void inizia() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer > 1000000000) {
				// System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update() {

		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tm.draw(g2);

		player.draw(g2);

		g2.dispose();
	}

}