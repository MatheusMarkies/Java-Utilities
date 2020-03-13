/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.TextFileUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class Read {
    
    static File TXT_File = new File("FilePath\\FileName.txt");
    static FileReader Read_txt;
    
    public static String LineContent;
    //public static ArrayList<String> TXT_Content = new ArrayList<String>();
    
    static int LineNumbers;
    static int ReadLine;
    static boolean ReadBool;
    
    public static ArrayList<String> readFile(File file) throws FileNotFoundException{
        
       ArrayList<String> TXT_Content = new ArrayList<String>();
   
       Scanner Reader = new Scanner(file);
       
        while (Reader.hasNext()) {
            String next = Reader.next();
            TXT_Content.add(next);
        }
   
        return TXT_Content;
    }
    
    public static String getLineContent(File file,int Line,int LineIndex){
        
        Scanner Reader = null;
        
        if(ReadLine == 0){
            TXT_File = file;
            LineNumbers = LineIndex;
            ReadBool = true;
        }
        
        while (ReadBool) {  
        try {
            Read_txt = new FileReader(TXT_File);
            Reader  = new Scanner(TXT_File);
            
            LineContent = Reader.nextLine();
            
            if(ReadLine == Line){
            Reader.close();
            ReadLine = 0;
            ReadBool = false;
            }else{
            ReadLine += 1;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return LineContent;
  }
    
}
