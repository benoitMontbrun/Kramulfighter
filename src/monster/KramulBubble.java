package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import entity.Entity;
import main.GamePanel;




public class KramulBubble extends Entity{
	
	GamePanel gp;
	Entity kramul;


	public KramulBubble(GamePanel gp, Entity kramul, int worldX, int worldY) {
		super(gp);
		this.gp = gp;
		this.kramul = kramul;
		

		name = "KramulFire";
		determinant = "le";

		
		this.worldX = worldX;
		this.worldY = worldY;
		scale = 2;
		
		alive = true;
		life = 300;
		
		spriteNum = 1;
		
		getImage();
	}
	
	
	
	public void getImage() {
		up1 = setup("/kramul_fire/kramul_bubble_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/kramul_fire/kramul_bubble_2", gp.tileSize*scale, gp.tileSize*scale);
		
	}
		
	
	public void update() {
		
		life--;
		
		worldX = kramul.worldX;
		worldY = kramul.worldY;
		
		spriteCounter++;
		if (spriteCounter > 30) {
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
		
		
		if (life <= 0) {
			alive = false;
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		

			
		BufferedImage image = null;
		if (spriteNum == 1) {
			image = up1;
		}
		else if (spriteNum == 2) {
			image = up2;
		}

		
		
		g2.drawImage(image, screenX, screenY, null);
	}

}
