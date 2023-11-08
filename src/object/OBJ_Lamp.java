package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lamp extends Entity{
	
	public static final String objName = "Lamp";

	public OBJ_Lamp(GamePanel gp, int col, int row) {
		super(gp);
		
		type = type_light;
		name = objName;
		name2 = "lampe de poche";
		determinant = "une";
		down0 = setup("/objects/lamp", gp.tileSize, gp.tileSize);
		description = "[Lamp]\nPour t'éclairer\ndans le noir.";
		price = 100;
		lightRadius = 500;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}

}
