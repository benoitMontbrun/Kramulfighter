package monster;

import java.awt.Rectangle;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_KramulHead;



public class MON_Bat extends Entity{
	
	GamePanel gp;


	public MON_Bat(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		name = "Chauve-Souris";
		determinant = "la";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 2;
		life = maxLife;
		attack = 1;
		defense = 0;
		exp = 1;
		knockBackPower = 0;
		
		scale = 2;
		solidArea.x = 8*scale;
		solidArea.y = 6*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32*scale;
		solidArea.height = 12*scale;

		
		hitBox = new Rectangle();
		hitBox.x = 8*scale;
		hitBox.y = 6*scale;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 32*scale;
		hitBox.height = 12*scale;
		
		attackArea.x = 8*scale;
		attackArea.y = 6*scale;
		attackAreaDefaultX = attackArea.x;
		attackAreaDefaultY = attackArea.y;
		attackArea.width = 32*scale;
		attackArea.height = 12*scale;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		getImage();
	}
	
	
	
	public void getImage() {
		up0 = setup("/bat/bat", gp.tileSize*2, gp.tileSize);
		up1 = setup("/bat/bat_1", gp.tileSize*2, gp.tileSize);
		up2 = setup("/bat/bat_2", gp.tileSize*2, gp.tileSize);
		
		down0 = up0;
		down1 = up1;
		down2 = up2;
		
		right0 = up0;
		right1 = up1;
		right2 = up2;
		
		left0 = up0;
		left1 = up1;
		left2 = up2;
		
	}
	
	
	
	public void setAction() {
		
		if (onPath) {}
		else if (standing) {}
		else {
			//get a random direction
			getRandomDirection(10);
			
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;

	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100);
		
		if (i < 70) {
			dropItem(new OBJ_Coin(gp, 0, 0));
		}
		else if (i >= 70) {
			dropItem(new OBJ_Heart(gp, 0, 0));
		}

	}
	
	public void playMonsterSound() {}
	

}
