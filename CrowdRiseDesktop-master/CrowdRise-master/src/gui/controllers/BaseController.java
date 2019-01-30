/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import crowdrise.main;
import static crowdrise.main.afficher;
import static crowdrise.main.url_authentification;
import static crowdrise.main.url_chart;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class BaseController implements Initializable {

    @FXML
    JFXDrawer drawer;
    @FXML
    JFXHamburger titleBurger;
    @FXML
    StackPane titleBurgerContainer;
    @FXML
    AnchorPane content;
    @FXML
    AnchorPane sideContent;
    private boolean visible = false;
    @FXML
    Label titre_page;
    @FXML
    JFXListView<Label> menu;
    @FXML
    JFXButton deconnect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        main.initBase(content, main.url_main_home_page);
        drawer.setOnDrawingAction((e) -> {
            titleBurger.getAnimation().setRate(1);
            titleBurger.getAnimation().setOnFinished((event) -> visible = true);
            titleBurger.getAnimation().play();
        });
        drawer.setOnHidingAction((e) -> {
            titleBurger.getAnimation().setRate(-1);
            titleBurger.getAnimation().setOnFinished((event) -> visible = false);
            titleBurger.getAnimation().play();
        });
        titleBurgerContainer.setOnMouseClicked((e) -> {
            if (!visible) {
                drawer.draw();
                visible = true;
            } else {
                drawer.hide();
                visible = false;
            }
        });
        if (main.getMembre().getId() != 0) {
            ArrayList<Label> labels = new ArrayList<>();
            labels.add(CreateLink("Accueil", main.url_main_home_page));
            labels.add(CreateLink("Profil", main.url_profil));
            labels.add(CreateLink("Projets CF", main.url_liste_projet_cf));
            labels.add(CreateLink("Liste des idées", main.url_liste_idees));
            labels.add(CreateLink("Problèmes", main.url_probleme));
            labels.add(CreateLink("Reclamation", main.url_reclamation));
            labels.add(CreateLink("Envoyer un email", main.url_Mail));
            labels.add(CreateLink("Mes solutions", main.url_my_sol));

            ObservableList<Label> data = FXCollections.observableArrayList(labels);
            menu.setItems(data);
        } else {
            ArrayList<Label> labels = new ArrayList<>();
            labels.add(CreateLink("Profil", main.url_profil_admin));
            labels.add(CreateLink("Membres et compétences", main.url_liste_membre));
            labels.add(CreateLink("Liste des problèmes", main.url_admin_probleme));
            labels.add(CreateLink("Liste des idées & Projets CF", main.url_ListeIdeeAdmin));
            labels.add(CreateLink("liste des reclamations", main.url_list_reclamation));
            labels.add(CreateLink("Commentaires signalés", main.url_comm_signale));
            labels.add(CreateLink("Chart Projet CF", main.url_chart));

            ObservableList<Label> data = FXCollections.observableArrayList(labels);
            menu.setItems(data);
        }

    }

    public Label CreateLink(String name, String url) {
        Label l = new Label(name);
        l.setOnMouseClicked((event) -> {
            main.initBase(content, url);
            drawer.hide();
            titre_page.setText(name);
        });
        return l;
    }
    
    @FXML
    public void onBtnDeconncetClicked(ActionEvent event) {
        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Se déconnecter");
        msg.setHeaderText(null);
        msg.setContentText("Voulez vous vraiment vous déconnecter?");
        Optional<ButtonType> result = msg.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (main.getMembre().getId()!=0)
            {
                main.setMembre(null);
                afficher(url_authentification, "Se connecter", true);
            }
            else
            {
                main.setAdmin(null);
                afficher(url_authentification, "Se connecter", true);
            }
        } 
    }

}
