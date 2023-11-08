package tile_interactive;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class IT_Trunk extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_Trunk(GamePanel gp, int col, int row) {
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
		hitBox.width = gp.tileSize*scale;
		hitBox.height = gp.tileSize*scale;
		
		solidArea = new Rectangle();
		solidArea.x = 42*scale;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 12*scale;
		solidArea.height = gp.tileSize*scale;
		
		
		down0 = setup("/tiles_interactive/trunk", gp.tileSize*4, gp.tileSize*2);
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
		Color color = new Color(33, 40, 16);
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