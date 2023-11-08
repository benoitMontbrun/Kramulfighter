package npc;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class NPC_Philippe extends Entity{
	
	public NPC_Philippe(GamePanel gp) {
		
		super(gp);
		name = "Oncle Philippe";
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
		
		getImage();
		setDialogue();
		
	}
	
	
	public void getImage() {
		
		up0 = setup("/npc/philippe_up", gp.tileSize*scale, gp.tileSize*scale);
		up1 = setup("/npc/philippe_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/npc/philippe_up_2", gp.tileSize*scale, gp.tileSize*scale);
		down0 = setup("/npc/philippe_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/philippe_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/philippe_down_2", gp.tileSize*scale, gp.tileSize*scale);
		right0 = setup("/npc/philippe_right", gp.tileSize*scale, gp.tileSize*scale);
		right1 = setup("/npc/philippe_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/npc/philippe_right_2", gp.tileSize*scale, gp.tileSize*scale);
		left0 = setup("/npc/philippe_left", gp.tileSize*scale, gp.tileSize*scale);
		left1 = setup("/npc/philippe_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/npc/philippe_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Salut " + gp.player.name + " !\n" + "Je suis ton oncle Philippe.";
		dialogues[0][1] = "J'aurais besoin d'un petit service.";
		dialogues[0][2] = "Tu vois les branches du kiwi qui vont\njusqu'au toit du ché ?\nIl faudrait les couper rapidement.";
		dialogues[0][3] = "Tu peux t'en occuper "+ gp.player.name + " ?\n" + "Ce serait vraiment sympa!";
		
		dialogues[1][0] = "Bla bla bla";
		dialogues[1][1] = "Bla bla bla";
		dialogues[1][2] = "Bla bla bla";
		
		dialogues[2][0] = "Bla bla bla";
		
	}
	
	
	public void setAction() {
		
		if (onPath) {
			
//			int goalCol = 12;
//			int goalRow = 9;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			//searchPath(goalCol, goalRow);
		}
		
		else {
			actionLockCounter++;
			
			if (actionLockCounter >= 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1;
				
				if(i <= 25) {
					direction = "up";
				}
				else if (i > 25 && i <= 50) {
					direction = "down";
				}
				else if (i > 50 && i <= 75) {
					direction = "right";
				}
				else if (i > 75) {
					direction = "left";
				}
				
				actionLockCounter = 0;
			}
		}
		


	}
	
	public void speak() {
		
		facePlayer();
		startDialogue(this, dialogueSet);
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet--;
		}
		
//		onPath = true;
	}
	

	
}
