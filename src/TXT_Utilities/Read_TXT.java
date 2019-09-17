/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TXT_Utilities;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
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
public class Read_TXT {
    
    static File TXT_File = new File("FilePatch\\FileName.txt");
    static FileReader Read_txt;
    
    public static String LineContent;
    public static ArrayList<String> TXT_Content = new ArrayList<String>();
    
    static Scanner Reader;
    
    static int LineNumbers;
    static int ReadLine;
    static boolean ReadBool;
    
    public static void Read_TXT_File(File file,int LineIndex){
        
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
            
            TXT_Content.add(LineContent);
            
            ReadLine += 1;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Read_TXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(ReadLine == LineNumbers){
            Reader.close();
            ReadLine = 0;
            ReadBool = false;
        }
        }
        
    }
    
    public static void Get_TXT_Line_Content(File file,int Line,int LineIndex){
        
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
            Logger.getLogger(Read_TXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
  }
    
}
