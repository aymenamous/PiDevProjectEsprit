/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.SignalisationDao;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Projet;
import Entity.Reclamation;
import Entity.Signalisation;
import crowdrise.main;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class Signalisation2Controller implements Initializable {

    @FXML
    TextArea txtSignal;
    @FXML
    Button btnSignal;
    @FXML
    RadioButton CI;
    @FXML
    RadioButton In;
    @FXML
    RadioButton AI;

    final ToggleGroup group = new ToggleGroup();

    public static int id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(id);

    }

    @FXML
    void Confirmer() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        if (!txtSignal.getText().isEmpty()) {

            Signalisation s = new Signalisation(main.getMembre(), new Commentaire(id), txtSignal.getText(), date);
            SignalisationDao sdao = new SignalisationDao();
            sdao.add(s);
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Succès");
            msg.setContentText("votre signalisation a été ajoutée");
            msg.setHeaderText(null);
            msg.showAndWait();
                
        
           
        } else if ( CI.isSelected()) {

            Signalisation s = new Signalisation(main.getMembre(), new Commentaire(id), CI.getText(), date);
            SignalisationDao sdao = new SignalisationDao();
            sdao.add(s);
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Succès");
            msg.setContentText("votre signalisation a été ajoutée");
            msg.setHeaderText(null);
            msg.showAndWait();
        } else if (In.isSelected()) {
            Signalisation s = new Signalisation(main.getMembre(), new Commentaire(id), In.getText(), date);
            SignalisationDao sdao = new SignalisationDao();
            sdao.add(s);
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Succès");
            msg.setContentText("votre signalisation a été ajoutée");
            msg.setHeaderText(null);
            msg.showAndWait();
        } else if (AI.isSelected()) {
            Signalisation s = new Signalisation(main.getMembre(), new Commentaire(id), AI.getText(), date);
            SignalisationDao sdao = new SignalisationDao();
            sdao.add(s);
            Alert msg = new Alert(Alert.AlertType.WARNING);
            msg.setTitle("Succès");
            msg.setContentText("votre signalisation a été ajoutée");
            msg.setHeaderText(null);
            msg.showAndWait();
        } else {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Erreur");
            msg.setContentText("Champ vide");
            msg.setHeaderText(null);
            msg.showAndWait();
        }

    }
    
    
    
    

}
