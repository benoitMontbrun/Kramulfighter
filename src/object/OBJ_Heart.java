package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends OBJ{

	GamePanel gp;
	
	public static final String objName = "Heart";
	
	public OBJ_Heart(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 2;
		down0 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public boolean use(Entity entity) {
		
 		gp.playSE(2);
 		entity.life += value;
 		if (entity.life > entity.maxLife) {
 			entity.life = entity.maxLife;
 		}
 		return true;
	}
}
