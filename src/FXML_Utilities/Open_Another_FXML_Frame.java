/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML_Utilities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Matheus Markies
 */
public class Open_Another_FXML_Frame {
    
   static String FXML_Name = "FXML_Login_Frame";//Exemple
   static String FXML_Package = "FXML_Frames";//Exemple
    
   FXMLLoader loader;
    
   public void Open_Another_FXML() {
    
    try {
    loader = new FXMLLoader(getClass().getResource(FXML_Package+"/"+FXML_Name+".fxml"));
    Parent rootNew = (Parent) loader.load();
    Stage stage = new Stage();

    stage.setScene(new Scene(rootNew));  
    stage.show();
    }catch(Exception e){
    
    }
       
   }
  
    
}
