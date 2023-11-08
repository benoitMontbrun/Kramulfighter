package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import monster.KramulBubble;
import monster.KramulFire;
import object.OBJ_Key;
import projectile.Projectile;

public class Entity {
	
	
	public GamePanel gp;	
	public BufferedImage up0, down0, right0, left0, up1, up2, down1, down2, right1, right2, left1, left2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2,
						 shootUp1, shootUp2, shootDown1, shootDown2, shootRight1, shootRight2, shootLeft1, shootLeft2,
						 guardUp, guardDown, guardLeft, guardRight;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public Rectangle hitBox = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int attackAreaDefaultX, attackAreaDefaultY;
	public int attackAreaDefaultWidth, attackAreaDefaultHeight;
	public int hitBoxDefaultX, hitBoxDefaultY;
	public int scale;
	public boolean collision = false;
	public String dialogues[][] = new String[20][20];
	public Entity attacker;
	public KramulFire kramulFire;
	public KramulBubble kramulBubble;

	
	// STATE
	public int worldX,worldY;	
	public String direction = "down";
	public int spriteNum = 0;
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public boolean standing = false;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean shooting = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean hpbarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public Entity loot;
	public boolean opened = false;
	public boolean inRage = false;
	
	
	// COUNTER
	public int invincibleCounter = 0;
	public int actionLockCounter = 0;
	public int spriteCounter = 0;
	public int shotCounter;
	int dyingCounter = 0;
	int standCounter = 0;
	int hpbarCounter = 0;	
	int knockBackCounter = 0;
	public int guardCounter = 0;
	int offBalanceCounter = 0;
	public int dialogueSituation = 0;
	
	// CHARACHTER ATTRIBUTE
	public String name;
	public String name2;
	public String name3;
	public int defaultSpeed;
	public int speed;
	public int maxLife;
	public int life;
	public int ammo;
	public int level;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration, motion2_duration, shoot_motion1_duration, shoot_motion2_duration;
	public int arrowNum, bubbleNum;
	public Entity currentWeapon;
	public Entity currentShootWeapon;
	public Entity currentLight;
	public Projectile projectile;
	
	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public String determinant;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadius;
	
	// TYPE
	public int type; // 0 = player, 1 = npc, 2 = monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_bat = 5;
	public final int type_consumable = 6;	
	public final int type_pickupOnly = 7;
	public final int type_obstacle = 8;
	public final int type_light = 9;
	public final int type_pickaxe = 10;
	public final int type_unic = 11;
	public final int type_automatic = 12;
	public final int type_moveable = 13;
	
