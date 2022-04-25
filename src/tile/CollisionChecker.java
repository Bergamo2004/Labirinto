package tile;

import entity.Entity;
import view.PanelloDiGioco;

public class CollisionChecker {
	PanelloDiGioco pg;

	public CollisionChecker(PanelloDiGioco gp) {
		this.pg = gp;
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
			switch (entity.direzione) {
			case "su":
				entityTopRow = (entityTopWorldY - entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityTopRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "su";
				}
				break;
			case "giu":
				entityBottomRow = (entityBottomWorldY + entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "giu";
				}
				break;
			case "destra":
				entityRightCol = (entityRightWorldX + entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityRightCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "destra";
				}
				break;
			case "sinistra":
				entityLeftCol = (entityLeftWorldX - entity.speed) / pg.dim.grandezzaInGioco;
				tileNum1 = pg.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = pg.tm.mapTileNum[entityLeftCol][entityBottomRow];
				if (pg.tm.tile[tileNum1].collision == true || pg.tm.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
					entity.collisionDirection = "sinistra";
				}
				break;

			}
		} catch (ArrayIndexOutOfBoundsException e) {
			double d1 = pg.dim.grandezzaInGioco * 24.5;
			
			entity.worldX = (int) d1;
			entity.worldY = (int) d1;
		}
	}
}
