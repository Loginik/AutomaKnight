package game;
import entity.Entity;
import game.GamePanel;

public class Collision {
		GamePanel gp;
	public Collision(GamePanel gp) {
		this.gp = gp ;
	}
	
	public void checkTile(Entity entity) {
		
		double entityLeftWorldX = entity.worldX + entity.solidArea.x;
		double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		double entityTopWorldY = entity.worldY + entity.solidArea.y;
		double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		double entityLeftCol = entityLeftWorldX/gp.tileSize;
		double entityRightCol = entityLeftWorldX/gp.tileSize;
		double entityTopRow = entityTopWorldY/gp.tileSize;
		double entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
	}
	
}

