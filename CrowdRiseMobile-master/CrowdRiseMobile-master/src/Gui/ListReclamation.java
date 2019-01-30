/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Entity.Reclamation;
import Handler.ReclamationHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Yosra
 */
public class ListReclamation  extends List implements Runnable, CommandListener{
    
    Reclamation [] reclamations;
     Command accepter=new Command("accepter", Command.SCREEN, 0);
    Command refuser=new Command("refuser", Command.SCREEN, 0);
    Command refresh =new Command("refresh", Command.SCREEN, 0);
    Command cmdMenu= new Command("Menu Pricipal", Command.SCREEN, 0);
    Command supp=new Command("supprimer", Command.SCREEN, 0);
    Command retour = new Command("Retour", Command.BACK, 0);
    Display disp;
     Thread th;
       public ListReclamation() {
        super("Liste de reclamations ",List.IMPLICIT);
        Midlet.currentPosition = "reclamation";
         th = new Thread(this);
         th.start();

        addCommand(refresh);
        addCommand(cmdMenu);
        addCommand(retour);
           addCommand(supp);
        setCommandListener(this); 
        
    }
       
    public void commandAction(Command c, Displayable d) {
    
            if(c==refresh)
            run();
          else if (c==retour)
            Midlet.menuPrincipal.show();
          else if (c==supp)
          {
               try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/deleteRec.php?id=" + reclamations[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Midlet.md.disp.setCurrent(this);
                Alert a = new Alert("Suppression effectuée", "La réclamation a été supprimé", null, AlertType.INFO);
                Midlet.md.disp.setCurrent(a);
                th=new Thread(this);
                th.start();
                con.close();
                data.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          }
    
    }
       
    public void run() {

     try {
             ReclamationHandler  r = new  ReclamationHandler ();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/SelectReclamation.php");
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, r);
            reclamations = new Reclamation[r.getVect().size()];
            r.getVect().copyInto(reclamations);
            deleteAll();
            if (reclamations.length > 0) {
                for (int i = 0; i < reclamations.length; i++) {
                    append("Id "+reclamations[i].getId()+ "\n Reclamation "+reclamations[i].getReclamation(),null);
                 
            }
        } 
            
            con.close();
            data.close();
    }   catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }}


}
