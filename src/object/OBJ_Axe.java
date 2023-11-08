package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
	public static final String objName = "Axe";
	
	public OBJ_Axe(GamePanel gp, int col, int row) {
		super(gp);
		
		type = type_axe;
		name = objName;
		name2 = "hache de bûcheron";
		determinant = "une";
		down0 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		
		scale = 1;
		attackArea.width = 10*2;
		attackArea.height = 6*2;
		description = "[" + name2 + "]\nBien aiguisée, cette\nhache coupe tout.";
		price = 30;
		knockBackPower = 5;
		motion1_duration = 20;
		motion2_duration = 40;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
	}
	
	public int[] getAttackUpPosition() {
		int[] position = new int[2];	
		position[0] = 18*2;
		position[1] = -10*2;
		return position;
	}
	
	public int[] getAttackDownPosition() {
		int[] position = new int[2];	
		position[0] = 20*2;
		position[1] = 61*2;
		return position;
	}
	
	public int[] getAttackRightPosition() {
		int[] position = new int[2];	
		position[0] = 46*2;
		position[1] = 30*2;
		return position;
	}
	
	public int[] getAttackLeftPosition() {
		int[] position = new int[2];	
		position[0] = -6*2;
		position[1] = 30*2;
		return position;
	}
}
