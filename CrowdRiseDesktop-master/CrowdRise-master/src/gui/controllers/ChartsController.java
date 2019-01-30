/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Dao.ProjetCFDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import crowdrise.main;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Amine Triki
 */
public class ChartsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    LineChart<String, Number> LineChart;

    @FXML
    JFXComboBox cmbYear = new JFXComboBox();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        ProjetCFDao pjt = new ProjetCFDao();
        series.getData().add(new XYChart.Data<String, Number>("Jan", pjt.displayByYearMonth(2016, 1).size()));
        series.getData().add(new XYChart.Data<String, Number>("Fab", pjt.displayByYearMonth(2016, 2).size()));
        series.getData().add(new XYChart.Data<String, Number>("Mar", pjt.displayByYearMonth(2016, 3).size()));
        series.getData().add(new XYChart.Data<String, Number>("Apr", pjt.displayByYearMonth(2016, 4).size()));
        series.getData().add(new XYChart.Data<String, Number>("Mai", pjt.displayByYearMonth(2016, 5).size()));
        series.getData().add(new XYChart.Data<String, Number>("Juin", pjt.displayByYearMonth(2016, 6).size()));
        series.getData().add(new XYChart.Data<String, Number>("Jul", pjt.displayByYearMonth(2016, 7).size()));
        series.getData().add(new XYChart.Data<String, Number>("Aug", pjt.displayByYearMonth(2016, 8).size()));
        series.getData().add(new XYChart.Data<String, Number>("Sept", pjt.displayByYearMonth(2016, 9).size()));
        series.getData().add(new XYChart.Data<String, Number>("Oct", pjt.displayByYearMonth(2016, 10).size()));
        series.getData().add(new XYChart.Data<String, Number>("Nvbr", pjt.displayByYearMonth(2016, 11).size()));
        series.getData().add(new XYChart.Data<String, Number>("Dec", pjt.displayByYearMonth(2016, 12).size()));
        series.setName("2016");
        LineChart.getData().add(series);
        for(final XYChart.Data<String,Number> data: series.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    Tooltip.install(data.getNode(), new Tooltip("Month: "+data.getXValue()+"\nNombre: "+String.valueOf(data.getYValue())));
                }
                
            });
        }

        cmbYear.getItems().addAll("2016", "2015", "2014", "2013", "2012");
        cmbYear.setPromptText("Year");
        cmbYear.setEditable(true);
        cmbYear.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LineChart.getData().clear();
                XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
                System.out.println(Integer.parseInt(newValue));
                series.getData().add(new XYChart.Data<String, Number>("Jan", pjt.displayByYearMonth(Integer.parseInt(newValue), 1).size()));
                series.getData().add(new XYChart.Data<String, Number>("Fab", pjt.displayByYearMonth(Integer.parseInt(newValue), 2).size()));
                series.getData().add(new XYChart.Data<String, Number>("Mar", pjt.displayByYearMonth(Integer.parseInt(newValue), 3).size()));
                series.getData().add(new XYChart.Data<String, Number>("Apr", pjt.displayByYearMonth(Integer.parseInt(newValue), 4).size()));
                series.getData().add(new XYChart.Data<String, Number>("Mai", pjt.displayByYearMonth(Integer.parseInt(newValue), 5).size()));
                series.getData().add(new XYChart.Data<String, Number>("Juin", pjt.displayByYearMonth(Integer.parseInt(newValue), 6).size()));
                series.getData().add(new XYChart.Data<String, Number>("Jul", pjt.displayByYearMonth(Integer.parseInt(newValue), 7).size()));
                series.getData().add(new XYChart.Data<String, Number>("Aug", pjt.displayByYearMonth(Integer.parseInt(newValue), 8).size()));
                series.getData().add(new XYChart.Data<String, Number>("Sept", pjt.displayByYearMonth(Integer.parseInt(newValue), 9).size()));
                series.getData().add(new XYChart.Data<String, Number>("Oct", pjt.displayByYearMonth(Integer.parseInt(newValue), 10).size()));
                series.getData().add(new XYChart.Data<String, Number>("Nvbr", pjt.displayByYearMonth(Integer.parseInt(newValue), 11).size()));
                series.getData().add(new XYChart.Data<String, Number>("Dec", pjt.displayByYearMonth(Integer.parseInt(newValue), 12).size()));
                series.setName(newValue);
                LineChart.getData().add(series);

            }
        });
    }

}
