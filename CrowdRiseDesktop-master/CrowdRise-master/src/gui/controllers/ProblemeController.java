/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;


import Entity.Problem;
import Dao.ProblemDAO;
import com.jfoenix.controls.JFXCheckBox;
import crowdrise.main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date ;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
public class ProblemeController implements Initializable
{   @FXML 
    private Button btnAjout; 
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtAjout;
    @FXML
    private TextField txtmotcles;
    @FXML 
    private ListView liste;
    @FXML private Pagination pagination;
    List<Problem> liste2 ;
    private final ObservableList<Problem> data = FXCollections.observableArrayList();
    private Timeline timeline; 
    private boolean show = false;
    @FXML 
    private Pane pane;
    @FXML 
    private Button lb;
    @FXML 
    private Button mesprob;
    @FXML private TextField limitText;
    private IntegerProperty limit;
    public static Problem pro;
    @FXML AnchorPane content;
    
    public Problem getProblem2() {
        return pro;
    }
  
    public void onBtnAjoutClicked(ActionEvent event)
    {
     timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        if (!show)
        {   KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), pane.getWidth());
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            show=true;
            lb.setText("Menu");
            //lb.setLayoutX(lb.getLayoutX()-35);
        }
        else
        {
            KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            show=false; 
            lb.setText("Menu");
            //lb.setLayoutX(lb.getLayoutX()+35);

        } 
    }

       public void onMesProblemesClicked(ActionEvent event) throws IOException
       {
            //main.initBase(content, "/gui/views/ListeMesProblems.fxml");
            Parent root=FXMLLoader.load(getClass().getResource("/gui/views/ListeMesProblems.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Mes Problèmes");
            stage.setScene(new Scene(root));
            stage.show();  
        }

        @Override
        public void initialize(URL url, ResourceBundle rb) 
        {   limit = new SimpleIntegerProperty(10);
            data.clear();
            ProblemDAO problemdao = new ProblemDAO();
            liste2 = new ArrayList<>();
            System.out.println("HEEEEREEE !!");
            liste2 = problemdao.displayAll();
            System.out.println("fuck this shit \n "+liste2);
            for (int i = 0; i < liste2.size(); i++)
            {
                
                Problem prob = new Problem(liste2.get(i).getDate(),liste2.get(i).getDescription(), liste2.get(i).getId_membre(),liste2.get(i).getTitre()); 
                prob.setId(liste2.get(i).getId());
               /* System.out.println("Fou9 DATA");
                System.out.println(liste2.get(i).getId()); 
                System.out.println(liste2.get(i).getDate());*/
               data.add(prob); 
               //System.out.println("WITH DATA "+data);
            }
            //System.out.println("HRLLOO");
            liste.setCellFactory(new Callback<ListView<Problem>, ListCell<Problem>>() 
             {

                @Override
                 public ListCell<Problem> call(ListView<Problem> param) 
                 {
                    ListCell<Problem> cell = new ListCell<Problem>()
                    {
                        
                        @Override
                        protected void updateItem(Problem ob , boolean b)
                        {
                          super.updateItem(ob, b);
                            //System.out.println("CELL BEFORE");
                            if (ob!=null) 
                            {
                                //System.out.println("grriddd  ");
                                GridPane grid = new GridPane();
                                grid.setHgap(10);
                                grid.setVgap(4);
                                grid.setPadding(new Insets(0, 10, 0, 10));
                                Image img = new Image(getClass().getResource("/gui/utility/details.png").toExternalForm());
                                ImageView imgv = new ImageView(img);
                                grid.add(imgv, 0,0);
                                
                                System.out.println("");   
                                
                                Label iconLogin = new Label("Username");
                                iconLogin.setMaxWidth(100);
                                iconLogin.setWrapText(true);
                                grid.add(iconLogin, 0, 1);

                                Label iconNom = new Label("Titre : "+ob.getTitre()+"\ndate de création 21/10/2016");
                                grid.add(iconNom, 1, 1);
                                
                                JFXCheckBox check = new JFXCheckBox("Solved");
//                                if (check.isSelected())
//                                {
//                                    
//                                }
                               // check.setAlignment(Pos.BOTTOM_RIGHT);
                                grid.add(check,1,3);
                                
                                
                                TextArea text = new TextArea(ob.getDescription());
                                text.setEditable(false);
                                text.setWrapText(true);
                                text.setPrefHeight(50);
                                grid.add(text, 1, 0);
                                setGraphic(grid);
                            }
                        }
                };

                    //System.out.println("xzeee  ");
                    return cell;
                    
                }             
            });
            //liste.setItems(data);

            pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() 
            {
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
        pro = newValue;
        
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/views/ConsulterProblem.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Consulter Problème");
            stage.setScene(new Scene(root));  
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProblemeController.class.getName()).log(Level.SEVERE, null, ex);
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
        limitText.setText(""+l.size());
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
                //pagination.setCurrentPageIndex(Math.min(pagination.getCurrentPageIndex(),table.getItems().size()/limit.get()));
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }

    }
         
        
    
}
