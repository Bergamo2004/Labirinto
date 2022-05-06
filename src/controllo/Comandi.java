package controllo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GestionePannelli;

public class Comandi implements KeyListener {

	public boolean suPremuto, giuPremuto, sinistraPremuto, destraPremuto;
	public int velocita = 4;
	public boolean giocoInPausa = false;

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

		if (tasto == KeyEvent.VK_W && !giocoInPausa) {
			suPremuto = true;
		}
		if (tasto == KeyEvent.VK_A && !giocoInPausa) {
			sinistraPremuto = true;
		}
		if (tasto == KeyEvent.VK_S && !giocoInPausa) {
			giuPremuto = true;
		}
		if (tasto == KeyEvent.VK_D && !giocoInPausa) {
			destraPremuto = true;
		}
		if (tasto == KeyEvent.VK_SHIFT && !giocoInPausa) {
			if (velocita == 4)
				velocita = 8;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_CONTROL && !giocoInPausa) {
			if (velocita == 4)
				velocita = 2;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_ESCAPE) {
			if (gp.giocoAvviato) {
				suPremuto = false;
				giuPremuto = false;
				destraPremuto = false;
				sinistraPremuto = false;
				// gp.pausa(codPausa);
				if (!giocoInPausa)
					giocoInPausa = true;
				else
					giocoInPausa = false;
				//System.out.println(giocoInPausa);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int tasto = e.getKeyCode();

		if (tasto == KeyEvent.VK_W) {
			suPremuto = false;
		}
		if (tasto == KeyEvent.VK_A) {
			sinistraPremuto = false;
		}
		if (tasto == KeyEvent.VK_S) {
			giuPremuto = false;
		}
		if (tasto == KeyEvent.VK_D) {
			destraPremuto = false;
		}
	}

}
