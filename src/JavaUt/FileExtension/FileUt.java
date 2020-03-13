/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.FileExtension;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class FileUt {
  
        public ArrayList<String> getFolderSubPaths(String dir) {

        ArrayList<String> SubFiles = new ArrayList<>();
        

         for(File file : new File(dir).listFiles()){
             
         SubFiles.add(file.getAbsolutePath());
             
         }
         
         for(int i=0;i<SubFiles.size();i++){
         File[] anFile = new File(SubFiles.get(i)).listFiles();
          if(anFile != null){
         
         for(File file : anFile){
         SubFiles.add(file.getAbsolutePath());   
         }   
         }

         }
        
    return SubFiles;
    }
     public ArrayList<File> getFolderSubFiles(String dir) {

        ArrayList<File> SubFiles = new ArrayList<>();
        

         for(File file : new File(dir).listFiles()){
             
         SubFiles.add(file);
             
         }
         
         for(int i=0;i<SubFiles.size();i++){
         File[] anFile = new File(SubFiles.get(i).getAbsolutePath()).listFiles();
          if(anFile != null){
         
         for(File file : anFile){
         SubFiles.add(file);   
         }   
         }

         }
        
    return SubFiles;
    }
}
