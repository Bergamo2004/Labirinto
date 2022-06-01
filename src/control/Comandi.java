package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import view.GestionePannelli;

public class Comandi implements KeyListener {

	public boolean suPremuto, giuPremuto, sinistraPremuto, destraPremuto;
	public int velocita = 4;
	public boolean giocoInPausa = false;
	public boolean sp = false;
	public boolean cheat = false;

	private GestionePannelli gp;

	public Comandi(GestionePannelli gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int tasto = e.getKeyCode();

		if ((tasto == KeyEvent.VK_W || tasto == KeyEvent.VK_UP) && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			suPremuto = true;
		}
		if ((tasto == KeyEvent.VK_A || tasto == KeyEvent.VK_LEFT) && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			sinistraPremuto = true;
		}
		if ((tasto == KeyEvent.VK_S || tasto == KeyEvent.VK_DOWN) && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			giuPremuto = true;
		}
		if ((tasto == KeyEvent.VK_D || tasto == KeyEvent.VK_RIGHT) && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			destraPremuto = true;
		}
		if (tasto == KeyEvent.VK_SHIFT && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			if (velocita == 4)
				velocita = 8;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_CONTROL && !giocoInPausa && !gp.gamePanel.player.vittoria) {
			if (velocita == 4)
				velocita = 2;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_ESCAPE && !gp.gamePanel.player.vittoria) {
			if (gp.giocoAvviato) {
				suPremuto = false;
				giuPremuto = false;
				destraPremuto = false;
				sinistraPremuto = false;
				if (!giocoInPausa)
					giocoInPausa = true;
				else
					giocoInPausa = false;
			}
		}
		if (tasto == KeyEvent.VK_E && (gp.gamePanel.player.vittoria || giocoInPausa)) {
			System.exit(0);
		}
		if (tasto == KeyEvent.VK_P) {
			cheat = true;
			if (!sp)
				sp = true;
			else
				sp = false;
		}
		if (tasto == KeyEvent.VK_L) {
			cheat = true;
			suPremuto = false;
			giuPremuto = false;
			destraPremuto = false;
			sinistraPremuto = false;
			String s = JOptionPane.showInputDialog(gp, "Inserisci velocità");
			if (s != null && s.equals("no")) {
				JOptionPane.showMessageDialog(gp, "ok >:(");
				velocita = 0;
			}
			try {
				velocita = Integer.parseInt(s);
			} catch (Exception e1) {

			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int tasto = e.getKeyCode();

		if (tasto == KeyEvent.VK_W || tasto == KeyEvent.VK_UP) {
			suPremuto = false;
		}
		if (tasto == KeyEvent.VK_A || tasto == KeyEvent.VK_LEFT) {
			sinistraPremuto = false;
		}
		if (tasto == KeyEvent.VK_S || tasto == KeyEvent.VK_DOWN) {
			giuPremuto = false;
		}
		if (tasto == KeyEvent.VK_D || tasto == KeyEvent.VK_RIGHT) {
			destraPremuto = false;
		}
	}

}
