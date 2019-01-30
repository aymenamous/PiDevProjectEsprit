/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.ProjetCFDao;
import Entity.Membre;
import Entity.ProjetCF;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import crowdrise.main;
import de.jensd.fx.fontawesome.AwesomeIcon;
import de.jensd.fx.fontawesome.Icon;
import gui.controllers.ListProjetsCFController;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Amine Triki
 */
public class ModifProjetCFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXTextField titre;
    
    @FXML
    private JFXTextArea Desc;
    
    @FXML
    private JFXDatePicker DateDebut;
    
    @FXML
    private JFXDatePicker DateFin;
    
    @FXML
    private JFXTextField Budget;
    
    @FXML
    private JFXButton bouton;
    
    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    public void onboutonClicked(ActionEvent event) throws ParseException {
        ProjetCF pCF = ListProjetsCFController.ProjetCF2;
        ProjetCF cf= new ProjetCF();
        
        LocalDate ld= DateDebut.getValue();        
        Date datedebut = Date.valueOf(ld);
        
        ld= DateFin.getValue();
        Date datefin= Date.valueOf(ld);
        
        java.util.Date daa= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");        
        java.sql.Date sDate1 = new java.sql.Date(daa.getTime());
        
        cf.setNom(titre.getText());
        cf.setDebut(datedebut);
        cf.setFin(datefin);
        cf.setBudget_final(Double.parseDouble(Budget.getText()));
        cf.setBudget_actuel(0);
        cf.setDate(sDate1);
        cf.setMembre((Membre)crowdrise.main.getMembre());
        cf.setDescription(Desc.getText());
        cf.setId(pCF.getId());
        ProjetCFDao cfdao= new ProjetCFDao();
        cfdao.updateAllById(cf);
        main.initBase(anchorpane, main.url_liste_projet_cf);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ImageView img= new ImageView("/ressources/extend.png");
        img.setFitHeight(20);
        img.setFitWidth(20);
        bouton.setGraphic(img);
        
        ControleSaisieTitre();
        ControleSaisieDec();
        ControleSaisieDateDebut();
        ControleBudget();
        
        DateDebut.setDefaultColor(Color.valueOf("#3f51b5"));
        DateFin.setDefaultColor(Color.valueOf("#3f51b5"));
        
        ProjetCF pCF = ListProjetsCFController.ProjetCF2;
        titre.setText(pCF.getNom());
        Desc.setText(pCF.getDescription());
        Date date = pCF.getDebut();
        LocalDate localD = date.toLocalDate();
        DateDebut.setValue(localD);
        date = pCF.getFin();
        localD = date.toLocalDate();
        DateFin.setValue(localD);
        Budget.setText(""+pCF.getBudget_final());
        
    }    
    
    private void ControleSaisieTitre() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();                
                if (!verifOnlyWords(textField.getText()) ) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Ce champ ne peut pas etre vide");
        v.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));
        titre.getValidators().add(v);
        titre.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                titre.validate();
            }
        });
        
    }
    
    private void ControleSaisieDec() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
	validator.setMessage("Ce champ ne peut etre vide");
        validator.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));		
        Desc.getValidators().add(validator);
        Desc.focusedProperty().addListener((o,oldVal,newVal)->{
                if(!newVal) Desc.validate();
        });        
    }
    
    private void ControleSaisieDateDebut() {  
    }
    
    
    public boolean verifOnlyWords(String chaine) {
        return !chaine.matches("");
    }

    private void ControleBudget() {
        ValidatorBase v = new ValidatorBase() {

            @Override
            protected void eval() {
                if (srcControl.get() instanceof TextInputControl) {
                    evalTextInputField();
                }
            }

            protected void evalTextInputField() {
                TextInputControl textField = (TextInputControl) srcControl.get();                
                if (!verifOnlyWords(textField.getText()) ) {
                    hasErrors.set(true);
                } else {
                    hasErrors.set(false);
                }
            }
        };
        v.setMessage("Ce champ ne peut pas etre vide");
        v.setIcon(new Icon(AwesomeIcon.WARNING,"1em",";","error"));
        Budget.getValidators().add(v);
        Budget.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                Budget.validate();
            }
        });
    }

    
}
