/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.IdeeDAO;
import Dao.ProjetCFDao;
import Entity.Idee;
import Entity.ProjetCF;
import crowdrise.main;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author userpc
 */
public class ListeIdeesAdminController implements Initializable {

    @FXML private ListView liste ;
    
    
    List<Idee> listeIdee ;
    private final ObservableList<Idee> data = FXCollections.observableArrayList();
    
    @FXML
    private ListView listeCF;
    List<ProjetCF> listeProjetCF ;
    private final ObservableList<ProjetCF> dataCF = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IdeeDAO ideedao = new IdeeDAO();
        listeIdee = new ArrayList<>();
        //changer diplay par IdMembre
        listeIdee = ideedao.displayListEnAttente();

        for (Idee idee : listeIdee) { 
           data.add(idee); 
        }
        
        liste.getItems().addAll(data);
         
        liste.setCellFactory(new Callback<ListView<Idee>, ListCell<Idee>>() {

            @Override
            public ListCell<Idee> call(ListView<Idee> param) {
                ListCell<Idee> cell = new ListCell<Idee>(){
                    
                    @Override
                    protected void updateItem(Idee ob , boolean b){
                      super.updateItem(ob, b);
                        if (ob!=null) {
                            
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(0, 10, 0, 10));
                            
                            /*Image img = new Image(getClass().getResource("utility/profile.jpg").toExternalForm());
                            ImageView imgv = new ImageView(img);
                            grid.add(imgv, 0,0);*/
                            
//                            if (ob.getImage().startsWith("file:")) {
//                                Image img = new Image((ob.getImage()), 154, 145, true, true);
//                                ImageView imgv = new ImageView(img);
//                                imgv.setImage(img);
//                                grid.add(imgv, 0, 0);
//                            }
                            
                            Label iconLogin = new Label(ob.getMembre().getNom());
                            iconLogin.setMaxWidth(100);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);
                            
                            Label iconNom = new Label("Titre : "+ob.getNom()+"\ndate de création "+ob.getDate());
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);
                            
                            Button btnAccepte = new Button("Accepter");
                            btnAccepte.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   IdeeDAO ideedao = new IdeeDAO();
                                   ideedao.updateStatutById(ob.getId(), 1);
                                   liste.getItems().remove(ob);
                                   
                                }
                            });
                            grid.add(btnAccepte, 2, 0);
                            
                            Button btnRefuse = new Button("Refuser");
                            btnRefuse.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   IdeeDAO ideedao = new IdeeDAO();
                                   ideedao.updateStatutById(ob.getId(), -1);
                                   liste.getItems().remove(ob);
                                }
                            });
                            grid.add(btnRefuse, 3, 0);
                            
                            setGraphic(grid);
                            
                        }
                    }   
            };
        
            return cell;
            }               
        });
        
        
        ProjetCFDao PjCFdao = new ProjetCFDao();
        listeProjetCF = new ArrayList<>();
        //changer diplay par IdMembre
        listeProjetCF = PjCFdao.displayListEnAttente();

        for (ProjetCF CF : listeProjetCF) { 
           dataCF.add(CF); 
        }
        
        listeCF.getItems().addAll(dataCF);
         
        listeCF.setCellFactory(new Callback<ListView<ProjetCF>, ListCell<ProjetCF>>() {

            @Override
            public ListCell<ProjetCF> call(ListView<ProjetCF> param) {
                ListCell<ProjetCF> cell = new ListCell<ProjetCF>(){
                    
                    @Override
                    protected void updateItem(ProjetCF ob , boolean b){
                      super.updateItem(ob, b);
                        if (ob!=null) {
                            
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(0, 10, 0, 10));
                            
                            /*Image img = new Image(getClass().getResource("utility/profile.jpg").toExternalForm());
                            ImageView imgv = new ImageView(img);
                            grid.add(imgv, 0,0);*/
                            
                            if (ob.getImage().startsWith("file:")) {
                                Image img = new Image((ob.getImage()), 154, 145, true, true);
                                ImageView imgv = new ImageView(img);
                                imgv.setImage(img);
                                grid.add(imgv, 0, 0);
                            }
                            
                            Label iconLogin = new Label(ob.getMembre().getNom());
                            iconLogin.setMaxWidth(100);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);
                            
                            Label iconNom = new Label("Titre : "+ob.getNom()+"\ndate de création "+ob.getDate());
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);
                            
                            Button btnAccepte = new Button("Accepter");
                            btnAccepte.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   ProjetCFDao ProjCFdao = new ProjetCFDao();
                                   ProjCFdao.updateStatutById(ob.getId(), 1);
                                   liste.getItems().remove(ob);
                                   
                                }
                            });
                            grid.add(btnAccepte, 2, 0);
                            
                            Button btnRefuse = new Button("Refuser");
                            btnRefuse.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   ProjetCFDao ProjCFdao = new ProjetCFDao();
                                   ProjCFdao.updateStatutById(ob.getId(), -1);
                                   liste.getItems().remove(ob);
                                }
                            });
                            grid.add(btnRefuse, 3, 0);
                            
                            setGraphic(grid);
                            
                        }
                    }   
            };
        
            return cell;
            }               
        });
        
        
    }    
    
}
