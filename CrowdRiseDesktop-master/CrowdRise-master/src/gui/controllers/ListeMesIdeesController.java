/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import gui.*;
import Dao.IdeeDAO;
import Dao.VoteDao;
import Entity.Idee;
import Entity.vote;
import crowdrise.main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author userpc
 */
public class ListeMesIdeesController implements Initializable {

   @FXML private ListView liste ;
   @FXML private Pane pane;
   @FXML private Button affMenu;
   @FXML private Pagination pagination;
   @FXML private TextField limitText;
   @FXML private AnchorPane pk;
    
   
    
    private Timeline timeline; 
    List<Idee> liste2 ;
    private final ObservableList<Idee> data = FXCollections.observableArrayList();
    private IntegerProperty limit;
    public static Idee idee2;

    public Idee getIdee2() {
        return idee2;
    }
    
    public void onBtnAffMenuClicked(ActionEvent event){
 
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    public void onMouseClicked(MouseEvent event){
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), -pane.getWidth());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play(); 
    }
    
    public void onRetourClicked(ActionEvent event){
       crowdrise.main.afficher(crowdrise.main.url_liste_idees, "Liste Des Idées", true); 
       //crowdrise.main.initBase(pk, crowdrise.main.url_liste_idees);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limit = new SimpleIntegerProperty(10);
        data.clear();
        IdeeDAO ideedao = new IdeeDAO();
        liste2 = new ArrayList<>();
        //changer diplay par IdMembre
        liste2 = ideedao.displayByIdMembre(main.getMembre().getId());
        
        //System.out.println("HERE LISTE 2 "+liste2);
        for (Idee idee : liste2) {
            System.out.println(idee); 
           data.add(idee); 
        }
         
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

                            Image img;
                            try {
                                
                                img = new Image(getClass().getResourceAsStream("/ressources/"+ob.getImage()), 45, 45, false, true);
                                
                            } catch (Exception e) {
                                System.out.println("Impossible de charger l'image");
                                img = new Image(getClass().getResourceAsStream("/ressources/cross.png"), 45, 45, false, true);
                            }
                             ImageView imgv = new ImageView(img);
                             grid.add(imgv, 0, 0);
                            
                            Label iconLogin = new Label(ob.getMembre().getNom());
                            iconLogin.setPrefWidth(50);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);
                            
                            Label iconNom = new Label("Titre : "+ob.getNom()+"\ndate de création "+ob.getDate());
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);
                            
                            ProgressIndicator pi = new ProgressIndicator();
                            VoteDao votedao = new VoteDao();
                            List<vote> votes = new ArrayList<vote>();
                            votes = votedao.displayByIdProjet(ob.getId());
                            pi.setProgress(votes.size()*0.01);
                            grid.add(pi, 2, 0);
                            
                            Label iconvote = new Label("votes");
                            grid.add(iconvote, 2, 1);
                            
                            setGraphic(grid);
                            
                        }
                    }   
            };
        
            return cell;
            }               
        });
        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeListeView(newValue.intValue(), limit.get());
            }

        });

        limit.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeListeView(pagination.getCurrentPageIndex(), newValue.intValue());
            }

        });
        
         liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Idee>() {

    @Override
    public void changed(ObservableValue<? extends Idee> observable, Idee oldValue, Idee newValue) {
        // Your action here
        System.out.println("Selected item: " + newValue);
        idee2 = newValue;
        
        crowdrise.main.initBase(pk,crowdrise.main.url_update_idee);
        //crowdrise.main.initBase(pk, crowdrise.main.url_update_idee);
       }
     });
        init();

     }; 
                            
        public void init() {
        pagination.setPageCount((int) (Math.ceil(data.size() * 1.0 / limit.get())));
        pagination.setCurrentPageIndex(0);
        changeListeView(0, limit.get());
    }
         
        public void changeListeView(int index, int limit) {
        int newIndex = index * limit;

        List<Idee> ls = data.subList(Math.min(newIndex, data.size()), Math.min(data.size(), newIndex + limit));
        liste.getItems().clear();
        liste.setItems(null);
        ObservableList<Idee> l = FXCollections.observableArrayList();
        liste.setItems(l);
        for (Idee t : ls) {
            l.add(t);
        }
        System.out.println("Size:" + l.size());
        limitText.setText("" + l.size());

    }
        
        @FXML
        public void changeLimit(ActionEvent evt) {

        TextField txt = (TextField) evt.getSource();
        if (txt != null) {
            try {
                int i = Integer.parseInt(txt.getText());
                limit.set(i);
                pagination.setPageCount((int) (Math.ceil(data.size() * 1.0 / limit.get())));
                
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }
    }
    
}
