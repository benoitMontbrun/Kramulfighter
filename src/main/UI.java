package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Arrow;
import object.OBJ_Bow;
import object.OBJ_BubblePistol;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_Key;
import projectile.Bubble;



public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B, police;
	BufferedImage heart_full, heart_half, heart_blank, coin, arrow, bubble;
	BufferedImage wallpaper, wallpaper2;
	public boolean messageOn = false;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	
	public boolean gameFinished = false;
	public String currentDialogue;
	public int commandNum = 0;
	
	public boolean drawSpotName;
	public String spotName;
	
	public int titleScreenState = 0;
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	int counter = 0;
	public int spotNameCounter = 0;
	public Entity npc;
	public Entity wall;
	int charIndex = 0;
	String combinedText = "";
	
	 
	

	public UI(GamePanel gp) {
		this.gp =gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		
		InputStream is = getClass().getResourceAsStream("/font/MotionControl-Bold.otf");
		try {
			police = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch (FontFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		wall = new Entity(gp);
		wallpaper = wall.setup("/photo/artagnan", gp.screenWidth, gp.screenHeight);
		wallpaper2 = wall.setup("/photo/artagnan2", gp.screenWidth, gp.screenHeight);
		
		OBJ_Heart heart = new OBJ_Heart(gp, 0, 0);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;

		OBJ_Coin euro = new OBJ_Coin(gp, 0, 0);
		coin = euro.down0;
		
		OBJ_Arrow arrowObj = new OBJ_Arrow(gp, 0, 0);
		arrow = arrowObj.down0;
		
		Bubble bubbleObj = new Bubble(gp);
		bubble = bubbleObj.down1;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(police);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		
		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
			drawSpotName(spotName);
		}
		
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		
		// CHARACTER STATE
		else if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true );
		}
		
		// OPTION STATE
		else if (gp.gameState == gp.optionState) {
			drawOptionScreen();
		}
		
		// GAME OVER STATE
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
		// TRANSITION STATE
		if (gp.gameState == gp.transitionState) {
			drawTransitionState();
		}
		
		// TRADE STATE
		if (gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
//		
//		// SLEEP STATE
//		if (gp.gameState == gp.sleepState) {
//			drawSleepScreen();
//		}
	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
	    i = 0;
	    
	    //DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		//DRAW COINS
		x = gp.tileSize*19;
		y = gp.tileSize + 10;
	    i = 0;
	    
	    g2.setColor(new Color(100, 78, 0));
	    g2.setFont(g2.getFont().deriveFont(40f));
	    String money = "" + gp.player.coin;
	    x = getXforAlignToRightText(money, x);
	    
	    g2.drawString(money, x, y);
	    y = gp.tileSize/2;
	    x = gp.tileSize*19;
	    g2.drawImage(coin, x, y, null);
	    
	    //DRAW PROJECTILE
	    if (gp.player.currentShootWeapon != null) {
	    	
	    	String projNumber = "";
	    	BufferedImage image = null;
	    	if (gp.player.currentShootWeapon.name == OBJ_Bow.objName) {
	    		projNumber = "" + gp.player.arrowNum;
	    		image = arrow;
	    	}
	    	else if (gp.player.currentShootWeapon.name == OBJ_BubblePistol.objName) {
	    		projNumber = "" + gp.player.bubbleNum + "mL";
	    		image = bubble;
	    	}
	    	
		    y = gp.tileSize * 2 + 34;
		    g2.setColor(Color.white);
		    g2.setFont(g2.getFont().deriveFont(40f));
		    x = getXforAlignToRightText(projNumber, x);
		    
		    g2.drawString(projNumber, x, y);
		    y = gp.tileSize*2;
		    x = gp.tileSize*19;
		    g2.drawImage(image, x, y, null);
	    }

	    
	}
	
	public void drawSpotName(String text) {
		if (drawSpotName) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			int x = getXforCenteredText(text);
			int y = gp.tileSize;
			//SHADOW
			g2.setColor(Color.black);
			g2.drawString(text, x + 5, y + 5);
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			spotNameCounter++;
			if (spotNameCounter >= 180) {
				spotNameCounter = 0;
				drawSpotName = false;
			}
		}

	}
	
	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		
		for (int i = 0; i < message.size(); i++) {
			if (message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;  //messageCounter[i]++
				messageCounter.set(i, counter);
				messageY += 50;
				
				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen() {
		
		if (titleScreenState == 0) {
			g2.drawImage(wallpaper, 0, 0, gp.screenWidth, gp.screenHeight, null);
			
			//TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Kramulfighter";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			
			//SHADOW
			g2.setColor(Color.black);
			g2.drawString(text, x + 5, y + 5);
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//JOYEUX NOEL
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			text = "Joyeux Noël!";
			y += gp.tileSize*2;
			x = getXforCenteredText(text);
			
			g2.setColor(Color.black);
			g2.drawString(text, x + 2, y + 2);
			g2.setColor(new Color(164, 148, 45));
			g2.drawString(text, x, y);
			
			//XAVIER IMAGE
			x = gp.screenWidth/2 - gp.tileSize;
			y += gp.tileSize;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			g2.setColor(Color.white);
			
			text = "NOUVELLE PARTIE";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			text = "CHARGER PARTIE";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			text = "QUITTER";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
		}
		
		else if (titleScreenState == 1) {
			g2.drawImage(wallpaper2, 0, 0, gp.screenWidth, gp.screenHeight, null);
			
			// CLASS SELECTION SCREEN
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(48F));
			
			String text = "Choisis ton personnage !";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			g2.drawString(text, x, y);
			
			g2.setColor(Color.white);
			text = "Xavier";
			x = getXforCenteredText(text);
			y += gp.tileSize * 4; 
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			text = "Xavier";
			x = getXforCenteredText(text);
			y += gp.tileSize; 
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			text = "Xavier";
			x = getXforCenteredText(text);
			y += gp.tileSize; 
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			text = "RETOUR";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2; 
			g2.drawString(text, x, y);
			if (commandNum == 3) {
				g2.drawString(">", x - gp.tileSize/2, y);
			}
			
			
		}
		

	}
	
	public void drawPauseScreen() {
		final int frameX = gp.screenWidth/2 - gp.tileSize * 3;
		final int frameY = gp.screenHeight/2 - gp.tileSize * 3;
		final int frameWidth = gp.tileSize * 6;
		final int frameHeight = gp.tileSize * 2;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(64F));
		
		String text = "PAUSE";

		int y = gp.screenHeight/2 - gp.tileSize - 28;
		int x = getXforCenteredText(text);
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		//Npc name
		int x = gp.tileSize*8;
		int y = 10;
		int width = gp.tileSize*4;
		int height = gp.tileSize;
		if (npc.type == 1) {
			
			drawSubWindow(x, y, width, height);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
			x = getXforCenteredText(npc.name);
			y = gp.tileSize - 5;
			g2.drawString(npc.name, x, y);
		}

		// WINDOW
		x = gp.tileSize * 3;
		y = gp.tileSize;
		width = gp.screenWidth - gp.tileSize * 6;
		height = gp.tileSize * 3;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
		x = gp.tileSize*4;
		y = gp.tileSize*2;
		
		if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
//			currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
			
			char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
			
			if (charIndex < characters.length) {
				//gp.playSE(17);
				String s = String.valueOf(characters[charIndex]);
				combinedText = combinedText + s;
				currentDialogue = combinedText;
				charIndex++;
			}
			
			if (gp.keyH.enterPressed) {
				
				charIndex = 0;
				combinedText = "";
						
				if (gp.gameState == gp.dialogueState) {
					npc.dialogueIndex++;
					gp.keyH.enterPressed = false;
				}
			}
		}
		else {
			npc.dialogueIndex = 0;
			if (gp.gameState == gp.dialogueState) {
				gp.gameState = gp.playState;
			}
		}
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 35;
		}
		
		
		
	}
	
	public void drawCharacterScreen() {
		
		// CREATE A FRAME
		final int frameX = gp.tileSize * 2;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Niveau", textX, textY);
		textY += lineHeight;
		g2.drawString("Vie", textX, textY);
		textY += lineHeight;
		g2.drawString("Attaque", textX, textY);
		textY += lineHeight;
		g2.drawString("Défense", textX, textY);
		textY += lineHeight;
		g2.drawString("XP", textX, textY);
		textY += lineHeight;
		g2.drawString("Euros", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Armes", textX, textY);

		
		// VALUES
		int tailX = frameX + frameWidth - 30;
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.exp + "/" + gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		
		textY += lineHeight;
		if (gp.player.currentWeapon != null) {
			g2.drawImage(gp.player.currentWeapon.down0, tailX - gp.tileSize, textY - 24, null);
		}
		textY += 2*lineHeight;
		if (gp.player.currentShootWeapon != null) {
			g2.drawImage(gp.player.currentShootWeapon.down0, tailX - gp.tileSize, textY - 24, null);
		}
		
		//Munitions
		int x = frameX + gp.tileSize;
		int y = textY + 3*lineHeight;
		
	    g2.setColor(Color.white);
	    g2.setFont(g2.getFont().deriveFont(40f));
	    String text = "" + gp.player.arrowNum;
	    x = getXforAlignToRightText(text, x);
	    g2.drawString(text, x, y);
	    x = frameX + gp.tileSize;
	    y -= gp.tileSize;
		g2.drawImage(arrow, x, y, null);
		
		x += gp.tileSize*3;
		y = textY + 3*lineHeight;
		text = "" + gp.player.bubbleNum + "mL";
	    x = getXforAlignToRightText(text, x);
	    g2.drawString(text, x, y);
	    x = frameX + gp.tileSize*4 ;
	    y -= 40;
		g2.drawImage(bubble, x, y, null);
	} 
	
	public void drawInventory(Entity entity, boolean cursor) {
		
		// NPC OR PLAYER?
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if (entity == gp.player) {
			frameX = gp.tileSize * 12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize * 2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize * 6;
			frameHeight = gp.tileSize * 5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		// FRAME

		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		int col = 0;
		int row = 0;
		
		// DRAW PLAYER'S ITEMS
		for (Entity object : entity.inventory) {
			
			// EQUIP CURSOR
			if (object == entity.currentWeapon || object == entity.currentShootWeapon || object == entity.currentLight) {
				g2.setColor(new Color(240, 190, 90)); 
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(object.down0, slotX, slotY, null);
			
			//DISPLAY AMOUNT
			if (entity == gp.player && object.amount > 1) {
				
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				
				String s = "" + object.amount;
				amountX = getXforAlignToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				
				//Shadow
				g2.setColor(new Color(60, 60, 60));
				g2.drawString(s, amountX, amountY);
				//Number
				g2.setColor(Color.white);
				g2.drawString(s, amountX - 3, amountY - 3);
				
			}
			
			slotX += slotSize;
			col++;
			
			if (col == 5) {
				col = 0;
				row++;
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		// CURSOR
		if (cursor) {
			int cursorX = slotXstart + slotSize * slotCol;
			int cursorY = slotYstart + slotSize * slotRow;
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// DESCRIPTION FRAME
			final int dFrameX = frameX;
			final int dFrameY = frameY + frameHeight;
			final int dFrameWidth = frameWidth;
			final int dFrameHeight = gp.tileSize * 3;
			
			// DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(32F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			if (itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
	}
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		
		text = "Game Over";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize * 4;
		g2.drawString(text, x, y);
		//Main text
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Rejouer";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
		
		//Back to title
		text = "Quitter";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	
	public void drawOptionScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		//SUB WINDOW
		int frameX = gp.tileSize * 6;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_fullScreenNotification(frameX, frameY);
			break;
		case 2:
			options_control(frameX, frameY);
			break;
		case 3:
			options_endGameConfirmation(frameX, frameY);
			break;
		}
		
		
		gp.keyH.enterPressed = false;
	}
	
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//TITLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//FULL SCREEN
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 2;
		g2.drawString("Plein Ecran", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				gp.fullScreenOn = !gp.fullScreenOn;
				subState = 1;
			}
		}
		
		//MUSIC
		textY += gp.tileSize;
		g2.drawString("Musique", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		
		//SE
		textY += gp.tileSize;
		g2.drawString("Son", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		
		//CONTROL
		textY += gp.tileSize;
		g2.drawString("Contrôles", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		//END GAME
		textY += gp.tileSize;
		g2.drawString("Quitter", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		//BACK
		textY += gp.tileSize*2;
		g2.drawString("Retour", textX, textY);
		if (commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//FULL SCREEN CHECKBOX
		textX = frameX + gp.tileSize * 4 + 24;
		textY = frameY + gp.tileSize * 2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (gp.fullScreenOn) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		//MUSIC VOLUME
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24 * gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//SE
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24 * gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		gp.config.saveConfig();
		
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Le changement prendra\neffet après le redémarrage\ndu jeu.";
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//BACK
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Retour", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 0; 
			}
		}
	}
	
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//TITLE
		String text = "Contrôle";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Bouger", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Confirmer/Attaquer", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Tirer", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Inventaire", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		
		textX = frameX + gp.tileSize * 6;
		textY = frameY + gp.tileSize * 2;
		g2.drawString("ZQSD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		g2.drawString("F", textX, textY);
		textY += gp.tileSize;
		g2.drawString("E", textX, textY);
		textY += gp.tileSize;
		g2.drawString("P", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ECHAP", textX, textY);
		textY += gp.tileSize;
		
		//BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Retour", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 3;
		
		currentDialogue = "Quitter et retourner au";
		g2.drawString(currentDialogue, textX, textY);
		textY += 32;
		currentDialogue = "menu principal.";
		textX = getXforCenteredText(currentDialogue);
		g2.drawString(currentDialogue, textX, textY);
		
		//YES
		String text = "Oui";
		textX = getXforCenteredText(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 0;
				gp.gameState = gp.titleState;
				gp.resetGame(true);
			}
		}
		
		//NO
		text = "Non";
		textX = getXforCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed) {
				subState = 0;
				commandNum = 4;
			}
		}
	}

	
	public void drawTransitionState() {
		
		counter++;
		g2.setColor(new Color(0, 0, 0, counter*5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		if (counter >= 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.eHandler.tempCol * gp.tileSize;
			gp.player.worldY = gp.eHandler.tempRow * gp.tileSize;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
		}
	}
	
//	public void drawSleepScreen() {
//		counter++;
//		
//		if (counter < 120) {
//			gp.eManager.lighting.filterAlpha += 0.01f;
//			if (gp.eManager.lighting.filterAlpha > 1f) {
//				gp.eManager.lighting.filterAlpha = 1f;
//			}
//		}
//		else if (counter >= 120) {
//			gp.eManager.lighting.filterAlpha -= 0.01f;
//			if (gp.eManager.lighting.filterAlpha <= 0f) {
//				gp.eManager.lighting.filterAlpha = 0f;
//				counter = 0;
//				gp.eManager.lighting.dayState = gp.eManager.lighting.day;
//				gp.eManager.lighting.dayCounter = 0;
//				gp.gameState = gp.playState;
//				gp.player.getImage();
//			}
//		}
//			
//	}
	
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0:
			trade_select();
			break;
		case 1:
			trade_buy();
			break;
		case 2:
			trade_sell();
			break;
		}
		gp.keyH.enterPressed = false;
	}
	
	public void trade_select () {
		
		npc.dialogueSet = 0;
		drawDialogueScreen();
		
		//DRAW WINDOW
		int x = gp.tileSize * 15;
		int y = gp.tileSize * 4;
		int width = gp.tileSize * 4;
		int height = gp.tileSize * 3 + 24; 
		drawSubWindow(x, y, width, height);
		
		//DRAW TEXT
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Acheter", x, y);
		if (commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed) {
				subState = 1;
			}
		}
		y += gp.tileSize;
		g2.drawString("Vendre", x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed) {
				subState = 2;
			}
		}
		y += gp.tileSize;
		g2.drawString("Quitter", x, y);
		if (commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if (gp.keyH.enterPressed) {
				commandNum = 0;
				npc.startDialogue(npc, 1);
			}
		}
	}
	
	public void trade_sell() {
		
		//DRAW PLAYER INVENTORY
		drawInventory(gp.player, true);
		
		//DRAW HINT WINDOW
		int x  = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] : Retour", x + 24, y + 60);
		
		//DRAW PLAYER COIN WINDOW
		x  = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Pépettes : " + gp.player.coin, x + 24, y + 60);
		
		//DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if (itemIndex < gp.player.inventory.size()) {
			x = gp.tileSize * 15 + 24;
			y = gp.tileSize * 5 + 24;
			width = gp.tileSize * 2 + 24;
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
			g2.drawString(text, x, y + 34);
			
			//SELL AN ITEM
			if (gp.keyH.enterPressed) {
				if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || 
						gp.player.inventory.get(itemIndex) == gp.player.currentShootWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentLight) {
					
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 4);
				}
				else if (gp.player.inventory.get(itemIndex).name == OBJ_Key.objName) {
					commandNum = 0;
					subState = 0;
					npc.startDialogue(npc, 5);
				}
				else {
					if (gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}
	}
	
	public void trade_buy() {
		
		//DRAW PLAYER INVENTORY
		drawInventory(gp.player, false);
		//DRAW NPC INVENTORY
		drawInventory(npc, true);
		
		//DRAW HINT WINDOW
		int x  = gp.tileSize * 2;
		int y = gp.tileSize * 9;
		int width = gp.tileSize * 6;
		int height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] : Back", x + 24, y + 60);
		
		//DRAW PLAYER COIN WINDOW
		x  = gp.tileSize * 12;
		y = gp.tileSize * 9;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Euros : " + gp.player.coin, x + 24, y + 60);
		
		//DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if (itemIndex < npc.inventory.size()) {
			x = gp.tileSize * 5 + 24;
			y = gp.tileSize * 5 + 24;
			width = gp.tileSize * 2 + 24;
			height = gp.tileSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
			g2.drawString(text, x, y + 34);
		}
		
		//DRAW Munitions
		x = gp.tileSize*12;
		y = gp.tileSize*6;
		width = gp.tileSize * 6;
		height = gp.tileSize * 2;
		drawSubWindow(x, y, width, height);
		
		x = gp.tileSize*13 + 24;
		y = gp.tileSize*7 + 24;
	    g2.setColor(Color.white);
	    g2.setFont(g2.getFont().deriveFont(40f));
	    String text = "" + gp.player.arrowNum;
	    x = getXforAlignToRightText(text, x);
	    g2.drawString(text, x, y);
	    x = gp.tileSize*13 + 24;
	    y -= gp.tileSize;
		g2.drawImage(arrow, x, y, null);
		
		x += gp.tileSize*3 + 24;
		y = gp.tileSize*7 + 24;
		text = "" + gp.player.bubbleNum + "mL";
	    x = getXforAlignToRightText(text, x);
	    g2.drawString(text, x, y);
	    x = gp.tileSize*16 + 40;
	    y -= 40;
		g2.drawImage(bubble, x, y, null);
		
		//BUY AN ITEM
		if (gp.keyH.enterPressed && itemIndex < npc.inventory.size()) {
			if (npc.inventory.get(itemIndex).price > gp.player.coin) {
				subState = 0;
				npc.startDialogue(npc, 2);
			}
			else {
				if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
					gp.player.coin -= npc.inventory.get(itemIndex).price;
				}
				else {
					subState = 0;
					npc.startDialogue(npc, 3);
				}
			}
		}
	}
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + slotRow * 5;
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 5, 30, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5)); 
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		
		
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
	
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
	}

}
