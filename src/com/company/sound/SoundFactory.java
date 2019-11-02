package com.company.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import static com.company.constants.Constants.EXPLOSION_SOUND;
import static com.company.constants.Constants.LASER_SOUND;

public class SoundFactory {

    private Clip clip;

    public SoundFactory() {
    }

    public void laser() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(LASER_SOUND));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void explosion() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(EXPLOSION_SOUND));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
