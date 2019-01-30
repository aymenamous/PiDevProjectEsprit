/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import gui.*;
import Dao.IdeeDAO;
import Dao.MembreDao;
import Dao.VoteDao;
import Entity.Idee;
import Entity.vote;
import com.jfoenix.controls.JFXProgressBar;
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
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.GroupBuilder;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
public class ListeIdeesController implements Initializable {

    @FXML
    private ListView liste;
    @FXML
    private Pane pane;
    @FXML
    private Button affMenu;
    @FXML
    private Pagination pagination;
    @FXML
    private Button ajouterIdee;
    @FXML
    private Button mesIdees;
    @FXML
    private TextField limitText;
    @FXML
    private ImageView imgProfile;
    @FXML
    private ImageView imgIdee;
    @FXML
    public StackPane stackRech;
    @FXML
    public Button testAdmin;
    @FXML
    public Label login;
    @FXML public AnchorPane pk;

    private boolean show = false;
    private Timeline timeline;
    List<Idee> liste2;
    private final ObservableList<Idee> data = FXCollections.observableArrayList();
    private IntegerProperty limit;
    public static Idee idee2;

    public Idee getIdee2() {
        return idee2;
    }
    
    private Rectangle2D boxBounds = new Rectangle2D(100, 100, 300, 180);
    private double ACTION_BOX_HGT = 30;
    private SimpleBooleanProperty isExpanded = new SimpleBooleanProperty();
    private VBox searchPane;
    private TextField searchField;
    private Rectangle clipRect;
    private Timeline timelineUp;
    private Timeline timelineDown;
    private StackPane downArrow = StackPaneBuilder.create().style("-fx-padding: 8px 5px 0px 5px;-fx-background-color: black;-fx-shape: \"M0 0 L1 0 L.5 1 Z\";").maxHeight(10).maxWidth(15).build();
    private StackPane upArrow = StackPaneBuilder.create().style("-fx-padding: 8px 5px 0px 5px;-fx-background-color: black;-fx-shape: \"M0 1 L1 1 L.5 0 Z\";").maxHeight(10).maxWidth(15).build();
    private Label searchLbl = LabelBuilder.create().text("Recherche").graphic(downArrow).contentDisplay(ContentDisplay.RIGHT).build();

