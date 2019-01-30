/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.IdeeDAO;
import Entity.Idee;
import crowdrise.main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author userpc
 */
public class ConsulterIdeeController implements Initializable {

    @FXML
    Label titre;
    @FXML
    TextArea description;
    @FXML
    Label dateDebut;
    @FXML
    Label dateFin;
    @FXML
    Label remuneration;
    @FXML
    Button btnSolution;
    @FXML
    Button btnMenu;
    @FXML
    Button btnContacter;
    @FXML
    Button btnQuitter;
    @FXML
    Button displaySolBtn;
    @FXML
    Pane pane;
    @FXML
    public AnchorPane pk;

    private Timeline timeline;

    public static Idee idee;

    public Idee getIdee() {
        return idee;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setEditable(false);
        idee = ListeIdeesController.idee2;
        System.out.println("[ConsulterIdeController.java]\n"
                + ListeIdeesController.idee2);
        titre.setText(idee.getNom());
        description.setText(idee.getDescription());
        dateDebut.setText(""+idee.getDebut());
        dateFin.setText(""+idee.getFin());
        remuneration.setText(""+idee.getRemuneration_totale());
        
        /*displaySolBtn.setOnAction(e->{
            
            
        });*/
        loadComment();

    }

    public void onBtnMenuClicked(ActionEvent event) {
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void onMouseClicked(MouseEvent event) {
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), -pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void onQuitterClicked(ActionEvent event) {
        crowdrise.main.afficher(crowdrise.main.url_liste_idees, "Liste Des Idées", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_liste_mes_idees);

    }



    public void onBtnContacterClicked(ActionEvent event) {
        crowdrise.main.afficher(crowdrise.main.url_Mail, "Mail", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_Mail);
    }

    public void loadComment() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/views/Commentaire.fxml"));
            AnchorPane content;
            CommentaireController.id=idee.getId();
            content = loader.load();
            pk.getChildren().add(content);
            pk.setPrefHeight(content.getPrefHeight());
        } catch (IOException ex) {
            Logger.getLogger(ConsulterIdeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


     
    
     public void onBtnSolutionClicked(ActionEvent event){
            main.referer = main.url_consulter_idee;
            main.afficher(main.url_taches, "Tâches", false);
            
     }
     
      
      
      
    

}
