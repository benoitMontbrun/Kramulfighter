package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KramulHead extends OBJ{

	GamePanel gp;
	public static final String objName = "KramulHead";
	
	public OBJ_KramulHead(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_consumable;
		stackable = true;
		name = objName;
		determinant = "une";
		name2 = "tête de kramul";
		description = "[" + name2 + "]\n" + "Pouahh.. c'est\nrépugnant.";
		down0 = setup("/objects/kramul_head", gp.tileSize, gp.tileSize);
		price = 8;
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Que fais tu?";
		
	}
	
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		return false;
	}
}
