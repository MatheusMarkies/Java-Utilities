/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.TextFileUtilities.FileIA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class GetLineIndex {
    
    static int LineIndex;
    
    static String LineContent;
    static Scanner Reader;
    static FileReader Read_txt;
    
    public static int GetLineIndex(File file) throws FileNotFoundException{
        
        Read_txt = new FileReader(file);
        Reader  = new Scanner(file);
        
        LineIndex = 0;
        
        while (true) {
            
            try{
            LineContent = Reader.nextLine();
            LineIndex++;
            }catch(Exception e){
                //System.out.println("Line Number: "+LineIndex);
                Reader.close();
                break;
            }
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(GetLineIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return LineIndex;
    }
   
    
}
