/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controllers;

import Entity.Idee;
import Entity.Mail;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Yosra
 */
public class MailApiController implements Initializable {

    @FXML
    TextField Txtmailsender;
    @FXML
    PasswordField txtpwdsender;
    @FXML
    TextField txtMailAdress;
    @FXML
    TextField TxtMailSujet;
    @FXML
    TextArea TxtMailContenu;
    @FXML
    Button btnenvoi;
    @FXML
    URL url;
    @FXML
    Mail mail = new Mail();
    @FXML 
      private Button file ;
    @FXML 
      private Label label_file ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (ListeIdeesController.idee2!=null) {
        txtMailAdress.setText(ListeIdeesController.idee2.getMembre().getEmail());
        }
        
    }

     @FXML 
    void SelectFile(ActionEvent evt)
    {
         JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(null);
        if(option == JFileChooser.APPROVE_OPTION){
            try{
                url = chooser.getSelectedFile().toURL();
                System.out.println(url);
                label_file.setText(url.toString());
               
            }
            catch(MalformedURLException exception){
                System.out.println("Erreur URL ");
              
            }
        }
    }

    @FXML
    void Envoyer(ActionEvent event) {
        System.out.println("rttt");
        System.out.println(Txtmailsender.getText());
        mail.setMailAddressRecep(txtMailAdress.getText());
        mail.setPwd(txtpwdsender.getText());
        mail.setMailAddresseEnvoi(Txtmailsender.getText());
        mail.setMailSujet(TxtMailSujet.getText());
        String[] lignes = TxtMailContenu.getText().split("\\n");
        String msg = "";

        for (String s : lignes) {
            msg = msg + s;
        }
        mail.setMailContenu(msg);
        MailConstruction mc = new MailConstruction();
        mc.getMailProperties();
        String s = this.cleanUrl(url);
        mc.getMailMessage(s, mail);
        mc.SendMessage();

          Alert msg2 = new Alert(Alert.AlertType.INFORMATION);
            msg2.setTitle("Envoi");
            msg2.setContentText("Succès");
            msg2.setHeaderText(null);
            msg2.showAndWait();
    }

    public String cleanUrl(URL url) {

        String s = url.toString();
        System.out.println("Before Clean");
        System.out.println(s);
        String delims = "/";
        String[] tokens = s.split(delims);
        System.out.println("After Clean");
        String mailPart = "";
        for (int i = 1; i < tokens.length; i++) {
            mailPart += tokens[i] + "\\\\";
        }
        System.out.println(mailPart);
        return mailPart;
    }

}
