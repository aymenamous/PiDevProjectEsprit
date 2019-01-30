/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Membre;
import Entity.Signalisation;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Yosra
 */
public class SignalisationHandler extends DefaultHandler{
    
    Vector vect;
    Signalisation currentsig;
    String currentBalise;
    Membre currentMembre;
    
    public SignalisationHandler()
    {
        vect=new Vector();
    }
    
    public Signalisation[] getSignalisation()
    {
        Signalisation[] tab=new Signalisation[vect.size()];
        vect.copyInto(tab);
        return tab;
    }
    
     public Vector getVect() {
        return vect;
    }
    
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       
       if(qName.equals("signalisation"))
       {
             currentsig = new Signalisation();
             currentMembre=new Membre();
             currentBalise = "reclamation";
       }
       
        if(qName.equals("id"))
       {
           currentBalise="id";
       }
        
        if(qName.equals("raison"))
       {
           currentBalise="raison";
       }
         if(qName.equals("date"))
       {
           currentBalise="date";
       }
         
          if(qName.equals("id_membre"))
       {
           currentBalise="id_membre";
       }
           if(qName.equals("id_commentaire"))
       {
           currentBalise="id_commentaire";
       }
}

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("signalisation"))
        {
            currentsig.setMembre(currentMembre);
            vect.addElement(currentsig);
            currentsig=null;
        }
    }
    
   public void characters(char[] ch, int start, int length) throws SAXException {
      String value = new String(ch,start,length).trim();
          if (currentBalise.equals("id")) {            
            currentsig.setId(Integer.parseInt(value));
          }else     if (currentBalise.equals("raison")) {            
            currentsig.setRaison(value);
          }    if (currentBalise.equals("date")) {            
            currentsig.setDate(value);
          }    if (currentBalise.equals("id_membre")) {            
             currentMembre.setId(Integer.parseInt(value));
          }
   
   }
    
    
}
