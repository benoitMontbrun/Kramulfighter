package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Arrow extends OBJ{

	GamePanel gp;
	public static final String objName = "Arrow";
	
	public OBJ_Arrow(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_pickupOnly;
		name = objName;
		name2 = "flèches";
		value = 1;
		determinant = "" + value;
		description = "[" + name2 + "]\nRapides et\naiguisées.";
		down0 = setup("/objects/arrow", gp.tileSize, gp.tileSize);
		price = 2;
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public boolean use(Entity entity) {
	
 		gp.playSE(3);
 		gp.player.arrowNum += value;
 		return true;
	}
	
	public void setValue(int value) {
		this.value = value;
		determinant = "" + value;
	}
}
