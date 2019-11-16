/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUtilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Markies
 */
public class SwitchFrameFXML {
    
    static String FXML_Name = "FXML_Login_Frame";//Exemple
    static String FXML_Package = "FXML_Frames";//Exemple
    
    static Scene scene;//Your Scene 
    
    public void Switch_Scene_Frame(String FXMLName,String FXMLPatch){
     
        //scene = scene_;
        FXML_Package = FXMLPatch;
        FXML_Name = FXMLName;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_Package+"/"+FXML_Name));
        Stage stage = (Stage) scene.getWindow();
        scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        
    }
    
    //public static void Set_Scene(Scene scene_){
    //    scene = scene_;
    //}
    
}
