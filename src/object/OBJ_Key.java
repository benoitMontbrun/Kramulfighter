package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends OBJ{
	
	GamePanel gp;
	
	public static final String objName = "Key";
	
	public OBJ_Key(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		name2 = "une clé";
		determinant = "la";
		down0 = setup("/objects/key", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		price = 0;
		stackable = false;
		setDialogue();
		setDescription();

	}
	
	public void setDescription() {
		description = "[" + name2 + "]\nOuvre une porte.";
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Que fais tu?";
		
	}
	
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		return false;
		
	}
}
