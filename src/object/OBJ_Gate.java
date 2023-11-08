package object;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;

public class OBJ_Gate extends Entity{

	GamePanel gp;
	
	public static final String objName = "Gate";
	public String key;
	
	public OBJ_Gate (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/gate", gp.tileSize*2, gp.tileSize*2);
		image2 = setup("/objects/gate_opened", gp.tileSize*2, gp.tileSize*2);
		down0 = image;
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		solidArea.x = 0;
		solidArea.y = 14;
		solidArea.width = 48*scale;
		solidArea.height = 34*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Il te faut la " + key + ".";
		dialogues[1][0] = "Vous utilisez la " + key + " et\nouvrez le portail.";
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
			if (key == AssetSetter.key_kitchen) {
				gp.eHandler.kitchenDoorOpened = true;
			}
		}
		else {
			startDialogue(this, 0);
		}
	}
	
	public void setOpened() {
		gp.playSE(5);
		down0 = image2;
		collision = false;
		opened = true;
	}
	
	public void setClosed() {
		gp.playSE(5);
		down0 = image;
		collision = true;
		opened = false;
	}
}