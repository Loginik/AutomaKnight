package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;

import game.GamePanel;
import game.KeyHandler;



public class Player extends Entity{

	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	 
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		int eScale = gp.tileSize * scale;
		
		screenX = gp.screenWidth / 2 - (eScale / 2);
		screenY = gp.screenHeight / 2 - (eScale / 2);
		
		solidArea = new Rectangle(8, 16, 32, 32);
		setDefaultValues();
		getPlayerImage();
	}

	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		speed = gp.worldWidth / 600;
		direction = "down";
	}
	
	public void getPlayerImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/player/chara1.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		up1 = img.getSubimage(0, 96, 32, 32);
		up2 = img.getSubimage(32, 96, 32, 32);
		up3 = img.getSubimage(64, 96, 32, 32);
		down1 = img.getSubimage(0, 0, 32, 32);
		down2 = img.getSubimage(32, 0, 32, 32);
		down3 = img.getSubimage(64, 0, 32, 32);
		left1 = img.getSubimage(0, 32, 32, 32);
		left2 = img.getSubimage(32, 32, 32, 32);
		left3 = img.getSubimage(64, 32, 32, 32);
		right1 = img.getSubimage(0, 64, 32, 32);
		right2 = img.getSubimage(32, 64, 32, 32);
		right3 = img.getSubimage(64, 64, 32, 32);
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
			direction = "up";
			worldY -= speed;
		} 	
		else if(keyH.downPressed == true) {
			direction = "down";
			worldY += speed;
		} 	
		else if(keyH.leftPressed == true) {
			direction = "left";
			worldX -= speed;
		} 	
		else if(keyH.rightPressed == true) {
			direction = "right";
			worldX += speed;
		}
			
		if(keyH.upPressed == true && keyH.leftPressed == true) {
			direction = "up";
			worldY -= (speed / 2);
			worldX -= (speed / 2);
		}
		else if (keyH.upPressed == true && keyH.rightPressed == true) {
			direction = "up";
			worldY -= (speed / 2);
			worldX += (speed / 2);
		}
		else if (keyH.downPressed == true && keyH.leftPressed == true) {
			direction = "down";
			worldY += (speed / 2);
			worldX -= (speed / 2);
		}
		else if (keyH.downPressed == true && keyH.rightPressed == true) {
			direction = "down";
			worldY += (speed / 2);
			worldX += (speed / 2);
		}
		
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		  }
		} else {
			spriteNum = 2;
			if (direction != "up") {
				notMoving++;
				if (notMoving == 250) {
					direction = "down";
					notMoving = 0;
			}
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);	
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			if (spriteNum == 3) {
				image = up3;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			if (spriteNum == 3) {
				image = down3;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteNum == 3) {
				image = left3;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			if (spriteNum == 3) {
				image = right3;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize * scale, gp.tileSize * scale, null);
	}
}
