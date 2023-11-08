package projectile;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Bubble extends Projectile{
	
	GamePanel gp;
	
	
	public Bubble(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Bubble";
		speed = 10;
		maxLife = 200;
		life = maxLife;
		attack = 0;
		knockBackPower = 0;
		useCost = 1;
		alive = false;
		getImage();
		spriteNum = 1;
		
		attackArea = new Rectangle();
		attackArea.x = 0;
		attackArea.y = 0;
		attackAreaDefaultX = attackArea.x;
		attackAreaDefaultY = attackArea.y;
		attackArea.width = 24;
		attackArea.height = 24;
		attackAreaDefaultWidth = attackArea.width;
		attackAreaDefaultHeight = attackArea.height;
	}
	
	public void getImage() {
		
		BufferedImage image = setup("/projectile/little_bubble", gp.tileSize, gp.tileSize);
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		right1 = image;
		right2 = image;
		left1 = image;
		left2 = image;
	}
	
	@Override
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		Bubble bubble = new Bubble(gp);
		bubble.changeImage();
		bubble.worldX = worldX;
		bubble.worldY = worldY;
		bubble.direction = direction;
		bubble.life = bubble.maxLife;
		bubble.alive = alive;
		bubble.user = user;

		for (int i = 0; i < gp.projectile[1].length; i++) {
			if (gp.projectile[gp.currentMap][i] == null) {
				gp.projectile[gp.currentMap][i] = bubble;
				break; 
			}
		}
		subtractRessource(user);
	}
	
	public boolean haveRessource(Entity user) {
		return user.bubbleNum >= useCost;
	}
	
	public void subtractRessource(Entity user) {
		user.bubbleNum -= useCost;
	}
	
	public void playShootSound() {
		gp.playSE(23);
	}
	
	public Color getParticleColor() {
		Color color = new Color(60, 90, 90);
		return color;
	}
	
	public int getParticleSize() {
		int size = 5;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
	
	public void changeImage() {
		BufferedImage image;
		int n = new Random().nextInt(5);
		if (n == 1) {
			image = setup("/projectile/bubble", gp.tileSize, gp.tileSize);
		}
		else {
			image = setup("/projectile/little_bubble", gp.tileSize, gp.tileSize);
		}
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		right1 = image;
		right2 = image;
		left1 = image;
		left2 = image;
	}
	
	public void changeMovement() {
		if (speed > 1) {
			speed--;
		}
		int n = new Random().nextInt(50);
		if (n == 1) {
			direction = "up";
		}
		else if (n == 2) {
			direction = "down";
		}
		else if (n == 3) {
			direction = "right";
		}
		else if (n == 4) {
			direction = "left";
		}
		
	}
}






