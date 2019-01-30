/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Commentaire;
import Entity.Idee;
import Entity.Problem;
import Entity.Projet;
import Handler.CommentaireHandler;
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
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Aymen
 */
public class ListCommentaires extends List implements Runnable, CommandListener {

    private Commentaire[] comentaires;
    private String script;
    Command retour;
    Projet projet;
    Problem problem;
    Command supprimer;
    Command rafraichir;
    Alert msg;
    Command oui;
    Command non;
    Thread th;

    public ListCommentaires(Projet projet, Problem problem) {
        super("Commentaires", List.IMPLICIT);
        supprimer = new Command("Supprimer", Command.SCREEN, 1);
        retour = new Command("Retour", Command.BACK, 0);
        rafraichir = new Command("Rafraichir", Command.SCREEN, 0);
        oui = new Command("Oui", Command.SCREEN, 0);
        non = new Command("Non", Command.BACK, 0);
        msg = new Alert("Confirmation", "Voules vous vraiment supprimer ce commentaire?", null, AlertType.WARNING);
        msg.setTimeout(Alert.FOREVER);
        msg.addCommand(oui);
        msg.addCommand(non);
        msg.setCommandListener(this);
        addCommand(rafraichir);
        addCommand(retour);
        addCommand(supprimer);
        setCommandListener(this);
        if (projet != null) {
            script = "http://localhost/crowdrise/commentaire/select_commentaire_projet.php?id=" + projet.getId();
            this.projet = projet;
        } else if (problem != null) {
            this.problem = problem;
            script = "http://localhost/crowdrise/commentaire/select_commentaire_probleme.php?id=" + problem.getId();
        }
        th = new Thread(this);
        th.start();
    }

    public void run() {
        try {
            deleteAll();
            CommentaireHandler commentaireHandler = new CommentaireHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open(script);
            DataInputStream data = con.openDataInputStream();
            parser.parse(data, commentaireHandler);
            comentaires = new Commentaire[commentaireHandler.getVect().size()];
            commentaireHandler.getVect().copyInto(comentaires);
            
            con.close();
            data.close();
            if (comentaires.length > 0) {
                for (int i = 0; i < comentaires.length; i++) {
                    if (Midlet.membre.getId() == comentaires[i].getMembre().getId()) {
                        append("Par vous :\n" + comentaires[i].getText_commentaire(), null);
                    } else {
                        append("Par " + comentaires[i].getMembre().getNom() + ":\n" + comentaires[i].getText_commentaire(), null);
                    }

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
        if (c == retour) {
            if (Midlet.currentPosition.equals("MesProblemes") || Midlet.currentPosition.equals("ListeProblemes")) {
                TextBoxProbleme p = new TextBoxProbleme(problem);
                Midlet.md.disp.setCurrent(p);
            } else if (Midlet.currentPosition.equals("listIdées") || Midlet.currentPosition.equals("listMesIdées") || Midlet.currentPosition.equals("listIdéesAdmin")) {
                ConsulterIdeeForm cif=new ConsulterIdeeForm(Midlet.md.disp,(Idee) projet);
                Midlet.md.disp.setCurrent(cif);
            } else if (Midlet.currentPosition.endsWith("/Idee")) {

            }
        } else if (c == supprimer) {
            if (Midlet.admin.getId() != 0 || Midlet.membre.getId() == comentaires[getSelectedIndex()].getMembre().getId()) {
            Midlet.md.disp.setCurrent(msg);
            } else {
            Alert a = new Alert("Suppression impossible", "Vous ne pouvez pas supprimer ce commentaire", null, AlertType.ERROR);
            Midlet.md.disp.setCurrent(a);
            }
        } else if (c == rafraichir) {
            th=new Thread(this);
            th.start();
        }
        else if (c==oui)
        {
            try {
                HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/commentaire/delete_commentaire.php?id=" + comentaires[getSelectedIndex()].getId());
                DataInputStream data = con.openDataInputStream();
                Midlet.md.disp.setCurrent(this);
                Alert a = new Alert("Suppression effectuée", "Le commentaire a été supprimé", null, AlertType.INFO);
                Midlet.md.disp.setCurrent(a);
                th=new Thread(this);
                th.start();
                con.close();
                data.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (c==non)
        {
            Midlet.md.disp.setCurrent(this);
        }

    }

}
