package entity;

import java.awt.image.BufferedImage;

import game.GamePanel;


public class Entity {
	

	public double worldX, worldY;
	public int speed;
	public int scale = 2;
   
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 2;
	public int notMoving = 0;
	
}
