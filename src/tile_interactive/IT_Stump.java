package tile_interactive;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class IT_Stump extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_Stump(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		scale = 2;
		
		hitBox = new Rectangle();
		hitBox.x = 0;
		hitBox.y = 0;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 0;
		hitBox.height = 0;
		
		solidArea = new Rectangle();
		solidArea.x = 14*scale;
		solidArea.y = 40*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 16*scale;
		solidArea.height = 8*scale;
		
		
		down0 = setup("/tiles_interactive/stump", gp.tileSize*2, gp.tileSize*2);
		destructible = false;
		life = 1;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == type_axe;
	}
	
	public void playSE() {
		//gp.playSE(11);
	}
	
	
	public Color getParicleColor() {
		Color color = new Color(43, 32, 16);
		return color;
	}
	
	public int getParticleSize() {
		int size = 6;
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