	public Entity(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public BufferedImage setup(String imageName, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			image = uTool.scaleImage(image, width, height);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	public int getWidth() {
		return down0.getWidth();
	}
	
	public int getHeight() {
		return down0.getHeight();
	}
	
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	
	public int getTopY() {
		return worldY + solidArea.y;
	}
	
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	
	public int getCol() {
		return (worldX + solidArea.x)/gp.tileSize;
	}
	
	public int getRow() {
		return (worldY + solidArea.y)/gp.tileSize;
	}
	
	public int getCenterX() {
		return worldX + down0.getWidth()/2;
	}
	
	public int getCenterY() {
		return worldY + down0.getHeight()/2;
	}
	
	public int[] getAttackUpPosition() {
		return new int[2];
	}
	
	public int[] getAttackDownPosition() {
		return new int[2];
	}
	
	public int[] getAttackRightPosition() {
		return new int[2];
	}
	
	public int[] getAttackLeftPosition() {
		return new int[2];
	}
	
	public int getXdistance(Entity target) {
		return Math.abs(getCenterX() - target.getCenterX());
	}
	
	public int getYdistance(Entity target) {
		return Math.abs(getCenterY() - gp.player.getCenterY());
	}
	
	public int getTileDistance(Entity target) {
		return (getXdistance(target) + getYdistance(target))/gp.tileSize;
	}
	
	public int getGoalCol(Entity target) {
		return (target.worldX + target.solidArea.x)/gp.tileSize;
	}
	
	public int getGoalRow(Entity target) {
		return (target.worldY + target.solidArea.y)/gp.tileSize;
	}
	
	public int getAttack() {
		if (currentWeapon != null) {
			attackArea = currentWeapon.attackArea;	
			attackAreaDefaultWidth = attackArea.width;
			attackAreaDefaultHeight = attackArea.height;
			
			if (type == type_monster) {
				motion1_duration = currentWeapon.motion1_duration*2;
				motion2_duration = currentWeapon.motion2_duration*2;
				return currentWeapon.attackValue*4;
			}
			else {
				motion1_duration = currentWeapon.motion1_duration;
				motion2_duration = currentWeapon.motion2_duration;
				return currentWeapon.attackValue;
			}
		}
		else {
			return 0;
		}

	}
	
	public void setLoot(Entity loot) {}
	
	public void setDialogue() {}
	
	public void setImage() {}
	
	public void setType(String type) {}
	
	public void setValue(int value) {}
	
	public void setAction() {}
	
	public void changeValues(String name2, String name3, int value) {}
	
	public void setKey(String key) {}
	
	public void move(String direction) {}
	
	public void damageReaction() {}
	
	public void speak() {}
	
	public void interact() {}
	
	public boolean use(Entity entity) {return false;} 
	
	public void checkDrop() {}
	
	public void resetCounter() {
		invincibleCounter = 0;
		actionLockCounter = 0;
		spriteCounter = 0;
		shotCounter = 0;
		dyingCounter = 0;
		standCounter = 0;
		hpbarCounter = 0;	
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;
	}
	
	public void setDescription() {}
	
	public void getRandomDirection(int interval) {
		
		actionLockCounter++;
		if (actionLockCounter >= interval) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			}
			else if (i > 25 && i <= 50) {
				direction = "down";
			}
			else if (i > 50 && i <= 75) {
				direction = "right";
			}
			else if (i > 75) {
				direction = "left";
			}
			
			if (type == type_monster) {
				playMonsterSound();
			}
			
			actionLockCounter = 0;
		}
	}
	
	public void playMonsterSound() {}
	
	public void facePlayer() {
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public void playBendSound() {}
	
	public void playShotSound() {}
	
	public void setOpened() {}
	
	public void setClosed() {}
	
	public void setColor(String color) {}
	
	public void startDialogue(Entity entity, int setNum) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}
	
	public void dropItem(Entity droppedItem) {
		for (int i = 0; i < gp.obj[1].length; i++) {
			if (gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = getCenterX() - gp.tileSize/2;
				gp.obj[gp.currentMap][i].worldY = getCenterY() - gp.tileSize/2;
				break;
			}
		}
	}
	
	public void damagePlayer(int attack, int trueKnockbackPower) {
		if (gp.player.invincible == false) {
			
			int damage = attack - gp.player.defense;
			
			//Get opposite direction of attacker
			String canGuardDirection = getOppositeDirection(direction);
			if (gp.player.guarding && gp.player.direction == canGuardDirection) {
				//Parry
				if (gp.player.guardCounter < 10) {
					damage = 0;
					gp.playSE(16);
					setKnockBack(this, gp.player, 5);
					offBalance = true;
					spriteCounter -= 60;
				}
				else {//normal guard
					damage /= 3;
					gp.playSE(15);
				}

			}
			else {//not guarding
				gp.playSE(4);
				
				
				if (damage < 1) {
					damage = 1 ;
				}
			}
			
			if (damage != 0) {
				gp.player.transparent = true;
				setKnockBack(gp.player, this, trueKnockbackPower);
			}

			gp.player.life -= damage;
			gp.player.invincible = true;
		}
	}
	
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if (this.type  == type_monster && contactPlayer == true) {
			damagePlayer(attack, knockBackPower);
		}
	}
	
	public void checkObjectCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
	}
	
