/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entity.Admin;
import Handler.AdminHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author H4DH
 */
public class AdminLogin extends Form implements CommandListener, Runnable
{
    TextField email = new TextField("Email","", 255, TextField.EMAILADDR);
    TextField password = new TextField("Mot De Passe","", 255, TextField.PASSWORD);
    Command connecter = new Command("Se Connecter", Command.OK, 0);
    Command inscription = new Command("S'inscrire", Command.OK, 0);
    Command membre = new Command("Membre", Command.OK, 0);
    Command exit = new Command("Quitter", Command.EXIT, 0);
    HttpConnection ht;
    DataInputStream data;
    StringBuffer str ;
    int ch;
    Alert info;
    private Admin[] admin;
    Thread th = new Thread(this);
    String mail;
    String pwd;
    StringBuffer sb;
    Image img;
    
    
    

    public AdminLogin(String title) throws IOException 
    {
        super(title);
        this.img = Image.createImage("/Gui/admin.jpg");
        this.append(img);
        this.append(email);
        this.append(password);
        this.addCommand(membre);
        this.addCommand(connecter);
        this.addCommand(exit);
        this.setCommandListener(this);
 
        
    }
    
    public void run ()
    {
        try 
        {          
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/admin/select.php?email="+mail+"&password="+pwd);
            data = con.openDataInputStream();
             int ascii;
                    sb = new StringBuffer();

                    /*while ((ascii = data.read()) != -1) {
                    
                    sb.append((char) ascii);
                    
                    }*/

                    if (sb.toString().equals("0 results")) 
                    {
                        info = new Alert("No Admin Found !", null, null, AlertType.ERROR);
                        System.out.println("3amalia fechla");
                        Midlet.md.disp.setCurrent(info);
                    }
                    else 
                    {
                        System.out.println("i am here");
                        AdminHandler adminHandler = new AdminHandler();
                        parser.parse(data, adminHandler);
                        admin = new Admin[adminHandler.getAdminvect().size()];
                        adminHandler.getAdminvect().copyInto(admin);
            
                if (admin.length > 0) 
                {
                deleteAll();
                    for (int i = 0; i < admin.length; i++) 
                    {
                        append("Admin Email:"+admin[i].getEmail()+"\nMember Pass:"+admin[i].getMdp());
                    }
                }
            con.close();
            data.close();
        }
    }   catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
    
    public void commandAction(Command c, Displayable dd)
    {
        if (c==connecter)
        { 
            mail = email.getString();
            pwd = password.getString();
             run();
            System.out.println("hhhhhhhhh");
            
             if(admin.length > 0)
            {
                for (int i = 0; i < admin.length; i++) 
                {
                    if((mail.equals(admin[i].getEmail())) && (pwd.equals(admin[i].getMdp())))
                    {
                        Midlet.admin=admin[i];
                        info = new Alert("Login Successful", null, null, AlertType.CONFIRMATION);
                        //System.out.println("3amalia nej7a admin");
                        Midlet.menuPrincipal= new MenuPricipal("Menu");
                        Midlet.menuPrincipal.show();
                         
                    }
                }          
        }
        }
        if(c==exit)
        {
            Midlet.md.notifyDestroyed();
        }
         if(c==inscription)
         {
             info = new Alert("Chargement de formulaire d'inscription!", null, null, AlertType.INFO);
             Midlet.md.disp.setCurrent(info,new Inscription("Inscription")); 
         }
        if(c==membre)
         {
             info = new Alert("Se Connecter : Membre", null, null, AlertType.INFO);
                try { 
                    Midlet.md.disp.setCurrent(info,new MembreLogin("Login"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
         }
        

    } 

 
}

  
