package object;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class OBJ extends Entity{
	
	GamePanel gp;
	
	public OBJ(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.drawImage(down0, screenX, screenY, gp.tileSize*scale, gp.tileSize*scale, null);
	}
	
	public void setDialogue() {}
	
	public void setImage() {}
	
	public void setValue(int value) {}
	
	public void changeValues(String name2, String name3, int value) {}

}
