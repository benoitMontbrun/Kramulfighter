package object;



import entity.Entity;
import main.GamePanel;

public class OBJ_PQ extends OBJ{

	GamePanel gp;
	public static final String objName = "PQ";
	
	public OBJ_PQ(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
				
		type = type_consumable;
		stackable = true;
		name = objName;
		determinant = "un";
		name2 = "rouleau de papier toilette";
		description = "[PQ]\nPeut toujours\nservir.";
		down0 = setup("/objects/pq", gp.tileSize, gp.tileSize);
		price = 0;
		
		scale = 1;
		worldX = col*gp.tileSize;
		worldY = row*gp.tileSize;
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Que fais tu?";
		
	}
	
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		return false;
	}
}