    public void onBtnAffMenuClicked(ActionEvent event) {

        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        if (!show) {
            KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), pane.getWidth());
            KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            show = true;
            affMenu.setText("<<");
            affMenu.setLayoutX(affMenu.getLayoutX() - 35);
        } else {
            KeyValue kvUp2 = new KeyValue(pane.translateXProperty(), 0);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(500), kvUp2);
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            show = false;
            affMenu.setText(">>");
            affMenu.setLayoutX(affMenu.getLayoutX() + 35);

        }
    }
    
    public void onAjoutIdeeClicked(ActionEvent event) throws IOException {
        crowdrise.main.afficher(crowdrise.main.url_idee_add, "Ajouter Idee", true);
    }
    
    public void onMesIdeesClicked(ActionEvent event) throws IOException {
        crowdrise.main.afficher(crowdrise.main.url_liste_mes_idees, "Mes Idees", true);
        //crowdrise.main.initBase(pk, crowdrise.main.url_liste_mes_idees);
    }
    
    public void onMenuClicked(ActionEvent event){
        crowdrise.main.afficher(crowdrise.main.url_Base, "Acceuil", true);
    }

    public void chargeSearch(StackPane root) {
        //root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_RIGHT);
        root.setLayoutX(500);
        root.autosize();
    }

    private void setAnimation() {
        /* Initial position setting for Top Pane*/
        clipRect = new Rectangle();
        clipRect.setWidth(boxBounds.getWidth());
        clipRect.setHeight(ACTION_BOX_HGT);
        clipRect.translateYProperty().set(boxBounds.getHeight() - ACTION_BOX_HGT);
        searchPane.setClip(clipRect);
        searchPane.translateYProperty().set(-(boxBounds.getHeight() - ACTION_BOX_HGT));

        /* Animation for bouncing effect. */
        final Timeline timelineDown1 = new Timeline();
        timelineDown1.setCycleCount(2);
        timelineDown1.setAutoReverse(true);
        final KeyValue kv1 = new KeyValue(clipRect.heightProperty(), (boxBounds.getHeight() - 15));
        final KeyValue kv2 = new KeyValue(clipRect.translateYProperty(), 15);
        final KeyValue kv3 = new KeyValue(searchPane.translateYProperty(), -15);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1, kv2, kv3);
        timelineDown1.getKeyFrames().add(kf1);

        /* Event handler to call bouncing effect after the scroll down is finished. */
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                timelineDown1.play();
            }
        };

        timelineDown = new Timeline();
        timelineUp = new Timeline();

        /* Animation for scroll down. */
        timelineDown.setCycleCount(1);
        timelineDown.setAutoReverse(true);
        final KeyValue kvDwn1 = new KeyValue(clipRect.heightProperty(), boxBounds.getHeight());
        final KeyValue kvDwn2 = new KeyValue(clipRect.translateYProperty(), 0);
        final KeyValue kvDwn3 = new KeyValue(searchPane.translateYProperty(), 0);
        final KeyFrame kfDwn = new KeyFrame(Duration.millis(200), onFinished, kvDwn1, kvDwn2, kvDwn3);
        timelineDown.getKeyFrames().add(kfDwn);

        /* Animation for scroll up. */
        timelineUp.setCycleCount(1);
        timelineUp.setAutoReverse(true);
        final KeyValue kvUp1 = new KeyValue(clipRect.heightProperty(), ACTION_BOX_HGT);
        final KeyValue kvUp2 = new KeyValue(clipRect.translateYProperty(), boxBounds.getHeight() - ACTION_BOX_HGT);
        final KeyValue kvUp3 = new KeyValue(searchPane.translateYProperty(), -(boxBounds.getHeight() - ACTION_BOX_HGT));
        final KeyFrame kfUp = new KeyFrame(Duration.millis(200), kvUp1, kvUp2, kvUp3);
        timelineUp.getKeyFrames().add(kfUp);
    }

    private void configureSearch(StackPane root) {
        searchPane = new VBox();
        searchPane.setPrefSize(boxBounds.getWidth(), boxBounds.getHeight());
        searchPane.setAlignment(Pos.TOP_RIGHT);
        StackPane sp1 = new StackPane();
        sp1.setPadding(new Insets(10));
        sp1.setAlignment(Pos.TOP_LEFT);
        sp1.setStyle("-fx-background-color:#333333,#EAA956;-fx-background-insets:0,1.5;-fx-opacity:.92;-fx-background-radius:0px 0px 0px 5px;");
        sp1.setPrefSize(boxBounds.getWidth(), boxBounds.getHeight() - ACTION_BOX_HGT);

        Label searchTitle = LabelBuilder.create().text("Chercher Idée ").font(Font.font("Arial", 20)).build();
        searchField = new TextField();
        HBox hb = HBoxBuilder.create().children(LabelBuilder.create().text("Enter Titre Idée : ").font(Font.font("Arial", 14)).build(), searchField).maxHeight(24).translateY(45).build();
        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                String str = searchField.getText();
                liste.getItems().clear();
                if (str != null && str.length() > 0) {
                    for (Idee idee : data) {
                        if (idee.getNom().toLowerCase().contains(str.toLowerCase())) {
                            liste.getItems().add(idee);
                        }
                    }
                } else {
                    //liste.getItems().addAll(data);
                }
            }
        });
        Button searchBtn = new Button("Chercher");
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
                String str = searchField.getText();
                liste.getItems().clear();
                if (str != null && str.length() > 0) {
                    for (Idee idee : data) {
                        if (idee.getNom().toLowerCase().contains(str.toLowerCase())) {
                            liste.getItems().add(idee);
                        }
                    }
                } else {
                    liste.getItems().addAll(data);
                }
            }
        });
        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent paramT) {
                liste.getItems().clear();
                liste.getItems().addAll(data);
            }
        });
        HBox hb2 = HBoxBuilder.create().children(searchBtn, resetBtn).maxHeight(24).spacing(10).translateY(100).build();
        sp1.getChildren().addAll(searchTitle, hb, hb2);

        StackPane sp2 = new StackPane();
        sp2.setPrefSize(100, ACTION_BOX_HGT);
        sp2.getChildren().add(searchLbl);
        sp2.setStyle("-fx-cursor:hand;-fx-background-color:#EAA956;-fx-border-width:0px 1px 1px 1px;-fx-border-color:#333333;-fx-opacity:.92;-fx-border-radius:0px 0px 5px 5px;-fx-background-radius:0px 0px 5px 5px;");
        sp2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent paramT) {
                togglePaneVisibility();
            }
        });
        searchPane.getChildren().addAll(GroupBuilder.create().children(sp1).build(), GroupBuilder.create().children(sp2).build());
        root.getChildren().add(GroupBuilder.create().children(searchPane).build());
    }

    private void togglePaneVisibility() {
        if (isExpanded.get()) {
            isExpanded.set(false);
        } else {
            isExpanded.set(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       if(crowdrise.main.getMembre()!=null){
           try{
             if (crowdrise.main.getMembre().getPhoto().startsWith("file:")) {
               imgProfile.setImage(new Image((crowdrise.main.getMembre().getPhoto()), 154, 145, false, false));
            }  
           }catch(Exception e){
               imgProfile.setImage(new Image(getClass().getResourceAsStream("/ressources/profile.jpg"), 154, 145, false, true)); 
           }
        }
        login.setText(crowdrise.main.getMembre().getNom());
        limit = new SimpleIntegerProperty(10);
        data.clear();
        IdeeDAO ideedao = new IdeeDAO();
        liste2 = new ArrayList<>();
        liste2 = ideedao.displayAll();
        for (Idee idee : liste2) {
            
            data.add(idee);
        }

        liste.setCellFactory(new Callback<ListView<Idee>, ListCell<Idee>>() {

            @Override
            public ListCell<Idee> call(ListView<Idee> param) {
                ListCell<Idee> cell = new ListCell<Idee>() {

                    @Override
                    protected void updateItem(Idee ob, boolean b) {
                        super.updateItem(ob, b);
                        if (ob != null) {

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
                            iconLogin.setPrefWidth(100);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);
                            
                            Label iconNom = new Label("Titre : "+ob.getNom()+"\ndate de création "+ob.getDate());
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefWidth(550);
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

        chargeSearch(stackRech);
        configureSearch(stackRech);
        setAnimation();
        // liste.setItems(data);
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

        isExpanded.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> paramObservableValue, Boolean paramT1, Boolean paramT2) {
                if (paramT2) {
                    // To expand
                    timelineDown.play();
                    searchLbl.setGraphic(upArrow);
                    searchField.requestFocus();
                } else {
                    // To close
                    timelineUp.play();
                    searchLbl.setGraphic(downArrow);
                }
            }
        });

        liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Idee>() {

            @Override
            public void changed(ObservableValue<? extends Idee> observable, Idee oldValue, Idee newValue) {
                // Your action here
                
                idee2 = newValue;

                crowdrise.main.afficher(crowdrise.main.url_consulter_idee, "Consulter Idee", true);
                //crowdrise.main.initBase(pk, crowdrise.main.url_consulter_idee);
            }
        });
        //StackPane.setAlignment(pagination, Pos.CENTER);
        init();

    }

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
                
                //pagination.setCurrentPageIndex(Math.min(pagination.getCurrentPageIndex(),table.getItems().size()/limit.get()));
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }

    }
    
}
