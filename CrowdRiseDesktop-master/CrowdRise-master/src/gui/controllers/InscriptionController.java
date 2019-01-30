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
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import crowdrise.main;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXTextField nom_field;
    @FXML
    private JFXTextField prenom_field;
    @FXML
    private JFXTextField email_field;
    @FXML
    private JFXPasswordField mdp_field;
    @FXML
    private JFXPasswordField confirmer_field;
    @FXML
    private AnchorPane base;
    @FXML
    private Button btn_creer_compte;
    @FXML
    private Button btn_annuler;
    Tooltip toolt_nom_field;
    Tooltip toolt_prenom_field;
    Tooltip toolt_email_field;
    Tooltip toolt_mdp_field;
    @FXML
    private JFXButton btn_dialog;
    @FXML
    private Label content_dialog;
    @FXML
    private Label title_dialog;
    @FXML
    private JFXDialog dialog;
    @FXML
    private BorderPane border;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        toolt_nom_field = new Tooltip("Un nom ne peut comporter que des lettres");
        toolt_email_field = new Tooltip("exmple: exemple@gmail.com");
        toolt_mdp_field = new Tooltip("Le mot de passe doit contenir au moins 8 caractères");
        toolt_prenom_field = new Tooltip("Un prénom ne peut comporter que des lettres");
        nom_field.setTooltip(toolt_nom_field);
        prenom_field.setTooltip(toolt_prenom_field);
        email_field.setTooltip(toolt_email_field);
        mdp_field.setTooltip(toolt_mdp_field);
        ControleSaisieEmail();
        ControleSaisieMdp();
        ControleSaisieNom();
        ControleSaisiePrenom();
        ControleSaisieConfirmer();
        initDialog();
        

    }

    public void initDialog() {
        
        dialog.setPrefWidth(base.getPrefWidth());
        dialog.setPrefHeight(base.getPrefHeight());
        dialog.setLayoutX(base.getPrefWidth() / 2 - dialog.getPrefWidth() / 2);
        dialog.setLayoutY(base.getPrefHeight() / 2 - dialog.getPrefHeight() / 2);
        

    }

    public void activateDialog(String title, String content, JFXDialog.DialogTransition transition) {
        dialog.setTransitionType(transition);
        title_dialog.setText(title);
        content_dialog.setText(content);
        Text t=new Text(title);
        dialog.show(base);
    }

    public void success() {
        title_dialog.setStyle("-fx-text-fill:#2ECC71;-fx-font-size:16px;");
        Image img = new Image(getClass().getResourceAsStream("/ressources/success.png"), 25, 0, true, true);
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));
        btn_dialog.setOnMouseClicked((e) -> {
            dialog.close();
        });

    }

    public void failure() {
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 30, 0, true, true);
        title_dialog.setStyle("-fx-text-fill:#E74C3C;-fx-font-size:16px;");
        title_dialog.setContentDisplay(ContentDisplay.RIGHT);
        title_dialog.setGraphic(new ImageView(img));

        btn_dialog.setOnMouseClicked((e) -> {
            dialog.close();
            border.setDisable(false);
        });
    }

    public void ControleSaisieNom() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!verifOnlyWords(textField.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Un nom ne peut contenir que des lettres");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        nom_field.getValidators().add(v);
        nom_field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                nom_field.validate();
            }
        });
    }

    public void ControleSaisieMdp() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!verifMdp(textField.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Un mot de passe doit contenir au moins 8 caractères");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        mdp_field.getValidators().add(v);
        mdp_field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                mdp_field.validate();
            }
        });
    }

    public void ControleSaisieEmail() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!verifEmail(textField.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Email invalide");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        email_field.getValidators().add(v);
        email_field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                email_field.validate();
            }
        });
    }

    public void ControleSaisiePrenom() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!verifOnlyWords(textField.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Un prenom ne peut contenir que des lettres");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        prenom_field.getValidators().add(v);
        prenom_field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                prenom_field.validate();
            }
        });
    }

    public void ControleSaisieConfirmer() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();
                if (!confirmer_field.getText().equals(mdp_field.getText())) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Les mots de passe ne correspondent pas");
        Image img = new Image(getClass().getResourceAsStream("/ressources/warning.png"), 18, 0, true, true);
        v.setIcon(new ImageView(img));
        confirmer_field.getValidators().add(v);
        confirmer_field.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                confirmer_field.validate();
            }
        });
    }

    public void onBtnCreerCompteClicked(ActionEvent event) {

        if (nom_field.validate() && prenom_field.validate() && mdp_field.validate() && email_field.validate() && confirmer_field.validate()) {
            MembreDao md = new MembreDao();
            AdminDao ad=new AdminDao();
            if (md.displayByEmail(email_field.getText()).getId() == 0 && ad.displayByEmail(email_field.getText()).getId() ==0) {
                Membre m = new Membre(nom_field.getText(), prenom_field.getText(), email_field.getText(), mdp_field.getText());
                md.add(m);
                activateDialog("Compte créé", "Votre compte a été créé avec succès",JFXDialog.DialogTransition.RIGHT);
                success();
            } else {
                activateDialog("Inscription Impossible", "Cette adresse mail est déjà utilisée",JFXDialog.DialogTransition.LEFT);
                failure();
            }
        } else {
            activateDialog("Inscription Impossible", "Un ou plusieurs champs sont erronés",JFXDialog.DialogTransition.LEFT);
                failure();
        }
    }

    public void onBtnAnnulerClicked(ActionEvent event) throws IOException {
        main.afficher(main.url_authentification, "Authentification", false);
    }

    public void onConfirmerFieldChanged(KeyEvent event) {
        if (confirmer_field.getText().equals(mdp_field.getText())) {
            confirmer_field.setStyle("-fx-border-color: green;");
        } else {
            confirmer_field.setStyle("-fx-border-color: red;");
        }
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
