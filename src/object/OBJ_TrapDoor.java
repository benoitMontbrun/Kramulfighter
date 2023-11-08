package object;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;

public class OBJ_TrapDoor extends Entity{

	GamePanel gp;
	
	public static final String objName = "TrapDoor";
	public String key;
	
	public OBJ_TrapDoor (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/trapdoor", gp.tileSize, gp.tileSize*2);
		image2 = setup("/objects/trapdoor_opened", gp.tileSize, gp.tileSize*2);
		down0 = image;
		collision = true;
		opened = false;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		solidArea.x = 0;
		solidArea.y = 24*scale;
		solidArea.width = 24*scale;
		solidArea.height = 24*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Il te faut la " + key + ".";
		dialogues[1][0] = "Vous utilisez la " + key + " et\nouvrez la trappe.";
	}
	
	public void setKey(String key) {
		if (key == null) {
			dialogues[0][0] = "Cette porte n'a pas de serrure.";
		}
		else {
			this.key = key;
			setDialogue();
		}
	}
	
	public void interact() {
		
		int i = gp.player.searchItemInInventory(OBJ_Key.objName, key);
		if (i != 999) { 
			gp.player.inventory.remove(i);
			setOpened();
			startDialogue(this, 1);
		}
		else {
			startDialogue(this, 0);
		}
	}
	
	public void setOpened() {
		if (gp.player.getBottomY() <= worldY + gp.tileSize) {
			gp.player.worldY -= gp.tileSize;
		}
		gp.playSE(5);
		down0 = image2;
		opened = true;
		

		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 24*scale;
		solidArea.height = 24*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
