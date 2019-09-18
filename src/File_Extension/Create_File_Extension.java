/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File_Extension;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Matheus Markies
 */
public class Create_File_Extension {
    
   //static String File_Extension = ".rer";//exemple
   static String Cryptography;
   static String[] Valid_Encryptions = {};
   
   static String Encryption_Key;
   
   public static void Create_Extension(String[] Content,File filePatch,String FileName,String File_Extension){
       
        File file_Extension = new File(filePatch.getAbsolutePath()+"\\"+FileName+File_Extension);
        
            try {
            file_Extension.createNewFile();
            //Set_Extension_Cryptography(Cryptography,file_Extension);
            Set_Extension_Cryptography(file_Extension,Content);
            
            } catch (IOException ex) {
                 System.err.println(ex);
            }
   }
   
   static void Set_Extension_Cryptography(File file,String[] Content) throws FileNotFoundException{
   
   //Crip Vars
   String[] Letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};
   String[] Crip_Letters = {"!","@","#","$","%","^","&","*","(",")","_","-","+","=","{","}","[","]",";",":",">","<","/","?",".",",","`","~","|","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
   
   ArrayList<String> Key = new ArrayList<String>();
   
   //String FileLine = "";
   int randomNum = 0;
      
   ArrayList<String> NewContent = new ArrayList<String>();
   String NewLineCrip = "";
   
        //Create Key
        for(int i = 0;i< Content.length;i++){
           
            for(int e = 0;i< Letters.length;i++){
            if(e == 0){
            randomNum = ThreadLocalRandom.current().nextInt(0, Crip_Letters.length);
            Key.add(Crip_Letters[randomNum]);
            }else{
            
            randomNum = ThreadLocalRandom.current().nextInt(0, Crip_Letters.length);  
            for(int f = 0;f < Key.size();f++){
            if(Key.get(f) == Crip_Letters[randomNum]){
            randomNum = ThreadLocalRandom.current().nextInt(0, Crip_Letters.length);
            f = 0;
            }
            }
            Key.add(Crip_Letters[randomNum]);
            }
            
            }
            
            System.out.println(Key);
        }
        
            for(int i = 0;i< Content.length;i++){
            
            for(int f = 0;f< Content[i].length();f++){
            String Fline = Content[i];
            Fline = Fline.toLowerCase();
            
            int LetterIndex = 0;
            
            for(int t = 0;t< Letters.length;t++){
                Letters[t] = Letters[t].toLowerCase();
                if(Letters[t].charAt(0) == Fline.charAt(f)){
                 LetterIndex = t;
                }
                }
            
            NewLineCrip = NewLineCrip+Key.get(LetterIndex);
            
            }
            
            //NewContent.add(NewLineCrip);
            //NewLineCrip = "";
            
            try{
            FileWriter IndexWriter = null;
            IndexWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(IndexWriter);

            bw.write(NewLineCrip+"\n");
            bw.close();
            
            NewLineCrip = "";
            
            }catch(Exception e){
                System.err.println(e); 
            }
            
        }
        
        
        
   }
   
   public static String Get_Encryption_Key(){
   return Encryption_Key;
   }
    
}
