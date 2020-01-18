/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.TextFileUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Matheus Markies
 */
public class CreateFile {
    
    public CreateFile(File file){

            try {
            file.createNewFile();
            FileWriter IndexWriter = null;
            IndexWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(IndexWriter);
            
            //bw.write("Write...");
            bw.close();  
            
             } catch (IOException ex) {
                 
             }
        
    }
    
    
}
