/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaUt.TXTUtilities.TXTIA;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class SearchWordsTXT {
    
    public static ArrayList<String> Word_Line = new ArrayList<String>();
    public static ArrayList<Integer> Word_Index = new ArrayList<Integer>();
    
    static String[] LineWords;
    
    public static void Search_Words(File file, String Word){
      
        //TXTUtilities.ReadTXT.Read_TXT_File(file,2);
        //for(int i = 0;i < TXTUtilities.ReadTXT.TXT_Content.size();i++){
             
            // LineWords = TXTUtilities.ReadTXT.TXT_Content.get(i).split(" ");//
            // for(int e = 0;e<LineWords.length;e++){
               //  if(LineWords[e] == Word){
                     
                 //    Word_Line.add(TXTUtilities.ReadTXT.TXT_Content.get(i));
                  //   Word_Index.add(i);
                     
                // }
            // }
             
         //}
        
    }
    
    public static ArrayList<String> Get_Word_Line(){
    return Word_Line;
    }
    public static ArrayList<Integer> Get_Word_Index(){
    return Word_Index;
    }
    
}
