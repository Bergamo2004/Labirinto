package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.*;
import controllo.*;
import entity.Player;
import oggetto.*;
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
	public Comandi comandi;
	public Menu menu;

	public PanelloDiGioco(Dimensioni dimensioni, Comandi comandi) {

		this.dim = dimensioni;
		this.player = new Player(this, comandi);
		// Mappa randomica
		int r = (int) Math.floor(Math.random() * 10 + 1);
		if (r < 10)
			this.tm = new TileManager(this, "/maps/map0" + r + ".txt");
		else
			this.tm = new TileManager(this, "/maps/map" + r + ".txt");
		System.out.println("Generata mappa numero: " + r);
		this.cChecker = new CollisionChecker(this, comandi);
		this.ogg = new SuperOggetto[10];
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
	
	public void setObject() {
		this.ogg[0] = new OggKey();
		this.ogg[0].worldX = 3* 48; //dim da vedere
		this.ogg[0].worldY = 8* 48;
		
		this.ogg[1] = new OggDoor();
		this.ogg[1].worldX = 26* 48; //dim da vedere
		this.ogg[1].worldY = 47* 48;
	}

	public void update() {

		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tm.draw(g2);

		for(int i = 0; i < ogg.length; i++){
			if(ogg[i] != null){
				ogg[i].draw(g2, this);
			}
		}
		
		player.draw(g2);

		if (comandi.giocoInPausa == true)
			pausa(g2);
		g2.dispose();
	}

	public void pausa(Graphics2D g) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, 0, dim.lunghezzaSchermo, dim.altezzaSchermo);
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 100)); // Font.BOLD -> Grassetto; Font.Plain -> Normale
		g.drawString("PAUSA", dim.lunghezzaSchermo / 2 - 150, 100);
	}

}