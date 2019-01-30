/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.AdminDao;
import Dao.MembreDao;
import Entity.Membre;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import crowdrise.main;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class AuthentificationController {

    @FXML
    private TextField email_field;
    @FXML
    private TextField password_field;
    @FXML
    private AnchorPane fields;
    @FXML
    private Hyperlink inscription;
    @FXML
    private AnchorPane base;
    @FXML
    public SplitPane contents;
    @FXML
    private Button btn_connexion;
    @FXML
    private JFXDialog dialog;
    @FXML
    private BorderPane border;
    @FXML
    private JFXButton btn_dialog;
    @FXML
    private Label content_dialog;
    @FXML
    private Label title_dialog;
    

    public void initialize() {
        initDialog();
    }

    public void initDialog() {
        dialog.setPrefWidth(base.getPrefWidth());
        dialog.setPrefHeight(base.getPrefHeight());
        dialog.setTransitionType(DialogTransition.CENTER);
        dialog.setLayoutX(base.getPrefWidth()/ 2 -dialog.getPrefWidth()/2);
        dialog.setLayoutY(contents.getPrefHeight()/2-dialog.getPrefHeight()/2);
        
    }

    public void activateDialog(String title, String content, JFXDialog.DialogTransition transition) {
        dialog.setTransitionType(transition);
        title_dialog.setText(title);
        content_dialog.setText(content);
        dialog.show(base);
    }
    
    public void success()
    {
        title_dialog.setStyle("-fx-text-fill:#2ECC71;-fx-font-size:16px;");
        Image img=new Image(getClass().getResourceAsStream("/ressources/success.png"), 25,0 , true, true);
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));
        btn_dialog.setOnMouseClicked((e) -> {
            dialog.close();
            main.afficher(main.url_base, "CrowdRise", true);
        });

    }
    
    public void failure()
    {
       
        Image img=new Image(getClass().getResourceAsStream("/ressources/warning.png"), 30,0 , true, true);
        title_dialog.setStyle("-fx-text-fill:#E74C3C;-fx-font-size:16px;");
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));
        
        btn_dialog.setOnMouseClicked((e) -> {
            dialog.close();
            border.setDisable(false);
        });
    }
    
    public void onIncriptionClicked(ActionEvent event) throws IOException {
        main.afficher(main.url_inscription, "Inscription", false);

    }

    public void onBtnConnexionClicked(ActionEvent event) {
        MembreDao mdao = new MembreDao();
        AdminDao adao = new AdminDao();
        main.setMembre(mdao.displayByEmailMdp(email_field.getText(), password_field.getText()));
        main.setAdmin(adao.displayByEmailMdp(email_field.getText(), password_field.getText()));
        if (main.getMembre().getId() != 0 && main.getMembre().isStatut()) {
            main.getMembre().fillListes();
            activateDialog("Confirmer l'authentification", "Authentification avec succès",JFXDialog.DialogTransition.TOP);
            success();
            
        } else if (main.getAdmin().getId() != 0) {

            activateDialog("Confirmer l'authentification", "Authentification avec succès",JFXDialog.DialogTransition.TOP);
            success();
        } else if (!main.getMembre().isStatut() && main.getMembre().getId()!=0 ) {
            activateDialog("Impossible de se connecter", "Ce compte a été désactivé",JFXDialog.DialogTransition.BOTTOM);
            failure();
        } else {
            activateDialog("Impossible de se connecter", "Email ou mot de passe invalide",JFXDialog.DialogTransition.BOTTOM);
            failure();
        }

    }

}
