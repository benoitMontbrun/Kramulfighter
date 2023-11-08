package npc;

import java.awt.Rectangle;

import entity.Entity;

import main.GamePanel;
import object.OBJ_Arrow;
import object.OBJ_Bow;


public class NPC_Guilhem extends Entity{
	
	public NPC_Guilhem(GamePanel gp) {
		
		super(gp);
		name = "Guilhem";
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
		setItems();
	}
	
	
	public void getImage() {
		
		down0 = setup("/npc/guilhem_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/guilhem_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/guilhem_down_2", gp.tileSize*scale, gp.tileSize*scale);
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Salut " + gp.player.name + " !\n" + "J'ai peut être des choses qui peuvent\nt'intéresser.";
		dialogues[1][0] = "A plus!";
		dialogues[2][0] = "Ah... il te faut plus pour acheter ça.";
		dialogues[3][0] = "Tu ne peux pas porter plus d'objets.";
		dialogues[4][0] = "Tu ne peux pas vendre un objet équipé.";
		dialogues[5][0] = "Tu ne peux pas vendre de clés.";
	}
	
	public void setItems() {
		
		inventory.add(new OBJ_Bow(gp, 0, 0));
		inventory.add(new OBJ_Arrow(gp, 0, 0));
		
	}
	
	public void setAction() {}
	
	public void speak() {
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
	
	public void changeDialogue() {}
	

	
}
