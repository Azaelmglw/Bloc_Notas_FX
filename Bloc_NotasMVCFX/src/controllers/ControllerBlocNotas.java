package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import models.ModelBlocNotas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

/**
 *
 * @author Azaelmglw
 */

public class ControllerBlocNotas implements Initializable {
    
    private final ModelBlocNotas model_bloc_notas;
    private final ControllerMain controller_main;
    
    public ControllerBlocNotas(ArrayList models, ArrayList controllers){
        this.model_bloc_notas = (ModelBlocNotas)models.get(1);
        this.controller_main = (ControllerMain)controllers.get(0);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    @FXML
    private TextArea main_display_txtarea;
    
    @FXML
    private void NewFile(ActionEvent event){
        System.out.println("<New File event triggered>");
        model_bloc_notas.SaveCurrentChangesConfirmationRequest();
        if(model_bloc_notas.getResult().get() == ButtonType.YES){
            System.out.println("user's confirmation choice: YES");
            SaveFile(event);
            main_display_txtarea.setText("");
        }
        else if (model_bloc_notas.getResult().get() == ButtonType.NO){
            System.out.println("user's confirmation choice: NO");
            main_display_txtarea.setText("");
        }
        else if (model_bloc_notas.getResult().get() == ButtonType.CANCEL){
            System.out.println("user's confirmation choice: CANCEL");
        }
    }
    
    @FXML
    private void OpenFile(ActionEvent event){
        System.out.println("<Open File event triggered>");
        if(main_display_txtarea.getText().length() > 1){
            model_bloc_notas.SaveCurrentChangesConfirmationRequest();
            if (model_bloc_notas.getResult().get() == ButtonType.YES) {
                System.out.println("user's confirmation choice: YES");
                SaveFile(event);
                model_bloc_notas.ReadFile();
                main_display_txtarea.setText(model_bloc_notas.getText());
            } 
            else if (model_bloc_notas.getResult().get() == ButtonType.NO) {
                System.out.println("user's confirmation choice: NO");
                model_bloc_notas.ReadFile();
                main_display_txtarea.setText(model_bloc_notas.getText());
            } 
            else if (model_bloc_notas.getResult().get() == ButtonType.CANCEL) {
                System.out.println("user's confirmation choice: CANCEL");
            }
        }
        else{
            model_bloc_notas.ReadFile();
            main_display_txtarea.setText(model_bloc_notas.getText());
        }
    }
    
    @FXML
    private void SaveFile(ActionEvent event){
        System.out.println("<Save File event triggered>");
        model_bloc_notas.setText(main_display_txtarea.getText());
        model_bloc_notas.WriteFile();
    }
    
    @FXML
    private void CipherFile(ActionEvent event){
        System.out.println("<Cipher File event triggered>");
        model_bloc_notas.setText(main_display_txtarea.getText());
        model_bloc_notas.CipherFileContents();
        main_display_txtarea.setText(model_bloc_notas.getText());
    }
    
    @FXML
    private void DecipherFile(ActionEvent event){
        System.out.println("<Decipher File event triggered>");
        model_bloc_notas.setText(main_display_txtarea.getText());
        model_bloc_notas.DecipherFileContents();
        main_display_txtarea.setText(model_bloc_notas.getText());
    }
}