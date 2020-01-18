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
public class Write {
    
    public Write (File file,String Text) throws IOException{
        
        FileWriter IndexWriter = null;
        IndexWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(IndexWriter);
            
        bw.write(Text+"\n");
        bw.close();  
        
    }
    
        public Write (File file,String[] Texts) throws IOException{
        
        FileWriter IndexWriter = null;
        IndexWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(IndexWriter);
        for(int i = 0;i<Texts.length;i++){    
        bw.write(Texts[i]+"\n");
        }
        bw.close();  
        
    }
    
}
