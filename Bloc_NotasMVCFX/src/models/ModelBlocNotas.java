package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Azaelmglw
 */

public class ModelBlocNotas {
    
    private final ModelMain model_main;
    
    private Optional <String> user_dialog_input;
    private Optional <ButtonType> result;
    
    private String text;
    private String cipher_key;
    
    
    private File text_file;
    private FileReader file_reader;
    private FileWriter file_writer;
    
    
    public ModelBlocNotas(ModelMain model_main){
        this.model_main = model_main;
    }
    
    public void ReadFile(){
        try{
            model_main.getFileChooser(0).setTitle("Select resource file");
            text_file = model_main.getFileChooser(0).showOpenDialog(model_main.getPrimaryStage());
            file_reader = new FileReader(text_file);
            int character = 0;
            String content = "";
            while ((character = file_reader.read()) != -1) {
                content += (char) character;
            }
            text = content;
            file_reader.close();
        }
        catch(FileNotFoundException e){
            model_main.getAlert(1).setContentText("Err 000: File not Found:" + e);
        }
        catch(IOException e){
            model_main.getAlert(1).setContentText("Err 001: The file couldn't be read: " + e);
        }    
    }
    
    public void WriteFile(){
        try{
            model_main.getFileChooser(0).setTitle("Select the path to save the file");
            text_file = model_main.getFileChooser(0).showSaveDialog(model_main.getPrimaryStage());
            file_writer = new FileWriter(text_file, false);
            file_writer.write(text);
            file_writer.close();
        }
        catch(FileNotFoundException e){
            model_main.getAlert(1).setContentText("Err 000: File not Found:" + e);
        }
        catch(IOException e){
            model_main.getAlert(1).setContentText("Err 001: The file couldn't be read: " + e);
        }
    }
    
    public void CipherFileContents(){
        RequestCipherKey();
        int cipher_key = 0;
        String ciphered_text = "";
        
        for(int x = 0; x < this.cipher_key.length(); x ++){
            cipher_key += (int)this.cipher_key.charAt(x);
        }
        
        for(int y = 0; y < text.length(); y ++){
            ciphered_text += (char)((int)text.charAt(y) + cipher_key);
        }
        text = ciphered_text;
        System.out.println("Key provided :" + cipher_key);
    }
    
    public void DecipherFileContents(){
        RequestCipherKey();
        int cipher_key = 0;
        String ciphered_text = "";
        
        for(int x = 0; x < this.cipher_key.length(); x ++){
            cipher_key += (int)this.cipher_key.charAt(x);
        }
        
        for(int y = 0; y < text.length(); y ++){
            ciphered_text += (char)((int)text.charAt(y) - cipher_key);
        }
        text = ciphered_text;
        System.out.println("Key provided :" + cipher_key);
        
        
    }
    
    public void RequestCipherKey(){
        model_main.getTextInputDialog(0).setTitle("Input Required");
        model_main.getTextInputDialog(0).setHeaderText("Write the cipher key to apply to the document: ");
        model_main.getTextInputDialog(0).setContentText("Cipher Key");
        user_dialog_input = model_main.getTextInputDialog(0).showAndWait();
        this.cipher_key = user_dialog_input.get();
        model_main.getTextInputDialog(0).getEditor().clear();
    }
    
    public void SaveCurrentChangesConfirmationRequest(){
        model_main.getAlert(0).setTitle("Confirmation Required");
        model_main.getAlert(0).setHeaderText("Save changes of the current file?");
        model_main.getAlert(0).setContentText("Choose one of the following options.");
        
        result = model_main.getAlert(0).showAndWait();
    }
    
    public ModelMain getModel_Main() {
        return model_main;
    }
    
    public Optional getResult(){
        return result;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
}