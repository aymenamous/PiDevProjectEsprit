/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import Entity.Membre;
import crowdrisemobile.Midlet;

/**
 *
 * @author Triki Amine
 */
public class Profil extends Form implements CommandListener {

    Membre m = Midlet.membre;

    TextField tfNom = new TextField("Nom", m.getNom(), 10, TextField.ANY);
    TextField tfPrenom = new TextField("Prenom", m.getPrenom(), 10, TextField.ANY);
    TextField tfAdresse = new TextField("Adresse", m.getAdresse(), 255, TextField.ANY);
    TextField tfEmail = new TextField("Email", m.getEmail(), 255, TextField.ANY);
    TextField tfMdp = new TextField("Mot de passe", m.getMdp(), 255, TextField.ANY);
    TextField tfTelephone = new TextField("Telephone", m.getTelephone(), 255, TextField.ANY);
    TextField tfConfirmMdp = new TextField("Confirmer Mot de passe", m.getMdp(), 255, TextField.ANY);
    TextField tfCR= new TextField("CR",""+m.getCr(), 255, TextField.ANY);
    TextField tfNombrSolved= new TextField("Nombre Solved",""+m.getNbr_solved(), 255, TextField.ANY);
    Command cmdEdit = new Command("Edit", Command.SCREEN, 0);
    Command cmdNext = new Command("Update", Command.OK, 0);
    Command cmdReturn= new Command("Retour", Command.BACK, 0);
    Command cmdRetour= new Command("Retour", Command.BACK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;

    String url = "http://localhost/crowdrise/membre/update.php?";

    public Profil() {

        super("Update");
        tfNom.setConstraints(TextField.UNEDITABLE);
        tfPrenom.setConstraints(TextField.UNEDITABLE);
        tfAdresse.setConstraints(TextField.UNEDITABLE);
        tfEmail.setConstraints(TextField.UNEDITABLE);
        tfMdp.setConstraints(TextField.UNEDITABLE | TextField.PASSWORD);
        tfTelephone.setConstraints(TextField.UNEDITABLE);
        tfCR.setConstraints(TextField.UNEDITABLE);
        tfNombrSolved.setConstraints(TextField.UNEDITABLE);
        append(tfNom);
        append(tfPrenom);
        append(tfCR);
        append(tfNombrSolved);
        append(tfAdresse);
        append(tfTelephone);
        append(tfEmail);
        append(tfMdp);
        

        addCommand(cmdEdit);
        addCommand(cmdRetour);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c==cmdRetour){
            Midlet.menuPrincipal.show();
        }
        if (c == cmdEdit) {
            tfNom.setConstraints(TextField.ANY);
            tfPrenom.setConstraints(TextField.ANY);
            tfAdresse.setConstraints(TextField.ANY);
            tfEmail.setConstraints(TextField.ANY);
            tfMdp.setConstraints(TextField.PASSWORD);
            tfTelephone.setConstraints(TextField.NUMERIC);
            tfConfirmMdp.setConstraints(TextField.PASSWORD);
            this.append(tfConfirmMdp);
            addCommand(cmdNext);            
            removeCommand(cmdEdit);

        }
        if (c == cmdNext) {
            try {
                if (tfMdp.getString().equals(tfConfirmMdp.getString())) {

                    String nom, prenom, adresse, email, mdp, telephone;
                    nom = tfNom.getString();
                    prenom = tfPrenom.getString();
                    adresse = tfAdresse.getString();
                    email = tfEmail.getString();
                    mdp = tfMdp.getString();
                    telephone = tfTelephone.getString();

                    hc = (HttpConnection) Connector.
                            open(url + "nom=" + nom + "&prenom=" + prenom + "&adresse=" + adresse + "&email=" + email
                                    + "&mdp=" + mdp + "&telephone=" + telephone + "&id=" + m.getId());

                    dis = hc.openDataInputStream();

                    int ascii;
                    sb = new StringBuffer();

                    while ((ascii = dis.read()) != -1) {

                        sb.append((char) ascii);

                    }

                    if (sb.toString().equals("successfully updated")) {
                        Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                        a.setTimeout(3000);
                        Midlet.md.disp.setCurrent(a);
                    } else {
                        Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                        a.setTimeout(3000);
                        Midlet.md.disp.setCurrent(a);
                    }

                    tfNom.setConstraints(TextField.UNEDITABLE);
                    tfPrenom.setConstraints(TextField.UNEDITABLE);
                    tfAdresse.setConstraints(TextField.UNEDITABLE);
                    tfEmail.setConstraints(TextField.UNEDITABLE);
                    tfMdp.setConstraints(TextField.UNEDITABLE | TextField.PASSWORD);
                    tfTelephone.setConstraints(TextField.UNEDITABLE);
                    tfConfirmMdp.setConstraints(TextField.UNEDITABLE | TextField.PASSWORD);
                    delete(7);
                    removeCommand(cmdNext);
                    addCommand(cmdEdit);
                    hc.close();
                }else{
                    Alert a = new Alert("Information", "Verifier votre Mot de passe", null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);
                    Midlet.md.disp.setCurrent(a);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
