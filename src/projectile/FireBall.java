package projectile;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class FireBall extends Projectile{
	
	GamePanel gp;
	
	
	public FireBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "FireBall";
		speed = 5;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		knockBackPower = 4;
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
		up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
		
		down0 = down1;
	}
	
	public void playShootSound() {
		gp.playSE(24);
	}
	
	public boolean haveRessource(Entity user) {
		return false;
	}
	
	public void subtractRessource(Entity user) {}
	
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
