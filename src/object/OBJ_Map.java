package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Map extends OBJ{

	GamePanel gp;
	public static final String objName = "Map";
	
	public OBJ_Map(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_pickupOnly;
		name = objName;
		name2 = "carte de la zone";
		determinant = "la";
		value = 0;
		down0 = setup("/objects/map", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean use(Entity entity) {
	
 		gp.playSE(3);
 		gp.player.hasMap[value] = true;
 		return true;
	}
}
