/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TXT_Utilities.TXT_IA;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class Search_Words_TXT {
    
    public static ArrayList<String> Word_Line = new ArrayList<String>();
    public static ArrayList<Integer> Word_Index = new ArrayList<Integer>();
    
    static String[] LineWords;
    
    public static void Search_Words(File file, String Word){
      
        TXT_Utilities.Read_TXT.Read_TXT_File(file);
         for(int i = 0;i < TXT_Utilities.Read_TXT.TXT_Content.size();i++){
             
             LineWords = TXT_Utilities.Read_TXT.TXT_Content.get(i).split(" ");
             for(int e = 0;e<LineWords.length;e++){
                 if(LineWords[e] == Word){
                     
                     Word_Line.add(TXT_Utilities.Read_TXT.TXT_Content.get(i));
                     Word_Index.add(i);
                     
                 }
             }
             
         }
        
    }
    
    public static ArrayList<String> Get_Word_Line(){
    return Word_Line;
    }
    public static ArrayList<Integer> Get_Word_Index(){
    return Word_Index;
    }
    
}
