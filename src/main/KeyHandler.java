 package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, spacePressed;
	public boolean debugOn = false;
	public boolean godModeOn = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		// TITLE STATE
		
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		
		// PLAY STATE
		else if (gp.gameState == gp.playState) {
			playState(code);
		}
		
		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
			}
		
		// CHARACTER STATE
		else if (gp.gameState == gp.characterState) {
			characterState(code);
		}
		
		// OPTION STATE
		else if (gp.gameState == gp.optionState) {
			optionState(code);
		}
		
		// GAME OVER STATE
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		
		// TRADE STATE
		else if (gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		
		// MAP STATE
		else if (gp.gameState == gp.mapState) {
			mapState(code);
		}
	}
	
	public void titleState(int code) {
		if (gp.ui.titleScreenState == 0) {
			if (code == KeyEvent.VK_Z) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				case 0:
					gp.ui.titleScreenState = 1;
					break;
				case 1:
				gp.saveLoad.load();
					gp.gameState = gp.playState;
					gp.playMusic(0);
					break;
				case 2:
					System.exit(0);
					break;
				}
			}
		}
		else if (gp.ui.titleScreenState == 1) {
			if (code == KeyEvent.VK_Z) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				switch(gp.ui.commandNum) {
				case 0:
					gp.gameState = gp.playState;
					gp.playMusic(0);
					gp.ui.titleScreenState = 0;
					break;
				case 1:
					gp.gameState = gp.playState;
					gp.playMusic(0);
					gp.ui.titleScreenState = 0;
					break;
				case 2:
					gp.gameState = gp.playState;
					gp.ui.titleScreenState = 0;
					gp.playMusic(0);
					break;
				case 3:
					gp.ui.titleScreenState = 0;
					break; 
				}
			}
		}
		
	}
 	
	public void playState(int code) {

		if (code == KeyEvent.VK_Z) {
			upPressed = true;
		}
		
		if (code == KeyEvent.VK_Q) {
			leftPressed = true;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		
		if (code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
			gp.stopMusic();
		}
		
		if (code == KeyEvent.VK_E) {
			gp.gameState = gp.characterState;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
		
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionState;
		}
		
		if (code == KeyEvent.VK_M) {
			gp.map.openMap();;
		}
		
		if (code == KeyEvent.VK_X) {
			gp.map.openMiniMap();
		}
		
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		// DEBUG
		
		if (code == KeyEvent.VK_T) {
			debugOn = !debugOn;
		}
		if (code == KeyEvent.VK_O) {
			godModeOn = !godModeOn;
		}
	}
	
	public void pauseState(int code) {
		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
			gp.playMusic(0);
		}
	}
	
	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}
	
	public void characterState(int code) {
		if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		playerInventory(code);
	}

	public void optionState(int code) {
		
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0:
			maxCommandNum = 5;
			break;
		case 3:
			maxCommandNum = 1;
			break;
		}
		
		if (code == KeyEvent.VK_Z) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
		if (code == KeyEvent.VK_Q) {
			if (gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				else if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.se.checkVolume();
					gp.playSE(9);
				}
			}
		}
		
		if (code == KeyEvent.VK_D) {
			if (gp.ui.subState == 0) {
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(9);
				}
				else if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.se.checkVolume();
					gp.playSE(9);
				}
			}
		}
	}
	
	public void gameOverState(int code) {
		
		if (code == KeyEvent.VK_Z) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(9);
		}
		
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}
		
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.resetGame(false);
				gp.playAreaMusic(gp.currentArea);
			}
			else if (gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				gp.resetGame(true);
			}
		}
		
	}
	
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if (gp.ui.subState == 0) {
			if (code == KeyEvent.VK_Z) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(9);
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(9);
			}
		}
		
		if (gp.ui.subState == 1) {
			npcInventory(code);
			if (code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if (gp.ui.subState == 2) {
			playerInventory(code);
			if (code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	
	public void mapState(int code) {
		
		if (code == KeyEvent.VK_M || code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
	}
	
	public void playerInventory(int code) {
		if (code == KeyEvent.VK_Z) {
			if (gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_Q) {
			if (gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(9);
			}
		}
	}
	
	public void npcInventory(int code) {
		if (code == KeyEvent.VK_Z) {
			if (gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_S) {
			if (gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_Q) {
			if (gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(9);
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(9);
			}
		}
	}
 	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_Z) {
			upPressed = false;
		}
		
		if (code == KeyEvent.VK_Q) {
			leftPressed = false;
		}
		
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
		if (code == KeyEvent.VK_F) {
			shotKeyPressed = false;
		}
		
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}
