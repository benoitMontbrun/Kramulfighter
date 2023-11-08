package projectile;

import entity.Entity;
import main.GamePanel;
import monster.KramulBubble;

public class Projectile extends Entity{
	
	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
		
	public void update() {
		
		if (user == gp.player) {
			int monsterIndex = gp.cChecker.checkAttackEntity(this, gp.monster);
			if (monsterIndex != 999) {
				if (this.name == "Bubble" && gp.monster[gp.currentMap][monsterIndex].name == "Kramul") {
					gp.monster[gp.currentMap][monsterIndex].kramulFire.alive = false;
					gp.monster[gp.currentMap][monsterIndex].standing = true;
					gp.monster[gp.currentMap][monsterIndex].onPath = false;
					gp.monster[gp.currentMap][monsterIndex].kramulBubble = 
							new KramulBubble(gp, gp.monster[gp.currentMap][monsterIndex], 
												 gp.monster[gp.currentMap][monsterIndex].worldX,
												 gp.monster[gp.currentMap][monsterIndex].worldY);
				}
				else {
					gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
					alive = false;
				}
			}
		}
		else {
			boolean contactPlayer = gp.cChecker.checkAttackPlayer(this);
			if (gp.player.invincible == false && contactPlayer) {
				damagePlayer(attack, knockBackPower);
				generateParticle(user.projectile, user.projectile);
				alive = false;
			}
		}
		changeMovement();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		if (collisionOn) {
			alive = false;
		}
		
		switch (direction) {
		case "up":
			worldY -= speed;
			break;
		case "down":
			worldY += speed;
			break;
		case "left":
			worldX -= speed;
			break;
		case "right":
			worldX += speed;
			break;
		}
		
		life --;
		if (life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum ==1) {
				spriteNum = 2;
			}
			else if (spriteNum ==2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public boolean haveRessource(Entity user) {
		return false;
	}
	
	public void subtractRessource(Entity user) {}
	
	public void changeMovement() {}
	
	public void playShootSound() {}
}



