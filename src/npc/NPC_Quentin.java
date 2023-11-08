package npc;

import java.awt.Rectangle;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;
import object.OBJ_Key;
import object.OBJ_KramulHead;

public class NPC_Quentin extends Entity{
	
	boolean kramulHeadGiven = false;
	
	public NPC_Quentin(GamePanel gp) {
		
		super(gp);
		name = "Quentin";
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
		
		down0 = setup("/npc/quentin_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/quentin_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/quentin_down_2", gp.tileSize*scale, gp.tileSize*scale);
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Bonjour " + gp.player.name + ". C'est Quentin.\n" + 
						  "J'ai laissé tomber la clé de la piscine de\nl'autre côté...";
		dialogues[0][1] = "Tu as vu des kramuls? Tu es fou!\nCes monstres n'existent pas.";
		dialogues[0][2] = "Je veux bien te croire mais ramène moi une\ntête de kramul d'abord.\n" +
						  "A ce moment là, je t'aiderai.";
		
		dialogues[1][0] = "Oh ça alors!\nUne tête de Kramul!";
		dialogues[1][1] = "Tu avais donc raison " + gp.player.name + "...";
		
		dialogues[2][0] = "Tiens je te donne la clé de la maison.\n" + 
						  "Hmm... Je ne suis pas sûr que ce soit la\nbonne en fait. A toi de voir.";
		
		dialogues[3][0] = "Aïe, je m'y perds avec toutes ces clés...";
		dialogues[3][1] = "Si la clé de la maison reste introuvable\nsache que Sophie avait perdu la clé de la\n" +
				"chaufferie quelque part dans des buissons.";
		dialogues[3][2] = "ça pourrait toujours dépanner...";
		
	}
	
	
	public void setAction() {}
	
	public void speak() {
		
		startDialogue(this, dialogueSet);
		changeDialogue();
		
	}
	
	public void changeDialogue() {
		int i = gp.player.searchItemInInventory(OBJ_KramulHead.objName);
		if (dialogueSituation == 3) {
			dialogueSet = 3;
		}
		else if (dialogueSituation == 2) {
			OBJ_Key key = new OBJ_Key(gp, 0, 0);
			key.name2 = AssetSetter.key_guardian;
			if(givePlayer(key)) {
				dialogueSet = 2;
				dialogueSituation = 3;
			}
		}
		else if (i != 999) {
			gp.player.inventory.remove(i);
			gp.ui.addMessage("Quentin vous prend une tête de Kramul.");
			dialogueSet = 1;
			dialogueSituation = 2;
		}
		else {
			dialogueSet = 0;
		}
	}

	
	public boolean givePlayer(Entity item) {
		if (gp.player.canObtainItem(item)) {
			gp.ui.addMessage("Tu obtiens " + item.determinant + " " + item.name2 + ".");
			return true;
		}
		else {
			gp.ui.addMessage("Tu ne peux pas obtenir " + item.determinant + " " + item.name2 + ".");
			return false;
		}
	}
	

	
}
