/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author H4DH
 */
        // The Stage is the window
        // The scene is the content

public class ideeMain extends Application {
    Button btn;
  
    public static void main(String[] args) {
     // belongs to Application and set up the application as a javaFX then its gonna call the Start method   
        launch(args); 
    }   
    
    @Override
    public void start(Stage primaryStage) throws IOException 
    {
     Parent root = FXMLLoader.load(getClass().getResource("ListeIdees.fxml"));
    
        Scene scene = new Scene(root);
    
        primaryStage.setTitle("Liste Des Idées");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    
    }
    
}
