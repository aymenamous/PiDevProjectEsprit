/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Problem;
import Handler.ProblemHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Aymen
 */
public class MesProblemes extends List implements Runnable, CommandListener {

    private Problem[] problemes;
    int id_membre;
    Command retour;
    Command supprimer;
    Command ouvrir;
    Command oui;
    Command non;
    Command rafraichir;
    Command recherche;
    Alert msg ;
    String script;
    Thread th;

    public MesProblemes(String filtre) {
        super("Mes problèmes", List.IMPLICIT);
        id_membre = Midlet.membre.getId();
        if (filtre==null)
        {
            script="http://localhost/crowdrise/probleme/select_probleme_membre.php?id=" + id_membre;
        }
        else
        {
            script="http://localhost/crowdrise/probleme/select_probleme_membre_name.php?id=" + id_membre+"&nom="+filtre;
        }
        
        initMsg();
        initCommandes();
        th = new Thread(this);
        th.start();
    }
    
    public void initCommandes()
    {
        recherche=new Command("Recherche", Command.SCREEN, 0);
        retour = new Command("Retour", Command.BACK, 0);
        supprimer = new Command("Supprimer", Command.SCREEN, 1);
        ouvrir = new Command("Ouvrir", Command.SCREEN, 2);
        rafraichir=new Command("Rafraichir", Command.SCREEN, 0);
        oui = new Command("Oui", Command.SCREEN, 0);
        non = new Command("Non", Command.BACK, 0);
        addCommand(retour);
        addCommand(ouvrir);
        addCommand(supprimer);
        addCommand(rafraichir);
        addCommand(recherche);
        setCommandListener(this);
        msg.addCommand(oui);
        msg.addCommand(non);
        msg.setCommandListener(this);
    }
    
    public void initMsg()
    {
        msg= new Alert("Confirmation", "Voules vous vraiment supprimer ce problème?", null, AlertType.WARNING);
        msg.setTimeout(Alert.FOREVER);
    }

    public void run() {
        try {
            deleteAll();
            ProblemHandler problemHandler = new ProblemHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open(script);
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, problemHandler);
            problemes = new Problem[problemHandler.getVect().size()];
            problemHandler.getVect().copyInto(problemes);
            
            con.close();
            data.close();
            if (problemes.length > 0) {
                for (int i = 0; i < problemes.length; i++) {
                    append("Sujet:" + problemes[i].getTitre() + "\nDate:" + problemes[i].getDate(), null);
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

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND || c == ouvrir) {
            Midlet.currentPosition = "MesProblemes";
            TextBoxProbleme p = new TextBoxProbleme(problemes[getSelectedIndex()]);
            Midlet.md.disp.setCurrent(p);
        } else if (c == supprimer) {

            Midlet.md.disp.setCurrent(msg);
        } else if (c == retour) {
            Midlet.menuPrincipal.show();

        } else if (c == oui) {
            try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/probleme/delete_probleme.php?id=" + problemes[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Alert a = new Alert("Suppression effectuée", "Le problème a été supprimé avec succès", null, AlertType.INFO);
                a.setTimeout(Alert.FOREVER);
                Midlet.md.disp.setCurrent(this);
                Midlet.md.disp.setCurrent(a);
                th=new Thread(this);
                th.start();
                con.close();
                data.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if (c==non)
        {
            Midlet.md.disp.setCurrent(this);
        }else if (c==rafraichir) {
            script="http://localhost/crowdrise/probleme/select_probleme_membre.php?id=" + id_membre;
            th=new Thread(this);
            th.start();
        }else if (c==recherche)
        {
            Midlet.currentPosition = "MesProblemes";
            RechercheProbleme r=new RechercheProbleme();
            Midlet.md.disp.setCurrent(r);
        }
    }

}
