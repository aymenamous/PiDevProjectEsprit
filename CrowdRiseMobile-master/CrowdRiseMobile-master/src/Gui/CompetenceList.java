/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Competence;
import Handler.CompetenceHandler;
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
 * @author H4DH
 */
public class CompetenceList extends List implements CommandListener
{
    DataInputStream data2;
    String [] competencesArray;
    private Competence[] competence;
    Vector selectedCompetence = new Vector();
    Command Terminer = new Command("Terminer", Command.OK, 0);
    String prenom;
    Command exit = new Command("Quitter", Command.EXIT, 0);
    Command back = new Command("Retour", Command.BACK, 0);
    HttpConnection ht;
    DataInputStream data;
    StringBuffer str ;
    int ch;
    Alert info;
    Display d;
  
    public CompetenceList(String title, int listType,String prenom) 
    {
        super(title, listType);
        this.prenom=prenom;
        this.addCommand(exit);
        this.addCommand(Terminer);
        this.addCommand(back);
        this.setCommandListener(this);
        run();
        for(int i=0;i<competencesArray.length;i++)
        {
            this.append(competencesArray[i], null);
        }
    }
  
public void run ()
    {
        try 
        {          
            SAXParser parser2 = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con2 = (HttpConnection) Connector.open("http://localhost/crowdrise/membre/competence.php");
            data2 = con2.openDataInputStream();
            CompetenceHandler competenceHandler = new CompetenceHandler();
            parser2.parse(data2, competenceHandler);
            competence = new Competence[competenceHandler.getCompetencevect().size()];
            competenceHandler.getCompetencevect().copyInto(competence);
                if (competence.length > 0) 
                {
                    competencesArray = new String [competence.length];
                  
                    for (int i = 0; i < competence.length; i++) 
                    {
                        //append("Nom : "+competence[i].getnom_Competence());
                        competencesArray[i]=competence[i].getnom_Competence();
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
    public void commandAction(Command c, Displayable dd) {
       if(c==Terminer)
       {
            for (int i=0; i<this.size(); i++) 
            {
            if (this.isSelected(i))              
                selectedCompetence.addElement(this.getString(i)); //vector has what the memeber selected   
            }
            try 
                {
                    for(int i=0;i<selectedCompetence.size();i++)
                    {
                    ht=(HttpConnection)Connector.open("http://localhost/crowdrise/membre/insertcompetence.php?nom="+selectedCompetence.elementAt(i)+"&id_membre="+Midlet.membre.getId());
                    data=ht.openDataInputStream();   
                    int size;
                    str =new  StringBuffer();
                    while( (size=data.read()) != -1 )
                    {
                        str.append((char)size);
                    }
                    if(str.toString().equals("successfully added"))
                    {
            com.sun.lwuit.Display.init(Midlet.md);
            MenuPricipal lwuitForm= new MenuPricipal("Menu");
            lwuitForm.show();
                        
                    } 
                }
                }       
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }  
       }
        if(c==exit)
        {
            Midlet.md.notifyDestroyed();
        }
        
        if(c==back)
        {
            com.sun.lwuit.Display.init(Midlet.md);
            MenuPricipal lwuitForm= new MenuPricipal("Menu");
            lwuitForm.show();
        }
    }
}
