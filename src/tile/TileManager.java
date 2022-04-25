package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import view.PanelloDiGioco;

public class TileManager {
	PanelloDiGioco gp;
	public Tile[] tile;
	int mapTileNum[][];

	public TileManager(PanelloDiGioco gp, String mappa) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.dim.maxWorldCol][gp.dim.maxWorldRow];
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

			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;

			while (col < gp.dim.maxWorldCol && row < gp.dim.maxWorldRow) {

				String linea = br.readLine();
				while (col < gp.dim.maxWorldCol) {

					String numeri[] = linea.split(" ");
					int num = Integer.parseInt(numeri[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.dim.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.dim.maxWorldCol && worldRow < gp.dim.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];

			// coordinate della mappa
			int worldX = worldCol * gp.dim.grandezzaInGioco;
			int worldY = worldRow * gp.dim.grandezzaInGioco;

			// coordinate dello schermo
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			// differenza tra altezza/lunghezza dello schermo e i loro valori di default
			int d1 = gp.player.defaultScreenX - gp.player.screenX;
			int d2 = gp.player.defaultScreenY - gp.player.screenY;

			if (worldX + gp.dim.grandezzaInGioco > (gp.player.worldX - gp.player.defaultScreenX) + d1
					&& worldX - gp.dim.grandezzaInGioco < (gp.player.worldX + gp.player.defaultScreenX) + d1
					&& worldY + gp.dim.grandezzaInGioco > (gp.player.worldY - gp.player.defaultScreenY) + d2
					&& worldY - gp.dim.grandezzaInGioco < (gp.player.worldY + gp.player.defaultScreenY) + d2)
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.dim.grandezzaInGioco, gp.dim.grandezzaInGioco,
						null);

			worldCol++;

			if (worldCol == gp.dim.maxWorldCol) {
				worldCol = 0;

				worldRow++;

			}
		}
	}

}
