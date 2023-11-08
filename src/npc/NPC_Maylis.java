package npc;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class NPC_Maylis extends Entity{
	
	public NPC_Maylis(GamePanel gp) {
		
		super(gp);
		name = "Maylis";
		type = type_npc;
		direction = "down";
		speed = 1;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.x = 13 * scale;
		solidArea.y = 28 * scale;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 22 * scale;
		solidArea.height = 20 * scale;
		
		dialogueSet = 0;
		dialogueSituation = 0;
		standing = true;
		
		getImage();
		setDialogue();
		
	}
	
	
	public void getImage() {
		
		down0 = setup("/npc/maylis", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/maylis_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/maylis_2", gp.tileSize*scale, gp.tileSize*scale);
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Oh merci " + gp.player.name + " !\n" + "Tu m'as délivré !";
		dialogues[0][1] = "J'ai vraiment eu super peur...";
		dialogues[0][2] = "Est-ce que je pourrais jouer sur ton\nportable après ?";
 		
	}
	
	
	public void setAction() {}
	
	public void speak() {
		
		startDialogue(this, dialogueSet);
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
	}
	

	
}
