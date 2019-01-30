/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.ProblemDAO;
import Dao.ProblemDAO;
import Entity.Problem;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author H4DH
 */
public class AdminListeProblemeController implements Initializable {
        
   @FXML private ListView liste ;
   @FXML private Pane pane;
   @FXML private Button affMenu;
   @FXML private JFXButton ajouterIdee;
   @FXML private Pagination pagination;
   
    
    private Timeline timeline; 
    List<Problem> liste2 ;
    private final ObservableList<Problem> data = FXCollections.observableArrayList();
    private IntegerProperty limit;
    public static Problem prob2;

    public Problem getIdee2() {
        return prob2;
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
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        limit = new SimpleIntegerProperty(10);
        data.clear();
        ProblemDAO problemdao = new ProblemDAO();;
        liste2 = new ArrayList<>();
        liste2 = problemdao.displayAllByStatut();
        
        System.out.println("HERE LISTE ADMIN "+liste2);
        for (int i = 0; i < liste2.size(); i++) {
             Problem prob = new Problem(liste2.get(i).getDate(),liste2.get(i).getDescription(), liste2.get(i).getId_membre(),liste2.get(i).getTitre()); 
            prob.setId(liste2.get(i).getId());
           data.add(prob); 
        }
         // here 
        liste.setCellFactory(new Callback<ListView<Problem>, ListCell<Problem>>() {

            @Override
            public ListCell<Problem> call(ListView<Problem> param) {
                ListCell<Problem> cell = new ListCell<Problem>(){
                    
                    @Override
                    protected void updateItem(Problem ob , boolean b){
                      super.updateItem(ob, b);
                        if (ob!=null) {
                            
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(0, 10, 0, 10));
                            
//                            Image img = new Image(getClass().getResource("/gui/utility/details.png").toExternalForm());
//                            ImageView imgv = new ImageView(img);
//                            grid.add(imgv, 0,0);

                            Label iconLogin = new Label("Hadhemi Laouini ");
                            iconLogin.setMaxWidth(100);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);
                            
                            Label iconNom = new Label("Titre : "+ob.getTitre()+"\ndate de création 21/10/2016");
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);
                            
                            // added 
                                                        Button btnAccepte = new Button("Accepter");
                            btnAccepte.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   ProblemDAO problemdao = new ProblemDAO();
                                   problemdao.updateStatutById(ob.getId(), 1);
                                   liste.getItems().remove(ob);
                                   
                                }
                            });
                            grid.add(btnAccepte, 2, 0);
                            
                            Button btnRefuse = new Button("Refuser");
                            btnRefuse.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                   ProblemDAO problemdao = new ProblemDAO();
                                   problemdao.updateStatutById(ob.getId(), -1);
                                   liste.getItems().remove(ob);
                                }
                            });
                            grid.add(btnRefuse, 3, 0);
                            // ends here 
                            
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
        
         liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Problem>() {

    @Override
    public void changed(ObservableValue<? extends Problem> observable, Problem oldValue, Problem newValue) {
        // Your action here
        System.out.println("Selected item: " + newValue);
        prob2 = newValue;
        
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/views/updateProblemAdmin.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Informations Problem Admin");
            stage.setScene(new Scene(root));  
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminListeProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        List<Problem> ls = data.subList(Math.min(newIndex, data.size()), Math.min(data.size(), newIndex + limit));
        liste.getItems().clear();
        liste.setItems(null);
        ObservableList<Problem> l = FXCollections.observableArrayList();
        liste.setItems(l);
        for (Problem t : ls) {
            l.add(t);
        }
        System.out.println("Size:" + l.size());

    }
        
        @FXML
        public void changeLimit(ActionEvent evt) {

        TextField txt = (TextField) evt.getSource();
        if (txt != null) {
            try {
                int i = Integer.parseInt(txt.getText());
                limit.set(i);
                pagination.setPageCount((int) (Math.ceil(data.size() * 1.0 / limit.get())));
                System.out.println(liste.getItems().size());
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }
    }
    }    
    
