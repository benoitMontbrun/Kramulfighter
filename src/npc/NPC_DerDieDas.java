package npc;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class NPC_DerDieDas extends Entity{
	
	public NPC_DerDieDas(GamePanel gp) {
		
		super(gp);
		name = "Thomas DerDieDas";
		type = type_npc;
		direction = "down";
		speed = 0;
		scale = 2;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 24 * scale;
		solidArea.height = 48 * scale;
		
		dialogueSet = 0;
		dialogueSituation = 0;
		standing = true;
		
		getImage();
		setDialogue();
		
	}
	
	
	public void getImage() {
		
		down0 = setup("/npc/thomas", gp.tileSize, gp.tileSize*2);
		down1 = down0;
		down2 = down0;
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[0][1] = "Il était une fois un pingouin qui respirait\npar les fesses. Un jour il s'assoie, et ...";
		dialogues[0][2] = "Il meurt.";
		
		dialogues[1][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[1][1] = "Il était une fois un pingouin qui respirait\npar les fesses. Un jour il s'assoie, et ...";
		dialogues[1][2] = "Il ne meurt pas ! Parce qu'il a des\nchaussures Geox.\nGeox, la chaussure qui respire !";
		
		dialogues[2][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[2][1] = "Quel est le point commun entre un robot et\ndes spaghettis à la bolognaise?";
		dialogues[2][2] = "Ils sont tous les deux au tomate.";
		
		dialogues[3][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[3][1] = "Comment est mort Hitler?";
		dialogues[3][2] = "Il a vu sa facture de gaz...";
		
		dialogues[4][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[4][1] = "Comment est mort Hitler?";
		dialogues[4][2] = "Il s'est suicidé.";
		
		dialogues[5][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[5][1] = "C'est un mec qui rentre dans un bar, il dit\n«C'est moi!».";
		dialogues[5][2] = "Mais en fait c'était pas lui!";
		
		dialogues[6][0] = "Hello c'est Thomas DerDieDas.\nJe vais te faire une blague. Tu es prêt ?";
		dialogues[6][1] = "C'est un aveugle qui rentre dans un bar,\npuis dans une table, puis dans une chaise.";
		
		
		
	}
	
	
	public void setAction() {}
	
	public void speak() {
		
		dialogueSet = new Random().nextInt(7);
		
		startDialogue(this, dialogueSet);
	}
	

	
}
