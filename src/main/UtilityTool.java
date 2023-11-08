package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()==0?2:original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	

    public static int[] stringListToIntList(String[] strings) {
        int[] nums = new int[strings.length];
        int i = 0;
        for (String s : strings)
            nums[i] = (int) (Integer.parseInt(s));
        	i++;
        return nums;
    }
}
