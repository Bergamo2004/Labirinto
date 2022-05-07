package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import oggetto.*;
import view.PanelloDiGioco;

public class TileManager {
	public PanelloDiGioco pg;
	public Tile[] tile;
	int mapTileNum[][];
	public int contOgg;

	public TileManager(PanelloDiGioco pg, String mappa) {
		this.pg = pg;
		tile = new Tile[10];
		mapTileNum = new int[pg.dim.maxWorldCol][pg.dim.maxWorldRow];
		contOgg = 0;
		getTileImage();
		loadMap(mappa);
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
			tile[3].collision = true;

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			e2.printStackTrace();
			System.exit(1);
		}
	}

	public void loadMap(String map) {
		try {

			BufferedImage img = ImageIO.read(getClass().getResourceAsStream(map));
			for (int y = 0; y < img.getHeight(); y++) {
				for (int x = 0; x < img.getWidth(); x++) {
					int pixel = img.getRGB(x, y);

					Color color = new Color(pixel, true);

					int red = color.getRed();
					int green = color.getGreen();
					int blue = color.getBlue();

					if (red == 0 && green == 255 && blue == 0) {
						// verde == erba
						mapTileNum[x][y] = 0;
					}

					if (red == 0 && green == 0 && blue == 0) {
						// nero == muro
						mapTileNum[x][y] = 1;
					}

					if (red == 0 && green == 0 && blue == 255) {
						// blu == acqua
						mapTileNum[x][y] = 2;
					}

					if (red == 255 && green == 0 && blue == 0) {
						// rosso == albero
						mapTileNum[x][y] = 3;
					}

					if (red == 255 && green == 255 && blue == 255) {
						// bianco == sabbia
						mapTileNum[x][y] = 4;
					}

					if (red == 255 && green == 117 && blue == 20) {
						// arancione == terra
						mapTileNum[x][y] = 5;
					}

					if (red == 101 && green == 67 && blue == 33) {
						// marrone == porta
						mapTileNum[x][y] = 0;

						pg.ogg[contOgg] = new OggDoor();
						pg.ogg[contOgg].worldX = x * pg.dim.grandezzaInGioco; // dim da vedere
						pg.ogg[contOgg].worldY = y * pg.dim.grandezzaInGioco;
						contOgg++;
					}

					if (red == 255 && green == 255 && blue == 0) {
						// Giallo == chiave
						mapTileNum[x][y] = 0;

						pg.ogg[contOgg] = new OggKey();
						pg.ogg[contOgg].worldX = x * pg.dim.grandezzaInGioco; // dim da vedere
						pg.ogg[contOgg].worldY = y * pg.dim.grandezzaInGioco;
						contOgg++;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		try {
			int worldCol = 0;
			int worldRow = 0;

			while (worldCol < pg.dim.maxWorldCol && worldRow < pg.dim.maxWorldRow) {
				int tileNum = mapTileNum[worldCol][worldRow];

				// coordinate della mappa
				int worldX = worldCol * pg.dim.grandezzaInGioco;
				int worldY = worldRow * pg.dim.grandezzaInGioco;

				// coordinate dello schermo
				int screenX = worldX - pg.player.worldX + pg.player.screenX;
				int screenY = worldY - pg.player.worldY + pg.player.screenY;

				// differenza tra altezza/lunghezza dello schermo e i loro valori di default
				int d1 = pg.player.defaultScreenX - pg.player.screenX;
				int d2 = pg.player.defaultScreenY - pg.player.screenY;

				if (worldX + pg.dim.grandezzaInGioco > (pg.player.worldX - pg.player.defaultScreenX) + d1
						&& worldX - pg.dim.grandezzaInGioco < (pg.player.worldX + pg.player.defaultScreenX) + d1
						&& worldY + pg.dim.grandezzaInGioco > (pg.player.worldY - pg.player.defaultScreenY) + d2
						&& worldY - pg.dim.grandezzaInGioco < (pg.player.worldY + pg.player.defaultScreenY) + d2)
					g2.drawImage(tile[tileNum].image, screenX, screenY, pg.dim.grandezzaInGioco,
							pg.dim.grandezzaInGioco, null);

				worldCol++;

				if (worldCol == pg.dim.maxWorldCol) {
					worldCol = 0;

					worldRow++;

				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
