package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import projectile.Projectile;
import tile.TileManager;
import tile.Map;
import tile_interactive.InteractiveTile;


public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16;    //16*16 Tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;    // 960 pixels
	public final int screenHeight = tileSize * maxScreenRow;   // 576 pixels
	// WORLD SETTINGS
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;	
	public final int maxMap = 10;
	public int currentMap = 0;
	// FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	// FPS
	int FPS = 60;
	// SYSTEM   
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	EnvironmentManager eManager = new EnvironmentManager(this);
	Map map = new Map(this);
	SaveLoad saveLoad = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	Thread gameThread;
	
	// ENTITY & OBJECT
	public Player player = new Player(this,keyH);
	public Entity obj[][] = new Entity[maxMap][140];
	public Entity npc[][] = new Entity[maxMap][5];
	public Entity monster[][] = new Entity[maxMap][40];
	public InteractiveTile[][] iTile = new InteractiveTile[maxMap][150];
	ArrayList<Entity> entityList = new ArrayList<>();
	public Projectile projectile[][] = new Projectile[maxMap][100];
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;   
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	
	// AREA
	public int currentArea;
	public int nextArea;
	public final int outside = 0;
	public final int indoor = 1;
	public final int house = 2; 
	public final int cave = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		
		aSetter.setObject();  
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		eManager.setup();
		
		gameState = titleState;
		currentArea = outside;

		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		if (fullScreenOn) {
			setFullScreen();
		}
	}
	
	public void resetGame(boolean restart) {
		
		player.restoreStatus();
		player.setSpawnPosition();
		player.resetCounter();
		aSetter.setNPC();
		aSetter.setMonster();
		eHandler.previousEventX = player.worldX;
		eHandler.previousEventY = player.worldY;
		eHandler.canTouchEvent = false;
		stopMusic();
		
		if(restart) {
			currentArea = outside;
			player.setDefaultValues();
			aSetter.setObject(); 
			aSetter.setInteractiveTile();
			eManager.lighting.resetDay();

		}
	}
	
	
	public void setFullScreen() {
		
		//GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		//GET FULL SCREEN WIDTH AND HEIGHT
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
;		
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
			
				drawToTempScreen();
				drawToScreen();
				delta--;
			}
		}
		
	}
	
	public void playMusic(int i) {
		music.play(i);
		music.loop(i);
	}
	
	public void stopMusic() {
		music.stop(0);
		music.stop(1);
		music.stop(22);
		music.stop(27);
	}
	
	public void playSE(int i) {
		se.play(i);
	}
	
	public void playAreaMusic(int area) {
		if (area == outside) {
			playMusic(0);
		}
		else if (area == indoor) {
			playMusic(1);
		}
		else if (area == house) {
			playMusic(22);
		}
		else if (area == cave) {
			playMusic(27);
		}
	}
	
	public void changeArea() {
		
		if (map.miniMapOn && !player.hasMap[nextArea]) {
			map.miniMapOn = false;
		}
		
		if (nextArea != currentArea) {
			stopMusic();
			playAreaMusic(nextArea);
			
		}
		currentArea = nextArea;
		aSetter.setMonster();
		aSetter.resetMoveableObject();
	}
	
	public void update() {
		if (gameState == playState) {
			// PLAYER
			player.update();
			
			//NPC
			for (Entity pnj : npc[currentMap]) {
				 if (pnj != null) {
					 pnj.update();
				 }
			}
			 
			// MONSTER
			for (int i =  0; i < monster[1].length; i++) {
				 if (monster[currentMap][i] != null) {
					 if (monster[currentMap][i].alive && monster[currentMap][i].dying == false) {
						 monster[currentMap][i].update();
					 }
					 else if (monster[currentMap][i].alive == false) {
						 monster[currentMap][i].checkDrop();
						 monster[currentMap][i] = null;
					 }
 					 
				 }
			}
			
			// PROJECTILE
			for (int i =  0; i < projectile[1].length; i++) {
				 if (projectile[currentMap][i] != null) {
					 if (projectile[currentMap][i].alive) {
						 projectile[currentMap][i].update();
					 }
					 else if (projectile[currentMap][i].alive == false) {
						 projectile[currentMap][i] = null;
					 }
					 
				 }
			}
			
			// PARTICLE
			for (int i =  0; i < particleList.size(); i++) {
				 if (particleList.get(i) != null) {
					 if (particleList.get(i).alive) {
						 particleList.get(i).update();
					 }
					 else if (particleList.get(i).alive == false) {
						 particleList.remove(i);
					 }
					 
				 }
			}
			
			// INTERACTIVE TILES
			for (InteractiveTile inter : iTile[currentMap]) {
				if (inter != null) {
					inter.update();
				}
			}
			
			// ENVIRONMENT
			eManager.update();
		}
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D)g;
//		
//		player.draw(g2d);
//		g2d.dispose();
//	}
	
	public void drawToTempScreen() {
		
		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		}
		
		// MAP SCREEN
		else if (gameState == mapState) {
			map.drawFullMapScreen(g2);
		}
		
		else {
			
			g2.setColor(Color.black);
			g2.fillRect(0, 0, screenWidth, screenHeight);
			
			
			// TILE
			tileM.draw(g2);
			
			// INTERACTIVE TILE
			for (InteractiveTile inter : iTile[currentMap]) {
				if (inter != null) {
					inter.draw(g2);
				}
			}
			
			entityList.add(player);
			
			// ADD ENTITIES TO THE LIST 
			for(Entity pnj : npc[currentMap]) {
				if(pnj != null) {
					entityList.add(pnj);
				}
			}
			for(Entity object : obj[currentMap]) {
				if(object != null) {
					entityList.add(object);
				}
			}
			for(Entity mst : monster[currentMap]) {
				if(mst != null) {
					entityList.add(mst);
				}
			}
			for(Entity projectile : projectile[currentMap]) {
				if(projectile != null) {
					entityList.add(projectile);
				}
			}
			for(Entity particle : particleList) {
				if(particle != null) {
					entityList.add(particle);
				}
			}
			
			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.getBottomY(), e2.getBottomY());
					return result;
				}
				
			});
			
			// DRAW ENTITIES
			for (Entity e : entityList) {
				e.draw(g2);
			}
			
			// EMPTY ENTITY LIST 
			entityList.clear(); 
			
			// ENVIRONMENT
			eManager.draw(g2);
			
			// MINIMAP
			map.drawMiniMap(g2);
			
			// UI
			ui.draw(g2);
			
			//DEBUG
			if (keyH.debugOn) {
				
				g2.setColor(Color.white);
				g2.setFont(getFont().deriveFont(24f));
				int x = 10;
				int y = 400;
				int lineHeight = 20;
				g2.drawString("WorldX : " + player.worldX, x, y);
				y += lineHeight;
				g2.drawString("WorldY : " + player.worldY, x, y);
				y += lineHeight;
				g2.drawString("Col : " + (player.worldX + player.solidArea.x)/tileSize, x, y);
				y += lineHeight;
				g2.drawString("Row : " + (player.worldY + player.solidArea.y)/tileSize, x, y);
				y += lineHeight;
				
				if (keyH.godModeOn) {
					g2.drawString("godMode", x, y);
				}
			}
		}
	}
	
	
	
	public void drawToScreen() {
		Graphics g = getGraphics(); 
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
		
}
