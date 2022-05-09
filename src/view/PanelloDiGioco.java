package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.*;

import control.*;
import model.*;

@SuppressWarnings("serial")
public class PanelloDiGioco extends JPanel implements Runnable {

	// Dimensioni generali
	public Dimensioni dim;

	// FPS
	private int fps = 60;

	// Timer
	public int secCount = 0;
	public int minCount = 0;

	public Thread gameThread;
	public Player player;
	public TileManager tm;
	public CollisionChecker cChecker;
	public SuperOggetto ogg[];
	public Comandi comandi;
	public Menu menu;
	Sound suoni = new Sound();

	public PanelloDiGioco(Dimensioni dimensioni, Comandi comandi) {

		this.dim = dimensioni;
		this.player = new Player(this, comandi);
		this.ogg = new SuperOggetto[10];
		// Mappa randomica
		try {
			int r = (int) Math.floor(Math.random() * 10 + 1);
			this.tm = new TileManager(this, "/maps/mp" + r + ".png");
			System.out.println("Generata mappa numero: " + r);
		} catch (Exception e) {
			this.tm = new TileManager(this, "/maps/defaultMp.png");
		}
		this.cChecker = new CollisionChecker(this, comandi);
		this.comandi = comandi;

		this.setPreferredSize(new Dimension(dim.lunghezzaSchermo, dim.altezzaSchermo));
		this.setBackground(Color.decode("#567F31"));
		this.setDoubleBuffered(true);
		this.setFocusable(true);

		this.setObject();

		inizia();
	}

	public void inizia() {
		gameThread = new Thread(this);
		gameThread.start();
		playmusica(0);
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			if (!player.vittoria && !comandi.giocoInPausa && !(minCount > 98 && secCount > 58))
				timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				if (player.vittoria)
					animazioneVittoria();
			}
			if (timer > 1000000000) {
				secCount++;
				if (secCount % 60 == 0) {
					minCount++;
					secCount = 0;
				}
				timer = 0;
			}
		}
	}

	public void setObject() {
		for (int i = 0; i < tm.contOgg; i++)
			this.ogg[i] = tm.pg.ogg[i];

	}

	public void update() {

		player.update();
	}

	public void playmusica(int i) {
		suoni.setFile(i);
		suoni.play();
		suoni.loop();
	}

	public void stopmusica() {
		suoni.stop();
	}

	public void effettosonoro(int i) {
		suoni.setFile(i);
		suoni.play();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tm.draw(g2);

		for (int i = 0; i < ogg.length; i++) {
			if (ogg[i] != null) {
				ogg[i].draw(g2, this);
			}
		}

		player.draw(g2);

		testoTimer(g2);
		if (comandi.giocoInPausa == true)
			testoPausa(g2);
		if (player.vittoria == true) {
			testoVittoria(g2);
		}
		g2.dispose();
	}

	public void testoTimer(Graphics2D g) {
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(new Color(0, 0, 0, 150));
		if (minCount == 0) {
			g.fillRect(20, 5, 40, 25);
			g.setColor(Color.WHITE);
			g.drawString(secCount + "s", 25, 25);
		} else {
			g.fillRect(20, 5, 80, 25);
			g.setColor(Color.WHITE);
			g.drawString(minCount + "m " + secCount + "s", 25, 25);
		}
	}

	public void testoPausa(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, 0, dim.lunghezzaSchermo, dim.altezzaSchermo);
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 100));
		g.drawString("PAUSA", dim.lunghezzaSchermo / 2 - 175, 100);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString("Premi 'e' per uscire", 275, 200);
	}

	public void testoVittoria(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, 0, dim.lunghezzaSchermo, dim.altezzaSchermo);
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 100));
		g.drawString("HAI VINTO", dim.lunghezzaSchermo / 2 - 250, 100);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		if (minCount > 0) {
			if (minCount > 1) {
				if (secCount > 1)
					g.drawString("Hai finito in " + minCount + " minuti e " + secCount + " secondi", 200, 150);
				else
					g.drawString("Hai finito in " + minCount + " minuti e " + secCount + " secondo", 200, 150);
			} else {
				if (secCount > 1)
					g.drawString("Hai finito in " + minCount + " minuto e " + secCount + " secondi", 200, 150);
				else
					g.drawString("Hai finito in " + minCount + " minuto e " + secCount + " secondo", 200, 150);
			}
		} else
			g.drawString("Hai finito in " + secCount + " secondi", 250, 150);
		g.drawString("Premi 'e' per uscire", 275, 200);
	}

	public void animazioneVittoria() {
		comandi.velocita = 2;
		if (player.worldX < (dim.grandezzaInGioco * 23) || player.worldX > (dim.grandezzaInGioco * 23) + 2) {
			if (player.worldX < (dim.grandezzaInGioco * 23))
				comandi.destraPremuto = true;

			if (player.worldX > (dim.grandezzaInGioco * 23) + 2)
				comandi.sinistraPremuto = true;

		} else {
			comandi.destraPremuto = false;
			comandi.sinistraPremuto = false;
			if (player.worldY >= -(dim.grandezzaInGioco + (5 * dim.scala))) {
				comandi.suPremuto = true;
			} else
				comandi.suPremuto = false;
		}
	}
}