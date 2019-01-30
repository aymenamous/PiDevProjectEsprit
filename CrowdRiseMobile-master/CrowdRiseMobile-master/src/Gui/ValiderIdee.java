/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Idee;
import Handler.IdeeHandler;
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
public class ValiderIdee extends List implements Runnable,CommandListener{

    Idee[] idees;
    Command accepter=new Command("accepter", Command.SCREEN, 0);
    Command refuser=new Command("refuser", Command.SCREEN, 0);
    Command refresh =new Command("refresh", Command.SCREEN, 0);
    Command cmdMenu= new Command("Menu Pricipal", Command.SCREEN, 0);
    Command retour = new Command("Retour", Command.BACK, 0);
     Display disp;
     
     public ValiderIdee()
     {
         super("Liste d'idees", List.IMPLICIT);
         Midlet.currentPosition = "idee";
   Thread th = new Thread(this);
    th.start();

        addCommand(refresh);
        addCommand(cmdMenu);
        addCommand(accepter);
        addCommand(refuser);
        addCommand(retour);
        setCommandListener(this); 
     }

    public void run() {
           try {
               IdeeHandler pHandler = new IdeeHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/SelectIdee.php");
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, pHandler);
            idees= new Idee[pHandler.getVect().size()];
                                System.out.println("ddd");

            pHandler.getVect().copyInto(idees);
            deleteAll();
            if (idees.length > 0) {
                for (int i = 0; i < idees.length; i++) {
                    append("Id "+idees[i].getId()+"   \n Projet :  "+idees[i].getNom()+" \n "+idees[i].getDate(), null);
                    
                }
            }
            con.close();
            data.close();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    
    }

    public void commandAction(Command c, Displayable d) {

     if(c==refresh)
            run();
       
        else if(c==accepter)
        {
             try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/activerProjetCF.php?id=" + idees[getSelectedIndex()].getId());
                 System.out.println("pcf"+idees[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Alert a = new Alert("Acceptation", "Idee acceptée", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
             //   Midlet.md.disp.setCurrent(this);
            //    Midlet.md.disp.setCurrent(a);
                run();
                con.close();
                data.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        else if(c==refuser)
        {
             try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/refuserProjetCF.php?id=" + idees[getSelectedIndex()].getId());
                 System.out.println("pcf"+idees[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Alert a = new Alert("Idee refusée", "Idee refusée", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
             //   Midlet.md.disp.setCurrent(this);
            //    Midlet.md.disp.setCurrent(a);
                run();
                con.close();
                data.close();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        else if (c==cmdMenu)
        {
              com.sun.lwuit.Display.init(Midlet.md);
            MenuPricipal lwuitForm= new MenuPricipal("Menu");
            lwuitForm.show();
        }
     
          else if (c==retour)
                Midlet.menuPrincipal.show();
    
    
    }
    
}
