/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML_Utilities.FXML_Frames;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller class
 *
 * @author Matheus Markies
 */
public class FXML_Login_FrameController implements Initializable {

   static Scene scene;
    
   @FXML
   private TextField UserField;
   
   @FXML
   private TextField PasswordField;
   
   @FXML
   private Hyperlink RegisterHyperlink;
   
   @FXML
   private Button LoginButton;
   
   @FXML
   @Override
   public void initialize(URL url, ResourceBundle rb) {
    
     //UserField = (TextField) scene.lookup("#UserInput_ID");
        
    }
    
    public static void Set_Scene(Scene scene_){
        scene = scene_;
    }
    
}
