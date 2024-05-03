package game.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author 揭程
 * @version 1.0
 */
public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}