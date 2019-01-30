/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entity.Membre;
import Handler.MembreHandler;
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
public class MembreLogin extends Form implements CommandListener, Runnable
{
    TextField email = new TextField("Email","", 255, TextField.EMAILADDR);
    TextField password = new TextField("Mot De Passe","", 255, TextField.PASSWORD);
    Command connecter = new Command("Se Connecter", Command.OK, 0);
    Command inscription = new Command("S'inscrire", Command.OK, 0);
    Command admin = new Command("Adminstrateur", Command.OK, 0);
    Command exit = new Command("Quitter", Command.EXIT, 0);
    HttpConnection ht;
    DataInputStream data;
    StringBuffer str ;
    int ch;
    Alert info;
    public static Membre[] membre;
    StringBuffer  sb;
    Thread th = new Thread(this);
    String mail;
    String pwd;
    Image img;
 
    
    

    public MembreLogin(String title) throws IOException 
    {
        super(title);
        this.img = Image.createImage("/Gui/membre.png");
        this.append(img);
        this.append(email);
        this.append(password);
        this.addCommand(connecter);
        this.addCommand(inscription);
        this.addCommand(admin);
        this.addCommand(exit);
        this.setCommandListener(this);
       
        
    }
    
    public void run ()
    {
        try {
            
            System.out.println("helloo there");
            MembreHandler membreHandler = new MembreHandler();
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            
            HttpConnection con = (HttpConnection) Connector.open("http://localhost/crowdrise/membre/select.php?email="+mail+"&password="+pwd);
            System.out.println(mail+"  *" + pwd);
            DataInputStream data = con.openDataInputStream();
            int ascii;
                    sb = new StringBuffer();

                    /*while ((ascii = data.read()) != -1) {
                    
                    sb.append((char) ascii);
                    
                    }*/
                    System.out.println("here sb = "+sb.toString());
                    if (sb.toString().equals("0 results")) 
                    {
                        info = new Alert("No User Found !", null, null, AlertType.ERROR);
                        System.out.println("3amalia fechla");
                        Midlet.md.disp.setCurrent(info);
                    }
                    else 
                    {
                        
                        parser.parse(data, membreHandler);
                        membre = new Membre[membreHandler.getMembrevect().size()];
                        System.out.println("HEEELLOO222");
                        membreHandler.getMembrevect().copyInto(membre);
                        System.out.println("HEEELLOO333");
                        System.out.println("dfhdf"+membre.toString());
                        if (membre.length > 0) 
                        {
                            deleteAll();
                            for (int i = 0; i < membre.length; i++) 
                            {
                                //append("Membre Email:"+membre[i].getEmail()+"\nMember Pass:"+membre[i].getMdp());
                            }
                        }


    }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     catch (SAXException ex) {
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
            
            if(membre != null && membre.length > 0)
            {
                for (int i = 0; i < membre.length; i++) 
                {
                    if((mail.equals(membre[i].getEmail())) && (pwd.equals(membre[i].getMdp())))
                    {
                        info = new Alert("Login Successful", null, null, AlertType.CONFIRMATION);
                        System.out.println("3amalia nej7a");
                        
                        try {
                           Midlet.membre = membre[i];
                            System.out.println(Midlet.membre.getId());
                           Midlet.md.disp.setCurrent(new Spinner()); // normalement ça passe vers le menu principale 
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                
                }
            }
                }
            
        

       if(c==admin)
         {
             info = new Alert("Interface d'authentification d'admin ", null, null, AlertType.INFO);
                try 
                { 
                    Midlet.md.disp.setCurrent(info,new AdminLogin("Inscription"));
                } catch (IOException ex) {
                    ex.printStackTrace();
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
        

    } 

 
}

  
