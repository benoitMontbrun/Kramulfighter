package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_WC extends Entity{

	GamePanel gp;
	
	public static final String objName = "WC";
	
	public OBJ_WC (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		

	}
	

	public void setType(String direction) {
		if (direction == "down") {
			down0 = setup("/objects/wc", gp.tileSize*2, gp.tileSize*2);
			solidArea.x = 10*scale;
			solidArea.y = 4*scale;
			solidArea.width = 28*scale;
			solidArea.height = 44*scale;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
		else if (direction == "right") {
			down0 = setup("/objects/wc_right", gp.tileSize*2, gp.tileSize*2);
			solidArea.x = 0*scale;
			solidArea.y = 4*scale;
			solidArea.width = 27*scale;
			solidArea.height = 44*scale;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}

	}
}

