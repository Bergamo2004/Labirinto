package controllo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GestionePannelli;

public class Comandi implements KeyListener {

	public boolean suPremuto, giuPremuto, sinistraPremuto, destraPremuto;
	public int velocita = 4;
	private int codPausa = 0;

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
		
		if (tasto == KeyEvent.VK_W) {
			suPremuto = true;
		}
		if (tasto == KeyEvent.VK_A) {
			sinistraPremuto = true;
		}
		if (tasto == KeyEvent.VK_S) {
			giuPremuto = true;
		}
		if (tasto == KeyEvent.VK_D) {
			destraPremuto = true;
		}
		if (tasto == KeyEvent.VK_SHIFT) {
			if (velocita == 4)
				velocita = 8;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_CONTROL) {
			if (velocita == 4)
				velocita = 2;
			else
				velocita = 4;
		}
		if (tasto == KeyEvent.VK_ESCAPE) {
			if(gp.giocoAvviato) {
				suPremuto = false;
				giuPremuto = false;
				destraPremuto = false;
				sinistraPremuto = false;
				gp.pausa(codPausa);
				if(codPausa == 0)
					codPausa = 1;
				else
					codPausa = 0;
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
