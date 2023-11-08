package main;

import java.awt.Rectangle;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	public static final String attackArea = "attack area";
	public static final String solidArea = "solid area";
	public static final String hitBox = "hit box";
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.getLeftX();
		int entityRightWorldX = entity.getRightX();
		int entityTopWorldY = entity.getTopY();
		int entityBottomWorldY = entity.getBottomY();
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		//Use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if (entity.knockBack) {
			direction = entity.knockBackDirection;
		}
		
		switch(direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap] [entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		//Use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if (entity.knockBack) {
			direction = entity.knockBackDirection;
		}
		
		for (int i = 0; i < gp.obj[1].length; i++) {
			if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i] != entity) {
				
				// Get entity's solidArea position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get the object's solid Area position
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
				
				switch (direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;

					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if (gp.obj[gp.currentMap][i].collision) {
						entity.collisionOn = true;
					}
					if (player) {
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	
	//NPC OR MONSTER
	public int checkEntity(Entity entity, Entity[][] target) {
		
		int index = 999;
		
		//Use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if (entity.knockBack) {
			direction = entity.knockBackDirection;
		}
		
		for (int i = 0; i < target[1].length; i++) {
			if (target[gp.currentMap][i] != null) {
				
				if (checkAreaCollision(entity, target[gp.currentMap][i], entity.solidArea, target[gp.currentMap][i].solidArea,
						true)) {
					index  = i;
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	
	
	//NPC OR MONSTER ATTACK
	public int checkAttackEntity(Entity entity, Entity[][] target) {
		
		int index = 999;
		
		//Use a temporal direction when it's being knockbacked
		String direction = entity.direction;
		if (entity.knockBack) {
			direction = entity.knockBackDirection;
		}
		
		for (int i = 0; i < target[1].length; i++) {
			if (target[gp.currentMap][i] != null) {
				
				if (checkAreaCollision(entity, target[gp.currentMap][i], entity.attackArea, target[gp.currentMap][i].hitBox,
						false)) {
					index  = i;
				}
				
//				entity.attackArea.x = entity.attackAreaDefaultX;
//				entity.attackArea.y = entity.attackAreaDefaultY;
				entity.attackArea.x = entity.attackArea.x - entity.worldX;
				entity.attackArea.y = entity.attackArea.y - entity.worldY;
				target[gp.currentMap][i].hitBox.x = target[gp.currentMap][i].hitBoxDefaultX;
				target[gp.currentMap][i].hitBox.y = target[gp.currentMap][i].hitBoxDefaultY;
			}
			
		}
		
		return index;
	}
	
	
	
	public boolean checkAreaCollision(Entity entity, Entity target, Rectangle entityArea, Rectangle targetArea, 
			boolean anticipation) {
		
		boolean collision = false;
		
		//Use a temporal direction when it's being knockbacked
		String direction = entity.direction;	
		if (entity.knockBack) {
			direction = entity.knockBackDirection;
		}
		
		
		// Get entity's solidArea position
		entityArea.x = entity.worldX + entityArea.x;
		entityArea.y = entity.worldY + entityArea.y;
		
		// Get the object's solid Area position
		targetArea.x = target.worldX + targetArea.x;
		targetArea.y = target.worldY + targetArea.y;
		
		if (anticipation) {
			switch (direction) {
			case "up":
				entityArea.y -= entity.speed;
				break;
			case "down":
				entityArea.y += entity.speed;
				break;
			case "left":
				entityArea.x -= entity.speed;
				break;
			case "right":
				entityArea.x += entity.speed;
				break;
			}
		}

		if (entityArea.intersects(targetArea)) {
			if (target != entity) {
				entity.collisionOn = true;
				collision = true;
			}
		}
		
		return collision;
	}
	
	
	
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		if (checkAreaCollision(entity, gp.player, entity.solidArea, gp.player.solidArea, true)) {
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
	
	public boolean checkAttackPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		if (checkAreaCollision(entity, gp.player, entity.attackArea, gp.player.hitBox, false)) {
			contactPlayer = true;
		}
		
		entity.attackArea.x = entity.attackAreaDefaultX;
		entity.attackArea.y = entity.attackAreaDefaultY;
		gp.player.hitBox.x = gp.player.hitBoxDefaultX;
		gp.player.hitBox.y = gp.player.hitBoxDefaultY;
		
		return contactPlayer;
	}
	
	
	
	
	
	
	
}
