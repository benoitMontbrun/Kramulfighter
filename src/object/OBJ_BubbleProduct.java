package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_BubbleProduct extends OBJ{

	GamePanel gp;
	public static final String objName = "BubbleProduct";
	
	public OBJ_BubbleProduct(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_pickupOnly;
		name = objName;
		name2 = "de produit à bulle";
		value = 10;
		determinant = value + "mL";
		down0 = setup("/objects/bubble_product", gp.tileSize, gp.tileSize);
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
	}
	
	public boolean use(Entity entity) {
	
 		gp.playSE(3);
 		gp.player.bubbleNum += value;
 		gp.ui.addMessage("Tu obtiens "  + value + "mL de produit à bulle.");
 		return true;
	}
	
	public void setValue(int value) {
		this.value = value;
		determinant = value + "mL";
	}
}