//	public int getDetected(Entity user, Entity target[][], String targetName) {
//		
//		int index = 999;
//		
//		//Check the surroundings objects
//		int nextWorldX = user.getLeftX();
//		int nextWorldY = user.getTopY();
//		
//		switch (user.direction) {
//		case "up":
//			nextWorldY = user.getTopY() - gp.player.speed;
//			break;
//		case "down":
//			nextWorldY = user.getBottomY() + gp.player.speed;
//			break;
//		case "left":
//			nextWorldX = user.getLeftX() - gp.player.speed;
//			break;
//		case "right":
//			nextWorldX = user.getRightX() + gp.player.speed	;
//			break;
//		}
//		int col = nextWorldX/gp.tileSize;
//		int row = nextWorldY/gp.tileSize;
//		
//		for (int i = 0; i < target[1].length; i++) {
//			if (target[gp.currentMap][i] != null) {
//				if (target[gp.currentMap][i].getCol() == col &&
//						target[gp.currentMap][i].getRow() == row &&
//						target[gp.currentMap][i].name.equals(targetName)) {
//					
//					index = i ;
//					break;
//				}
//			}
//		}
//		return index;	
//	}
	
	public String getOppositeDirection(String direction) {
		String oppositeDirection = "";
		switch(direction) {
		case "up":
			oppositeDirection = "down";
			break;
		case "down":
			oppositeDirection = "up";
			break;
		case "left":
			oppositeDirection = "right";
			break;
		case "right":
			oppositeDirection = "left";
			break;
		}
		return oppositeDirection;
	}
	
	public void moveTowardPlayer(int interval) {
		
		actionLockCounter++;
		if (actionLockCounter >= interval) {
			
			if (getXdistance(gp.player) > getYdistance(gp.player) ) {
				if (gp.player.getCenterX() < getCenterX()) {
					direction = "left";
				}
				else {
					direction = "right";
				}
			}
			else if (getXdistance(gp.player) < getYdistance(gp.player)) {
				if (gp.player.getCenterY() < getCenterY()) {
					direction = "up";
				}
				else {
					direction = "down";
				}
			}
			actionLockCounter = 0;
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {
			changeAlpha(g2, 0f);
		}
		else if(dyingCounter > i && dyingCounter <= i*2) {
			changeAlpha(g2, 1f);
		}
		else if(dyingCounter > i*2 && dyingCounter <= i*3) {
			changeAlpha(g2, 0f);
		}
		else if(dyingCounter > i*3 && dyingCounter <= i*4) {
			changeAlpha(g2, 1f);
		}
		else if(dyingCounter > i*4 && dyingCounter <= i*5) {
			changeAlpha(g2, 0f);
		}
		else if(dyingCounter > i*5 && dyingCounter <= i*6) {
			changeAlpha(g2, 1f);
		}
		else if(dyingCounter > i*6 && dyingCounter <= i*7) {
			changeAlpha(g2, 0f);
		}
		else if(dyingCounter > i*7 && dyingCounter <= i*8) {
			changeAlpha(g2, 1f);
		}
		else if(dyingCounter > i*8) {
			alive = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public void fireSprite() {}
	
	public void checkStopChasing(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}
		}
	}
	
	public void checkStartChasing(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}
		}
	}
	
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if (gp.pFinder.search()) {
			
			//Next worldX & worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			//Entity's solidArea position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize*scale) {
				direction = "up";
			}
			else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize*scale) {
				direction = "down";
			}
			else if ((enTopY >= nextY && enBottomY < nextY + gp.tileSize) || (enTopY <= nextY && enBottomY > nextY)) {
				if (enLeftX > nextX) {
					direction = "left";
				}
				else if (enLeftX < nextX) {
					direction = "right";
				}
			}
			//exception cases
			else if (enTopY > nextY && enLeftX > nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn) {
					direction = "left";
				}
			}
			else if (enTopY > nextY && enLeftX < nextX) {
				direction = "up";
				checkCollision();
				if (collisionOn) {
					direction = "right";
				}
			}
			else if (enTopY < nextY && enLeftX > nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn) {
					direction = "left";
				}
			}
			else if (enTopY < nextY && enLeftX < nextX) {
				direction = "down";
				checkCollision();
				if (collisionOn) {
					direction = "right";
				}
			}
			
			//if reaches goal, stop the search
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if (nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}	
	}
	
	public Color getParicleColor() {
		return null;
	}
	
	public int getParticleSize() {
		int size = 0;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	
	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.getParicleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
		gp.particleList.add(p1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
		gp.particleList.add(p2);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
		gp.particleList.add(p3);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
		gp.particleList.add(p4);
	}
	
	public void checkShoot(int rate, int shotInterval) {
		
		int i = new Random().nextInt(rate);
		if (i == 0 && projectile.alive == false  && shotCounter == shotInterval) {
			shotCounter = 0;
			spriteCounter = 0;
			shooting = true;
			projectile.alive = true;
			spriteNum = 1;
		}
	}
	
	public void attacking() {
		
		spriteCounter++;
		
		if (spriteCounter <= motion1_duration) {
			spriteNum = 1;
			
			//Save the current x and y and solidArea
		}
		else if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
			spriteNum = 2;
			
			//Adjust player's x and y and solidArea to the AttackArea
			switch (direction) {
			case "up":
				attackArea.x = currentWeapon.getAttackUpPosition()[0];
				attackArea.y = currentWeapon.getAttackUpPosition()[1];
				break;
			case "down":
				attackArea.x = currentWeapon.getAttackDownPosition()[0];
				attackArea.y = currentWeapon.getAttackDownPosition()[1];
				break;
			case "right":
				attackArea.x = currentWeapon.getAttackRightPosition()[0];
				attackArea.y = currentWeapon.getAttackRightPosition()[1];
				attackArea.width = attackAreaDefaultHeight;
				attackArea.height = attackAreaDefaultWidth;
				break;
			case "left":
				attackArea.x = currentWeapon.getAttackLeftPosition()[0];
				attackArea.y = currentWeapon.getAttackLeftPosition()[1];	
				attackArea.width = attackAreaDefaultHeight;
				attackArea.height = attackAreaDefaultWidth;
				break;
			}
			

			if(type == type_monster) {
				if (gp.cChecker.checkAttackPlayer(this)) {
					damagePlayer(attack, currentWeapon.knockBackPower);
				}
			}
			else {
				//check monster collision with updated position
				int monsterIndex = gp.cChecker.checkAttackEntity(this, gp.monster);
				gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
				
				//interactive tile
				int iTileIndex = gp.cChecker.checkAttackEntity(this, gp.iTile);
				gp.player.damageInteractiveTile(iTileIndex);
//				
//				//projectile
//				int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
//				gp.player.damageProjectile(projectileIndex);
			}
		}
		else if (spriteCounter > motion2_duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
			//restore original data
			attackArea.width = attackAreaDefaultWidth;
			attackArea.height = attackAreaDefaultHeight;
		}
	}
	
	public void shooting() {
		if (type == type_monster || currentShootWeapon.type == type_unic) {
			spriteCounter++;
			
			if (spriteCounter < shoot_motion1_duration) {
				spriteNum = 1;
			}
			else if (spriteCounter == shoot_motion1_duration) {
				spriteNum = 2;
				
				projectile.set(worldX + gp.tileSize/2, worldY + gp.tileSize/2, direction, true, this);
				for (int i = 0; i < gp.projectile[1].length; i++) {
					if (gp.projectile[gp.currentMap][i] == null) {
						gp.projectile[gp.currentMap][i] = projectile;
						break; 
					}
				}
				projectile.subtractRessource(this);
				if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize*5 &&
						worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
						worldY > gp.player.worldY - gp.player.screenY - gp.tileSize*5 &&
						worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
					projectile.playShootSound();
				}
				
				
			}
			else if (spriteCounter > shoot_motion1_duration && spriteCounter < shoot_motion2_duration) {
				spriteNum = 2;
			}
			else if (spriteCounter >= shoot_motion2_duration) {
				spriteNum = 1;
				spriteCounter = 0;
				shooting = false;
				if (this == gp.player) {
					gp.keyH.shotKeyPressed = false;
				}
			}
		}
		else if (currentShootWeapon.type == type_automatic) {
			if (gp.keyH.shotKeyPressed && projectile.haveRessource(this)) {
				spriteCounter++;
				if (spriteCounter > shoot_motion1_duration) {
					projectile.set(worldX + gp.tileSize/2, worldY + gp.tileSize/2, direction, true, this);
					projectile.alive = true;
					spriteCounter = 0;
					projectile.playShootSound();
				}
			}
			else {
				spriteCounter = 0;
				spriteNum = 1;
				shooting = false;
				projectile.alive = false;
			}
			
		}
		
	}
	
	public void checkAttack(int rate, int straight, int horizontal) {
		
		boolean targetInRange = false;
		int xDis = getXdistance(gp.player);
		int yDis = getYdistance(gp.player);
		
		switch(direction) {
		case "up":
			if (gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "down":
			if (gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "left":
			if (gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		case "right":
			if (gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}
		
		if (targetInRange) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attack  = getAttack();
				gp.playSE(6);
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotCounter = 0;
			}
		}
	}
	
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction; 
		target.speed += knockBackPower;
		target.knockBack = true;
	}
	
	public void update() {
		
		if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize*5 &&
				worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
				worldY > gp.player.worldY - gp.player.screenY - gp.tileSize*5 &&
				worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
			if (knockBack) {
				
				checkCollision();
				if (collisionOn == true) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
				else if (collisionOn == false) {
					switch(knockBackDirection) {
					case "up":
						worldY -= speed; break;
					case "down":
						worldY += speed; break;
					case "left":
						worldX -= speed; break;
					case "right":
						worldX += speed; break; 
					}
				}
				
				knockBackCounter++;
				if (knockBackCounter >= 10) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
			}
			else if (attacking) {
				attacking();
			}
			else if (shooting) {
				shooting();
			}
			else {
					
				setAction();
				checkCollision();

				if (collisionOn == false) {
					
					if (standing == false) {
						switch(direction) {
						case "up":
							worldY -= speed; break;
						case "down":
							worldY += speed; break;
						case "left":
							worldX -= speed; break;
						case "right":
							worldX += speed; break; 
						}
					}

					
					spriteCounter++;

					if (spriteCounter > 16 && !(type == type_monster && standing)) {
						switch(spriteNum) {
						case 0:
							spriteNum = 1;
							break;
						case 1:
							spriteNum = 6;
							break;
						case 2:
							spriteNum = 0;
							break;
						case 6:
							spriteNum = 2;
							break;
						}
						spriteCounter = 0;
					}
				}
				else {
					
					spriteNum = 0;
				}
			}
			
			// INVICIBLE
			if (invincible) {
				invincibleCounter++;
				if (invincibleCounter >= 40) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
			
			if (shotCounter < 30) {
				shotCounter++;
			}
			if (offBalance) {
				offBalanceCounter++;
				if (offBalanceCounter > 120) {
					offBalance = false;
					offBalanceCounter = 0;
				}
			}
			
			//KRAMULFIRE
			if (type == type_monster && this.name == "Kramul") {
				if (kramulFire.alive) {
					kramulFire.update();
				}
				else if (kramulBubble.alive){
					kramulBubble.update();
				}
				else if (!kramulBubble.alive) {
					kramulFire.alive = true;
					standing = false;
				}
			}
		}
		
		
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize*5 &&
			worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
			worldY > gp.player.worldY - gp.player.screenY - gp.tileSize*5 &&
			worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
			
			BufferedImage image = null;
			int tempScreenX = screenX;
			int tempScreenY = screenY;
			
			
			switch(direction) {
			case "up":
				if (attacking == false && shooting == false) {
					if (spriteNum == 1) {
						image = up1;
					}
					else if (spriteNum == 2) {
						image = up2;
					}
					else if (spriteNum == 0 || spriteNum == 6) {
						image = up0;
					}
				}
				else if (attacking) {
					tempScreenY = screenY - up1.getHeight();
					if (spriteNum == 1) {
						image = attackUp1;
					}
					else if (spriteNum == 2) {
						image = attackUp2;
					}
				}
				else if (shooting) {
					if (spriteNum == 1) {
						image = shootUp1;
					}
					else if (spriteNum == 2) {
						image = shootUp2;
					}
				}
				break;
			case "down":
				if (attacking == false && shooting == false) {
					if (spriteNum == 1) {
						image = down1;
					}
					else if (spriteNum == 2) {
						image = down2;
					}
					else if (spriteNum == 0 || spriteNum == 6) {
						image = down0;
					}
				}
				else if (attacking) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					else if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				else if (shooting) {
					if (spriteNum == 1) {
						image = shootDown1;
					}
					else if (spriteNum == 2) {
						image = shootDown2;
					}
				}
				break;
			case "right":
				if (attacking == false && shooting == false) {
					if (spriteNum == 1) {
						image = right1;
					}
					else if (spriteNum == 2) {
						image = right2;
					}
					else if (spriteNum == 0 || spriteNum == 6) {
						image = right0;
					}
				}
				else if (attacking) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					else if (spriteNum == 2) {
						image = attackRight2;
					}
				}
				else if (shooting) {
					if (spriteNum == 1) {
						image = shootRight1;
					}
					else if (spriteNum == 2) {
						image = shootRight2;
					}
				}
				break;
			case "left":
				if (attacking == false && shooting == false) {
					if (spriteNum == 1) {
						image = left1;
					}
					else if (spriteNum == 2) {
						image = left2;
					}
					else if (spriteNum == 0 || spriteNum == 6) {
						image = left0;
					}
				}
				else if (attacking) {
					tempScreenX = screenX - left1.getWidth();
					if (spriteNum == 1) {
						image = attackLeft1;
					}
					else if (spriteNum == 2) {
						image = attackLeft2;
					}
				}
				else if (shooting) {
					if (spriteNum == 1) {
						image = shootLeft1;
					}
					else if (spriteNum == 2) {
						image = shootLeft2;
					}
				}
				break;
			}
			
			
			//Monster HP bar
			if (type == 2 && hpbarOn == true) {
				double oneScale = (double)gp.tileSize/maxLife;
				double hpbarValue = oneScale * life;
				
				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 11, gp.tileSize + 2, 12);
				
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - 10, (int)hpbarValue, 10);
				
				hpbarCounter++;
				
				if (hpbarCounter > 600) {
					hpbarCounter = 0;
					hpbarOn = false;
				}
				
			}
			

			if (invincible) {
				hpbarOn = true; 
				hpbarCounter = 0; 
				changeAlpha(g2, 0.4f);
			}
			
			if (dying == true) {
				dyingAnimation(g2);
			}
			
			
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			
			//KRAMULFIRE
			if (type == type_monster && name == "Kramul") {
				if (kramulFire.alive) {
					kramulFire.draw(g2);
				}
				else if (kramulBubble.alive) {
					kramulBubble.draw(g2);
				}
				
				
			}
			
			
			//Reset alpha
			
			changeAlpha(g2, 1f);	
			
			//Debug collision
			if (gp.keyH.debugOn) {
				g2.setColor(new Color(50, 60, 86, 70));
				g2.fillRect(tempScreenX + hitBox.x, tempScreenY + hitBox.y, hitBox.width, hitBox.height);
			}
		}		
	}
	

	
}
