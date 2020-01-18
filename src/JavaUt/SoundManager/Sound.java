/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.SoundManager;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;

/**
 *
 * @author Matheus Markies
 */
public class Sound {
    
    private static Clip clip;
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);
        File file = null;
        
        boolean StartProcess = false;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            StartProcess = true;

        }
        if (StartProcess) {

            AudioInputStream ais = SoundFile.getAudioInputStream(file);
            Sound.PlaySound.playSoundFile(ais);
            
        }
        while (StartProcess) {           

        }
    }
    
    public static class SoundFile extends Sound{
        
        public static AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException{
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        return ais;
        }
        
    }
    
    public static class PlaySound extends Sound{

        public static void playSoundFile(AudioInputStream ais) throws LineUnavailableException, IOException {
        clip = AudioSystem.getClip();  
        clip.open(ais);
        clip.start();
        }
 
        public static Clip getAudioClip(){
        return clip;
        }
        
    }
    
}
