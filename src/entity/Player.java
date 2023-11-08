package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Arrow;
import object.OBJ_Bottle;
import object.OBJ_Bow;
import object.OBJ_BubblePistol;
import projectile.Arrow;
import projectile.Bubble;

public class Player extends Entity{
	
	public static final String xavier = "Xavier";
	public static final String quitterie = "Quitterie";
	public static final String benoit = "Benoît";
	public static final String etienne = "Etienne";
	
	
	public String name = "Xavier";
	
	KeyHandler keyH;
	int spawnX, spawnY;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	public boolean hasMap[] = {false, false, false, false};
	
	

	public Player(GamePanel gp, KeyHandler keyH) {	
		super(gp);
		this.keyH = keyH; 
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.x = 16*scale;
		solidArea.y = 28*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16*scale;
		solidArea.height = 19*scale;
		
		hitBox = new Rectangle();
		hitBox.x = 16*scale;
		hitBox.y = 4*scale;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 16*scale;
		hitBox.height = 44*scale;
		
		attackArea = new Rectangle();
		attackArea.x = 0;
		attackArea.y = 0;
		attackAreaDefaultX = attackArea.x;
		attackAreaDefaultY = attackArea.y;
		attackArea.width = 10*scale;
		attackArea.height = 20*scale;
		attackAreaDefaultWidth = attackArea.width;
		attackAreaDefaultHeight = attackArea.height;
		
		setDefaultValues(); 
	}
	
	public void setDefaultValues() {

		setDefaultPosition();
		setSpawnPoint(worldX, worldY);

		defaultSpeed = 4;
		speed = defaultSpeed;
		direction = "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		ammo = 10;
		exp = 0;
		nextLevelExp = 5;
		coin = 0;
//		currentWeapon = new Sword_Normal(gp);
//		currentShield = new Shield_Wood(gp);
//		currentLight = new Lantern(gp);
//		attack = getAttack();
//		defense = getDefense();
		
		getImage();
		
		if (currentWeapon != null) {
			getAttackImage();
		}

//		getGuardImage();
//		setItems();
//		setDialogue();
 
	}
	
