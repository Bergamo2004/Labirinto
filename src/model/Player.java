package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.Comandi;
import view.Dimensioni;
import view.PanelloDiGioco;

public class Player extends Entity {
	PanelloDiGioco pg;
	Comandi com;

	public final int defaultScreenX;
	public final int defaultScreenY;

	public int screenX;
	public int screenY;

	public int hasKey;

	public boolean vittoria = false;

	public Player(PanelloDiGioco pg, Comandi com) {
		this.pg = pg;
		this.com = com;

		defaultScreenX = pg.dim.lunghezzaSchermo / 2 - (pg.dim.grandezzaInGioco / 2); // 360
		defaultScreenY = pg.dim.altezzaSchermo / 2 - (pg.dim.grandezzaInGioco / 2); // 264

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

		worldX = 25 * pg.dim.grandezzaInGioco;
		worldY = 43 * pg.dim.grandezzaInGioco;

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		hasKey = 0;

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
			pickupObject(pg.cChecker.checkObject(this, true));

			if (collisionOn == false) {

				int worldXCam = pg.dim.worldWidth - pg.dim.lunghezzaSchermo / 2 - (pg.dim.grandezzaInGioco / 2);
				int worldYCam = pg.dim.worldHeight - pg.dim.altezzaSchermo / 2 - (pg.dim.grandezzaInGioco / 2);

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
				// System.out.println("Coord = X: " + worldX + ", Y: " + worldY);
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

	public void pickupObject(int i) {
		if (i != 999) {
			String objectName = pg.ogg[i].nome;
			switch (objectName) {
			case "chiave":
				hasKey++;
				pg.ogg[i] = null;
				pg.effettosonoro(3);
				break;
			case "porta":
				if (hasKey > 0) {
					hasKey--;
					pg.ogg[i] = null;
					pg.effettosonoro(4);
					vittoria = true;
				} else
					collisionOn = true;
				break;
			}
		}
	}

}
