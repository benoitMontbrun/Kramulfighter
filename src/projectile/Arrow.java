package projectile;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Arrow extends Projectile{
	
	GamePanel gp;
	
	
	public Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Arrow";
		speed = 12;
		maxLife = 80;
		life = maxLife;
		attack = 1;
		knockBackPower = 3;
		useCost = 1;
		alive = false;
		getImage();
		spriteNum = 1;
		
		
		solidArea = new Rectangle();
		solidArea.x = 7;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 10;
		solidArea.height = 24;
		
		attackArea = new Rectangle();
		attackArea.x = 7;
		attackArea.y = 0;
		attackAreaDefaultX = attackArea.x;
		attackAreaDefaultY = attackArea.y;
		attackArea.width = 10;
		attackArea.height = 24;
		attackAreaDefaultWidth = attackArea.width;
		attackAreaDefaultHeight = attackArea.height;
	}
	
	public void getImage() {
		up1 = setup("/projectile/arrow_up", gp.tileSize, gp.tileSize);
		up2 = up1;
		down1 = setup("/projectile/arrow_down", gp.tileSize, gp.tileSize);
		down2 = down1;
		right1 = setup("/projectile/arrow_right", gp.tileSize, gp.tileSize);
		right2 = right1;
		left1 = setup("/projectile/arrow_left", gp.tileSize, gp.tileSize);
		left2 = left1;
		
		down0 = down1;
	}
	
	public boolean haveRessource(Entity user) {
		return user.arrowNum >= useCost;
	}
	
	public void subtractRessource(Entity user) {
		user.arrowNum -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240, 50, 0);
		return color;
	}
	
	public int getParticleSize() {
		int size = 10;
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
}
