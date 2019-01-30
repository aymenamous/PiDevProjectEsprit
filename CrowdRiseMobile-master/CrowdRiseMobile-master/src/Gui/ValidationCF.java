/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Entity.Problem;
import Entity.ProjetCF;
import Handler.MembresActBannHandler;
import Handler.ProblemHandler;
import Handler.ProjetCFHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;

import java.util.Vector;
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
public class ValidationCF extends List implements Runnable, CommandListener {

    ProjetCF [] pCF;
    Command accepter=new Command("accepter", Command.SCREEN, 0);
    Command refuser=new Command("refuser", Command.SCREEN, 0);
    Command refresh =new Command("refresh", Command.SCREEN, 0);
    Command cmdMenu= new Command("Menu Pricipal", Command.SCREEN, 0);
    Command retour = new Command("Retour", Command.BACK, 0);

    Display disp;

    public ValidationCF() {
  super("Liste de projets CF", javax.microedition.lcdui.List.IMPLICIT);
   Midlet.currentPosition = "ProjetsCFattente";
   Thread th = new Thread(this);
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
       
        else if(c==accepter)
        {
             try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/activerProjetCF.php?id=" + pCF[getSelectedIndex()].getId());
                 System.out.println("pcf"+pCF[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Alert a = new Alert("Acceptation", "Projet accepté", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
                Midlet.md.disp.setCurrent(this);
                Midlet.md.disp.setCurrent(a);
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
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/refuserProjetCF.php?id=" + pCF[getSelectedIndex()].getId());
                 System.out.println("pcf"+pCF[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Alert a = new Alert("Projet refusé", "Projet refusé", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
               Midlet.md.disp.setCurrent(this);
                Midlet.md.disp.setCurrent(a);
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

    public void run() { 
     try {
            ProjetCFHandler pHandler = new ProjetCFHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/CrowdRise/admin/selectProjetCF.php");
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, pHandler);
            pCF= new ProjetCF[pHandler.getVect().size()];
                                System.out.println("ddd");

            pHandler.getVect().copyInto(pCF);
            deleteAll();
            if (pCF.length > 0) {
                for (int i = 0; i < pCF.length; i++) {
                    append("Id "+pCF[i].getId()+"   \n Projet :  "+pCF[i].getNom()+" \n "+pCF[i].getDate(), null);
                    System.out.println("ddd");
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
    
}
