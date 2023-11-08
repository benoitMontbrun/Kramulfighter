package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;

public class OBJ_Thrash extends Entity{

	GamePanel gp;
	
	public static final String objName = "Thrash";
	
	
	public static final String green = "green";
	public static final String yellow = "yellow";
	public static final String brown = "brown";
	
	int goalCol, goalRow;
	
	public OBJ_Thrash (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_moveable;
		name = objName;
		name2 = brown;
		
		image = setup("/objects/thrash_brown", gp.tileSize*2, gp.tileSize*2);
		setImage(image);
		collision = true;
		
		scale = 2;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		speed = 2;
		
		solidArea.x = 7;
		solidArea.y = 0;
		solidArea.width = 38*scale;
		solidArea.height = 48*scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		goalCol = 90;
		goalRow = 10;
		
		setDialogue();
		opened = false;
	}
	
	public void setDialogue() {
		dialogues[0][0] = "C'est une poubelle.";
	}
	
	public void setImage(BufferedImage image) {
		down0 = image;
		down1 = image;
		down2 = image;
		up0 = image;
		up1 = image;
		up2 = image;
		left0 = image;
		left1 = image;
		left2 = image;
		right0 = image;
		right1 = image;
		right2 = image;
	}
	
	public void setColor(String color) {
		this.name2 = color;
		switch (color) {
		case green:
			image = setup("/objects/thrash_green", gp.tileSize*2, gp.tileSize*2);
			setImage(image);
			goalCol = 97;
			goalRow = 10;
			break;
		case yellow:
			image = setup("/objects/thrash_yellow", gp.tileSize*2, gp.tileSize*2);
			setImage(image);
			goalCol = 90;
			goalRow = 13;
			break;
		}
	}
	
	public void move(String d) {

		this.direction = d;
		checkObjectCollision();
		
		if (!collisionOn) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}	
		opened = false;
		detectTile();
	}
	
	public void detectTile() {
		if (getCol() == goalCol && getRow() == goalRow && !opened) {
			opened = true;
			gp.playSE(26);
			checkComplete();
		}
	}
	
	public void checkComplete() {
		if (gp.obj[2][1].opened && gp.obj[2][2].opened && gp.obj[2][3].opened) {
			gp.obj[2][0].setOpened();
		}
	}
	
	public void setAction() {}
	
	public void update() {}
	
	public void interact() {
		
		startDialogue(this, 0);
	}
	
}
