package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import control.Comandi;

@SuppressWarnings("serial")
public class GestionePannelli extends JFrame implements ActionListener {
	public PanelloDiGioco gamePanel;
	private Menu m;
	private BufferedImage icona;
	private Dimensioni dim;
	private Comandi comandi;
	public boolean giocoAvviato = false;

	public GestionePannelli() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Labirinto");

		try {
			icona = ImageIO.read(getClass().getResourceAsStream("/menu/icona.png"));
			this.setIconImage(icona);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch(IllegalArgumentException e2) {
			System.out.println("Errore: Icona non trovata");
		}

		comandi = new Comandi(this);
		this.addKeyListener(comandi);

		dim = new Dimensioni();
		m = new Menu(dim);

		this.add(m);
		m.start.addActionListener(this);
		m.stop.addActionListener(this);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == m.start) {
			if(comandi.giocoInPausa)
				comandi.giocoInPausa = false;
			if (!giocoAvviato) {
				gamePanel = new PanelloDiGioco(dim, comandi);
				giocoAvviato = true;
			}
			this.remove(m);
			this.add(gamePanel);
			this.revalidate();
		}

		if (evt.getSource() == m.stop) {
			System.exit(0);
		}

	}
}
