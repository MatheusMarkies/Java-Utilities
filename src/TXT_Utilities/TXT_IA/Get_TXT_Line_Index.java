/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TXT_Utilities.TXT_IA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class Get_TXT_Line_Index {
    
    static int LineIndex;
    
    static String LineContent;
    static Scanner Reader;
    static FileReader Read_txt;
    
    public static void Get_TXT_File_LineIndex(File file) throws FileNotFoundException{
        
        Read_txt = new FileReader(file);
        Reader  = new Scanner(file);
        
        LineIndex = 0;
        
        while (true) {
            
            try{
            LineContent = Reader.nextLine();
            LineIndex++;
            }catch(Exception e){
                
                System.out.println("Line Number: "+LineIndex);
                break;
                
            }
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Get_TXT_Line_Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
   
    
}
