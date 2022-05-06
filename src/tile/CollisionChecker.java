package tile;

import entity.Entity;
import view.PanelloDiGioco;
import controllo.*;

public class CollisionChecker {
	PanelloDiGioco pg;
	Comandi com;

	public CollisionChecker(PanelloDiGioco gp, Comandi com) {
		this.pg = gp;
		this.com = com;
	}

	public void checkTile(Entity entity) {

		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / pg.dim.grandezzaInGioco;
		int entityRightCol = entityRightWorldX / pg.dim.grandezzaInGioco;
		int entityTopRow = entityTopWorldY / pg.dim.grandezzaInGioco;
		int entityBottomRow = entityBottomWorldY / pg.dim.grandezzaInGioco;

		int tileNum1, tileNum2;
		try {
			if (com.suPremuto) {
				entityTopRow = (entityTopWorldY - entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityTopRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "su";
				}
			}
			if (com.giuPremuto) {
				entityBottomRow = (entityBottomWorldY + entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "giu";
				}
			}
			if (com.destraPremuto) {
				entityRightCol = (entityRightWorldX + entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "destra";
				}
			}
			if (com.sinistraPremuto) {
				entityLeftCol = (entityLeftWorldX - entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityLeftCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "sinistra";
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			double d1 = pg.dim.grandezzaInGioco * 24.5;

			entity.worldX = (int) d1;
			entity.worldY = (int) d1;
		}
	}
public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < pg.ogg.length; i++) {
			if(pg.ogg[i] != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				pg.ogg[i].solidArea.x = pg.ogg[i].worldX +pg.ogg[i].solidArea.x;
				pg.ogg[i].solidArea.y = pg.ogg[i].worldY +pg.ogg[i].solidArea.y;
				
				switch(entity.direzione) {
					case "up":
						entity.solidArea.y -= entity.speed;
						if(entity.solidArea.intersects(pg.ogg[i].solidArea)) {
							if(pg.ogg[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
					break;
					case "down":
						entity.solidArea.y += entity.speed;
						if(entity.solidArea.intersects(pg.ogg[i].solidArea)) {
							if(pg.ogg[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
					break;
					case "left":
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(pg.ogg[i].solidArea)) {
							if(pg.ogg[i].collision == true) {
							entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}	
						}
						
					break;
					case "right":
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(pg.ogg[i].solidArea)) {
							if(pg.ogg[i].collision == true) {
							entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}					
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				pg.ogg[i].solidArea.x = pg.ogg[i].solidAreaDefaultX;
				pg.ogg[i].solidArea.y = pg.ogg[i].solidAreaDefaultY;
			}
		}		
		return index;
	}
}
