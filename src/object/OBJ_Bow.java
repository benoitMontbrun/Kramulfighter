package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bow extends Entity{
	
	public static final String objName = "Bow";
	
	public OBJ_Bow(GamePanel gp, int col, int row) {
		super(gp);
		
		type = type_unic;
		name = objName;
		name2 = "arc";
		determinant = "un";
		down0 = setup("/objects/bow", gp.tileSize, gp.tileSize);
		
		scale = 1;

		description = "[" + name2 + "]\nGrande précision.\nVitesse moyenne.";
		price = 75;
		knockBackPower = 10;
		
		motion1_duration = 10;
		motion2_duration = 25;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public void playBendSound() {
		gp.playSE(20);
		
	}
	

}
