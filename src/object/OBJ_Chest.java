package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends OBJ{

	GamePanel gp;
	
	public static final String objName = "Chest";

	public OBJ_Chest (GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/chest", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
		down0 = image;
		collision = true;
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Tu ouvres le coffre et trouves " + loot.determinant + "\n" + loot.name2 
						+ "!\n... Mais tu ne peux pas porter plus d'objets.";
		dialogues[1][0] = "Tu ouvres le coffre et trouves " + loot.determinant + "\n" + loot.name2 
						+ "!\nC'est rangé dans ton inventaire.";
		dialogues[2][0] = "Coffre vide.";
	}
	
	public void interact() {

		if (opened == false) {
			gp.playSE(5);
			
			if (!gp.player.canObtainItem(loot)) {
				startDialogue(this, 0);
			}
			else {
				startDialogue(this, 1);
				down0 = image2;
				opened = true;
			}
		}
		else {
			startDialogue(this, 2);
		}
	}
	
	
	
	
	
}
