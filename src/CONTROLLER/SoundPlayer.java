package CONTROLLER;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public static void playSound(String filePath) {
        try {
            // Tải file âm thanh
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            // Lấy định dạng và hệ thống phát âm thanh
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Tạo Clip và mở file âm thanh
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            // Phát âm thanh
            audioClip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Lỗi khi phát âm thanh: " + e.getMessage());
        }
    }
}
