package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Bill extends OBJ{

	GamePanel gp;
	public static final String objName = "Bill";
	
	public OBJ_Bill(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_pickupOnly;
		name = objName;
		name2 = "0 euro";
		determinant = "un";
		value = 0;
		down0 = setup("/objects/bill", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public void setValue(int value) {
		this.value = value;
		name2 = "billet de " + value + " euros";
	}
	
	public boolean use(Entity entity) {
	
 		gp.playSE(3);
 		gp.player.coin += value;
 		gp.ui.addMessage("Tu obtiens un billet de " + value + " euros.");
 		return true;
	}
}
