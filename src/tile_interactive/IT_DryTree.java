package tile_interactive;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Arrow;
import object.OBJ_Coin;
import object.OBJ_Heart;

public class IT_DryTree extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		scale = 2;
		
		hitBox = new Rectangle();
		hitBox.x = 12*scale;
		hitBox.y = 27*scale;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 24*scale;
		hitBox.height = 21*scale;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = gp.tileSize*scale;
		solidArea.height = gp.tileSize*scale;
		
		
		down0 = setup("/tiles_interactive/dry_tree", gp.tileSize*2, gp.tileSize*2);
		destructible = true;
		life = 3;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return entity.currentWeapon.type == type_axe;
	}
	
	public void playSE() {
		gp.playSE(8);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Stump(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
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
	
	public void checkDrop() {
		int i = new Random().nextInt(10);
		
		if (i >= 1 && i <= 5) {
			OBJ_Arrow arrows= new OBJ_Arrow(gp, 0, 0);
			arrows.setValue(i);
			dropItem(arrows);
		}

	}
	
	
}