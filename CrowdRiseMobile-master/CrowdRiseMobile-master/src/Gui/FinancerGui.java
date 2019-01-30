/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.ProjetCF;
import crowdrisemobile.Midlet;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

/**
 *
 * @author Giliwawaa
 */
public class FinancerGui extends Form implements CommandListener{
    ProjetCF pcf;
    TextField amount = new TextField("Montant", null, 2500, TextField.NUMERIC);
    Command confirmer = new Command("confirmer", Command.SCREEN, 0);
    Command back = new Command("retour", Command.BACK, 0);
    String url = "";

    public FinancerGui(String title, ProjetCF pcf) {
        super(title);
        this.pcf = pcf;
        this.append(amount);
        this.addCommand(back);
        this.addCommand(confirmer);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c == back ){
            Midlet.md.disp.setCurrent(new ListProjetCF(1));
        }
        if(c == confirmer){
            url = "http://localhost/crowdriseJ2ME/financer.php?id_p="+pcf.getId()+"&id_m="+pcf.getMembre().getId()+"&amount="+amount.getString();
            try {
                HttpConnection con = (HttpConnection) Connector.open(url);
                Midlet.md.disp.setCurrent(new ListProjetCF(1));
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
