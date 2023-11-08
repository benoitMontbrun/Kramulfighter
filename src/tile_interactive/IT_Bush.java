package tile_interactive;

import java.awt.Color;
import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class IT_Bush extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_Bush(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		hitBox = new Rectangle();
		hitBox.x = 0;
		hitBox.y = 0;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = gp.tileSize;
		hitBox.height = gp.tileSize;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		
		
		down0 = setup("/tiles_interactive/bush", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 1;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == type_axe;
	}
	
	public void playSE() {
		gp.playSE(7);
	}
	
//	public InteractiveTile getDestroyedForm() {
//		InteractiveTile tile = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
//		return tile;
//	}
	
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