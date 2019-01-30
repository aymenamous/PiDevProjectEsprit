/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;


import Entity.Problem;
import crowdrise.main;
import static gui.controllers.ConsulterIdeeController.idee;
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
public class ConsulterProblemController implements Initializable {

    @FXML Label titre;
    @FXML TextArea description;
    @FXML Label dateDebut;
    @FXML Button btnSolution;
    @FXML Button btnMenu;
    @FXML Button btnContacter;
    @FXML Button btnQuitter;
    @FXML Pane pane;
    private Timeline timeline; 
    @FXML AnchorPane pk;
    
    private static Problem prob;

    public Problem getprob() {
        return prob;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setEditable(false);
        prob = ProblemeController.pro;
        System.out.println("HERE ID = "+prob);
        titre.setText(prob.getTitre());
        description.setText(prob.getDescription());
//        System.out.println(prob.getDate().toString());
        //dateDebut.setText(prob.getDate().toString());
        loadComment();
                
    } 
    
    public void onBtnMenuClicked(ActionEvent event){
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
     }
    
    public void onMouseClicked(MouseEvent event){
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), -pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play(); 
    }
    
     public void onBtnSolutionClicked(ActionEvent event){
            //go to solution
     }
     
      public void onBtnContacterClicked(ActionEvent event){
            //go to contacter via mail
      }
    
      public void loadComment() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/gui/views/Commentaire.fxml"));
            AnchorPane content;
            CommentaireController.id=prob.getId();
            content = loader.load();
            pk.getChildren().add(content);
            pk.setPrefHeight(content.getPrefHeight());
        } catch (IOException ex) {
            Logger.getLogger(ConsulterIdeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
