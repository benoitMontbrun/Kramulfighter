package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import entity.Entity;
import main.GamePanel;




public class KramulFire extends Entity{
	
	GamePanel gp;
	Entity kramul;


	public KramulFire(GamePanel gp, Entity kramul, int col, int row) {
		super(gp);
		this.gp = gp;
		this.kramul = kramul;
		

		name = "KramulFire";
		determinant = "le";

		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		scale = 2;
		
		spriteNum = 1;
		alive = true;
		
		getImage();
	}
	
	
	
	public void getImage() {
		up1 = setup("/kramul_fire/kramul_fire_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/kramul_fire/kramul_fire_up_2", gp.tileSize*scale, gp.tileSize*scale);
		
		down1 = setup("/kramul_fire/kramul_fire_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/kramul_fire/kramul_fire_down_2", gp.tileSize*scale, gp.tileSize*scale);
		
		right1 = setup("/kramul_fire/kramul_fire_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/kramul_fire/kramul_fire_right_2", gp.tileSize*scale, gp.tileSize*scale);

		left1 = setup("/kramul_fire/kramul_fire_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/kramul_fire/kramul_fire_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
	}
		
	
	public void update() {
		
		worldX = kramul.worldX;
		worldY = kramul.worldY;
		direction = kramul.direction;
		
		spriteCounter++;
		if (spriteCounter > 8) {
			switch(spriteNum) {
			case 1:
				spriteNum = 2;
				break;
			case 2:
				spriteNum = 1;
				break;
			}
			spriteCounter = 0;
		}
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		

			
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		
		
		}
		
		
		g2.drawImage(image, screenX, screenY, null);
	}

}
