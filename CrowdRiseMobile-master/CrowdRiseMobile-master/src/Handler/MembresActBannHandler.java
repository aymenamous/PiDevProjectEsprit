package Handler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Membre;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MembresActBannHandler extends DefaultHandler {

    // this will hold all the data we read
    private Vector peopleVector;

    public MembresActBannHandler() {
        peopleVector = new Vector();
    }

    public Membre[] getPeople() {
        Membre[] personTab = new Membre[peopleVector.size()];
        peopleVector.copyInto(personTab);
        return personTab;
    }

    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Membre currentPerson;
    private String currentBalise;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("mydata")) {
            currentPerson = new Membre();
            currentBalise = "mydata";
        }
        if (qName.equals("id")) {
            currentBalise = "id";
        } else if (qName.equals("nom")) {
            currentBalise = "nom";
        } else if (qName.equals("prenom")) {
            currentBalise = "prenom";
        }else if (qName.equals("adresse")) {
            currentBalise = "adresse";
        }else if (qName.equals("email")) {
            currentBalise = "email";
        }else if (qName.equals("telephone")) {
            currentBalise = "telephone";
        }else if (qName.equals("statut")) {
            currentBalise = "statut";
        }

    }

    // endElement is the closing part ("</tagname>"), or the opening part if it ends with "/>"
    // so, a tag in the form "<tagname/>" generates both startElement() and endElement()
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("mydata")) {
            peopleVector.addElement(currentPerson);
            
        }
        
    }

    // "characters" are the text inbetween tags
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch,start,length).trim();
        if (currentBalise.equals("id")) {            
            currentPerson.setId(Integer.parseInt(value));
        } else if (currentBalise.equals("nom")) {
            currentPerson.setNom(value);
        } else if (currentBalise.equals("prenom")) {
            currentPerson.setPrenom(value);
        }else if (currentBalise.equals("adresse")) {
            currentPerson.setAdresse(value);
        }else if (currentBalise.equals("email")) {
            currentPerson.setEmail(value);
        }else if (currentBalise.equals("telephone")) {
            currentPerson.setTelephone(value);
        }else if (currentBalise.equals("statut")) {            
            if(value.equals("0")){
                currentPerson.setStatut(false);
            }else
                currentPerson.setStatut(true);
        }
    }
}
