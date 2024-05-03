package game.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * @author 揭程
 * @version 1.0
 */
public class SoundPlayer {
    public static void playSound(String filePath, long startTime) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            long frames = (long) (startTime * format.getFrameRate() / 1000000); // 将毫秒转换为帧数

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setFramePosition((int) frames); // 设置起始位置
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}