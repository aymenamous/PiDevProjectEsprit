/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Entity.Signalisation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class SignalisationController implements Initializable {

    @FXML
     Button btnSignaler;
    public int id;
   
    public static Signalisation signal;
        public static Signalisation getSignalisation() {
        return signal ;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    
    @FXML
    void Signaler() throws IOException
    {   Signalisation2Controller.id=id;
        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/Signalisation2.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Reclamation");
        stage.setScene(new Scene(root));
        stage.show();
        
    }
}
