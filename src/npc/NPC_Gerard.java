package npc;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.AssetSetter;
import main.GamePanel;
import object.OBJ_Bottle;
import object.OBJ_Key;
import object.OBJ_KramulHead;

public class NPC_Gerard extends Entity{
	
	boolean armagnacBottleGiven = false;
	
	public NPC_Gerard(GamePanel gp) {
		
		super(gp);
		name = "Oncle Gérard";
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
		
		getImage();
		setDialogue();
		
	}
	
	
	public void getImage() {
		
		up0 = setup("/npc/gerard_up", gp.tileSize*scale, gp.tileSize*scale);
		up1 = setup("/npc/gerard_up_1", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/npc/gerard_up_2", gp.tileSize*scale, gp.tileSize*scale);
		down0 = setup("/npc/gerard_down", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/npc/gerard_down_1", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/npc/gerard_down_2", gp.tileSize*scale, gp.tileSize*scale);
		right0 = setup("/npc/gerard_right", gp.tileSize*scale, gp.tileSize*scale);
		right1 = setup("/npc/gerard_right_1", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/npc/gerard_right_2", gp.tileSize*scale, gp.tileSize*scale);
		left0 = setup("/npc/gerard_left", gp.tileSize*scale, gp.tileSize*scale);
		left1 = setup("/npc/gerard_left_1", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/npc/gerard_left_2", gp.tileSize*scale, gp.tileSize*scale);
		
	}
	
	
	public void setDialogue() {
		dialogues[0][0] = "Bonjour esclave de première classe!\n" + "Bienvenue à Artagnan, je suis ton oncle\nGérard.";
		dialogues[0][1] = "Malheureusement, Artagnan a été envahi\npar les Kramuls. " + 
						  "Ce n'est plus la paisible\nmaison de vacance que tu connaissais...";
		dialogues[0][2] = "Les Kramuls ont enlevé la princesse\nMaylis. A toi de la retrouver !";
		dialogues[0][3] = "Bonne chance " + gp.player.name + " !";
		
		dialogues[0][4] = "Avant que tu ne partes, j'ai quelque chose\npour toi si tu veux!";
		dialogues[0][5] = "Il s'agit d'une bouteille de Madiran 2009\nune excellente année à mon avis.";
		dialogues[0][6] = "J'espère que ça t'aidera pour ta quête.";
		
		dialogues[0][7] = "Tu devrais chercher de quoi te\ndéfendre contre les kramuls.";
		dialogues[0][8] = "Il devrait y avoir quelque chose\nquelque part dans l'écurie.";
		dialogues[0][9] = "Vas-y, " + gp.player.name + " !";
		
		dialogues[1][0] = "Tu devrais chercher de quoi te\ndéfendre contre les kramuls.";
		dialogues[1][1] = "Il devrait y avoir quelque chose\nquelque part dans l'écurie.";
		dialogues[1][2] = "Vas-y, jeune esclave de première classe.";
		
		dialogues[3][0] = "Tiens! Tu as trouvé de quoi te défendre!";
		dialogues[3][1] = "J'aurais besoin d'un petit service...";
		dialogues[3][2] = "Je dois aller chercher des bouteilles\nde vin à la cave.";
		dialogues[3][3] = "On se retrouve à l'intérieur, dans la\nchambre d'oncle Henri.\n" +
						  "N'oublie pas de prendre une lampe.";
		
		dialogues[4][0] = "Ah " + gp.player.name + " !\n" + "Te voilà.";
		dialogues[4][1] = "Malheureusement j'ai oublié la clé de la\ncave dans ma chambre...\n" + 
						  "Tu pourrais aller la chercher s'il te plaît?";
		
		dialogues[5][0] = "Tu as trouvé la clé?\nSuper!";
		dialogues[5][1] = "Pourrais tu aller chercher une bouteille\nd'Armagnac dans la cave ?\nC'est important.";
		dialogues[5][2] = "Il y a une lampe dans la chambre des\ngrands-parents, tu en auras besoin.";
		
		dialogues[6][0] = "Ah merci " + gp.player.name + " pour la bouteille\nd'Armagnac.";
		dialogues[6][1] = "Par contre si tu as besoin d'ouvrir l'autre\ntrappe de la cave, je crois que j'ai laissé"
				+ "\nla clé dans mon pantalon à la piscine.";
		
		dialogues[7][0] = "Tu as vu des kramuls dans la cave ?";
		dialogues[7][1] = "Princesse Maylis ne doit pas être loin...";
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
	
	public void changeDialogue() {
		int i = gp.player.searchItemInInventory(OBJ_Key.objName, AssetSetter.key_cellar);
		int j = gp.player.searchItemInInventory(OBJ_Bottle.objName, OBJ_Bottle.armagnacBottle);
		if (armagnacBottleGiven) {
			dialogueSet = 7;
		}
		else if (gp.currentMap == 2 && j != 999) {
			gp.player.inventory.remove(j);
			gp.ui.addMessage("Oncle Gérard vous prend une bouteille d'Armagnac.");
			armagnacBottleGiven = true;
			dialogueSet = 6;
		}
		else if (gp.currentMap == 2 && (i != 999 || gp.obj[2][4].opened)) {
			dialogueSet = 5;
		}
		else if (gp.player.currentWeapon == null) {
			switch(dialogueSituation) {
			case 0:
				OBJ_Bottle bottle = new OBJ_Bottle(gp, 0, 0);
				bottle.name2 = OBJ_Bottle.redWineBottle;
				bottle.name3 = "Bouteille de Madiran";
				dialogueSet = 0;
				if (givePlayer(bottle)) {
					dialogueSituation = 1;
				}
				break;
			case 1:
				dialogueSet = 1;
				break;
			}
		}
		else {
			if (dialogueSituation < 5) {
				dialogueSet = 3;
				dialogueSituation = 4;
			}
			else {
				dialogueSet = 4;
			}
		}
	}

	
	public boolean givePlayer(Entity item) {
		if (gp.player.canObtainItem(item)) {
			gp.ui.addMessage("Tu obtiens " + item.determinant + " " + item.name3 + ".");
			return true;
		}
		else {
			gp.ui.addMessage("Tu ne peux pas obtenir " + item.determinant + " " + item.name3 + ".");
			return false;
		}
	}
	
	public void speak() {
		
		facePlayer();
		
		startDialogue(this, dialogueSet);
		changeDialogue();

	}
	

	
}
