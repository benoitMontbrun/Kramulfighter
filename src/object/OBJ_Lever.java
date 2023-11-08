package object;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;

public class OBJ_Lever extends Entity{

	GamePanel gp;
	
	public static final String objName = "Lever";
	public Entity gate;
	
	public OBJ_Lever (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/lever", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/lever_activated", gp.tileSize, gp.tileSize);
		down0 = image;
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		solidArea.x = 0;
		solidArea.y = 12;
		solidArea.width = 24*scale;
		solidArea.height = 12*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Levier activé.";
		dialogues[1][0] = "Levier désactivé.";
	}
	
	public void setLoot(Entity gate) {
		this.gate = gate;
	}
	
	public void interact() {
		
		if (!gate.opened) {
			setOpened();
			gate.setOpened();
			startDialogue(this, 0);
		}
		else {
			setClosed();
			gate.setClosed();
			startDialogue(this, 1);
		}
	}
	
	public void setOpened() {
		down0 = image2;
	}
	
	public void setClosed() {
		down0 = image;
	}
}
