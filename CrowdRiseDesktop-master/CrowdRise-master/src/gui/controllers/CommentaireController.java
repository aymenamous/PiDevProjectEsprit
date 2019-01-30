/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.CommentaireDao;
import Dao.ReclamationDao;
import Entity.Commentaire;
import Entity.Idee;
import Entity.Membre;
import Entity.Projet;
import Entity.ProjetCF;
import Entity.Reclamation;
import com.jfoenix.controls.JFXDecorator;
import crowdrise.main;
import static crowdrise.main.stage;
import java.io.IOException;
import java.net.URL;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.xml.stream.events.Comment;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class CommentaireController implements Initializable {
    
    @FXML
    private TextField TxtFieldCom;
    @FXML
    ObservableList<Commentaire> items = FXCollections.observableArrayList();
    @FXML
    private ListView<Commentaire> lst = new ListView<>(items);
    List<Commentaire> liste2;
    public static int id;
    
    public CommentaireController() {
    }
    public void btnRec(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/views/Reclamation.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Reclamation");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void btnCom(ActionEvent event) throws IOException {
        String com = TxtFieldCom.getText();
        if (!com.isEmpty()) {
            Commentaire c;
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
         //  c=new   Commentaire(ListProjetsCFController.getProjetCF2(),main.getMembre(),TxtFieldCom.getText());
            c = new Commentaire(new Projet(id), main.getMembre(), TxtFieldCom.getText());
            CommentaireDao mdao = new CommentaireDao();
            mdao.add(c);
            TxtFieldCom.clear();
            lst.getItems().addAll(c);
        } else {    
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setTitle("Saisissez votre commentaire");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items.clear();
        CommentaireDao cdao = new CommentaireDao();
        liste2 = new ArrayList<>();
        liste2 = cdao.displayByIdProjet(id);
        System.out.println("HERE LISTE 2 " + liste2);
        for (int i = 0; i < liste2.size(); i++) {
            items.add(new Commentaire(liste2.get(i).getId(), liste2.get(i).getProjet(), liste2.get(i).getMembre(), liste2.get(i).getText_commentaire()));
        }
        lst.setEditable(true);
        lst.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
            @Override
            public ListCell< Commentaire> call(ListView<Commentaire> param) {
                ListCell<Commentaire> cell;
                cell = new ListCell<Commentaire>() {
                    @Override
                    protected void updateItem(Commentaire ob, boolean b) {
                        super.updateItem(ob, b);
                        if (ob != null) {
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(1, 0, 1, 0));
                            TextArea text = new TextArea(ob.getText_commentaire());
                            AnchorPane p=new AnchorPane();
                            p.setPrefHeight(30);
                            p.setPrefWidth(80);
                            signalButton(p,ob.getId());
                            if (ob.getMembre().getId() == main.getMembre().getId()) {
                                text.setEditable(true);
                            } else {
                                text.setEditable(false);
                            }
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);
                            grid.add(p,2, 0);
                            java.sql.Date dateF = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                            Calendar cal = Calendar.getInstance();
                            Label date;
                            date = new Label(dateF.toString());
                            grid.add(date, 1, 1);
                            setGraphic(grid);
                        }}
                };
              return cell;
            }
         });
        lst.setItems(items);
   

    }
    
    @FXML
    void startEdit() throws SQLException
    {
        ObservableList<Commentaire>selected,reste;
         reste= lst.getItems();
         selected= lst.getSelectionModel().getSelectedItems();
         Commentaire c =new Commentaire();
         for (Commentaire supp1 : selected) {
             //recuperer l'id du membre connecté
               // if (supp1.getMembre().getId()==main.getMembre().getId())
             if(supp1.getMembre().getId()!=main.getMembre().getId())
                { 
            Alert msg=new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Erreur");
            msg.setContentText("Impossible de supprimer ce commentaire");
            msg.setHeaderText(null);
            msg.showAndWait();
                }             
                else 
                {                   
            Alert msg=new Alert(Alert.AlertType.CONFIRMATION);
            msg.setTitle("Suppression");
            msg.setContentText("Voulez vous supprimer  ce commentaire? ");
              
            Optional<ButtonType> result=msg.showAndWait();
            if (result.get()==ButtonType.OK)
            {
                CommentaireDao  c2 = new CommentaireDao();      
             selected.forEach(reste::remove);
             c2.Delete(supp1);
            }
            
              }
    }}
    
    void signalButton(AnchorPane base, int id)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/gui/views/Signalisation.fxml"));
        try {
            base.getChildren().clear();
            AnchorPane content = loader.load();
            SignalisationController controller=loader.<SignalisationController>getController();
            controller.id=id;            
            base.getChildren().add(content);  
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

