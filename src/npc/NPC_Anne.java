package npc;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class NPC_Anne extends Entity{
	
	public NPC_Anne(GamePanel gp) {
		
		super(gp);
		name = "Tante Anne";
		type = type_npc;
		direction = "down";
		speed = 2;
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
		
		down0 = setup("/npc/anne_0", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/anne_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/anne_2", gp.tileSize*scale, gp.tileSize*scale);
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Bonjour " + gp.player.name + " .\n" + "C'est tante Anne, tu vas bien ?";
		dialogues[0][1] = "C'est bête mais nous avons perdu la clé\nde la maison.";
		dialogues[0][2] = "Quentin doit savoir où elle est.\nAu dernière nouvelle, il s'occupait de la\npiscine.";
		
		dialogues[1][0] = "Bla bla bla";
		dialogues[1][1] = "Bla bla bla";
		dialogues[1][2] = "Bla bla bla";
		
		dialogues[2][0] = "Bla bla bla";
		
	}
	
	
	public void setAction() {}
	
	public void speak() {
		
		startDialogue(this, dialogueSet);
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
		
		
//		onPath = true;
	}
	

	
}
