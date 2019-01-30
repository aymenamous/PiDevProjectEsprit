/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;


import Dao.CompetenceDao;
import Dao.ListeCompetencesDao;
import crowdrise.main;
import Dao.MembreDao;
import Entity.Competence;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Entity.Membre;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class ProfilController implements Initializable {

    @FXML
    public JFXTextField nom_field;
    @FXML
    JFXTextField prenom_field;
    @FXML
    JFXTextField email_field;
    @FXML
    JFXPasswordField mdp_field;
    @FXML
    JFXTextField adresse_field;
    @FXML
    JFXTextField telephone_field;
    @FXML
    JFXTextField cr_field;
    @FXML
    JFXPasswordField nouveau_field;
    @FXML
    JFXPasswordField confirmer_field;
    @FXML
    Pane pane_parametre_compte;
    @FXML
    ScrollPane pane_competence;
    @FXML
    AnchorPane anchor_competence;
    @FXML
    Label telephone_label;
    @FXML
    Label adresse_label;
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
    Button btn_adresse;
    @FXML
    Button btn_telephone;
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
    Button btn_annuler_adresse;
    @FXML
    Button btn_annuler_telephone;
    @FXML
    ImageView imgv_photo;
    
    public static ImageView imv;
    @FXML
    Hyperlink link_photo;
    MembreDao mdao;
    ListeCompetencesDao lcdao;
    CompetenceDao compdao;
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
    SplitPane border;
    @FXML
    JFXDialogLayout dialog_layout;
    @FXML
    JFXButton action;
    @FXML
    JFXButton action1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imv=imgv_photo;
        nom_field.setText(main.getMembre().getNom());
        prenom_field.setText(main.getMembre().getPrenom());
        email_field.setText(main.getMembre().getEmail());

        telephone_field.setText(main.getMembre().getTelephone());
        adresse_field.setText(main.getMembre().getAdresse());
        nom_field.setDisable(true);
        prenom_field.setDisable(true);
        email_field.setDisable(true);
        mdp_field.setDisable(true);
        adresse_field.setDisable(true);
        telephone_field.setDisable(true);
        confirmer_field.setVisible(false);
        nouveau_field.setVisible(false);
        nouveau_label.setVisible(false);
        confirmer_label.setVisible(false);
        btn_annuler_adresse.setDisable(true);
        btn_annuler_email.setDisable(true);
        btn_annuler_mdp.setDisable(true);
        btn_annuler_nom.setDisable(true);
        btn_annuler_prenom.setDisable(true);
        btn_annuler_telephone.setDisable(true);
        mdao = new MembreDao();
        lcdao = new ListeCompetencesDao();
        compdao = new CompetenceDao();
        pane_competence.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pane_competence.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        restrict();
        FillCompetence();
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
        cr_field.setText(main.getMembre().getCr() + "");
        initDialog();
        action1.setOnMouseClicked((event) -> dialog.close());
    }

    public void afficherPhotoProfil() {
        try {
            if (main.getMembre().getPhoto().startsWith("file:")) {
                Image img = new Image((main.getMembre().getPhoto()), 154, 145, false, true);
                imgv_photo.setImage(img);
            } else {
                
                System.out.println(getClass().getResourceAsStream("/photo/1.png"));
                Image img = new Image(getClass().getResourceAsStream(main.getMembre().getPhoto()), 154, 145, false, true);
                imgv_photo.setImage(img);
            }
        } catch (Exception e) {
            System.out.println("Impossible de charger l'image");
        }
    }

    public void initDialog() {
        dialog_layout.setPrefHeight(100);
        dialog_layout.setPrefWidth(500);
        dialog.setPrefHeight(1500);
        dialog.setPrefWidth(2000);
        dialog.setLayoutX(base.getPrefWidth() / 2 - dialog.getPrefWidth() / 2);
        dialog.setLayoutY(base.getPrefHeight() / 2 - dialog.getPrefHeight() / 2);

    }

    public void activateDialog(String title, String content, JFXDialog.DialogTransition transition) {
        dialog.setTransitionType(transition);
        title_dialog.setText(title);
        content_dialog.setText(content);
        Text t = new Text(title);
        dialog.show(base);
        action.setVisible(true);
    }

    public void failure() {
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 30, 0, true, true);
        title_dialog.setStyle("-fx-text-fill:#E74C3C;-fx-font-size:16px;");
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));
        action.setVisible(false);

    }

    public void onLinkPhotoClicked(ActionEvent event) {
        dialog_btn.getChildren().clear();
        title_dialog.setStyle("");
        title_dialog.setGraphic(null);
        activateDialog("Modifier la photo de profil", "", JFXDialog.DialogTransition.CENTER);
        action.setVisible(false);
        JFXButton b = new JFXButton("Importer une photo");
        b.setOnMouseClicked((e) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Ouvrir");
            File f = fc.showOpenDialog(main.stage);
            if (f != null) {
                String path = "file:" + f.getAbsolutePath();
                path = path.replace("\\", "/");
                main.getMembre().setPhoto(path);
                mdao.updateAllById(main.getMembre());
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
                main.getMembre().setNom(nom_field.getText());
                mdao.updateAllById(main.getMembre());
                nom_field.setDisable(true);
                btn_annuler_nom.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
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
                main.getMembre().setPrenom(prenom_field.getText());
                mdao.updateAllById(main.getMembre());
                prenom_field.setDisable(true);
                btn_annuler_prenom.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
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
                main.getMembre().setEmail(email_field.getText());
                mdao.updateAllById(main.getMembre());
                email_field.setDisable(true);
            } else {
                dialog_btn.getChildren().clear();
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
            if (mdp_field.getText().equals(main.getMembre().getMdp())) {
                if (nouveau_field.getText().equals(confirmer_field.getText()) && verifMdp(nouveau_field.getText())) {
                    btn_annuler_mdp.setDisable(true);
                    main.getMembre().setMdp(nouveau_field.getText());
                    mdao.updateAllById(main.getMembre());
                    normalMode();
                } else if (!verifMdp(nouveau_field.getText())) {
                    dialog_btn.getChildren().clear();
                    activateDialog("Modification impossible", "Le mot de passe doit au moins contenir 8 caractères", JFXDialog.DialogTransition.CENTER);
                    failure();
                } else if (!nouveau_field.getText().equals(confirmer_field.getText())) {
                    dialog_btn.getChildren().clear();
                    activateDialog("Modification impossible", "Les mots de passe ne correspondent pas", JFXDialog.DialogTransition.CENTER);
                    failure();
                }

            } else {
                dialog_btn.getChildren().clear();
                activateDialog("Modification impossible", "Mot de passe erroné", JFXDialog.DialogTransition.CENTER);
                failure();
            }
        }

    }

    public void onBtnAdresseClicked(ActionEvent event) {
        if (adresse_field.isDisabled()) {
            btn_annuler_adresse.setDisable(false);
            adresse_field.setDisable(false);
        } else {
            btn_annuler_adresse.setDisable(true);
            main.getMembre().setAdresse(adresse_field.getText());
            mdao.updateAllById(main.getMembre());
            adresse_field.setDisable(true);
        }
    }

    public void onBtnTelephoneClicked(ActionEvent event) {
        if (telephone_field.isDisabled()) {
            btn_annuler_telephone.setDisable(false);
            telephone_field.setDisable(false);
        } else {
            if (verifOnlyDigits(telephone_field.getText())) {
                btn_annuler_telephone.setDisable(true);
                main.getMembre().setTelephone(telephone_field.getText());
                mdao.updateAllById(main.getMembre());
                telephone_field.setDisable(true);
            } else {
                activateDialog("Modification impossible", "Numéro de téléphone erroné", JFXDialog.DialogTransition.CENTER);
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
        nom_field.setText(main.getMembre().getNom());
        nom_field.setDisable(true);
        btn_annuler_nom.setDisable(true);
    }

    public void onBtnAnnulerPrenomClicked(ActionEvent event) {
        prenom_field.setText(main.getMembre().getPrenom());
        prenom_field.setDisable(true);
        btn_annuler_prenom.setDisable(true);
    }

    public void onBtnAnnulerEmailClicked(ActionEvent event) {
        email_field.setText(main.getMembre().getEmail());
        email_field.setDisable(true);
        btn_annuler_email.setDisable(true);
    }

    public void onBtnAnnulerMdpClicked(ActionEvent event) {
        mdp_field.setText("");
        mdp_field.setDisable(true);
        btn_annuler_mdp.setDisable(true);
        normalMode();
    }

    public void onBtnAnnulerAdresseClicked(ActionEvent event) {
        adresse_field.setText(main.getMembre().getAdresse());
        adresse_field.setDisable(true);
        btn_annuler_adresse.setDisable(true);
    }

    public void onBtnAnnulerTelephoneClicked(ActionEvent event) {
        telephone_field.setText(main.getMembre().getTelephone());
        telephone_field.setDisable(true);
        btn_annuler_telephone.setDisable(true);
    }

    public void onBtnAjouterCompetenceClicked(ActionEvent event) {
        List<String> liste = lcdao.displayAll();
        List<String> duplicate = new ArrayList<>();
        for (Competence c : main.getMembre().getCompetences()) {
            for (String s : liste) {
                if (s.equals(c.getnom_Competence())) {
                    duplicate.add(s);
                }
            }
        }
        liste.removeAll(duplicate);
        if (liste.isEmpty()) {
            dialog_btn.getChildren().clear();
            activateDialog("Ajout impossible", "Vous ne pouvez plus ajouter des compétences", JFXDialog.DialogTransition.CENTER);
            failure();
        } else {
            dialog_btn.getChildren().clear();
            activateDialog("Choix de la compétence", "Choisissez une compétence", JFXDialog.DialogTransition.CENTER);
            JFXComboBox<String> choice = new JFXComboBox<>();
            ObservableList<String> obList = FXCollections.observableArrayList(liste);
            choice.setItems(obList);
            choice.setValue(obList.get(0));
            choice.setLayoutY(70);
            dialog_btn.getChildren().add(choice);
            action.setOnMouseClicked((e) -> {
                Competence c = new Competence(choice.getValue(), new Membre(main.getMembre().getId()));
                compdao.add(c);
                main.getMembre().setCompetences(compdao.displayByIdMembre(main.getMembre().getId()));
                FillCompetence();
                dialog.close();
            });
        }
    }

    public void mdpModifMode() {
        telephone_field.setLayoutY(285);
        telephone_label.setLayoutY(285);
        btn_telephone.setLayoutY(285);
        adresse_field.setLayoutY(250);
        adresse_label.setLayoutY(250);
        btn_adresse.setLayoutY(250);
        btn_annuler_adresse.setLayoutY(250);
        btn_annuler_telephone.setLayoutY(285);
        confirmer_field.setVisible(true);
        nouveau_field.setVisible(true);
        nouveau_label.setVisible(true);
        confirmer_label.setVisible(true);
        mdp_field.setDisable(false);
        pane_parametre_compte.setPrefHeight(320);
    }

    public void normalMode() {
        telephone_field.setLayoutY(215);
        telephone_label.setLayoutY(215);
        btn_telephone.setLayoutY(215);
        adresse_field.setLayoutY(180);
        adresse_label.setLayoutY(180);
        btn_adresse.setLayoutY(180);
        btn_annuler_adresse.setLayoutY(180);
        btn_annuler_telephone.setLayoutY(215);
        confirmer_field.setVisible(false);
        nouveau_field.setVisible(false);
        nouveau_label.setVisible(false);
        confirmer_label.setVisible(false);
        mdp_field.setDisable(true);
        pane_parametre_compte.setPrefHeight(260);
    }

    public void extend() {
        pane_parametre_compte.setPrefHeight(260);

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
        adresse_field.setVisible(true);
        adresse_label.setVisible(true);
        btn_adresse.setVisible(true);
        telephone_field.setVisible(true);
        telephone_label.setVisible(true);
        btn_telephone.setVisible(true);
        btn_annuler_adresse.setVisible(true);
        btn_annuler_email.setVisible(true);
        btn_annuler_mdp.setVisible(true);
        btn_annuler_nom.setVisible(true);
        btn_annuler_prenom.setVisible(true);
        btn_annuler_telephone.setVisible(true);
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
        adresse_field.setVisible(false);
        adresse_label.setVisible(false);
        btn_adresse.setVisible(false);
        telephone_field.setVisible(false);
        telephone_label.setVisible(false);
        btn_telephone.setVisible(false);
        btn_annuler_adresse.setVisible(false);
        btn_annuler_email.setVisible(false);
        btn_annuler_mdp.setVisible(false);
        btn_annuler_nom.setVisible(false);
        btn_annuler_prenom.setVisible(false);
        btn_annuler_telephone.setVisible(false);
    }

    public void FillCompetence() {
        anchor_competence.getChildren().clear();
        anchor_competence.setPrefHeight(140);
        double y = 5;
        double x = 5;
        Image img = new Image(getClass().getResourceAsStream("/ressources/cross_comp.png"), 10, 10, true, true);
        for (Competence c : main.getMembre().getCompetences()) {

            ImageView imgv = new ImageView(img);
            Button b = new Button(c.getnom_Competence(), imgv);
            b.setContentDisplay(ContentDisplay.RIGHT);
            b.setDefaultButton(true);
            b.setId("" + c.getId());
            b.setPrefWidth(35 + b.getText().length() * 8);
            if (x + b.getPrefWidth() >= anchor_competence.getPrefWidth()) {
                x = 5;
                y = y + 30;
                if (y + 30 >= anchor_competence.getPrefHeight()) {
                    anchor_competence.setPrefHeight(y + 30);
                }

            }

            b.setLayoutY(y);
            b.setLayoutX(x);
            anchor_competence.getChildren().add(b);

            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    anchor_competence.getChildren().remove(b);
                    compdao.deleteById(Integer.parseInt(b.getId()));
                    main.getMembre().getCompetences().remove(c);
                    FillCompetence();
                }
            });
            x = x + b.getPrefWidth() * 1.2;
        }
    }

    public void onBtnAjouterClicked(ActionEvent event) {
        dialog_btn.getChildren().clear();
        JFXTextField t = new JFXTextField();
        Label l = new Label("Combien de point CR voulez vous ajouter ? (1CR = 1DT)");
        t.setMaxWidth(100);
        t.setPrefWidth(100);
        dialog_btn.getChildren().add(l);
        dialog_btn.getChildren().add(t);
        t.setLayoutY(50);
        controleSaisieCr(t);
        activateDialog("Ajout de points", "", JFXDialog.DialogTransition.CENTER);
        action.setOnMouseClicked((e) -> {
            if (verifOnlyDigits1(t.getText())) {
                main.getMembre().setCr(main.getMembre().getCr() + Integer.valueOf(t.getText()));
                mdao.updateAllById(main.getMembre());
                dialog.close();
                cr_field.setText(main.getMembre().getCr() + "");
            }

        });
    }

    public void onBtnConvertirClicked(ActionEvent event) {
        dialog_btn.getChildren().clear();
        JFXTextField t = new JFXTextField();
        Label l = new Label("Combien de point CR voulez vous convertir ? (1CR = 1DT)");
        t.setMaxWidth(100);
        t.setPrefWidth(100);
        dialog_btn.getChildren().add(l);
        dialog_btn.getChildren().add(t);

        t.setLayoutY(50);
        controleSaisieCr(t);
        activateDialog("Conversion des points", "", JFXDialog.DialogTransition.CENTER);
        action.setOnMouseClicked((e) -> {
            if (verifOnlyDigits1(t.getText()) && main.getMembre().getCr() - Integer.valueOf(t.getText()) >= 0) {
                main.getMembre().setCr(main.getMembre().getCr() - Integer.valueOf(t.getText()));
                mdao.updateAllById(main.getMembre());
                dialog.close();
                cr_field.setText(main.getMembre().getCr() + "");
            }
        });
    }

    public void controleSaisieCr(JFXTextField t) {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!verifOnlyDigits1(textField.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Valeur invalide");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        t.getValidators().add(v);
        t.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                t.validate();
            }
        });
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

    public boolean verifOnlyDigits(String chaine) {
        return chaine.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    }

    public boolean verifOnlyDigits1(String chaine) {
        return chaine.matches("[0-9]+");
    }
}
