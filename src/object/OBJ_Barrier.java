package object;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;

public class OBJ_Barrier extends Entity{

	GamePanel gp;
	
	public static final String objName = "Barrier";
	public static final String type1 = "barrier1";
	public static final String type2 = "barrier2";
	public String key;
	
	public OBJ_Barrier (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		name2 = type1;
		down0 = setup("/objects/barrier_1", gp.tileSize, gp.tileSize*2);
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		solidArea.x = 0;
		solidArea.y = 10;
		solidArea.width = 24*scale;
		solidArea.height = 28*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void setType(String type) {
		this.name2 = type;
		setImage();
	}
	
	public void setImage() {
		if (name2 == type2) {
			down0 = setup("/objects/barrier_2", gp.tileSize, gp.tileSize*2);
		}
		else {
			down0 = setup("/objects/barrier_1", gp.tileSize, gp.tileSize*2);
		}
	}
	
	public void setKey(String key) {}
	
	public void interact() {}
	
}
