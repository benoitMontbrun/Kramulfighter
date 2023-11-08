package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {
	
	GamePanel gp;
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	
	//Day State
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;
	
	public Lighting(GamePanel gp) {
		
		this.gp = gp;
		setLightSource();

	}
	
	public void setLightSource() {
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		if(gp.player.currentLight == null) {
			g2.setColor(new Color(0, 0, 0, 0.90f));
		}
		else {
			int centerX = gp.player.screenX + (gp.tileSize)/2;
			int centerY = gp.player.screenY + (gp.tileSize)/2;
			
			Color color[] = new Color[5];
			float fraction[] = new float[5];
			
			color[0] = new Color(0, 0, 0, 0f);
			color[1] = new Color(0, 0, 0, 0.25f);
			color[2] = new Color(0, 0, 0, 0.5f);
			color[3] = new Color(0, 0, 0, 0.75f);
			color[4] = new Color(0, 0, 0, 0.90f);
			
			fraction[0] = 0f;
			fraction[1] = 0.25f;
			fraction[2] = 0.5f;
			fraction[3] = 0.75f;
			fraction[4] = 1f;
			
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius/2, fraction, color);
			g2.setPaint(gPaint);
		}
		

		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.dispose();
	}
	
	public void resetDay() {
		dayState = day;
		filterAlpha = 0f;
	}
	
	public void update() {
		
		if (gp.player.lightUpdated) {
			setLightSource();
			gp.player.lightUpdated = false;
		}
		
		//Check state of the day 
		if (dayState == day) {
			
			dayCounter++;
			if (dayCounter > 18000) {  
				dayState = dusk;
				dayCounter = 0;
			}
		}
		else if (dayState == dusk) {
			
			filterAlpha += 0.001f;
			if (filterAlpha > 1f) {
				filterAlpha = 1f;
				dayState = night;
			}
		}
		else if (dayState == night) {
			dayCounter++;
			if (dayCounter > 7200) {
				dayState = dawn;
				dayCounter = 0;
			}
		}
		else if (dayState == dawn) {
			
			filterAlpha -= 0.001f;
			if (filterAlpha < 0f) {
				filterAlpha = 0f;
				dayState = day;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		
		if (gp.currentArea == gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		}
		if (gp.currentArea == gp.outside || gp.currentArea == gp.cave) {
			g2.drawImage(darknessFilter, 0, 0, null);
		}

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
//		//DEBUG
//		String situation = "";
//		switch(dayState) {
//		case day:
//			situation = "Jour";
//			break;
//		case dusk:
//			situation = "Crépuscule";
//			break;
//		case night:
//			situation = "Nuit";
//			break;
//		case dawn:
//			situation = "Aube";
//			break;
//		}
//		g2.setColor(Color.white);
//		g2.setFont(g2.getFont().deriveFont(50f));
//		g2.drawString(situation, 800, 500);
		
	}
	
	
}




