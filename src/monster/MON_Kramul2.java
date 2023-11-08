package monster;

import java.awt.Rectangle;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bill;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_KramulHead;
import projectile.FireBall;



public class MON_Kramul2 extends Entity{
	
	GamePanel gp;


	public MON_Kramul2(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		name = "Kramul";
		determinant = "le";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 8;
		life = maxLife;
		attack = 1;
		defense = 0;
		exp = 3;
		knockBackPower = 4;
		
		scale = 2;
		solidArea.x = 6*scale;
		solidArea.y = 24*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 36*scale;
		solidArea.height = 24*scale;

		
		hitBox = new Rectangle();
		hitBox.x = 6*scale;
		hitBox.y = 0;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 36*scale;
		hitBox.height = 48*scale;
		
		attackArea.x = 6*scale;
		attackArea.y = 24*scale;
		attackAreaDefaultX = attackArea.x;
		attackAreaDefaultY = attackArea.y;
		attackArea.width = 36*scale;
		attackArea.height = 23*scale;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		getImage();
		kramulFire = new KramulFire(gp, this, col, row);
		projectile = new FireBall(gp);
		shoot_motion1_duration = 10;
		shoot_motion2_duration = 10;
	}
	
	
	
	public void getImage() {
		up0 = setup("/kramul2/kramul_up", gp.tileSize*scale, gp.tileSize*scale);
		up1 = setup("/kramul2/kramul_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/kramul2/kramul_up_2", gp.tileSize*scale, gp.tileSize*scale);
		
		down0 = setup("/kramul2/kramul_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/kramul2/kramul_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/kramul2/kramul_down_2", gp.tileSize*scale, gp.tileSize*scale);
		
		right0 = setup("/kramul2/kramul_right", gp.tileSize*scale, gp.tileSize*scale);
		right1 = setup("/kramul2/kramul_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/kramul2/kramul_right_2", gp.tileSize*scale, gp.tileSize*scale);
		
		left0 = setup("/kramul2/kramul_left", gp.tileSize*scale, gp.tileSize*scale);
		left1 = setup("/kramul2/kramul_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/kramul2/kramul_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
		shootUp1 = setup("/kramul2/kramul_shoot_up", gp.tileSize*scale, gp.tileSize*scale);
		shootUp2 = shootUp1;
		shootDown1 = setup("/kramul2/kramul_shoot_down", gp.tileSize*scale, gp.tileSize*scale);
		shootDown2 =shootDown1;
		shootRight1 = setup("/kramul2/kramul_shoot_right", gp.tileSize*scale, gp.tileSize*scale);
		shootRight2 = shootRight1;
		shootLeft1 = setup("/kramul2/kramul_shoot_left", gp.tileSize*scale, gp.tileSize*scale);
		shootLeft2 =shootLeft1;
		
	}
	
	
	
	public void setAction() {
		
		if (onPath) {
			//Check if monster stop chasing player
			checkStopChasing(gp.player, 15, 300);

			//Search the direction to go 
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			if (!collisionOn) {
				checkShoot(50, 30); 
			}
			
		}
		else if (standing) {}
		else {
			//check if it starts chasing
			checkStartChasing(gp.player, 5, 50);
			
			//get a random direction
			getRandomDirection(120);
			if (!collisionOn) {
				checkShoot(100, 30); 
			}
		}
	}
	
	public void damageReaction() {
		gp.playSE(11);
		actionLockCounter = 0;
		direction = gp.player.direction;
		onPath = true;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100);
		
		if (i < 15) {
			OBJ_Bill bill = new OBJ_Bill(gp, 0, 0);
			bill.setValue(5);
			dropItem(bill);
		}
		else if (i >= 15 && i < 50) {
			dropItem(new OBJ_Coin(gp, 0, 0));
		}
		else if (i >= 50 && i < 80) {
			dropItem(new OBJ_Heart(gp, 0, 0));
		}
		else if (i >= 80) {
			dropItem(new OBJ_KramulHead(gp, 0, 0));
		}

	}
	
	public void playMonsterSound() {
		

		int i = new Random().nextInt(20);
		
		if (i == 2) {
			gp.playSE(12);
		}
		else if (i == 3) {
			gp.playSE(13);
		}
		else if (i == 5) {
			gp.playSE(15);
		}
		else if (i == 5) {
			gp.playSE(15);
		}
		else if (i == 6) {
			gp.playSE(16);
		}
		else if (i == 7) {
			gp.playSE(17);
		}
		else if (i == 8) {
			gp.playSE(16);
		}
		else if (i == 9) {
			gp.playSE(17);
		}
		

	}
	

}
