/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Commentaire;
import Entity.Membre;
import Entity.Projet;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aymen
 */
public class CommentaireHandler extends DefaultHandler {

    private Vector vect;
    private Commentaire currentComent;
    private String curentBalise;
    private Membre currentMembre;

    public CommentaireHandler() {
        vect = new Vector();
    }

    public Vector getVect() {
        return vect;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("commentaire")) {
            curentBalise = "commentaire";
            currentComent = new Commentaire();
            currentMembre=new Membre();
        } else if (qName.equals("id")) {
            curentBalise = "id";
        } else if (qName.equals("text_commentaire")) {
            curentBalise = "text_commentaire";
        } else if (qName.equals("id_projet")) {
            curentBalise = "id_projet";
        } else if (qName.equals("id_membre")) {
            curentBalise = "id_membre";
        }else if (qName.equals("nom")) {
            curentBalise = "nom";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("commentaire")) {
            curentBalise = "";
            currentComent.setMembre(currentMembre);
            vect.addElement(currentComent);
            currentMembre=null;
            currentComent = null;
        } else if (qName.equals("id")) {
            curentBalise = "";
        } else if (qName.equals("text_commentaire")) {
            curentBalise = "";
        } else if (qName.equals("id_projet")) {
            curentBalise = "";
        } else if (qName.equals("id_membre")) {
            curentBalise = "";
        }else if (qName.equals("nom")) {
            curentBalise = "";
        }
        
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (curentBalise.equals("id")) {
            currentComent.setId(Integer.parseInt(new String(ch, start, length).trim()));
        } else if (curentBalise.equals("text_commentaire")) {
            currentComent.setText_commentaire(new String(ch, start, length).trim());
        } else if (curentBalise.equals("id_projet")) {
            currentComent.setProjet(new Projet(Integer.parseInt(new String(ch, start, length).trim())));
        } else if (curentBalise.equals("id_membre")) {
            currentMembre.setId(Integer.parseInt(new String(ch, start, length).trim()));
        }else if (curentBalise.equals("nom"))
        {
            currentMembre.setNom(new String(ch, start, length).trim());
        }
        
    }

}
