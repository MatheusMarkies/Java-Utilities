/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TXTUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Matheus Markies
 */
public class CreateTXT {
    
    public static void CreateTXTFile(File file){
        
        File TXT_File = new File("FilePatch\\FileName.txt");
        
        TXT_File = file;
        
            try {
            TXT_File.createNewFile();
            FileWriter IndexWriter = null;
            IndexWriter = new FileWriter(TXT_File.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(IndexWriter);
            
            //bw.write("Write...");
            bw.close();  
            
             } catch (IOException ex) {
                 
             }
        
    }
    
    
}
