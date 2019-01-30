/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Membre;
import Entity.ProjetCF;
import Entity.Reclamation;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Yosra
 */
public class ReclamationHandler extends DefaultHandler{
    
       
     private Vector vect;
    private Reclamation currentPCF;
    private String currentBalise;
    private Membre currentMembre;

  
       
    public ReclamationHandler()
    {
        vect=new Vector();
    }
    
    public Reclamation [] getReclamation()
    {
        Reclamation[] PCFTab=new Reclamation[vect.size()];
        vect.copyInto(PCFTab);
        return PCFTab;
    }
    
    
       public Vector getVect() {
        return vect;
    }

    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
          if (qName.equals("reclamation")) 
        {
             currentPCF = new Reclamation();
             currentMembre=new Membre();
            currentBalise = "reclamation";
            
        }
          
       if(qName.equals("id"))
       {
           currentBalise="id";
       }
       if(qName.equals("id_membre"))
       {
           currentBalise="id_membre";
       }
        
            if(qName.equals("date"))
       {
           currentBalise="date";
       }
        
                 if(qName.equals("reclamationt"))
       {
           currentBalise="reclamationt";
       }
             if(qName.equals("sujet"))
       {
           currentBalise="sujet";
       }

    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
           if (qName.equals("reclamation")) 
        {
//         currentPCF.setMembre(currentMembre);

            vect.addElement(currentPCF);
            currentPCF=null;
        }
    }
 
    public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch,start,length).trim();
        if (currentBalise.equals("id")) {            
            currentPCF.setId(Integer.parseInt(value));
        } else if (currentBalise.equals("id_membre")) {
            currentMembre.setId(Integer.parseInt(value));
        } else if (currentBalise.equals("date")) {
           currentPCF.setDate(value);
        }else if (currentBalise.equals("reclamationt")) {
            currentPCF.setReclamation(value);
        }
    
}

    
    
}
