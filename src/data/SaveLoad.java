package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import object.*;
import entity.Entity;
import main.GamePanel;

public class SaveLoad {

	GamePanel gp;

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	} 
	
	public void save() {
		
		try {
			//PLAYER STATS
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			DataStorage ds = new DataStorage();
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.exp = gp.player.exp;
			ds.nextLevelExp = gp.player.nextLevelExp;
			ds.coin = gp.player.coin;
			
			//PLAYER INVENTORY
			for (Entity item : gp.player.inventory) {
				ds.itemNames.add(item.name);
				ds.itemAmounts.add(item.amount);
			}
			
			//PLAYER EQUIPMENT
			ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
			
			//OBJECTS ON MAP
			ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			
			for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for (int i = 0; i < gp.obj[1].length; i++) {
					if (gp.obj[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] = "NA";
					}
					else {
						ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if (gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}
			
			//Write the DataStorage object
			oos.writeObject(ds);
		}
		catch (Exception e) {
			System.out.println("Save Exception!");
		}
		
	}
	
	
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			//Read the DataStorage
			DataStorage ds = (DataStorage)ois.readObject();
			
			//PLAYER STATS
			gp.player.level = ds.level;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.exp = ds.exp;
			gp.player.nextLevelExp = ds.nextLevelExp;
			gp.player.coin = ds.coin;
			
			//PLAYER INVENTORY
			gp.player.inventory.clear();
			for (int i = 0; i < ds.itemNames.size(); i++) {
				gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
				gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
			}
			
			//PLAYER EQUIPMENT
			gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
			gp.player.currentShootWeapon = gp.player.inventory.get(ds.currentShootWeaponSlot);
			gp.player.getAttack();
			gp.player.getAttackImage();
			
			//OBJECTS ON MAP
			for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				for (int i = 0; i < gp.obj[1].length; i++) {
					if (ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i] = null;
					
					}
					else {
						gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if (ds.mapObjectLootNames[mapNum][i] != null) {
							gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if (gp.obj[mapNum][i].opened) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}
			
			
		}
		catch (Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
