package main;


import monster.MON_Bat;
import monster.MON_Kramul1;
import monster.MON_Kramul2;
import monster.MON_Kramul3;
import npc.NPC_Anne;
import npc.NPC_DerDieDas;
import npc.NPC_Gerard;
import npc.NPC_Guilhem;
import npc.NPC_Maylis;
import npc.NPC_Philippe;
import npc.NPC_Quentin;
import object.*;
import tile_interactive.IT_BigTree;
import tile_interactive.IT_Bush;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_PineTree;
import tile_interactive.IT_PineTrunk;
import tile_interactive.IT_Tree;
import tile_interactive.IT_Trunk;

public class AssetSetter {
	
	GamePanel gp;
	
	public static final String key_pool = "clé de la piscine";
	public static final String key_che = "clé du chai";
	public static final String key_chauf = "clé de la chaufferie";
	public static final String key_arta = "clé de la maison";
	public static final String key_kitchen = "clé de la cuisine";
	public static final String key_guardian = "clé maison du gardien";
	public static final String key_cellar = "clé de la cave";
	public static final String key_gate = "clé du portail";
	public static final String key_jail = "clé de la prison";
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int i = 0;
		int mapNum = 0;  //JARDIN
		
		gp.obj[mapNum][i] = new OBJ_Door(gp, 27, 66);
		gp.obj[mapNum][i].setKey(key_arta);
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp, 39, 66);
		gp.obj[mapNum][i].setKey(key_kitchen);
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp, 45, 66);
		gp.obj[mapNum][i].setKey(key_chauf);
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp, 74, 67);
		gp.obj[mapNum][i].setKey(key_che);
		i++;
		gp.obj[mapNum][i] = new OBJ_Gate(gp, 27, 94);
		gp.obj[mapNum][i].setKey(key_gate);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Heart(gp, 11, 69);
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp, 11, 70);
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp, 11, 71);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp, 11, 81);
		gp.obj[mapNum][i].name2 = key_chauf;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp, 78, 65);
		gp.obj[mapNum][i].name2 = key_pool;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp, 78, 59);
		gp.obj[mapNum][i].name2 = key_cellar;
		i++;
		
		//
		gp.obj[mapNum][i] = new OBJ_Key(gp, 45, 70);
		gp.obj[mapNum][i].name2 = key_chauf;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Key(gp, 53, 80);
		gp.obj[mapNum][i].name2 = key_guardian;
		i++;
		//
		
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 38, 90);
		gp.obj[mapNum][i].setLoot(new OBJ_Bat(gp, 0, 0));
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 61, 90);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 61, 91);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 64, 94);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 71, 94);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 61, 94);
		gp.obj[mapNum][i].setValue(10);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 11, 94);
		gp.obj[mapNum][i].setValue(20);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 86, 73);
		OBJ_Map map = new OBJ_Map(gp, 0, 0);
		map.setValue(0);
		gp.obj[mapNum][i].setLoot(map);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 84, 85);
		OBJ_Bill bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 34, 94);
		OBJ_Bottle bottle = new OBJ_Bottle(gp, 0, 0);
		bottle.name2 = OBJ_Bottle.redWineBottle;
		bottle.name3 = "Bouteille de Bordeaux";
		gp.obj[mapNum][i].setLoot(bottle);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 88, 92);
		bottle = new OBJ_Bottle(gp, 0, 0);
		bottle.name2 = OBJ_Bottle.redWineBottle;
		bottle.name3 = "Bouteille de Bordeaux";
		gp.obj[mapNum][i].setLoot(bottle);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 89, 90);
		OBJ_Arrow arrows = new OBJ_Arrow(gp, 0, 0);
		arrows.setValue(30);
		gp.obj[mapNum][i].setLoot(arrows);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 65, 38);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 62, 66);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 51, 61);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 53, 38);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bourgogne", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 51, 43);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(20);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Barrier(gp, 78, 68);
		i++;	
		gp.obj[mapNum][i] = new OBJ_PoolGate(gp, 79, 68);
		gp.obj[mapNum][i].setKey(key_pool);
		i++;
		gp.obj[mapNum][i] = new OBJ_Barrier(gp, 80, 69);
		gp.obj[mapNum][i].setType(OBJ_Barrier.type2);
		i++;	
		
