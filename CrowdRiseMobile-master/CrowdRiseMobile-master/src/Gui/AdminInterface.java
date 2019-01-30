/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author H4DH
 */
public class AdminInterface extends Form implements CommandListener
{
    TextField competenceName1 = new TextField("Competence :","", 255, TextField.ANY);
    TextField competenceName2 = new TextField("Competence :","", 255, TextField.ANY);
    TextField competenceName3 = new TextField("Competence :","", 255, TextField.ANY);
    Command ajout = new Command("Ajouter", Command.OK, 0);
    Command back = new Command("Retour", Command.BACK, 0);
    //Command exit = new Command("EXIT", Command.EXIT, 0);
    HttpConnection ht;
    DataInputStream data,data2;
    StringBuffer str ;
    int ch;
    Alert info;
    String [] competencesAdded= new String [3];
    
    public AdminInterface(String title) 
    {
        super(title);
        this.append(competenceName1);
        this.append(competenceName2);
        this.append(competenceName3);
        this.addCommand(ajout);
        this.addCommand(back);
        this.setCommandListener(this);
        
    }

    public void commandAction(Command c, Displayable d) 
    {
        if(c==ajout)
        {
                competencesAdded[0]=competenceName1.getString();
                competencesAdded[1]=competenceName2.getString();
                competencesAdded[2]=competenceName3.getString();
            
            try 
                {
                    for(int i=0;i<competencesAdded.length;i++)
                    {
                    ht=(HttpConnection)Connector.open("http://localhost/crowdrise/admin/insertcompetence.php?nom="+ competencesAdded[i]);
                    data=ht.openDataInputStream();   
                    int size;
                    str =new  StringBuffer();
                    while( (size=data.read()) != -1 )
                    {
                        str.append((char)size);
                    }
                    if(str.toString().equals("successfully added"))
                    {
                        Midlet.md.disp.setCurrent(new Spinner2());
                    } 
                }
                }       
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
        }
        if(c==back)
        {
          // Midlet.md.disp.setCurrent( );  // Menu principale de l'admin
        }
        
        if(c==back)
        {
            com.sun.lwuit.Display.init(Midlet.md);
            MenuPricipal lwuitForm= new MenuPricipal("Menu");
            lwuitForm.show();
        }
        
    }

}
