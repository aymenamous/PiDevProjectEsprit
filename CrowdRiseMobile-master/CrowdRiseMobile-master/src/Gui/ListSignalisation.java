/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Signalisation;
import Handler.SignalisationHandler;
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
public class ListSignalisation extends List implements Runnable,CommandListener{
    Signalisation [] s;
     Command accepter=new Command("accepter", Command.SCREEN, 0);
    Command refuser=new Command("refuser", Command.SCREEN, 0);
    Command refresh =new Command("refresh", Command.SCREEN, 0);
    Command cmdMenu= new Command("Menu Pricipal", Command.SCREEN, 0);
    Command retour = new Command("Retour", Command.BACK, 0);
    
     Thread th;
     Display disp;
     public ListSignalisation()
     {
         super("liste de signalisations ",List.IMPLICIT);
         Midlet.currentPosition = "ProjetsCFattente";
       th = new Thread(this);
       th.start();

        addCommand(refresh);
        addCommand(cmdMenu);
        addCommand(accepter);
        addCommand(refuser);
        addCommand(retour);
        setCommandListener(this); 
     }
     
      public void commandAction(Command c, Displayable d) {

      if(c==refresh)
            run();
       else if (c==retour)
                Midlet.menuPrincipal.show();
       else if(c==retour)
       {
            try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/deleteSig.php?id=" + s[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Midlet.md.disp.setCurrent(this);
                Alert a = new Alert("Suppression effectuée", "La signalisation a été supprimé", null, AlertType.INFO);
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
            SignalisationHandler Handler = new SignalisationHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/SelectSignal.php");
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, Handler);
            s= new Signalisation[Handler.getVect().size()];
                                System.out.println("ddd");

            Handler.getVect().copyInto(s);
            deleteAll();
            if (s.length > 0) {
                for (int i = 0; i < s.length; i++) {
                    append("Id "+s[i].getId()+"   \n Date t :  "+s[i].getDate(), null);
                    System.out.println("ddd");
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

   
}
