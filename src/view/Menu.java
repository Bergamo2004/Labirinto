package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JPanel {

	private Dimensioni dim;
	public JButton start;
	public JButton stop;
	private BufferedImage sfondo;
	
	public Menu(Dimensioni dimensioni) {
		dim = dimensioni;
		this.setPreferredSize(new Dimension(dim.lunghezzaInGioco, dim.altezzaInGioco));
		this.setBackground(Color.black);
		setLayout(null);
		
		start = new JButton();
		start.setBounds(dim.lunghezzaInGioco/2-86, dim.altezzaInGioco/2-25, 85, 25);
		start.setText("Start");
		start.setFocusable(false);
		this.add(start);

		stop = new JButton();
		stop.setBounds(dim.lunghezzaInGioco/2+2, dim.altezzaInGioco/2-25, 85, 25);
		stop.setText("Stop");
		stop.setFocusable(false);
		this.add(stop);
	}

	public void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			sfondo = ImageIO.read(getClass().getResourceAsStream("/menu/menu1.png"));
			g2.drawImage(sfondo, 0, 0, dim.lunghezzaInGioco, dim.altezzaInGioco, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pausa() {
		repaint();
	}
}
