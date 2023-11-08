package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bed extends Entity{

	GamePanel gp;
	
	public static final String objName = "Bed";
	
	public static final int simple = 1;
	public static final int large = 2;
	public static final String blue = "bleu";
	public static final String green = "green";
	public static final String red = "red";
	
	public OBJ_Bed (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		
		solidArea.x = 0;
		solidArea.y = 20*scale;
		solidArea.width = 96*scale;
		solidArea.height = 28*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

	}
	

	public void changeValues(String direction, String Color, int type) {
		if (type == simple) {
			if (direction == "right") {
				switch(Color) {
				case blue:
					down0 = setup("/objects/bed_right_blue", gp.tileSize*4, gp.tileSize*2);
					break;
				case red:
					down0 = setup("/objects/bed_right_red", gp.tileSize*4, gp.tileSize*2);
					break;
				case green:
					down0 = setup("/objects/bed_right", gp.tileSize*4, gp.tileSize*2);
					break;	
				}
			}
			else if (direction == "left") {
				switch(Color) {
				case blue:
					down0 = setup("/objects/bed_left_blue", gp.tileSize*4, gp.tileSize*2);
					break;
				case red:
					down0 = setup("/objects/bed_left_red", gp.tileSize*4, gp.tileSize*2);
					break;
				case green:
					down0 = setup("/objects/bed_left", gp.tileSize*4, gp.tileSize*2);
					break;	
				}
			}
		}
		else if (type == large) {
			if (direction == "right") {
				solidArea.height = 76*scale;
				down0 = setup("/objects/bed_right_double", gp.tileSize*4, gp.tileSize*4);
			}
			else if (direction == "left") {
				solidArea.height = 76*scale;
				down0 = setup("/objects/bed_left_double", gp.tileSize*4, gp.tileSize*4);
			}
		}


	}
}

