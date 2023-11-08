package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bottle extends Entity{
	
	GamePanel gp;
	
	public static final String objName = "Bottle";
	public static final String emptyBottle = "Bouteille vide";
	public static final String redWineBottle = "Bouteille de vin rouge";
	public static final String whiteWineBottle = "Bouteille de vin blanc";
	public static final String armagnacBottle = "Bouteille d'armagnac";
	
	public OBJ_Bottle(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		type = type_consumable;
		name = objName;
		name2 = objName;
		name3 = name2;
		determinant = "une";
		
		setImage();
		
		price = 10;
		stackable = false;
		value = 2;
		setDialogue();
		setDescription();
	}
	
	public void setImage() {
		switch(name2) {
		case emptyBottle:
			down0 = setup("/objects/empty_bottle", gp.tileSize, gp.tileSize);
			break;
		case redWineBottle:
			down0 = setup("/objects/wine_bottle", gp.tileSize, gp.tileSize);
			break;
		case whiteWineBottle:
			down0 = setup("/objects/white_wine_bottle", gp.tileSize, gp.tileSize);
			break;
		case armagnacBottle:
			down0 = setup("/objects/armagnac_bottle", gp.tileSize, gp.tileSize);
			break;
		}
	}

	public void changeValues(String name2, String name3, int value) {
		this.name2 = name2;
		this.name3 = name3;
		setValue(value);
		setImage();
		setDialogue();
		setDescription();
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setDescription() {
		if (name2 == emptyBottle) {
			description = "[" + name3 + "]\nRien à boire.";
		}
		else {
			description = "[" + name3 + "]\nA boire.\n+" + value + " coeurs";
		}
	
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Tu bois la " + name3 + ".\nTu regagne " + value + " coeurs.";
		dialogues[1][0] = "Cette bouteille est vide.";
		
	}
	
	public boolean use(Entity entity) {
		
		if (name2 == emptyBottle) {
			gp.gameState = gp.dialogueState;
			startDialogue(this, 1);
			return false;
		}
		else {
			
			gp.gameState = gp.dialogueState;
			startDialogue(this, 0);
			entity.life += value*2;
			if (gp.player.life > gp.player.maxLife) {
				gp.player.life = gp.player.maxLife;
			}
	 		gp.playSE(2);
	 		return true;
		}

	}
}