package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;

	public BufferedImage fronte1, fronte2, retro1, retro2, destra1, destra2, sinistra1, sinistra2;
	public String direzione;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	// Collisioni
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public String collisionDirection;
}
