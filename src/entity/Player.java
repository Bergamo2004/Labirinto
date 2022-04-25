package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllo.Comandi;
import view.PanelloDiGioco;

public class Player extends Entity {
	PanelloDiGioco pg;
	Comandi com;

	public final int defaultScreenX;
	public final int defaultScreenY;

	public int screenX;
	public int screenY;

	public Player(PanelloDiGioco pg, Comandi com) {
		this.pg = pg;
		this.com = com;

		defaultScreenX = pg.dim.lunghezzaInGioco / 2 - (pg.dim.grandezzaInGioco / 2); // 360
		defaultScreenY = pg.dim.altezzaInGioco / 2 - (pg.dim.grandezzaInGioco / 2);   // 264

		solidArea = new Rectangle(8, 16, 32, 32);

		setDefaultValues();
		getPlayerImage();
	}

	public void getPlayerImage() {
		try {
			fronte1 = ImageIO.read(getClass().getResourceAsStream("/player/Fronte1.png"));
			fronte2 = ImageIO.read(getClass().getResourceAsStream("/player/Fronte2.png"));
			retro1 = ImageIO.read(getClass().getResourceAsStream("/player/Retro1.png"));
			retro2 = ImageIO.read(getClass().getResourceAsStream("/player/Retro2.png"));
			destra1 = ImageIO.read(getClass().getResourceAsStream("/player/Destra1.png"));
			destra2 = ImageIO.read(getClass().getResourceAsStream("/player/Destra2.png"));
			sinistra1 = ImageIO.read(getClass().getResourceAsStream("/player/Sinistra1.png"));
			sinistra2 = ImageIO.read(getClass().getResourceAsStream("/player/Sinistra2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		
		double d1 = pg.dim.grandezzaInGioco * 24.5;
		
		worldX = (int) d1;
		worldY = (int) d1;
		
		screenX = defaultScreenX;
		screenY = defaultScreenY;
		
		// System.out.println("Coord = X: " + worldX + ", Y: " + worldY);
		speed = 4;
		direzione = "giu";
	}

	public void update() {
		if (com.suPremuto || com.giuPremuto || com.destraPremuto || com.sinistraPremuto) {
			speed = com.velocita;
			if (com.suPremuto) {
				direzione = "su";
			}
			if (com.giuPremuto) {
				direzione = "giu";
			}
			if (com.destraPremuto) {
				direzione = "destra";
			}
			if (com.sinistraPremuto) {
				direzione = "sinistra";
			}

			collisionOn = false;
			pg.cChecker.checkTile(this);

			if (collisionOn == false) {

				int worldXCam = pg.dim.worldWidth - pg.dim.lunghezzaInGioco / 2 - (pg.dim.grandezzaInGioco / 2);
				int worldYCam = pg.dim.worldHeight - pg.dim.altezzaInGioco / 2 - (pg.dim.grandezzaInGioco / 2);

				if (com.suPremuto) {
					if (worldY <= defaultScreenY || worldY >= worldYCam) {
						worldY -= speed;
						screenY -= speed;
					} else {
						worldY -= speed;
						screenY = defaultScreenY;
					}
				}
				if (com.giuPremuto) {
					if (worldY <= defaultScreenY || worldY >= worldYCam) {
						worldY += speed;
						screenY += speed;
					} else {
						worldY += speed;
						screenY = defaultScreenY;
					}
				}
				if (com.destraPremuto) {
					if (worldX <= defaultScreenX || worldX >= worldXCam) {
						worldX += speed;
						screenX += speed;
					} else {
						worldX += speed;
						screenX = defaultScreenX;
					}
				}
				if (com.sinistraPremuto) {
					if (worldX <= defaultScreenX || worldX >= worldXCam) {
						worldX -= speed;
						screenX -= speed;
					} else {
						worldX -= speed;
						screenX = defaultScreenX;
					}
				}
				//System.out.println("Coord = X: " + worldX + ", Y: " + worldY);
			}
			// Velocita animazione
			if (collisionOn == false) {
				spriteCounter++;
				if (spriteCounter > 15) {
					if (spriteNum == 1)
						spriteNum = 2;
					else if (spriteNum == 2)
						spriteNum = 1;
					spriteCounter = 0;
				}
			} else
				spriteNum = 1;
		} else
			spriteNum = 1;

	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direzione) {
		case "su":
			if (spriteNum == 1)
				image = fronte1;
			if (spriteNum == 2)
				image = fronte2;
			break;
		case "giu":
			if (spriteNum == 1)
				image = retro1;
			if (spriteNum == 2)
				image = retro2;
			break;
		case "destra":
			if (spriteNum == 1)
				image = destra1;
			if (spriteNum == 2)
				image = destra2;
			break;
		case "sinistra":
			if (spriteNum == 1)
				image = sinistra1;
			if (spriteNum == 2)
				image = sinistra2;
			break;
		}
		g2.drawImage(image, screenX, screenY, pg.dim.grandezzaInGioco, pg.dim.grandezzaInGioco, null);
	}

}
