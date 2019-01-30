/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import crowdrisemobile.Midlet;
import javax.microedition.lcdui.*;

/**
 *
 * @author Giliwawaa
 */
public class ProjetCFRecherche extends Form implements CommandListener{

    TextField shearchField = new TextField("Mot clé", null, 255, TextField.ANY);
    Command back = new Command("Retour", Command.BACK, 0);
    Command go = new Command("Go", Command.SCREEN, 0);
    public ProjetCFRecherche(String title) {
        super(title);
        this.append(shearchField);
        this.addCommand(back);
        this.addCommand(go);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c == go){
            Midlet.md.disp.setCurrent(new ListProjetCF(shearchField.getString()));
        }
    }
    
    
}
