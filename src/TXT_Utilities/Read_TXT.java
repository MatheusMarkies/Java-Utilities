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
    
    static String LineContent;
    
    static ArrayList<String> TXT_Content = new ArrayList<String>();
    
    static Scanner Reader;
    
    static int LineNumbers;
    static int ReadLine;
    
    public static void Read_TXT_File(){
        
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
        }
        
    }
    
    public static void Get_TXT_Line_Content(int Line){
        
        try {
            Read_txt = new FileReader(TXT_File);
            Reader  = new Scanner(TXT_File);
            
            LineContent = Reader.nextLine();
            
            if(ReadLine == Line){
            Reader.close();
            }else{
            ReadLine += 1;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Read_TXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    
}
