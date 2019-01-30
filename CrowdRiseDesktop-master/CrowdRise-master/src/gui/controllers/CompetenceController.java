/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Dao.ListeCompetencesDao;
import Entity.Competence;
import Entity.Membre;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Aymen
 */
public class CompetenceController implements Initializable {

    @FXML Button btn_ajouter;
    @FXML Button btn_retirer;
    ListeCompetencesDao lcompdao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lcompdao=new ListeCompetencesDao();
    }    
    
    public void onBtnRetirerClicked(ActionEvent event)
    {
        List<String> liste=lcompdao.displayAll();
        ChoiceDialog<String> choices=new ChoiceDialog<>(liste.get(0),liste);
        choices.setTitle("Choix de la compétence");
        choices.setHeaderText(null);
        choices.setContentText("choisissez la compétence à retirer");
        Optional<String> result=choices.showAndWait();
        if (result.isPresent())
        {
            lcompdao.delete(result.get());
        }
        
    }
    
    public void onBtnAjouterClicked(ActionEvent event)
    {
        TextInputDialog dialog=new TextInputDialog();
        dialog.setTitle("Saisie de la compétence");
        dialog.setHeaderText(null);
        dialog.setContentText("Saisissez la compétence que vous voulez ajouter");
        Optional<String> result=dialog.showAndWait();
        if (result.isPresent())
        {
            if (lcompdao.display(result.get())=="")
            {
                if (!result.get().isEmpty())
                {
                    lcompdao.add(result.get());
                }
                else{
                    Alert msg=new Alert(Alert.AlertType.ERROR);
                    msg.setHeaderText(null);;
                    msg.setTitle("Ajout impossible");
                    msg.setContentText("Le champ de texte est vide");
                    msg.showAndWait();
                }
            }
            else
            {
                Alert msg=new Alert(Alert.AlertType.ERROR);
                msg.setHeaderText(null);;
                msg.setTitle("Ajout impossible");
                msg.setContentText("La compétence existe déjà");
                msg.showAndWait();
            }
        }
    }
    
}
