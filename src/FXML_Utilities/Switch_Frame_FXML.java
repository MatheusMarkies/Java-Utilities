/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML_Utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Markies
 */
public class Switch_Frame_FXML {
    
    static String FXML_Name = "FXML_Login_Frame";//Exemple
    static String FXML_Package = "FXML_Frames";//Exemple
    
    static Scene scene;//Your Scene 
    
    public void Switch_Scene_Frame(){
     
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_Package+"/"+FXML_Name+".fxml"));
        Stage stage = (Stage) scene.getWindow();
        scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        
    }
    
    public static void Set_Scene(Scene scene_){
        scene = scene_;
    }
    
}
