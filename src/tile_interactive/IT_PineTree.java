package tile_interactive;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class IT_PineTree extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_PineTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		hitBox = new Rectangle();
		hitBox.x = 0;
		hitBox.y = 0;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = gp.tileSize*2;
		hitBox.height = gp.tileSize*2;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.x = 28*scale;
		solidArea.y = 40*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 40*scale;
		solidArea.height = 104*scale;
		
		
		down0 = setup("/tiles_interactive/pine_tree", gp.tileSize*4, gp.tileSize*6);
		destructible = false;
		life = 1;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == type_axe;
	}
	
	public void playSE() {
		gp.playSE(8);
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
