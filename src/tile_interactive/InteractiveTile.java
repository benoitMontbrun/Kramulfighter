package tile_interactive;




import java.awt.Graphics2D;


import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{
	
	GamePanel gp;
	public boolean destructible = false;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	
	public boolean isCorrectItem(Entity entity) {
		return false;
	}
	
	public void playSE() {}
	
	public InteractiveTile getDestroyedForm() {
		return null;
	}
	
	public void update() {
		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX > gp.player.worldX - gp.player.screenX - getWidth() &&
			worldX < gp.player.worldX + gp.player.screenX + getWidth() &&
			worldY > gp.player.worldY - gp.player.screenY - getHeight() &&
			worldY < gp.player.worldY + gp.player.screenY + getHeight()) {
			
			g2.drawImage(down0, screenX, screenY, null);	
		}
	}
}