//		gp.obj[mapNum][i] = new OBJ_KramulHead(gp, 75, 75);
//		i++;	
		
		
		
		
		mapNum = 1;  //INTERIEURS BATIMENTS
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 3, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 5, 1);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 12, 1);
		gp.obj[mapNum][i].setLoot(new OBJ_PQ(gp, 0, 0));
		i++;
		
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 55, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 58, 1);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 33, 10);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 33, 15);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 33, 20);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 33, 25);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 27, 13);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 27, 18);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 27, 23);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 30, 25);
		bill.setValue(20);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		
		
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 20);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 23);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 32);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 35);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 1, 27);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 14, 21);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 14, 30);
		bottle = new OBJ_Bottle(gp, 0, 0);
		bottle.name2 = OBJ_Bottle.redWineBottle;
		bottle.name3 = "Bouteille de Bordeaux";
		gp.obj[mapNum][i].setLoot(bottle);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 14, 39);
		gp.obj[mapNum][i].setLoot(new OBJ_Axe(gp, 0, 0));
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 14, 37);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 14, 41);
		i++;
		
		
		
		i = 0;                 //INTERIEUR MAISON
		mapNum = 2;
		//ETAGE 0
		gp.obj[mapNum][i] = new OBJ_Door(gp, 94, 6);
		gp.obj[mapNum][i].setKey(null);
		i++;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 96, 13);
		i++;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 97, 18);
		gp.obj[mapNum][i].setColor(OBJ_Thrash.green);
		i++;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 91, 23);
		gp.obj[mapNum][i].setColor(OBJ_Thrash.yellow);
		i++;
		gp.obj[mapNum][i] = new OBJ_TrapDoor(gp, 5, 25);
		gp.obj[mapNum][i].setKey(key_cellar);
		i++;
		gp.obj[mapNum][i] = new OBJ_TrapDoor(gp, 28, 4);
		gp.obj[mapNum][i].setKey(key_cellar);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 73, 28);
		OBJ_Key key = new OBJ_Key(gp, 0, 0);
		key.name2 = key_kitchen;
		gp.obj[mapNum][i].setLoot(key);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 71, 28);
		key = new OBJ_Key(gp, 0, 0);
		key.name2 = key_pool;
		gp.obj[mapNum][i].setLoot(key);
		i++;	
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 64, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 66, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 66, 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 67, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 68, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 70, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 82, 17);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 86, 17);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 61, 12);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 63, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.emptyBottle, "Bouteille vide", 0);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 65, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 67, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Jurançon", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 69, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Côte de Gascogne", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 71, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Pacherenc", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 73, 17);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Sauternes", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 61, 19);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 61, 23);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 59, 7);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 59, 9);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 92, 2);
		map = new OBJ_Map(gp, 0, 0);
		map.setValue(2);
		gp.obj[mapNum][i].setLoot(map);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 96, 2);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(20);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 90, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 98, 2);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 14, 14);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 15, 15);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 16, 14);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 17, 15);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 18, 14);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 20, 14);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 21, 15);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 22, 14);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 14, 16);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 15, 17);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 16, 16);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 17, 17);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 19, 17);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 20, 17);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 1, 6);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(50);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		
