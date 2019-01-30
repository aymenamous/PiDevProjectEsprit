/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.MembreDao;
import Entity.Admin;
import Entity.Membre;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import crowdrise.main;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class ListeMembresController implements Initializable {

    @FXML
    GridPane grid_membres;
    @FXML
    AnchorPane anchor_liste;
    MembreDao mdao;
    @FXML
    JFXTextField recherche_field;
    @FXML
    JFXComboBox<String> filtre;

    @FXML AnchorPane gestion_competence;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> liste = new ArrayList<>();
        liste.add("Nom");
        liste.add("Prenom");
        liste.add("Email");
        filtre.setItems(FXCollections.observableArrayList(liste));
        filtre.setValue("Nom");
        filtre.setOnAction((event)->fillTable());
        fillTable();
        main.initBase(gestion_competence, "/gui/views/Competence.fxml");
        

    }

    public void fillTable() {
        anchor_liste.setPrefHeight(392);
        int t = 1;
        MembreDao mdao = new MembreDao();
        List<Membre> membres = new ArrayList<>();

        if (filtre.getValue() == "Nom") {
            Membre recherche = new Membre(recherche_field.getText(), "", "", "");
            membres = mdao.displayByAll(recherche);
        } else if (filtre.getValue() == "Prenom") {
            Membre recherche = new Membre("", recherche_field.getText(), "", "");
            membres = mdao.displayByAll(recherche);
        } else if (filtre.getValue() == "Email") {
            Membre recherche = new Membre("", "", recherche_field.getText(), "");
            membres = mdao.displayByAll(recherche);
        }
        
        for (int i = 1; i < 11; i++) {
            grid_membres.getRowConstraints().add(i, new RowConstraints(36));
            StackPane[] liste_stack = new StackPane[8];
            String color = "";
            if (i % 2 == 0) {
                color = "#CEE3F6";
            } else {
                color = "white";
            }

            for (int j = 0; j < 8; j++) {

                liste_stack[j] = new StackPane();
                liste_stack[j].setStyle("-fx-background-color:" + color + ";-fx-border-color: transparent gray transparent transparent");
                grid_membres.add(liste_stack[j], j, i);
            }
        }

        for (Membre m : membres) {
            if (t > 10) {
                anchor_liste.setPrefHeight(anchor_liste.getPrefHeight() + 36);
            }

            grid_membres.getRowConstraints().add(t, new RowConstraints(36));

            TextField[] liste_tfield = new TextField[8];
            liste_tfield[0] = new TextField("" + m.getId());
            liste_tfield[1] = new TextField(m.getNom());
            liste_tfield[2] = new TextField(m.getPrenom());
            liste_tfield[3] = new TextField(m.getEmail());
            liste_tfield[4] = new TextField(m.getTelephone());
            liste_tfield[5] = new TextField(m.getAdresse());
            liste_tfield[6] = new TextField(m.getNbr_solved() + "");
            JFXButton action = new JFXButton();
            action.setStyle("-fx-font-size:14px;-fx-background-color:WHITE;");
            action.setButtonType(JFXButton.ButtonType.RAISED);
            action.setId("" + m.getId());
            if (m.isStatut()) {
                action.setText("Bannir");
                action.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        MembreDao mdao = new MembreDao();
                        m.setStatut(false);
                        mdao.updateAllById(m);
                        action.setText("Activer");
                        fillTable();
                    }
                });
            } else {
                action.setText("Activer");
                action.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        MembreDao mdao = new MembreDao();
                        m.setStatut(true);
                        mdao.updateAllById(m);
                        action.setText("Bannir");
                        fillTable();
                    }
                });
            }
            StackPane[] liste_stack = new StackPane[8];
            String color = "";
            if (t % 2 == 0) {
                color = "#CEE3F6";
            } else {
                color = "white";
            }
            for (int j = 0; j < 7; j++) {
                liste_tfield[j].setEditable(false);
                liste_tfield[j].setStyle("-fx-background-insets: 0;-fx-background-color: transparent," + color + ", transparent, " + color + ";");
                liste_stack[j] = new StackPane(liste_tfield[j]);
                liste_stack[j].setStyle("-fx-background-color:" + color + ";-fx-border-color: transparent gray transparent transparent");
                grid_membres.add(liste_stack[j], j, t);
            }
            liste_stack[7] = new StackPane(action);
            liste_stack[7].setStyle("-fx-background-color:" + color + ";-fx-border-color: transparent gray transparent transparent");
            grid_membres.add(liste_stack[7], 7, t);

            t++;
        }
    }

    public void onRechercheFieldChanged(KeyEvent event) {
        fillTable();
    }
}
