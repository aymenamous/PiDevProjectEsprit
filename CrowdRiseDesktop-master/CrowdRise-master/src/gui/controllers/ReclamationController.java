/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.CommentaireDao;
import Dao.ReclamationDao;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Projet;
import Entity.Reclamation;
import crowdrise.main;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class ReclamationController  {
    
    
    @FXML
    private TextField TxtFieldSujet;     
    @FXML
     private  TextArea TxtRec;
   

      public void btnEnvoyer(ActionEvent event) throws IOException
    {
        String  sujet=TxtFieldSujet.getText();
        String rec=TxtRec.getText();
    System.out.println("aaa");

       java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
      Reclamation r=new Reclamation(main.getMembre(),date,sujet, rec);
      ReclamationDao rdao=new ReclamationDao();
      
      if(sujet.isEmpty() && rec.isEmpty())
      {
           Alert msg = new Alert(Alert.AlertType.ERROR);
          msg.setHeaderText(null);
          msg.setTitle("Saisissez votre reclamation");
          msg.setContentText("saisissez votre reclamation");
          msg.showAndWait();
      }
      
      else 
      {
            rdao.add(r);
      TxtFieldSujet.clear();
      TxtRec.clear();
      Alert msg = new Alert(Alert.AlertType.INFORMATION);
      msg.setHeaderText(null);
      msg.setTitle("Confirmation");
      msg.setContentText("reclamation ajoutée");
      msg.showAndWait();
      }
    
    
//          Alert msg = new Alert(Alert.AlertType.ERROR);
//          msg.setHeaderText(null);
//          msg.setTitle("Saisissez votre reclamation");
    // }
     
   
        
    }
      
@FXML
      public void mailS(MouseEvent even) throws IOException
      {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/MailApi.fxml"));

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Mail");
        stage.setScene(new Scene(root));
        stage.show();
      }

}

