/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.MembreDao;
import Dao.ProjetCFDao;
import Entity.Membre;
import Entity.ProjetCF;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.mail.FetchProfile.Item;

/**
 * FXML Controller class
 *
 * @author Amine Triki
 */
public class ListProjetsCFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    JFXToggleButton ToggButt;

    @FXML
    AnchorPane lstProjetsAnch;

    @FXML
    private JFXListView liste;

    @FXML
    private ToggleButton ToogButt;

    @FXML
    private Pagination pagination;

    List<ProjetCF> liste2;

    private final ObservableList<ProjetCF> data = FXCollections.observableArrayList();

    private IntegerProperty limit;

    public static boolean toogleValue;

    public static ProjetCF ProjetCF2;

    public static ProjetCF getProjetCF2() {
        return ProjetCF2;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        limit = new SimpleIntegerProperty(10);
        data.clear();
        ProjetCFDao Projetdao = new ProjetCFDao();
        liste2 = new ArrayList<>();
        liste2 = Projetdao.displayAll();
        for (ProjetCF projetCF : liste2) {
            System.out.println(projetCF);
            data.add(projetCF);
        }

        liste.setCellFactory(new Callback<JFXListView<ProjetCF>, ListCell<ProjetCF>>() {

            @Override
            public ListCell<ProjetCF> call(JFXListView<ProjetCF> param) {
                ListCell<ProjetCF> cell = new ListCell<ProjetCF>() {

                    @Override
                    protected void updateItem(ProjetCF ob, boolean b) {
                        super.updateItem(ob, b);
                        if (ob != null) {
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(0, 10, 0, 10));
                            System.out.println("here image = " + ob.toString());
                            Image img;
                            try {

                                img = new Image(getClass().getResourceAsStream("/ressources/" + ob.getImage()), 45, 45, false, true);

                            } catch (Exception e) {
                                System.out.println("Impossible de charger l'image");
                                img = new Image(getClass().getResourceAsStream("/ressources/cross.png"), 45, 45, false, true);
                            }

                            ImageView imgv = new ImageView(img);
                            grid.add(imgv, 0, 0);

                            Label iconLogin = new Label("Triki Amine");
                            iconLogin.setMaxWidth(100);
                            iconLogin.setWrapText(true);
                            grid.add(iconLogin, 0, 1);

                            Label iconNom = new Label("Titre : " + ob.getNom() + "\ndate de création " + ob.getDate());
                            grid.add(iconNom, 1, 1);

                            TextArea text = new TextArea(ob.getDescription());
                            text.setEditable(false);
                            text.setWrapText(true);
                            text.setPrefHeight(50);
                            grid.add(text, 1, 0);

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

        liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProjetCF>() {

            @Override
            public void changed(ObservableValue<? extends ProjetCF> observable, ProjetCF oldValue, ProjetCF newValue) {
                // Your action here
                System.out.println("Selected item: " + newValue);
                ProjetCF2 = newValue;
                main.initBase(lstProjetsAnch, "/gui/views/ConsulterProjetCF.fxml");
            }
        });

        init();

        toogleValue = false;
        ToggButt.setText("Tous les Projets Existant");
        ToggButt.setOnAction((event) -> {
            System.out.println(toogleValue);
            if (toogleValue) {
                ToggButt.setText("Tous les Projets Existant");

                toogleValue = false;
                limit = new SimpleIntegerProperty(10);
                data.clear();

                liste2 = new ArrayList<>();
                liste2 = Projetdao.displayAll();

                for (ProjetCF projetCF : liste2) {
            System.out.println(projetCF);
            data.add(projetCF);
        }

                liste.setCellFactory(new Callback<JFXListView<ProjetCF>, ListCell<ProjetCF>>() {

                    @Override
                    public ListCell<ProjetCF> call(JFXListView<ProjetCF> param) {
                        ListCell<ProjetCF> cell = new ListCell<ProjetCF>() {

                            @Override
                            protected void updateItem(ProjetCF ob, boolean b) {
                                super.updateItem(ob, b);
                                if (ob != null) {
                                    GridPane grid = new GridPane();
                                    grid.setHgap(10);
                                    grid.setVgap(4);
                                    grid.setPadding(new Insets(0, 10, 0, 10));

                                    Image img;
                                    try {

                                        img = new Image(getClass().getResourceAsStream("/ressources/" + ob.getImage()), 45, 45, false, true);

                                    } catch (Exception e) {
                                        System.out.println("Impossible de charger l'image");
                                        img = new Image(getClass().getResourceAsStream("/ressources/cross.png"), 45, 45, false, true);
                                    }
                                    ImageView imgv = new ImageView(img);
                                    grid.add(imgv, 0, 0);

                                    Label iconLogin = new Label("Triki Amine");
                                    iconLogin.setMaxWidth(100);
                                    iconLogin.setWrapText(true);
                                    grid.add(iconLogin, 0, 1);

                                    Label iconNom = new Label("Titre : " + ob.getNom() + "\ndate de création 21/10/2016");
                                    grid.add(iconNom, 1, 1);

                                    TextArea text = new TextArea(ob.getDescription());
                                    text.setEditable(false);
                                    text.setWrapText(true);
                                    text.setPrefHeight(50);
                                    grid.add(text, 1, 0);

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

                liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProjetCF>() {

                    @Override
                    public void changed(ObservableValue<? extends ProjetCF> observable, ProjetCF oldValue, ProjetCF newValue) {
                        // Your action here
                        System.out.println("Selected item: " + newValue);
                        ProjetCF2 = newValue;
                        main.initBase(lstProjetsAnch, "/gui/views/ConsulterProjetCF.fxml");
                    }
                });
                init();
            } else {
                toogleValue = true;
                ToggButt.setText("My List");
                limit = new SimpleIntegerProperty(10);
                data.clear();

                liste2 = new ArrayList<>();

                liste2 = Projetdao.displayByIdMembre(main.getMembre().getId());
                for (ProjetCF projetCF : liste2) {
            System.out.println(projetCF);
            data.add(projetCF);
        }

                liste.setCellFactory(new Callback<JFXListView<ProjetCF>, ListCell<ProjetCF>>() {

                    @Override
                    public ListCell<ProjetCF> call(JFXListView<ProjetCF> param) {
                        ListCell<ProjetCF> cell = new ListCell<ProjetCF>() {

                            @Override
                            protected void updateItem(ProjetCF ob, boolean b) {
                                super.updateItem(ob, b);
                                if (ob != null) {
                                    GridPane grid = new GridPane();
                                    grid.setHgap(10);
                                    grid.setVgap(4);
                                    grid.setPadding(new Insets(0, 10, 0, 10));

                                    Image img;
                                    try {

                                        img = new Image(getClass().getResourceAsStream("/ressources/" + ob.getImage()), 45, 45, false, true);

                                    } catch (Exception e) {
                                        System.out.println("Impossible de charger l'image");
                                        img = new Image(getClass().getResourceAsStream("/ressources/cross.png"), 45, 45, false, true);
                                    }
                                    ImageView imgv = new ImageView(img);
                                    grid.add(imgv, 0, 0);

                                    Label iconLogin = new Label("Triki Amine");
                                    iconLogin.setMaxWidth(100);
                                    iconLogin.setWrapText(true);
                                    grid.add(iconLogin, 0, 1);

                                    Label iconNom = new Label("Titre : " + ob.getNom() + "\ndate de création 21/10/2016");
                                    grid.add(iconNom, 1, 1);

                                    TextArea text = new TextArea(ob.getDescription());
                                    text.setEditable(false);
                                    text.setWrapText(true);
                                    text.setPrefHeight(50);
                                    grid.add(text, 1, 0);

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

                liste.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProjetCF>() {

                    @Override
                    public void changed(ObservableValue<? extends ProjetCF> observable, ProjetCF oldValue, ProjetCF newValue) {
                        // Your action here
                        System.out.println("Selected item: " + newValue);
                        ProjetCF2 = newValue;
                        main.initBase(lstProjetsAnch, "/gui/views/ConsulterProjetCF.fxml");
                    }
                });
                init();
            }
        });

    }

    public void init() {
        pagination.setPageCount((int) (Math.ceil(data.size() * 1.0 / limit.get())));
        pagination.setCurrentPageIndex(0);
        changeListeView(0, limit.get());
    }

    public void changeListeView(int index, int limit) {
        int newIndex = index * limit;

        List<ProjetCF> ls = data.subList(Math.min(newIndex, data.size()), Math.min(data.size(), newIndex + limit));
        liste.getItems().clear();
        liste.setItems(null);
        ObservableList<ProjetCF> l = FXCollections.observableArrayList();
        liste.setItems(l);
        for (ProjetCF t : ls) {
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
                //pagination.setCurrentPageIndex(Math.min(pagination.getCurrentPageIndex(),table.getItems().size()/limit.get()));
            } catch (NumberFormatException nfe) {
                System.err.println("NFE error");
            }
        }

    }

}
