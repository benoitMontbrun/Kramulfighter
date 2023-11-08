package main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	int soundNum = 30;
	Clip clip[] = new Clip[soundNum];
	URL soundURL[] = new URL[soundNum];
	FloatControl fc[] = new FloatControl[soundNum]; 
	int volumeScale = 4;
	float volume = 1f;
	
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/Christiana.wav");
		soundURL[1] = getClass().getResource("/sound/Guilhem.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/coin.wav");
		soundURL[4] = getClass().getResource("/sound/ouch.wav");
		soundURL[5] = getClass().getResource("/sound/unlock.wav");
		soundURL[6] = getClass().getResource("/sound/swing.wav");
		soundURL[7] = getClass().getResource("/sound/cut_bush.wav");
		soundURL[8] = getClass().getResource("/sound/cut_tree.wav");
		soundURL[9] = getClass().getResource("/sound/cursor.wav");
		
		soundURL[10] = getClass().getResource("/sound/kramul10.wav");
		soundURL[11] = getClass().getResource("/sound/kramul1.wav");
		soundURL[12] = getClass().getResource("/sound/kramul2.wav");
		soundURL[13] = getClass().getResource("/sound/kramul3.wav");
		soundURL[15] = getClass().getResource("/sound/kramul5.wav");
		soundURL[16] = getClass().getResource("/sound/kramul6.wav");
		soundURL[17] = getClass().getResource("/sound/kramul7.wav");
		soundURL[18] = getClass().getResource("/sound/kramul8.wav");
		soundURL[19] = getClass().getResource("/sound/kramul9.wav");
		
		soundURL[20] = getClass().getResource("/sound/bow.wav");
		soundURL[21] = getClass().getResource("/sound/footstep.wav");
		soundURL[22] = getClass().getResource("/sound/Sail.wav");
		soundURL[23] = getClass().getResource("/sound/bubble.wav");
		soundURL[24] = getClass().getResource("/sound/fireball.wav");
		soundURL[25] = getClass().getResource("/sound/game_over.wav");
		soundURL[26] = getClass().getResource("/sound/level_up.wav");
		soundURL[27] = getClass().getResource("/sound/Megalovania.wav");
		
		
		
		
		for (int i = 0; i < soundURL.length; i++) {
			if (soundURL[i] != null) {
				setFile(i);
			}

		}
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip[i] = AudioSystem.getClip();
			clip[i].open(ais);
			fc[i] = (FloatControl) clip[i].getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
		}
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }	
	}
	
	public void play(int i) {
		clip[i].setFramePosition(0);
		clip[i].start();
	}

	public void loop(int i) {
		clip[i].loop(Clip.LOOP_CONTINUOUSLY);
	}	
	
	public void stop(int i) {
		clip[i].stop();
	}
	
	public void checkVolume() {
		switch(volumeScale) {
		case 0:
			volume = -80f;
			break;
		case 1:
			volume = -20f;
			break;
		case 2:
			volume = -12f;
			break;
		case 3:
			volume = -5f;
			break;
		case 4:
			volume = 1f;
			break;
		case 5:
			volume = 6f;
			break;
		}
		for (FloatControl fc : fc) {
			if (fc != null) {
				fc.setValue(volume);
			}
		}
		
	}
}
