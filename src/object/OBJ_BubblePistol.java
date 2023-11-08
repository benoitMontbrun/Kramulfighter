package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BubblePistol extends Entity{
	
	public static final String objName = "Bubble Pistol";
	
	public OBJ_BubblePistol(GamePanel gp, int col, int row) {
		super(gp);
		
		type = type_automatic;
		name = objName;
		name2 = "pistolet à bulle";
		determinant = "un";
		down0 = setup("/objects/bubble_pistol", gp.tileSize, gp.tileSize);
		
		scale = 1;

		description = "[" + name2 + "]\nFaible précision.\nGrosse quantité.";
		price = 30;
		knockBackPower = 10;
		
		motion1_duration = 4;
		motion2_duration = 4;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public void playShotSound() {
		
	}

}
