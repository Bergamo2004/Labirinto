package oggetto;

import java.awt.image.BufferedImage;

public class SuperOggetto {
	
	public BufferedImage image;
	public String nome;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw (Graphics2D g2, PanelloDiGioco gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		int d1 = gp.player.defaultScreenX - gp.player.screenX;
		int d2 = gp.player.defaultScreenY - gp.player.screenY;

		if (worldX + gp.dim.grandezzaInGioco > (gp.player.worldX - gp.player.defaultScreenX) + d1
				&& worldX - gp.dim.grandezzaInGioco < (gp.player.worldX + gp.player.defaultScreenX) + d1
				&& worldY + gp.dim.grandezzaInGioco > (gp.player.worldY - gp.player.defaultScreenY) + d2
				&& worldY - gp.dim.grandezzaInGioco < (gp.player.worldY + gp.player.defaultScreenY) + d2) {
			
			g2.drawImage(image, screenX, screenY, gp.dim.grandezzaInGioco, gp.dim.grandezzaInGioco, null);
		}
	}
}
