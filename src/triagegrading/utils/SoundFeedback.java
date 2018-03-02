package triagegrading.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import triagegrading.controller.TriageController;

public class SoundFeedback {

    private static final String ENTERED_SOUND_TRACK = "src/enter_score_sound.wav";
    private static final String ERROR_SOUND_TRACK = "src/error_sound.wav";
    private static final String DELETE_SOUND_TRACK = "src/delete_sound.wav";
    private static final String ERROR_MESSAGE = "Error playing the audio feedback";

    public static void playEnteredSound() {
        playInANewThread(ENTERED_SOUND_TRACK);
    }

    public static void playErrorSound() {
        playInANewThread(ERROR_SOUND_TRACK);
    }

    public static void playDeleteSound() {
        playInANewThread(DELETE_SOUND_TRACK);
    }

    private static void playInANewThread(final String soundTrack) {
        Thread th = new Thread(() -> {
            try {
                play(soundTrack);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(TriageController.class.getName()).log(Level.SEVERE, ERROR_MESSAGE, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(TriageController.class.getName()).log(Level.SEVERE, ERROR_MESSAGE, ex);
            } catch (IOException ex) {
                Logger.getLogger(TriageController.class.getName()).log(Level.SEVERE, ERROR_MESSAGE, ex);
            }
        });
        th.start();
    }

    private static void play(String soundTrack) throws LineUnavailableException, 
            UnsupportedAudioFileException, IOException {
        File soundFile = new File(soundTrack);
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
        clip.open(inputStream);
        clip.start();
    }
}
