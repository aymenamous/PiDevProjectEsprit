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
import Entity.Competence;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CompetenceHandler extends DefaultHandler
{

    private Vector competenceVector;
    private Competence currentCompetence;
    private String currentBalise;

    public CompetenceHandler() 
    {
        competenceVector = new Vector();
    }

    public Vector getCompetencevect() 
    {
        return competenceVector;
    }
    public Competence getcurrentCompetence()
    {
        return currentCompetence;
    }


    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
    {
        if (qName.equals("competence")) 
        {
            currentCompetence = new Competence();
            currentBalise = "competence";
        }
        if (qName.equals("nom")) 
        {
            currentBalise = "nom";
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        if (qName.equals("competence")) 
        {
            competenceVector.addElement(currentCompetence);
            currentCompetence=null;
            
        }
         if (currentBalise.equals("nom"))
        {
            currentBalise="";
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException 
    {
        if (currentCompetence!=null) {
            
            if (currentBalise.equals("nom"))
        {
            String nom = new String(ch, start, length).trim();
            currentCompetence.setnom_Competence(nom);
        }
        
    }
    }
}
