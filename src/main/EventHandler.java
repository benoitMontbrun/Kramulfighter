package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import data.AreaCoordinates;
import entity.Entity;
import object.OBJ_Key;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	ArrayList<AreaCoordinates> eventList;
	ArrayList<AreaCoordinates> areaList;

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	boolean guardianDoorOpened = false;
	public boolean kitchenDoorOpened = false;
	public boolean houseDoorOpened = false;
	

	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		eventList = new ArrayList<AreaCoordinates>();
		areaList = new ArrayList<AreaCoordinates>();
		
		//Initialize eventList with file events.txt
		InputStream is = getClass().getResourceAsStream("/maps/events.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		int[] coordinates;
		String direction;
		String spotName;
		boolean display;
		try {
			while((spotName = br.readLine()) != null ) {
				line = br.readLine();
				coordinates = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
				display = (br.readLine().equals("true"));
				AreaCoordinates areacoo = new AreaCoordinates(coordinates, "any", spotName, display);
				eventList.add(areacoo);
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Initialize areaList with file areas.txt
		is = getClass().getResourceAsStream("/maps/areas.txt");
		br = new BufferedReader(new InputStreamReader(is));
		try {
			while((spotName = br.readLine()) != null ) {
				line = br.readLine();
				coordinates = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
				direction = br.readLine();
				AreaCoordinates areacoo = new AreaCoordinates(coordinates, direction, spotName, true);
				areaList.add(areacoo);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Initialize all EventRect for event detection and area detection
		int map = 0;
		int col = 0;
		int row = 0;
		for (AreaCoordinates areacoo : eventList) {
			map = areacoo.getCoo()[0];
			col = areacoo.getCoo()[1];
			row = areacoo.getCoo()[2];
			initializeEventRect(map, col, row);
		}
		for (AreaCoordinates areacoo : areaList) {
			map = areacoo.getCoo()[0];
			col = areacoo.getCoo()[1];
			row = areacoo.getCoo()[2];
			initializeEventRect(map, col, row);
		}
		initializeEventRect(0, 53, 87);
		initializeEventRect(2, 75, 29);
		
		initializeEventRect(0, 43, 89);
		initializeEventRect(0, 44, 89);
		initializeEventRect(2, 29, 29);
		initializeEventRect(2, 30, 29);
		
	}
	
	public void initializeEventRect(int map, int col, int row) {
		eventRect[map][col][row] = new EventRect();
		eventRect[map][col][row].x = 14;
		eventRect[map][col][row].y = 14;
		eventRect[map][col][row].width = 20;
		eventRect[map][col][row].height = 20;
		eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
		eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
	}
	
	public void setDialogue(String key) {
		
	eventMaster.dialogues[0][0] = "Il te faut la " + key + ".";
	eventMaster.dialogues[1][0] = "Vous utilisez la " + key + " et\nouvrez la porte.";
	}
	
	public void checkEvent() {
		
		// CHECK CHARACHTER DISTANCE
		
		int xDistance  = Math.abs(gp.player.worldX - previousEventX);
		int yDistance  = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent) {
			
			for (AreaCoordinates areacoo : eventList) {
				if (hit(areacoo.getCoo()[0], areacoo.getCoo()[1], areacoo.getCoo()[2], areacoo.getDirection())) {
					teleport(areacoo.getCoo()[3], areacoo.getCoo()[4], areacoo.getCoo()[5], areacoo.getCoo()[3], 
							 areacoo.isDisplay(), areacoo.getSpotName());
				}
			}
			
			if (hit(0, 53, 87, "any")) { // Maison du gardien
				if (guardianDoorOpened || hasKey(AssetSetter.key_guardian)) {
					teleport(1, 3, 17, gp.indoor, true, "Maison du gardien");
				}
			}
			else if (hit(2, 75, 29, "any")) { // Cuisine
				if (kitchenDoorOpened) {
					teleport(0, 39, 67, gp.outside, true, "Jardin");
				}
				else {
					if (hasKey(AssetSetter.key_kitchen)) {
						gp.obj[0][1].setOpened();
						kitchenDoorOpened = true;
						teleport(0, 39, 67, gp.outside, true, "Jardin");
					}
					
				}
			}
			else if (hit(2, 29, 29, "any") || hit(2, 30, 29, "any")) { // Entrée principale
				if (houseDoorOpened) {
					teleport(0, 27, 67, gp.outside, true, "Jardin");
				}
				else {
					if (hasKey(AssetSetter.key_arta)) {
						gp.obj[0][0].setOpened();
						houseDoorOpened = true;
						teleport(0, 27, 67, gp.outside, true, "Jardin");
					}
					
				}
			}
		}	
		
	}
	
	public void checkChangeArea() {
		for (AreaCoordinates areacoo : areaList) {
			if (hit(areacoo.getCoo()[0], areacoo.getCoo()[1], areacoo.getCoo()[2], areacoo.getDirection())) {
				gp.ui.spotName = areacoo.getSpotName();
				gp.ui.drawSpotName = true;
				gp.ui.spotNameCounter = 0;
			}
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if (map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
			
			if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false	) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}	
		
		return hit;
	}

	public boolean hasKey(String key) {
		int i = gp.player.searchItemInInventory(OBJ_Key.objName, key);
		setDialogue(key);
		if (i != 999) { 
			gp.player.inventory.remove(i);
			gp.playSE(5);
			eventMaster.startDialogue(eventMaster, 1);
			if (key == AssetSetter.key_guardian) {
				guardianDoorOpened = true;
			}
			return true;
		}
		else {
			eventMaster.startDialogue(eventMaster, 0);
			canTouchEvent = false;
			return false;
		}
	}
	
	public void teleport(int map, int col, int row, int area, boolean displayArea, String spotName) {
		
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		
		gp.player.setSpawnPoint(col*gp.tileSize, row*gp.tileSize);
		//gp.saveLoad.save();
		
		canTouchEvent = false;
		gp.playSE(5);
		
		if (displayArea) {
			gp.ui.spotName = spotName;
			gp.ui.drawSpotName = true;
		}
	}
	
	public void speak(Entity entity) {
		
		if (gp.keyH.enterPressed) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
			
		}
	}
	
	
}
