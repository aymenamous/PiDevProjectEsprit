/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Problem;
import crowdrisemobile.Midlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author Aymen
 */
public class TextBoxProbleme extends TextBox implements CommandListener{
    
    Command retour;
    Command commentaire;
    Problem problem;

    public TextBoxProbleme(Problem p) {
        super(p.getTitre(),"Par: "+p.getMembre().getNom()+"\nDate: "+p.getDate()+"\n\n"+p.getDescription(),1000 , TextField.UNEDITABLE);
        System.out.println(Midlet.currentPosition);
        retour=new Command("retour", Command.BACK, 0);
        commentaire=new Command("Commentaires", Command.SCREEN, 0);
        problem=p;
        addCommand(retour);
        addCommand(commentaire);
        setCommandListener(this);
        
    }

    public void commandAction(Command c, Displayable d) {
        if (c==retour)
        {
            if (Midlet.currentPosition.equals("MesProblemes"))
            {
                MesProblemes p=new MesProblemes(null);
                Midlet.md.disp.setCurrent(p);
            }
            else if (Midlet.currentPosition.equals("ListeProblemes"))
            {
                ListProblemes p=new ListProblemes(null);
                Midlet.md.disp.setCurrent(p);
            }
        }
        else if (c==commentaire)
        {
            ListCommentaires lc=new ListCommentaires( null, problem);
            Midlet.md.disp.setCurrent(lc);
        }
        
    }
    
}
