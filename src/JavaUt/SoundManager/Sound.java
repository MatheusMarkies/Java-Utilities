/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.SoundManager;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Matheus Markies
 */
public class Sound {
    
    public static class SoundFile extends Sound{
        
        public static AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException{
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        return ais;
        }
        
    }
    
    public class playSound extends Sound{

        public playSound(AudioInputStream ais) throws LineUnavailableException, IOException {
        Clip clip = AudioSystem.getClip();  
        clip.open(ais);
        clip.start();
        }
        
    }
    
}
