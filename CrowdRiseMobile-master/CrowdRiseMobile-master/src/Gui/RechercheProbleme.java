/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import crowdrisemobile.Midlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author Aymen
 */
public class RechercheProbleme extends Form implements CommandListener {

    TextField recherche;
    Command valider;
    Command annuler;

    public RechercheProbleme() {
        super("Recherche problème");
        recherche = new TextField("Recherche", "", 50, TextField.ANY);
        append(recherche);
        initCommandes();
    }

    public void initCommandes() {
        valider = new Command("Valider", Command.SCREEN, 0);
        annuler = new Command("Annuler", Command.BACK, 0);
        addCommand(valider);
        addCommand(annuler);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == annuler) {
            System.out.println(Midlet.currentPosition);
            if (Midlet.currentPosition.equals("MesProblemes")) {
                System.out.println("je suis la");
                MesProblemes l = new MesProblemes(null);
                Midlet.md.disp.setCurrent(l);
            } else if (Midlet.currentPosition.equals("ListeProblemes")) {
                ListProblemes l = new ListProblemes(null);
                Midlet.md.disp.setCurrent(l);
            }

        } else if (c == valider) {
            if (Midlet.currentPosition.equals("MesProblemes")) {
                MesProblemes l = new MesProblemes(recherche.getString());
                Midlet.md.disp.setCurrent(l);
            } else if (Midlet.currentPosition.equals("ListeProblemes")) {
                ListProblemes l = new ListProblemes(recherche.getString());
                Midlet.md.disp.setCurrent(l);
            }
        }
    }

}
