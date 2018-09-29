package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Azaelmglw
 */

public class ModelMain {
    /*  Parents array list position:
    [0] -> main    |   [1] -> bloc_notas |
    */
    
    /*  Files array list position:
    [0] -> text_file    |
    */
    
    /*  File Choosers array list position:
    [0] -> text_file_chooser    |
    */
    
    /*  Alerts array list position:
    [0] -> confirmation_alert   |   [1] -> error_alert  |
    */
    
    private final Stage primaryStage;
    
    private List<Parent> parents = new ArrayList<>(5);
    private List <File> files = new ArrayList<>(5);
    private List<FileChooser> file_choosers = new ArrayList<>(5);
    private List <Alert> alerts = new ArrayList<>(5);
    

    public ModelMain(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Parent getParent(int parent_position) {
        return parents.get(parent_position);
    }

    public void setParent(int parent_position, Parent parent) {
        this.parents.add(parent_position, parent);
    }     
    
    public File getFile(int file_position){
        return files.get(file_position);
    }
    
    public void setFile(int file_position, File file){
        this.files.add(file_position, file);
    }
    
    public FileChooser getFileChooser(int file_chooser_position){
        return file_choosers.get(file_chooser_position);
    }
    
    public void setFileChooser(int file_chooser_position, FileChooser file_chooser){
        this.file_choosers.add(file_chooser_position, file_chooser);
    }

    public Alert getAlert(int alert_position) {
        return alerts.get(alert_position);
    }

    public void setAlert(int alert_position, Alert alert) {
        this.alerts.add(alert_position, alert);
    }
       
}