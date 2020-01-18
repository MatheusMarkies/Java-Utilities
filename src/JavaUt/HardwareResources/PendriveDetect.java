/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.HardwareResources;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class PendriveDetect extends Thread{
    
    static  String[] letters = new String[]{ "A", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
    static File[] drives = new File[letters.length];
    
    static String Pendrive_Folder;
    static boolean[] isDrive = new boolean[letters.length];
    
    public PendriveDetect(){

for ( int i = 0; i < letters.length; ++i )
    {
    drives[i] = new File(letters[i]+":/");

    isDrive[i] = drives[i].canRead();
// isDrive[i] = drives[i].canWrite();
}

 System.out.println("FindDrive: waiting for devices...");

 while(true)
    {
   
    for ( int i = 0; i < letters.length; ++i )
        {
        boolean pluggedIn = drives[i].canRead();

        
        if ( pluggedIn != isDrive[i] )
            {
            if (pluggedIn)
            System.out.println("Drive "+letters[i]+" has been plugged in");            
     
            Pendrive_Folder = letters[i]+":\\";
   
            isDrive[i] = pluggedIn;
            }
        }

  }

  }
    
    public static String getPendriveFolder(){
    return Pendrive_Folder;
    }
    public static boolean[] getPluggedIn(){
    return isDrive;
    }
    
}
