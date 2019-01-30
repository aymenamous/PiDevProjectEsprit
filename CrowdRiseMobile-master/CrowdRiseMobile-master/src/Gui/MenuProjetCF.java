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
public class MenuProjetCF extends List implements CommandListener{
    
    Command retour = new Command("Retour", Command.BACK, 0);
    public MenuProjetCF(String title, int listType) {
        super(title, listType);
        if(Midlet.admin.getId() == 0){
            append("Liste des projets",null);
            append("Mes projets",null);
            append("Rechercher Projet", null);
        }else{
            append("Liste des projets",null);
            append("Rechercher Projet", null);
        }
        this.addCommand(retour);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c == List.SELECT_COMMAND){
            if(this.getSelectedIndex() == 2){
                Midlet.md.disp.setCurrent(new ProjetCFRecherche("Rechercher Projet"));
            }else
                Midlet.md.disp.setCurrent(new ListProjetCF(this.getSelectedIndex()));
        }
        if(c == retour){
            Midlet.menuPrincipal.show();
        }
    }
    
}
