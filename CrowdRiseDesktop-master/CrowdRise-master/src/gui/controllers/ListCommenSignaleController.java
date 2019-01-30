/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.CommentaireDao;
import Dao.SignalisationDao;
import Entity.Commentaire;
import Entity.Projet;
import Entity.Signalisation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class ListCommenSignaleController implements Initializable {

    @FXML 
 private TableView<Commentaire> TVCom;
    @FXML 
 private TableColumn<Commentaire,Integer> idCom;
    @FXML
 private TableColumn<Commentaire,String> textCom;
    @FXML 
 private TextField txtRech;
    
 List<Signalisation> listS;
 
 ObservableList<Signalisation>items=FXCollections.observableArrayList();
 
 List<Commentaire> listC;
 
 ObservableList<Commentaire>items2=FXCollections.observableArrayList();

    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CommentaireDao cdao=new CommentaireDao();
        SignalisationDao sdao=new SignalisationDao();
        Signalisation s;
        items.clear();
        listS=new ArrayList<>();
        listS=sdao.displayAll();
        listC=new ArrayList<>();
        items2.clear();
        for(int i=0;i<listS.size();i++)
        {
            items.add(new Signalisation(listS.get(i).getId()));
            System.out.println(listS.get(i).getId());
            listC=cdao.CommentaireSignale(listS.get(i).getId());
            System.out.println(listC);   
            for (Commentaire listC1 : listC) {
                items2.add(new Commentaire(listC1.getId(), listC1.getText_commentaire()));
            }
        }
        idCom.setCellValueFactory(new PropertyValueFactory<Commentaire,Integer>("id"));
        textCom.setCellValueFactory(new PropertyValueFactory<Commentaire,String>("text_commentaire"));
        TVCom.setItems(items2);
        
        
    }
    
     @FXML
    void rechercher(KeyEvent event) {
        Commentaire c=new Commentaire(0,txtRech.getText());
         CommentaireDao cdao=new CommentaireDao();
        List<Commentaire> commentaires = new ArrayList<>();

        commentaires= cdao.RechercherByAll(c);

        items2.clear();
        for (Commentaire rec : commentaires) {

            items2.add(rec);
        }

        TVCom.setItems(items2);

    }

    
}