	public void setSpawnPoint(int spawnX, int spawnY) {
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	public void setSpawnPosition() {
		worldX = spawnX;
		worldY = spawnY;
		direction = "down";
	}
	
	
	public void setDefaultPosition() {
		
		gp.currentMap = 2;
		worldX = gp.tileSize * 75;
		worldY = gp.tileSize * 43;
//		gp.currentMap = 0;
//		worldX = gp.tileSize * 27;
//		worldY = gp.tileSize * 92;
		direction = "down";
	}
	
	public void restoreStatus() {
		life = maxLife;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
		
	}
	
	public void getImage() {
		
		down0 = setup("/xavier/xavier_down", gp.tileSize * scale, gp.tileSize * scale);
		up0 = setup("/xavier/xavier_up", gp.tileSize * scale, gp.tileSize * scale);
		left0 = setup("/xavier/xavier_left", gp.tileSize * scale, gp.tileSize * scale);
		right0 = setup("/xavier/xavier_right", gp.tileSize * scale, gp.tileSize * scale);
		
		down1 = setup("/xavier/xavier_down_1", gp.tileSize * scale, gp.tileSize * scale);
		up1 = setup("/xavier/xavier_up_1", gp.tileSize * scale, gp.tileSize * scale);
		left1 = setup("/xavier/xavier_left_1", gp.tileSize * scale, gp.tileSize * scale);
		right1 = setup("/xavier/xavier_right_1", gp.tileSize * scale, gp.tileSize * scale);
		
		down2 = setup("/xavier/xavier_down_2", gp.tileSize * scale, gp.tileSize * scale);
		up2 = setup("/xavier/xavier_up_2", gp.tileSize * scale, gp.tileSize * scale);
		left2 = setup("/xavier/xavier_left_2", gp.tileSize * scale, gp.tileSize * scale);
		right2 = setup("/xavier/xavier_right_2", gp.tileSize * scale, gp.tileSize * scale);
	}
	
	public void getAttackImage() {
		if (currentWeapon.type == type_bat) {
			attackDown1 = setup("/xavier/xavier_attack_down_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackDown2 = setup("/xavier/xavier_attack_down_2", gp.tileSize*scale, gp.tileSize*scale*2);
			attackUp1 = setup("/xavier/xavier_attack_up_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackUp2 = setup("/xavier/xavier_attack_up_2", gp.tileSize*scale, gp.tileSize*scale*2);
			attackRight1 = setup("/xavier/xavier_attack_right_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackRight2 = setup("/xavier/xavier_attack_right_2", gp.tileSize*scale*2, gp.tileSize*scale);
			attackLeft1 = setup("/xavier/xavier_attack_left_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackLeft2 = setup("/xavier/xavier_attack_left_2", gp.tileSize*scale*2, gp.tileSize*scale);
		}
		else if (currentWeapon.type == type_axe) {
			attackDown1 = setup("/xavier/xavier_attack_axe_down_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackDown2 = setup("/xavier/xavier_attack_axe_down_2", gp.tileSize*scale, gp.tileSize*scale*2);
			attackUp1 = setup("/xavier/xavier_attack_axe_up_1", gp.tileSize*scale, gp.tileSize*scale*2);
			attackUp2 = setup("/xavier/xavier_attack_axe_up_2", gp.tileSize*scale, gp.tileSize*scale*2);
			attackRight1 = setup("/xavier/xavier_attack_axe_right_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackRight2 = setup("/xavier/xavier_attack_axe_right_2", gp.tileSize*scale*2, gp.tileSize*scale);
			attackLeft1 = setup("/xavier/xavier_attack_axe_left_1", gp.tileSize*scale*2, gp.tileSize*scale);
			attackLeft2 = setup("/xavier/xavier_attack_axe_left_2", gp.tileSize*scale*2, gp.tileSize*scale);
		}
	}
	
	public void getShootImage() {
		if (currentShootWeapon.name == OBJ_Bow.objName) {
			shootDown1 = setup("/xavier/xavier_bow_down_1", gp.tileSize*scale, gp.tileSize*scale);
			shootDown2 = setup("/xavier/xavier_bow_down_2", gp.tileSize*scale, gp.tileSize*scale);
			shootUp1 = setup("/xavier/xavier_bow_up_1", gp.tileSize*scale, gp.tileSize*scale);
			shootUp2 = setup("/xavier/xavier_bow_up_2", gp.tileSize*scale, gp.tileSize*scale);
			shootRight1 = setup("/xavier/xavier_bow_right_1", gp.tileSize*scale, gp.tileSize*scale);
			shootRight2 = setup("/xavier/xavier_bow_right_2", gp.tileSize*scale, gp.tileSize*scale);
			shootLeft1 = setup("/xavier/xavier_bow_left_1", gp.tileSize*scale, gp.tileSize*scale);
			shootLeft2 = setup("/xavier/xavier_bow_left_2", gp.tileSize*scale, gp.tileSize*scale);
		}
		else if (currentShootWeapon.name == OBJ_BubblePistol.objName) {
			shootDown1 = setup("/xavier/xavier_bubble_pistol_down", gp.tileSize*scale, gp.tileSize*scale);
			shootUp1 = setup("/xavier/xavier_bubble_pistol_up", gp.tileSize*scale, gp.tileSize*scale);
			shootLeft1 = setup("/xavier/xavier_bubble_pistol_left", gp.tileSize*scale, gp.tileSize*scale);
			shootRight1 = setup("/xavier/xavier_bubble_pistol_right", gp.tileSize*scale, gp.tileSize*scale);
		}
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
				break;
			}
		}
		return currentWeaponSlot;
	}
	
	public void interactNPC(int i) {
		
		if (i != 999) {
			if (gp.keyH.enterPressed) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak(); 
				
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		
		int itemIndex = 999;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name == itemName) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public int searchItemInInventory(String itemName, String itemName2) {
		
		int itemIndex = 999;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name == itemName && inventory.get(i).name2 == itemName2) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean canObtainItem(Entity item) {
		
		boolean canObtain = false;
		
		Entity newItem = gp.eGenerator.getObject(item.name);
		newItem.name2 = item.name2;
		newItem.setDescription();
		
		if (newItem.name == OBJ_Bottle.objName) {
			newItem.changeValues(item.name2, item.name3, item.value);
		}

		
		//CHECK IF STACKABLE
		if (newItem.stackable) {
			int index = searchItemInInventory(newItem.name);
			if (index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else { //New item : need to check vacancy
				if (inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		}
		else if (newItem.type == type_pickupOnly) {
			newItem.setValue(item.value);
			newItem.use(this);
			canObtain = true;
		}
		else {
			if (inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
		}
		return canObtain;
	}
	
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		if (itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			if (selectedItem.type == type_bat || selectedItem.type == type_axe || selectedItem.type == type_pickaxe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
			}
			else if (selectedItem.name == OBJ_Bow.objName) {
				currentShootWeapon = selectedItem;
				getShootImage();
				projectile = new Arrow(gp);
				shoot_motion1_duration = selectedItem.motion1_duration;
				shoot_motion2_duration = selectedItem.motion2_duration;
			}
			else if (selectedItem.name == OBJ_BubblePistol.objName) {
				currentShootWeapon = selectedItem;
				getShootImage();
				projectile = new Bubble(gp);
				shoot_motion1_duration = selectedItem.motion1_duration;
				shoot_motion2_duration = selectedItem.motion2_duration;
			}
			else if (selectedItem.type == type_consumable) {
				if (selectedItem.use(this)) {
					if (selectedItem.amount > 1) {
						selectedItem.amount--;
					}
					else {
						inventory.remove(itemIndex);
					}
				}
			}
			else if (selectedItem.type == type_light) {
				if (currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			
		}
	}
	
	public void contactMonster(int i) {
		if(i != 999) {
			if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(4);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if (damage < 1) {
					damage = 1;
				}
		
				life -= damage;
				invincible = true;
				transparent = true;
			}

		}
	}
	
	public void pickUpObject(int i) {
		
		if (i != 999) {
			
			// PICKUP ONLY ITEMS
			if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			//OBSTACLE
			else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
				if (gp.keyH.enterPressed) {
					attackCanceled = true;      
					gp.obj[gp.currentMap][i].interact();
				}
			}
			//MOVEABLE
			else if (gp.obj[gp.currentMap][i].type == type_moveable) {
				gp.obj[gp.currentMap][i].move(direction);
			}
			// INVENTORY ITEMS
			else {
				
				String text;
				if (canObtainItem(gp.obj[gp.currentMap][i])) {
					gp.playSE(3);
					text = "Tu obtiens " + gp.obj[gp.currentMap][i].determinant + " " + gp.obj[gp.currentMap][i].name2 + "!";
					gp.obj[gp.currentMap][i] = null;
					gp.ui.addMessage(text);	
				}
		
				
				
			}
		}			
	}
	
	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		if (i != 999) {
			if (gp.monster[gp.currentMap][i].invincible == false) {
				
				
				if (knockBackPower > 0) {
					setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower );
				}
				
				if (gp.monster[gp.currentMap][i].offBalance) {
					attack *= 3;
				}
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if (damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("Tu as tué " + gp.monster[gp.currentMap][i].determinant + " "
							+ gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp + "!");
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		} 
	}
	
	public void setDialogue() {
		dialogues[0][0] = "LEVEL UP\n" + "Tu es niveau " + level + "!\n" + "Tu te sens plus fort.";
	}
	
	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			attack = getAttack();
			exp = 0;
			
			gp.playSE(26);
			gp.gameState = gp.dialogueState;
		
			setDialogue();
			startDialogue(this, 0);
		}
	}
	
	public void damageInteractiveTile(int i) {
		if (i != 999 && gp.iTile[gp.currentMap][i].invincible == false && gp.iTile[gp.currentMap][i].isCorrectItem(this)) {
			
			if (gp.iTile[gp.currentMap][i].destructible) {
				gp.iTile[gp.currentMap][i].playSE();
				gp.iTile[gp.currentMap][i].life--;
				gp.iTile[gp.currentMap][i].invincible = true;
				
				generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
				
				if (gp.iTile[gp.currentMap][i].life <= 0 ) {
					gp.iTile[gp.currentMap][i].checkDrop();
					gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
				}
			}
			
			else {
				generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
				gp.iTile[gp.currentMap][i].invincible = true;
				gp.iTile[gp.currentMap][i].playSE();
			}
			
			

		}
	}

	public void update() {
		
		if (knockBack) {
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, true);
			gp.cChecker.checkEntity(this, gp.npc);
			gp.cChecker.checkEntity(this, gp.monster);
			gp.cChecker.checkEntity(this, gp.iTile);
			
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
		else if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.enterPressed) {
			
			if (keyH.upPressed) {
				direction = "up";
			}
			else if (keyH.downPressed) {
				direction = "down";
			}
			else if (keyH.leftPressed) {
				direction = "left";
			}
			else if (keyH.rightPressed) {
				direction = "right";
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK INTERACTIVE TILE COLLISION
			gp.cChecker.checkEntity(this, gp.iTile);
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
			
			// CHECK AREA
			gp.eHandler.checkChangeArea();
			
			//If collision is false, player can move.
			if (collisionOn == false && keyH.enterPressed == false) {
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
			
			if (keyH.enterPressed && attackCanceled == false && currentWeapon != null) {
				gp.playSE(6);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			guarding = false;
			guardCounter = 0;
			
			spriteCounter++;
			if (spriteCounter > 8) {
				if (spriteNum == 0 ) {
					spriteNum = 1;
				}
				else if (spriteNum == 1 ) {
					spriteNum = 3;
				}
				else if (spriteNum == 3) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 0;
				}
				spriteCounter = 0;
			}
		}
		else if (standCounter <= 60){
			standCounter++;
			if (standCounter == 60) {
				spriteNum = 0;
				standCounter = 0;
			}
			guarding = false;
			guardCounter = 0;
		}
		
		//PROJECTILE
		if (gp.keyH.shotKeyPressed && currentShootWeapon != null && projectile.alive == false && 
			projectile.haveRessource(this)) {
			
			shotCounter = 0;
			spriteCounter = 0;
			currentShootWeapon.playBendSound();
			shooting = true;
			projectile.alive = true;
			spriteNum = 1;
		}
		
		
		
		// INVICIBLE
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter >= 60) {
				invincible = false;
				transparent = false;
				invincibleCounter = 0;
			}
		}
		

		//DEBUG : GOD MOD
 		if (!keyH.godModeOn) {
 			if (life <= 0) {
 	 			gp.gameState = gp.gameOverState;
 	 			exp = 0;
 	 			gp.ui.commandNum = -1;
 	 			gp.stopMusic();
 	 			gp.playSE(25);
 	 		}
 		}
	}
	
	public void draw(Graphics2D g2) {
		

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
				else if (spriteNum == 0 || spriteNum == 3) {
					image = up0;
				}
				
			}
			else if (attacking) {
				tempScreenY = screenY - gp.tileSize*scale;
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
			if (guarding) {
				image = guardUp;
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
				else if (spriteNum == 0 || spriteNum == 3) {
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
			if (guarding) {
				image = guardDown;
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
				else if (spriteNum == 0 || spriteNum == 3) {
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
			if (guarding) {
				image = guardRight;
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
				else if (spriteNum == 0 || spriteNum == 3) {
					image = left0;
				}
			}
			else if (attacking) {
				tempScreenX = screenX - gp.tileSize*scale;
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
			if (guarding) {
				image = guardLeft;
			}
			break;
		}
		
		
		if (transparent) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
	
		
		//Reset alpha
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		
		
		//Debug collision
		if (gp.keyH.debugOn) {
			g2.setColor(new Color(255, 128, 0, 70));
			g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			
			if(attacking) {
				g2.fillRect(screenX + attackArea.x, screenY + attackArea.y, attackArea.width, attackArea.height);
			}
			
		}	
	}

	
	
	
	
	
}
