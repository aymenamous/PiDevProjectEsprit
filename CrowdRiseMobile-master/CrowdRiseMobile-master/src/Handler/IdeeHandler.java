/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.Idee;
import Entity.Membre;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Yosra
 */
public class IdeeHandler extends DefaultHandler {
    
     private Vector vect;
    private  Idee currentPCF;
    private String currentBalise;
    private Membre currentMembre;

    public IdeeHandler() {
              vect=new Vector();
    }

  public Idee[] getIdee()
  {
      Idee[] tab=new Idee[vect.size()];
      vect.copyInto(tab);
      return tab;
  }
  
      public Vector getVect() {
        return vect;
    }

     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
          
         if (qName.equals("idee")) 
        {
            currentPCF = new Idee();
             currentMembre=new Membre();
            currentBalise = "idee";
            
        }
          if(qName.equals("id"))
       {
           currentBalise="id";
       }
       if(qName.equals("nom"))
       {
           currentBalise="nom";
       }
        
            if(qName.equals("debut"))
       {
           currentBalise="debut";
       }
        
                 if(qName.equals("fin"))
       {
           currentBalise="fin";
       }
             if(qName.equals("id_membre"))
       {
           currentBalise="id_membre";
       }
                  if(qName.equals("description"))
       {
           currentBalise="description";
       }
                  if(qName.equals("date"))
       {
           currentBalise="date";
       }
                  
         if(qName.equals("remuneration_totale"))
       {
           currentBalise="remuneration_totale";
       }
                  
       
               
          if(qName.equals("statut"))
       {
           currentBalise="statut";
       }
          
    
     }
     
     
     public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("idee")) 
        {
         currentPCF.setMembre(currentMembre);

            vect.addElement(currentPCF);
            currentPCF=null;
        }
     
     }
     
        public void characters(char[] ch, int start, int length) throws SAXException {
            String value = new String(ch,start,length).trim();
        if (currentBalise.equals("id")) {            
            currentPCF.setId(Integer.parseInt(value));
        } else if (currentBalise.equals("nom")) {
            currentPCF.setNom(value);
        } else if (currentBalise.equals("debut")) {
           currentPCF.setDebut(value);
        }else if (currentBalise.equals("fin")) {
            currentPCF.setFin(value);
        }else if (currentBalise.equals("id_membre")) {
             currentMembre.setId(Integer.parseInt(value));

        }else if (currentBalise.equals("description")) {
            currentPCF.setDescription(value);
        }else if (currentBalise.equals("date")) {   
            currentPCF.setDate(value);
        }          
    }
      
}
