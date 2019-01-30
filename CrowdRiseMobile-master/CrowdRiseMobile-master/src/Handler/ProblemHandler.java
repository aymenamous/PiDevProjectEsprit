/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Membre;
import Entity.Problem;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aymen
 */
public class ProblemHandler extends DefaultHandler{
    
    private Vector vect;
    private Problem currentProb;
    private String curentBalise;
    private Membre currentMembre;

    public ProblemHandler() {
        vect =new Vector();
    }

    public Vector getVect() {
        return vect;
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("probleme"))
        {
            curentBalise="probleme";
            currentProb=new Problem();
            currentMembre=new Membre();
        }
        else if (qName.equals("id"))
        {
            curentBalise="id";
        }
        else if (qName.equals("date"))
        {
            curentBalise="date";
        }
        else if (qName.equals("description"))
        {
            curentBalise="description";
        }
        else if (qName.equals("id_membre"))
        {
            curentBalise="id_membre";
        }
        else if (qName.equals("nom"))
        {
            curentBalise="nom";
        }
        else if (qName.equals("prenom"))
        {
            curentBalise="prenom";
        }
        else if (qName.equals("titre"))
        {
            curentBalise="titre";
        }
        else if (qName.equals("etat"))
        {
            curentBalise="etat";
        }
        
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("probleme"))
        {
            curentBalise="";
            currentProb.setMembre(currentMembre);
            vect.addElement(currentProb);
            currentProb=null;
            currentMembre=null;
        }
        else if (qName.equals("id"))
        {
            curentBalise="";
        }
        else if (qName.equals("date"))
        {
            curentBalise="";
        }
        else if (qName.equals("description"))
        {
            curentBalise="";
        }
        else if (qName.equals("id_membre"))
        {
            curentBalise="";
        }
        else if (qName.equals("nom"))
        {
            curentBalise="";
        }
        else if (qName.equals("prenom"))
        {
            curentBalise="";
        }
        else if (qName.equals("titre"))
        {
            curentBalise="";
        }
        else if (qName.equals("etat"))
        {
            curentBalise="";
        }
    }
 
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (curentBalise.equals("id"))
        {
            currentProb.setId(Integer.parseInt(new String(ch,start,length).trim()));
        }
        else if (curentBalise.equals("date"))
        {
            currentProb.setDate(new String(ch,start,length).trim());
        }
        else if (curentBalise.equals("description"))
        {
            currentProb.setDescription(new String(ch,start,length).trim());
        }
        else if (curentBalise.equals("id_membre"))
        {
            currentMembre.setId(Integer.parseInt(new String(ch,start,length).trim()));
        }
        else if (curentBalise.equals("nom"))
        {
            currentMembre.setNom(new String(ch,start,length).trim());
        }
        else if (curentBalise.equals("prenom"))
        {
            currentMembre.setPrenom(new String(ch,start,length).trim());
        }
        else if (curentBalise.equals("titre"))
        {
            currentProb.setTitre(new String(ch,start,length).trim());
        }
        else if (curentBalise.equals("etat"))
        {
            currentProb.setEtat(Integer.parseInt(new String(ch,start,length).trim()));
        }
    }
    
    
    
}
