/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import gui.*;
import Dao.IdeeDAO;
import Entity.Idee;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author userpc
 */
public class IdeeAddController implements Initializable{
    
    @FXML TextField txtAjout;
    @FXML TextField txtDescription;
    @FXML DatePicker dateDebut;
    @FXML DatePicker dateFin;
    @FXML TextField txtRenum;
    @FXML CheckBox checkbox;
    @FXML Button btnAjout;
    @FXML Hyperlink link;
    @FXML ImageView imageIdee;
    @FXML Button selectImage;
    @FXML AnchorPane pk;
    
    public static Idee idee;
    String path ="cross.png";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        javafx.scene.image.Image img = new javafx.scene.image.Image("/ressources/"+path, 154, 145, true, true);
        imageIdee.setImage(img); 
    }
    
    public void onLinkClicked(ActionEvent event) throws IOException {

        try {
            String filePath = "new.pdf";
            Document d = new Document(PageSize.A4);
            PdfWriter.getInstance(d, new FileOutputStream(filePath));
            d.open();
            d.add(new Paragraph("********** Titre du projet ********** "));
            d.add(new Paragraph(txtAjout.getText()));
            d.add(new Paragraph("Bonjour "+crowdrise.main.getMembre().getNom()+"\n"));
            d.add(new Paragraph("Pour assurer le bon fonctionnement de cette application et faire "
                    + "en sorte que chacun puisse partager ces idées en toute securité "
                    + ", vous devez lire et accepter nos termes et conditions, que vous"
                    + " trouverez dans cette section."));
            d.add(new Paragraph("Votre Idée sera mise en attente jusqu'à l'accepter ou la refuser !"));
//            d.add(new Paragraph("LE TITRE D'IDEE : "+txtAjout.getText()));
            /*Image img = Image.getInstance("ressources/image_pdf.jpg");
            img.scaleAbsolute(100f,100f);*/
            d.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String pdfFile = "new.pdf";
        if (pdfFile.toString().endsWith(".pdf")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + pdfFile);
        } else {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(pdfFile));
        }
    }
    
    public void onBtnAjoutClicked(ActionEvent event){
        if (controle()) {
         java.util.Date date = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
         
         idee = new Idee(Double.parseDouble(txtRenum.getText()),path ,txtAjout.getText(),Date.valueOf(dateDebut.getValue()), Date.valueOf(dateFin.getValue()),txtDescription.getText(),sqlDate,crowdrise.main.getMembre());
         IdeeDAO idao = new IdeeDAO();
        /* LocalDate localdt = dateDebut.getValue();
         idee.setDate(Date.valueOf(localdt));*/
        int i;
        i = idao.addAndgetLastInsertedIdee(idee);
        idee.setId(i);
        
        Alert conf=new Alert(Alert.AlertType.INFORMATION);
        conf.setTitle("Information");
        conf.setHeaderText("Idée ajoutée avec succées.\nVeuillez selectionner maintenant les taches..");
        conf.showAndWait();

        crowdrise.main.referer = crowdrise.main.url_idee_add;
        crowdrise.main.afficher(crowdrise.main.url_solution, "Ajouter des Taches", true);
        }
            
     }
    
    public void onBtnAnnulerClicked(ActionEvent event){
        crowdrise.main.afficher(crowdrise.main.url_liste_idees, "Liste des Idees", true);
    }
    
    public void onBtnSelectClicked(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setTitle("Ouvrir");
        File f = fc.showOpenDialog(crowdrise.main.stage);
        if (f != null) {
            path = f.getName();
            javafx.scene.image.Image img = new javafx.scene.image.Image("/ressources/"+path, 154, 145, true, true);
            imageIdee.setImage(img);    
        }else{
            path = "cross.png";
        }
    }
    
    public boolean controle(){
            
            Alert msg=new Alert(Alert.AlertType.ERROR);
            msg.setHeaderText(null);
            msg.setTitle("Impossible de créer une Idée");
            String chaine="";
            
            if (txtAjout.getText().equals("")){
            chaine=chaine+"Titre est un Champs Obligatoire";
            txtAjout.setStyle("-fx-border-color: red;");
            txtAjout.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
            }
               
            
            if (txtDescription.getText().equals("")) {
            chaine=chaine+"Description est un Champs Obligatoire";
            txtDescription.setStyle("-fx-border-color: red;");
            txtDescription.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
             } 
            
            if (dateDebut.getValue()==null) {
            chaine=chaine+"Date Debut est un Champs Obligatoire";
            dateDebut.setStyle("-fx-border-color: red;");
            dateDebut.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
            }else dateDebut.setStyle("-fx-border-color: green;");
            
            if (dateFin.getValue()==null) {
            chaine=chaine+"Date Fin est un Champs Obligatoire";
            dateFin.setStyle("-fx-border-color: red;");
            dateFin.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
             }else dateFin.setStyle("-fx-border-color: green;");
            
            if (txtRenum.getText().equals("")) {
            chaine=chaine+"Remuneration est un Champs Obligatoire";
            txtRenum.setStyle("-fx-border-color: red;");
            txtRenum.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
            }
            
            //pas encore
            if (!txtRenum.getText().matches("[0-9]+[.]{0,1}[0-9]*")) {
            chaine=chaine+"ERREUR selectionner une somme d'argent Double required";
            txtRenum.setStyle("-fx-border-color: red;");
            txtRenum.setFocusTraversable(true);
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
            }
            
            if (!checkbox.isSelected()) {
            chaine=chaine+"Vous devez accepter les conditions";
            msg.setContentText(chaine);
            msg.showAndWait();
            return false;
           }
            
            return true;
        }
    
    public void ontitreFieldChanged(KeyEvent event)
    {
        if (txtAjout.getText().equals(""))
        {
            txtAjout.setStyle("-fx-border-color: red;");
        }
        else
        {
            txtAjout.setStyle("-fx-border-color: green;");
        }
    }
    
    public void onDescriptionFieldChanged(KeyEvent event)
    {
        if (txtDescription.getText().equals(""))
        {
            txtDescription.setStyle("-fx-border-color: red;");
        }
        else
        {
            txtDescription.setStyle("-fx-border-color: green;");
        }
    }
    
    public void onRemunFieldChanged(KeyEvent event)
    {
        if (txtRenum.getText().equals(""))
        {
            txtRenum.setStyle("-fx-border-color: red;");
        }
        else
        {
            txtRenum.setStyle("-fx-border-color: green;");
        }
    }
    
    
    
}
