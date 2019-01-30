/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gui;

import Entity.Competence;
import Entity.Membre;
import Handler.CompetenceHandler;
import crowdrisemobile.Midlet;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author H4DH
 */
public class Inscription extends Form implements CommandListener
{
    TextField     nom = new TextField("Nom:","", 255, TextField.ANY);
    TextField prenom = new TextField("Preom:","", 255, TextField.ANY);
    TextField adresse = new TextField("Adresse:","", 255, TextField.ANY);
    TextField email = new TextField("Email:","", 255, TextField.EMAILADDR);
    TextField password = new TextField("Mot De Passe:","", 255, TextField.PASSWORD);
    TextField confirmerpassword = new TextField("Confirmer:","", 255, TextField.PASSWORD);
    TextField tel = new TextField("Num° Tel:","", 255, TextField.NUMERIC);
    Command inscription = new Command("Suivant", Command.OK, 0);
    Command connecter = new Command("Se Connecter", Command.OK, 0);
    Command retour = new Command("Retour", Command.OK, 0);
    Command exit = new Command("Quitter", Command.EXIT, 0);
    HttpConnection ht;
    DataInputStream data,data2;
    StringBuffer str ;
    int ch;
    Alert info;
    private Membre[] membre;
    private Competence[] competence;
    String nomtxt,prenomtxt,mail,pwd,passconfirmtxt,teltxt,adressetxt;
    Image img;
    String url="http://localhost/crowdrise/membre/insert.php?";  
    String mailErro;
    String passError;
    ChoiceGroup competencesCG;
    String [] competencesArray;
    Vector selectedCompetence = new Vector();
    

    public Inscription(String title) 
    {
        super(title);
    
        this.append(nom);
        this.append(prenom);
        this.append(adresse);
        this.append(email);
        this.append(password);
        this.append(confirmerpassword);
        this.append(tel);
        this.addCommand(inscription);
        //this.addCommand(connecter);
        this.addCommand(retour);
        this.addCommand(exit);
        this.setCommandListener(this);    
    }
    
       public Inscription(String title,String nom1,String prenom1,String add,String tel1) 
    {
        super(title);
        this.append(nom);
        this.append(prenom);
        this.append(adresse);
        this.append(email);
        this.append(password);
        this.append(confirmerpassword);
        this.append(tel);
        this.addCommand(inscription);
        this.addCommand(retour);
        this.addCommand(exit);
        this.setCommandListener(this);
        nom.setString(nom1);
        prenom.setString(prenom1);
        tel.setString(tel1);
        adresse.setString(add);
    }
       
    public Inscription(String title,String nom1,String prenom1,String add,String tel1,String email1) 
    {
        super(title);
        this.append(nom);
        this.append(prenom);
        this.append(adresse);
        this.append(email);
        this.append(password);
        this.append(confirmerpassword);
        this.append(tel);
        this.addCommand(inscription);
        this.addCommand(retour);
        this.addCommand(exit);
        this.setCommandListener(this);
        nom.setString(nom1);
        prenom.setString(prenom1);
        tel.setString(tel1);
        email.setString(email1);
        adresse.setString(add);
    }
     
    
  /*  public static boolean checkEmail(String email) 
    {
        boolean resultat = true;
        try 
        {
           InternetAddress emailAdd = new InternetAddress(email);
           emailAdd.validate();
        } 
        catch (AddressException ex) 
        {
           resultat = false;
        }
        return resultat;
    }*/
    
    public static boolean checkPassword(String password,String confirmpwd) 
    {
        return password.equals(confirmpwd) && password.length()>=5;
    }

    public void commandAction(Command c, Displayable d) 
    {
        if(c==inscription)
        {
            nomtxt=nom.getString();
            prenomtxt=prenom.getString();
            teltxt=tel.getString();
            adressetxt=adresse.getString();
            mail=email.getString();
           
            if (checkPassword(password.getString(), confirmerpassword.getString()))
            {
                pwd=password.getString();
                passconfirmtxt=confirmerpassword.getString();
                try 
                {
                    ht=(HttpConnection)Connector.
                    open(url+"nom="+nomtxt+"&prenom="+prenomtxt+"&adresse="+adressetxt+"&email="+mail+"&pass="+pwd+"&tel="+teltxt);
                     System.out.println("heeeree");
                    data=ht.openDataInputStream();   
                    int size;
                    str =new  StringBuffer();
                    while( (size=data.read()) != -1 )
                    {
                        str.append((char)size);
                    }
                    if(str.toString().equals("successfully added"))
                    {
                        info= new Alert("Passage au compétence", "", null, AlertType.CONFIRMATION);
                        info.setTimeout(3000);
                        Midlet.md.disp.setCurrent(info,new CompetenceList("Choix de Compétence", List.MULTIPLE, prenomtxt));
                    }
                    else if (str.toString().equals("email used"))
                    {
                        info= new Alert("Email utilisé !", "", null, AlertType.ERROR);
                        info.setTimeout(3000);
                        Midlet.md.disp.setCurrent(info,new Inscription("", nomtxt, prenomtxt, pwd, teltxt)); 
                    }
                }       
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                passError="Vérifier votre mot de passe"; 
                info = new Alert("Error Mot De Passe",passError, null, AlertType.ERROR);
                Midlet.md.disp.setCurrent(info,new Inscription("Inscription",nomtxt,prenomtxt,adressetxt,teltxt,mail));
            }
        }
        if(c== connecter)
        {
           info = new Alert("Interface de Connex","S'authentifier", null, AlertType.INFO);
            try {
                Midlet.md.disp.setCurrent(info,new MembreLogin("Login"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
                if(c== retour)
        {
           info = new Alert("Interface de Connex","S'authentifier", null, AlertType.INFO);
            try {
                Midlet.md.disp.setCurrent(info,new MembreLogin("Login"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
