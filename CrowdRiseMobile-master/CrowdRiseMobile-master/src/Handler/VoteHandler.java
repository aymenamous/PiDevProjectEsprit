/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Entity.vote;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Aymen
 */
public class VoteHandler extends DefaultHandler{
    
    private Vector vect;
    private vote currentVote;
    private String curentBalise;

    public VoteHandler() {
        vect =new Vector();
    }

    public Vector getVect() {
        return vect;
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
    }
 
    public void characters(char[] ch, int start, int length) throws SAXException {
       
    }
    
}
