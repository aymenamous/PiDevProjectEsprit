/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.MembreDao;
import Dao.ReclamationDao;
import Entity.Admin;
import Entity.Commentaire;
import Entity.Membre;
import Entity.Reclamation;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import static crowdrise.main.stage;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<Object> table;
    
    @FXML 
    private TableView<Membre> tableM;

    @FXML
    private ObservableList<Object> items = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Object> items2 = FXCollections.observableArrayList();

    
    @FXML
    private TableColumn<Object, Integer> id;

    @FXML
    private TableColumn<Object, Membre> membre;
    
       
    @FXML
    private TableColumn<Object, Date> date;

    @FXML
    private TableColumn<Object, String> sujet;

    @FXML
    private TableColumn<Object, String> reclamation;

    
   

    @FXML
    private TextField recherche;

    List<Reclamation> liste1;

    List<Reclamation> liste2;
    
    Membre listM;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationDao rdao = new ReclamationDao();
        items.clear();
        liste2 = new ArrayList<>();
        liste2 = rdao.displayAll();

        MembreDao mdao=new MembreDao();

        for (Reclamation rec : liste2) {
            
            System.out.println("HEREE " + rec);
            listM= mdao.displayById(rec.getId());
            System.out.println(listM);
            rec.setId_membre(listM);
            
            items.add(rec);
            //items.add(listM.getNom());
             }

       
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
         membre.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        reclamation.setCellValueFactory(new PropertyValueFactory<>("Reclamation"));
        
        table.setItems(items);

    }
//
//    void changer() {
//        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
//
//            @Override
//            public void changed(ObservableValue<? extends Object> observable,Object oldValue, Object newValue) {
//
//                ReclamationDao rdao = new ReclamationDao();
//
//                int delete = table.getSelectionModel().selectedItemProperty().getValue().getId();
//                rdao.deleteById(delete);
//
//                TableViewSelectionModel selectionModel = table.getSelectionModel();
//                ObservableList selectedCells = selectionModel.getSelectedCells();
//                TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//                Object val = tablePosition.getTableColumn().getCellData(newValue);
//                System.out.println("Selected value IS :" + val);
//                System.out.println(table.getSelectionModel().getSelectedIndex());
//                System.out.println(table.getSelectionModel().selectedItemProperty().getValue().getId());
//
//                liste1 = new ArrayList<>();
//                liste1 = rdao.displayAll();
//                items2.clear();
//                for (Reclamation rec : liste1) {
//                    items2.add(rec);
//                }
//                table.setItems(items2);
//
//            }
//
//        });
//
//    }

    @FXML
    void rechercher(KeyEvent event) {
        ReclamationDao rdao = new ReclamationDao();
        List<Reclamation> reclamations = new ArrayList<>();
        Reclamation r = new Reclamation(0, null, null, recherche.getText(), "");

        reclamations = rdao.RechercherByAll(r);

        items.clear();
       for (Reclamation rec : reclamations) {
                  items.add(rec);

       }
               table.setItems(items);

       }

        
   

    @FXML
    void supprimer() throws SQLException
  {
        System.out.println("ssuupp");
        ObservableList<Object>selected,reste;
         reste= table.getItems();
         selected= table.getSelectionModel().getSelectedItems();
         System.out.println(selected);
             Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Suppression");
            msg.setContentText("Voulez vous supprimer  cette réclamation ? ");
              
            Optional<ButtonType> result=msg.showAndWait();
            if (result.get()==ButtonType.OK)
            {
                 selected.forEach(reste::remove);
          ReclamationDao  c2 = new ReclamationDao();
           
         for (Object supp1 : selected) {
               c2.Delete((Reclamation) supp1);
               
            
    }
            }
        
    
   
}
    
}

