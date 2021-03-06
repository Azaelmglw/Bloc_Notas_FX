package main;

import models.*;
import controllers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Azaelmglw
 */

public class Main extends Application{
    
    public static void main(String oamg[]) {  
        Application.launch(oamg);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            ArrayList<FXMLLoader> loaders_list = new ArrayList<>(5);
            ArrayList<Object> models_list = new ArrayList<>(5);
            ArrayList<Object> controllers_list = new ArrayList<>(5);
            
            // FXML Loaders Declaration.
            FXMLLoader loader_main = new FXMLLoader(getClass().getResource("ViewMain.fxml"));
            FXMLLoader loader_bloc_notas = new FXMLLoader(getClass().getResource("ViewBlocNotas.fxml"));
            
            loaders_list.add(0, loader_main);
            loaders_list.add(1, loader_bloc_notas);
                        
            /*  To maintain an MVC structure and avoid performance issues due to infinite instantiation, 
            each one of the application Models, Controllers, Parents and Scenes have been instanced in 
            this exact class and method.*/
            
            /*  In order to be able to get the <<model_main>> which contains the <<primaryStage>> in the Controllers
            and allow the modification of it's <<root>> (switching views), the Controller of each respective 
            <<.fxml>> file needs to be declared outside of itself and before the <<load()>> method is called 
            or it's build will fail.*/
            
            /*  Models array list position:
            [0] -> model_main   |   [1] -> model_bloc_notas |
            */
            
            /*  Controllers array list position:
            [0] -> controller_main  |   [1] -> controller_bloc_notas |
            */
            
            ModelMain model_main = new ModelMain(primaryStage);
            models_list.add(0, model_main);
            ControllerMain controller_main = new ControllerMain(model_main);
            loader_main.setController(controller_main);
            controllers_list.add(0, controller_main);
            
            ModelBlocNotas model_bloc_notas = new ModelBlocNotas(model_main);
            models_list.add(1, model_bloc_notas);
            ControllerBlocNotas controller_bloc_notas = new ControllerBlocNotas(models_list, controllers_list);
            loader_bloc_notas.setController(controller_bloc_notas);
            controllers_list.add(1, controller_bloc_notas);
            
            //  Parents Declaration.
            Parent main = (Parent)loader_main.load();
            Parent bloc_notas = (Parent)loader_bloc_notas.load();
            
            /*  Once all the Parent variables are declared, the <<model_main>> needs to receive the reference 
            for each one trough it's <<set()>> method, making available all the Parents to the Controllers.*/
            
            //  Parents Assignation.
            model_main.setParent(0, main);
            model_main.setParent(1, bloc_notas);
            
            //  Scenes Declaration.
            Scene main_scene = new Scene(main, 800, 768);
            
            //  File Choosers Declaration.
            FileChooser text_file_chooser = new FileChooser();
            
            //  File Choosers Assignation.
            model_main.setFileChooser(0, text_file_chooser);
            
            //  Input Dialogs Declaration.
            TextInputDialog key_request = new TextInputDialog();
            
            //  Input Dialogs Assignation.
            model_main.setTextInputDialog(0, key_request);
            
            //  Alerts Declaration.
            Alert confirmation_alert = new Alert(AlertType.CONFIRMATION);
            confirmation_alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            Alert error_alert = new Alert(AlertType.ERROR);
            error_alert.setTitle("Something went wrong");
            
            //  Alerts Assignation.
            model_main.setAlert(0, confirmation_alert);
            model_main.setAlert(1, error_alert);
            
            /*  Only the <<main>> UI is set in this method, all of the other manipulations of <<primaryStage>>
            need to be declared in it's respective Controllers.*/
            primaryStage.setTitle("Bloc de Notas 3.0");
            primaryStage.setScene(main_scene);
            primaryStage.show();
        } 
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}