/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

/**
 *
 * @author H4DH
 */
import Entity.Membre;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MembreHandler extends DefaultHandler
{

    private Vector membreVector;
    private Membre currentMembre;
    private String currentBalise;

    public MembreHandler() 
    {
        membreVector = new Vector();

    }

    public Vector getMembrevect() 
    {
        return membreVector;
    }
    public Membre getcurrentMembre()
    {
        return currentMembre;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
    {
        if (qName.equals("membre")) 
        {
            currentMembre = new Membre();
            currentBalise = "membre";
        }
        if (qName.equals("Id")) 
        {
            currentBalise = "Id";
        }
        if (qName.equals("FirstName")) 
        {
            currentBalise = "FirstName";
        }
        if (qName.equals("LastName"))
        {
            currentBalise = "LastName";
        }
        if (qName.equals("Adresse")) 
        {
            currentBalise = "Adresse";
        }
        if (qName.equals("Email")) 
        {
            currentBalise = "Email";
        }
        if (qName.equals("MotDePasse")) 
        {
            currentBalise = "MotDePasse";
        }
        if (qName.equals("telephone")) 
        {
            currentBalise = "telephone";
        }
        if (qName.equals("nbr_solved")) 
        {
            currentBalise = "nbr_solved";
        }
        if (qName.equals("cr")) 
        {
            currentBalise = "cr";
        }
        if (qName.equals("photo")) 
        {
            currentBalise = "photo";
        }
        if (qName.equals("statut")) 
        {
            currentBalise = "statut";
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        if (qName.equals("membre")) 
        {
            membreVector.addElement(currentMembre);
            currentMembre=null;
            
        }
         if (currentBalise.equals("Id"))
        {
            currentBalise="";
        }
        if (currentBalise.equals("FirstName"))
        {
            currentBalise="";
        }
        if (currentBalise.equals("LastName"))
        {
            currentBalise="";
        }
        if (currentBalise.equals("Adresse")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("Email")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("MotDePasse")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("telephone")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("nbr_solved"))
        {
            currentBalise="";
        }
        if (currentBalise.equals("cr")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("photo")) 
        {
            currentBalise="";
        }

    }

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        if (currentMembre!=null) {
            
            if (currentBalise.equals("Id"))
        {
            String id = new String(ch, start, length).trim();
            currentMembre.setId(Integer.parseInt(id));
        }
        if (currentBalise.equals("FirstName"))
        {
            String FirstName = new String(ch, start, length).trim();
            currentMembre.setNom(FirstName);
        }
        if (currentBalise.equals("LastName"))
        {
            String LastName = new String(ch, start, length).trim();
            currentMembre.setPrenom(LastName);
        }
        if (currentBalise.equals("Adresse")) 
        {
            String Adresse = new String(ch, start, length).trim();
            currentMembre.setAdresse(Adresse);
        }
        if (currentBalise.equals("Email")) 
        {
            String Email = new String(ch, start, length).trim();
            currentMembre.setEmail(Email);
        }
        if (currentBalise.equals("MotDePasse")) 
        {
            String mdp = new String(ch, start, length).trim();
            currentMembre.setMdp(mdp);
        }
        if (currentBalise.equals("telephone")) 
        {
            String tel = new String(ch, start, length).trim();
            currentMembre.setTelephone(tel);
        }
        if (currentBalise.equals("nbr_solved"))
        {
            String nbr_solved = new String(ch, start, length).trim();
            currentMembre.setNbr_solved(Double.parseDouble(nbr_solved));
        }
        if (currentBalise.equals("cr")) 
        {
            String cr = new String(ch, start, length).trim();
            currentMembre.setCr(Integer.parseInt(cr));
        }
        if (currentBalise.equals("photo")) 
        {
            String photo = new String(ch, start, length).trim();
            currentMembre.setPhoto(photo);
        }

        }
        
    }
}
