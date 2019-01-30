/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.ProblemDAO;
import Entity.Problem;
import crowdrise.main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author H4DH
 */
public class ProblemAddController implements Initializable {
@FXML 
private Button btnAjout; 
@FXML
private TextField txtDescription;
@FXML
private TextField txtAjout;
@FXML
private TextField txtmotcles;
@FXML 
private Button quit; 
Stage stage;

 //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    
       public void onBtnquitClicked (ActionEvent event)
    {
         stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
    

public void method(ActionEvent e)
    {
       
        if(!txtAjout.getText().isEmpty() && !txtDescription.getText().isEmpty() && !txtmotcles.getText().isEmpty()) //Date date, String description, int id_membre, String titre
       {
           
            Problem b = new Problem (null,txtDescription.getText(),main.getMembre().getId(),txtAjout.getText());
            ProblemDAO problemdao = new ProblemDAO();
            problemdao.add(b);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Ajout avec succès !");
            alert.showAndWait();
       }
         else
        {
            Alert msg=new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setTitle("Vérifier tous les champs"); 
        }
        
      
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
    
}
