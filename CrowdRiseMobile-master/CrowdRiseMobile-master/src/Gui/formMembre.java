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
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import Entity.Membre;
import crowdrisemobile.Midlet;

/**
 *
 * @author Amine Triki
 */
public class formMembre extends Form implements CommandListener{
    
    TextField tfNom;
    TextField tfPrenom;
    TextField tfMail;
    TextField tfAdresse;
    TextField tfTelephone;
    boolean statut;
    Command cmdBannir = new Command("Bannir", Command.SCREEN, 0);
    Command cmdActiver= new Command("Activer", Command.SCREEN, 0);
    Command cmdBack= new Command("Back", Command.BACK, 0);
    HttpConnection hc;
    DataInputStream dis;
    StringBuffer sb;
    int statutInt;
    String url = "http://localhost/crowdrise/admin/updateMembre.php?";
    Membre m;
    public formMembre(String title, Membre m) {
        super(title);
        statut= m.isStatut();
        this.m=m;
        
        tfNom= new TextField("Nom", m.getNom(), 255, TextField.UNEDITABLE);
        tfPrenom= new TextField("Prenom", m.getPrenom(), 255, TextField.UNEDITABLE);
        tfMail= new TextField("Email", m.getEmail(), 255, TextField.UNEDITABLE);
        tfAdresse= new TextField("Adresse", m.getAdresse(), 255, TextField.UNEDITABLE);
        tfTelephone= new TextField("Telephone", m.getTelephone(), 8, TextField.NUMERIC | TextField.UNEDITABLE);
        
        this.append(tfNom);
        this.append(tfPrenom);
        this.append(tfMail);
        this.append(tfAdresse);
        this.append(tfTelephone);
        
        if(statut){
            this.addCommand(cmdBannir);
            statutInt=1;
        }else {
            this.addCommand(cmdActiver);
            statutInt=0;
        }
        addCommand(cmdBack);
        this.setCommandListener(this);
        
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c==cmdBannir){
            statutInt=0;
            try {
                String nom, prenom;
                nom = tfNom.getString();
                prenom = tfPrenom.getString();

                hc = (HttpConnection) Connector.
                        open(url + "statut=" + statutInt + "&id="+ m.getId() );
                dis = hc.openDataInputStream();

                int ascii;
                sb = new StringBuffer();

                while ((ascii = dis.read()) != -1) {

                    sb.append((char) ascii);

                }

                if (sb.toString().equals("successfully updated")) {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);
                    //Midlet.md.disp.setCurrent(a);
                    javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(a);
                } else {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                    a.setTimeout(3000);
                    javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(a);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            removeCommand(cmdBannir);
            addCommand(cmdActiver);
        }
        if(c==cmdActiver){
            statutInt=1;
            try {
                String nom, prenom;
                nom = tfNom.getString();
                prenom = tfPrenom.getString();

                hc = (HttpConnection) Connector.
                        open(url + "statut=" + statutInt + "&id="+ m.getId());
                dis = hc.openDataInputStream();

                int ascii;
                sb = new StringBuffer();

                while ((ascii = dis.read()) != -1) {

                    sb.append((char) ascii);

                }

                if (sb.toString().equals("successfully updated")) {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.CONFIRMATION);
                    a.setTimeout(3000);
                    //Midlet.md.disp.setCurrent(a);
                    javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(a);
                } else {
                    Alert a = new Alert("Information", sb.toString(), null, AlertType.ERROR);
                    a.setTimeout(3000);
                    //Midlet.md.disp.setCurrent(a);
                    javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(a);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            removeCommand(cmdActiver);
            addCommand(cmdBannir);
        }
        if (c==cmdBack){
            //Midlet.md.disp.setCurrent(new MembresBannir_Activer("Liste des Membres", List.IMPLICIT, Midlet.md.disp));
            javax.microedition.lcdui.Display.getDisplay(Midlet.md).setCurrent(new MembresBannir_Activer("Liste des Membres", List.IMPLICIT, javax.microedition.lcdui.Display.getDisplay(Midlet.md)));
        }
    }

}
