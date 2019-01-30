    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.AdminDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import crowdrise.main;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class ProfilAdminController implements Initializable {

    public static ImageView imv;
    @FXML
    public TextField nom_field;
    @FXML
    TextField prenom_field;
    @FXML
    TextField email_field;
    @FXML
    TextField mdp_field;
    @FXML
    TextField nouveau_field;
    @FXML
    TextField confirmer_field;
    @FXML
    Pane pane_parametre_compte;
    @FXML
    Label nouveau_label;
    @FXML
    Label confirmer_label;
    @FXML
    Label nom_label;
    @FXML
    Label prenom_label;
    @FXML
    Label email_label;
    @FXML
    Label mdp_label;
    @FXML
    Button btn_nomm;
    @FXML
    Button btn_prenom;
    @FXML
    Button btn_email;
    @FXML
    Button btn_mdp;
    @FXML
    Button btn_annuler_nom;
    @FXML
    Button btn_annuler_prenom;
    @FXML
    Button btn_annuler_email;
    @FXML
    Button btn_annuler_mdp;
    @FXML
    ImageView imgv_photo;
    @FXML
    Hyperlink link_photo;
    AdminDao adao;
    @FXML
    VBox vbox_photo;
    @FXML
    JFXDialog dialog;
    @FXML
    Label title_dialog;
    @FXML
    Label content_dialog;
    @FXML
    AnchorPane dialog_btn;
    @FXML
    AnchorPane base;

    @FXML
    JFXDialogLayout dialog_layout;
    @FXML
    JFXButton action;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imv=imgv_photo;
        nom_field.setText(main.getAdmin().getNom());
        prenom_field.setText(main.getAdmin().getPrenom());
        email_field.setText(main.getAdmin().getEmail());
        nom_field.setDisable(true);
        prenom_field.setDisable(true);
        email_field.setDisable(true);
        mdp_field.setDisable(true);
        confirmer_field.setVisible(false);
        nouveau_field.setVisible(false);
        nouveau_label.setVisible(false);
        confirmer_label.setVisible(false);
        btn_annuler_email.setDisable(true);
        btn_annuler_mdp.setDisable(true);
        btn_annuler_nom.setDisable(true);
        btn_annuler_prenom.setDisable(true);
        adao = new AdminDao();
        restrict();
        afficherPhotoProfil();
        link_photo.setVisible(false);
        vbox_photo.setVisible(false);
        Tooltip toolt_nom_field = new Tooltip("Un nom ne peut comporter que des lettres");
        Tooltip toolt_email_field = new Tooltip("exmple: exemple@gmail.com");
        Tooltip toolt_mdp_field = new Tooltip("Le mot de passe doit contenir au moins 8 caractères");
        Tooltip toolt_prenom_field = new Tooltip("Un prénom ne peut comporter que des lettres");
        nom_field.setTooltip(toolt_nom_field);
        prenom_field.setTooltip(toolt_prenom_field);
        email_field.setTooltip(toolt_email_field);
        mdp_field.setTooltip(toolt_mdp_field);
        initDialog();
    }

    public void afficherPhotoProfil() {
        try {
            if (main.getAdmin().getPhoto().startsWith("file:")) {
                Image img = new Image((main.getAdmin().getPhoto()), 154, 145, false, true);
                imgv_photo.setImage(img);
            } else {
                Image img = new Image(getClass().getResourceAsStream(main.getAdmin().getPhoto()), 154, 145, false, true);
                imgv_photo.setImage(img);
            }
        } catch (Exception e) {
            System.out.println("Impossible de charger l'image");
        }
    }

    public void initDialog() {
        dialog_layout.setPrefHeight(100);
        dialog_layout.setPrefWidth(500);
        dialog.setPrefHeight(1366);
        dialog.setPrefWidth(800);
        dialog.setLayoutX(base.getPrefWidth() / 2 - dialog.getPrefWidth() / 2);
        dialog.setLayoutY(base.getPrefHeight() / 2 - dialog.getPrefHeight() / 2);

    }

    public void activateDialog(String title, String content, JFXDialog.DialogTransition transition) {
        dialog.setTransitionType(transition);
        title_dialog.setText(title);
        content_dialog.setText(content);
        Text t = new Text(title);
        dialog.show(base);
    }

    public void failure() {
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 30, 0, true, true);
        title_dialog.setStyle("-fx-text-fill:#E74C3C;-fx-font-size:16px;");
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));

        action.setOnMouseClicked((e) -> {
            dialog.close();
        });
    }

    public void onLinkPhotoClicked(ActionEvent event) {
        dialog_btn.getChildren().clear();
        title_dialog.setStyle("");
        title_dialog.setGraphic(null);
        action.setText("Annuler");
        action.setOnMouseClicked((e) -> dialog.close());
        activateDialog("Modifier la photo de profil", "", JFXDialog.DialogTransition.CENTER);
        JFXButton b = new JFXButton("Importer une photo");
        b.setOnMouseClicked((e) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Ouvrir");
            File f = fc.showOpenDialog(main.stage);
            if (f != null) {
                String path = "file:" + f.getAbsolutePath();
                path = path.replace("\\", "/");
                main.getAdmin().setPhoto(path);
                adao.updateAllById(main.getAdmin());
                Image img = new Image(path, 154, 145, false, true);
                imgv_photo.setImage(img);
                dialog.close();
            }
        });
        JFXButton b1 = new JFXButton("Prendre une photo");
        b1.setOnMouseClicked((e) -> {
            main.afficherCam();
        });
        b.setPrefHeight(200);
        b.setPrefWidth(300);

        b1.setPrefHeight(200);
        b1.setPrefWidth(300);
        b1.setLayoutX(400);
        dialog_btn.getChildren().add(b);
        b.setStyle("-fx-border-color:#2ECC71;");
        dialog_btn.getChildren().add(b1);
        b1.setStyle("-fx-border-color:#2ECC71;");

    }

    public void onPhotoEntred(MouseEvent event) {
        link_photo.setVisible(true);
        vbox_photo.setVisible(true);
    }

    public void onPhotoExisted(MouseEvent event) {
        link_photo.setVisible(false);
        vbox_photo.setVisible(false);
    }

    public void onBtnNomClicked(ActionEvent event) {
        if (nom_field.isDisabled()) {
            btn_annuler_nom.setDisable(false);
            nom_field.setDisable(false);
        } else {
            if (verifOnlyWords(nom_field.getText())) {
                main.getAdmin().setNom(nom_field.getText());
                adao.updateAllById(main.getAdmin());
                nom_field.setDisable(true);
                btn_annuler_nom.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
                action.setText("Accepter");
                activateDialog("Modification impossible", "Nom erroné", JFXDialog.DialogTransition.CENTER);
                failure();
            }
        }

    }

    public void onBtnPrenomClicked(ActionEvent event) {

        if (prenom_field.isDisabled()) {
            btn_annuler_prenom.setDisable(false);
            prenom_field.setDisable(false);
        } else {
            if (verifOnlyWords(prenom_field.getText())) {
                main.getAdmin().setPrenom(prenom_field.getText());
                adao.updateAllById(main.getAdmin());
                prenom_field.setDisable(true);
                btn_annuler_prenom.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
                action.setText("Accepter");
                activateDialog("Modification impossible", "Prenom erroné", JFXDialog.DialogTransition.CENTER);
                failure();
            }
        }
    }

    public void onBtnEmailClicked(ActionEvent event) {
        if (email_field.isDisabled()) {
            btn_annuler_email.setDisable(false);
            email_field.setDisable(false);
        } else {
            if (verifEmail(email_field.getText())) {
                btn_annuler_email.setDisable(true);
                main.getAdmin().setEmail(email_field.getText());
                adao.updateAllById(main.getAdmin());
                email_field.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
                action.setText("Accepter");
                activateDialog("Modification impossible", "Email erroné", JFXDialog.DialogTransition.CENTER);
                failure();
            }
        }
    }

    public void onBtnMdpClicked(ActionEvent event) {
        if (mdp_field.isDisabled()) {
            btn_annuler_mdp.setDisable(false);
            mdpModifMode();
        } else {
            if (mdp_field.getText().equals(main.getAdmin().getMdp())) {
                if (nouveau_field.getText().equals(confirmer_field.getText()) && verifMdp(nouveau_field.getText())) {
                    btn_annuler_mdp.setDisable(true);
                    main.getAdmin().setMdp(nouveau_field.getText());
                    adao.updateAllById(main.getAdmin());
                    normalMode();
                } else if (!verifMdp(nouveau_field.getText())) {
                    dialog_btn.getChildren().clear();
                    action.setText("Accepter");
                    activateDialog("Modification impossible", "Le mot de passe doit au moins contenir 8 caractères", JFXDialog.DialogTransition.CENTER);
                    failure();
                } else if (!nouveau_field.getText().equals(confirmer_field.getText())) {
                    dialog_btn.getChildren().clear();
                    action.setText("Accepter");
                    activateDialog("Modification impossible", "Les mots de passe ne correspondent pas", JFXDialog.DialogTransition.CENTER);
                    failure();
                }

            } else {
                dialog_btn.getChildren().clear();
                action.setText("Accepter");
                activateDialog("Modification impossible", "Mot de passe erroné", JFXDialog.DialogTransition.CENTER);
                failure();
            }
        }

    }

    public void onExtendClicked(ActionEvent event) {
        if (pane_parametre_compte.getPrefHeight() == 40) {
            extend();
        } else {
            normalMode();
            restrict();
        }
    }

    public void onBtnAnnulerNomClicked(ActionEvent event) {
        nom_field.setText(main.getAdmin().getNom());
        nom_field.setDisable(true);
        btn_annuler_nom.setDisable(true);
    }

    public void onBtnAnnulerPrenomClicked(ActionEvent event) {
        prenom_field.setText(main.getAdmin().getPrenom());
        prenom_field.setDisable(true);
        btn_annuler_prenom.setDisable(true);
    }

    public void onBtnAnnulerEmailClicked(ActionEvent event) {
        email_field.setText(main.getAdmin().getEmail());
        email_field.setDisable(true);
        btn_annuler_email.setDisable(true);
    }

    public void onBtnAnnulerMdpClicked(ActionEvent event) {
        mdp_field.setText("");
        mdp_field.setDisable(true);
        btn_annuler_mdp.setDisable(true);
        normalMode();
    }

    public void mdpModifMode() {
        confirmer_field.setVisible(true);
        nouveau_field.setVisible(true);
        nouveau_label.setVisible(true);
        confirmer_label.setVisible(true);
        mdp_field.setDisable(false);
        pane_parametre_compte.setPrefHeight(260);
    }

    public void normalMode() {
        confirmer_field.setVisible(false);
        nouveau_field.setVisible(false);
        nouveau_label.setVisible(false);
        confirmer_label.setVisible(false);
        mdp_field.setDisable(true);
        pane_parametre_compte.setPrefHeight(200);
    }

    public void extend() {
        pane_parametre_compte.setPrefHeight(200);
        nom_field.setVisible(true);
        nom_label.setVisible(true);
        btn_nomm.setVisible(true);
        prenom_field.setVisible(true);
        prenom_label.setVisible(true);
        btn_prenom.setVisible(true);
        email_field.setVisible(true);
        email_label.setVisible(true);
        btn_email.setVisible(true);
        mdp_field.setVisible(true);
        mdp_label.setVisible(true);
        btn_mdp.setVisible(true);
        btn_annuler_email.setVisible(true);
        btn_annuler_mdp.setVisible(true);
        btn_annuler_nom.setVisible(true);
        btn_annuler_prenom.setVisible(true);
    }

    public void restrict() {
        pane_parametre_compte.setPrefHeight(40);
        nom_field.setVisible(false);
        nom_label.setVisible(false);
        btn_nomm.setVisible(false);
        prenom_field.setVisible(false);
        prenom_label.setVisible(false);
        btn_prenom.setVisible(false);
        email_field.setVisible(false);
        email_label.setVisible(false);
        btn_email.setVisible(false);
        mdp_field.setVisible(false);
        mdp_label.setVisible(false);
        btn_mdp.setVisible(false);
        btn_annuler_email.setVisible(false);
        btn_annuler_mdp.setVisible(false);
        btn_annuler_nom.setVisible(false);
        btn_annuler_prenom.setVisible(false);
    }

    public boolean verifOnlyWords(String chaine) {
        return chaine.matches("[A-Za-z]+");
    }

    public boolean verifEmail(String chaine) {
        return chaine.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
    }

    public boolean verifMdp(String chaine) {
        return (chaine.length() >= 8);
    }

}