//		//
//		gp.obj[mapNum][i] = new OBJ_Key(gp, 10, 25);
//		gp.obj[mapNum][i].name2 = key_cellar;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Lamp(gp, 13, 25);
//		i++;
//		gp.obj[mapNum][i] = new OBJ_BubblePistol(gp, 13, 27);
//		i++;
//		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 13, 28);
//		gp.obj[mapNum][i].setValue(100);
//		i++;
//		//
		
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 1, 16);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Moscatel", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 1, 20);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Côte du Rhône", 2);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_WC(gp, 70, 4);
		gp.obj[mapNum][i].setType("right");
		i++;
		gp.obj[mapNum][i] = new OBJ_WC(gp, 67, 2);
		gp.obj[mapNum][i].setType("down");
		i++;
		gp.obj[mapNum][i] = new OBJ_WC(gp, 18, 14);
		gp.obj[mapNum][i].setType("down");
		i++;
		
		
		
		//ETAGE 1
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 60, 41);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 62, 41);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 64, 43);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 66, 43);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 57, 41);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 58, 41);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 98, 55);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 98, 56);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 78, 41);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 28, 41);
		gp.obj[mapNum][i].setLoot(new OBJ_BubblePistol(gp, 0, 0));
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 55, 47);
		gp.obj[mapNum][i].setValue(80);
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 93, 59);
		gp.obj[mapNum][i].setValue(120);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 72, 51);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 76, 51);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 79, 51);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 94, 67);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 62, 67);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 68, 67);
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 64, 67);
		gp.obj[mapNum][i].setValue(50);
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 66, 67);
		gp.obj[mapNum][i].setValue(50);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 46, 67);
		arrows = new OBJ_Arrow(gp, 0, 0);
		arrows.setValue(20);
		gp.obj[mapNum][i].setLoot(arrows);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 56);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 3, 56);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 67);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 3, 67);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 21, 56);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 21, 59);
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 1, 58);
		gp.obj[mapNum][i].setValue(70);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 3, 41);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 7, 41);
		gp.obj[mapNum][i].setValue(10);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 10, 41);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 15, 41);
		gp.obj[mapNum][i].setValue(10);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 32, 59);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 38, 59);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 46, 59);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 49, 59);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 36, 50);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 36, 45);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 52, 51);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 21, 43);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 17, 54);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 21, 52);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 31, 54);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 34, 54);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 37, 54);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 53, 60);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 87, 47);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 1, 46);
		gp.obj[mapNum][i].setLoot(new OBJ_Lamp(gp, 0, 0));
		i++;
		
		gp.obj[mapNum][i] = new OBJ_WC(gp, 61, 41);
		gp.obj[mapNum][i].setType("down");
		i++;
		gp.obj[mapNum][i] = new OBJ_WC(gp, 1, 62);
		gp.obj[mapNum][i].setType("right");
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 68, 50);
		gp.obj[mapNum][i].changeValues("left", OBJ_Bed.red, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 82, 50);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.green, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 82, 46);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.blue, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 82, 42);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.red, 1);
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 72, 61);
		gp.obj[mapNum][i].changeValues("left", OBJ_Bed.red, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 95, 61);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.blue, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 48, 59);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.green, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 49, 45);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.green, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 23, 44);
		gp.obj[mapNum][i].changeValues("left", OBJ_Bed.red, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 23, 48);
		gp.obj[mapNum][i].changeValues("left", OBJ_Bed.red, 1);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 18, 46);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.green, 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bed(gp, 37, 62);
		gp.obj[mapNum][i].changeValues("right", OBJ_Bed.green, 2);
		i++;

		
		
		
		
		
		//ETAGE 2
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 87);
		i++;
		gp.obj[mapNum][i] = new OBJ_PQ(gp, 1, 91);
		i++;
		gp.obj[mapNum][i] = new OBJ_WC(gp, 1, 88);
		gp.obj[mapNum][i].setType("right");
		i++;
		gp.obj[mapNum][i] = new OBJ_BubbleProduct(gp, 4, 87);
		gp.obj[mapNum][i].setValue(130);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 11, 88);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 14, 88);
		i++;
		gp.obj[mapNum][i] = new OBJ_Book(gp, 17, 88);
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp, 14, 81);
		gp.obj[mapNum][i].name2 = key_cellar;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 47, 80);
		arrows = new OBJ_Arrow(gp, 0, 0);
		arrows.setValue(15);
		gp.obj[mapNum][i].setLoot(arrows);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 34, 98);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(10);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 1, 82);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(20);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, 1, 95);
		bill = new OBJ_Bill(gp, 0, 0);
		bill.setValue(20);
		gp.obj[mapNum][i].setLoot(bill);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 16, 98);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 20, 98);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 24, 98);
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin(gp, 28, 98);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 14, 94);
		gp.obj[mapNum][i].setValue(5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bill(gp, 29, 94);
		gp.obj[mapNum][i].setValue(5);
		i++;
		
		
		
		//CAVE
		i = 0;
		mapNum = 3;
		gp.obj[mapNum][i] = new OBJ_Gate(gp, 65, 95);
		gp.obj[mapNum][i].setKey(key_jail);
		i++;
		gp.obj[mapNum][i] = new OBJ_Gate(gp, 65, 69);
		gp.obj[mapNum][i].setKey(null);
		i++;
		gp.obj[mapNum][i] = new OBJ_Lever(gp, 70, 69);
		gp.obj[mapNum][i].setLoot(gp.obj[3][1]);
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp, 27, 52);
		gp.obj[mapNum][i].name2 = key_jail;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 8, 94);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Pacherenc", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 8, 92);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Sauternes", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 8, 90);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Sauternes", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 6, 84);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 6, 82);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 6, 80);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 19, 85);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Pacherenc", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 19, 87);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Pacherenc", 4);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 27, 77);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.emptyBottle, "Bouteille vide", 0);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 29, 66);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Madiran", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 29, 64);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Madiran", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 29, 62);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Madiran", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 32, 81);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 34, 81);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 36, 81);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 38, 78);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Moscatel", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 40, 78);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Bouteille de Moscatel", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 53, 65);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Beaujolais", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 55, 65);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Beaujolais", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 57, 65);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Beaujolais", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 36, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 38, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 40, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 42, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 44, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 46, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 48, 53);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.redWineBottle, "Bouteille de Bordeaux", 3);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 28, 28);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 30, 28);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 32, 28);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 8, 33);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.armagnacBottle, "Bouteille d'Armagnac", 5);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 3, 41);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Vin d'Alsace", 2);
		i++;
		gp.obj[mapNum][i] = new OBJ_Bottle(gp, 5, 41);
		gp.obj[mapNum][i].changeValues(OBJ_Bottle.whiteWineBottle, "Vin d'Alsace", 2);
		i++;
		
		
		
	}
	
	public void setNPC() {
		
		int i = 0;             //JARDIN
		int mapNum = 0;
		
		gp.npc[mapNum][i] = new NPC_Gerard(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 20;
		gp.npc[mapNum][i].worldY = gp.tileSize * 85;
		i++;
		gp.npc[mapNum][i] = new NPC_Philippe(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 70;
		gp.npc[mapNum][i].worldY = gp.tileSize * 50;
		i++;
		gp.npc[mapNum][i] = new NPC_Anne(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 41;
		gp.npc[mapNum][i].worldY = gp.tileSize * 70;
		i++;
		gp.npc[mapNum][i] = new NPC_Quentin(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 76;
		gp.npc[mapNum][i].worldY = gp.tileSize * 68;
		i++;
		
		
		
		i = 0;             //INTERIEUR MAISON
		mapNum = 2;
		
		gp.npc[mapNum][i] = new NPC_Gerard(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 10;
		gp.npc[mapNum][i].worldY = gp.tileSize * 20;
		gp.npc[mapNum][i].dialogueSituation = 5;
		i++;
		
		gp.npc[mapNum][i] = new NPC_Guilhem(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 80;
		gp.npc[mapNum][i].worldY = gp.tileSize * 46;
		i++;
		
		gp.npc[mapNum][i] = new NPC_DerDieDas(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 85;
		gp.npc[mapNum][i].worldY = gp.tileSize * 44;
		i++;
		
		i = 0;
		mapNum = 3;         //CAVE
		gp.npc[mapNum][i] = new NPC_Maylis(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 65;
		gp.npc[mapNum][i].worldY = gp.tileSize * 97 - 4;
		i++;
	}
	
	public void setMonster() {
		
		int i = 0;            //JARDIN
		int mapNum = 0;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 15, 50);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 15, 40);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 15, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 25, 40);
		i++;
//		gp.monster[mapNum][i] = new MON_Kramul1(gp, 25, 40);
//		i++;
//		gp.monster[mapNum][i] = new MON_Kramul1(gp, 25, 45);
//		i++;
//		gp.monster[mapNum][i] = new MON_Kramul1(gp, 20, 50);
//		i++;
//		gp.monster[mapNum][i] = new MON_Kramul1(gp, 20, 45);
//		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 83, 77);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 83, 80);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 83, 83);
		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 64, 39);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 50, 45);
		i++;
		
		
		
		i = 0;                 //INTERIEURS BATIMENTS
		mapNum = 1;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 30, 10);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 30, 15);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 30, 20);
		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 51, 3);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 48, 3);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 15, 17);
		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 10, 19);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 10, 23);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 9, 28);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 12, 33);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 13, 27);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 8, 38);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 11, 41);
		i++;
		
		
		
		
		i = 0;                 //INTERIEUR MAISON
		mapNum = 2;
		//ETAGE 0
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 94, 10);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 94, 14);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 94, 18);
		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 66, 4);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 70, 2);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 82, 4);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 86, 4);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 82, 8);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 86, 8);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 21, 18);
		i++;
		
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 10, 4);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 10, 9);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 46, 17);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 50, 5);
		i++;
		
		//ETAGE 1
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 61, 42);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 65, 41);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 56, 43);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 94, 54);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 94, 57);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 24, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 30, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 24, 49);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 30, 49);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 62, 62);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 68, 62);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 2, 58);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 35, 62);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 4, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 14, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 9, 50);
		i++;
		
		//ETAGE 2
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 2, 89);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 36, 83);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 47, 83);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 36, 95);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 47, 95);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul3(gp, 16, 81);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul3(gp, 7, 96);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul3(gp, 10, 82);
		i++;
		
		
		
		//CAVE
		mapNum = 3;
		i = 0;
		gp.monster[mapNum][i] = new MON_Kramul3(gp, 63, 88);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul3(gp, 69, 88);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 63, 80);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 69, 80);
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp, 2, 97);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 1, 93);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 3, 76);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 16, 93);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 22, 94);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 31, 74);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 18, 78);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 13, 69);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 9, 60);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 13, 56);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 4, 64);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 5, 53);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 26, 71);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 31, 73);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 44, 85);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 41, 67);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 58, 57);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 59, 36);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 65, 29);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 67, 46);
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp, 5, 33);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 19, 47);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 2, 73);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 6, 45);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 23, 68);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 23, 62);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 15, 64);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 37, 90);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul1(gp, 50, 63);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 22, 32);
		i++;
		gp.monster[mapNum][i] = new MON_Kramul2(gp, 13, 40);
		i++;
		
	}
	
	public void setInteractiveTile() {
		int i = 0;
		int mapNum = 0;
		
		gp.iTile[mapNum][i] = new IT_Tree(gp, 11, 62);
		i++;
		gp.iTile[mapNum][i] = new IT_Tree(gp, 52, 65);
		i++;
		gp.iTile[mapNum][i] = new IT_Tree(gp, 47, 53);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 64);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 69);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 34, 92);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 93);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 52, 76);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 56, 81);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 87, 89);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 38, 14);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 40, 13);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 42, 13);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 44, 14);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 46, 13);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 53, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 55, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 57, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 59, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 61, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 63, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 65, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 67, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 71, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 73, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 75, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 77, 8);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 54, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 56, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 58, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 60, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 62, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 64, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 66, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 68, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 70, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 72, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 74, 10);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 57, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 59, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 61, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 63, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 65, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 67, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 69, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 71, 12);
		i++;
		
		
		

		
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 17, 89);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 17, 91);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 57, 74);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 57, 76);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 43, 47);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 43, 49);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 41, 41);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 41, 43);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 44, 30);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 44, 32);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 36, 33);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 36, 35);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 53, 18);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 53, 20);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 45, 24);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 45, 26);
		i++;
		gp.iTile[mapNum][i] = new IT_BigTree(gp, 47, 19);
		i++;
		gp.iTile[mapNum][i] = new IT_Trunk(gp, 47, 21);
		i++;
		
		gp.iTile[mapNum][i] = new IT_PineTree(gp, 18, 36);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTrunk(gp, 18, 42);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTree(gp, 20, 27);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTrunk(gp, 20, 33);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTree(gp, 21, 20);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTrunk(gp, 21, 26);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTree(gp, 30, 81);
		i++;
		gp.iTile[mapNum][i] = new IT_PineTrunk(gp, 30, 87);
		i++;
		
		
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 68);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 68);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 69);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 70);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 71);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 72);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 72);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 14, 94);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 13, 94);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 94);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 13, 93);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 93);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 93);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 92);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 30, 80);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 31, 76);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 80);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 12, 81);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 11, 82);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 42, 88);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 45, 88);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 60, 94);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 61, 93);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 61, 40);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 61, 39);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 62, 38);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 62, 39);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 62, 40);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 63, 38);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 63, 39);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 63, 40);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 54, 38);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 39);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 41);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 42);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 45);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 47);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 49);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 50);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 53);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 54);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 56);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 57);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 59);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 53, 60);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 58);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 57);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 56);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 50);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 50, 49);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 49);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 50, 48);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 50, 47);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 49, 47);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 49, 46);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 49, 45);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 49, 44);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 50, 43);
		i++;
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 42);
		i++;
		
		gp.iTile[mapNum][i] = new IT_Bush(gp, 51, 38);
		i++;
	}
	
	public void resetMoveableObject() {
		int mapNum = 2;
		int i =1;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 96, 13);
		i++;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 97, 18);
		gp.obj[mapNum][i].setColor(OBJ_Thrash.green);
		i++;
		gp.obj[mapNum][i] = new OBJ_Thrash(gp, 91, 23);
		gp.obj[mapNum][i].setColor(OBJ_Thrash.yellow);
		i++;
	}

}
