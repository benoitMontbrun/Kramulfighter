package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bat extends Entity{
	
	public static final String objName = "Bat";
	
	public OBJ_Bat(GamePanel gp, int col, int row) {
		super(gp);
		
		type = type_bat;
		name = objName;
		name2 = "batte de baseball";
		determinant = "une";
		down0 = setup("/objects/bat", gp.tileSize, gp.tileSize);
		attackValue = 1;
		
		scale = 1;
		attackArea.width = 10*2;
		attackArea.height = 20*2;
		description = "[" + name2 + "]\nBatte d'un bois bien\nrésistant.";
		price = 30;
		knockBackPower = 10;
		motion1_duration = 10;
		motion2_duration = 25;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public int[] getAttackUpPosition() {
		int[] position = new int[2];	
		position[0] = 20*2;
		position[1] = -10*2;
		return position;
	}
	
	public int[] getAttackDownPosition() {
		int[] position = new int[2];	
		position[0] = 20*2;
		position[1] = 48*2;
		return position;
	}
	
	public int[] getAttackRightPosition() {
		int[] position = new int[2];	
		position[0] = 34*2;
		position[1] = 29*2;
		return position;
	}
	
	public int[] getAttackLeftPosition() {
		int[] position = new int[2];	
		position[0] = -6*2;
		position[1] = 29*2;
		return position;
	}
}
