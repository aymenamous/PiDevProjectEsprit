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
import Entity.Admin;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AdminHandler extends DefaultHandler
{

    private Vector adminVector;
    private Admin currentAdmin;
    private String currentBalise;

    public AdminHandler() 
    {
        adminVector = new Vector();
    }

    public Vector getAdminvect() 
    {
        return adminVector;
    }
    public Admin getcurrentAdmin()
    {
        return currentAdmin;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
    {
        if (qName.equals("admin")) 
        {
            currentAdmin = new Admin();
            currentBalise = "admin";
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
        if (qName.equals("Email")) 
        {
            currentBalise = "Email";
        }
        if (qName.equals("MotDePasse")) 
        {
            currentBalise = "MotDePasse";
        }
        if (qName.equals("photo")) 
        {
            currentBalise = "photo";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        if (qName.equals("admin")) 
        {
            adminVector.addElement(currentAdmin);
            currentAdmin=null;
            
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
        if (currentBalise.equals("Email")) 
        {
            currentBalise="";
        }
        if (currentBalise.equals("MotDePasse")) 
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
        if (currentAdmin!=null) {
            
            if (currentBalise.equals("Id"))
        {
            String id = new String(ch, start, length).trim();
            currentAdmin.setId(Integer.parseInt(id));
        }
        if (currentBalise.equals("FirstName"))
        {
            String FirstName = new String(ch, start, length).trim();
            currentAdmin.setNom(FirstName);
        }
        if (currentBalise.equals("LastName"))
        {
            String LastName = new String(ch, start, length).trim();
            currentAdmin.setPrenom(LastName);
        }

        if (currentBalise.equals("Email")) 
        {
            String Email = new String(ch, start, length).trim();
            currentAdmin.setEmail(Email);
        }
        if (currentBalise.equals("MotDePasse")) 
        {
            String mdp = new String(ch, start, length).trim();
            currentAdmin.setMdp(mdp);
        }

        if (currentBalise.equals("photo")) 
        {
            String photo = new String(ch, start, length).trim();
            currentAdmin.setPhoto(photo);
        }

        }
        
    }
}
