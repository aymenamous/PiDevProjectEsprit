/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import gui.*;
import Dao.IdeeDAO;
import Entity.Idee;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author userpc
 */
public class UpdateIdeeController implements Initializable {

    @FXML
    TextField nom;
    @FXML
    TextArea description;
    @FXML
    DatePicker dateDebut;
    @FXML
    DatePicker dateFin;
    @FXML
    TextField remuneration;
    @FXML
    Button btnUpdate;
    @FXML
    Button btnMenu;
    @FXML
    Button btnModifier;
    @FXML
    Button btnReset;
    @FXML
    Button btnSupprimer;
    @FXML
    Button btnQuitter;
    @FXML
    Pane pane;
    @FXML
    ImageView imgv_idee;
    @FXML
    Hyperlink link_image;
    @FXML
    AnchorPane pk;
    @FXML
    private Button ModifierSol;
    String path;

    private Timeline timeline;

    public static Idee idee;
    @FXML
    Button displaySolBtn;

    public Idee getIdee() {
        return idee;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setEditable(false);
        description.setEditable(false);
        dateDebut.setEditable(false);
        dateFin.setEditable(false);
        remuneration.setEditable(false);
        btnUpdate.setVisible(false);
        btnReset.setVisible(false);
        link_image.setVisible(false);

        idee = ListeMesIdeesController.idee2;
        //System.out.println("path = "+idee.getImage());
        path = idee.getImage();
        System.out.println("path = "+path);
        System.out.println("HERE ID = " + idee);
        nom.setText(idee.getNom());
        description.setText(idee.getDescription());
        //Ajouter les dates
        /*LocalDate date1 = idee.getDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateDebut.setValue(date1);
        LocalDate date2 = idee.getFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateFin.setValue(date2);*/
        remuneration.setText("" + idee.getRemuneration_totale());
        javafx.scene.image.Image img = new javafx.scene.image.Image("/ressources/"+path, 154, 145, true, true);
        imgv_idee.setImage(img);

        ModifierSol.setOnAction(e->{
            
            crowdrise.main.referer = crowdrise.main.url_update_idee;
            crowdrise.main.initBase(pk, crowdrise.main.url_solution);
        });
    }

    public void onPhotoEntred(MouseEvent event) {
        link_image.setVisible(true);
    }

    public void onPhotoExisted(MouseEvent event) {
        link_image.setVisible(false);
    }

    public void onBtnUpdateClicked(ActionEvent event) {

        //here update
        Idee id = new Idee(Double.parseDouble(remuneration.getText()), path, nom.getText(), Date.valueOf(dateDebut.getValue()), Date.valueOf(dateFin.getValue()), description.getText(), null, crowdrise.main.getMembre());
        id.setId(idee.getId());
        IdeeDAO idao = new IdeeDAO();
        idao.updateAllById(id);
        
        crowdrise.main.afficher(crowdrise.main.url_liste_mes_idees, "Mes Idees", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_liste_mes_idees);

    }

    public void onBtnResetClicked(ActionEvent event) {
        nom.setText(idee.getNom());
        description.setText(idee.getDescription());
        //dateDebut.setValue(idee.getDebut().toLocalDate());
        //dateFin.setValue(idee.getFin().toLocalDate());
        remuneration.setText("" + idee.getRemuneration_totale());
        javafx.scene.image.Image img = new javafx.scene.image.Image("/ressources/"+path, 154, 145, true, true);
        imgv_idee.setImage(img);
    }

    public void onLinkClicked(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Ouvrir");
        File f = fc.showOpenDialog(crowdrise.main.stage);
         if (f != null) {
            path = f.getName();
            javafx.scene.image.Image img = new javafx.scene.image.Image("/ressources/"+path, 154, 145, true, true);
            imgv_idee.setImage(img);    
        }else{
            path = idee.getImage();
        }
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

    public void onBtnModifierClicked(ActionEvent event) {
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), -pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        nom.setEditable(true);
        description.setEditable(true);
        dateDebut.setEditable(true);
        dateFin.setEditable(true);
        remuneration.setEditable(true);
        btnUpdate.setVisible(true);
        btnReset.setVisible(true);

    }

    public void onBtnSupprimerClicked(ActionEvent event) {
        IdeeDAO idao = new IdeeDAO();
        idao.deleteById(idee.getId());
        
        crowdrise.main.afficher(crowdrise.main.url_liste_mes_idees, "Mes Idees", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_liste_mes_idees);

    }

    public void onQuitterClicked(ActionEvent event) {

        crowdrise.main.afficher(crowdrise.main.url_liste_mes_idees, "Mes Idees", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_liste_mes_idees);
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

}
