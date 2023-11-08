package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends OBJ{

	GamePanel gp;
	public static final String objName = "Coin";
	
	public OBJ_Coin(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_pickupOnly;
		name = objName;
		name2 = "1 euro";
		value = 1;
		down0 = setup("/objects/coin", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public boolean use(Entity entity) {
	
 		gp.playSE(3);
 		gp.player.coin += value;
 		return true;
	}
}
