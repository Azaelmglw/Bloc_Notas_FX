package controllers;

import models.ModelMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Azaelmglw
 */

public class ControllerMain implements Initializable {
    
    private final ModelMain model_main;
    
    public ControllerMain(ModelMain model_main){
        this.model_main = model_main;
    }
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void LaunchBlocNotas(ActionEvent event) {
        SwitchPrimaryStageRoot(model_main.getParent(1));
    }
    
    public void SwitchPrimaryStageRoot(Parent root){
        model_main.getPrimaryStage().getScene().setRoot(root);
    }
    
}