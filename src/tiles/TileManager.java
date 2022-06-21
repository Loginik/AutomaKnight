package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager (GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void tileConstructor(int tileNum,String path) {
		tile[tileNum] = new Tile();
		try {
			tile[tileNum].image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getTileImage() {
		
			tileConstructor(0, "/tiles/grass.png");
			tileConstructor(1, "/tiles/wall.png");
			tileConstructor(2, "/tiles/water.png");
			tileConstructor(3, "/tiles/earth.png");
			tileConstructor(4, "/tiles/tree.png");
			tileConstructor(5, "/tiles/sand.png");
	}
	public void loadMap(String path) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader( new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			double screenX = worldX - gp.player.worldX + gp.player.screenX;
			double screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize * 4 < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize * 4 < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, (int)screenX, (int)screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
