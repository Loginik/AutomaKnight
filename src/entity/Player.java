package entity;

import java.awt.Color;
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
	 
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		//Image image = new ImageIcon("/player/Chara1").getImage();
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/player/chara1.png"));
			//BufferedImage image = ImageIO.read(new File("res_class/player/chara1.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
			
			up1 = img.getSubimage(0, 96, 32, 32);
			up2 = img.getSubimage(32, 96, 32, 32);
			down1 = img.getSubimage(0, 0, 32, 32);
			down2 = img.getSubimage(32, 0, 32, 32);
			left1 = img.getSubimage(0, 32, 32, 32);
			left2 = img.getSubimage(32, 32, 32, 32);
			right1 = img.getSubimage(32, 32, 32, 32);
			right2 = img.getSubimage(32, 32, 32, 32);
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
			direction = "up";
			y -= speed;
		} 	
		else if(keyH.downPressed == true) {
			direction = "down";
			y += speed;
		} 	
		else if(keyH.leftPressed == true) {
			direction = "left";
			x -= speed;
		} 	
		else if(keyH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter > 15) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
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
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
