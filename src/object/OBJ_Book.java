package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_Book extends OBJ{

	GamePanel gp;
	public static final String objName = "Book";
	
	public OBJ_Book(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_consumable;
		stackable = true;
		name = objName;
		determinant = "un";
		name2 = "livre";
		description = "[Livre]\nça sent le\nvieux livre.";
		down0 = setup("/objects/book", gp.tileSize, gp.tileSize);
		price = 10;
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Rien à lire.";
		
	}
	
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		return false;
	}
}
