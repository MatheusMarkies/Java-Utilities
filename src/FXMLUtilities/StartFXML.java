/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUtilities;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Matheus Markies
 */
public class StartFXML extends Application{
    
    static String FXML_Name = "FXML_Login_Frame";//Exemple
    static String FXML_Package = "FXML_Frames";//Exemple
    
    @Override
    public void start(Stage primaryStage) {
      
        try {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_Package+"/"+FXML_Name+".fxml"));

        AnchorPane root = (AnchorPane) loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        
        }catch(Exception e){
            System.err.println(e);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    launch(args);
    }
    
   // @Override
   // public void run() {
   //     String[] args = {};
   //     launch(args);
    //}
    
    
    public static void Set_FXML_Name(String name){
    FXML_Name = name;
    }
    public static void Set_FXML_Package(String pack){
    FXML_Package = pack;
    }
    
}
