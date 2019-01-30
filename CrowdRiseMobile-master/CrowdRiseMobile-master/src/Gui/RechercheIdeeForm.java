/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Entity.Idee;
import Handler.IdeesHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author userpc
 */
class RechercheIdeeForm extends Form implements CommandListener{
    Display disp;
    Command cmdBack = new Command("Retour", Command.SCREEN, 0);
    Command cmdOK = new Command("chercher", Command.SCREEN, 0);
    TextField tf = new TextField("titre", null , 30, TextField.ANY);
    Idee[] ideeRech;
    public static String str;
    public RechercheIdeeForm(Display d) {
        super("Recherche Idée");
        append(tf);
        addCommand(cmdBack);
        addCommand(cmdOK);
        setCommandListener(this);
        disp = d;
    }

    public void commandAction(Command c, Displayable d) {
        if (c==cmdBack) {
            if (Midlet.currentPosition.equals("listIdées")) {
            disp.setCurrent(new ListIdeesCanvas(disp));
        }else if (Midlet.currentPosition.equals("listMesIdées")){
            disp.setCurrent(new ListMesIdeesCanvas(disp));
             }else{
            disp.setCurrent(new ListIdeeAdminCanvas(disp));
        }
        }
        
        if (c==cmdOK) {
            str=tf.getString();
            if (Midlet.currentPosition.equals("listIdées")) {
                ListIdeeRechCanvas.from = "listIdées";
            }else if (Midlet.currentPosition.equals("listIdéesAdmin")){
                ListIdeeRechCanvas.from = "listIdéesAdmin";
            }else
                ListIdeeRechCanvas.from = "listMesIdées";
            
        if (str != null && str.length() > 0) { 
         disp.setCurrent(new ListIdeeRechCanvas(disp));
        }else{
            Alert alert = new Alert(null);
            alert.setTimeout(5000);
            alert.setType(AlertType.ERROR);
            alert.setString(" Inserer le titre à chercher !");
            alert.setTitle("Erreur");
            disp.setCurrent(alert); 
           }
        }
    }

}
