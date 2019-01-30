/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Membre;
import Handler.MembresActBannHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class MembresBannir_Activer extends List implements CommandListener, Runnable {

    Command cmdRefresh = new Command("refresh", Command.SCREEN, 0);
    Membre[] people;
    StringBuffer sb;
    Command cmdMenu= new Command("Retour", Command.BACK, 0);
    Display disp;
    public MembresBannir_Activer(String title, int listType, Display d) {
        super(title, listType);
        disp=d;
        Thread th = new Thread(this);
        th.start();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdRefresh) {
            //disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        if( c== cmdMenu){
            com.sun.lwuit.Display.init(Midlet.md);
            MenuPricipal lwuitForm= new MenuPricipal("Menu");
            lwuitForm.show();
        }
          if (c == List.SELECT_COMMAND) {
              System.out.println(this.getSelectedIndex());
              formMembre frm= new formMembre(this.getString(this.getSelectedIndex()),people[this.getSelectedIndex()]);
              disp.setCurrent(frm);
        }
    }

    public void run() {
        try {
            setCommandListener(this);
            addCommand(cmdRefresh);
            addCommand(cmdMenu);
            MembresActBannHandler peopleHandler = new MembresActBannHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/selectMembres.php");//people.xml est un exemple
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, peopleHandler);
            // display the result
            people = peopleHandler.getPeople();
             
            if (people.length > 0) {
                this.deleteAll();
                for (int i = 0; i < people.length; i++) {
                    append(people[i].getNom().toString() , null);
                }
            }
            hc.close();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
    
        private String showNumbers(int i) {
        sb = new StringBuffer();
        String res = "";
        //numbers = people[i].getPhoneNumbers();
//        if (numbers.length > 0) {
//            for (int j = 0; j < numbers.length; j++) {
//                sb.append("* ");
//                sb.append(numbers[j].getNumber());
//                sb.append("\n");
//            }
//        }
        res = sb.toString();
        return res;
    }

}
