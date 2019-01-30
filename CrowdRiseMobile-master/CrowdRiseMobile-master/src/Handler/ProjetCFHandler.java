/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Membre;
import Entity.ProjetCF;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Yosra
 */
public class ProjetCFHandler extends DefaultHandler {

    private Vector vect;
    private ProjetCF currentPCF;
    private String currentBalise;
    private Membre currentMembre;

    public ProjetCFHandler() {
        vect = new Vector();
    }

    public ProjetCF[] getProjetCF() {
        ProjetCF[] PCFTab = new ProjetCF[vect.size()];
        vect.copyInto(PCFTab);
        return PCFTab;
    }

    public Vector getVect() {
        return vect;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("projetcf")) {
            currentPCF = new ProjetCF();
            currentMembre = new Membre();
            currentBalise = "projetcf";

        }

        if (qName.equals("id")) {
            currentBalise = "id";
        }
        if (qName.equals("nom")) {
            currentBalise = "nom";
        }

        if (qName.equals("debut")) {
            currentBalise = "debut";
        }

        if (qName.equals("fin")) {
            currentBalise = "fin";
        }
        if (qName.equals("id_membre")) {
            currentBalise = "id_membre";
        }
        if (qName.equals("description")) {
            currentBalise = "description";
        }
        if (qName.equals("date")) {
            currentBalise = "date";
        }
        

        if (qName.equals("statut")) {
            currentBalise = "statut";
        }
        if(qName.equals("image")){
            currentBalise = "image";
        }
        if(qName.equals("nom_membre")){
            currentBalise = "nom_membre";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("projetcf")) {
            currentPCF.setMembre(currentMembre);
            vect.addElement(currentPCF);
            currentPCF = null;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (currentBalise.equals("id")) {
            currentPCF.setId(Integer.parseInt(value));
        } else if (currentBalise.equals("nom")) {
            currentPCF.setNom(value);
        } else if (currentBalise.equals("debut")) {
            currentPCF.setDebut(value);
        } else if (currentBalise.equals("fin")) {
            currentPCF.setFin(value);
        } else if (currentBalise.equals("id_membre")) {
            currentMembre.setId(Integer.parseInt(value));
            
        } else if (currentBalise.equals("description")) {
            currentPCF.setDescription(value);
        } else if (currentBalise.equals("date")) {
            currentPCF.setDate(value);
        } else if (currentBalise.equals("budget_actuel")) {
            currentPCF.setBudget_actuel(Float.parseFloat(value));
        } else if (currentBalise.equals("budget_finale")) {
            currentPCF.setBudget_final(Integer.parseInt(value));
        }else if (currentBalise.equals("nom_membre")) {
            currentMembre.setNom(value);
        }
        else {
            currentPCF.setImage(value);
        }

    }
}
