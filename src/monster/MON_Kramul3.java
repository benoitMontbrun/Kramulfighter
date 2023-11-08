package monster;

import java.awt.Rectangle;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bat;
import object.OBJ_Bill;
import object.OBJ_Bottle;
import object.OBJ_KramulHead;



public class MON_Kramul3 extends Entity{
	
	GamePanel gp;


	public MON_Kramul3(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		type = type_monster;
		name = "Kramul";
		determinant = "le";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 20;
		life = maxLife;
		attack = 1;
		defense = 0;
		exp = 6;
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
		
		motion1_duration = 40;
		motion2_duration = 85;
		currentWeapon = new OBJ_Bat(gp, 0, 0);
	}
	
	
	
	public void getImage() {
		up0 = setup("/kramul3/kramul_up", gp.tileSize*scale, gp.tileSize*scale);
		up1 = setup("/kramul3/kramul_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/kramul3/kramul_up_2", gp.tileSize*scale, gp.tileSize*scale);
		
		down0 = setup("/kramul3/kramul_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/kramul3/kramul_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/kramul3/kramul_down_2", gp.tileSize*scale, gp.tileSize*scale);
		
		right0 = setup("/kramul3/kramul_right", gp.tileSize*scale, gp.tileSize*scale);
		right1 = setup("/kramul3/kramul_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/kramul3/kramul_right_2", gp.tileSize*scale, gp.tileSize*scale);
		
		left0 = setup("/kramul3/kramul_left", gp.tileSize*scale, gp.tileSize*scale);
		left1 = setup("/kramul3/kramul_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/kramul3/kramul_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
		attackUp1 = setup("/kramul3/kramul_attack_up_1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackUp2 = setup("/kramul3/kramul_attack_up_2", gp.tileSize*scale, gp.tileSize*scale*2);
		
		attackDown1 = setup("/kramul3/kramul_attack_down_1", gp.tileSize*scale, gp.tileSize*scale*2);
		attackDown2 = setup("/kramul3/kramul_attack_down_2", gp.tileSize*scale, gp.tileSize*scale*2);
		
		attackRight1 = setup("/kramul3/kramul_attack_right_1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackRight2 = setup("/kramul3/kramul_attack_right_2", gp.tileSize*scale*2, gp.tileSize*scale);
		
		attackLeft1 = setup("/kramul3/kramul_attack_left_1", gp.tileSize*scale*2, gp.tileSize*scale);
		attackLeft2 = setup("/kramul3/kramul_attack_left_2", gp.tileSize*scale*2, gp.tileSize*scale);
		
	}
	
	
	
	public void setAction() {
		
		if (onPath) {
			//Check if monster stop chasing player
			checkStopChasing(gp.player, 15, 200);

			//Search the direction to go 
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			//Attack
			if (!attacking) {
				attack = 1;
				checkAttack(30, gp.tileSize*2, gp.tileSize*2);
			}
		}
		else if (standing) {}
		else {
			//check if it starts chasing
			checkStartChasing(gp.player, 5, 30);
			
			//get a random direction
			getRandomDirection(120);
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
			bill.setValue(10);
			dropItem(bill);
		}
		else if (i >= 15 && i < 50) {
			OBJ_Bill bill = new OBJ_Bill(gp, 0, 0);
			bill.setValue(5);
			dropItem(bill);
		}
		else if (i >= 50 && i < 70) {
			OBJ_Bottle bottle = new OBJ_Bottle(gp, 0, 0);
			bottle.changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Piquette", 2);
			dropItem(bottle);
		}
		else if (i >= 70 && i < 80) {
			dropItem(new OBJ_Bat(gp, 0, 0));
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
