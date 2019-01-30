/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import gui.controllers.ListeMesProblemsController;
import Dao.IdeeDAO;
import Dao.ProblemDAO;
import Entity.Idee;
import Entity.Problem;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
public class UpdateProblemController implements Initializable {

    @FXML TextField nom;
    @FXML TextArea description;
    @FXML DatePicker dateDebut;
    @FXML Button btnUpdate;
    @FXML Button btnMenu;
    @FXML Button btnModifier;
    @FXML Button btnReset;
    @FXML Button btnSupprimer;
    @FXML Button btnQuitter;
    @FXML Pane pane;
    @FXML ProgressBar pb;
    private Timeline timeline; 
    
    private static Problem prob;

    public Problem getProb() {
        return prob;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setEditable(false);
        description.setEditable(false);
        dateDebut.setEditable(false);
        btnUpdate.setVisible(false);
        btnReset.setVisible(false);
        pb.setVisible(false);
        
        prob = ListeMesProblemsController.prob2;
        System.out.println("HERE ID = "+prob);
        nom.setText(prob.getTitre());
        description.setText(prob.getDescription());
        // date goes here 
        // new Problem(20,liste2.get(i).getDate(),liste2.get(i).getDescription(), liste2.get(i).getId_membre(),liste2.get(i).getTitre()); 
        // prob.setId(liste2.get(i).getId());        
    }  
    
    public void onBtnUpdateClicked(ActionEvent event){
     
        Problem probl = new Problem(null,description.getText(), 1,nom.getText(),1);
        probl.setId(prob.getId());
        ProblemDAO problemdao = new ProblemDAO();
        problemdao.updateAllById(probl);
        System.out.println("Succes !");
        pb.setVisible(true);
        
  
     }
    
    public void onBtnResetClicked(ActionEvent event){
        nom.setText(prob.getTitre());
        description.setText(prob.getDescription());
        //DATE GOES HERE
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
    
     public void onBtnModifierClicked(ActionEvent event){
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), -pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play(); 
        
        nom.setEditable(true);
        description.setEditable(true);
        btnUpdate.setVisible(true);
        btnReset.setVisible(true);
        
        
  
     }
     
      public void onBtnSupprimerClicked(ActionEvent event){
          ProblemDAO problemdao = new ProblemDAO();
          problemdao.deleteById(prob.getId());
          System.out.println("Succes");
          Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/views/ListeMesProblems.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Ajout Problème");
            stage.setScene(new Scene(root));  
            stage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(UpdateProblemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
}
